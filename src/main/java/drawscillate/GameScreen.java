package drawscillate;

import processing.core.PApplet;
import processing.core.PGraphics;
import processing.core.PImage;
import processing.sound.SinOsc;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Optional;

import static javax.swing.JOptionPane.YES_NO_OPTION;
import static javax.swing.JOptionPane.getRootFrame;
import static javax.swing.JOptionPane.showConfirmDialog;
import static processing.core.PConstants.CLOSE;
import static processing.core.PConstants.HAND;

public class GameScreen implements IScreen, OptionsScreenObserver {
    private PApplet applet;
    private String difficultySelection;
    private String shapeSelection;
    private SinOsc[] sineWaves; // Array of sines
    private float[] sineFreq; // Array of frequencies
    private int numSines = 5; // Number of oscillators to use
    int redColor = 0;
    int greenColor = 0;
    int blueColor = 0;
    int[] pixelsFrame;
    float red;
    float green;
    float blue;
    boolean gameOver = false;
    boolean startPointRecorded = false;
    int startPointX;
    int startPointY;
    int strokeWeight;
    int [][] starCheckPoints;
    int [][] heartCheckPoints;
    private PGraphics graphics;
    private boolean selectionComplete = false;
    private ArrayList traceX = new ArrayList();
    private ArrayList traceY = new ArrayList();
    private boolean firstTime = true;

    GameScreen(PApplet applet) {
        this.applet = applet;
        sineWaves = new SinOsc[numSines]; // Initialize the oscillators
        sineFreq = new float[numSines]; // Initialize array for Frequencies

        for (int i = 0; i < numSines; i++) {
            // Calculate the amplitude for each oscillator
            double sineVolume = (1.0 / numSines) / (i + 1);
            // Create the oscillators
            sineWaves[i] = new SinOsc(applet);
            // Start Oscillators
            sineWaves[i].play();
            // Set the amplitudes for all oscillators
            sineWaves[i].amp((float) sineVolume);
        }
    }

    @Override
    public void mousePressed() {
        if (selectionComplete) {
            if (whiteBackground()) {
                traceX.add(applet.mouseX);
                traceY.add(applet.mouseY);
            }
        }
    }

    @Override
    public void display() {
        if (firstTime) {
            graphics = applet.createGraphics(500, 500);
            selectionComplete = true;
            switch (shapeSelection) {
                case "Star":
                    drawStar(difficultySelection);
                    break;
                case "Heart":
                    drawHeart(difficultySelection);
                    break;
            }
            firstTime = false;
        }

        // Map mouseY from 0 to 1
        float yoffset = PApplet.map(applet.mouseY, 0, applet.height, 0, 1);
        // Map mouseY logarithmically to 150 - 1150 to create a base frequency range
        float frequency = PApplet.pow(1000, yoffset) + 150;
        // Use mouseX mapped from -0.5 to 0.5 as a detune argument
        float detune = PApplet.map(applet.mouseX, 0, applet.width, -0.5f, 0.5f);

        for (int i = 0; i < numSines; i++) {
            sineFreq[i] = frequency * (i + 1 * detune);
            // Set the frequencies for all oscillators
            sineWaves[i].freq(sineFreq[i]);
        }

        if (applet.mousePressed) {
            applet.stroke(redColor, greenColor, blueColor);
            applet.strokeWeight(5);

            if (!gameOver && selectionComplete) {
                applet.line(applet.mouseX, applet.mouseY, applet.pmouseX, applet.pmouseY);
                hasLineReachedCheckPoint();
                if (!startPointRecorded) {
                    startPointX = applet.mouseX;
                    startPointY = applet.mouseY;
                    startPointRecorded = true;
                    System.out.println("Start x :" + startPointX);
                    System.out.println("Start y :" + startPointY);
                }
            }

            if (!whiteBackground()) {
                gameOver = true;
                playSound("lose.wav");
                replayOption("Better luck next time!");
            }
        }
        if (selectionComplete) {
            if (allCheckPointsReached() && startReached()) {
                playSound("win.wav");
                System.out.println("Game successfully completed");
                replayOption("Congratulations! You Won!");
            }
        }

        if (applet.keyPressed) {
            switch (applet.key) {
                case 'r':
                    changeCursorAndColor("apple.png", 255, 0, 0);
                    break;
                case 'b':
                    changeCursorAndColor("water.png", 0, 0, 255);
                    break;
                case 'g':
                    changeCursorAndColor("grapes.png", 0, 255, 0);
                    break;
                case ' ':
                    changeCursorAndColor(null, 0, 0, 0);
                    break;
                case 'o':
                    changeCursorAndColor("orange.png", 255, 165, 0);
                    break;
                case 'p':
                    changeCursorAndColor("eggplant.png", 147, 112, 219);
                    break;
                case 'y':
                    changeCursorAndColor("banana.png", 255, 255, 51);
                    break;
            }
        }
    }

    private void playSound(String s) {
        try {
            Clip clip = AudioSystem.getClip();
            clip.open(AudioSystem.getAudioInputStream(getClass().getResource("/" + s)));
            clip.start();
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            e.printStackTrace();
        }
    }

    /**
     * @return If the start point been visited again
     */
    private boolean startReached() {
        return isPointInCircle(startPointX, startPointY, applet.mouseX, applet.mouseY,100) == 1;
    }

    private void replayOption(String string){
        int replay = showConfirmDialog(null, "Wanna Replay?", string, YES_NO_OPTION);
        if (replay == 0) {
            System.out.println("REPLAY");
            applet.setup();
            return;
        }
        if (replay == 1) {
            System.out.println("EXIT");
            applet.exit();
            return;
        }

        getRootFrame().dispose();
        System.out.println(replay);
    }

    @Override
    public void update2(String difficultySelection, String shapeSelection) {
        this.difficultySelection = difficultySelection;
        this.shapeSelection = shapeSelection;
    }

    /**
     * @return If all check points have been reached
     */
    private boolean allCheckPointsReached() {
        if (shapeSelection.equals("Star")) {
            for(int i =0;i<10;i++) {
                if(starCheckPoints[i][2] !=1) {
                    return false;
                }
            }
        } else if (shapeSelection.equals("Heart")) {
            for(int i =0;i<5;i++) {
                if(heartCheckPoints[i][2] !=1) {
                    return false;
                }
            }
        }
        return true;
    }

    private void drawStar(String difficultySelection) {
        graphics.beginDraw();
        graphics.background(51);
        graphics.fill(102);
        graphics.stroke(255);
        strokeWeight = getStrokeWeight(difficultySelection);
        graphics.strokeWeight(strokeWeight);
        graphics.beginShape();
        starCheckPoints = new int[10][3];
        int startX = 550 / 2 - 47 / 2;
        int startY = 160 / 2 - 45 / 2;
        graphics.vertex(startX, startY);
        insertCheckPoint(startX, startY, 0, starCheckPoints);
        graphics.vertex(startX + 60, startY + 140);
        insertCheckPoint(startX + 60, startY + 140, 1, starCheckPoints);
        graphics.vertex(startX + 230, startY + 150);
        insertCheckPoint(startX + 230, startY + 150, 2, starCheckPoints);
        graphics.vertex(startX + 100, startY + 240);
        insertCheckPoint(startX + 100, startY + 240, 3, starCheckPoints);
        graphics.vertex(startX + 180, startY + 380);
        insertCheckPoint(startX + 180, startY + 380, 4, starCheckPoints);
        graphics.vertex(startX, startY + 300);
        insertCheckPoint(startX, startY + 300, 5, starCheckPoints);
        graphics.vertex(startX - 180, startY + 380);
        insertCheckPoint(startX- 180, startY + 380, 6, starCheckPoints);
        graphics.vertex(startX - 100, startY + 240);
        insertCheckPoint(startX -100, startY + 240, 7, starCheckPoints);
        graphics.vertex(startX - 230, startY + 150);
        insertCheckPoint(startX -230, startY + 150, 8, starCheckPoints);
        graphics.vertex(startX - 60, startY + 140);
        insertCheckPoint(startX - 60, startY + 140, 9, starCheckPoints);
        graphics.endShape(CLOSE);
        graphics.endDraw();
        startPointRecorded = false;
        applet.image(graphics, 0, 0);
    }

    private void drawHeart(String difficultySelection) {
        graphics.beginDraw();
        graphics.background(51);
        graphics.fill(102);
        graphics.stroke(255);
        strokeWeight = getStrokeWeight(difficultySelection);
        graphics.strokeWeight(strokeWeight);
        graphics.beginShape();
        heartCheckPoints = new int [5][3];
        final int x1 = applet.width / 2;
        final int halfHeartWidth = 500;
        final int y1 = 100;
        final int y2 = -50;
        final int y3 = 5;
        final int y4 = 485;
        graphics.vertex(x1, y1);
        graphics.bezierVertex(x1, y2, x1 + halfHeartWidth, y3, x1, y4);
        graphics.bezierVertex(x1 - halfHeartWidth, y3, x1, y2, x1, y1);
        graphics.endShape();
        graphics.endDraw();
        insertCheckPoint(249, 93, 0, heartCheckPoints);
        insertCheckPoint(29, 157, 1, heartCheckPoints);
        insertCheckPoint(474, 158, 2, heartCheckPoints);
        insertCheckPoint(252, 481, 3, heartCheckPoints);
        insertCheckPoint(47, 219, 4, heartCheckPoints);
        startPointRecorded =false;
        applet.image(graphics, 0, 0);
    }

    /**
     * Create the checkpoint array for the given figure
     * @param x The x coordinate
     * @param y The y coordinate
     * @param i The index of the coordinate
     * @param checkpoints The data structure into which the checkpoints should be recorded
     */
    private void insertCheckPoint(int x, int y, int i, int[][] checkpoints) {
        checkpoints[i][0] = x;
        checkpoints[i][1] = y;
        checkpoints[i][2] = 0;
    }

    private int getStrokeWeight(String difficultySelection) {
        switch (difficultySelection) {
            case "Hard":
                return 10;
            case "Normal":
                return 25;
            case "Easy":
                return 50;
        }
        return 10;
    }

    private void changeCursorAndColor(String resourceName, int redColor, int greenColor, int blueColor) {
        final Optional<PImage> imageOptional =
            Optional
                .ofNullable(resourceName)
                .map("/"::concat)
                .map(getClass()::getResource)
                .map(URL::getFile)
                .map(applet::loadImage);
        if (imageOptional.isPresent()) {
            applet.cursor(imageOptional.get());
        } else {
            applet.cursor(HAND);
        }
        this.redColor = redColor;
        this.greenColor = greenColor;
        this.blueColor = blueColor;
    }

    /**
     * Function name - hasLineReachedCheckPoint
     * Description   - check if current point is in the vicinity of some checkpoint
     * @param     - mouseX,mouseY
     * @return        - void
     */
    private void hasLineReachedCheckPoint() {
        if (shapeSelection.equals("Star")) {
            for (int i =0 ;i <10 ;i++) {
                if (starCheckPoints[i][2] != 1) {
                    starCheckPoints[i][2] = isPointInCircle(starCheckPoints[i][0],starCheckPoints[i][1],applet.mouseX,applet.mouseY,strokeWeight*strokeWeight);
                }
            }
        } else if (shapeSelection.equals("Heart")) {
            for (int i =0 ;i <5 ;i++) {
                if (heartCheckPoints[i][2] != 1) {
                    heartCheckPoints[i][2] = isPointInCircle(heartCheckPoints[i][0],heartCheckPoints[i][1],applet.mouseX,applet.mouseY,strokeWeight*strokeWeight);
                }
            }
        }
    }

    /**
     * Function name - isPointInCircle
     * Description   - check if point is within circle with centre i ,j
     * @param     - i,j,mouseX,mouseY
     * @return        - int
     */
    private int isPointInCircle(int i, int j, int mouseX, int mouseY, int radius) {
        int distance = (i-mouseX)*(i-mouseX)+(j-mouseY)*(j-mouseY);
        if (distance <= radius) {
            return 1;
        }
        return 0;
    }

    /**
     * Function name - whiteBackgroud
     * Description   - check if bg colour is white or not
     * @return        - boolean
     */
    private boolean whiteBackground(){
        pixelsFrame = graphics.get().pixels;
        red = applet.red(pixelsFrame[applet.mouseX + applet.mouseY * applet.width]);
        green = applet.green(pixelsFrame[applet.mouseX + applet.mouseY * applet.width]);
        blue = applet.blue(pixelsFrame[applet.mouseX + applet.mouseY * applet.width]);
        if(red == 255 && green == 255 && blue == 255)
            return true;
        else
            return false;
    }
}

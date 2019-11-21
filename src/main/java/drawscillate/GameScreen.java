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
import static processing.core.PConstants.HAND;

public class GameScreen implements IScreen, OptionsScreenObserver,IGameLogicObserver {
    private PApplet applet;
    private GameLogicManager gameManager;
    private String difficultySelection;
    private String shapeSelection;
    private SinOsc[] sineWaves; // Array of sines
    private float[] sineFreq; // Array of frequencies
    private int numSines = 5; // Number of oscillators to use
    private float yoffset;
    private float frequency;
    private float detune;
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
    int [][] checkpoints;
    private PGraphics graphics;
    private ArrayList traceX = new ArrayList();
    private ArrayList traceY = new ArrayList();
    private boolean firstTime = true;
    private ShapeFactory shapeFactory;
    private IShapes shapes;
    CustomizeLine customizeLine;
    IColorCommand showRedColor;
    IColorCommand showYellowColor;
    IColorCommand showGreenColor;
    IColorCommand showBlueColor;
    IColorCommand showOrangeColor;
    IColorCommand showPurpleColor;
    IColorCommand showBlackColor;

    GameScreen(PApplet applet) {
        this.applet = applet;
        graphics = applet.createGraphics(500, 500);
        shapeFactory = new ShapeFactory();
        gameManager = new GameLogicManager(applet);
        gameManager.registerObserver((IGameLogicObserver)this);
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
        
        customizeLine = new CustomizeLine();

        initializeCommands();
        setReceivers(showRedColor, "apple.png", 255, 0, 0);
        setReceivers(showYellowColor, "banana.png", 255, 255, 51);
        setReceivers(showGreenColor, "grapes.png", 0, 255, 0);
        setReceivers(showBlueColor, "water.png", 0, 0, 255);
        setReceivers(showOrangeColor, "orange.png", 255, 165, 0);
        setReceivers(showPurpleColor, "eggplant.png", 147, 112, 219);
        setReceivers(showBlackColor, null, 0, 0, 0);

        colorItem('r', showRedColor);
        colorItem('y', showYellowColor);
        colorItem('b', showBlueColor);
        colorItem('p', showPurpleColor);
        colorItem('g', showGreenColor);
        colorItem(' ', showBlackColor);
        colorItem('o', showOrangeColor);
    }

    @Override
    public void mouseDragged() {
        traceX.add(applet.mouseX);
        traceY.add(applet.mouseY);
    }

    @Override
    public void display() {
        if (firstTime) {
            shapes = shapeFactory.getShape(shapeSelection);
            if(shapes != null) {
                strokeWeight = getStrokeWeight(difficultySelection);
                checkpoints = shapes.draw(strokeWeight, graphics, applet);
                startPointRecorded = false;
            }
            firstTime = false;
        }

        // Map mouseY from 0 to 1
        yoffset = PApplet.map(applet.mouseY, 0, applet.height, 0, 1);
        // Map mouseY logarithmically to 150 - 1150 to create a base frequency range
        frequency = PApplet.pow(1000, yoffset) + 150;
        // Use mouseX mapped from -0.5 to 0.5 as a detune argument
        detune = PApplet.map(applet.mouseX, 0, applet.width, -0.5f, 0.5f);

        for (int i = 0; i < numSines; i++) {
            sineFreq[i] = frequency * (i + 1 * detune);
            // Set the frequencies for all oscillators
            sineWaves[i].freq(sineFreq[i]);
        }

        if (applet.mousePressed) {
            mousePressed();
        }
            

        if (applet.keyPressed) {
            customizeLine.setKey(applet.key);
            customizeLine.initialize();
        }
    }
    
    public void mousePressed() {
        applet.stroke(redColor, greenColor, blueColor);
        applet.strokeWeight(5);

        if (!gameOver) {
            applet.line(applet.mouseX, applet.mouseY, applet.pmouseX, applet.pmouseY);
            gameManager.mouseEvent(graphics);
            hasLineReachedCheckPoint();
            if (!startPointRecorded) {
                startPointX = applet.mouseX;
                startPointY = applet.mouseY;
                startPointRecorded = true;
                System.out.println("Start x :" + startPointX);
                System.out.println("Start y :" + startPointY);
            }
            if (allCheckPointsReached() && startReached()) {
                playSound("win.wav");
                System.out.println("Game successfully completed");
                replayOption("Congratulations! You Won!");
            }
        }
        else {
            playSound("lose.wav");
            replayOption("Better luck next time!");
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
        if(checkpoints.length==0)
            return false;
        for(int i=0;i< checkpoints.length ; i++) {
            if(checkpoints[i][2] != 1) {
                return false;
            }
        }
        return true;
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
    * 
    * Function name - hasLineReachedCheckPoint
    * Description   - check if current point is in the vicinity of some checkpoint
    * @param     - mouseX,mouseY
    * @return        - void
    */
    private void hasLineReachedCheckPoint() {
        
        for(int i=0; i < checkpoints.length ;i++) {
            if (checkpoints[i][2] != 1) { 
                checkpoints[i][2] = isPointInCircle(checkpoints[i][0],checkpoints[i][1],applet.mouseX,applet.mouseY,strokeWeight*strokeWeight);
               }
         }
    }

    /**
     *
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
     * @param key    Various keyboard keys to change color
     * 
     * @param icolor map keys to their corresponding menu
     */
    private void colorItem(char key, IColorCommand icolor) {
        customizeLine.setColorItem(key, icolor);
    }
    
    /**
     * Set Receivers for ColorCommand
     * 
     * @param m            set a receiver for command
     * 
     * @param resourceName name of image to be loaded
     * 
     * @param redColor     red pixels
     * 
     * @param redColor     green pixels
     * 
     * @param redColor     blue pixels
     */
    private void setReceivers(IColorCommand m, String resourceName, int redColor, int greenColor, int blueColor) {
        m.setReceiver(new IColorReceiver() {
            /** Command Action */
            public void doAction() {
                changeCursorAndColor(resourceName, redColor, greenColor, blueColor);
            }
        });
    }

    /**
     * create command to be mapped to options
     */
    private void initializeCommands() {
        showRedColor = new ColorCommand();
        showYellowColor = new ColorCommand();
        showBlueColor = new ColorCommand();
        showPurpleColor = new ColorCommand();
        showGreenColor = new ColorCommand();
        showBlackColor = new ColorCommand();
        showOrangeColor = new ColorCommand();
    }



    @Override
    public void isGameOver(boolean gameOver) {
        this.gameOver = gameOver;
        
    }
}

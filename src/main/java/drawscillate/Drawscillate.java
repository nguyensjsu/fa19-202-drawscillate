package drawscillate;

import controlP5.CColor;
import controlP5.ControlP5;
import controlP5.ScrollableList;
import processing.core.PApplet;
import processing.core.PGraphics;
import processing.core.PImage;
import processing.sound.SinOsc;

import java.net.URL;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class Drawscillate extends PApplet {
    private SinOsc[] sineWaves; // Array of sines
    private float[] sineFreq; // Array of frequencies
    private int numSines = 5; // Number of oscillators to use
    private ControlP5 cp5;
    private String shapeSelection = "";
    private String difficultySelection = "";
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
    int shapeChosen = 0;
    int strokeWeight;
    int [][] starCheckPoints;
    int [][] heartCheckPoints;
    private PGraphics graphics;
    private boolean selectionComplete = false;
    
    CustomizeLine customizeLine;
    
    IColorCommand showRedColor;
    IColorCommand showYellowColor;
    IColorCommand showGreenColor;
    IColorCommand showBlueColor;
    IColorCommand showOrangeColor;
    IColorCommand showPurpleColor;
    IColorCommand showBlackColor;
    
    public Drawscillate() {
        customizeLine = new CustomizeLine();
        
        initializeCommands();
        setReceivers(showRedColor,"apple.png",255, 0, 0);
        setReceivers(showYellowColor, "banana.png",255,255,51);
        setReceivers(showGreenColor,"grapes.png",0, 255, 0);
        setReceivers(showBlueColor, "water.png",0,0,255);
        setReceivers(showOrangeColor, "orange.png",255,165,0);
        setReceivers(showPurpleColor,"eggplant.png",147, 112, 219);
        setReceivers(showBlackColor, null, 0,0,0);
        
        colorItem('r', showRedColor);
        colorItem('y', showYellowColor);
        colorItem('b', showBlueColor);
        colorItem('p', showPurpleColor);
        colorItem('g', showGreenColor);
        colorItem(' ', showBlackColor);
        colorItem('o', showOrangeColor);
    }
    
    private void colorItem(char key, IColorCommand icolor) {
        customizeLine.setColorItem(key, icolor);
    }
    
    
    private void setReceivers(IColorCommand m, String resourceName, int redColor, int greenColor, int blueColor ) {
        m.setReceiver(new IColorReceiver() {
            /** Command Action */
            public void doAction() {
                changeCursorAndColor(resourceName, redColor, greenColor, blueColor);
            }
        });
    }
    
    private void initializeCommands() {
        showRedColor = new ColorCommand();
        showYellowColor = new ColorCommand(); 
        showBlueColor = new ColorCommand();
        showPurpleColor = new ColorCommand();
        showGreenColor = new ColorCommand();
        showBlackColor = new ColorCommand();
        showOrangeColor = new ColorCommand();
    }

    public void settings() {
        size(500, 500);
    }

    public void setup() {
        background(255);
        graphics = createGraphics(500, 500);
        sineWaves = new SinOsc[numSines]; // Initialize the oscillators
        sineFreq = new float[numSines]; // Initialize array for Frequencies

        for (int i = 0; i < numSines; i++) {
            // Calculate the amplitude for each oscillator
            double sineVolume = (1.0 / numSines) / (i + 1);
            // Create the oscillators
            sineWaves[i] = new SinOsc(this);
            // Start Oscillators
            sineWaves[i].play();
            // Set the amplitudes for all oscillators
            sineWaves[i].amp((float) sineVolume);
        }

        // Drop Down
        cp5 = new ControlP5(this);
        List shapes = Arrays.asList("Star", "Rectangle", "Heart", "Circle");
        /* add a ScrollableList, by default it behaves like a DropdownList */
        cp5.addScrollableList("select_shape")
                .setOpen(false)
                .setPosition(30, 100)
                .setSize(200, 100)
                .setHeight(300)
                .setBarHeight(30)
                .setItemHeight(30)
                .addItems(shapes);
    }

    public void select_difficulty(int n){
        cursor(HAND);
        CColor c = new CColor();
        c.setBackground(color(255, 0, 0));
        difficultySelection = cp5.get(ScrollableList.class, "select_difficulty").getItem(n).get("name").toString();
        System.out.println(difficultySelection);
        cp5.get(ScrollableList.class, "select_difficulty").hide();

        selectionComplete = true;

        switch (shapeSelection) {
            case "Heart":
                drawHeart(difficultySelection);
                break;
            case "Star":
                drawStar(difficultySelection);
                break;
        }
    }

    public void select_shape(int n) {
        cursor(HAND);
        CColor c = new CColor();
        c.setBackground(color(255, 0, 0));
        shapeSelection = cp5.get(ScrollableList.class, "select_shape").getItem(n).get("name").toString();
        System.out.println(shapeSelection);
        cp5.get(ScrollableList.class, "select_shape").hide();

        //Difficulty drop down
        List difficulty = Arrays.asList("Easy", "Medium", "Hard");
        cp5.addScrollableList("select_difficulty")
                .setOpen(false)
                .setPosition(270, 100)
                .setSize(200, 100)
                .setHeight(300)
                .setBarHeight(30)
                .setItemHeight(30)
                .addItems(difficulty);

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
        shapeChosen = 1;
        startPointRecorded = false;
        image(graphics, 0, 0);
    }

    /**
     * Create the checkpoint array for the given figure
     * @param x The x coordinate
     * @param y The y coordinate
     * @param i The index of the coordinate
     * @param checkpoints The data structure into which the checkpoints should be recorded
     */
    private void insertCheckPoint(int x, int y, int i, int[][] checkpoints) {
        checkpoints[i][0] =x;
        checkpoints[i][1] =y;
        checkpoints[i][2] =0;  
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
        final int x1 = width / 2;
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
        shapeChosen = 1;
        startPointRecorded =false;
        image(graphics, 0, 0);
    }

    private int getStrokeWeight(String difficultySelection) {
        switch (difficultySelection){
            case "Hard":
                return 10;
            case "Medium":
                return 25;
            case "Easy":
                return 50;
        }return 10;
    }


    public void draw() {
        // Map mouseY from 0 to 1
        float yoffset = map(mouseY, 0, height, 0, 1);
        // Map mouseY logarithmically to 150 - 1150 to create a base frequency range
        float frequency = pow(1000, yoffset) + 150;
        // Use mouseX mapped from -0.5 to 0.5 as a detune argument
        float detune = map(mouseX, 0, width, -0.5f, 0.5f);

        for (int i = 0; i < numSines; i++) {
            sineFreq[i] = frequency * (i + 1 * detune);
            // Set the frequencies for all oscillators
            sineWaves[i].freq(sineFreq[i]);
        }

        if (mousePressed) {
            stroke(redColor, greenColor, blueColor);
            strokeWeight(5);
            
            if (!gameOver && selectionComplete) {
                line(mouseX, mouseY, pmouseX, pmouseY);
                hasLineReachedCheckPoint();
                if (!startPointRecorded) {
                    startPointX = mouseX;
                    startPointY = mouseY;
                    startPointRecorded = true;
                    System.out.println("Start x :" + startPointX);
                    System.out.println("Start y :" + startPointY);
                }
            }

            if (shapeChosen == 1) {
                pixelsFrame = graphics.get().pixels;
                red = red(pixelsFrame[mouseX + mouseY * width]);
                green = green(pixelsFrame[mouseX + mouseY * width]);
                blue = blue(pixelsFrame[mouseX + mouseY * width]);
                if (red != 255.0 && blue != 255.0 && green != 255) {
                    gameOver = true;
                }
            }
        }
        if (selectionComplete) {
            if (allCheckPointsReached() && startReached()) {
                System.out.println("Game successfully completed");
            }
        }

        if (keyPressed) {
            
            customizeLine.setKey(key);
            customizeLine.initialize();
         
        }
    }
    
    private void changeCursorAndColor(String resourceName, int redColor, int greenColor, int blueColor) {
        final Optional<PImage> imageOptional =
            Optional
                .ofNullable(resourceName)
                .map("/"::concat)
                .map(getClass()::getResource)
                .map(URL::getFile)
                .map(this::loadImage);
        if (imageOptional.isPresent()) {
            cursor(imageOptional.get());
        } else {
            cursor(HAND);
        }
        this.redColor = redColor;
        this.greenColor = greenColor;
        this.blueColor = blueColor;
    }

    /**
     * @return If the start point been visited again
     */
    private boolean startReached() {

        
        return isPointInCircle(startPointX, startPointY, mouseX, mouseY,100) == 1;

        
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

    /**
    * 
    * Function name - hasLineReachedCheckPoint
    * Description   - check if current point is in the vicinity of some checkpoint
    * @param     - mouseX,mouseY
    * @return        - void
    */
    private void hasLineReachedCheckPoint() {
       if (shapeSelection == "Star") {
           for (int i =0 ;i <10 ;i++) {
              if (starCheckPoints[i][2] != 1) { 
               starCheckPoints[i][2] = isPointInCircle(starCheckPoints[i][0],starCheckPoints[i][1],mouseX,mouseY,strokeWeight*strokeWeight);
              }
           }
       } else if (shapeSelection == "Heart") {
           for (int i =0 ;i <5 ;i++) {
               if (heartCheckPoints[i][2] != 1) { 
                   heartCheckPoints[i][2] = isPointInCircle(heartCheckPoints[i][0],heartCheckPoints[i][1],mouseX,mouseY,strokeWeight*strokeWeight);
               }
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

    public static void main(String[] args) {
        String[] processingArgs = { "MySketch" };
        Drawscillate mySketch = new Drawscillate();
        PApplet.runSketch(processingArgs, mySketch);
    }
}

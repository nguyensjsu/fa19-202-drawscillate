package drawscillate;

import controlP5.CColor;
import controlP5.ControlP5;
import controlP5.ScrollableList;
import processing.core.PApplet;
import processing.sound.SinOsc;

import java.util.Arrays;
import java.util.List;

public class Drawscillate extends PApplet {
    private SinOsc[] sineWaves; // Array of sines
    private float[] sineFreq; // Array of frequencies
    private int numSines = 5; // Number of oscillators to use
    private ControlP5 cp5;
    private String dropDownSelection = "";

    public void settings(){
        size(500, 500);
    }

    public void setup() {
        background(255);

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

        //Drop Down
        cp5 = new ControlP5(this);
        List l = Arrays.asList("Star", "Rectangle", "Heart", "Circle");
        /* add a ScrollableList, by default it behaves like a DropdownList */
        cp5.addScrollableList("dropdown")
                .setPosition(100, 100)
                .setSize(200, 100)
                .setBarHeight(20)
                .setItemHeight(20)
                .addItems(l);
    }

    private void drawStar() {
        background(51);
        fill(102);
        stroke(255);
        strokeWeight(10);
        beginShape();
        int startX = 550 / 2 - 47 / 2;
        int startY = 160 / 2 - 45 / 2;
        vertex(startX, startY);
        vertex(startX + 60, startY + 140);
        vertex(startX + 230, startY + 150);
        vertex(startX + 100, startY + 240);
        vertex(startX + 180, startY + 380);
        vertex(startX, startY + 300);
        vertex(startX - 180, startY + 380);
        vertex(startX - 100, startY + 240);
        vertex(startX - 230, startY + 150);
        vertex(startX - 60, startY + 140);
        endShape(CLOSE);
    }

    private void drawHeart() {
        background(51);
        fill(102);
        stroke(255);
        strokeWeight(10);
        beginShape();
        final int x1 = width / 2;
        final int halfHeartWidth = 500;
        final int y1 = 100;
        final int y2 = -50;
        final int y3 = 5;
        final int y4 = 485;
        vertex(x1, y1);
        bezierVertex(x1, y2, x1 + halfHeartWidth, y3, x1, y4);
        bezierVertex(x1 - halfHeartWidth, y3, x1, y2, x1, y1);
        endShape();
    }

    public void draw() {
        //Map mouseY from 0 to 1
        float yoffset = map(mouseY, 0, height, 0, 1);
        //Map mouseY logarithmically to 150 - 1150 to create a base frequency range
        float frequency = pow(1000, yoffset) + 150;
        //Use mouseX mapped from -0.5 to 0.5 as a detune argument
        float detune = map(mouseX, 0, width, -0.5f, 0.5f);

        for (int i = 0; i < numSines; i++) {
            sineFreq[i] = frequency * (i + 1 * detune);
            // Set the frequencies for all oscillators
            sineWaves[i].freq(sineFreq[i]);
        }

    }

    public void dropdown(int n) {
        /* request the selected item based on index n */
        CColor c = new CColor();
        c.setBackground(color(255,0,0));
        dropDownSelection = cp5.get(ScrollableList.class, "dropdown").getItem(n).get("name").toString();
        System.out.println(dropDownSelection);
        cp5.get(ScrollableList.class, "dropdown").hide();
        switch(dropDownSelection){
            case "Heart":
                drawHeart();break;
        }

    }

    public static void main(String[] args){
        String[] processingArgs = {"MySketch"};
        Drawscillate mySketch = new Drawscillate();
        PApplet.runSketch(processingArgs, mySketch);
    }
}

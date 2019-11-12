package drawscillate;

import processing.core.PApplet;
import processing.sound.*;
import controlP5.*;
import java.util.*;

public class Drawscillate extends PApplet {
    private SinOsc[] sineWaves; // Array of sines
    private float[] sineFreq; // Array of frequencies
    private int numSines = 5; // Number of oscillators to use

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
        size(400, 400);
        cp5 = new ControlP5(this);
        List l = Arrays.asList("Star", "Rectangle", "Heart", "Circle");
        /* add a ScrollableList, by default it behaves like a DropdownList */
        cp5.addScrollableList("dropdown")
         .setPosition(100, 100)
         .setSize(200, 100)
         .setBarHeight(20)
         .setItemHeight(20)
         .addItems(l)
         // .setType(ScrollableList.LIST) // currently supported DROPDOWN and LIST
         ;
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
        
        //Drop Down
        background(240);
    }

    public void mousePressed(){
        background(64);
    }
    
    public void dropdown(int n) {
        /* request the selected item based on index n */
        println(n, cp5.get(ScrollableList.class, "dropdown").getItem(n));

        CColor c = new CColor();
        c.setBackground(color(255,0,0));
        cp5.get(ScrollableList.class, "dropdown").getItem(n).put("color", c);
    }

    public static void main(String[] args){
        String[] processingArgs = {"MySketch"};
        Drawscillate mySketch = new Drawscillate();
        PApplet.runSketch(processingArgs, mySketch);
    }
}

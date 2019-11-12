package drawscillate;

import processing.core.PApplet;
import processing.sound.*;

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

        drawHeart();
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

    public void mousePressed(){
        background(64);
    }

    public static void main(String[] args){
        String[] processingArgs = {"MySketch"};
        Drawscillate mySketch = new Drawscillate();
        PApplet.runSketch(processingArgs, mySketch);
    }
}
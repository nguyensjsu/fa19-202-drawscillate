package drawscillate;

import processing.core.PApplet;
import processing.core.PGraphics;

public class Drawscillate2 extends PApplet {
    int redColor = 0;
    int greenColor = 0;
    int blueColor = 0;
    boolean startPointRecorded = false;
    int shapeChosen = 0;
    int strokeWeight;
    int [][] starCheckPoints;
    private PGraphics graphics;

    public void settings() {
        size(500, 500);
    }

    public void setup() {
        background(255);
        graphics = createGraphics(500, 500);
        drawStar();
    }

    private void drawStar() {
        graphics.beginDraw();
        graphics.background(51);
        graphics.fill(102);
        graphics.stroke(255);
        strokeWeight = 10;
        graphics.strokeWeight(strokeWeight);
        graphics.beginShape();
        starCheckPoints = new int[10][3];
        int startX = 550 / 2 - 47 / 2;
        int startY = 160 / 2 - 45 / 2;
        graphics.vertex(startX, startY);
        graphics.vertex(startX + 60, startY + 140);
        graphics.vertex(startX + 230, startY + 150);
        graphics.vertex(startX + 100, startY + 240);
        graphics.vertex(startX + 180, startY + 380);
        graphics.vertex(startX, startY + 300);
        graphics.vertex(startX - 180, startY + 380);
        graphics.vertex(startX - 100, startY + 240);
        graphics.vertex(startX - 230, startY + 150);
        graphics.vertex(startX - 60, startY + 140);
        graphics.endShape(CLOSE);
        graphics.endDraw();
        shapeChosen = 1;
        startPointRecorded = false;
        image(graphics, 0, 0);
    }

    public void draw() {
        if (mousePressed) {
            stroke(redColor, greenColor, blueColor);
            strokeWeight(5);
            line(mouseX, mouseY, pmouseX, pmouseY);
        }
    }

    public void mousePressed() {
//        current.touch();
    }

    public static void main(String[] args) {
        String[] processingArgs = { "MySketch" };
        Drawscillate2 mySketch = new Drawscillate2();
        PApplet.runSketch(processingArgs, mySketch);
    }
}

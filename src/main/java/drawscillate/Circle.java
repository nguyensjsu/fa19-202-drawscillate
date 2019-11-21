package drawscillate;

import processing.core.PApplet;
import processing.core.PGraphics;

public class Circle implements Shapes{

    @Override
    public int[][] draw(int weight, PGraphics graphics, PApplet applet) {
        graphics.beginDraw();
        graphics.background(51);
        graphics.fill(102);
        graphics.stroke(255);
        graphics.strokeWeight(weight);
        graphics.beginShape();
        graphics.ellipse(250, 250, 400, 400);
        graphics.endShape();
        graphics.endDraw();
        applet.image(graphics, 0, 0);
        int[][] checkpoints = new int[0][0];
        return checkpoints;
    }
}

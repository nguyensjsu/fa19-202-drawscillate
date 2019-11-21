package drawscillate;

import processing.core.PApplet;
import processing.core.PGraphics;

public class Circle implements Shapes{

    int [][] checkpoints = new int [4][3];
    private PApplet applet;

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

        insertCheckPoint(50, 250, 0);
        insertCheckPoint(250, 50, 1);
        insertCheckPoint(450, 250, 2);
        insertCheckPoint(250, 450, 3);

        applet.image(graphics, 0, 0);
        return checkpoints;
    }

    /**
     * Create the checkpoint array for the given figure
     * @param x The x coordinate
     * @param y The y coordinate
     * @param i The index of the coordinate
     */
    private void insertCheckPoint(int x, int y, int i) {
        checkpoints[i][0] = x;
        checkpoints[i][1] = y;
        checkpoints[i][2] = 0;
    }

}

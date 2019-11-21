package drawscillate;

import processing.core.PApplet;
import processing.core.PGraphics;

public class Rectangle implements Shapes{

    int [][] checkpoints = new int [4][3];

    @Override
    public int[][] draw(int weight, PGraphics graphics, PApplet applet) {
        graphics.beginDraw();
        graphics.background(51);
        graphics.fill(102);
        graphics.stroke(255);
        graphics.strokeWeight(weight);
        graphics.beginShape();
        graphics.rect(50, 100, 400, 280);
        graphics.endShape();
        graphics.endDraw();

        insertCheckPoint(50, 100, 0);
        insertCheckPoint(450, 100, 1);
        insertCheckPoint(50, 380, 2);
        insertCheckPoint(450, 380, 3);
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

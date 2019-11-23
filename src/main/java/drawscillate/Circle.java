package drawscillate;

import processing.core.PApplet;
import processing.core.PGraphics;

public class Circle extends Shapes implements IShapes {

    @Override
    public int[][] draw(int weight, PGraphics graphics, PApplet applet) {
        graphics.beginDraw();
        graphics.background(51);
        graphics.fill(102);
        graphics.stroke(255);
        graphics.strokeWeight(weight);
        graphics.beginShape();
        graphics.ellipse(applet.width/2, applet.height/2, 500, 500);
        graphics.endShape();
        graphics.endDraw();

        setCheckpointCount(4);

        insertCheckPoint(50, 250, 0);
        insertCheckPoint(250, 50, 1);
        insertCheckPoint(450, 250, 2);
        insertCheckPoint(250, 450, 3);

        applet.image(graphics, 0, 0);
        return getCheckpoints();
    }

    @Override
    public String getName() {
        return "Circle";
    }
}

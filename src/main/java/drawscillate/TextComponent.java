package drawscillate;

import static processing.core.PConstants.CENTER;

import processing.core.PApplet;
import processing.core.PConstants;

public class TextComponent implements IDisplayComponent {

    private PApplet applet;
    private int width;
    private int height;
    private int x;
    private int y;
    private int alignX = PConstants.LEFT;
    private int alignY = PConstants.CENTER;
    private String label;
    private float r = 255f;
    private float g = 255f;
    private float b = 255f;
    private float size = 12f;

    TextComponent(PApplet applet, int x, int y, int width, int height, String label) {

        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.label = label;
        this.applet = applet;

    }

    TextComponent(PApplet applet, int x, int y, int width, int height, String label, int alignX) {

        this(applet, x, y, width, height, label);
        this.alignX = alignX;

    }

    TextComponent(PApplet applet, int x, int y, int width, int height, String label, int alignX, int alignY) {

        this(applet, x, y, width, height, label, alignX);
        this.alignY = alignY;

    }

    @Override
    public void display() {
        applet.noFill();
        applet.noStroke();
        applet.rectMode(CENTER);
        applet.rect(x, y, width, height);
        applet.textAlign(alignX, alignY);
        applet.fill(r, g, b);
        applet.textSize(size);
        applet.text(label, x, y, width - 10, height);

    }

    public void setLabel(String label) {
        this.label = label;
    }

    public void setFill(float r, float g, float b) {
        this.r = r;
        this.g = g;
        this.b = b;
    }

    public void setTextSize(float size) {
        this.size = size;
    }

    @Override
    public void addSubComponent(IDisplayComponent c) {
        // TODO Auto-generated method stub

    }

}

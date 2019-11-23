package drawscillate;

import processing.core.PApplet;

class Button {
    int x;
    int y;
    private PApplet applet;
    private String label;

    Button(PApplet applet, String label) {
        this.applet = applet;
        this.label = label;
    }

    void draw() {
        applet.stroke(0);
        applet.strokeWeight(1f);
        applet.fill(200);
        if (over()) {
            applet.fill(255);
        }
        applet.rect(x, y, width(), height());
        applet.fill(0);
        applet.text(label, x + 8, y + 8 + 20);
    }

    boolean over() {
        return applet.mouseX >= x
            && applet.mouseY >= y
            && applet.mouseX <= x + width()
            && applet.mouseY <= y + height();
    }

    float width() {
        return applet.textWidth(label) + 8 * 2;
    }

    private float height() {
        return 22 + 8 * 2;
    }
}
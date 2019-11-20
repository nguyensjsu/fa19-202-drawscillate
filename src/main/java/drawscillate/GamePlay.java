package drawscillate;

import processing.core.PApplet;
import processing.core.PConstants;

public class GamePlay implements IScreen {
    private PApplet applet;

    GamePlay(PApplet applet) {
        this.applet = applet;
    }

    /**
     * Add Observer to Subscribers List
     * 
     * @param obj Observer Object
     */
    public void attach(IScreenObserver obj) {

        observer = obj;
    @Override
    public void touch() {

    }

    @Override
    public void display() {
        applet.textAlign(PConstants.CENTER);
        applet.fill(237, 97, 21);
        applet.textSize(25);
        applet.text("Game Screen", applet.width / 2f, applet.height - 30);
    }
}

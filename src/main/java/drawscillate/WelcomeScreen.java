package drawscillate;

import processing.core.PApplet;
import processing.core.PConstants;

public class WelcomeScreen implements IScreen, IScreenSubject {

    PApplet parent;
    IScreenObserver observer;
    private int gameScreen;

    WelcomeScreen(PApplet p) {
        parent = p;
        gameScreen = 0;
        attach((IScreenObserver)p);

    }

    public void display() {
        parent.textAlign(PConstants.CENTER);
        parent.fill(237, 97, 21);
        parent.textSize(70);
        parent.text("Drawscillate", parent.width / 2, parent.height / 2);
        parent.textSize(25);
        parent.text("Click to start", parent.width / 2, parent.height - 30);
    }

    /**
     * Touch Event
     */
    public void touch() {
        gameScreen = 1;
        notifyObservers();

    }

    /**
     * Add Observer to Subscribers List
     * 
     * @param obj Observer Object
     */
    public void attach(IScreenObserver obj) {
        
        observer = obj;

    }

    /**
     * Trigger Events to Observers
     */
    public void notifyObservers() {
        
        observer.screenChange(gameScreen);

    }

}

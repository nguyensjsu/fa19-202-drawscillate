package drawscillate;

import processing.core.PApplet;

import java.util.ArrayList;
import java.util.List;

import static processing.core.PConstants.CENTER;
import static processing.core.PConstants.LEFT;

public class WelcomeScreen implements IScreen {
    private PApplet applet;
    private final Button button;

    WelcomeScreen(PApplet applet) {
        this.applet = applet;
        button = new Button(applet, "Click to start");
    }

    @Override
    public void touch() {
        if (button.over()) {
            notifyAllWelcomeScreenObservers();
        }
    }

    @Override
    public void display() {
        // draw the label
        applet.textAlign(CENTER);
        applet.fill(237, 97, 21);
        applet.textSize(70);
        applet.text("Drawscillate", applet.width / 2f, applet.height / 2f);

        // draw the button
        applet.textAlign(LEFT);
        applet.textSize(25);
        button.x = (int) Math.ceil(applet.width / 2f - button.width() / 2);
        button.y = 400;
        button.draw();
    }

    private List<WelcomeScreenObserver> welcomeScreenObservers = new ArrayList<>();

    void attach(WelcomeScreenObserver welcomeScreenObserver) {
        welcomeScreenObservers.add(welcomeScreenObserver);
    }

    private void notifyAllWelcomeScreenObservers() {
        welcomeScreenObservers.forEach(WelcomeScreenObserver::update);
    }
}

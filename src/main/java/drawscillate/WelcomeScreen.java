package drawscillate;

import processing.core.PApplet;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;

import static javax.swing.JOptionPane.showInputDialog;
import static processing.core.PConstants.CENTER;
import static processing.core.PConstants.LEFT;

public class WelcomeScreen implements IScreen {
    private PApplet applet;
    private final Button button;
    private Collection<WelcomeScreenObserver> welcomeScreenObservers = new HashSet<>();
    private AppController app;

    WelcomeScreen(PApplet applet) {
        this.applet = applet;
        button = new Button(applet, "Click to start");
        app = AppController.getInstance();
    }

    @Override
    public void mouseReleased() {
        if (button.over()) {
            notifyAllWelcomeScreenObservers();
            app.setName(showInputDialog("Please enter your Name"));
        }
    }

    @Override
    public void display() {
        applet.background(74, 73, 70);
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

    void attach(WelcomeScreenObserver welcomeScreenObserver) {
        welcomeScreenObservers.add(welcomeScreenObserver);
    }

    private void notifyAllWelcomeScreenObservers() {
        welcomeScreenObservers.forEach(WelcomeScreenObserver::update);
    }
}
package drawscillate;

import controlP5.CallbackEvent;
import controlP5.CallbackListener;
import controlP5.ControlP5;
import controlP5.Controller;
import controlP5.DropdownList;
import processing.core.PApplet;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;

import static processing.core.PConstants.LEFT;

public class OptionsScreen implements IScreen, CallbackListener {
    private PApplet applet;
    private ControlP5 controlP5;
    private DropdownList d1, d2;
    private final Button button;
    private String difficultySelection;
    private String shapeSelection;
    private Collection<OptionsScreenObserver> optionsScreenObservers = new HashSet<>();

    OptionsScreen(PApplet applet) {
        this.applet = applet;
        controlP5 = new ControlP5(applet);
        // create a DropdownList
        d1 = controlP5
            .addDropdownList("Difficulty")
            .onChange(this)
            .setOpen(false)
            .setBarVisible(false)
            .setPosition(270, 100)
            .setSize(200, 100)
            .setHeight(300)
            .setBarHeight(30)
            .setItemHeight(30)
            .addItems(Arrays.asList("Easy", "Normal", "Hard"));

        // create a second DropdownList
        d2 = controlP5
            .addDropdownList("Shape")
            .onChange(this)
            .setOpen(false)
            .setBarVisible(false)
            .setPosition(30, 100)
            .setSize(200, 100)
            .setHeight(300)
            .setBarHeight(30)
            .setItemHeight(30)
            .addItems(Arrays.asList("Star", "Rectangle", "Heart", "Circle"));

        button = new Button(applet, "Play!");
    }

    @Override
    public void mouseReleased() {
        if (difficultySelection != null
            && shapeSelection != null
            && button.over()) {
            notifyAllOptionsScreenObservers();
        }
    }

    @Override
    public void willDisplay() {
        d1.setBarVisible(true);
        d2.setBarVisible(true);
    }

    @Override
    public void willStopDisplaying() {
        d1.setBarVisible(false);
        d2.setBarVisible(false);
    }

    @Override
    public void display() {
        applet.background(74, 73, 70);
        // draw the button
        applet.textAlign(LEFT);
        applet.textSize(25);
        button.x = (int) Math.ceil(applet.width / 2f - button.width() / 2);
        button.y = 400;
        button.draw();
    }

    void attach(OptionsScreenObserver optionsScreenObserver) {
        optionsScreenObservers.add(optionsScreenObserver);
    }

    private void notifyAllOptionsScreenObservers() {
        optionsScreenObservers.forEach(optionsScreenObserver -> optionsScreenObserver.update2(difficultySelection, shapeSelection));
    }

    @Override
    public void controlEvent(CallbackEvent callbackEvent) {
        final Controller controller = callbackEvent.getController();
        final int selectedIndex = (int) controller.getValue();
        if (controller == d1) {
            difficultySelection = controlP5.get(DropdownList.class, "Difficulty").getItem(selectedIndex).get("name").toString();
        } else if (controller == d2) {
            shapeSelection = controlP5.get(DropdownList.class, "Shape").getItem(selectedIndex).get("name").toString();
        }
    }
}

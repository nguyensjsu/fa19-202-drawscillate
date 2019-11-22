package drawscillate;

import controlP5.CallbackEvent;
import controlP5.CallbackListener;
import controlP5.ControlP5;
import controlP5.Controller;
import controlP5.DropdownList;
import controlP5.Textarea;
import processing.core.PApplet;
import processing.core.PConstants;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;

import static processing.core.PConstants.CENTER;
import static processing.core.PConstants.LEFT;
import static processing.core.PConstants.RIGHT;
import static processing.core.PConstants.CORNER;

public class OptionsScreen implements IScreen, CallbackListener {
    private PApplet applet;
    private ControlP5 controlP5;
    private DropdownList d1, d2;
    private final Button button;
    private String difficultySelection;
    private String shapeSelection;
    private Collection<OptionsScreenObserver> optionsScreenObservers = new HashSet<>();
    private AppController app;
    private Textarea myTextArea;

    OptionsScreen(PApplet applet) {
        this.applet = applet;
        controlP5 = new ControlP5(applet);
        app = AppController.getInstance();
        // create a DropdownList
        d1 = controlP5
            .addDropdownList("Difficulty")
            .onChange(this)
            .setOpen(false)
            .setBarVisible(false)
            .setPosition(applet.width-300, applet.height-300)
            .setSize(200, 100)
            .setHeight(300)
            .setBarHeight(40)
            .setItemHeight(40)
            .addItems(Arrays.asList("Easy", "Normal", "Hard"));

        // create a second DropdownList
        d2 = controlP5
            .addDropdownList("Shape")
            .onChange(this)
            .setOpen(false)
            .setBarVisible(false)
            .setPosition(applet.width-300, applet.height-350)
            .setSize(200, 100)
            .setHeight(300)
            .setBarHeight(40)
            .setItemHeight(40)
            .addItems(Arrays.asList("Star", "Rectangle", "Heart", "Circle"));
        
        myTextArea = controlP5
                    .addTextarea("txt")
                    .setPosition(applet.width/10f+10, applet.height/2f)
                    .setSize(applet.width-350 ,applet.height-300)
                    .setVisible(false)
                    .setFont(applet.createFont("Georgia",14))
                    .setLineHeight(18)
                    .setColor(applet.color(237, 97, 21))
                    .setText("Key Controls:\n\n"
                            +" y - Yellow\n r - Red\n"
                            +" o - Green\n b - Blue\n"
                            +" p - Purple \n g - Orange\n"
                            );

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
        myTextArea.setVisible(true);
    }

    @Override
    public void willStopDisplaying() {
        d1.setBarVisible(false);
        d2.setBarVisible(false);
        myTextArea.setVisible(false);
    }

    @Override
    public void display() {
        //Text
        applet.background(0);
        applet.noFill();
        applet.noStroke();
        applet.rectMode(CENTER); 
        applet.rect(applet.width/2, 30, applet.width-100,30);
        applet.textAlign(CENTER,CENTER);
        applet.fill(237, 97, 21);
        applet.textSize(20);
        applet.text("Hi "+app.getName()+"! Select your Preferences!", applet.width/2, 30, applet.width -110 , 30);
        applet.noFill();
        applet.stroke(0, 153, 204);
        applet.rectMode(PConstants.CENTER);
        applet.rect(applet.height/2f, applet.width/2f, applet.width-100, applet.height-100);
        applet.noFill();
        applet.noStroke();
        applet.rectMode(CENTER); 
        applet.rect(applet.width/2, 60, applet.width-100,30);
        applet.textAlign(LEFT,CENTER);
        applet.fill(48, 145, 50);
        applet.textSize(20);
        applet.text("How to play:", applet.width/2, 60, applet.width-110,30);
        applet.noFill();
        applet.noStroke();
        applet.rectMode(CENTER); 
        applet.rect(applet.width/2, 90, applet.width-100,60);
        applet.fill(255);
        applet.textSize(14);
        applet.text("Move your cursor through the outline of the picture,\n"
                + "without crossing over the border to win!", applet.width/2, 90, applet.width-110,60);
        applet.noFill();
        applet.noStroke();
        applet.rectMode(CENTER); 
        applet.rect(applet.width/4, 170, applet.width/4,40);
        applet.textAlign(RIGHT,CENTER);
        applet.fill(255);
        applet.textSize(13);
        applet.text("CHOOSE SHAPE :", applet.width/4, 170, applet.width/4-10,40);
        applet.noFill();
        applet.noStroke();
        applet.rectMode(CENTER); 
        applet.rect(applet.width/4, 220, applet.width/4,40);
        applet.textAlign(RIGHT,CENTER);
        applet.fill(255);
        applet.textSize(13);
        applet.text("CHOOSE LEVEL :", applet.width/4, 220, applet.width/4-10,40);
        applet.rectMode(CORNER);
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

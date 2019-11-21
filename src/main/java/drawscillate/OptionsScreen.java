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

public class OptionsScreen implements IScreen, CallbackListener {
    private PApplet applet;
    private ControlP5 controlP5;
    private DropdownList d1, d2;
    private final Button button;
    private String difficultySelection;
    private String shapeSelection;
    private Collection<OptionsScreenObserver> optionsScreenObservers = new HashSet<>();
    private AppController app;
    private Textarea myTextarea;

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
            .setColorBackground(applet.color(0, 153, 204, 99))
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
        
        myTextarea = controlP5.addTextarea("txt")
                .setPosition(applet.width/10f+10, applet.height/2f)
                .setSize(applet.width -100 ,applet.height)
                .setVisible(false)
                .setFont(applet.createFont("arial",16))
                .setLineHeight(20)
                .setColor(applet.color(237, 97, 21));
        myTextarea.setText("Key Controls:\n\n"
                +" y - Yellow          r - Red\n"
                +" o - Green          b - Blue\n"
                +" p - Purple          g - Orange\n"
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
        myTextarea.setVisible(true);
    }

    @Override
    public void willStopDisplaying() {
        d1.setBarVisible(false);
        d2.setBarVisible(false);
        myTextarea.setVisible(false);
    }

    @Override
    public void display() {
        //Text
        applet.background(0);
        applet.textAlign(CENTER);
        applet.fill(237, 97, 21);
        applet.textSize(20);
        applet.text("Hi "+app.getName()+"! Select your Preferences!", applet.width / 2f, applet.width / 10f);
        applet.noFill();
        applet.stroke(0, 153, 204);
        applet.rect(applet.height/10f, applet.width/9f, applet.height-100, applet.width-100);
        applet.fill(48, 145, 50);
        applet.textSize(20);
        applet.textAlign(LEFT);
        applet.text("How to play:", applet.height /10f+10, applet.width / 6f);
        applet.fill(255);
        applet.textAlign(PConstants.LEFT,PConstants.TOP);
        applet.textSize(14);
        applet.text("Move your cursor through the outline of the picture,\n"
                + "without crossing over the border to win!", applet.height/10f+10 , applet.width / 9f+35 );
        applet.textSize(13);
        applet.textAlign(PConstants.RIGHT);
        applet.text("CHOOSE SHAPE",applet.width-315, applet.height-325);
        applet.text("CHOOSE DIFFCULTY",applet.width-315, applet.height-275);

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

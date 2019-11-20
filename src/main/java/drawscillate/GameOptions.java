package drawscillate;

import controlP5.CColor;
import controlP5.ControlP5;
import controlP5.ScrollableList;
import processing.core.PApplet;
import processing.core.PConstants;

import java.util.Arrays;
import java.util.List;

import static processing.core.PConstants.HAND;

public class GameOptions implements IScreen {
    private PApplet applet;
    private ControlP5 cp5;

    GameOptions(PApplet applet) {
        this.applet = applet;
        cp5 = new ControlP5(applet);
    }

    @Override
    public void touch() {

    }

    @Override
    public void display() {
        applet.textAlign(PConstants.CENTER);
        applet.fill(237, 97, 21);
        applet.textSize(25);
        applet.text("Choose game options", applet.width / 2f, applet.height - 30);

        cp5 = new ControlP5(applet);
        cp5.addScrollableList("select_shape")
            .setOpen(false)
            .setPosition(30, 100)
            .setSize(200, 100)
            .setHeight(300)
            .setBarHeight(30)
            .setItemHeight(30)
            .addItems(Arrays.asList("Star", "Rectangle", "Heart", "Circle"));
        cp5.addScrollableList("select_difficulty")
            .setOpen(false)
            .setPosition(270, 100)
            .setSize(200, 100)
            .setHeight(300)
            .setBarHeight(30)
            .setItemHeight(30)
            .addItems(Arrays.asList("Easy", "Medium", "Hard"));
    }

    //Name of the function is with underscore(and not camelCase) as processing sets the name to the function to dropdown head
    public void select_difficulty(int n) {
        final CColor color = new CColor();
        color.setBackground(applet.color(255, 0, 0));
        final String difficultySelection = cp5.get(ScrollableList.class, "select_difficulty").getItem(n).get("name").toString();
        System.out.println(difficultySelection);
        cp5.get(ScrollableList.class, "select_difficulty").hide();
    }

    //Name of the function is with underscore(and not camelCase) as processing sets the name to the function to dropdown head
    public void select_shape(int n) {
        final CColor color = new CColor();
        color.setBackground(applet.color(255, 0, 0));
        final String shapeSelection = cp5.get(ScrollableList.class, "select_shape").getItem(n).get("name").toString();
        System.out.println(shapeSelection);
        cp5.get(ScrollableList.class, "select_shape").hide();
    }
}

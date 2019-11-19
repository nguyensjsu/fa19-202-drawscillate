package drawscillate;

import processing.core.PApplet;

public class AppController extends PApplet {
    
    private int gameScreen =0;
    private WelcomeScreen welcomeScreen;
    public void settings() {
        size(1000, 750);
        smooth();
    }
    
    public void setup() {
        background(74, 73, 70);
        welcomeScreen = new WelcomeScreen(this);
    }
    
    public void draw() {
        if(gameScreen ==0) {
            welcomeScreen.display();
        }
    }
    public static void main(String[] args) {
        String[] processingArgs = { "MySketch" };
        AppController mySketch = new AppController();
        PApplet.runSketch(processingArgs, mySketch);
    }

}

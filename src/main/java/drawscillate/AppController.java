package drawscillate;

import processing.core.PApplet;

public class AppController extends PApplet implements IScreenObserver{

    private IScreen welcomeScreen;
    private IScreen current;
    public void settings() {
        size(1000, 750);
        smooth();
    }
    
    public void setup() {
        background(74, 73, 70);
        welcomeScreen = new WelcomeScreen(this);
        current = welcomeScreen;
    }
    
    public void draw() {
        current.display();
    }
    
    public void mousePressed() {
        current.touch();
        
    }
    
    public void screenChange(int gameScreen) {
        switch (gameScreen) {
        case 0:
            current = welcomeScreen;
            break;
        default:
            current = welcomeScreen;
        }
        
    }
    
    public static void main(String[] args) {
        String[] processingArgs = { "MySketch" };
        AppController mySketch = new AppController();
        PApplet.runSketch(processingArgs, mySketch);
    }

}

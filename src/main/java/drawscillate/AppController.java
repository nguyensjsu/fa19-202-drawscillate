package drawscillate;

import processing.core.PApplet;

public class AppController extends PApplet implements IScreenObserver{

    private static AppController theApp = null;   
    private IScreen welcomeScreen;
    private IScreen gameOptions;
    private IScreen gamePlay;
    private IScreen current;
    
    /**
     * Get Singleton Instance
     * @return Reference to Current App  (Create if none exists)
     */
    public synchronized static AppController getInstance() {
        if (theApp == null) {
            theApp = new AppController();
        }
        return theApp;
    }
    
    public void settings() {
        size(1000, 750);
        smooth();
    }
    
    public void setup() {
        background(74, 73, 70);
        welcomeScreen = new WelcomeScreen(this);
        gameOptions = new GameOptions(this);
        gamePlay = new GamePlay(this);
        current = welcomeScreen;
    }
    
    public void draw() {
        background(74, 73, 70);
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
        case 1:
            current =gameOptions;
            break;
        case 2:
            current = gamePlay;
            break;
        default:
            current = welcomeScreen;
        }
        
    }
    
    public static void main(String[] args) {
        String[] processingArgs = { "MySketch" };
        AppController mySketch = AppController.getInstance();
        PApplet.runSketch(processingArgs, mySketch);
    }

}

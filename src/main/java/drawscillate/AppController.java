package drawscillate;

import processing.core.PApplet;

public class AppController extends PApplet implements WelcomeScreenObserver {
    private static AppController appController;
    private IScreen welcomeScreen;
    private IScreen gameOptions;
    private IScreen gamePlay;
    private IScreen current;

    public static void main(String[] args) {
        String[] processingArgs = { "MySketch" };
        AppController mySketch = AppController.getInstance();
        PApplet.runSketch(processingArgs, mySketch);
    }
    
    /**
     * Get Singleton Instance
     * @return Reference to Current App  (Create if none exists)
     */
    public synchronized static AppController getInstance() {
        if (appController == null) {
            appController = new AppController();
        }
        return appController;
    }
    
    public void settings() {
        size(500, 500);
        smooth();
    }
    
    public void setup() {
        final WelcomeScreen welcomeScreen = new WelcomeScreen(this);
        welcomeScreen.attach(this);
        this.welcomeScreen = welcomeScreen;

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

    @Override
    public void update() {
        current = gameOptions;
    }
}

package drawscillate;

import processing.core.PApplet;

public class AppController extends PApplet implements WelcomeScreenObserver, OptionsScreenObserver {
    private static AppController appController;
    private IScreen welcomeScreen;
    private IScreen optionsScreen;
    private IScreen gameScreen;
    private IScreen current;

    public static void main(String[] args) {
        String[] processingArgs = {"MySketch"};
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

        final GameScreen gameScreen = new GameScreen(this);
        this.gameScreen = gameScreen;

        final OptionsScreen optionsScreen = new OptionsScreen(this);
        optionsScreen.attach(this);
        optionsScreen.attach(gameScreen);
        this.optionsScreen = optionsScreen;

        current = welcomeScreen;
    }
    
    public void draw() {
        current.display();
    }

    @Override
    public void mousePressed() {
        current.mousePressed();
    }

    @Override
    public void mouseReleased() {
        current.mouseReleased();
    }

    @Override
    public void update() {
        current.willStopDisplaying();
        current = optionsScreen;
        current.willDisplay();
    }

    @Override
    public void update2(String difficultySelection, String shapeSelection) {
        current.willStopDisplaying();
        current = gameScreen;
        current.willDisplay();
    }
}

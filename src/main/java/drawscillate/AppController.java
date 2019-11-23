package drawscillate;

import processing.core.PApplet;


public class AppController extends PApplet implements WelcomeScreenObserver, OptionsScreenObserver {
    private static AppController appController;
    private IScreen welcomeScreen;
    private IScreen optionsScreen;
    private IScreen gameScreen;
    private IScreen current;
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

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
        size(600, 600);
        smooth();
    }
    
    public void setup() {
        surface.setTitle("Drawscillate");
        final WelcomeScreen welcomeScreen = new WelcomeScreen(this);
        welcomeScreen.attach(this);
        this.welcomeScreen = welcomeScreen;
        current = welcomeScreen;
    }

    private void setupOptionsScreen() {
        final GameScreen gameScreen = new GameScreen(this);
        this.gameScreen = gameScreen;

        final OptionsScreen optionsScreen = new OptionsScreen(this);
        optionsScreen.attach(this);
        optionsScreen.attach(gameScreen);
        this.optionsScreen = optionsScreen;
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
    public void mouseDragged() {
        current.mouseDragged();
    }

    @Override
    public void update() {
        current.willStopDisplaying();
        setupOptionsScreen();
        current = optionsScreen;
        current.willDisplay();
    }

    @Override
    public void update2(String difficultySelection, String shapeSelection) {
        current.willStopDisplaying();
        current = gameScreen;
        current.willDisplay();
    }

    public  void update3(String shapeSelection,String difficultySelection, int hits, boolean gameWon){
        current.willStopDisplaying();
        current = new ScorecardScreen(this,shapeSelection,difficultySelection,hits,gameWon);
        current.willDisplay();
    }
}

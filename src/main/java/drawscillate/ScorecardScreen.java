package drawscillate;

import processing.core.PApplet;

public class ScorecardScreen implements IScreen {

    private PApplet applet;
    private int hits;
    private IScoringStrategy scoringStrategy;
    private final Button buttonReplay;
    private final Button buttonExit;

    public ScorecardScreen(PApplet applet,String shape, int hits) {
        this.applet = applet;
        this.hits = hits;

        switch (shape) {
            case "Heart":
                scoringStrategy = new HeartScoringStrategy();
                break;
            case "Circle":
                scoringStrategy = new CircleScoringStrategy();
                break;
            case "Rectangle":
                scoringStrategy = new RectangleScoringStrategy();
                break;
            case "Star":
                scoringStrategy = new StarScoringStrategy();
                break;
        }

        buttonReplay = new Button(applet, "Replay");
        buttonExit = new Button(applet, "Exit");
    }


    @Override
    public void mousePressed() {

    }

    @Override
    public void display() {
        applet.background(230, 222, 204);
        applet.text(Integer.toString(getScore(scoringStrategy)), applet.width / 2f, applet.height / 2f);

        buttonReplay.x = (int) Math.ceil(applet.width / 2f - buttonReplay.width() / 2);
        buttonReplay.y = 300;
        buttonReplay.draw();

        buttonExit.x = (int) Math.ceil(applet.width / 2f - buttonExit.width() / 2);
        buttonExit.y = 400;
        buttonExit.draw();
    }

    private int getScore(IScoringStrategy scoringStrategy){
        return scoringStrategy.calculateScore(hits);
    }

    @Override
    public void willDisplay() {

    }

    @Override
    public void willStopDisplaying() {

    }

    @Override
    public void mouseReleased() {
    }

    @Override
    public void mouseDragged() {

    }
}

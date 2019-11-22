package drawscillate;

import processing.core.PApplet;

public class ScorecardScreen implements IScreen {

    private PApplet applet;
    private int hits;
    private IScoringStrategy scoringStrategy;

    public ScorecardScreen(String shape, int hits) {
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
    }


    @Override
    public void mousePressed() {

    }

    @Override
    public void display() {
        applet.text("Drawscillate", applet.width / 2f, applet.height / 2f);
    }

    private int getScore(IScoringStrategy scoringStrategy){
        return scoringStrategy.CalculateScore(hits);
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

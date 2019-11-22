package drawscillate;

import processing.core.PApplet;

public class ScorecardScreen implements IScreen {

    private PApplet applet;
    private int hits;
    private IScoringStrategy scoringStrategy;

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
    }


    @Override
    public void mousePressed() {

    }

    @Override
    public void display() {
        applet.background(255);
        applet.text(Integer.toString(getScore(scoringStrategy)), applet.width / 2f, applet.height / 2f);
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

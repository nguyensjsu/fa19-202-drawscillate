package drawscillate;

import processing.core.PApplet;
import processing.core.PGraphics;

public class GameLogicManager implements IGameLogicSubject {

    private IGameLogicObserver observer;
    private boolean gameOver;
    private boolean gameWon;
    private PApplet applet;
    private int[] pixelsFrame;
    private float red;
    private float green;
    private float blue;
    private int[][] checkpoints;
    private int strokeWeight;
    private boolean startPointRecorded = false;
    private int startPointX;
    private int startPointY;
    private GamePlayStateMachine gamePlayStateMachine;

    GameLogicManager(PApplet applet) {
        this.applet = applet;
        gameOver = false;
        gameWon = false;
        gamePlayStateMachine = new GamePlayStateMachine();
    }

    public void setCheckPoints(int[][] points) {
        checkpoints = points;
    }

    public void setStrokeWeight(int strokeWeight) {
        this.strokeWeight = strokeWeight;
    }

    @Override
    public void registerObserver(IGameLogicObserver obj) {
        this.observer = obj;

    }

    @Override
    public void removeObserver() {
        this.observer = null;

    }

    @Override
    public void notifyObserver() {
        observer.gameState(gameOver, gameWon);

    }

    /**
     * 
     * Function name - hasLineReachedCheckPoint Description - check if current point
     * is in the vicinity of some checkpoint
     * 
     * @param - mouseX,mouseY
     * @return - void
     */
    private void hasLineReachedCheckPoint() {

        for (int i = 0; i < checkpoints.length; i++) {
            if (checkpoints[i][2] != 1) {
                checkpoints[i][2] = isPointInCircle(checkpoints[i][0], checkpoints[i][1], applet.mouseX, applet.mouseY,
                        strokeWeight * strokeWeight);
            }
        }
    }

    /**
     *
     * Function name - isPointInCircle Description - check if point is within circle
     * with centre i ,j
     * 
     * @param - i,j,mouseX,mouseY
     * @return - int
     */
    private int isPointInCircle(int i, int j, int mouseX, int mouseY, int radius) {
        int distance = (i - mouseX) * (i - mouseX) + (j - mouseY) * (j - mouseY);
        if (distance <= radius) {
            return 1;
        }
        return 0;
    }

    /**
     * @return If all check points have been reached
     */
    private boolean allCheckPointsReached() {
        if (checkpoints.length == 0)
            return false;
        for (int i = 0; i < checkpoints.length; i++) {
            if (checkpoints[i][2] != 1) {
                return false;
            }
        }
        return true;
    }

    /**
     * @return If the start point been visited again
     */
    private boolean startReached() {
        return isPointInCircle(startPointX, startPointY, applet.mouseX, applet.mouseY, 100) == 1;
    }

    public void mouseEvent(PGraphics graphics) {
        pixelsFrame = graphics.get().pixels;
        red = applet.red(pixelsFrame[applet.mouseX + applet.mouseY * applet.width]);
        green = applet.green(pixelsFrame[applet.mouseX + applet.mouseY * applet.width]);
        blue = applet.blue(pixelsFrame[applet.mouseX + applet.mouseY * applet.width]);
        if (red != 255 && green != 255 && blue != 255) {
            this.gamePlayStateMachine.setStateLose();
            gameOver = true;
        }    
        hasLineReachedCheckPoint();
        if (!startPointRecorded) {
            startPointX = applet.mouseX;
            startPointY = applet.mouseY;
            startPointRecorded = true;
            this.gamePlayStateMachine.setStateInPlay();
            
        }

        if (allCheckPointsReached() && startReached()) {
            this.gamePlayStateMachine.setStateWin();
            gameWon = true;
        }   
        notifyObserver();

    }

}

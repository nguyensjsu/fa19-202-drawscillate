/**
 * 
 */
package drawscillate;

import processing.core.PApplet;
import processing.core.PGraphics;

public class GamePlayStateMachine implements IGamePlayStateMachine,IGameLogicSubject {
    
    private InitialGameState initialGameState;
    private InplayGameState inplayGameState;
    private LoseGameState loseGameState;
    private WinGameState winGameState;
    
    private IGameState currentState;
    
    private IGameLogicObserver observer;
    private boolean gameOver;
    private boolean gameWon;
    private PApplet applet;
    int[][] checkpoints;
    int strokeWeight;
    int startPointX;
    int startPointY;
    
    
    /**
     * 
     */
    public GamePlayStateMachine(PApplet applet) {
      initialGameState = new InitialGameState(this);
      inplayGameState = new InplayGameState(this);
      loseGameState = new LoseGameState(this);
      winGameState = new  WinGameState(this);
      currentState = initialGameState;
      this.applet = applet;
    }
    

    public void setCheckPoints(int[][] points) {
        checkpoints = points;
    }

    public void setStrokeWeight(int strokeWeight) {
        this.strokeWeight = strokeWeight;
    }
    
    public void setStartPoints(int x, int y) {
        startPointX = applet.mouseX;
        startPointY = applet.mouseY;
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
    public void hasLineReachedCheckPoint() {

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
    public int isPointInCircle(int i, int j, int mouseX, int mouseY, int radius) {
        int distance = (i - mouseX) * (i - mouseX) + (j - mouseY) * (j - mouseY);
        if (distance <= radius) {
            return 1;
        }
        return 0;
    }

    /**
     * @return If all check points have been reached
     */
    public boolean allCheckPointsReached() {
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
    public boolean startReached() {
        return isPointInCircle(startPointX, startPointY, applet.mouseX, applet.mouseY, 100) == 1;
    }
    /**
    * 
    * Function name - setStateInitial
    * Description   - 
    * @param     - 
    * @return        - 
    */
    @Override
    public void setStateInitial() {
        currentState = initialGameState;
        
    }

    /**
    * 
    * Function name - setStateInPlay
    * Description   - 
    * @param     - 
    * @return        - 
    */
    @Override
    public void setStateInPlay() {
            currentState = inplayGameState;
    }

    /**
    * 
    * Function name - setStateWin
    * Description   - 
    * @param     - 
    * @return        - 
    */
    @Override
    public void setStateWin() {
            currentState = winGameState;
            gameWon = true;
            notifyObserver();
    }

    /**
    * 
    * Function name - setStateLose
    * Description   - 
    * @param     - 
    * @return        - 
    */
    @Override
    public void setStateLose() {
            currentState = loseGameState;
            gameOver = true;
            notifyObserver();
    }
    
    public void mouseEvent(PGraphics graphics) {
        this.currentState.handleMouseEvent(applet,graphics);
    }
    

}

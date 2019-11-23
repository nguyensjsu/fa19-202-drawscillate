package drawscillate;

public interface IGameLogicObserver {
    
    /**
     * Send game state event to current screen
     * @param gameOver
     * @param gameWon
     */
    void gameState(boolean gameOver, boolean gameWon) ;

}

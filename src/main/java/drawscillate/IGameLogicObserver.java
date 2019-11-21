package drawscillate;

public interface IGameLogicObserver {
    
    /**
     * Send game over event to current screen
     * @param gameover 
     */
    void isGameOver(boolean gameover) ;

}

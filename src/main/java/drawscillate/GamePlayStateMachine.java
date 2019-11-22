/**
 * 
 */
package drawscillate;

public class GamePlayStateMachine implements IGamePlayStateMachine {
    private InitialGameState initialGameState;
    private InplayGameState inplayGameState;
    private LoseGameState loseGameState;
    private WinGameState winGameState;
    
    private IGameState currentState;
    
    
    /**
     * 
     */
    public GamePlayStateMachine() {
      initialGameState = new InitialGameState(this);
      inplayGameState = new InplayGameState(this);
      loseGameState = new LoseGameState(this);
      winGameState = new  WinGameState(this);
      currentState = initialGameState;
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
        if(currentState.equals(initialGameState)) {
            currentState = inplayGameState;
        }
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
        if(currentState.equals(inplayGameState)) {
            currentState = winGameState;
        }  
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
        if(currentState.equals(inplayGameState)) {
            currentState = loseGameState;
        }
        
        
        
    }

}

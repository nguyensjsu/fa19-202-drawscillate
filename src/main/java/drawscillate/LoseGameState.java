/**
 * 
 */
package drawscillate;

/**
 * @author rajee
 *
 */
public class LoseGameState implements IGameState {

    private GamePlayStateMachine machine;
    /**
     * @param gamePlayStateMachine
     */
    public LoseGameState(GamePlayStateMachine gamePlayStateMachine) {
        this.machine = gamePlayStateMachine;
    }

    /**
    * 
    * Function name - handleMouseEvent
    * Description   - change to initial when lost
    * @param     - none 
    * @return        - none
    */
    @Override
    public void handleMouseEvent() {
        this.machine.setStateInitial();
        
    }

}

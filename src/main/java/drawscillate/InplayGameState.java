/**
 * 
 */
package drawscillate;

/**
 * @author rajee
 *
 */
public class InplayGameState implements IGameState{

    IGamePlayStateMachine machine;
    
    
    public InplayGameState(IGamePlayStateMachine machine) {
        this.machine =machine;
       
    }
    
    /**
    * 
    * Function name - handleMouseEvent
    * Description   - keep in the current state
    * @param     - none 
    * @return        - none
    */
    @Override
    public void handleMouseEvent() {
        this.machine.setStateInPlay();
    }

}

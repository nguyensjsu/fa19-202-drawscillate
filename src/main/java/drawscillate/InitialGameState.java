/**
 * 
 */
package drawscillate;

/**
 * @author rajee
 *
 */
public class IntialGameState implements IGameState {

    IGamePlayStateMachine machine;
    
    
    public IntialGameState(IGamePlayStateMachine machine) {
        this.machine =machine;
       
    }
    
    /**
    * 
    * Function name - handleMouseEvent
    * Description   - 
    * @param     - 
    * @return        - 
    */
    @Override
    public void handleMouseEvent() {
        this.machine.setStateInPlay();
        
    }

}

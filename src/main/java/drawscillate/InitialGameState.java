/**
 * 
 */
package drawscillate;

/**
 * @author rajee
 *
 */
public class InitialGameState implements IGameState {

    IGamePlayStateMachine machine;
    
    
    public InitialGameState(IGamePlayStateMachine machine) {
        this.machine =machine;
       
    }
    
    /**
    * 
    * Function name - handleMouseEvent
    * Description   -  change to inplay state
    * @param     -      none
    * @return        - none
    */
    @Override
    public void handleMouseEvent() {
        this.machine.setStateInPlay();
        
    }

}

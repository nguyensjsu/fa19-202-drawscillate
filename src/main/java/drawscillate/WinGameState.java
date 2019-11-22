/**
 * 
 */
package drawscillate;

/**
 * @author rajee
 *
 */
public class WinGameState implements IGameState {

IGamePlayStateMachine machine;
    
    
    public WinGameState(IGamePlayStateMachine machine) {
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
        this.machine.setStateInitial();
        
    }


}

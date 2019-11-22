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
    * Description   - 
    * @param     - 
    * @return        - 
    */
    @Override
    public void handleMouseEvent() {
        
        
    }

}

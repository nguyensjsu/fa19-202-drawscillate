/**
 * 
 */
package drawscillate;

import processing.core.PApplet;
import processing.core.PGraphics;

/**
 * @author rajee
 *
 */
public class WinGameState implements IGameState {

    private IGamePlayStateMachine machine;
    
    
    public WinGameState(IGamePlayStateMachine machine) {
        this.machine =machine;
       
    }
    
    /**
    * 
    * Function name - handleMouseEvent
    * Description   -  handle the mouse event
    * @param     - none
    * @return        - none
    */
    @Override
    public void handleMouseEvent(PApplet applet, PGraphics graphics) {
        this.machine.setStateInitial();
        
    }


}

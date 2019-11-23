package drawscillate;
/**
 * Color Command class for changing color
 */
public class ColorCommand implements IColorCommand {

    IColorReceiver target;

    /**
     * Function name -
     * Description   -
     * @param     - none
     * @return    - none
     */
    public void execute() {
        target.doAction();
    }

    /**
     * Set Receiver of Command
     * 
     * @param t Target Receiver
     */
    public void setReceiver(IColorReceiver t) {
        target = t;
    }

}

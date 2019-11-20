package drawscillate;

public class ColorCommand implements IColorCommand {

    IColorReceiver target;

    /** Execute Command */
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

package drawscillate;
/**
 * Color Option for the command pattern
 */
public class ColorOption implements IColorInvoker {

    private IColorCommand cmd ;

    /**
     * Set Command for Menu Option
     * @param c [description]
     */
    public void setCommand( IColorCommand c ) {
        cmd = c ;
    }

    /**
     * Function name -
     * Description   -
     * @param     - none
     * @return        - none
     */
    public void invoke() 
    {
        cmd.execute() ;
    }

}

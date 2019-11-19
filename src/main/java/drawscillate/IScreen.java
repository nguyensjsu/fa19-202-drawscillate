package drawscillate;

public interface IScreen {
    
    /**
     * Send touch events to screen
     */
    void touch() ;              

    /**
     * Displays screen components
     * @return Return Screen Contents
     */
    void display() ;

}

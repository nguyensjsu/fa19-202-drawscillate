package drawscillate;

public interface IScreen {
    /**
     * Send touch events to screen
     */
    default void mousePressed() {}

    /**
     * Displays screen components
     */
    void display() ;

    default void willDisplay() {}

    default void willStopDisplaying() {}

    default void mouseReleased() {}

    default void mouseDragged() {}
}

package drawscillate;


public interface IScreenSubject {
    
    /**
     * Add Observer to Subscribers List
     * @param obj Observer Object
     */
    void attach( IScreenObserver obj ) ;


    /**
     * Trigger Events to Observers
     */
    void notifyObservers() ;

}

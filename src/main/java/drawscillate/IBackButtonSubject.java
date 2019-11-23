/**
 * 
 */
package drawscillate;

/**
 * @author rajee
 *
 */
public interface IBackButtonSubject {
    
    
    
    public void attachObserver(IBackButtonObserver backButtonObserver);
    
    public void notifyObserver();
    

}

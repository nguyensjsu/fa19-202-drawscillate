package drawscillate;

import processing.core.PApplet;
import processing.core.PConstants;

public class GamePlay implements IScreen {

    PApplet parent;
    IScreenObserver observer;
    private int gameScreen;
    private int goBackbuttonX;
    private int goBackbuttonY;

    GamePlay(PApplet p) {
        parent = p;
        gameScreen = 0;
        attach((IScreenObserver) p);

    }

    public void display() {
        parent.textAlign(PConstants.CENTER);
        parent.fill(237, 97, 21);
        parent.textSize(25);
        parent.text("Game Screen", parent.width / 2, parent.height - 30);
        goBackbuttonX = (parent.width * 5) / 6;
        goBackbuttonY = (parent.height) / 6;
        //parent.rect(goBackbuttonX, goBackbuttonY, 100 , 50 );
        parent.textAlign(PConstants.LEFT);
        parent.text("Main Menu",goBackbuttonX,goBackbuttonY);
    }

    /**
     * Touch Event
     */
    public void touch() {
        //Need to implement this function
         if(overRect(150,50)) {
             gameScreen = 1;
             notifyObservers();
         }   
    }
    
    boolean overRect(int width, int height)  {
        System.out.println("X:" + parent.mouseX);
        System.out.println("Y:" + parent.mouseY);
        if (parent.mouseX >= goBackbuttonX && parent.mouseX <= goBackbuttonX + width && 
                parent.mouseY >= goBackbuttonY - 20 && parent.mouseY <= goBackbuttonY + height) {
          return true;
        } else {
          return false;
        }
      }

    /**
     * Add Observer to Subscribers List
     * 
     * @param obj Observer Object
     */
    public void attach(IScreenObserver obj) {

        observer = obj;

    }

    /**
     * Trigger Events to Observers
     */
    public void notifyObservers() {

        observer.screenChange(gameScreen);

    }

}

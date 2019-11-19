package drawscillate;

import processing.core.PApplet;
import processing.core.PConstants;

public class WelcomeScreen implements IScreen{
    
    PApplet parent;
    
    WelcomeScreen(PApplet p){
        parent = p;
        
    }
    
    public void display() {
        parent.textAlign(PConstants.CENTER);
        parent.fill(237, 97, 21);
        parent.textSize(70);
        parent.text("Drawscillate", parent.width / 2, parent.height / 2);
        parent.textSize(25);
        parent.text("Click to start", parent.width / 2, parent.height - 30);
    }

    /** 
     * Yet to implement this function
     */
    public void touch() {
        // TODO Auto-generated method stub
        
    }

    
    

}

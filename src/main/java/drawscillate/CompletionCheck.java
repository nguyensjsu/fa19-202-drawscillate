package drawscillate;

import java.util.ArrayList;
import java.util.List;

public class CompletionCheck {
    private int pointX;
    private int pointY;
    
    public void set(int pointX, int pointY)  
    { 
        this.pointX = pointX;
        this.pointY = pointY;
    } 
   
    public Memento saveToMemento()  
    { 
        return new Memento(pointX, pointY); 
    } 
   
    public List<Integer> restoreFromMemento(Memento memento)  
    { 
        List<Integer> list = new ArrayList<>();
        pointX = memento.getSavedPointX();
        pointY = memento.getSavedPointY();
        list.add(pointX);
        list.add(pointY);
        return list;
    } 
}

package drawscillate;

public class Memento {
    private int pointX;
    private int pointY;
    
    public Memento(int pointX, int pointY)  
    { 
        this.pointX = pointX;
        this.pointY = pointY;
    } 

    public int getSavedPointX()  
    { 
        return pointX; 
    } 
    
    public int getSavedPointY()  
    { 
        return pointY; 
    } 
}

package drawscillate;

import processing.core.PApplet;
import processing.core.PGraphics;

import java.util.ArrayList;

public interface Shapes {


    ArrayList traceX = new ArrayList();
    ArrayList traceY = new ArrayList();
    
    public int[][] draw(int weight, PGraphics graphics, PApplet applet);

}

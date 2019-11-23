package drawscillate;

import processing.core.PApplet;
import processing.core.PGraphics;

public interface IGameState {
    
    void handleMouseEvent(PApplet applet, PGraphics graphics);
}

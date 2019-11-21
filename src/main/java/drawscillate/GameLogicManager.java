package drawscillate;

import processing.core.PApplet;
import processing.core.PGraphics;

public class GameLogicManager implements IGameLogicSubject{
    
    private IGameLogicObserver observer;
    private boolean gameOver;
    private PApplet applet;
    int[] pixelsFrame;
    float red;
    float green;
    float blue;

    GameLogicManager(PApplet applet){
        this.applet =applet;
        gameOver = false;
    }
    
    @Override
    public void registerObserver(IGameLogicObserver obj) {
        this.observer = obj;
        
    }

    @Override
    public void removeObserver() {
        this.observer = null;
        
    }

    @Override
    public void notifyObserver() {
        observer.isGameOver(gameOver);
        
    }
    
    public void mouseEvent(PGraphics graphics) {
        pixelsFrame = graphics.get().pixels;
        red = applet.red(pixelsFrame[applet.mouseX + applet.mouseY * applet.width]);
        green = applet.green(pixelsFrame[applet.mouseX + applet.mouseY * applet.width]);
        blue = applet.blue(pixelsFrame[applet.mouseX + applet.mouseY * applet.width]);
        if(red != 255 && green != 255 && blue != 255) {
            gameOver = true;
            notifyObserver();
        }
        
    }

    
}

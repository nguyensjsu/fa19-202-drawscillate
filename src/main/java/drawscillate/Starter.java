package drawscillate;

import processing.core.PApplet;

/**Basic starter code */
public class Starter extends PApplet {

	public int gamescreen = 0;
	public void settings() {
		size(1000, 750);
		smooth();
	}

	public void setup() {
		background(255);
	}

	public void draw() {
		// Display the contents of the current screen
		if (gamescreen == 0) {
			initScreen();
		} else if (gamescreen == 1) {
			chooseShape();
		}
		else if (gamescreen == 2) {
			gamePlayScreen();
		}
	}
	
	/** Intial Screen
	 */
	public void initScreen() {
		background(236, 240, 241);
		textAlign(CENTER);
		fill(52, 73, 94);
		textSize(70);
		text("Drawscillate", width / 2, height / 2);
		textSize(25);
		text("Click to start", width / 2, height - 30);
	}
	
	/** Screen to choose your shape
	 */
	public void chooseShape() {
		background(236, 240, 241);
		textAlign(CENTER);
		fill(52, 73, 94);
		textSize(30);
		text("Choose Shape", width / 2, height - 30);	
	}

	/**Game Screen*/
	public void gamePlayScreen() {
		background(236, 240, 241);
		textAlign(CENTER);
		fill(52, 73, 94);
		textSize(30);
		text("Add Game Screen Content here...", width / 2, height - 30);	
	}
	
	public void mousePressed() {
		if(gamescreen == 0) {
			gamescreen = 1;
		} else if(gamescreen == 1) {
			gamescreen = 2;
		}
		
	}

	public static void main(String[] args) {
		String[] processingArgs = { "MySketch" };
		Starter mySketch = new Starter();
		PApplet.runSketch(processingArgs, mySketch);
	}
}
package drawscillate;

import processing.core.PApplet;

public class Starter extends PApplet {
	/**
	 * Processing Sound Library, Example 1
	 * 
	 * Five sine waves are layered to construct a cluster of frequencies. This
	 * method is called additive synthesis. Use the mouse position inside the
	 * display window to detune the cluster.
	 */
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
			gamePlayScreen();
		}
	}

	public void initScreen() {
		background(236, 240, 241);
		textAlign(CENTER);
		fill(52, 73, 94);
		textSize(70);
		text("Drawscillate", width / 2, height / 2);
		textSize(25);
		text("Click to start", width / 2, height - 30);
	}

	public void gamePlayScreen() {
		background(236, 240, 241);
		textAlign(CENTER);
		fill(52, 73, 94);
		textSize(30);
		text("Click to start", width / 2, height - 30);
	}
	
	public void mousePressed() {
		if(gamescreen == 0) {
			gamescreen = 1;
		}
	}

	public static void main(String[] args) {
		String[] processingArgs = { "MySketch" };
		Starter mySketch = new Starter();
		PApplet.runSketch(processingArgs, mySketch);
	}
}
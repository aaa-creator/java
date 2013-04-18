import objectdraw.*;
import java.awt.*;
import java.awt.event.*;
import java.applet.*;
import javax.swing.*;

/**
 * A Noisy Button is a GUI component designed for the implementation of the game
 * Simon. On the screen, a NoisyButton appears as a colored square. When the
 * user clicks on a NoisyButton, a "listener" is notifed by invoking its
 * "noisyButtonClicked" method. A "flash" method is provided which briefly
 * lightens the color of the button and makes a noise.
 */
public class NoisyButton extends JPanel implements MouseListener {

	/** size of a button square		*/
	public final int BUTTONSIZE = 100;

	/** canvas upon which the buttons are drawn	*/
	private JDrawingCanvas buttonArea;
	
	/** rectangle that represents the button	*/
	private FilledRect coloring; 

	/** sound to play when button flashes		*/
	private AudioClip noise; 

	/** object to notify when button is clicked	*/
	private NoisyButtonListener listener; 

	/** color of the button when it is unlit	*/
	private Color shade;

	/**
	 * Constructor for a light/sound button
	 * 
	 * @param sound
	 *            to be associated with the new button
	 * @param color
	 *            of the button when it is not lit
	 */
	public NoisyButton(AudioClip noise, Color shade) {
		this.noise = noise;
		this.shade = shade;

		// create a canvas for the button, add it to the Panel and listen for
		// clicks
		buttonArea = new JDrawingCanvas(BUTTONSIZE, BUTTONSIZE);
		add(buttonArea);
		buttonArea.addMouseListener(this);

		// draw a rectangle to make button appear
		coloring = new FilledRect(0, 0, BUTTONSIZE, BUTTONSIZE, buttonArea);
		coloring.setColor(shade);
	}

	/**
	 * register a listener for mouse clicks on this button
	 * 
	 * @param object
	 *            that implements the noisyButtonClicked method
	 */
	public void addListener(NoisyButtonListener listener) {
		this.listener = listener;
	}

	/**
	 * flash the color and play the sound associated with this button
	 * <P>
	 * This is implmented by creating an ActiveObject that will make the sound
	 * and flash the color for a designated period of time.
	 */
	public void flash() {
		new Beeper(noise, coloring, shade, buttonArea);
	}

	/**
	 * Called in response to a mouse click event within this button. If a
	 * listener has been registered, call it.
	 */
	public void mouseClicked(MouseEvent e) {
		if (listener != null)
			listener.noisyButtonClicked(this);
	}

	/** (mandatory) MouseListener event handler for mouse entered window */
	public void mouseEntered(MouseEvent e) {
	}

	/** (mandatory) MouseListener event handler for mouse exited window */
	public void mouseExited(MouseEvent e) {
	}

	/** (mandatory) MouseListener event handler for button pressed */
	public void mousePressed(MouseEvent e) {
	}

	/** (mandatory) MouseListener event handler for button released */
	public void mouseReleased(MouseEvent e) {
	}
}

/**
 * A Beeper is an ActiveObject that is created each time a NoisyButton must be
 * flashed. It flashes the light and plays the designated sound for a designated
 * period of time, and then restores the color to its unlit value.
 */
class Beeper extends ActiveObject {
	private final int PAUSETIME = 250; // Duration of flash

	private AudioClip sound; // The sound to play when flashing
	private FilledRect coloring; // The button rectangle to brighten
	private JDrawingCanvas buttonArea; // canvas on which the button is drawn

	private Color light; // lit color for the button
	private Color dark; // unlit color for the button

	/**
	 * constructor for a timed flash and beep
	 * 
	 * @param sound
	 *            sound to make while flashing the button
	 * @param coloring
	 *            area to be lit up while flashing the button
	 * @param shade
	 *            un-lit color of the button to be flashed
	 * @param buttonArea
	 *            canvas on which the button is drawn
	 */
	public Beeper(AudioClip sound, FilledRect coloring, Color shade,
			JDrawingCanvas buttonArea) {
		// Copy needed parameters to instance variables
		this.sound = sound;
		this.coloring = coloring;
		this.buttonArea = buttonArea;

		// Determine normal and highlighted colors.
		light = shade.brighter().brighter().brighter();
		dark = shade;

		start();
	}

	/**
	 * thread service routine for the ActiveObject, called whenever a new Beeper
	 * is instantiated.
	 */
	public void run() {
		// Hightlight the button and make some noise
		coloring.setColor(light);
		buttonArea.repaint();
		sound.play();

		// Pause while sound plays
		pause(PAUSETIME);

		// Restore original button color
		coloring.setColor(dark);
		buttonArea.repaint();
	}

}

import objectdraw.*;
import java.awt.*;
import java.applet.AudioClip;
import javax.swing.*;


/**
 * a collection of four NoisyButtons that make up the Simon game.
 */
public class ButtonPanel extends JPanel {

	/** number of buttons on the board	*/
	private static final int BUTTONCOUNT = 4;
	
	/** color value (intensity) for an un-lit button	*/
	private static final int COLORINTENSITY = 180;

	/** array to hold the buttons		*/
	private NoisyButton[] buttons; 

	/** random number generator to choose buttons in song	*/
	private RandomIntGenerator buttonPicker =
		new RandomIntGenerator(0, BUTTONCOUNT - 1);

	/**
	 * create a new panel of buttons
	 * 
	 * @param	array of sounds to be made by each of the (4) buttons
	 */
	public ButtonPanel(AudioClip[] sounds) {
		setLayout (new GridLayout (2, 2));
		
		// create an array of colors for the buttons
		Color shades[] = new Color[BUTTONCOUNT];
		shades[0] = new Color(COLORINTENSITY, 0, 0);	// RED
		shades[1] = new Color(0, COLORINTENSITY, 0);	// GREEN
		shades[2] = new Color(0, 0, COLORINTENSITY);	// BLUE
		shades[3] = new Color(COLORINTENSITY, COLORINTENSITY, 0);	// YELLOW

		// create the four buttons and store them in the buttons array.
		buttons = new NoisyButton[BUTTONCOUNT];

		for (int buttonNum = 0; buttonNum < BUTTONCOUNT; buttonNum++) {
			buttons[buttonNum] =
				new NoisyButton(sounds[buttonNum], shades[buttonNum]);
			add(buttons[buttonNum]);
		}

	}

	/**
	 * associate a NoisyButtonListener with each button on the panel
	 * 
	 * @param listener to be associated with the buttons
	 */
	public void addNoisyButtonListener(NoisyButtonListener listener) {
		for (int buttonNum = 0; buttonNum < BUTTONCOUNT; buttonNum++)
			buttons[buttonNum].addListener(listener);
	}

	/**
	 * choose (at random) a button from the panel
	 * (used to compose tunes to be reproduced by the user)
	 * 
	 * @return	one of the NoisyButtons on the panel
	 */
	public NoisyButton getRandomButton() {
		return buttons[buttonPicker.nextValue()];
	}

}
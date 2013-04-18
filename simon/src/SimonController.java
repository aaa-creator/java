import objectdraw.*;
import java.applet.AudioClip;

/**
 * Lab 9 - Simon.
 * <P>
 * Simulate the the "Simon" toy, which generates increasingly long sequences of
 * colors (and corresponding sounds) which the player must remember and
 * reproduce.
 * <P>
 * Suggested window size: 250x250
 * 
 * @author YOUR-NAME-HERE, YOUR-LAB-SECTION-HERE
 */
public class SimonController extends Controller implements NoisyButtonListener {
	/** number of sounds, corresponding to the number of game buttons	*/
	private static final int NUM_SOUNDS = 4; 

	/** an obnoxious razzing noise, played when a mistake is made		*/
	private AudioClip nastyNoise;

	/** the (growing) sequence of buttons/tones user must reproduce		*/
	private Song theSong;

	/**
	 * Initialization method, called when applet starts
	 * <p>
	 * Create the display of four buttons on the screen, and associate
	 * appropriate sounds with those buttons.
	 */
	public void begin() {

		// load the nasty noise
		nastyNoise = getAudio("razz.au");

		//* create the array of audio clips for the buttons
		AudioClip[] buttonSounds = new AudioClip[NUM_SOUNDS];
		for (int i = 0; i < NUM_SOUNDS; i++) {
			buttonSounds[i] = getAudio("tone." + i + ".au");
		}

		// create the button panel
		ButtonPanel theButtons = new ButtonPanel(buttonSounds);
		// add a listener for user clicks on the buttons
		theButtons.addNoisyButtonListener(this);

		// add the panel of buttons to the window
		getContentPane().add(theButtons);
		validate();

		// code to start a new song and play it for the user
		theSong = new Song(theButtons);
		theSong.addNote();
		theSong.play();
	}

	/**
	 * this method is called each time the user clicks the mouse within one of
	 * the colored buttons of the display.
	 * 
	 * @param which
	 *            button was pressed
	 */
	public void noisyButtonClicked(NoisyButton theButton) {
		
		// if the user clicks the right button, check to see if they finished the song
		// if the finished he song, add another note and play the song again
		if(theButton == theSong.getExpected()){
			theButton.flash();
			if (theSong.songLength == theSong.expected){
			theSong.addNote();
			theSong.play();
			}else{}
		// if the user presses the wrong button, play a nasty noise and reset the song
		}else{
			nastyNoise.play();
			theButton.flash();
			theSong.reset();
		}
	}

}

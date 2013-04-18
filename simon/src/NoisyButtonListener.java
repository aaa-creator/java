/**
 * This interface defines the call-back function that is to be called whenever a
 * particular NoisyButton is clicked.
 */
public interface NoisyButtonListener {

	/**
	 * Callback handler for notifications that a NoisyButton has been pressed
	 * (clicked with the mouse).
	 * 
	 * @param source
	 *            button that was selected
	 */
	void noisyButtonClicked(NoisyButton source);

}
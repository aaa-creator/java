
/**
 * a Song is a sequence of NoisyButtons that can be played by a SongPlayer.
 */
public class Song {
	// create an array of NoisyButtons that is the song
	NoisyButton[] song = new NoisyButton[100];
	
	// keep track of the song length and expected clicked button
	int songLength = 0;
	int expected = 0;
	
	// instance variables for the panel of buttons 
	ButtonPanel panel;
	
	public Song(ButtonPanel panel){
		this.panel = panel;
	}
	
	// add a new random note to the song and update the song length
	public void addNote(){
		song[songLength] = panel.getRandomButton();
		songLength++;
	}
	
	// make a new song player to play the song and reset the expected button
	public void play(){
		new SongPlayer(song, songLength);
		expected = 0;
	}
	
	// return the button that the user is expected to press
	public NoisyButton getExpected(){
		expected++;
		return song[expected - 1];
	}
	
	// reset the current song and create the first note of a new song
	public void reset(){
		song = new NoisyButton[100];
		songLength = 0;
		expected  = 0;
		this.addNote();
		this.play();
	}
}

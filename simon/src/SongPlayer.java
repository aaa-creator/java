import objectdraw.*;

/**
 * A SongPlayer is an ActiveObject that steps through a sequence of NoisyButtons
 * that comprise a song, playing each in turn.
 */
class SongPlayer extends ActiveObject {
	/** time to pause before the song is played	*/
	private static int INIT_PAUSE = 900; 
	
	/** time to pause between notes of the song	*/
	private static int PAUSE_TIME = 400;

	/** instance variables for the song array and length */
	NoisyButton[] song;
	int songLength;
	
	
	SongPlayer(NoisyButton[] song, int songLength){
		this.song = song;
		this.songLength = songLength;
		start();
	}
	
	public void run(){
		// play each note in the song until you reach the end (song length)
		pause(INIT_PAUSE);
		for (int i = 0; i < songLength; i++){
			song[i].flash();
			pause(PAUSE_TIME);
		}
		
	}
}
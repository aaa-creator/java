/** 
 * This class creates 5 ripples in succession with pauses in between each one
 * Author: Daniel Cogan
 */
import objectdraw.*;

public class Stone extends ActiveObject {
    
    //number of ripples to create and how long to pause between creating
    private static final int NUM_RIPPLES = 5;
    private static final int PAUSE_TIME = 450;
    
    //Location the stone is dropped and the drawing canvas
    Location stonePoint;
    DrawingCanvas canvas;
    
    public Stone (Location point, DrawingCanvas canvasTemp){
    	
    	//Save the canvas stone point as instance variables for run method
    	canvas = canvasTemp;
    	stonePoint = point;
    	start();
        
    }
    
    public void run() {
    	
    	//Makes a ripple then pause some time
    	//Does this a predetermined amount of times
    	int i = 0;
    	while (i < NUM_RIPPLES){
        new Ripple(stonePoint, canvas);
    	pause(PAUSE_TIME);
    	i++;
    }
}
}
/** 
 * This class creates a circle that grows outward until it reaches a certain size
 * Author: Daniel Cogan
 */
import objectdraw.*;

public class Ripple extends ActiveObject {

    //Ripple constants
    private static final int MAX_SIZE = 300;
    private static final int GROW_AMT = 6;
    private static final int PAUSE_TIME = 70;
    
    //Variables for oval that is the ripple, it's original size and position
    double size = 0;
    FramedOval ripple;
    Location mousePt;
    
    public Ripple(Location point, DrawingCanvas canvas){
    	
    	//Draw the initial oval and set the point to and instance variable
    	mousePt = point;
    	ripple = new FramedOval(mousePt, size, size, canvas);
    	start();
    }
    
    public void run () {
        
    	//Increase the size of the oval over time until it reaches max size
    	while(size < MAX_SIZE){
    		ripple.setSize(size, size);
    		ripple.moveTo(mousePt.getX() - size/2.0 , mousePt.getY() - size/2.0);
    		size += GROW_AMT;
    		pause(PAUSE_TIME);
    	}
    	
    	//Delete the oval when it reaches max size
    	ripple.removeFromCanvas();
        
    }
    
}

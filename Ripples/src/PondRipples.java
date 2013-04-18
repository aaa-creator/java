/** 
 * This program makes a ripple every time the mouse is clicked or dragged
 */
import objectdraw.*;

public class PondRipples extends WindowController {
    
	public void begin(){
		resize(450, 450);					//Resize the window
	}	
	
	public void onMousePress(Location pt){
		new Stone(pt, canvas);				//Drop a new stone on a mouse press
	}  
	
	public void onMouseDrag(Location pt){
		new Stone(pt, canvas);				//Drop a new stone everywhere mouse is dragged
	}
	


}

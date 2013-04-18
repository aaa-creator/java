import objectdraw.*;
import java.awt.*;

/**
 * Lab 3 - the "Magnet" game.
 * 	<P>
 *	Simulate the interactions of the poles of a pair of
 *	bar magnets, which can be dragged around with the mouse
 *	and whose poles can be reversed by clicking within the
 *	magnet.
 *  <P>
 *  Suggested window size: 400x400
 *  
 * @author Daniel Cogan
 */
public class MagnetGame extends WindowController
{

	//Variables
	Location mousePT;
	Magnet magnet_1, magnet_2, activeMag, otherMag, noMag;
	private static final double MAGNET1_X = 30;		// X location of magnet_1
	private static final double MAGNET1_Y = 100;	// Y location of magnet_1
	private static final double MAGNET2_X = 270;	// X location of magnet_2
	private static final double MAGNET2_Y = 100;	// Y location of magnet_2
	
	public void begin()
	{
		magnet_1 = new Magnet(MAGNET1_X, MAGNET1_Y, canvas); //Creates a magnet
		magnet_2 = new Magnet(MAGNET2_X, MAGNET2_Y, canvas); //Creates a magnet
		

	}

	/**
	 * Event handler, called when mouse button is pressed (and held).
	 * If within a magnet, this initiates a drag of that magnet.
	 */
	public void onMousePress(Location point)
	{
		mousePT = point;
		
		if(magnet_1.contains(point)){
			activeMag = magnet_1;
			otherMag = magnet_2;
		}
		else if (magnet_2.contains(point)){
			activeMag = magnet_2;
			otherMag = magnet_1;
		}
	}

	/**
	 * Event handler, called (periodically) when mouse is moved w/button held.
	 * If a magnet is being dragged, move it to follow the cursor.
	 *
	 * param point    current mouse coordinates 
	 */
	public void onMouseDrag(Location point)
	{
		
		activeMag.move(point.getX() - mousePT.getX(), point.getY() - mousePT.getY());
		mousePT = point;
		activeMag.interact(otherMag);
	}

	public void onMouseRelease(Location point){
		activeMag = noMag ;
		
	}
	/**
	 * Event handler, called when mouse button is "clicked" (pressed and released).
	 * If click is within a magnet, this reverses the poles.
	 *
	 * param point    mouse coordinates at time of click
	 */
	public void onMouseClick( Location point)
	{
		if (magnet_1.contains(point)){
			magnet_1.flip();
			magnet_1.interact(magnet_2);
		}
		else if (magnet_2.contains(point)){
			magnet_2.flip();
			magnet_2.interact(magnet_1);
		}
		
	}

}

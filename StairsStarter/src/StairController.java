import java.awt.*;
import objectdraw.*;

/**
 * Lab 7 (part II) - Stairs
 * <p>
 * Recursively construct a stairway, which is made up of
 * rectangles, surrounded by recursively smaller rectantles,
 * approximating a triangle.
 * <p>
 * Suggested window size: 400x400
 *
 * @author YOUR-NAME-HERE, YOUR-LAB-SECTION-HERE
 */
public class StairController extends WindowController
{
	/** coordinates of the lower left corner of the variegated stairs	*/
	private static final Location lowerLeft = new Location(100, 300);

	/** size of the base square in the variegated stairs	*/
	private static final double initialSize = 128;

	/** color of the base square			*/
	private static final Color initialColor = new Color(225, 225, 255);

	/** stairs to be drawn					*/
	private VariegatedStairs stairs;

	/** location where mouse was last		*/
	private Location lastPoint;

	/** whether stairs are being dragged	*/
	private boolean dragging;

	/**	  
	 * Initialization method, called when applet starts.
	 * <p>
	 * 
	 */
	public void begin() {resize(450, 400);
		stairs = new VariegatedStairs(lowerLeft, initialSize, initialColor, canvas);
	}

	/**
	 * Event handler, called when mouse button is pressed
	 * <p>
	 * Note location of press in preparation for dragging the stairway.
	 *
	 * @param point	mouse coordinates at time of press
	 */
	public void onMousePress(Location point) {
		lastPoint = point;
		dragging = stairs.contains(point);
	}

	/**
	 * Event handler, called (periodically) when mouse is moved w/button held
	 * <p>
	 * Drag the the stairway.
	 *
	 * @param point	mouse coordinates after recent motion
	 */
	public void onMouseDrag(Location point) {
		if (dragging){
			stairs.move(point.getX() - lastPoint.getX(), 
					point.getY() - lastPoint.getY());
			lastPoint = point;
		}
	}
}

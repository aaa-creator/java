import java.awt.Color;

import objectdraw.*;

/**
 * Lab 7 (part I) - Triangulate
 * <p>
 * Recursively construct a triangle, which is made up of
 * sub-triangles, which are in turn ...
 * <p>
 * Suggested window size: 400x400
 *
 * @author YOUR-NAME-HERE, YOUR-LAB-SECTION-HERE
 */
public class Triangulate extends WindowController {
	// the three vertices of the doodled triangle
	private static final Location vertex1 = new Location(50, 200);
	private static final Location vertex2 = new Location(250, 200);
	private static final Location vertex3 = new Location(150, 50);

	/** where the mouse was at last report	*/
	private Location lastPoint;

	/** doodle being drawn and dragged		*/
	private TriangleDoodle doodle;

	/**
	 * Initialization method, called when applet starts.
	 * <p>
	 * 
	 */
	public void begin() {resize(400, 400);
		doodle = new ComplexTriangleDoodle(vertex1, vertex2, vertex3, Color.BLACK, canvas);
	}

	/**
	 * Event handler, called when mouse button is pressed
	 * <p>
	 * Note location of press in preparation for dragging the triangle.
	 *
	 * @param point	mouse coordinates at time of press
	 */
	public void onMousePress(Location point) {
		lastPoint = point;
	}

	/**
	 * Event handler, called (periodically) when mouse is moved w/button held
	 * <p>
	 * Drag the the triangle
	 *
	 * @param point	mouse coordinates after recent motion
	 */
	public void onMouseDrag(Location point) {
		doodle.move(point.getX() - lastPoint.getX(), 
				point.getY() - lastPoint.getY());
		lastPoint = point;
	}
}

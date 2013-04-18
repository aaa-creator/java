/**
 * Applet that creates a balloon with a head and a basket and allows user 
 * to resize/reshape and drag it.
 */

import java.awt.*;
import objectdraw.*;

public class BalloonManager extends WindowController {
	// Balloon starting location
	private static final Location BALLOON_LOCN = new Location(50, 80);

	private static final double BALLOON_WIDTH = 100; // balloon starting width
	private static final double BALLOON_HEIGHT = 120; // balloon starting height

	private Balloon testBalloon; // The balloon to be displayed
	private Location lastPoint; // where mouse was before a drag
	private boolean resizing, // whether user dragging in resize box
			dragging; // whether user dragging in balloon

	// Create balloon on canvas
	public void begin() {resize(500, 500);
		testBalloon = new Balloon(BALLOON_LOCN, BALLOON_WIDTH, BALLOON_HEIGHT,
				canvas);
	}

	// Remember whether user pressed mouse in resize button or in the body of
	// balloon
	public void onMousePress(Location point) {
		resizing = testBalloon.inResizeButton(point);
		dragging = testBalloon.inBalloon(point);
		lastPoint = point;
	}

	/*
	 * If mouse pressed in resize button then resize it. If mouse pressed in the
	 * body of the balloon then drag it.
	 */
	public void onMouseDrag(Location point) {
		if (resizing) {
			testBalloon.resizeTo(point.getX() - lastPoint.getX(), point.getY() - lastPoint.getY());
		} else if (dragging) {
			testBalloon.move(point.getX() - lastPoint.getX(), point.getY()
					- lastPoint.getY());
		}
		lastPoint = point;
	}

}

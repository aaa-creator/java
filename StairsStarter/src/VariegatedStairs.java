import objectdraw.*;
import java.awt.*;

public class VariegatedStairs implements StairsInterface {

	/** minimum width and height for a base square in order to build around it */
	private static final int MINSIZE = 2;
	/** used to darken the color of stairs built around the base square	*/
	private static final int DARKNESS_FACTOR = 30;
	
	//Instance variables for the rectangle, the frame and each's above and right
	FilledRect rect;
	FramedRect rectFrame;
	StairsInterface above, right;
	
	public VariegatedStairs(Location lowerLeft, double size, Color color, DrawingCanvas canvas){
		//Darken the color for the next rectangles
		color  = new Color(color.getRed() - DARKNESS_FACTOR, color.getGreen() - DARKNESS_FACTOR, color.getBlue());
		
		//Draw the rectangle and its frame and set the color
		rect = new FilledRect(lowerLeft.getX(), lowerLeft.getY() - size, size, size, canvas);
		rectFrame = new FramedRect(lowerLeft.getX(), lowerLeft.getY() - size, size, size, canvas);
		rect.setColor(color);
		
		if (size > MINSIZE){
		//Draw a rectangle above and to the right of each rectangle (recursive)
			Location rightLocation = new Location(rect.getX() + size, rect.getY() + size);
			above = new VariegatedStairs(rect.getLocation(), size/2.0, color, canvas);
			right = new VariegatedStairs(rightLocation, size/2.0, color, canvas);
			
		}else{
			above = new EmptyStairs();					//Draw empty stairs when the rectangles get too small
			right = new EmptyStairs();					//AKA stop the recursion when they get too small
		}	
	}

	public void move(double dx, double dy) {
		rect.move(dx, dy);								//Move the rectangle and its frame for each above and right
		rectFrame.move(dx, dy);
		
		above.move(dx, dy);								//Move each above and right
		right.move(dx, dy);
	}
	
	public boolean contains(Location point){			//See if any of the above or right's rects contain a point
		
		return above.contains(point) || right.contains(point) || rect.contains(point);
	}
}
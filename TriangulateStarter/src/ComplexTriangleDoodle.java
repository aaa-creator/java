
import objectdraw.*;

import java.awt.*;

/**
 * A recursive class that creates a triangle, and then
 * (recursively) fills it in with smaller triangles
 * (until they get too small, at which point we fill 
 * it with an empty triangle to finish the recursion)
 */
public class ComplexTriangleDoodle implements TriangleDoodle {
	/** minimum size of a triangle side for further doodling	*/
	private static final int MINSIZE = 12;
	
	//Instance variables for the lines of the triangle, the midpoints and the left, top and right triangles
	Line line1, line2, line3;
	Location mid12, mid23, mid31;
	TriangleDoodle left, top, right;
	
	public ComplexTriangleDoodle(Location vertex1, Location vertex2, Location vertex3, Color color, DrawingCanvas canvas){
		
		//Define the midpoints as the points inbetween the vertecies
		mid12 = new Location((vertex1.getX() + vertex2.getX())/2.0, (vertex1.getY() + vertex2.getY())/2.0);
		mid23 = new Location((vertex2.getX() + vertex3.getX())/2.0, (vertex2.getY() + vertex3.getY())/2.0);
		mid31 = new Location((vertex3.getX() + vertex1.getX())/2.0, (vertex3.getY() + vertex1.getY())/2.0);
		
		//Draw a triangle and set it to a certain color
		line1 = new Line(vertex1, vertex2, canvas);
		line1.setColor(color);
		line2 = new Line(vertex2, vertex3, canvas);
		line2.setColor(color);
		line3 = new Line(vertex3, vertex1, canvas);
		line3.setColor(color);

		if (vertex1.distanceTo(vertex2) > MINSIZE){
		//For the above triangle, give it a left, right and top triangle inside of it
		//Set the left to color green, the right to color blue and the top to color red
		left = new ComplexTriangleDoodle(vertex1, mid12, mid31, Color.GREEN, canvas);
		right = new ComplexTriangleDoodle(mid12, vertex2, mid23, Color.BLUE, canvas);
		top = new ComplexTriangleDoodle(mid31, mid23, vertex3, Color.RED, canvas);
			
		}else{
		//If the triangles get too small, create a blank triangle to stop the recursion
		left = new EmptyTriangleDoodle();
		right = new EmptyTriangleDoodle();
		top = new EmptyTriangleDoodle();
		}
	}

	public void move(double dx, double dy) {
		line1.move(dx, dy);
		line2.move(dx, dy);					//Move the lines of each triangle
		line3.move(dx, dy);
		
		left.move(dx, dy);
		right.move(dx, dy);					//Move the inside triangles of each triangle
		top.move(dx, dy);
	}


}
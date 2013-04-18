// A class to represent a non-empty scribble
import objectdraw.*;
import java.awt.*;

public class Scribble implements ScribbleIfc {
   private Line first;			// an edge line of the scribble
   private ScribbleIfc rest;		// the rest of the scribble

   public Scribble(Line segment, ScribbleIfc theRest) {
      first = segment;
      rest = theRest;
   }

   // returns true iff the scribble contains the point
   public boolean contains(Location point) {
      return (first.contains(point) || rest.contains(point));
   }

   // the scribble is moved xOffset in the x direction
   //    and yOffset in the y direction
   public void move(double xOffset, double yOffset) {
      first.move(xOffset, yOffset);
      rest.move(xOffset, yOffset);
   }

   // color the scribble the desired color
   public void setColor(Color c) {
	  first.setColor(c);
	  rest.setColor(c);
   }
   
   // erase the scribble
   public void erase() {
	first.removeFromCanvas();
	rest.erase();
   }
}
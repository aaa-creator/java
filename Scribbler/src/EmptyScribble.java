/* Class representing an empty scribble */
import objectdraw.*;
import java.awt.*;

public class EmptyScribble implements ScribbleIfc{
   public EmptyScribble() {
   }

   // point is never in an empty scribble!
   public boolean contains(Location point) {
      return false;
   }

   // nothing to move, so do nothing!
   public void move(double dx, double dy) {
   }

   // nothing to color, so do nothing!
   public void setColor(Color c) {
   }

   // nothing to erase, so do nothing!
   public void erase() {
   }
}

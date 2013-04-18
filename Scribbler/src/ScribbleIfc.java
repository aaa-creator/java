import java.awt.*;
import objectdraw.*;

public interface ScribbleIfc
{
   // returns whether point is contained in scribble
   boolean contains(Location point);

   // move scribble by dx in x-direction and dy in y-direction
   void move(double dx, double dy);
   
   void setColor(Color c);

   void erase();
   
}

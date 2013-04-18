import objectdraw.*;
import java.awt.*;

//   CS 51 Laboratory 1 -- practice program
//   Daniel Cogan  1/24/13

public class NoClicking extends WindowController{

	//Variables
	   private Text message;
	   private FilledRect sign;
	   private FramedOval sign_oval;
	   private Line sign_line;
	   
   	//Draws the sign when the program begins
	
   public void begin()
   {
	   sign = new FilledRect(150, 50, 100, 100, canvas);
	   new FramedRect(150, 50, 100, 100, canvas);
	   sign_oval = new FramedOval(155, 55, 90, 90, canvas);
	   sign_line = new Line(168, 70, 231, 134, canvas);
	   new FilledRect(197.5, 150, 5, 200, canvas);
	   new Line(110, 350, 290, 350, canvas);
	   sign.setColor(Color.YELLOW);
	   message = new Text("CLICKING", 172, 95, canvas);
   }

   	//Hide the oval and line when the mouse button is down
   public void onMousePress(Location point)
   {
    sign_line.hide();
    sign_oval.hide();
   
   }


   public void onMouseEnter(Location point)
   {
	   message.setColor(Color.RED);
	   
   }

   public void onMouseExit(Location point)
   {
	   message.setColor(Color.BLACK);
   }

   public void onMouseClick(Location point)
   {

   }
   //Show the oval and line when the mouse button is released
   public void onMouseRelease(Location point)
   {
    sign_line.show();
    sign_oval.show();
   }

}

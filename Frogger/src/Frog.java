import java.awt.*;
import objectdraw.*;

public class Frog {
	// Declare instance variables
	VisibleImage frog;
	boolean alive  = true;
	Location startPt;
	Text ouch, congrats;
	int lane = 0;

public Frog(Location pt, Image frogPic, DrawingCanvas canvas){
	
	// Create the frog including the OUCH text and Congratulations text
	frog = new VisibleImage(frogPic, pt, canvas);
	startPt = pt;
	ouch = new Text("OUCH!", pt, canvas);
	ouch.setFontSize(20);
	ouch.setColor(Color.RED);
	ouch.hide();
	congrats = new Text("CONGRAGULATIONS", pt, canvas);
	congrats.setFontSize(20);
	congrats.hide();

}

	// Kills the frog and shows the OUCH text if frog got run over
	// and show Congratulations text if the frog made it to the top
	public void kill(){
		alive = false;
		if (lane>4){
			congrats.show();
		}else{
		ouch.show();
		}
	}
	
	// Set the frog's state to alive and move the forg back to the bottom
	public void reincarnate(){
		frog.moveTo(startPt);
		lane = 0;
		alive = true;
		ouch.hide();
		congrats.hide();
	}

	// Move the frog up a lane
	public void moveUp(){
		if (lane == 0){
			frog.moveTo(frog.getX(), Frogger.ln1.laneY + 15);
		}
		else if (lane == 1){
			frog.moveTo(frog.getX(), Frogger.ln2.laneY + 15);
		}
		else if (lane == 2){
			frog.moveTo(frog.getX(), Frogger.ln3.laneY + 15);
		}
		else if (lane == 3){
			frog.moveTo(frog.getX(), Frogger.ln4.laneY + 15);
		}
		else if (lane == 4){
			frog.move(0, -70);
		}

		lane++;	// change the lane of the frog
	}
	
	// Move the frog down a lane
	public void moveDown(){
		if (lane == 4){
			frog.moveTo(frog.getX(), Frogger.ln3.laneY + 15);
		}
		else if (lane == 3){
			frog.moveTo(frog.getX(), Frogger.ln2.laneY + 15);
		}
		else if (lane == 2){
			frog.moveTo(frog.getX(), Frogger.ln1.laneY + 15);
		}
		else if (lane == 1){
			frog.move(0, 110);
		}
		
		lane--;	// change the lane of the frog
	}
	
	// Move the frog to the right by 110
	public void moveRight(){
		frog.move(100, 0);
	}
	
	// Move the frog to the left by 110
	public void moveLeft(){
		frog.move(-100, 0);
	}
	
	// return the right x coordinate of the frog
	public double getRight(){
		return frog.getX()+frog.getWidth();
	}
	
	// return the right x coordinate of the frog
	public double getLeft(){
		return frog.getX();
	}
}

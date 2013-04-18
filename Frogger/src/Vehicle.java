import java.awt.*;
import objectdraw.*;

public class Vehicle extends ActiveObject{

	// Declare instance variables
	VisibleImage vehicle;
	boolean fromRight;
	Frog frog;
	int lane;
	
	// Declare random int generator for the speed of the vehicle
	RandomIntGenerator speedGen = new RandomIntGenerator(7, 10);
	public Vehicle(double x, double y, Frog frogTemp, boolean fromRightTemp, 
			Image carPic, DrawingCanvas canvas){
		
		// pass in the frog 
		frog = frogTemp;
		
		// a boolean the determines if the cars are coming from the right or left
		fromRight = fromRightTemp;
		
		// draw the vehicle on the canvas
		vehicle = new VisibleImage(carPic, x, y, canvas);
		
		// start the run method
		start();
		
		// Tell the vehicle which lane it is in
		if (y == Frogger.ln1.laneY){
			lane = 1;
		}
		else if (y == Frogger.ln2.laneY){
			lane = 2;
		}
		else if (y == Frogger.ln3.laneY){
			lane = 3;
		}
		else if (y == Frogger.ln4.laneY){
			lane = 4;
		}
	}
	
	public void run(){
		// While the vehicle is on the lane, move it a random but constant speed 
		if (fromRight){
			int speed = speedGen.nextValue();
			while (vehicle.getX() < Frogger.roadX + Frogger.LANE_WIDTH){
				vehicle.move(1, 0);
				pause(speed);
				if (this.getRight() > frog.getLeft() && this.getLeft() < frog.getRight() && frog.lane == lane && frog.alive){
					frog.kill();
					Frogger.deaths++;
					Frogger.deathsText.setText("Deaths: " + Frogger.deaths);
				}
			}
		}
		
		// While the vehicle is on the lane, move it a random but constant speed 
		else if (!fromRight){
			int speed = speedGen.nextValue();
			while (vehicle.getX() > Frogger.roadX - 100){
				vehicle.move(-1, 0);
				pause(speed);
				if (this.getRight() > frog.getLeft() && this.getLeft() < frog.getRight() && frog.lane == lane && frog.alive){
					frog.kill();
					Frogger.deaths++;
					Frogger.deathsText.setText("Deaths: " + Frogger.deaths);
				}
			}
			
		}
		vehicle.removeFromCanvas(); 			// remove the vehicle after it is off the lane
	}
	
	// returns the right x coordinate of the vehicle
	public double getRight(){
		return vehicle.getX()+vehicle.getWidth();
	}
	
	// returns the left x coordinate of the vehicle
	public double getLeft(){
		return vehicle.getX();
	}

	

}

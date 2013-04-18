import java.awt.*;
import objectdraw.*;

public class Lane extends ActiveObject{
	// Declare the instance variables
	Frog frog;
	double laneX, laneY;
	FilledRect sideRight, sideLeft;
	Image carPic;
	boolean fromRight;
	DrawingCanvas canvas;
	
	// Create number generators for the delay between cars in the lane
	RandomIntGenerator carInitialDelay = new RandomIntGenerator(500, 2000);
	RandomIntGenerator carDelay = new RandomIntGenerator(3000, 8000);
	
	public Lane(double x, double y, double width, double height, 
			Frog frogTemp, boolean fromRightTemp, Image carPicTemp, 
				DrawingCanvas canvasTemp){
		
		// Create the lane on the canvas
		canvas = canvasTemp;
		fromRight = fromRightTemp;
		new FilledRect(x, y, width, height, canvas);
		sideLeft = new FilledRect(x - 100, y, 100, height, canvas);
		sideRight = new FilledRect(x + width, y, 100, height, canvas);
		sideLeft.setColor(Color.WHITE);
		sideRight.setColor(Color.WHITE);
		
		carPic = carPicTemp;
		frog = frogTemp;
		laneX = x;
		laneY = y;
		
		start();		// start the run method
	}
	
	
	public void run(){
		// Create a new vehicle coming from the right at intervals 
		while (true){
			if (fromRight){
				pause(carInitialDelay.nextValue());
			new Vehicle(laneX - 100, laneY, frog, fromRight, carPic, canvas);
			sideRight.sendToFront();
			sideLeft.sendToFront();
				pause(carDelay.nextValue());
			
			}
			// Create a new vehicle coming from the left at intervals 
			else if (!fromRight){
				pause(carInitialDelay.nextValue());
				new Vehicle( laneX + Frogger.LANE_WIDTH, laneY, frog, fromRight, carPic, canvas);
				sideRight.sendToFront();
				sideLeft.sendToFront();
				pause(carDelay.nextValue());
			}
		}
	}


}

import objectdraw.*;

import java.awt.*;

public class Box{
	
	//Instance variables for properties of the box so they can be used in the methods below
	
	FilledRect rect;
	static double left, right, yCord, boxWidth, boxHeight, boxY;
	static RandomDoubleGenerator boxX;
	
	public Box(double y, double width, double height, DrawingCanvas canvas){
		boxHeight = height;
		boxWidth = width;							// Assigns y, width and height to variables so they can be
		boxY = y;									// used in the rePosition method
		rect = new FilledRect(0, 0, 0, 0, canvas);
		this.rePosition(canvas);					//positions the box when it is created
	}
	
	public void rePosition(DrawingCanvas acanvas){
		boxX = null;
		rect.removeFromCanvas();
		boxX = new RandomDoubleGenerator(30, 30 + BoxBall.AREA_WIDTH - boxWidth);
		left = boxX.nextValue();													// Re=positions the box
		rect = new FilledRect(left, boxY, boxWidth, boxHeight, acanvas);				
		right = left + boxWidth;
	
	}
	
	public void setEasy(){
		boxWidth = 70;						// Makes the box large and the clicking area large
		rect.setWidth(boxWidth);
	}
	public void setMedium(){
		boxWidth = 52;						// Makes the box medium sized and the clicking area medium sized
		rect.setWidth(boxWidth);
	}
	public void setHard(){
		boxWidth = 35;						// Makes the box small and the clicking area small
		rect.setWidth(boxWidth);
	}
	
	public double getLeft(){
		return left;						// Returns the left edge (X coordinate) of the box
	}
	
	public double getRight(){
		return right;						// Returns the left edge (X coordinate) of the box
	}
	
}

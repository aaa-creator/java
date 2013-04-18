/**
 * This class creates a balloon and gives methods to resize and move the balloon
 * also gives methods to see if a point is contained by the resizer or balloon
 * Author: Daniel Cogan
 */

import java.awt.Color;
import objectdraw.*;

public class Balloon {

	private static final double HEAD_SIZE = 20; // size of the person's head
	private static final double RESIZE_SIZE = 10; // size of the square resize button
	private static final double BASKET_HEIGHT = 40; // height of the basket
	private static final double BASKET_WIDTH = 30;  // width of the basket
	
	//Objects for balloon, person, resizer and basket
	FilledOval balloon, head;
	FilledRect resize, basket;
	Text text;

	public Balloon(Location point, double balloonWidth, double balloonHeight, DrawingCanvas canvas){
		
		//Draw the balloon, person, resizer, text and basket
		balloon = new FilledOval(point, balloonWidth, balloonHeight, canvas);
		resize = new FilledRect(balloon.getX() + balloonWidth - RESIZE_SIZE, balloon.getY() + balloonHeight - RESIZE_SIZE, RESIZE_SIZE, RESIZE_SIZE, canvas);
		head = new FilledOval(balloon.getX() + (balloon.getWidth() - HEAD_SIZE)/2, balloon.getY() + balloon.getHeight(), HEAD_SIZE, HEAD_SIZE, canvas);
		basket = new FilledRect(balloon.getX() + (balloon.getWidth() - BASKET_WIDTH)/2, balloon.getY() + balloon.getHeight() + HEAD_SIZE, BASKET_WIDTH, BASKET_HEIGHT, canvas);
		text = new Text("My Balloon", 0, 0, canvas);
		text.moveTo(balloon.getX() + (balloon.getWidth() - text.getWidth())/2, balloon.getY() + balloon.getHeight()/2);
		balloon.setColor(Color.PINK);
		resize.setColor(Color.BLUE);
		basket.setColor(Color.ORANGE);
		head.setColor(Color.yellow);
	}

	public void resizeTo(double x, double y){			//Resizes the balloon to a certain point
		resize.move(x, y);
		balloon.setSize(balloon.getWidth() + x, balloon.getHeight() + y);
		head.moveTo(balloon.getX() + (balloon.getWidth() - HEAD_SIZE)/2, balloon.getY() + balloon.getHeight());
		basket.moveTo(balloon.getX() + (balloon.getWidth() - BASKET_WIDTH)/2, balloon.getY() + balloon.getHeight() + HEAD_SIZE);
		text.moveTo(balloon.getX() + (balloon.getWidth() - text.getWidth())/2, balloon.getY() + balloon.getHeight()/2);
		
	}
	
	public boolean inResizeButton(Location point){	//Returns if a point is contained by the resizer
		return resize.contains(point);
	}

	public boolean inBalloon(Location point){		//Returns if a point is contained by the balloon
		return balloon.contains(point);
	}

	public void move(double dx, double dy){			//Moves all elements by an x and y amount
		balloon.move(dx, dy);
		resize.move(dx, dy);
		basket.move(dx, dy);
		head.move(dx, dy);
		text.move(dx, dy);
	}

	}


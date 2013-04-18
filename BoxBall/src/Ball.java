import objectdraw.*;

public class Ball extends ActiveObject {

	//Instance variables for properties of the ball so they can be used in the methods below
	FilledOval ball;
	Box box;
	double left, right;
	private static double BALL_RADIUS = 15;
	private static double BALL_SPEED = 2;
	DrawingCanvas acanvas;

	public Ball(Location pt, Box box1, DrawingCanvas canvas) {
		ball = new FilledOval(pt.getX() - BALL_RADIUS, 
				pt.getY() - BALL_RADIUS, BALL_RADIUS * 2, 	// Creates the circle that is the ball
				BALL_RADIUS * 2, canvas);
		box = box1;
		left = pt.getX()- BALL_RADIUS;
		right=pt.getX()+BALL_RADIUS;
		
		acanvas = canvas;		// Assigns a variable to canvas so it can be used in the box.rePosition method

		start();				// Initiates the run method
	}

	public void run() {
		while (ball.getY() < 530 - BALL_RADIUS * 2) {
			
			ball.move(0, 1);						// Makes the ball fall until it get to the bottom
			pause(BALL_SPEED);
		}
		if (box.getLeft() < this.getLeft() && box.getRight() > this.getRight()){
			BoxBall.score ++;
			BoxBall.scoreText.setText("Your score is:" + BoxBall.score);		// Adds to the score if the ball gets in the box
			BoxBall.box.rePosition(acanvas);
		}
		else{
			BoxBall.tryAgain.show();				// Says "TRY AGAIN" if the ball does not get in the box
		}
		
		ball.removeFromCanvas();					// Deletes the ball after it gets to the bottom
	}
	
	public double getLeft(){
		return left;								// Returns the left edge (X coordinate) of the ball
	}
	public double getRight(){						// Returns the left edge (X coordinate) of the ball
		return right;
	}


}

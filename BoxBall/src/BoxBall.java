import objectdraw.*;

import java.awt.*;

public class BoxBall extends WindowController{

	/**
	 * Declares instance variables for the sizes of the objects in the begin method
	 */
	static double AREA_WIDTH = 500;
	private static double AREA_HEIGHT = 500;
	
	private static double BUTTON_WIDTH = (0.9*AREA_WIDTH)/3;
	private static double BUTTON_HEIGHT = AREA_HEIGHT/10;
	
	private static double BOX_HEIGHT = 60;

	/**
	 * Assigns variable names to different objects that are created in the begin method
	 */
	
	FramedRect area, activeArea;
	FilledRect easy, medium, hard;
	Text easyText, mediumText, hardText;
	static Text scoreText, tryAgain;
	static int score = 0;
	static Box box;

	
	public void begin(){
		resize(700, 700);
		
		/**
		 * Creates all the shapes when the program begins
		 */
		
		area = new FramedRect(30, 30, AREA_WIDTH, AREA_HEIGHT, canvas);			// Creates the two boxes.  One that you can create 
		activeArea = new FramedRect(30, 30, AREA_WIDTH, 350, canvas);			// balls in and the other where the box is.
		
		scoreText = new Text("Your score is: " + score, 235, 10, canvas);	
		tryAgain = new Text("TRY AGAIN", 238, 40, canvas);
		tryAgain.hide();														// Creates the score and try again text
		tryAgain.setFontSize(20);
		tryAgain.setColor(Color.RED);
		
		easy = new FilledRect(30, 30 + AREA_HEIGHT + 20, BUTTON_WIDTH,
				BUTTON_HEIGHT, canvas);
		medium = new FilledRect(30 + BUTTON_WIDTH + 0.05 * AREA_WIDTH,
				30 + AREA_HEIGHT + 20, BUTTON_WIDTH, BUTTON_HEIGHT, canvas);
		hard = new FilledRect(30 + AREA_WIDTH - BUTTON_WIDTH,
				30 + AREA_HEIGHT + 20, BUTTON_WIDTH, BUTTON_HEIGHT, canvas);
		easy.setColor(Color.GREEN);												// Creates the buttons at the bottom allowing you to
		medium.setColor(Color.YELLOW);											// change the difficulty to easy, medium or hard
		hard.setColor(Color.RED);
		easyText = new Text("EASY", easy.getX() + 10, easy.getY() + 10, canvas);
		easyText = new Text("MEDIUM", medium.getX() + 10, medium.getY() + 10, canvas);
		easyText = new Text("HARD", hard.getX() + 10, hard.getY() + 10, canvas);
		
		
	
		box = new Box( 30+AREA_HEIGHT-BOX_HEIGHT, 70, BOX_HEIGHT, canvas);		// Creates a box at the bottom of the playing area
		
	}
	
	
	public void onMouseClick(Location pt){
		tryAgain.hide();					// Hides the text "TRY AGAIN" for the next try
		
		if (activeArea.contains(pt)){		// If you click in the upper box, it will create a new ball
		new Ball(pt, box, canvas);
		
		}else if (easy.contains(pt)){		// If you click on the easy button, it will make the clicking
			activeArea.setHeight(350);		// area large and the box large
			box.setEasy();
			
		}else if (medium.contains(pt)){
			activeArea.setHeight(250);		// If you click on the easy button, it will make the clicking
			box.setMedium();				// area small and the box small
			
		}else if (hard.contains(pt)){
			activeArea.setHeight(150);		// If you click on the easy button, it will make the clicking
			box.setHard();					// area very small and the box very small
		}
	}
	
}

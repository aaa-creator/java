
import java.awt.*;
import objectdraw.*;

public class Frogger extends WindowController{
	//Declare Images 
	Image froggyPic, car1, car2, car3, car4;


	// Declare constants
	static double LANE_HEIGHT = 100;
	static double LANE_WIDTH = 900;
	static double roadX = 200;
	static double roadY = 100;
	FilledRect yellow1, yellow2, sideLeft, sideRight;
	
	// Declare the Frog
	Frog frog;
	Location startPt = new Location(roadX+(LANE_WIDTH/2) - 20, roadY + LANE_HEIGHT*(65.0/14.0) + 22);

	// Declare the lanes and the score and the deaths
	static Lane ln1, ln2, ln3, ln4;
	static Text scoreText, deathsText;
	int score = 0;
	static int deaths = 0;
	
public void begin(){resize(1200, 700);

	//Draw the Frog and score
	froggyPic = getImage("froggy.gif");
	frog = new Frog(startPt, froggyPic, canvas);
	scoreText = new Text("Score: " + score, roadX, roadY-50, canvas);
	deathsText = new Text("Deaths: " + score, roadX + LANE_WIDTH - 60, roadY-50, canvas);
	
	/**
	 * Give each lane a random car each time the program starts
	 */

	RandomIntGenerator carGen = new RandomIntGenerator(1, 4);
	int option;
	
	//Lane 1 Car
	option = carGen.nextValue();
	if (option == 1){
		car1 = getImage("jeep_right.gif");
	}
	else if (option == 2){
		car1 = getImage("oldcar_right.gif");
	}
	else if (option == 3){
		car1 = getImage("van_right.gif");
	}
	else if (option == 4){
		car1 = getImage("taxi_right.gif");
	}
	
	//Lane 2 Car
	option = carGen.nextValue();
	if (option == 1){
		car2 = getImage("jeep_right.gif");
	}
	else if (option == 2){
		car2 = getImage("oldcar_right.gif");
	}
	else if (option == 3){
		car2 = getImage("van_right.gif");
	}
	else if (option == 4){
		car2 = getImage("taxi_right.gif");
	}
	
	//Lane 3 Car
	option = carGen.nextValue();
	if (option == 1){
		car3 = getImage("jeep_left.gif");
	}
	else if (option == 2){
		car3 = getImage("oldcar_left.gif");
	}
	else if (option == 3){
		car3 = getImage("van_left.gif");
	}
	else if (option == 4){
		car3 = getImage("taxi_left.gif");
	}
	
	//Lane 4 Car
	option = carGen.nextValue();
	if (option == 1){
		car4 = getImage("jeep_left.gif");
	}
	else if (option == 2){
		car4 = getImage("oldcar_left.gif");
	}
	else if (option == 3){
		car4 = getImage("van_left.gif");
	}
	else if (option == 4){
		car4 = getImage("taxi_left.gif");
	}

	

	
	//Draw 4 Lanes with lines in-between them
	ln4 = new Lane(roadX, roadY, LANE_WIDTH, LANE_HEIGHT, frog, false, car4, canvas);
	ln3 = new Lane(roadX, roadY + LANE_HEIGHT*(8.0/7.0), LANE_WIDTH, LANE_HEIGHT, frog, false, car3, canvas);
	ln2 = new Lane(roadX, roadY + LANE_HEIGHT*(35.0/14.0), LANE_WIDTH, LANE_HEIGHT, frog, true, car2, canvas);
	ln1 = new Lane(roadX, roadY + LANE_HEIGHT*(51.0/14.0), LANE_WIDTH, LANE_HEIGHT, frog, true, car1, canvas);
	
	// Draw the background (lane lines)
		double i = 30;
		while(i < LANE_WIDTH){
			new FilledRect(roadX + i, roadY + LANE_HEIGHT, 20, LANE_HEIGHT/7, canvas);
			i = i + LANE_WIDTH/10;
		}
		yellow1 = new FilledRect(roadX, roadY + LANE_HEIGHT*(15.0/7.0), LANE_WIDTH, LANE_HEIGHT/7, canvas);
			yellow1.setColor(Color.YELLOW);
			new FilledRect(roadX, roadY +(LANE_HEIGHT+LANE_HEIGHT/7)*2, LANE_WIDTH, LANE_HEIGHT/14, canvas);
		yellow2 = new FilledRect(roadX, roadY + LANE_HEIGHT*(33.0/14.0), LANE_WIDTH, LANE_HEIGHT/7, canvas);
			yellow2.setColor(Color.YELLOW);
	
		i = 30;
		while(i < LANE_WIDTH){
			new FilledRect(roadX + i, roadY + LANE_HEIGHT*(49.0/14.0), 20, LANE_HEIGHT/7, canvas);
			i = i + LANE_WIDTH/10;
		}
	
}

public void onMousePress(Location pt){
	frog.frog.sendToFront();
	
	/**
	 * Move the frog in the direction of the mouse press if the frog is alive
	 * If the frog is dead and user clicks at bottom, reincarnate the frog
	 */
	
	if (frog.alive){
		if (pt.getX() < frog.frog.getX()){
			frog.moveLeft();
		}
		else if (pt.getX() > frog.frog.getX() + frog.frog.getWidth()){
			frog.moveRight();
		}
		else if (pt.getY() < frog.frog.getY()){
			frog.moveUp();
			if (frog.lane > 4){
				frog.kill();
				score++;
				scoreText.setText("Score: " + score);
			}
		}
		else if (pt.getY() > frog.frog.getY() + frog.frog.getHeight()){
			frog.moveDown();
		}
	}
	else if (!frog.alive && pt.getY() > roadY + LANE_HEIGHT*(65.0/14.0)){
		
			frog.reincarnate();
		
	}
		
	
}
}

import objectdraw.*;
import java.awt.*;

//   CS 51 Laboratory 1 -- practice program
//   Daniel Cogan  1/24/13

public class Laundry extends WindowController{
	
	//Declare variables
	int clothColor, blacktime, redtime, whitetime, blacktimeNo, redtimeNo, whitetimeNo;
	FramedRect bask1, bask2, bask3, clothWhite;
	FilledRect clothRed, clothBlack;
	Text answer, blackLabel, redLabel, whiteLabel,blackLabelNo, redLabelNo, whiteLabelNo, black, red, white;
	Location clothPoint = new Location(240, 100);
	double offx, offy;
	
	//Define random number generator
	RandomIntGenerator generator = new RandomIntGenerator(1,3);
	
	
	public void begin(){
		
		//Draw 3 laundry baskets and text
		bask1 = new FramedRect(80, 200, 50, 30, canvas);
		bask2 = new FramedRect(230, 200, 50, 30, canvas);
		bask3 = new FramedRect(380, 200, 50, 30, canvas);
		black = new Text("Black", 84, 205, canvas);
		black.setColor(Color.BLACK);
		black.setFontSize(18);
		red = new Text("Red", 239, 205, canvas);
		red.setColor(Color.RED);
		red.setFontSize(18);
		white = new Text("White", 384, 205, canvas);
		white.setColor(Color.LIGHT_GRAY);
		white.setFontSize(17);
		answer = new Text("", 242, 50, canvas);
		blackLabel = new Text("Black Number Right: 0", 40, 260, canvas);
		redLabel = new Text("Red Number Right: 0", 200, 260, canvas);
		whiteLabel = new Text("White Number Right: 0", 360, 260, canvas);
		blackLabelNo = new Text("Black Number Wrong: 0", 40, 280, canvas);
		redLabelNo = new Text("Red Number Wrong: 0", 200, 280, canvas);
		whiteLabelNo = new Text("White Number Wrong: 0", 360, 280, canvas);
		
		
		
		//Draw the clothes
		clothWhite = new FramedRect(clothPoint, 30, 30, canvas);
		clothRed = new FilledRect(clothPoint, 30, 30, canvas);
		clothRed.setColor(Color.RED);
		clothBlack = new FilledRect(clothPoint, 30, 30, canvas);
		
		
		//Give the cloth a color
		clothColor = generator.nextValue();
		if (clothColor == 1){
			clothBlack.show();
			clothRed.hide();
			clothWhite.hide();
		}
		else if(clothColor == 2){
			clothBlack.hide();
			clothRed.show();
			clothWhite.hide();
		}
		else if(clothColor == 3){
			clothBlack.hide();
			clothRed.hide();
			clothWhite.show();
		}
		
		
	}
	
	

	//Determine how far way the mouse is pressed from clothPoint
	public void onMousePress(Location point){
		offx = point.getX() - clothPoint.getX();
		offy = point.getY() - clothPoint.getY();
	}
	
	//Move the cloth as it is dragged
	public void onMouseDrag(Location point){
		if (clothColor == 1 & clothBlack.contains(point)){
			
			clothBlack.moveTo(point.getX() - offx, point.getY() - offy);	
		}
		else if (clothColor == 2 & clothRed.contains(point)){
			
			clothRed.moveTo(point.getX() - offx, point.getY() - offy);	
		}
		else if (clothColor == 3 & clothWhite.contains(point)){
			
			clothWhite.moveTo(point.getX() - offx, point.getY() - offy);	
		}
	}
	
	
	public void onMouseRelease(Location point){
		
		//Reset the color location and size of the answer
				answer.setColor(Color.BLACK);
				answer.setFontSize(12);
				answer.moveTo(242, 50);
				
		if (clothColor == 1 & bask1.contains(point)){
			answer.setText("YES!");
			
			//Move the cloth back to original position
			clothBlack.moveTo(clothPoint);
			
			//Give the clothing a new color
			clothColor = generator.nextValue();
			if (clothColor == 1){
				clothBlack.show();
				clothRed.hide();
				clothWhite.hide();
			}
			else if(clothColor == 2){
				clothBlack.hide();
				clothRed.show();
				clothWhite.hide();
			}
			else if(clothColor == 3){
				clothBlack.hide();
				clothRed.hide();
				clothWhite.show();
			}
			
			//Add to the time
			blacktime ++;
			
			//Change text
			blackLabel.setText("Black Number Right: " + blacktime);
			
				
		}
		else if (clothColor == 2 & bask2.contains(point)){
			answer.setText("YES!");
			//Move the cloth back to original position
			clothRed.moveTo(clothPoint);
			
			//Give the clothing a new color
			clothColor = generator.nextValue();
			if (clothColor == 1){
				clothBlack.show();
				clothRed.hide();
				clothWhite.hide();
			}
			else if(clothColor == 2){
				clothBlack.hide();
				clothRed.show();
				clothWhite.hide();
			}
			else if(clothColor == 3){
				clothBlack.hide();
				clothRed.hide();
				clothWhite.show();
			}
			
			//Add to the time
			redtime ++;
			
			//Change text
			redLabel.setText("Red Number Right: " + redtime);
			
				
		}
		else if (clothColor == 3 & bask3.contains(point)){
			answer.setText("YES!");
			
			//Move the cloth back to original position
			clothWhite.moveTo(clothPoint);
			
			//Give the clothing a new color
			clothColor = generator.nextValue();
			if (clothColor == 1){
				clothBlack.show();
				clothRed.hide();
				clothWhite.hide();
			}
			else if(clothColor == 2){
				clothBlack.hide();
				clothRed.show();
				clothWhite.hide();
			}
			else if(clothColor == 3){
				clothBlack.hide();
				clothRed.hide();
				clothWhite.show();
			}
			
			//Add to the time
			whitetime ++;
			
			//Change text
			whiteLabel.setText("White Number Right: " + whitetime);
				
		}
		else{
			//Move the clothes back to original position
			clothBlack.moveTo(clothPoint);
			clothRed.moveTo(clothPoint);
			clothWhite.moveTo(clothPoint);
			
			//Tell the user NO!
			answer.setText("NO!");
			answer.setColor(Color.red);
			answer.setFontSize(30);
			answer.move(-8, -5);
			
			//Add to the No time
			if (clothColor == 1){
				blacktimeNo++;
				blackLabelNo.setText("Black Number Wrong: " + blacktimeNo);
				
			}
			else if(clothColor == 2){
				redtimeNo++;
				redLabelNo.setText("Red Number Wrong: " + redtimeNo);
			}
			else if(clothColor == 3){
				whitetimeNo++;
				whiteLabelNo.setText("White Number Wrong: " + whitetimeNo);
			}
		}
	}
	
	
	//This code was used to click on the baskets instead of drag the clothing
	
	/**** public void onMouseClick(Location point){
		
		//Reset the color location and size of the answer
		answer.setColor(Color.BLACK);
		answer.setFontSize(12);
		answer.moveTo(242, 50);
		
		if (bask1.contains(point) & clothColor == 1){
			answer.setText("YES!");
			
			//Give the clothing a new color
			clothColor = generator.nextValue();
			if (clothColor == 1){
				clothBlack.show();
				clothRed.hide();
				clothWhite.hide();
			}
			else if(clothColor == 2){
				clothBlack.hide();
				clothRed.show();
				clothWhite.hide();
			}
			else if(clothColor == 3){
				clothBlack.hide();
				clothRed.hide();
				clothWhite.show();
			}
			
			//Add to the time
			blacktime ++;
			//Change text
			blackLabel.setText("Black Number: " + blacktime);
			
		}
		else if (bask2.contains(point) & clothColor == 2){
			answer.setText("YES!");
			
			//Give the clothing a new color
			clothColor = generator.nextValue();
			if (clothColor == 1){
				clothBlack.show();
				clothRed.hide();
				clothWhite.hide();
			}
			else if(clothColor == 2){
				clothBlack.hide();
				clothRed.show();
				clothWhite.hide();
			}
			else if(clothColor == 3){
				clothBlack.hide();
				clothRed.hide();
				clothWhite.show();
			}
			
			//Add to the time
			redtime ++;
			
			//Change text
			redLabel.setText("Red Number: " + redtime);
			
		}
		else if (bask3.contains(point) & clothColor == 3){
			answer.setText("YES!");
			
			//Give the clothing a new color
			clothColor = generator.nextValue();
			if (clothColor == 1){
				clothBlack.show();
				clothRed.hide();
				clothWhite.hide();
			}
			else if(clothColor == 2){
				clothBlack.hide();
				clothRed.show();
				clothWhite.hide();
			}
			else if(clothColor == 3){
				clothBlack.hide();
				clothRed.hide();
				clothWhite.show();
			}
			
			//Add to the time
			whitetime ++;
			
			//Change text
			whiteLabel.setText("White Number: " + whitetime);
		}
		else{
			//Add to the No time
			if (clothColor == 1){
				blacktimeNo++;
				blackLabelNo.setText("Black Number: " + blacktime);
			}
			else if(clothColor == 2){
				redtimeNo++;
				redLabelNo.setText("Red Number: " + redtimeNo);
			}
			else if(clothColor == 3){
				whitetimeNo++;
				whiteLabelNo.setText("White Number: " + whitetimeNo);
			}
			//Tell the user NO!
			answer.setText("NO!");
			answer.setColor(Color.red);
			answer.setFontSize(30);
			answer.move(-8, -5);
		}
	} *****/
	
	
	
	
	
	
}
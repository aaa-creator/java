/**
 * In this game, you drag food to feed a cat
 * Author: Daniel Cogan
 */
import java.awt.*;
import objectdraw.*;

public class HungryCat extends WindowController {

    //cat size and location
    private static final int CAT_X = 50;
    private static final int CAT_Y = 50;
    private static final int CAT_WIDTH = 150;
    private static final int CAT_HEIGHT = 150;
    
    //fish size and location
    private static final int FISH_X = 300;
    private static final int FISH_Y = 300;
    private static final int FISH_WIDTH = 100;
    private static final int FISH_HEIGHT = 50;
    
    //milk size and location
    private static final int MILK_X = 150;
    private static final int MILK_Y = 275;
    private static final int MILK_WIDTH = 50;
    private static final int MILK_HEIGHT = 100;
    
    //Images for cat and fish, on-screen text and variable for mouse press location
    Image catPic, fishPic, milkPic;
    VisibleImage cat, fish, milk;
    Text catText, instructions;
    Location lastPoint;
    int foodNum = 0;
    boolean awake = true;
    
    public void begin(){
    	resize(500, 500);
    	
    	//Draw cat
    	catPic = getImage("cat.jpg");
    	cat = new VisibleImage(catPic, CAT_X, CAT_Y, canvas);
    	cat.setWidth(CAT_WIDTH);
    	cat.setHeight(CAT_HEIGHT);
    	
    	//Draw milk
    	milkPic = getImage("milk.jpg");
    	milk = new VisibleImage(milkPic, MILK_X, MILK_Y, canvas);
    	milk.setWidth(MILK_WIDTH);
    	milk.setHeight(MILK_HEIGHT);
    	
    	//Draw fish
    	fishPic = getImage("fish.jpg");
    	fish = new VisibleImage(fishPic, FISH_X, FISH_Y, canvas);
    	fish.setWidth(FISH_WIDTH);
    	fish.setHeight(FISH_HEIGHT);
    	
    	//Draw cat's text
    	catText = new Text("Meow", 200, 70, canvas);
    	
    	//Draw instructions
    	instructions = new Text("Feed the hungry kitty.", 150, 450, canvas);
    	instructions.setFontSize(18);
    }
    
    public void onMousePress(Location point){
    	lastPoint = point;
    	
    	//Wake the cat up when it is poked
    	if (!awake && cat.contains(point)){
    		instructions.move(50, 0);
    		instructions.setText("The Cat Woke Up");
    		instructions.show();
    		catText.setText("Meow");
    		awake = true;
    	}
    }
    
    public void onMouseDrag(Location point){
    	//Code for picking up and dragging the fish
    	if(fish.contains(point)){
    		instructions.hide();
    	fish.move(point.getX() - lastPoint.getX(),
				point.getY() - lastPoint.getY());
		lastPoint = point;
		
		//Code for picking up and dragging the milk
    	}else if(milk.contains(point)){
    		instructions.hide();
    	milk.move(point.getX() - lastPoint.getX(),
				point.getY() - lastPoint.getY());
		lastPoint = point;
		
    	}
    }
    
    public void onMouseRelease(Location point){
    	
    	//Makes the cat purr if the fish/milk is dropped on it
    	//Makes the cat meow more if the fish is dropped elsewhere
    	//Put the cat to sleep if it has more than five pieces of food
    	if (( milk.contains(point) || fish.contains(point)) && cat.contains(point) && awake){
    		foodNum++;
    		if(foodNum >= 5){
    			catText.setText("ZZZZZZZZZZZZ");
    			fish.moveTo(FISH_X, FISH_Y);
    			milk.moveTo(MILK_X, MILK_Y);
    			instructions.move(-90, 0);
    			instructions.show();
    			instructions.setText("The Cat Fell Asleep.  Poke Him To Wake Him Up");
    			awake = false;
    			foodNum = 0;
    		}else{
    		catText.setText("Purrrrrr");
    		fish.moveTo(FISH_X, FISH_Y);
    		milk.moveTo(MILK_X, MILK_Y);
    		}
    	} else if (( milk.contains(point) || fish.contains(point)) && !cat.contains(point) && awake){
    		catText.setText("MEOW MEOW");
    	}
    }
}

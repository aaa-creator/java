import objectdraw.*;

import java.awt.*;

public class Card{

	private static double CARD_WIDTH = 100;				//The width of the card
	private static double CARD_HEIGHT = CARD_WIDTH*1.4;	//The height of the cards
	
	int face;				//variables for the face value and position in the deck
	Stack stack;
	VisibleImage cardBack, cardFront;
	
	/**
	 * This is the Card class constructor. 
	 */
	
	public Card(Image cardBackPic, double x, double y, int value, Stack stack1, DrawingCanvas canvas) {
		cardBack = new VisibleImage(cardBackPic, x, y, canvas);
		cardBack.setHeight(CARD_HEIGHT);
		cardBack.setWidth(CARD_WIDTH);
		stack = stack1;
		face = value;
	}
	
	
	/**
	 * These are the mutator methods.  They change a card in some way.
	 */
	
	public void move(double x, double y) {
		cardBack.move(x, y);
	}

	public void moveTo(double x, double y) {
		this.move(x - cardBack.getX(), y - cardBack.getY());
	}

	public void moveTo(Location pt) {
		this.move(pt.getX() - cardBack.getX(), pt.getY() - cardBack.getY());
	}

	
	public void sendToBack(){
		cardBack.sendToBack();
	}
	
	
	/**
	 * These are the return methods.  They tell something about the card.
	 */
	
	public boolean contains(Location pt){	//Tells if the card contains a specified location
		return cardBack.contains(pt);
	}
	
	public int getFace(){					//Gives the face value of the card
		return face;
	}
	
	public double getHeight(){				//Gives the height of the card
		return CARD_HEIGHT;
	}
	
	public double getWidth(){				//Gives the width of the card
		return CARD_WIDTH;
	}
	
	public double getX(){					//Gives the X position of the card
		return cardBack.getX();
	}
	
	public double getY(){					//Gives the Y position of the card
		return cardBack.getY();
	}
	
	
	/**
	 * Here is the method for turning over a card
	 */

	
}

import objectdraw.*;
import java.awt.*;
import java.util.ArrayList;
public class CardGame extends WindowController{
	
	Image cardBackPic, tablePic;
	VisibleImage cardBack, table;
	Stack deck1;
	double deck1X = 100;
	double deck1Y = 100;
	Location mousePt;


	
	
	public void begin(){
		resize(1280, 700);			
		
		//Drawing the background table
		tablePic = getImage("table.png");
		table = new VisibleImage(tablePic, 0, 0, canvas);
		table.setWidth(1280);
		table.setHeight(700);
		
		//Constructing a new deck of cards
		cardBackPic = getImage("card_back_red.png");
		deck1 = new Stack(new Card(cardBackPic, deck1X, deck1Y+1, 0, deck1, canvas), deck1X, deck1Y, canvas);
		int i=1;
		/*while (i<52){

			deck1.add(new Card(cardBackPic, deck1X, deck1Y+(i/5)+1, i, deck1, canvas));
			i++;
		}*/
		//deck.shuffle();
	}
	
	public void onMousePress(Location pt){
		mousePt = pt;
	}
	
	public void onMouseClick(Location pt){

	}
	
	public void onMouseDrag(Location pt){
		
			mousePt = pt;
		}
	
	public void onMouseRelease(){
		
	}
}

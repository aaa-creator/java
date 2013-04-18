import objectdraw.*;

import java.awt.*;
import java.util.Arrays;

public class Deck {
	
	Stack deck;		//This array organizes the cards by value
	
	public Deck(Image cardBackPic, double x, double y, Stack name, DrawingCanvas canvas){
		
		/**
		 * These two arrays and the while statement have an end result of the 
		 * array "values" that is shuffled with numbers 0-51.  The value of a 
		 * card determines the face value (ace, king, queen, 10, 9 etc.)
		 * cards are ordered ace, king queen etc and spade, diamond, club,
		 * heart from 0-51 
		 *
		
		RandomIntGenerator valueGen = new RandomIntGenerator(0, 51);
		int[] tempValues = {0, 1, 2, 3, 4, 5, 6 ,7, 8, 9, 10, 11, 
				12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 
				24, 25, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35, 
				36, 37, 38, 39, 40, 41, 42, 43, 44, 45, 46, 47, 
				48, 49, 50, 51};
		
		int[] values = {0, 1, 2, 3, 4, 5, 6 ,7, 8, 9, 10, 11, 
				12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 
				24, 25, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35, 
				36, 37, 38, 39, 40, 41, 42, 43, 44, 45, 46, 47, 
				48, 49, 50, 51};

		int num=0;
		int from = valueGen.nextValue();
		
		while (num < 52) {

			if (tempValues[from] != 60) {
				System.arraycopy(tempValues, from, values, num, 1);
				tempValues[from] = 60;
				from = valueGen.nextValue();
				num++;
			} else {
				from = valueGen.nextValue();
			}
		}

		
		/**
		 * This is where the cards are constructed
		 */
		name = new Stack(new Card(cardBackPic, x, y+1, 0, name, canvas), x, y, canvas);
		int i=1;
		while (i<52){

			name.add(new Card(cardBackPic, x, y+(i/5)+1, i, name, canvas));
			i++;
		}
		//deck.shuffle();

	}
}

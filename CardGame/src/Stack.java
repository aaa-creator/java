import objectdraw.*;

import java.awt.*;
import java.util.ArrayList;
public class Stack {

	Location location;
	ArrayList<Card> stack;
	
	public Stack(Card bottom, double x, double y, DrawingCanvas canvas){
		stack.add(bottom);
		location = new Location(x, y);
	}

	public void add(Card top){
		stack.add(top);
	}
	
	public void setBottom(Card bottom){
		stack.add(0, bottom);
	}
	
	public void setTop(Card top){
		stack.add(top);
	}
	
	public void move(Location pt){
		int i = 0;
		while (i<stack.size()){
		stack.get(i).move(pt.getX(), pt.getY());
		i++;
		}
	}
	
	public void shuffle(){	
		RandomIntGenerator valueGen = new RandomIntGenerator(0, stack.size()-1);
		
		int[] tempValues = new int[stack.size()];
		int[] values =  new int[stack.size()];

		int i=0;
		int from = valueGen.nextValue();
		
		while (i < stack.size()) {

			if (tempValues[from] != 60) {
				System.arraycopy(tempValues, from, values, i, 1);
				tempValues[from] = 60;
				from = valueGen.nextValue();
				i++;
			} else {
				from = valueGen.nextValue();
			}
		}
		i=0;
		
		while (i < stack.size()){
			stack.add(stack.get(values[i]));
			stack.remove(stack.size());
			i++;
		}	
	}
}

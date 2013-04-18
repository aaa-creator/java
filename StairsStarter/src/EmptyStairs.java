import objectdraw.Location;

/**
 * the "null" stairs that cap off the recursion. 
 * <br>
 * Because they are empty, they do not have to be rendered or moved.
 */
public class EmptyStairs implements StairsInterface {

	public void move(double dx, double dy) {
	}


	public boolean contains(Location point) {
		return false;
	}



}

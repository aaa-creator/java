import objectdraw.*;

/**
 * Interface for stairs
 * <br>
 * Defines the method(s) to be implemented by all Stairs
 */
public interface StairsInterface {

	public void move(double dx, double dy);

	public boolean contains(Location point);
}

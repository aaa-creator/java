import objectdraw.*;

import java.awt.*;

/**
 * this class represents a bar magnet
 * <P>
 * This is only a skeletal implementation.  Please complete the
 * implementation of these methods and add the others described
 * in the lab handout.
 */
public class Magnet {
	private static final double MAGNET_WIDTH = 150;	// width of a bar magnet
	private static final double MAGNET_HEIGHT = 50;	// height of a bar magnet

	/** the framed rectangle that represents the perimeter of the magnet */
	private FramedRect box;	
	private Pole nPole, sPole;
	
	public Magnet(double x, double y, DrawingCanvas canvas){
		box = new FramedRect(x, y, MAGNET_WIDTH, MAGNET_HEIGHT, canvas);
		nPole = new Pole ( this, x + 15, y + MAGNET_HEIGHT/2, "N", canvas );
		sPole = new Pole ( this, x + (MAGNET_WIDTH-15), y + MAGNET_HEIGHT/2, "S", canvas );
	}


	/**
	 * get the location of (upper-left corner) of magnet rectangle
	 * 
	 * @return Location of the upper-left corner of the magnet rectangle
	 */
	public Location getLocation() {
		return box.getLocation();
	}

	/**
	 * relative motion of a magnet (and the poles it contains)
	 *
	 * @param xoff    horizontal distance to move
	 * @param yoff    vertical distance to move
	 */
	public void move(double xoff, double yoff) {
		box.move(xoff, yoff);
		nPole.move(xoff, yoff);
		sPole.move(xoff, yoff);
		
	}

	/**
	 * move a magnet (and the poles it contains) to an absolute position
	 *
	 * @param point   Location to which (upper left corner) should be moved
	 */
	public void moveTo( Location point) {
		this.move(point.getX() - box.getX(), point.getY() - box.getY());

	}
	
	public void moveTo( double x, double y) {
		this.move(x - box.getX(), y - box.getY());

	}

	/**
	 * determine whether or not a given Location is within the magnet.
	 * (based on the location and dimensions of the magnet rectangle)
	 *
	 * @return true if Location is within the magnet rectangle
	 */
	public boolean contains( Location point ) {

		return box.contains(point);
	}

	/**
	 * get the width of the magnet rectangle
	 * 
	 * @return width of the magnet
	 */
	public double getWidth() {
		return MAGNET_WIDTH;
	}

	/**
	 * get the height of the magnet rectangle
	 * 
	 * @return height of the magnet
	 */
	public double getHeight() {
		return MAGNET_HEIGHT;
	}
	/**
	 * Flips the poles of the two magnets
	 */
	public void flip(){
		double north_x = nPole.getX();
		double north_y = nPole.getY();
		nPole.move(sPole.getX() - north_x, sPole.getY() - north_y);
		sPole.move(north_x - sPole.getX(), north_y - sPole.getY());
	}
	
	/**
	 * Initiates the interactions of the two magnets
	 * @param otherMag
	 */
	public void interact(Magnet otherMag){
		nPole.attract(otherMag.sPole);
		nPole.repel(otherMag.nPole);
		sPole.attract(otherMag.nPole);
		sPole.repel(otherMag.sPole);
	}

}

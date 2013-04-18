import objectdraw.*;


/**
 * A class of objects used to represent poles of bar magnets
 * and simulating the repulsion of like poles, and the attraction
 * of unlike poles
 *
 * @author T. Murtagh + K. Bruce    9/16/99
 */
public class Pole {

	/** Arbitrarily chosen range within which poles interact	*/
	private static final double ATTRACTION_RANGE = 90.0;

	/** Font size for the pole labels	*/
	private static final int FONT_SIZE = 36;

	/** location of the center of this pole	*/
	private Location location; 
	/** type of this pole ("N" or "S")	*/
	private Text label; 
	/** The magnet which contains this pole	*/
	private Magnet mom; 


	/**
	 * Create a new pole
	 *
	 * @param parent  magnet that contains this pole
	 * @param x       x coordinate of center of pole
	 * @param y       y coordinate of center of pole
	 * @param name	  label for this pole ("N" or "S")
	 * @param canvas  the canvas on which poles should be drawn
	 */
	public Pole ( Magnet parent, double x, double y, String name, DrawingCanvas canvas ) {
		mom = parent;
		this.location = new Location(x,y);
		label = new Text( name, location, canvas );
		label.setFontSize(FONT_SIZE);

		// specified location was not for upper-left corner, but center
		// NOTE that from now on, all motion will be relative, 
		//      so that the offset need never be considered again.
		label.move( -label.getWidth()/2, -label.getHeight()/2);
	}

	/**
	 * return reference to the magnet that contains this pole
	 * 
	 * @return	reference to the Magnet that contains this pole
	 */
	public Magnet getParent() {
		return mom;
	}

	/**
	 * get the X coordinate of pole's center
	 * 
	 * @return	x coordinate of pole's center
	 */
	public double getX() {
		return location.getX();
	}

	/**
	 * get Y coordinate of pole's center
	 * 
	 * @return	y coordinate of pole's center
	 */
	public double getY() {
		return location.getY();
	}

	/**
	 * get X and Y coordinates of pole's center
	 * 
	 * @return	coordinates of pole's center
	 */
	public Location getLocation() {
		return location;
	}

	/**
	 * move pole, by some distance, from its current location
	 * 
	 * @param xoff	horizontal distance to move
	 * @param yoff	vertical distance to move
	 */
	public void move( double xoff, double yoff){
		location.translate(xoff,yoff);
		label.move(xoff,yoff);
	}
	


	/**
	 * Try to attract a pole of opposite polarity
	 * (where polarity is determined by the "N" or "S" label).
	 * <P>
	 * If the other pole is within "AttractionRange" of this pole, 
	 * move the other magnet so that it is next to this pole's 
	 * containing magnet
	 * 
	 * @param opposite   the (pole with which we want to try interacting)
	 */
	public void attract( Pole opposite ) {
		double xoff, yoff;

		xoff = opposite.getX() - location.getX();
		yoff = opposite.getY() - location.getY();

		// Check to see if the poles are close enough
		if ( location.distanceTo(opposite.getLocation()) < ATTRACTION_RANGE ) {

			// make the two magnets overlap
			opposite.getParent().moveTo( mom.getLocation());

			if ( Math.abs(xoff) >= Math.abs(yoff) ) {
				// move magnets so that they meet end-to-end
				if ( xoff < 0 ) {
					opposite.getParent().move(-mom.getWidth(), 0);
				} else {
					opposite.getParent().move(mom.getWidth(), 0);
				}
			} else {
				// Move magnets so they will be above one another with attracting pole's
				// aligned vertically

				opposite.getParent().move( location.getX()-opposite.getLocation().getX(),0);
				if ( yoff < 0 ) {
					opposite.getParent().move(0,-mom.getHeight());
				} else {
					opposite.getParent().move(0,mom.getHeight());
				}
			}
		}
	}

	/**
	 * Try to repel a pole of similar polarity
	 * (where polarity is determined by the "N" or "S" label).
	 * <P>
	 * If the other pole is within "AttractionRange" of this pole, 
	 * move the other magnet so that the other pole is "AttractionRange" 
	 * away from this one.
	 * 
	 * @param similar  the pole with which we want to try interacting
	 */
	public void repel( Pole similar ) {

		double xoff, yoff;
		double distance = location.distanceTo( similar.getLocation());

		xoff = similar.getX() - location.getX();
		yoff = similar.getY() - location.getY();

		// check to see if the poles are close enough
		if ( distance < ATTRACTION_RANGE - 1) {
			// Push other magnet away in the direction of the line between
			// the two poles.
			xoff = (ATTRACTION_RANGE-distance) * xoff /distance;
			yoff = (ATTRACTION_RANGE-distance) * yoff /distance;

			similar.getParent().move( xoff,yoff );
		}
	}
}

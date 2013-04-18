import objectdraw.Location;


public class EmptyScribbleCollection implements ScribbleCollectionIfc {


	public ScribbleIfc getScribble(Location point) {
		return new EmptyScribble();
	}

	public ScribbleIfc getFirst() {
		return null;
	}

	public ScribbleCollectionIfc getRest() {
		return null;
	}

}

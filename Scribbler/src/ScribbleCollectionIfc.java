import objectdraw.Location;


public interface ScribbleCollectionIfc {

	ScribbleIfc getScribble(Location point);
	ScribbleIfc getFirst();
	ScribbleCollectionIfc getRest();

}

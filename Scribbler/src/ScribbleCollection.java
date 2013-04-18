import objectdraw.*;

public class ScribbleCollection implements ScribbleCollectionIfc {

	private ScribbleIfc first;
	private ScribbleCollectionIfc rest;
	
	public ScribbleCollection(ScribbleIfc theFirst, ScribbleCollectionIfc theRest){
		first = theFirst;
		rest = theRest;
	}
	
	public ScribbleIfc getScribble(Location point){
		if (first.contains(point)){
			return first;
		}else {
			return rest.getScribble(point);
		}
	}
	
	public ScribbleIfc getFirst(){
		return first;
	}

	public ScribbleCollectionIfc getRest() {
		return rest;
	}
}

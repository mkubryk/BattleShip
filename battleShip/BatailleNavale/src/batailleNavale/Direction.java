package batailleNavale;

public enum Direction {
	HORIZONTAL ("H"),
	VERTICAL ("V");
	
	public final String label;
	
	private Direction (String label) {
		this.label = label;		
	}
	
	public static Direction parseDirection(String input) {
	    for (Direction dir : Direction.values()) {
	        if (dir.label.equals(input)) {
	            return dir;
	        }
	    }
	    return null; 
	}

}

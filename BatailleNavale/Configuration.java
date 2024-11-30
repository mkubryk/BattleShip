package BatailleNavale;

public abstract class Configuration {

    private static final String[][] ships
            = {
                {" 1 ", " Porte - avions ", " 5 "},
                {" 2 ", " Cuirasse ", " 4 ",},
                {" 3 ", " Croiseur ", " 3 "},
                {" 4 ", " Croiseur ", " 3 "},
                {" 5 ", " Torpilleur ", " 2 "}
            };
    private int gridSize = 10;


    public Configuration() {
    }

    public Configuration(int gridSize) throws OutOfBoundException {
        if (gridSize > this.gridSize) throw OutOfBoundException ("ERROR : Out of bounds");
        this.gridSize = gridSize;
    }

    public int getGridSize() {
        return this.gridSize;
    }

    public void setGridSize(int gridSize) {
        this.gridSize = gridSize;
    }

    public int getNbShips() {
        return this.ships[0].length;
    }

    public Configuration gridSize(int gridSize) {
        setGridSize(gridSize);
        return this;
    }

    public String toString() {
        return "{" +
            " gridSize='" + getGridSize() + "'" +
            "}";
    }
    
            

}

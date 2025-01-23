package batailleNavale;

public abstract class Configuration {

    private static final String[][] ships
            = {
                {" 1 ", " Porte - avions ", " 5 "},
                {" 2 ", " Cuirasse ", " 4 ",},
                {" 3 ", " Croiseur ", " 3 "},
                {" 4 ", " Croiseur ", " 3 "},
                {" 5 ", " Torpilleur ", " 2 "}
            };
    private static final int gridSize = 10;
    // indice allant de A à J pour les colonnes et de 0 à 9 pour les lignes

    public static String [][] getShipsConfig() {
    	return ships;
    }

    public static  int getGridSize() {
        return gridSize;
    }

    public static int getNbShips() {
        return ships.length;
    }
    
    /*
    public static String getDesc (int id) {
        int i = 0;
        String desc = "Bateau non trouvé ! ";
        while (i < ships.length) {
            if (ships[i][0].equals(id)) {
                desc = " id : "+ id+" Bateau " + ships[i][1] + " de taille " + ships[i][2];
                break;
            }
            i++;
        }
        return desc;
        
    } */

    public static String [] getShip (int id) {
        int i = 0;
        String [] ship = null;
        while (i < ships.length) {
            if (ships[i][0].equals(id)) {
                ship = ships[i];
                break;
            }
            i++;
        }
        return ship;
    }
            

}

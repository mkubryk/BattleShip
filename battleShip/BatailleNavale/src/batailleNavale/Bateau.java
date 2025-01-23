package batailleNavale;
import java.util.List;

public class Bateau {
	
	//Attributs
    private int id;  // Identifiant unique du bateau
    private String name;  // Nom du bateau
    private List<Case> cases;  // Liste des cases occupées par le bateau

    // Constructeur
    public Bateau(int id, String name, List<Case> cases) {
        this.id = id;
        this.name = name;
        this.cases = cases;
    }

    // Getters
    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public List<Case> getCases() {
        return cases;
    }
    
    public Case getCase (int index) {
    	return cases.get(index);
    }
    
    public Case getCase (int x, int y) {
    	return (Case) cases.stream().filter(cell -> cell.getX()==x && cell.getY()==y).findFirst().orElse(null);
    }

    /**
     * Retourne la taille du bateau (nombre de cases occupées).
     * @return La taille du bateau.
     */
    public int getSize() {
        return cases.size();
    }

    /**
     * Vérifie si le bateau est coulé (toutes ses cases sont touchées).
     * @return true si le bateau est coulé, false sinon.
     */
    public boolean isSunk() {
        int i = 0;
        boolean sunk = true;

        while (i < cases.size() && sunk) {
            if (!cases.get(i).isTouched()) {
                sunk = false; 
            }
            i++;
        }

        return sunk;
    }

    /**
     * Retourne une représentation textuelle du bateau.
     * @return Une chaîne contenant le nom, l'identifiant et l'état du bateau.
     */
    @Override
    public String toString() {
        return name + " (ID: " + id + ") - Taille: " + getSize() + " - " +
               (isSunk() ? "Coulé" : "A flot");
    }
}

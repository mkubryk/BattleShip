package BatailleNavale;
import java.util.List;

public class Bateau {

    // Attributs (en anglais)
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
    public boolean isSank() {
        for (Case c : cases) {
            if (!c.isTouched()) {
                return false;
            }
        }
        return true;
    }

    /**
     * Retourne une représentation textuelle du bateau.
     * @return Une chaîne contenant le nom, l'identifiant et l'état du bateau.
     */
    @Override
    public String toString() {
        return name + " (ID: " + id + ") - Taille: " + getSize() + " - " +
               (isSank() ? "Coulé" : "A flot");
    }
}

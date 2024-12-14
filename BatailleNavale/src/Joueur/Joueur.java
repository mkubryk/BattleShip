package BatailleNavale.Joueur;
import BatailleNavale.Plateau;
import BatailleNavale.Case;
import java.io.BufferedReader;
import java.io.InputStreamReader;

public abstract class Joueur {

    // Attributs (en anglais)
    protected String name;  // Nom du joueur
    protected Plateau plateau;  // Plateau du joueur
    protected int totalHits;  // Compteur des frappes totales
    protected int successfulHits;  // Compteur des frappes réussies
    protected int sunkShips;  // Nombre de bateaux coulés
    protected Case lastHit;  // Dernière case frappée
    protected BufferedReader input;  // Pour gérer les saisies clavier

    // Constructeur
    public Joueur() {
        this.plateau = new Plateau();
        this.totalHits = 0;
        this.successfulHits = 0;
        this.sunkShips = 0;
        this.lastHit = new Case(-1, -1);  // Par défaut, aucune case n'a été frappée
        this.input = new BufferedReader(new InputStreamReader(System.in));
    }

    // Getters
    public String getName() {
        return name;
    }

    public Plateau getPlateau() {
        return plateau;
    }

    public int getTotalHits() {
        return totalHits;
    }

    public int getSuccessfulHits() {
        return successfulHits;
    }

    public int getSunkShips() {
        return sunkShips;
    }

    public Case getLastHit() {
        return lastHit;
    }

    // Setters
    public void setLastHit(Case lastHit) {
        this.lastHit = lastHit;
    }

    // Méthodes supplémentaires

    /**
     * Incrémente le compteur des frappes totales.
     */
    public void incrementTotalHits() {
        this.totalHits++;
    }

    /**
     * Incrémente le compteur des frappes réussies.
     */
    public void incrementSuccessfulHits() {
        this.successfulHits++;
    }

    /**
     * Incrémente le nombre de bateaux coulés.
     */
    public void incrementSunkShips() {
        this.sunkShips++;
    }

    /**
     * Retourne les statistiques du joueur.
     * @return Une chaîne de caractères décrivant les statistiques.
     */
    public String getStatistics() {
        return "Nom : " + name + "\n" +
               "Frappes totales : " + totalHits + "\n" +
               "Frappes réussies : " + successfulHits + "\n" +
               "Bateaux coulés : " + sunkShips;
    }

    // Méthodes abstraites
    public abstract void placeShips();  // Méthode pour placer les bateaux
    public abstract void takeShot(Joueur adversaire);  // Méthode pour tirer sur un adversaire
    public abstract void initializeName();  // Méthode pour initialiser le nom du joueur
}

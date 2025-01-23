package batailleNavale;

public class Case {

    // Attributs
    private int x;  // Coordonnée ligne
    private int y;  // Coordonnée colonne
    private int idShip;  // Identifiant du bateau sur cette case (0 si aucun bateau)
    private boolean touched;  // Indique si la case a été touchée

    // Constructeur
    public Case(int x, int y) {
        this.x = x;
        this.y = y;
        this.idShip = 0;  // Par défaut, aucune case n'a de bateau
        this.touched = false;  // Par défaut, la case n'a pas été touchée
    }
    
    // Constructeur
    public Case(int x, int y, int idShip) {
        this.x = x;
        this.y = y;
        this.idShip = idShip;  
        this.touched = false;  // Par défaut, la case n'a pas été touchée
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getIdShip() {
        return idShip;
    }

    public boolean isTouched() {
        return touched;
    }

    // Setters
    public void setIdShip(int idShip) {
        this.idShip = idShip;
    }

    public void setTouched(boolean touched) {
        this.touched = touched;
    }

    /**
     * Vérifie si une case est vide (aucun bateau dessus).
     * @return true si la case est vide, false sinon.
     */
    public boolean isEmpty() {
        return idShip == 0;
    }

    @Override
    public String toString() {
        return touched ? " X " : (isEmpty() ? " . " : " "+String.valueOf(idShip)+" ");
    }
}

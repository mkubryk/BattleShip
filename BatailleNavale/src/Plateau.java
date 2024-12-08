package BatailleNavale;

import java.util.ArrayList;
import java.util.List;

public class Plateau {

    // Attributs (en anglais)
    private Case[][] grid;  // Grille de cases (tableau 2D)
    private List<Bateau> ships;  // Liste des bateaux placés sur le plateau

    // Constructeur
    public Plateau() {
        this.grid = new Case[Configuration.getGridSize()][Configuration.getGridSize()];
        this.ships = new ArrayList<>();
        initializeGrid();
    }

    /**
     * Initialise la grille avec des cases vides.
     */
    private void initializeGrid() {
        for (int x = 0; x < Configuration.getGridSize(); x++) {
            for (int y = 0; y < Configuration.getGridSize(); y++) {
                grid[x][y] = new Case(x, y);
            }
        }
    }

    /**
     * Retourne une case donnée à partir de ses coordonnées.
     * @param x Coordonnée ligne.
     * @param y Coordonnée colonne.
     * @return La case correspondante.
     * @throws IndexOutOfBoundsException si les coordonnées sont hors limites.
     */
    public Case getCase(int x, int y) throws IndexOutOfBoundsException {
        if (x < 0 || x >= Configuration.getGridSize() || y < 0 || y >= Configuration.getGridSize()) {
            throw new IndexOutOfBoundsException("Coordonnées hors limites.");
        }
        return grid[x][y];
    }

    /**
     * Ajoute un bateau au plateau.
     * @param ship Le bateau à ajouter.
     * @throws IllegalArgumentException si le bateau ne peut pas être placé.
     */
    public void addShip(Bateau ship) throws IllegalArgumentException {
        for (Case c : ship.getCases()) {
            if (!isValidPosition(c.getX(), c.getY()) || !getCase(c.getX(), c.getY()).isEmpty()) {
                throw new IllegalArgumentException("Impossible de placer le bateau : position invalide ou déjà occupée.");
            }
        }
        ships.add(ship);
        for (Case c : ship.getCases()) {
            getCase(c.getX(), c.getY()).setIdShip(ship.getId());
        }
    }

    /**
     * Vérifie si une position est valide sur le plateau.
     * @param x Coordonnée ligne.
     * @param y Coordonnée colonne.
     * @return true si la position est valide, false sinon.
     */
    public boolean isValidPosition(int x, int y) {
        return x >= 0 && x < Configuration.getGridSize() && y >= 0 && y < Configuration.getGridSize();
    }

    /**
     * Vérifie si un tableau de cases ne dépasse pas les limites du plateau.
     * @param shipCases Les cases occupées par un bateau.
     * @return true si le tableau de cases est valide, false sinon.
     */
    public boolean isWithinBounds(List<Case> shipCases) {
        for (Case c : shipCases) {
            if (!isValidPosition(c.getX(), c.getY())) {
                return false;
            }
        }
        return true;
    }

    /**
     * Vérifie si un tableau de cases n'est pas adjacent à d'autres bateaux.
     * @param shipCases Les cases occupées par un bateau.
     * @return true si aucune case n'est adjacente à un autre bateau, false sinon.
     */
    public boolean isNotAdjacent(List<Case> shipCases) {
        for (Case c : shipCases) {
            for (int dx = -1; dx <= 1; dx++) {
                for (int dy = -1; dy <= 1; dy++) {
                    int nx = c.getX() + dx;
                    int ny = c.getY() + dy;
                    if (isValidPosition(nx, ny) && !getCase(nx, ny).isEmpty()) {
                        return false;
                    }
                }
            }
        }
        return true;
    }

    /**
     * Affiche le plateau avec l'état actuel des cases.
     */
    public void display() {
        System.out.print("  ");
        for (int y = 0; y < Configuration.getGridSize(); y++) {
            System.out.print((char) ('A' + y) + " ");
        }
        System.out.println();
        for (int x = 0; x < Configuration.getGridSize(); x++) {
            System.out.print(x + " ");
            for (int y = 0; y < Configuration.getGridSize(); y++) {
                System.out.print(grid[x][y].toString() + " ");
            }
            System.out.println();
        }
    }
}

package batailleNavale;

import java.util.ArrayList;
import java.util.List;

import joueur.Joueur;
import support.TraitementCoordonnee;

public class Plateau {

	//Attributs
	private Case[][] grid; // Grille de cases 10x10 , colonnes : [A,J], lignes : [0,1]
	private List<Bateau> ships; // Liste des bateaux placés sur le plateau
	private final int nbShips = 5; // Nombre de bateau à placer
	
	private final String GREEN = "\u001B[32m";
	private final String RED = "\u001B[35m";
	private final String RESET = "\u001B[0m";
	

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
	 * 
	 * @param x Coordonnée ligne.
	 * @param y Coordonnée colonne.
	 * @return case de coordonnée (x,y)
	 * @throws IndexOutOfBoundsException si les coordonnées n'existent pas.
	 */
	public Case getCell(int x, int y) throws IndexOutOfBoundsException {
		if (x < 0 || x >= Configuration.getGridSize() || y < 0 || y >= Configuration.getGridSize()) {
			throw new IndexOutOfBoundsException("Coordonnées hors limites.");
		}
		return grid[x][y];
	}

	public void setCell(Case cell) {
		if (cell.getX() < 0 || cell.getX() >= Configuration.getGridSize() || cell.getY() < 0
				|| cell.getY() >= Configuration.getGridSize()) {
			throw new IndexOutOfBoundsException("Coordonnées hors limites.");
		}
		grid[cell.getX()][cell.getY()] = cell;
	}

	/**
	 * Ajoute un bateau au plateau.
	 * 
	 * @param ship Le bateau à ajouter.
	 * @throws IllegalArgumentException si le bateau ne peut pas être placé.
	 */
	public void addShip(Bateau ship) throws IllegalArgumentException {
		if (ships.size() < nbShips) {
			for (Case c : ship.getCases()) {
				if (!isValidPosition(c.getX(), c.getY()) || !getCell(c.getX(), c.getY()).isEmpty()) {
					throw new IllegalArgumentException(
							"Impossible de placer le bateau : position invalide ou déjà occupée.");
				}
			}
			ships.add(ship);
			for (Case c : ship.getCases()) {
				getCell(c.getX(), c.getY()).setIdShip(ship.getId());
			}
		} else {
			System.out.println("Vous avez déjà placé vos " + nbShips + " bateaux ! ");
		}
	}

	/**
	 * Vérifie si une position est valide sur le plateau.
	 * 
	 * @param x Coordonnée ligne.
	 * @param y Coordonnée colonne.
	 * @return true si la position est valide, false sinon.
	 */
	public boolean isValidPosition(int x, int y) {
		return x >= 0 && x < Configuration.getGridSize() && y >= 0 && y < Configuration.getGridSize();
	}

	/**
	 * Vérifie si un tableau de cases ne dépasse pas les limites du plateau.
	 * 
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
	 * 
	 * @param shipCases Les cases occupées par un bateau.
	 * @return true si aucune case n'est adjacente à un autre bateau, false sinon.
	 */
	public boolean isNotAdjacent(List<Case> shipCases) {
		for (Case c : shipCases) {
			for (int dx = -1; dx <= 1; dx++) {
				for (int dy = -1; dy <= 1; dy++) {
					int nx = c.getX() + dx;
					int ny = c.getY() + dy;
					if (isValidPosition(nx, ny) && !getCell(nx, ny).isEmpty()) {
						return false;
					}
				}
			}
		}
		return true;
	}

	/**
	 * Affiche le plateau du joueur avec l'état actuel des cases.
	 */
	public void displayBoard() {
		System.out.print("  ");
		for (int y = 0; y < Configuration.getGridSize(); y++) {
			System.out.print(TraitementCoordonnee.coordonneeNombreVersLettre(y) + " ");
		}
		System.out.println();
		for (int x = 0; x < Configuration.getGridSize(); x++) {
			System.out.print(x + " ");
			for (int y = 0; y < Configuration.getGridSize(); y++) {
				System.out.print(this.grid[x][y].toString() + " ");
			}
			System.out.println();
		}
	}

	/**
	 * Affiche le plateau avec l'état actuel des cases.
	 */
	public void showPlayBoard(Joueur joueur, Joueur adversaire) {
		//je ne vois pas comment utiliser le paramètre joueur mise à part en utilisant sa dernière case touchée 
		System.out.print("  ");
		for (int y = 0; y < Configuration.getGridSize(); y++) {
			System.out.print(TraitementCoordonnee.coordonneeNombreVersLettre(y) + " ");
		}
		System.out.println();
		for (int x = 0; x < Configuration.getGridSize(); x++) {
			System.out.print(x + " ");
			for (int y = 0; y < Configuration.getGridSize(); y++) {
				if (adversaire.getPlateau().getCell(x, y).isTouched() && adversaire.getPlateau().getCell(x, y).getIdShip() > 0) {
					System.out.print(GREEN+" X  "+RESET);
				}
				else if (adversaire.getPlateau().getCell(x, y).isTouched() && adversaire.getPlateau().getCell(x, y).getIdShip() <= 0) {
					System.out.print(RED+" 0  "+RESET);
				}
				else {
					System.out.print(" .  ");
				}
			}
			System.out.println();
		}

	}

	public Bateau getShipById(int id) {
		return (Bateau) ships.stream().filter(ship -> ship.getId() == id).findFirst().orElse(null);

	}

	public List<Bateau> getShips() {
		return ships;
	}

}

package joueur;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import batailleNavale.Bateau;
import batailleNavale.Case;
import batailleNavale.Configuration;

public class JoueurBot extends Joueur {

	//Attributs
	private Random random; // Générateur de nombres aléatoires pour les actions du bot

	// Constructeur
	public JoueurBot() {
		super(); // Appelle le constructeur de la classe Joueur
		this.random = new Random();
		initializeName(); // Initialise le nom du bot
	}

	/**
	 * Initialise le nom du bot.
	 */
	@Override
	public void initializeName() {
		this.name = "Bot simple" + random.nextInt(1000);
	}

	/**
	 * Place les bateaux automatiquement sur le plateau.
	 */
	@Override
	public void placeShips() {
		for (int i = 0; i < Configuration.getNbShips(); i++) {
			String[] shipConfig = Configuration.getShipsConfig()[i];
			int idShip = Integer.parseInt(shipConfig[0].trim());
			String nameShip = shipConfig[1].trim();
			int shipSize = Integer.parseInt(shipConfig[2].trim());

			boolean isPlaced = false;
			//tant que le bateau n'est pas placé on retente avec d'autres coordonnées
			while (!isPlaced) {
				int x = random.nextInt(Configuration.getGridSize());
				int y = random.nextInt(Configuration.getGridSize());

				List<Case> cellList = null;
				try {
					cellList = generateShipCoordinates(x, y, shipSize, idShip);
					plateau.addShip(new Bateau(idShip, nameShip, cellList));
					isPlaced = true;
				} catch (IllegalArgumentException e) {
					System.out.println("Erreur dans le placement du bateau " + idShip + " : " + e.getMessage());
					System.out.println("Retry : Tentative de placement du bateau n° " + idShip + " en cours ... ");
				}
			}
		}
	}

	/**
	 * Gère le tir automatique du bot sur l'adversaire.
	 * 
	 * @param adversaire Le joueur adverse.
	 */
	@Override
	public void shoot(Joueur adversaire) {
		boolean validShot = false;

		while (!validShot) {
			int x = random.nextInt(Configuration.getGridSize());
			int y = random.nextInt(Configuration.getGridSize());

			if (adversaire.getPlateau().isValidPosition(x, y) && !adversaire.getPlateau().getCell(x, y).isTouched()) {
				Case target = adversaire.getPlateau().getCell(x, y);
				setLastHit(target);
				incrementTotalHits();
				target.setTouched(true);

				if (target.getIdShip() != 0) {
					incrementSuccessfulHits();
					Bateau hitShip = adversaire.getPlateau().getShipById(target.getIdShip());
					System.out.println(" Touché !");
					if (hitShip == null) {
						System.out.println("Erreur: Aucun bateau trouvé avec l'ID " + target.getIdShip());
					} else {
						if (hitShip.getCase(x, y) != null) {
							hitShip.getCase(x, y).setTouched(true);
						}
						System.out.println("Bateau touché: " + hitShip.getName());
						if (hitShip.isSunk()) {
							incrementSunkShips();
							System.out.println("Bravo " + name + " a coulé un " + hitShip.getName() + " adverse !");
						}
					}
				} else {
					System.out.println("Raté !");
				}

				validShot = true;
			}
		}
	}

	/**
	 * Génère les coordonnées d'un bateau donné à partir d'une case (x,y) de départ, si les coodonnées ne fonctionnent pas dans une direction 
	 * on tente l'autre sinon on tente dans le sens opposé de la même manière
	 * @param startX, startY, shipSize, idShip
	 * @return liste de case correspondant aux coordonnées du bateau
	 */
	private List<Case> generateShipCoordinates(int startX, int startY, int shipSize, int idShip)
			throws IllegalArgumentException {
		List<Case> cellList = new ArrayList<>();
		boolean isHorizontal = Math.random() < 0.5; // Détermine aléatoirement la direction
		boolean direction = false;

		// Essayer la direction initiale
		if (canPlaceShip(startX, startY, shipSize, idShip, isHorizontal, direction)) {
			cellList = createShipCoordinates(startX, startY, shipSize, idShip, isHorizontal, direction);
		}
		// Si la direction initiale échoue, essayer l'autre direction
		else if (canPlaceShip(startX, startY, shipSize, idShip, !isHorizontal, direction)) {
			cellList = createShipCoordinates(startX, startY, shipSize, idShip, !isHorizontal, direction);
		}
		// Si aucune direction n'est faisable dans ce sens
		else {
			// Essayer la direction opposée
			if (canPlaceShip(startX, startY, shipSize, idShip, isHorizontal, !direction)) {
				cellList = createShipCoordinates(startX, startY, shipSize, idShip, isHorizontal, !direction);
			}
			// Si la direction initiale échoue, essayer l'autre direction
			else if (canPlaceShip(startX, startY, shipSize, idShip, !isHorizontal, !direction)) {
				cellList = createShipCoordinates(startX, startY, shipSize, idShip, !isHorizontal, !direction);
			} else {
				throw new IllegalArgumentException("Impossible de placer le bateau à la position donnée.");
			}
		}

		return cellList;
	}

	/**
	 * Vérifie si un bateau peut être placé à partir des coordonnées de départ dans
	 * une direction donnée.
	 * @param startX, startY, shipSize, idShip, isHorizontal
	 * @return true si on peut placer le bateau dans cette direction
	 */
	private boolean canPlaceShip(int startX, int startY, int shipSize, int idShip, boolean isHorizontal,
			boolean otherWay) {
		for (int i = 0; i < shipSize; i++) {
			int x = isHorizontal ? startX : (otherWay ? startX - i : startX + i);
			int y = isHorizontal ? (otherWay ? startY - i : startY + i) : startY;

			// Vérifier si la position est valide et vide
			if (!plateau.isValidPosition(x, y) || !plateau.getCell(x, y).isEmpty()) {
				return false;
			}

			// Vérifier si la case est adjacente à un autre bateau
			if (!plateau.isNotAdjacent(List.of(new Case(x, y, idShip)))) {
				return false;
			}
		}
		return true;
	}

	/**
	 * Crée les coordonnées du bateau dans une direction donnée.
	 * @param startX, startY, shipSize, idShip, isHorizontal, otherWay
	 * @return liste de cases représentant les coordonnées 
	 */
	private List<Case> createShipCoordinates(int startX, int startY, int shipSize, int idShip, boolean isHorizontal,
			boolean otherWay) {
		List<Case> cellList = new ArrayList<>();
		for (int i = 0; i < shipSize; i++) {
			int x = isHorizontal ? startX : (otherWay ? startX - i : startX + i); // si la direction est horizontale le
																					// x ne change pas sinon il augmente
																					// car on ajoute une case à la ligne
																					// suivante
			int y = isHorizontal ? (otherWay ? startY - i : startY + i) : startY; // si la direction est horizontale on
																					// augmente y car on ajoute une case
																					// à la colonne suivante sinon y
																					// reste le même car on sera à la
																					// verticale
			cellList.add(new Case(x, y, idShip));
		}
		return cellList;
	}

}

package batailleNavale;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.Random;

import joueur.*;

public class Partie {

	// Attributs
	private Joueur joueur1; // Premier joueur
	private Joueur joueur2; // Deuxième joueur
	private int currentPlayerIndex; // Indique le joueur en cours (1 ou 2)
	private String winner; // Nom du joueur gagnant
	private BufferedReader input; // Buffer pour la saisie clavier

	// Constructeur
	public Partie() {
		this.input = new BufferedReader(new InputStreamReader(System.in));
		this.winner = null;
	}

	/**
	 * Initialise la partie : crée les joueurs et place leurs bateaux.
	 */
	public void initialize() {
		System.out.println("=== Initialisation de la partie ===");

		// Création des joueurs
		joueur1 = createPlayer(1);
		if (joueur1 instanceof JoueurHumain)
			joueur1.initializeName();
		joueur2 = createPlayer(2);
		if (joueur2 instanceof JoueurHumain)
			joueur2.initializeName();

		// Placement des bateaux
		System.out.println("\nPlacement des bateaux pour " + joueur1.getName() + " :");
		joueur1.placeShips();
		joueur1.getPlateau().displayBoard();
		System.out.println("\nPlacement des bateaux pour " + joueur2.getName() + " :");
		joueur2.placeShips();
		joueur2.getPlateau().displayBoard();

		// Tirage au sort pour déterminer le premier joueur
		Random random = new Random();
		currentPlayerIndex = random.nextInt(2) + 1;
		System.out.println(
				"\nLe joueur " + (currentPlayerIndex == 1 ? joueur1.getName() : joueur2.getName()) + " commence !");
	}

	/**
	 * Boucle principale du jeu.
	 */
	public void play() {
		//cleanConsole();
		System.out.println("\n=== Début de la partie ===");

		while (!isGameOver()) {
			Joueur currentPlayer = (currentPlayerIndex == 1) ? joueur1 : joueur2;
			Joueur opponent = (currentPlayerIndex == 1) ? joueur2 : joueur1;
			System.out.println("\nC'est au tour de " + currentPlayer.getName() + " !");
			currentPlayer.shoot(opponent);

			// Affichage de l'état du plateau
			System.out.println("\nPlateau après le tir :");
			currentPlayer.getPlateau().showPlayBoard(currentPlayer, opponent);

			// Changer de joueur
			currentPlayerIndex = (currentPlayerIndex == 1) ? 2 : 1;
			//cleanConsole();
		
		}

		endGame();
	}

	/**
	 * Vérifie si un joueur a gagné.
	 * 
	 * @return true si un joueur a gagné, false sinon.
	 */
	public boolean isGameOver() {
		if (joueur1.getSunkShips() == Configuration.getNbShips()) {
			winner = joueur1.getName();
			return true;
		} else if (joueur2.getSunkShips() == Configuration.getNbShips()) {
			winner = joueur2.getName();
			return true;
		}
		return false;
	}

	/**
	 * Affiche les résultats de la partie et termine le jeu.
	 */
	public void endGame() {
		//cleanConsole();
		System.out.println("\n=== Fin de la partie ===");
		System.out.println("Le gagnant est : " + winner + " !");
		System.out.println("\nStatistiques des joueurs :");
		System.out.println(joueur1.getStatistics());
		System.out.println(joueur2.getStatistics());
	}

	private void cleanConsole() {
		try {
			new ProcessBuilder(" bash ", "-c", " clear ").inheritIO().start().waitFor();
		} catch (final Exception e) {
			System.out.println(" Erreur : " + e);
		}
	}

	/**
	 * Crée un joueur (humain ou bot) en fonction de l'entrée utilisateur.
	 * 
	 * @param playerNumber Numéro du joueur (1 ou 2).
	 * @return Une instance de Joueur (humain ou bot).
	 */
	public Joueur createPlayer(int playerNumber) {
		try {
			System.out.println("Quel type de joueur pour le Joueur " + playerNumber + " ?");
			System.out.println("1. Humain");
			System.out.println("2. Bot Basique");

			int choice = Integer.parseInt(input.readLine().trim());
			switch (choice) {
			case 1:
				return new JoueurHumain();
			case 2:
				return new JoueurBot();
			default:
				System.out.println("Choix invalide, joueur par défaut : Humain.");
				return new JoueurHumain();
			}
		} catch (IOException | NumberFormatException e) {
			System.out.println("Erreur de saisie. Par défaut, un joueur humain sera créé.");
			return new JoueurHumain();
		}
	}

	public Joueur getJoueur1() {
		return joueur1;
	}

	public void setJoueur1(Joueur joueur1) {
		this.joueur1 = joueur1;
	}

	public Joueur getJoueur2() {
		return joueur2;
	}

	public void setJoueur2(Joueur joueur2) {
		this.joueur2 = joueur2;
	}

}

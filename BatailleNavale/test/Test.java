package BatailleNavale.test;
import BatailleNavale.Configuration;
import BatailleNavale.Menu;
import BatailleNavale.Case;
import BatailleNavale.Bateau;
import BatailleNavale.Plateau;
import Joueur.Joueur;
import Joueur.JoueurHumain;

import java.lang.module.Configuration;
import java.util.ArrayList;
import java.util.List;

public class Test {

    public static void main(String[] args) {
        // Test de la classe Configuration
        System.out.println("=== Tests Configuration ===");
        System.out.println("Nombre de bateaux : " + Configuration.getNbShips());
        System.out.println("Taille de la grille : " + Configuration.getGridSize());
        System.out.println("Description du premier bateau : ");
        System.out.println(Configuration.getDesc(1));

        // Test de la classe Menu
        System.out.println("\n=== Tests Menu ===");
        Menu menu = new Menu();
        menu.lancer();

        // Test de la classe Case
        System.out.println("\n=== Tests Case ===");
        Case case1 = new Case(0, 0);
        System.out.println("Coordonnées de la case : (" + case1.getX() + ", " + case1.getY() + ")");
        System.out.println("La case est vide ? " + case1.isEmpty());
        case1.setIdShip(1);
        System.out.println("Identifiant du bateau sur la case : " + case1.getIdShip());
        System.out.println("La case est vide ? " + case1.isEmpty());
        case1.setTouched(true);
        System.out.println("La case est touchée ? " + case1.isTouched());
        System.out.println("Représentation de la case : " + case1.toString());

         System.out.println("\n=== Tests Bateau ===");
        List<Case> cases = new ArrayList<>();
        cases.add(new Case(0, 0));
        cases.add(new Case(0, 1));
        Bateau bateau = new Bateau(1, "Torpilleur", cases);

        System.out.println("Nom du bateau : " + bateau.getName());
        System.out.println("Taille du bateau : " + bateau.getSize());
        System.out.println("Le bateau est coulé ? " + bateau.isSank());

        // Toucher une case
        cases.get(0).setTouched(true);
        System.out.println("Le bateau est coulé après un tir ? " + bateau.isSank());

        // Toucher toutes les cases
        cases.get(1).setTouched(true);
        System.out.println("Le bateau est coulé après tous les tirs ? " + bateau.isSank());

        System.out.println("Représentation du bateau : " + bateau.toString());


        // Test de la classe Plateau
        System.out.println("\n=== Tests Plateau ===");
        Plateau Plateau = new Plateau();
        Plateau.display();

        List<Case> shipCases = new ArrayList<>();
        shipCases.add(new Case(0, 0));
        shipCases.add(new Case(0, 1));
        Bateau bateau = new Bateau(1, "Torpilleur", shipCases);

        try {
            Plateau.addShip(bateau);
            System.out.println("Bateau ajouté avec succès !");
        } catch (IllegalArgumentException e) {
            System.out.println("Erreur : " + e.getMessage());
        }

        Plateau.display();

        // Test de la classe Joueur
        System.out.println("\n=== Tests Joueur ===");

        // Classe anonyme pour tester Joueur (comme elle est abstraite)
        Joueur Joueur = new Joueur() {
            @Override
            public void placeShips() {
                System.out.println(name + " place ses bateaux.");
            }

            @Override
            public void takeShot(Joueur adversaire) {
                System.out.println(name + " tire sur " + adversaire.getName() + " !");
            }

            @Override
            public void initializeName() {
                this.name = "Joueur Test";
            }
        };

        Joueur.initializeName();
        System.out.println("Nom du joueur : " + Joueur.getName());
        Joueur.incrementTotalHits();
        Joueur.incrementSuccessfulHits();
        Joueur.incrementSunkShips();
        System.out.println(Joueur.getStatistics());


        // Test de la classe JoueurHumain
        System.out.println("\n=== Tests JoueurHumain ===");
        // 1. Test de l'initialisation du nom
        System.out.println("\n--- Test de l'initialisation du nom ---");
        JoueurHumain joueurHumain = new JoueurHumain();
        System.out.println("Nom du joueur humain : " + joueurHumain.getName());

        // 2. Test du placement des bateaux
        System.out.println("\n--- Test du placement des bateaux ---");
        Plateau plateau = joueurHumain.getPlateau();

        // Ajout manuel de bateaux pour tester la logique de placement
        List<Case> shipCases = new ArrayList<>();
        shipCases.add(new Case(0, 0));
        shipCases.add(new Case(0, 1));
        Bateau bateau = new Bateau(1, "Torpilleur", shipCases);
        try {
            plateau.addShip(bateau);
            System.out.println("Bateau placé avec succès !");
        } catch (IllegalArgumentException e) {
            System.out.println("Erreur lors du placement : " + e.getMessage());
        }
        plateau.display();

        // 3. Test du tir sur un adversaire
        System.out.println("\n--- Test du tir sur un adversaire ---");
        JoueurHumain adversaire = new JoueurHumain();
        adversaire.initializeName();
        System.out.println("Nom de l'adversaire : " + adversaire.getName());

        // Ajout d'un bateau pour l'adversaire
        List<Case> adversaryShipCases = new ArrayList<>();
        adversaryShipCases.add(new Case(1, 1));
        adversaryShipCases.add(new Case(1, 2));
        Bateau adversaryBateau = new Bateau(2, "Croiseur", adversaryShipCases);
        try {
            adversaire.getPlateau().addShip(adversaryBateau);
            System.out.println("Bateau de l'adversaire placé avec succès !");
        } catch (IllegalArgumentException e) {
            System.out.println("Erreur lors du placement pour l'adversaire : " + e.getMessage());
        }
        adversaire.getPlateau().display();

        // Tir sur l'adversaire
        joueurHumain.takeShot(adversaire);

        // Afficher les résultats après le tir
        System.out.println("\nPlateau de l'adversaire après le tir :");
        adversaire.getPlateau().display();

        System.out.println("\nStatistiques du joueur humain :");
        System.out.println(joueurHumain.getStatistics());



    }

    
}

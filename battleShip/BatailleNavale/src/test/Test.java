package test;

import java.util.ArrayList;
import java.util.List;

import batailleNavale.Bateau;
import batailleNavale.Case;
import batailleNavale.Configuration;
import batailleNavale.Menu;
import batailleNavale.Plateau;
import batailleNavale.Partie;
import joueur.Joueur;
import joueur.JoueurBot;
import joueur.JoueurHumain;

public class Test {

    public static void main(String[] args) {
    	     	
        // Test de la classe Configuration
        System.out.println("=== Tests Configuration ===");
        System.out.println("Nombre de bateaux : " + Configuration.getNbShips());
        System.out.println("Taille de la grille : " + Configuration.getGridSize());
       
        // Test de la classe Menu
        System.out.println("\n=== Tests Menu ===");
        Menu menu = new Menu();
        //menu.lancer();

        // Test de la classe Case
        System.out.println("\n=== Tests Case ===");
        Case caseTest = new Case(0, 0);
        System.out.println("Coordonnées de la case : (" + caseTest.getX() + ", " + caseTest.getY() + ")");
        System.out.println("La case est vide ? " + caseTest.isEmpty());
        caseTest.setIdShip(1);
        System.out.println("Identifiant du bateau sur la case : " + caseTest.getIdShip());
        System.out.println("La case est vide ? " + caseTest.isEmpty());
        caseTest.setTouched(true);
        System.out.println("La case est touchée ? " + caseTest.isTouched());
        System.out.println("Représentation de la case : " + caseTest.toString());

        // Test de la classe Bateau
        System.out.println("\n=== Tests Bateau ===");
        List<Case> casesBateau = new ArrayList<>();
        casesBateau.add(new Case(0, 0));
        casesBateau.add(new Case(0, 1));
        Bateau bateauTest = new Bateau(1, "Torpilleur", casesBateau);

        System.out.println("Nom du bateau : " + bateauTest.getName());
        System.out.println("Taille du bateau : " + bateauTest.getSize());
        System.out.println("Le bateau est coulé ? " + bateauTest.isSunk());

        // Toucher une case
        casesBateau.get(0).setTouched(true);
        System.out.println("Le bateau est coulé après un tir ? " + bateauTest.isSunk());

        // Toucher toutes les cases
        casesBateau.get(1).setTouched(true);
        System.out.println("Le bateau est coulé après tous les tirs ? " + bateauTest.isSunk());
        System.out.println("Représentation du bateau : " + bateauTest.toString());

        // Test de la classe Plateau
        System.out.println("\n=== Tests Plateau ===");
        Plateau plateauTest = new Plateau();
        plateauTest.displayBoard();

        List<Case> casesPlateau = new ArrayList<>();
        casesPlateau.add(new Case(3, 1));
        casesPlateau.add(new Case(3, 2));
        Bateau bateauPlateau = new Bateau(1, "Torpilleur", casesPlateau);

        try {
            plateauTest.addShip(bateauPlateau);
            System.out.println("Bateau ajouté avec succès !");
        } catch (IllegalArgumentException e) {
            System.out.println("Erreur : " + e.getMessage());
        }

        plateauTest.displayBoard();

        // Test de la classe Joueur
        System.out.println("\n=== Tests Joueur ===");
        Joueur joueurTest = new Joueur() {
            @Override
            public void placeShips() {
                System.out.println(name + " place ses bateaux.");
            }

            @Override
            public void shoot(Joueur adversaire) {
                System.out.println(name + " tire sur " + adversaire.getName() + " !");
            }

            @Override
            public void initializeName() {
                this.name = "Joueur Test";
            }
        };

        joueurTest.initializeName();
        System.out.println("Nom du joueur : " + joueurTest.getName());
        joueurTest.incrementTotalHits();
        joueurTest.incrementSuccessfulHits();
        joueurTest.incrementSunkShips();
        System.out.println(joueurTest.getStatistics());

        // Test de la classe JoueurHumain
        System.out.println("\n=== Tests JoueurHumain ===");
        JoueurHumain joueurHumain = new JoueurHumain();
        joueurHumain.initializeName();
        System.out.println("Nom du joueur humain : " + joueurHumain.getName());

        Plateau plateauHumain = joueurHumain.getPlateau();
        List<Case> casesHumain = new ArrayList<>();
        casesHumain.add(new Case(0, 0));
        casesHumain.add(new Case(0, 1));
        Bateau bateauHumain = new Bateau(1, "Torpilleur", casesHumain);

        try {
            plateauHumain.addShip(bateauHumain);
            System.out.println("Bateau placé avec succès !");
        } catch (IllegalArgumentException e) {
            System.out.println("Erreur lors du placement : " + e.getMessage());
        }
        plateauHumain.displayBoard();

        JoueurHumain adversaireHumain = new JoueurHumain();
        adversaireHumain.initializeName();
        System.out.println("Nom de l'adversaire : " + adversaireHumain.getName());

        List<Case> casesAdversaire = new ArrayList<>();
        casesAdversaire.add(new Case(1, 1));
        casesAdversaire.add(new Case(1, 2));
        Bateau bateauAdversaire = new Bateau(2, "Croiseur", casesAdversaire);

        try {
            adversaireHumain.getPlateau().addShip(bateauAdversaire);
            System.out.println("Bateau de l'adversaire placé avec succès !");
        } catch (IllegalArgumentException e) {
            System.out.println("Erreur lors du placement pour l'adversaire : " + e.getMessage());
        }

        adversaireHumain.getPlateau().displayBoard();
        joueurHumain.shoot(adversaireHumain);

        System.out.println("\nPlateau de l'adversaire après le tir :");
        adversaireHumain.getPlateau().displayBoard();

        System.out.println("\nStatistiques du joueur humain :");
        System.out.println(joueurHumain.getStatistics());

        // Test de la classe JoueurBot
        System.out.println("\n=== Tests JoueurBot ===");
        JoueurBot bot = new JoueurBot();
        System.out.println("Nom du bot : " + bot.getName());

        System.out.println("\n--- Placement automatique des bateaux ---");
        bot.placeShips();
        bot.getPlateau().displayBoard();

        System.out.println("\n--- Tir automatique sur un adversaire ---");
        JoueurHumain adversaireBot = new JoueurHumain();
        adversaireBot.initializeName();
        adversaireBot.placeShips();

        System.out.println("Plateau de l'adversaire avant les tirs :");
        adversaireBot.getPlateau().displayBoard();

        bot.shoot(adversaireBot);

        System.out.println("\nPlateau de l'adversaire après les tirs :");
        adversaireBot.getPlateau().displayBoard();

        System.out.println("\nStatistiques du bot :");
        System.out.println(bot.getStatistics());
        
        // Test de la classe Partie
        System.out.println("\n=== Tests Partie ===");
        Partie partie = new Partie();
        partie.initialize();
        
        partie.play();
        
        partie.endGame();
        
        Joueur jh = partie.createPlayer(1);
        if (jh instanceof JoueurHumain) {
        	System.out.println("création d'un joueur humain validé ! "); 
        }
        else {
        	System.out.println("création d'un joueur humain Raté ! "); 
        }
        
        Joueur jb = partie.createPlayer(2);
        if (jb instanceof JoueurBot) {
        	System.out.println("création d'un joueur bot validé ! "); 
        }
        else {
        	System.out.println("création d'un joueur bot Raté ! "); 
        }
        
        for (int i = 0 ; i< 5; i++ )  partie.getJoueur1().incrementSunkShips();
        
        if (partie.isGameOver()) {
        	System.out.println(" Fin de jeu réussie !");
        }
        else {
        	System.out.println(" Fin de jeu raté ! ");
        }
        
    }
}

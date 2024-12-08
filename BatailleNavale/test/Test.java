package BatailleNavale.test;
import BatailleNavale.Configuration;
import BatailleNavale.Menu;
import BatailleNavale.Case;
import BatailleNavale.Bateau;
import BatailleNavale.Plateau;
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
    }
}

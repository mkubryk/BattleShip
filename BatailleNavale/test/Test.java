package BatailleNavale.test;
import BatailleNavale.Configuration;
import BatailleNavale.Menu;
import BatailleNavale.Case;
import java.lang.module.Configuration;

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
    }
}

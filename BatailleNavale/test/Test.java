package BatailleNavale.test;
import BatailleNavale.Configuration;
import BatailleNavale.Menu;

public class Test {

    public static void main(String[] args) {
        // Test de la classe Configuration
        System.out.println("=== Tests Configuration ===");
        System.out.println("Nombre de bateaux : " + Configuration.getNombreBateaux());
        System.out.println("Taille de la grille : " + Configuration.getTailleGrille());
        System.out.println("Description du premier bateau : ");
        String[] bateau = Configuration.getBateau(0);
        for (String info : bateau) {
            System.out.println(info);
        }

        // Test de la classe Menu
        System.out.println("\n=== Tests Menu ===");
        Menu menu = new Menu();
        menu.lancer();
    }
}

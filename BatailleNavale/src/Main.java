package BatailleNavale;

public class Main {

    public static void main(String[] args) {
        Menu menu = new Menu();

        while (true) {
            System.out.println("\n=== Menu du jeu ===");
            menu.afficherMenu();
            menu.lancer();

            System.out.println("\nLancement d'une nouvelle partie...");
            Partie partie = new Partie();
            partie.initialize();
            partie.play();

            System.out.println("\nVoulez-vous rejouer ? (y/n)");
            if (!menu.replay()) {
                System.out.println("Merci d'avoir joué ! À bientôt.");
                break;
            }
        }
    }
}

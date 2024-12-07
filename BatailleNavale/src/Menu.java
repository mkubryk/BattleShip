package BatailleNavale;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.regex.Pattern;

public class Menu {

    private BufferedReader in;

    // Constructeur
    public Menu() {
        this.in = new BufferedReader(new InputStreamReader(System.in));
    }

    /**
     * Affiche les options du menu principal.
     */
    public void afficherMenu() {
        System.out.println("=== Menu Principal ===");
        System.out.println("1. Jouer");
        System.out.println("2. Afficher les règles du jeu");
        System.out.println("3. Quitter");
    }

    /**
     * Lance le menu et récupère le choix de l'utilisateur.
     */
    public void lancer() {
        String input = "";
        do {
            afficherMenu();
            System.out.print("Saisissez votre choix : [1, 2 ou 3] ");
            try {
                input = in.readLine();
            } catch (IOException e) {
                System.out.println("Une erreur est survenue : " + e.getMessage());
            }
        } while (!Pattern.matches("[123]", input));

        int choix = Integer.parseInt(input);
        switch (choix) {
            case 1:
                jouer();
                break;
            case 2:
                afficherRegles();
                break;
            case 3:
                quitter();
                break;
        }
    }

    /**
     * Démarre une nouvelle partie.
     */
    private void jouer() {
        System.out.println("Lancement du jeu...");
        // Implémentation à compléter
    }

    /**
     * Affiche les règles du jeu.
     */
    private void afficherRegles() {
        System.out.println("=== Règles du jeu ===");
        System.out.println("1. Chaque joueur place 5 bateaux de tailles différentes.");
        System.out.println("2. Les joueurs tirent à tour de rôle sur la grille de l'adversaire.");
        System.out.println("3. Le but est de couler tous les bateaux adverses.");
        System.out.println("4. Le jeu se termine lorsqu'un joueur n'a plus de bateaux.");
    }

    /**
     * Quitte le programme.
     */
    private void quitter() {
        System.out.println("Merci d'avoir joué ! À bientôt.");
        System.exit(0);
    }
}

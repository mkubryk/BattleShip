package BatailleNavale.Joueur;

import BatailleNavale.Bateau;
import BatailleNavale.Case;
import BatailleNavale.Configuration;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class JoueurBot extends Joueur {

    private Random random;  // Générateur de nombres aléatoires pour les actions du bot

    // Constructeur
    public JoueurBot() {
        super();  // Appelle le constructeur de la classe Joueur
        this.random = new Random();
        initializeName();  // Initialise le nom du bot
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
        System.out.println(name + " place ses bateaux automatiquement.");
        for (int i = 0; i < Configuration.getNbShips(); i++) {
            String[] shipConfig = Configuration.getShip(i + 1);
            String shipName = shipConfig[1].trim();
            int shipSize = Integer.parseInt(shipConfig[2].trim());
            boolean placed = false;

            while (!placed) {
                int startX = random.nextInt(Configuration.getGridSize());
                int startY = random.nextInt(Configuration.getGridSize());
                String direction = random.nextBoolean() ? "H" : "V";
                placed = placeShipOnBoard(shipName, shipSize, startX, startY, direction);
            }
        }
    }

    /**
     * Gère le tir automatique du bot sur l'adversaire.
     * @param adversaire Le joueur adverse.
     */
    @Override
    public void takeShot(Joueur adversaire) {
        boolean validShot = false;

        while (!validShot) {
            int x = random.nextInt(Configuration.getGridSize());
            int y = random.nextInt(Configuration.getGridSize());

            if (adversaire.getPlateau().isValidPosition(x, y) && !adversaire.getPlateau().getCase(x, y).isTouched()) {
                Case target = adversaire.getPlateau().getCase(x, y);
                target.setTouched(true);
                setLastHit(target);
                incrementTotalHits();

                if (target.getidShip() != 0) {
                    incrementSuccessfulHits();
                    Bateau hitShip = adversaire.getPlateau().getShipById(target.getidShip());
                    System.out.println(name + " a touché un bateau !");
                    if (hitShip.isSank()) {
                        incrementSunkShips();
                        System.out.println(name + " a coulé le " + hitShip.getName() + " adverse !");
                    }
                } else {
                    System.out.println(name + " a raté son tir.");
                }

                validShot = true;
            }
        }
    }

    /**
     * Place un bateau sur le plateau selon les coordonnées et la direction données.
     * @param name Nom du bateau.
     * @param size Taille du bateau.
     * @param startX Coordonnée ligne de départ.
     * @param startY Coordonnée colonne de départ.
     * @param direction Direction du bateau (H pour horizontal, V pour vertical).
     * @return true si le placement est réussi, false sinon.
     */
    private boolean placeShipOnBoard(String name, int size, int startX, int startY, String direction) {
        try {
            List<Case> shipCases = new ArrayList<>();
            for (int i = 0; i < size; i++) {
                if (direction.equals("H")) {
                    shipCases.add(plateau.getCase(startX, startY + i));
                } else if (direction.equals("V")) {
                    shipCases.add(plateau.getCase(startX + i, startY));
                }
            }

            if (plateau.isWithinBounds(shipCases) && plateau.isNotAdjacent(shipCases)) {
                Bateau ship = new Bateau(Configuration.getNbShips(), name, shipCases);
                plateau.addShip(ship);
                return true;
            }
        } catch (Exception e) {
            // Placement échoué
        }

        return false;
    }
}

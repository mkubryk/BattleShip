package BatailleNavale.Joueur;

import java.io.IOException;

public class JoueurHumain extends Joueur {

    // Constructeur
    public JoueurHumain() {
        super();  // Appelle le constructeur de la classe mère
        initializeName();  // Initialise le nom du joueur
    }

    /**
     * Initialise le nom du joueur à partir de la saisie clavier.
     */
    @Override
    public void initializeName() {
        System.out.print("Veuillez entrer votre nom : ");
        try {
            this.name = input.readLine();
        } catch (IOException e) {
            System.out.println("Une erreur est survenue lors de la saisie du nom : " + e.getMessage());
            this.name = "Joueur Inconnu";
        }
    }

    /**
     * Place les bateaux du joueur sur son plateau.
     */
    @Override
    public void placeShips() {
        System.out.println(name + ", veuillez placer vos bateaux.");
        for (int i = 0; i < Configuration.getNbShips(); i++) {
            String[] shipConfig = Configuration.getShip(i + 1);
            String shipName = shipConfig[1].trim();
            int shipSize = Integer.parseInt(shipConfig[2].trim());

            System.out.println("Placement du " + shipName + " (taille " + shipSize + ").");
            boolean placed = false;

            while (!placed) {
                try {
                    System.out.print("Entrez les coordonnées de départ (ex: A0) : ");
                    String startCoord = input.readLine().trim().toUpperCase();

                    System.out.print("Entrez la direction (H pour Horizontal, V pour Vertical) : ");
                    String direction = input.readLine().trim().toUpperCase();

                    placed = placeShipOnBoard(shipName, shipSize, startCoord, direction);

                    if (!placed) {
                        System.out.println("Placement invalide. Veuillez réessayer.");
                    }
                } catch (IOException e) {
                    System.out.println("Erreur lors de la saisie : " + e.getMessage());
                }
            }
        }
    }

    /**
     * Gère le tir du joueur humain sur l'adversaire.
     * @param adversaire Le joueur adverse.
     */
    @Override
    public void takeShot(Joueur adversaire) {
        boolean validShot = false;

        while (!validShot) {
            try {
                System.out.print("Entrez les coordonnées de la case à tirer (ex: A0) : ");
                String coord = input.readLine().trim().toUpperCase();

                int x = Integer.parseInt(coord.substring(1));
                int y = coord.charAt(0) - 'A';

                if (adversaire.getPlateau().isValidPosition(x, y) && !adversaire.getPlateau().getCase(x, y).isTouched()) {
                    Case target = adversaire.getPlateau().getCase(x, y);
                    target.setTouched(true);
                    setLastHit(target);
                    incrementTotalHits();

                    if (target.getidShip() != 0) {
                        incrementSuccessfulHits();
                        Bateau hitShip = adversaire.getPlateau().getShipById(target.getidShip());
                        System.out.println("Touché !");
                        if (hitShip.isSank()) {
                            incrementSunkShips();
                            System.out.println("Bravo ! Vous avez coulé le " + hitShip.getName() + " adverse !");
                        }
                    } else {
                        System.out.println("Raté !");
                    }

                    validShot = true;
                } else {
                    System.out.println("Tir invalide. Veuillez réessayer.");
                }
            } catch (IOException | NumberFormatException | StringIndexOutOfBoundsException e) {
                System.out.println("Erreur de saisie. Veuillez réessayer.");
            }
        }
    }

    /**
     * Place un bateau sur le plateau selon les coordonnées et la direction données.
     * @param name Nom du bateau.
     * @param size Taille du bateau.
     * @param startCoord Coordonnées de départ (ex: A0).
     * @param direction Direction du bateau (H pour horizontal, V pour vertical).
     * @return true si le placement est réussi, false sinon.
     */
    private boolean placeShipOnBoard(String name, int size, String startCoord, String direction) {
        try {
            int x = Integer.parseInt(startCoord.substring(1));
            int y = startCoord.charAt(0) - 'A';

            List<Case> shipCases = new ArrayList<>();
            for (int i = 0; i < size; i++) {
                if (direction.equals("H")) {
                    shipCases.add(plateau.getCase(x, y + i));
                } else if (direction.equals("V")) {
                    shipCases.add(plateau.getCase(x + i, y));
                }
            }

            if (plateau.isWithinBounds(shipCases) && plateau.isNotAdjacent(shipCases)) {
                Bateau ship = new Bateau(Configuration.getNbShips(), name, shipCases);
                plateau.addShip(ship);
                return true;
            }
        } catch (Exception e) {
            System.out.println("Erreur lors du placement : " + e.getMessage());
        }

        return false;
    }
}

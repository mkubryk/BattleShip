package support;

public class TraitementCoordonnee {

    /* *
    * Transforme une coordonnee lettre en nombre
    * Attention ici , on considere les grilles de taille 1 0 seulement . A
    changer si jamais la taille de la grille devient potentiellement
    plus grande
     */
    public static String CoordonneeLettreversNombre(String coord) {
        return String.valueOf(new String("ABCDEFGHIJ").indexOf(coord));
    }

    /* *
    * Transforme une coordonnee nombre dans son pendant en lettre
    * Meme remarque que precedemment
     */
    public static String coordonneeNombreVersLettre(int coord) {
        String[] lettres = {" A ", " B ", " C ", " D ", " E ", " F ", " G ", " H ", " I ",
            " J "};
        return lettres[coord];
    }
}

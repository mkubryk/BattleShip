package BatailleNavale;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.regex.Pattern;

public class Menu {

    private BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
    String input = " ";

        
    do {
System.out.print(" Saisissez votre choix : [ 1 , 2 ou 3 ] ");
        try {
            input = in.readLine();
        } catch (java.io.IOException e) {
            System.out.println(" Une erreur est survenue : " + e);
        }
    }

    while (! Pattern.matches (
    " [ 1 2 3 ] " , input ) ) ;
La classe java.util.regex.Pattern est très utile pour vérifier que les saisies respectent une expression
    régulière simple
    . Ce sera souvent le cas pour

    nous(saisie des
         coordonnées, des choix de menus etc)
Vous pouvez, pour rendre vos affichages propres, nettoyer la console avec
try {
new ProcessBuilder(" bash ", " -c ",
                " clear ").inheritIO().start().waitFor();
    }
    catch ( final Exception e

    
        ) {
System.out.println(" Erreur : " + e);
    }
}
}

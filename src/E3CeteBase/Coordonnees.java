package E3CeteBase;

public class Coordonnees {
    private int ligne;
    private int colonne;


/**
 * La classe E3Cete.Coordonnees représente les coordonnées (i,j) d'une E3Cete.Carte sur la E3Cete.Table
 * ou i représenta la ligne et j la colonne
 * Cette classe est utilisée uniquement pour intéragir avec l'utilisateur
 *  */


    /**
     * Pre-requis : x,y>=0
     * Action : Construit des Coordonnées ayant x comme numéro de ligne et y comme numéro de colonne
     */
    public Coordonnees(int x, int y) {
        this.ligne = x-1;
        this.colonne = y-1;
    }

    /**
     * Pre-requis : input est sous la forme suivante : int,int
     * Action : Construit des Coordonnées ayant x comme numéro de ligne et y comme numéro de colonne
     */
    public Coordonnees(String input) {
        if (formatEstValide(input)){
            String[] splited = input.split(",");
            this.ligne = Integer.parseInt(splited[0]);
            this.colonne = Integer.parseInt(splited[1]);
        }

        //splitted est un tableau de String qui contient les sous chaines de caracteres contenues dans input et séparées par ','
    }

    /**
     * Action : Retourne le numéro de la ligne
     */
    public int getLigne() {
        return this.ligne;
    }

    /**
     * Action : Retourne le numéro de la colonne
     */
    public int getColonne() {
        return this.colonne;
    }

    /**
     * Pre-requis : aucun
     * Action : Retourne vrai si la variable input est dans un format valide à savoir int,int
     * Aide : On peut utiliser E3Cete.Ut.estNombre pour vérifier qu'une chaîne de caractères est bien un nombre.
     */
    public static boolean formatEstValide(String input){
        boolean check;
        String[] splited = input.split(",");
        if (splited.length != 2){
            check = false;
        }
        else {
            if (!Ut.estNombre(splited[0])|| !Ut.estNombre(splited[1]) || (0 > Integer.parseInt(splited[0]) ||  0 > Integer.parseInt(splited[1]))){
                check = false;
            }
            else {
                check = true;
            }
        }
        return check;
    }
}

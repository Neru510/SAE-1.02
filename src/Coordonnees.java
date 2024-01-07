public class Coordonnees {
    private int ligne;
    private int colonne;


/**
 * La classe Coordonnees représente les coordonnées (i,j) d'une Carte sur la Table
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
     * Pre-requis : input est sous la forme  suivante : int,int
     * Action : Construit des Coordonnées ayant x comme numéro de ligne et y comme numéro de colonne
     */
    public Coordonnees(String input) {
        String[] splited = input.split(",");
        this.ligne = Integer.parseInt(splited[0]);
        this.colonne = Integer.parseInt(splited[1]);
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
     * Aide : On peut utiliser Ut.estNombre pour vérifier qu'une chaîne de caractères est bien un nombre.
     */
    public static boolean formatEstValide(String input){
        String[] splited = input.split(",");
        if (splited.length!=2){
            return false;
        }
        else return Ut.estNombre(splited[0]) && Ut.estNombre(splited[1]);
    }
}

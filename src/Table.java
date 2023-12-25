import static java.lang.Math.sqrt;

/**
 * La classe Table représente une table de jeu contenant des cartes.
 *
 * La table est représentée graphiquement par une matrice.
 * On peut donc avoir des tables de dimensions 3x3, 3x4, 4x4, 5x5, 10x15...
 * En mémoire, la Table est représentée par un simple tableau (à une dimension)
 * Quand elle est initialisée, la table est vide.
 *
 * Pour désigner une carte sur la table, on utilise des coordonnées (i,j) ou i représenta la ligne et j la colonne.
 * Les lignes et colonnes sont numérotés à partir de 1.
 * Les cartes sont numérotées à partir de 1.
 *
 * Par exemple, sur une table 3x3, la carte en position (1,1) est la premiere carte du tableau, soit celle à l'indice 0.
 * La carte (2,1) => carte numéro 4 stockée à l'indice 3 dans le tableau représentant la table
 * La carte (3,3) => carte numéro 9 stockée à l'indice 8 dans le tableau représentant la table
 */
public class Table {
    private int [] tableau;
    /**
     * Pre-requis : hauteur >=3, largeur >=3
     *
     * Action : Construit une table "vide" avec les dimensions précisées en paramètre.
     *
     * Exemple : hauteur : 3, largeur : 3 => construit une table 3x3 (pouvant donc accueillir 9 cartes).
     */

    public Table(int hauteur, int largeur){
        this.tableau = new int [hauteur * largeur];
    }

    /**
     * Résultat : Le nombre de cartes que la table peut stocker.
     */
    public int getTaille() {
        return tableau.length;
    }

    /**
     * Action : Affichage des cartes de la table mise en parametre sous forme de ligne
     */
    private static String ligneDeXCarte(Carte [] paquet){
        String texte = "";
        int min = 0;
        int y = min;
        char value ;
        for (int x = 0; x <5; x++){
            for (int c = 0; c < paquet.length; c++){
                value = paquet[c].toString().charAt(y);
                y = min;
                while (value != '\n'){
                    value = paquet[c].toString().charAt(y);
                    if (paquet[c].toString().charAt(y) != '\n'){
                        texte = texte + paquet[c].toString().charAt(y);
                    }
                    y= y+1;
                }
                texte = texte+Couleur.getAnnuler() + " ";
            }
            min = min+25;
            texte = texte + '\n';
        }
        return texte;
    }

    /**
     * Pre-requis : la table est pleine
     * Action : Affiche des cartes de la table sous forme de matrice
     * L'affichage des cartes doit respecter le format défini dans la classe Carte (chaque carte doit donc être colorée).
     * On ne donne volontairement pas d'exemple puisque celà depend du choix fait pour votre représentation de Carte
     */
    private int[] getDimension(){
        int taille = this.tableau.length;
        float valueR = (float) sqrt(taille);
        int value1;
        int value2;
        if (valueR%1==0 ){
            value1 = (int) valueR;
            value2 = (int) valueR;
        }else {
            if (taille%10 == 0){
                value1 = 10;
            }else if (taille%5 == 0){
                value1 = 5;
            }else if (taille%3 == 0){
                value1 = 3;
            }else{
                value1 = 2;
            }
            value2 = taille/value1;
        }
        return new int[]{value1, value2};
    }

    public String toString() { // PAS FONCTIONNEL MAIS MAINTENANT SAIT RETROUVER UNE TAILLE D'ORIGINE
        int xMax = getDimension()[0];
        int yMax = getDimension()[1];
        String texte = "";
        for (int x = 0; x < xMax; x ++){
            for (int y = 0; y < yMax; y++){
                texte = texte + (y+1)+" ";
            }
            texte = texte + '\n';
        }
        return texte;
    }

    /**
     * Résullat : Vrai la carte située aux coordonnées précisées en paramètre est une carte possible pour la table.
     */
    public boolean carteExiste(Coordonnees coordonnees) {
        boolean value = false;
        int[] xy = getDimension();
        if (0 < coordonnees.getLigne() && coordonnees.getLigne() <= xy[0] && 0 < coordonnees.getColonne() && coordonnees.getColonne() <= xy[1]){
            value = true;
        }
        return value;
    }

    /**
     * Pre-requis :
     *  Il reste des cartes sur la table.
     *
     * Action : Fait sélectionner au joueur (par saisie de ses coordonnées) une carte valide (existante) de la table.
     * L'algorithme doit faire recommencer la saisie au joueur s'il ne saisit pas une carte valide.
     *
     * Résullat : Le numéro de carte sélectionné.
     *
     */

    public int faireSelectionneUneCarte() {
        throw new RuntimeException("Méthode non implémentée ! Effacez cette ligne et écrivez le code nécessaire");
    }

    /**
     * Pre-requis : 1<=nbCartes <= nombre de Cartes de this
     * Action : Fait sélectionner nbCartes Cartes au joueur  sur la table en le faisant recommencer jusqu'à avoir une sélection valide.
     * Il ne doit pas y avoir de doublons dans les numéros de cartes sélectionnées.
     * Résullat : Un tableau contenant les numéros de cartes sélectionnées.
     */

    public int[] selectionnerCartesJoueur(int nbCartes) {
        throw new RuntimeException("Méthode non implémentée ! Effacez cette ligne et écrivez le code nécessaire");
    }

    /**
     * Action : Affiche les cartes de la table correspondantes aux numéros de cartes contenus dans selection
     * Exemple de format d'affichage : "Sélection : 2-O-H 3-O-H 2-C-H"
     * Les cartes doivent être affichées "colorées"
     */

    public void afficherSelection(int[] selection) {
        throw new RuntimeException("Méthode non implémentée ! Effacez cette ligne et écrivez le code nécessaire");
    }

}

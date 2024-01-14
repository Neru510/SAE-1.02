package E3CeteBase;

import static java.lang.Math.sqrt;

/**
 * La classe E3Cete.Table représente une table de jeu contenant des cartes.
 *
 * La table est représentée graphiquement par une matrice.
 * On peut donc avoir des tables de dimensions 3x3, 3x4, 4x4, 5x5, 10x15...
 * En mémoire, la E3Cete.Table est représentée par un simple tableau (à une dimension)
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
    private Carte [] cartes;
    /**
     * Pre-requis : hautenur >=3, largeur >=3
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
    public static String ligneDeXCarte(Carte [] paquet){
        String texte = "";
        int min = 0;
        int y = min;
        char value ;
        for (int x = 0; x < 5; x++){
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
     * L'affichage des cartes doit respecter le format défini dans la classe E3Cete.Carte (chaque carte doit donc être colorée).
     * On ne donne volontairement pas d'exemple puisque celà depend du choix fait pour votre représentation de E3Cete.Carte
     */
    public int[] getDimension(){
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

    public String toString() {
        String texte = "";
        Carte [] ligne;
        ligne = new Carte[getDimension()[0]];
        int i = 0;
        for (int x = 0; x < tableau.length; x++){
            ligne[i] = cartes[x];
            if ((x+1)%getDimension()[0] == 0){
                texte = texte + ligneDeXCarte(ligne) + "\n";
                ligne = new Carte[getDimension()[0]];
                i = -1;
            }
            i = i + 1;
        }
        return texte;
    }

    /**
     * Résullat : Vrai la carte située aux coordonnées précisées en paramètre est une carte possible pour la table.
     */
    public boolean carteExiste(Coordonnees coordonnees) {
        boolean value = false;
        int[] xy = getDimension();
        if (0 <= coordonnees.getLigne() && coordonnees.getLigne() < xy[0] && 0 <= coordonnees.getColonne() && coordonnees.getColonne() < xy[1]){
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
    public int getPositionByCoordonnes(Coordonnees co){
        int value = 0;
        for (int x = 0; x<co.getLigne()-1; x++){
            value = value + getDimension()[1];
        }
        value = value + co.getColonne();
        return value;
    }

    public int faireSelectionneUneCarte() {
        int [] dimension = this.getDimension();
        System.out.println("Saisissez les coordonné de la carte souhaitée au format 'x,y' : ");
        String texte = Ut.saisirChaine();
        if (Coordonnees.formatEstValide(texte)){
            Coordonnees coordonne = new Coordonnees(texte);
            if (carteExiste(new Coordonnees(coordonne.getLigne(), coordonne.getColonne()))){
                return getPositionByCoordonnes(coordonne);
            }
        }
        return faireSelectionneUneCarte();
    }

    /**
     * Renvoie la carte numero x
     **/
    public Carte getCarteByIndex(int x){
        return cartes[x-1];
    }

    public Carte [] getTableauCarteByIndex(int [] x){
        Carte [] Cartes = new Carte[x.length];
        for (int i = 0; i < x.length; i++){
            Cartes[i] = cartes[x[i]-1];
        }
        return Cartes;
    }

    /**
     * Pre-requis : 1<=nbCartes <= nombre de Cartes de this
     * Action : Fait sélectionner nbCartes Cartes au joueur sur la table en le faisant recommencer jusqu'à avoir une sélection valide.
     * Il ne doit pas y avoir de doublons dans les numéros de cartes sélectionnées.
     * Résullat : Un tableau contenant les numéros de cartes sélectionnées.
     */
    private boolean intInTab(int v, int[] tableau){
        boolean value = false;
        int fini = 0;
        while (fini < tableau.length && !value){
            if (tableau[fini] == v){
                value = true;
            }
            fini = fini +1;
        }
        return value;
    }

    public int[] selectionnerCartesJoueur(int nbCartes) {
        int [] carteSelect = new int[nbCartes];
        int ajout = 0;
        int carte;
        while (ajout < nbCartes){
            carte = faireSelectionneUneCarte();
            if (!intInTab(carte, carteSelect)){
                carteSelect[ajout] = carte;
                ajout = ajout+1;
            }
        }
        return carteSelect;
    }

    /**
     * Action : Affiche les cartes de la table correspondantes aux numéros de cartes contenus dans selection
     * Exemple de format d'affichage : "Sélection : 2-O-H 3-O-H 2-C-H"
     * Les cartes doivent être affichées "colorées"
     */

    public void afficherSelection(int[] selection) {
        Carte [] cartesAffiche = new Carte[selection.length];
        for (int x =0; x < selection.length; x++){
            cartesAffiche[x] = this.cartes[selection[x]-1];
        }
        System.out.println(ligneDeXCarte(cartesAffiche));
    }

    public void setCartes(Carte [] cartes){
        this.cartes = cartes;
    }

    public Carte[] getCartes(){return cartes;}

}

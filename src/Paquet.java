/**
 * La classe Paquet représente un paquet de cartes.
 * Les cartes sont stockées dans un tableau fixe et un indice (entier) permet de connaître le nombre de cartes
 * restantes (non piochées) dans le paquet. Quand on pioche, cet indice diminue.
 * Dans les traitements, on considère alors seulement les cartes se trouvant entre 0 et cet indice (exclus).
 * Par conséquent, on ne supprime pas vraiment les cartes piochées, on les ignore juste.
 * On a donc besoin de connaître :
 * - Le tableau stockant les cartes.
 * - Le nombre de cartes restantes dans le paquet.
 */
public class Paquet {
    public Carte [] ensTab;
    public int nbCarteRestantes;

    /**
     * Pre-requis : figures.length > 0, couleurs.length > 0, textures.length > 0, nbFiguresMax > 0
     *
     * Action : Construit un paquet de cartes mélangé contenant toutes les cartes incluant 1 à nbFiguresMax figures
     * qu'il est possibles de créer en combinant les différentes figures, couleurs et textures précisées en paramètre.
     * Bien sûr, il n'y a pas de doublons.
     *
     * Exemple :
     *  - couleurs = [Rouge, Jaune]
     *  - nbFiguresMax = 2
     *  - figures = [A, B]
     *  - textures = [S, T]
     *  Génère un paquet (mélangé) avec toutes les combinaisons de cartes possibles pour ces caractéristiques : 1-A-S (rouge), 1-A-T (rouge), etc...
     */

    /**public Paquet(Couleur[] couleurs, int nbFiguresMax, Figure[] figures, Texture[] textures) {
     ensTab = new Carte[ getNombreCartesAGenerer(couleurs, nbFiguresMax, figures, textures)];
     for (int i = 0; i < getNombreCartesAGenerer(couleurs, nbFiguresMax, figures, textures); i++){
     for (int n = 1; n < nbFiguresMax+1; n++){
     for (int f = 0; f < figures.length; f++){
     for (int t = 0; t < textures.length; t++){
     for (int c = 0; c < couleurs.length; c++){
     this.ensTab[i] = new Carte(couleurs[c], n, figures[f], textures[t]);
     }
     }
     }
     }
     }
     nbCarteRestantes = ensTab.length;
     //rajouter fonction mélanger ici
     }*/
    public Paquet(Couleur[] couleurs, int nbFiguresMax, Figure[] figures, Texture[] textures) {
        ensTab = new Carte[getNombreCartesAGenerer(couleurs, nbFiguresMax, figures, textures)];
        int i = 0;
        for (int c = 0; c < couleurs.length; c++){
            for (int n = 1; n < nbFiguresMax+1; n++){
                for (int f = 0; f < figures.length; f++){
                    for (int t = 0; t < textures.length; t++){
                        this.ensTab[i] = new Carte(couleurs[c], n, figures[f], textures[t]);
                        i = i+1;
                    }
                }
            }
        }
        nbCarteRestantes = ensTab.length;
        //rajouter fonction mélanger ici
        this.melanger();
    }

    public Paquet(){
        this(Couleur.values(), 3, Figure.values(), Texture.values());
    }

    /**
     * Action : Construit un paquet par recopie en copiant les données du paquet passé en paramètre.
     */

    public Paquet(Paquet paquet) {
        this.ensTab = new Carte[paquet.ensTab.length];
        for (int i = 0; i < paquet.ensTab.length; i++){
            this.ensTab[i] = paquet.ensTab[i];
        }
        this.nbCarteRestantes = paquet.nbCarteRestantes;
    }


    /**
     * Pre-requis : figures.length > 0, couleurs.length > 0, textures.length > 0, nbFiguresMax > 0
     *
     * Resultat : Le nombre de cartes uniques contenant entre 1 et nbFiguresMax figures qu'il est possible de générer en
     * combinant les différentes figures, couleurs et textures précisées en paramètre.
     */

    public static int getNombreCartesAGenerer(Couleur[] couleurs, int nbFiguresMax, Figure[] figures, Texture[] textures) {
        int total;
        int nbCouleur = couleurs.length;
        int nbFigure = figures.length;
        int nbTexture = textures.length;

        total = nbCouleur * nbFigure * nbTexture * nbFiguresMax;

        return total;
    }

    /**
     * Action : Mélange aléatoirement les cartes restantes dans le paquet.
     * Attention, on rappelle que le paquet peut aussi contenir des cartes déjà piochées qu'il faut ignorer.
     */

    public void melanger() {
        int r1, r2;
        for (int i = 0; i < nbCarteRestantes; i++){
            r1 = Ut.randomMinMax(0, nbCarteRestantes-1);
            r2 = Ut.randomMinMax(0, nbCarteRestantes-1);
            swap(r1, r2);
        }
    }

    /**
     * Prérequis : r1 et r2 sont plus petits que nbCarteRestantes - 1
     * Action : échange deux cartes dont les indices sont donnés en paramètre
     */
    public void swap(int r1, int r2){
        if (r1!=r2) {
            Carte c1 = new Carte(ensTab[r1].getCouleur(), ensTab[r1].getNbFigures(), ensTab[r1].getFigure(), ensTab[r1].getTexture());
            Carte c2 = new Carte(ensTab[r2].getCouleur(), ensTab[r2].getNbFigures(), ensTab[r2].getFigure(), ensTab[r2].getTexture());
            ensTab[r1] = c2;
            ensTab[r2] = c1;
        }
    }

    /**
     * Action : Calcule et renvoie un paquet trié à partir du paquet courant (this) selon la méthode du tri selection.
     * Le tri est effectué à partir des données du paquet courant (this) mais celui-ci ne doit pas être modifié !
     * Une nouvelle instance du paquet est traitée et renvoyée.
     * On rappelle que le paquet peut aussi contenir des cartes déjà piochées qu'il faut ignorer (voir partie 2 de la SAE).
     * Le tri doit fonctionner que le Paquet soit plein ou non.
     * https://www.youtube.com/watch?v=Ns4TPTC8whw&t=2s vidéo explicative
     */

    public Paquet trierSelection() {
        Paquet paquetTrier = new Paquet(this);
        int min;
        for (int i = 0; i < paquetTrier.nbCarteRestantes; i++){
            min = i;
            for (int j = i+1; j < paquetTrier.nbCarteRestantes; j++){
                if (paquetTrier.ensTab[min].compareTo(paquetTrier.ensTab[j]) < 0){
                    min = j;
                }
            }
            paquetTrier.swap(i, min);
        }
        return paquetTrier;
    }


    /**
     * Action : Calcule et renvoie un paquet trié à partir du paquet courant (this) selon la méthode du tri bulles.
     * Le tri est effectué à partir des données du paquet courant (this) mais celui-ci ne doit pas être modifié !
     * Une nouvelle instance du paquet est traitée et renvoyée.
     * On rappelle que le paquet peut aussi contenir des cartes déjà piochées  qu'il faut ignorer (voir partie 2 de la SAE).
     * Le tri doit fonctionner que le Paquet soit plein ou non.
     * https://www.youtube.com/watch?v=lyZQPjUT5B4&embeds_referring_euri=https%3A%2F%2Fwww.developpez.com%2F&source_ve_path=Mjg2NjY&feature=emb_logo
     * vidéo explicative
     */

    public Paquet trierBulles() {
        Paquet paquetTrier = new Paquet(this);
        int compteur = 0;
        boolean check = false;
        for (int i = paquetTrier.nbCarteRestantes; !check && 0 < i; i--) {
            for (int j = 0; j < i - 1; j++) {
                if (paquetTrier.ensTab[j].compareTo(paquetTrier.ensTab[j+1]) < 0) {
                    paquetTrier.swap(j, j+1);
                    compteur = compteur + 1;
                }
            }
            if (compteur > 0){
                compteur = 0;
            }
            else {
                check = true;
            }
        }
        return paquetTrier;
    }

    /**
     * Action : Calcule et renvoie un paquet trié à partir du paquet courant (this) selon la méthode du tri insertion.
     * Le tri est effectué à partir des données du paquet courant (this) mais celui-ci ne doit pas être modifié !
     * Une nouvelle instance du paquet est traitée et renvoyée.
     * On rappelle que le paquet peut aussi contenir des cartes déjà piochées  qu'il faut ignorer (voir partie 2 de la SAE).
     * Le tri doit fonctionner que le Paquet soit plein ou non.
     * https://www.youtube.com/watch?v=ROalU379l3U&t=1s : vidéo explicative
     */

    public Paquet trierInsertion() {
        Paquet paquetTrier = new Paquet(this);
        int k;
        for (int i = 1; i < paquetTrier.nbCarteRestantes; i++){
            k = i;
            while (k > 0 && paquetTrier.ensTab[i].compareTo(paquetTrier.ensTab[k-1]) > 0){
                paquetTrier.swap(k, k-1);
                k = k-1;
            }
        }
        return paquetTrier;
    }

    /**
     * Action : Permet de tester les différents tris.
     * On doit s'assurer que chaque tri permette effectivement de trier un paquet mélangé.
     * Des messages d'informations devront être affichés.
     * La méthode est "static" et ne s'effectue donc pas sur la paquet courant "this".
     */

    public long testTriSelec(){
        Runnable runnable = this::trierSelection;
        return Ut.getTempsExecution(runnable);
    }

    public long testTriBulle(){
        Runnable runnable = this::trierBulles;
        return Ut.getTempsExecution(runnable);
    }

    public long testTriInser(){
        Runnable runnable = this::trierInsertion;
        return Ut.getTempsExecution(runnable);
    }

    public static void testTri(){
        Couleur [] listeDeCouleur = Couleur.values();
        Figure [] listeDeFigure = Figure.values();
        Texture [] listeDeTexture= Texture.values();

        Paquet jeu1 = new Paquet(listeDeCouleur, 1000, listeDeFigure, listeDeTexture);
        System.out.println("Nous allons tester différent trie sur un paquet de " + jeu1.nbCarteRestantes + " cartes");
        jeu1.melanger();
        System.out.println("Tri Selection = " + jeu1.testTriSelec() + "ms");
        System.out.println("Tri Bulle = " + jeu1.testTriBulle() + "ms");
        System.out.println("Tri Insertion = " + jeu1.testTriInser() + "ms");
    }

    /**
     * Pre-requis : 0 < nbCartes <= nombre de cartes restantes dans le paquet.
     *
     * Action : Pioche nbCartes Cartes au dessus du Paquet this (et met à jour son état).
     *
     * Résultat : Un tableau contenant les nbCartes Cartes piochees dans le Paquet.
     *
     * Exemple :
     * Contenu paquet : [A,B,C,D,E,F,G]
     * Nombre de cartes restantes : 5. On considère donc seulement les cartes de 0 à 4.
     *
     * piocher(3)
     * Contenu paquet : [A,B,C,D,E,F,G]
     * Nombre de cartes restantes : 2
     * Renvoie [E,D,C]
     */

    public Carte[] piocher(int nbCartes) {
        Carte [] cartesPiocher = null;
        if (peutPiocher(nbCartes)){
            cartesPiocher = new Carte[nbCartes];
            int x = 0;
            for (int pioche = this.nbCarteRestantes; (this.nbCarteRestantes-nbCartes) < pioche; pioche--){
                cartesPiocher[x] = getCarteX(pioche-1);
                x = x +1;
            }
            this.nbCarteRestantes = this.nbCarteRestantes - nbCartes;
        }
        return cartesPiocher;
    }

    /**
     * Résultat : Vrai s'il reste assez de cartes dans le paquet pour piocher nbCartes.
     */

    public boolean peutPiocher(int nbCartes) {
        return this.nbCarteRestantes >= nbCartes;
    }

    /**
     * Résultat : Vrai s'il ne reste plus aucune cartes dans le paquet.
     */

    public boolean estVide() {
        return this.nbCarteRestantes == 0;
    }

    /**
     * Résultat : Une chaîne de caractères représentant le paquet sous la forme d'un tableau
     * [X, Y, Z...] représentant les cartes restantes dans le paquet.
     *
     * Exemple :
     * Contenu paquet : 1-O-P (rouge), 2-C-V (jaune), 3-L-P (jaune), 3-L-P (rouge), 1-L-V (bleu)
     * Nombre de cartes restantes : 3
     * Retourne [1-O-P, 2-C-V, 3-L-P] (et chaque représentation d'une carte est coloré selon la couleur de la carte...)
     */

    @Override
    public String toString() {
        String texte = "";
        int min = 0;
        int y = min;
        char value ;
        for (int x = 0; x <5; x++){
            for (int c = 0; c < nbCarteRestantes; c++){
                value = ensTab[c].toString().charAt(y);
                y = min;
                while (value != '\n'){
                    value = ensTab[c].toString().charAt(y);
                    if (ensTab[c].toString().charAt(y) != '\n'){
                        texte = texte + ensTab[c].toString().charAt(y);
                    }
                    y= y+1;
                }
                texte = texte + Couleur.getAnnuler() + " ";
            }
            min = min+25;
            texte = texte + '\n';
        }
        if (this.nbCarteRestantes < 2){
            texte = texte + "Carte restante : " + this.nbCarteRestantes;
        }else {
            texte = texte + "Cartes restantes : " + this.nbCarteRestantes;
        }
        return texte;
    }



    // temp ============================================================================================================
    public Carte getCarteX(int x){
        return ensTab[x];
    }

}

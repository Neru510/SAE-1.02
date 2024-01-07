/**
 * La classe Jeu permet de faire des parties du jeu "E3Cète" soit avec un humain, soit avec un ordinateur.
 *
 * Règles :
 *
 *  - On possède un paquet de cartes qui représentent entre une et trois figures (losange, carre ou ovale), une texture
 *   (vide, hachuré ou plein) et une couleur (rouge, jaune ou bleu). La cardinalité des énumérations est fixée à 3 pour
 *   cette partie 2 de la SAE uniquement.
 *
 *  - Une table 3x3 permet de stocker 9 cartes. Au début de la partie, on dispose 9 cartes sur la table, piochées sur le
 *  dessus du paquet.
 *
 *  - A chaque tour, le joueur doit essayer de trouver un E3C.
 *
 *  - Le joueur doit désigner trois cartes par leurs coordonnées.
 *      - Si ces cartes forment un E3C, il gagne trois points sur son score.
 *      - Si ce n'est pas un E3C, il perd 1 point sur son score.
 *
 *   - Les trois cartes sont remplacées par de nouvelles cartes piochées dans le paquet.
 *
 *   - La partie se termine quand il n'y a plus de cartes dans le paquet (même s'il reste des cartes sur la table).
 *
 * On a donc besoin :
 *
 * - D'un paquet pour stocker toutes les cartes et avoir une pioche.
 * - D'une table.
 * - De quoi stocker le score du joueur (humain ou ordinateur).
 */
public class Jeu {

    private Paquet paquet;
    private Table table;
    private int score;

    /**
     * Action :
     * - Initialise le jeu "E3Cète" en initialisant le score du joueur, le paquet et la table.
     * - La table doit être remplie.
     */

    public Jeu() {
        resetJeu();
    }

    /**
     * Action : Pioche autant de cartes qu'il y a de numéros de cartes dans le tableau numerosDeCartes et les place
     * aux positions correspondantes sur la table.
     */

    public void piocherEtPlacerNouvellesCartes(int[] numerosDeCartes) {
        Carte[] cartes = paquet.piocher(numerosDeCartes.length);
        Carte[] cartesTable = table.getCartes();
        for (int i = 0; i < numerosDeCartes.length; i++){
            cartesTable[numerosDeCartes[i]] = cartes[i];
        }
        table.setCartes(cartes);
    }

    /**
     * Action : Ré-initialise les données et variables du jeu afin de rejouer une nouvelle partie.
     */

    public void resetJeu() {
        this.score = 0;
        this.paquet = new Paquet();
        this.table = new Table(3, 3);
        table.setCartes(paquet.piocher(table.getTaille()));
    }

    /**
     * Résullat : Vrai si les cartes passées en paramètre forment un E3C.
     */
    private static boolean couleurE3C(Carte[] cartes) {
        boolean value = false;
        if (cartes[0].getCouleur().equals(cartes[1].getCouleur()) && cartes[1].getCouleur().equals(cartes[2].getCouleur())){
            value = true;
        }else if (!(cartes[0].getCouleur().equals(cartes[1].getCouleur())) && !(cartes[1].getCouleur().equals(cartes[2].getCouleur()))&& !(cartes[0].getCouleur().equals(cartes[2].getCouleur()))){
            value = true;
        }
        return value;
    }
    private static boolean textureE3C(Carte[] cartes) {
        boolean value = false;
        if (cartes[0].getTexture().equals(cartes[1].getTexture()) && cartes[1].getTexture().equals(cartes[2].getTexture())){
            value = true;
        }else if (!(cartes[0].getTexture().equals(cartes[1].getTexture())) && !(cartes[1].getTexture().equals(cartes[2].getTexture()))&& !(cartes[0].getTexture().equals(cartes[2].getTexture()))){
            value = true;
        }
        return value;
    }
    private static boolean figureE3C(Carte[] cartes) {
        boolean value = false;
        if (cartes[0].getFigure().equals(cartes[1].getFigure()) && cartes[1].getFigure().equals(cartes[2].getFigure())){
            value = true;
        }else if (!(cartes[0].getFigure().equals(cartes[1].getFigure())) && !(cartes[1].getFigure().equals(cartes[2].getFigure()))&& !(cartes[0].getFigure().equals(cartes[2].getFigure()))){
            value = true;
        }
        return value;
    }
    private static boolean nbE3C(Carte[] cartes) {
        boolean value = false;
        if (cartes[0].getNbFigures() == (cartes[1].getNbFigures()) && cartes[1].getNbFigures() == (cartes[2].getNbFigures())){
            value = true;
        }else if (!(cartes[0].getNbFigures() == (cartes[1].getNbFigures())) && !(cartes[1].getNbFigures() == (cartes[2].getNbFigures()))&& !(cartes[0].getNbFigures() == (cartes[2].getNbFigures()))){
            value = true;
        }
        return value;
    }

    public static boolean estUnE3C(Carte[] cartes) {
        return (nbE3C(cartes) && figureE3C(cartes) && textureE3C(cartes) && couleurE3C(cartes));
    }

    /**
     * Action : Recherche un E3C parmi les cartes disposées sur la table.
     * Résullat :
     *  - Si un E3C existe, un tableau contenant les numéros de cartes (de la table) qui forment un E3C.
     *  - Sinon, la valeur null.
     */

    public int[] chercherE3CSurTableOrdinateur() {
        throw new RuntimeException("Méthode non implémentée ! Effacez cette ligne et écrivez le code nécessaire");
    }

    /**
     * Action : Sélectionne alétoirement trois cartes sur la table.
     * La sélection ne doit pas contenir de doublons
     * Résullat : un tableau contenant les numéros des cartes sélectionnées alétaoirement
     */

    public int[] selectionAleatoireDeCartesOrdinateur() {
        throw new RuntimeException("Méthode non implémentée ! Effacez cette ligne et écrivez le code nécessaire");
    }

    /**
     * Résullat : Vrai si la partie en cours est terminée.
     */

    public boolean partieEstTerminee() {
        return this.paquet.estVide();
    }

    /**
     * Action : Fait jouer un tour à un joueur humain.
     * La Table et le score du joueur sont affichés.
     * Le joueur sélectionne 3 cartes.
     *  - Si c'est un E3C, il gagne trois points.
     *  - Sinon, il perd un point.
     * Les cartes sélectionnées sont remplacées.
     * Divers messages d'informations doivent être affichés pour l'ergonomie.
     */

    public void jouerTourHumain() {
        table.toString();

    }

    /**
     * Action : Fait jouer une partie à un joueur humain.
     * A la fin, le score final du joueur est affiché.
     */

    public void jouerHumain() {
        jouerTourHumain();
    }

    /**
     * Action : Fait jouer un tour à l'ordinateur.
     * La Table et le score de l'ordinateur sont affichés.
     * L'ordinateur sélectionne des cartes :
     *  - L'ordinateur essaye toujours de trouver un E3C sur la table. S'il en trouve un, il gagne donc trois points.
     *  - S'il n'en trouve pas, il se rabat sur 3 cartes sélectionnées aléatoirement et perd un point.
     * Les cartes sélectionnées sont remplacées.
     * Divers messages d'informations doivent être affichés pour l'ergonomie.
     */

    public void joueurTourOrdinateur() {

    }

    /**
     * Action : Fait jouer une partie à l'ordinateur.
     * Une pause est faite entre chaque tour (500 ms ou plus) afin de pouvoir observer la progression de l'ordinateur.
     * A la fin, le score final de l'ordinateur est affiché.
     * Rappel : Ut.pause(temps) permet de faire une pause de "temps" millisecondes
     */

    public void jouerOrdinateur() {

    }

    /**
     * Action : Permet de lancer des parties de "E3Cète" au travers d'un menu.
     * Le menu permet au joueur de sélectionner une option parmi :
     *  - humain : lance une partie avec un joueur humain
     *  - ordinateur : lance une partie avec un ordinateur
     *  - terminer : arrête le programme.
     * Après la fin de chaque partie, les données de jeu sont ré-initialisées et le menu est ré-affiché
     * (afin de faire une nouvelle sélection).
     * Les erreurs de saisie doivent être gérées (si l'utilisateur sélectionne une option inexistante).
     * Divers messages d'informations doivent être affichés pour l'ergonomie.
     */

    public void jouer() {

    }
}

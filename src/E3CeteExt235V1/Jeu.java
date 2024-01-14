package E3CeteExt235V1;
import java.util.Scanner;

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
    private int score = 0;
    private int[] tailleTable = new int[] {3, 3};
    private int[] paramettre = new int[] {3, 3, 3, 3};
    private int delay;
    private long timer;

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
        if (!partieEstTerminee()){
            Carte[] cartes = paquet.piocher(numerosDeCartes.length);
            Carte[] cartesTable = this.table.getCartes();
            for (int i = 0; i < numerosDeCartes.length; i++){
                cartesTable[numerosDeCartes[i]-1] = cartes[i];
            }

            this.table.setCartes(cartesTable);
        }
    }

    /**
     * Action : Ré-initialise les données et variables du jeu afin de rejouer une nouvelle partie.
     */

    public void resetJeu() {
        this.score = 0;
        this.paquet = new Paquet(paramettre);
        this.paquet.melanger();
        this.table = new Table(tailleTable[0], tailleTable[1]);
        table.setCartes(paquet.piocher(table.getTaille()));
    }

    /**
     * Résullat : Vrai si les cartes passées en paramètre forment un EXC.
     */


    private static boolean texturePareille(Carte[] cartes){
        boolean value = true;
        for (int x = 0; x < cartes.length-1; x++){
            if (cartes[x].getTexture() != cartes[x+1].getTexture()){
                value = false;
            }
        }
        return value;
    }
    private static boolean textureDif(Carte[] cartes){
        boolean value = true;
        for (int x = 0; x < cartes.length-1; x++){
            for (int y =0; y < cartes.length; y ++){
                if (x != y &&cartes[x].getTexture() == cartes[y].getTexture()){
                    value = false;
                }
            }
        }
        return value;
    }
    private static boolean textureEXC(Carte[] cartes){
        return textureDif(cartes) || texturePareille(cartes);
    }

    private static boolean couleurPareille(Carte[] cartes){
        boolean value = true;
        for (int x = 0; x < cartes.length-1; x++){
            if (cartes[x].getCouleur() != cartes[x+1].getCouleur()){
                value = false;
            }
        }
        return value;
    }
    private static boolean couleurDif(Carte[] cartes){
        boolean value = true;
        for (int x = 0; x < cartes.length-1; x++){
            for (int y =0; y < cartes.length; y ++){
                if (x != y &&cartes[x].getCouleur() == cartes[y].getCouleur()){
                    value = false;
                }
            }
        }
        return value;
    }
    private static boolean couleurEXC(Carte[] cartes){
        return couleurDif(cartes) || couleurPareille(cartes);
    }

    private static boolean figurePareille(Carte[] cartes){
        boolean value = true;
        for (int x = 0; x < cartes.length-1; x++){
            if (cartes[x].getFigure() != cartes[x+1].getFigure()){
                value = false;
            }
        }
        return value;
    }
    private static boolean figureDif(Carte[] cartes){
        boolean value = true;
        for (int x = 0; x < cartes.length-1; x++){
            for (int y =0; y < cartes.length; y ++){
                if (x != y &&cartes[x].getFigure() == cartes[y].getFigure()){
                    value = false;
                }
            }
        }
        return value;
    }
    private static boolean figureEXC(Carte[] cartes){
        return figureDif(cartes) || figurePareille(cartes);
    }

    private static boolean nbPareille(Carte[] cartes){
        boolean value = true;
        for (int x = 0; x < cartes.length-1; x++){
            if (cartes[x].getNbFigures() != cartes[x+1].getNbFigures()){
                value = false;
            }
        }
        return value;
    }
    private static boolean nbDif(Carte[] cartes){
        boolean value = true;
        for (int x = 0; x < cartes.length-1; x++){
            for (int y =0; y < cartes.length; y ++){
                if (x != y &&cartes[x].getNbFigures() == cartes[y].getNbFigures()){
                    value = false;
                }
            }
        }
        return value;
    }
    private static boolean nbEXC(Carte[] cartes){
        return nbDif(cartes) || nbPareille(cartes);
    }

    public static boolean estUnEXC(Carte[] cartes) {
        return (nbEXC(cartes) && figureEXC(cartes) && textureEXC(cartes) && couleurEXC(cartes));
    }

    /**
     * Action : Recherche un E3C parmi les cartes disposées sur la table.
     * Résullat :
     *  - Si un E3C existe, un tableau contenant les numéros de cartes (de la table) qui forment un E3C.
     *  - Sinon, la valeur null.
     */

    public int[] chercherEXCSurTableOrdinateur() {//toi
        int [] positionEXC = null;
        int nb = table.getDimension()[0];
        for(int x = 1; x < table.getTaille()+1-nb; x++){
            for(int y = 0; y < nb; y++){
                table.getCarteByIndex(x+y);
            }
        }
        return positionEXC;
    }

    /**
     * Action : Sélectionne alétoirement trois cartes sur la table.
     * La sélection ne doit pas contenir de doublons
     * Résultat : un tableau contenant les numéros des cartes sélectionnées aléatoirement
     */

    public int[] selectionAleatoireDeCartesOrdinateur() { // Pas opti (faire s'il y a encore du temps)
        int [] tab = new int[3];
        boolean check = false;
        while (!check){
            for (int i = 0; i < 3 ; i ++){
                tab[i] = this.selectNumCarte();
            }
            if (tab[0] != tab[1] && tab[0] != tab[2] && tab[1] != tab[2]){
                check = true;
            }
        }
        return tab;
    }

    public int selectNumCarte(){
        int [] dimensions = this.table.getDimension();
        int x = Ut.randomMinMax(2,dimensions[0]);
        int y = Ut.randomMinMax(2,dimensions[1]);
        Coordonnees co = new Coordonnees(x,y);
        return this.table.getPositionByCoordonnes(co);
    }

    /**
     * Résultat : Vrai si la partie en cours est terminée.
     */

    public boolean partieEstTerminee() {
        boolean value = this.paquet.estVide();
        if (!value){
            if (this.paquet.nbCarteRestantes < 3){
                value = true;
            }
        }
        return value;
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
        System.out.println("|                      [Début du tour de l'Humain]                      |");
        Carte [] cartes = new Carte[3];
        int [] cartesnum = new int[3];
        System.out.println(table.toString());
        boolean check = false;
        int checkk = 0;
        while (!check){
            System.out.println("Voulez-vous selectionner un E" + table.getDimension()[0]+"C (écrivez E" + table.getDimension()[0] + "C) ou remplacer une carte (écrivez remplacer) ?");
            Scanner clavier = new Scanner(System.in);
            String s = clavier.nextLine();
            if (s.equals("E"+table.getDimension()[0] + "C")){
                cartesnum = table.selectionnerCartesJoueur(3);
                cartes = table.getTableauCarteByIndex(cartesnum);
                table.afficherSelection(cartesnum);
                piocherEtPlacerNouvellesCartes(cartesnum);
                if (estUnEXC(cartes)){
                    score = score + 3;
                    System.out.println("Tu as gagné 3 points ! Voici ton nouveau score : " + score);
                    checkk = 1;
                }
                else {
                    score = score - 3;
                    System.out.println("Tu as perdu 3 points ! Voici ton nouveau score : " + score);
                    checkk = 2;
                }
                check = true;
            }
            else if (s.equals("remplacer")) {
                cartesnum = table.selectionnerCartesJoueur(1);
                cartes = table.getTableauCarteByIndex(cartesnum);
                table.afficherSelection(cartesnum);
                piocherEtPlacerNouvellesCartes(cartesnum);
                check = true;
                System.out.println("Ton score est toujours de : " + score);
            }
        }
        if (delay!= 0 && System.currentTimeMillis() - timer >= delay){
            if (checkk == 1){
                score = score - 3;
            }
            else if (checkk == 2){
                score = score + 3;
            }
        }
    }

    public static int choisirDifficulte(){
        System.out.println("Veuillez choisir le niveau de difficulté :");
        System.out.println("- Facile");
        System.out.println("- Moyen");
        System.out.println("- Difficile");
        System.out.println("- Libre");
        boolean check = false;
        while (!check){
            System.out.println("Entrez f pour facile, m pour moyen, d pour difficile ou l pour libre :");
            Scanner clavier = new Scanner(System.in);
            String s = clavier.nextLine();
            if (s.equals("f")){
                return 900000; //15 minutes
            }
            else if (s.equals("m")){
                return 600000; //10 minutes
            }
            else if (s.equals("d")){
                return 300000; //5 minutes
            }
            else if (s.equals("l")) {
                return 0;
            }
        }
        return 0;
    }

    /**
     * Action : Fait jouer une partie à un joueur humain.
     * A la fin, le score final du joueur est affiché.
     */

    public void jouerHumain() {
        boolean check = true;
        boolean checkon = false;
        boolean checkDelay = true;
        delay = choisirDifficulte();
        timer = System.currentTimeMillis();
        if (delay > 0){
            while ((System.currentTimeMillis() - timer) < delay) //tant que le temps écoulé depuis qu'on a initialisé le timer est inférieur au delay
            {
                while (check && !paquet.estVide()) {
                    System.out.println("Il vous reste " + (delay - (System.currentTimeMillis() - timer))/1000 + "secondes\n");
                    jouerTourHumain();
                    if ((System.currentTimeMillis() - timer) >= delay){
                        check = false;
                        checkDelay = false;
                    }
                    System.out.println("Voulez-vous arrêter de jouer ?");
                    while (!checkon) {
                        System.out.println(" Écrivez n pour non ou o pour oui");
                        Scanner clavier = new Scanner(System.in);
                        String s = clavier.nextLine();
                        if (s.equals("o")) {
                            check = false;
                            checkon = true;
                        } else if (s.equals("n")) {
                            checkon = true;
                        } else {
                            checkon = false;
                        }
                    }
                    checkon = false;
                }
            }
        }
        else {
            while (check && !paquet.estVide()) {
                jouerTourHumain();
                System.out.println("Voulez-vous arrêter de jouer ?");
                while (!checkon) {
                    System.out.println(" Écrivez n pour non ou o pour oui");
                    Scanner clavier = new Scanner(System.in);
                    String s = clavier.nextLine();
                    if (s.equals("o")) {
                        check = false;
                        checkon = true;
                    } else if (s.equals("n")) {
                        checkon = true;
                    } else {
                        checkon = false;
                    }
                }
                checkon = false;
            }
            if (check){
                jouerTourHumain();
            }
        }

        if (!checkDelay){
            System.out.println("Ton temps est écoulé ! Voici ton score final : " + score + " et le temps restant : " + (delay - (System.currentTimeMillis() - timer))/1000 + " secondes");
        }
        else {
            System.out.println("Il n'y a plus de cartes ! Voici ton score final : " + score);
        }
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

    public void jouerTourOrdinateur() {
        int [] cartes = chercherEXCSurTableOrdinateur();
        System.out.println("|                    [Début du tour de l'ordinateur]                    |");
        System.out.println('\n'+table.toString());
        System.out.print("Recherche E"+table.getDimension()[0]+"C : ");
        Ut.pause(500);
        System.out.println((cartes != null)+"\n");
        if (cartes != null){
            System.out.println("Saisie des coordonnées des cartes");
            Ut.pause(500);
            score = score + 3;
        } else{
            cartes = selectionAleatoireDeCartesOrdinateur();
            System.out.println("Selection de cartes aléatoire");
            Ut.pause(500);
            score = score - 1;
        }
        System.out.println("Affichage des cartes");
        Ut.pause(500);
        table.afficherSelection(cartes);

        System.out.println("Le score est actuellement de " + score);
        System.out.print("Il reste actuellement " + paquet.nbCarteRestantes + " carte");
        if (paquet.nbCarteRestantes < 2){
            System.out.print("s");
        }
        System.out.print(" dans le paquet.\n\nRemplacement des cartes ...\n\n");
        Ut.pause(1000);
        piocherEtPlacerNouvellesCartes(cartes);
        System.out.println("|                     [Fin du tour de l'ordinateur]                     |");
        Ut.pause(2000);
        System.out.println("\n\n\n");
    }

    /**
     * Action : Fait jouer une partie à l'ordinateur.
     * Une pause est faite entre chaque tour (500 ms ou plus) afin de pouvoir observer la progression de l'ordinateur.
     * A la fin, le score final de l'ordinateur est affiché.
     * Rappel : Ut.pause(temps) permet de faire une pause de "temps" millisecondes
     */

    public void jouerOrdinateur() {
        while (!partieEstTerminee()){
            jouerTourOrdinateur();

        }
        System.out.println("Il n'y a plus de cartes ! Le score final est de " + score);
        Ut.pause(2000);
        System.out.println("\n\n\n");
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

    private int parametre(String texte){
        int nb = 0;
        System.out.println(texte);
        Scanner clavier = new Scanner(System.in);
        String s = clavier.nextLine();
        if (Ut.estNombre(s)){
            nb = Integer.parseInt(s);
            if (1< nb && nb <= 20){
                return nb;
            }
        }
        return parametre(texte);
    }

    public void parametragePacket(){
        paramettre[0] = parametre("Veuillez choisir le nombre de couleur entre 2 et 20 : ");
        paramettre[1] = parametre("Veuillez choisir nombre de nombre entre 2 et 20 : ");
        paramettre[2] = parametre("Veuillez choisir un nombre de figure entre 2 et 20 : ");
        paramettre[3] = parametre("Veuillez choisir un nombre de texture entre 2 et 20 : ");
        System.out.println();
        int x = parametre("Veuillez choisir la largeur de la table entre 2 et 20 : ");
        int y = parametre("Veuillez choisir la longueur de la table entre 2 et 20 : ");
        tailleTable = new int[] {x, y};
        if (paramettre[0] * paramettre[1] * paramettre[2] * paramettre[3] < x * y){
            System.out.println("Erreur la table est trop grande par rapport au nombres de cartes");
            parametragePacket();
        }
    }

    public void jouer() {
        boolean check = false;
        System.out.println("---------Une nouvelle partie est en cours de création---------");
        System.out.println("Veuillez choisir qui va jouer :");
        System.out.println("- Vous");
        System.out.println("- Ordinateur");
        System.out.println("- Paramètre");
        System.out.println("- Terminer");

        while (!check){
            System.out.println("Entrez v pour vous, o pour l'ordinateur, p pour paramètre et t pour terminer le programme");
            Scanner clavier = new Scanner(System.in);
            String s = clavier.nextLine();
            if (s.equals("v")){
                resetJeu();
                jouerHumain();
            }
            else if (s.equals("o")){
                resetJeu();
                jouerOrdinateur();
            }
            else if (s.equals("p")) {
                parametragePacket();
            }else if (s.equals("t")){
                check = true;
                System.out.println("Merci d'avoir joué");
            }
            else {
                System.out.println("Il semble que vous n'avez pas rentré v ni o, veuillez recommencer.");
            }
        }
    }
}

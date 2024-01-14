package E3CeteBase;

public class QuestionMath {
    static int taille = 9;

    public static void changerTaille(int x, int y){taille = x*y;}
    public static int nbCarteRouge(Carte[] table){
        int value = 0;
        for (int x = 0; x < table.length; x++){
            if(table[x].getCouleur() == Couleur.ROUGE){
                value = value + 1;
            }
        }
        return value;
    }

    private static int testTableR(Paquet EnsembleCartes){
        Paquet paquet = new Paquet(EnsembleCartes);
        paquet.melanger();
        int carteRouge = nbCarteRouge(paquet.piocher(taille));
        int value = 0;
        if (carteRouge == 3){
            value = 1;
        }
        return value;
    }

    public static void proba3CR(int nbTest){
        Paquet paquet = new Paquet();
        int somme = 0;
        for (int i = 0; i < nbTest; i ++){
            somme = somme +testTableR(paquet);
        }
        float value =  (float) somme /nbTest;
        System.out.println("|                                [ Test de probabilité ]                                |" +
                "\nprobabilité d'avoir pile trois E3Cete.Carte Rouge dans une table aléatoire est de " + value+"." +
                "\nCe qui équivaut à " +(int)(value*100) + "%.");//0.289
    }
    private static int testTableE3C(){
        Jeu jeu = new Jeu();
        int [] tab = jeu.chercherE3CSurTableOrdinateur();
        int value = 0;
        if (tab != null){
            value = 1;
        }
        return value;
    }
    public static void E3CSurTable(int nbTest){
        int somme = 0;
        for (int i = 0; i < nbTest; i ++){
            somme = somme +testTableE3C();
        }
        float value =  (float) somme /nbTest;
        System.out.println("|                                [ Test de probabilité ]                                |" +
                "\nprobabilité d'avoir un E3C dans une table aléatoire est de " + value+"." +
                "\nCe qui équivaut à " +(int)(value*100) + "%.");//0.70
    }

    public static void main(String[]args) {
        int nbTest = 10000000;

        E3CSurTable(nbTest);
    }

}

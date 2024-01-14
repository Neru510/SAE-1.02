package E3CeteExt235V1;

public class Main {

    public static String toString(long [] tab) {
        String s = "";
        for (int i = 0; i<100; i++){
            s = s + '\n' + tab[i];
        }
        return s;
    }

    /**
     * Action : lance une partie de jeu "E3Cète"
     */
    public static void main(String[]args) {
        Jeu jeu = new Jeu();
        jeu.jouer();

        //Paquet paquet = new Paquet();
        //paquet = paquet.trierSelection();
        //System.out.println(paquet.toString());
    }

}

package E3CeteBase;

public class Main {

    public static String toString(long [] tab) {
        String s = "";
        for (int i = 0; i<100; i++){
            s = s + '\n' + tab[i];
        }
        return s;
    }

    public static void testTriTemps(){
        Couleur [] listeDeCouleur = Couleur.values();
        Figure [] listeDeFigure = Figure.values();
        Texture [] listeDeTexture= Texture.values();

        for (int i = 0; i < 100; i++) {

            Paquet jeu1 = new Paquet(listeDeCouleur, i, listeDeFigure, listeDeTexture);
            System.out.println(jeu1.toString());
            System.out.println(Paquet.getNombreCartesAGenerer(listeDeCouleur, i, listeDeFigure, listeDeTexture) + "=========================");

            System.out.println("Tri Selection = " + jeu1.testTriSelec() + " millisecondes");
            System.out.println("Tri Bulle     = " + jeu1.testTriBulle() + " millisecondes");
            System.out.println("Tri Insertion = " + jeu1.testTriInser() + " millisecondes");

            long[] tabS = new long[100];
            long[] tabB = new long[100];
            long[] tabI = new long[100];
            for (int j = 0; j < 100; j++) {
                tabS[j] = jeu1.testTriSelec();
                tabB[j] = jeu1.testTriBulle();
                tabI[j] = jeu1.testTriInser();
                jeu1.melanger();
            }


            System.out.println("Tri Selection");
            System.out.println(toString(tabS));
            System.out.println("================================================");
            System.out.println("Tri Bulle");

            System.out.println(toString(tabB));
            System.out.println("================================================");
            System.out.println("Tri Insertion");
            System.out.println(toString(tabI));
        }
    }

    /**
     * Action : lance une partie de jeu "E3Cète"
     */
    public static void main(String[]args) {
        //Jeu jeu = new Jeu();
        //jeu.jouer();
        testTriTemps();


        /*
            System.out.println("Tri Selection = " + jeu1.testTriSelec() + "ms");

            System.out.println("Tri Bulle = " + jeu1.testTriBulle() + "ms");

            System.out.println("Tri Insertion = " + jeu1.testTriInser() + "ms");

            System.out.println(E3Cete.Table.ligneDeXCarte(new E3Cete.Carte[] {jeu1.getCarteX(0), jeu1.getCarteX(1),jeu1.getCarteX(2)}));
        */
    }

}

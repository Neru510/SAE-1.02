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
/*
        Couleur [] listeDeCouleur = Couleur.values();
        Figure [] listeDeFigure = Figure.values();
        Texture [] listeDeTexture= Texture.values();

        Paquet jeu1 = new Paquet(listeDeCouleur, 3, listeDeFigure, listeDeTexture);
        System.out.println(jeu1.toString());
/*
        System.out.println("Tri Selection = " + jeu1.testTriSelec() + " millisecondes");
        System.out.println("Tri Bulle     = " + jeu1.testTriBulle() + " millisecondes");
        System.out.println("Tri Insertion = " + jeu1.testTriInser() + " millisecondes");

        /*long [] tabS = new long[100];
        long [] tabB = new long[100];
        long [] tabI = new long[100];
        for (int i = 0; i < 100; i++){
            tabS[i] = jeu1.testTriSelec();
            tabB[i] = jeu1.testTriBulle();
            tabI[i] = jeu1.testTriInser();
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





        /*
            System.out.println("Tri Selection = " + jeu1.testTriSelec() + "ms");

            System.out.println("Tri Bulle = " + jeu1.testTriBulle() + "ms");

            System.out.println("Tri Insertion = " + jeu1.testTriInser() + "ms");

            System.out.println(Table.ligneDeXCarte(new Carte[] {jeu1.getCarteX(0), jeu1.getCarteX(1),jeu1.getCarteX(2)}));
        */
    }

}

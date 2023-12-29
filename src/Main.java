public class Main {

    /**
     * Action : lance une partie de jeu "E3Cète"
     */
    public static void main(String[]args) {
        Couleur [] listeDeCouleur = Couleur.values();
        Figure [] listeDeFigure = Figure.values();
        Texture [] listeDeTexture= Texture.values();

        Paquet jeu1 = new Paquet(listeDeCouleur, 4, listeDeFigure, listeDeTexture);
        /*System.out.println(jeu1.toString());*/

        System.out.println("Tri Selection = " + jeu1.testTriSelec() + "ns");

        System.out.println("Tri Bulle = " + jeu1.testTriBulle() + "ns");

        System.out.println("Tri Insertion = " + jeu1.testTriInser() + "ns");

    }

}

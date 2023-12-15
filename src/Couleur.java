public enum Couleur {

    /**
     * Représente la couleur d'une Carte : jaune, rouge ...
     * En plus de donner une liste énumérative des couleurs possibles,
     * cette enumération doit permettre à la méthode toString d'une Carte de réaliser un affichage en couleur.
     */
    ROUGE("\u001B[41m");

    private String couleur;
    private static String texteCouleur = "\u001B[30m";
    private static String annuler = "\u001B[0m";


    Couleur(String couleur) {
        this.couleur = couleur;
    }

    public String toString(String texte){
        return this.couleur + texteCouleur + " " + texte + " " + annuler;
    }
}

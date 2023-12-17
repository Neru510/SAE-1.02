public enum Couleur {

    /**
     * Représente la couleur d'une Carte : jaune, rouge ...
     * En plus de donner une liste énumérative des couleurs possibles,
     * cette enumération doit permettre à la méthode toString d'une Carte de réaliser un affichage en couleur.
     */
    ROUGE("\u001B[41m"), JAUNE("\u001B[43m"), BLEU("\u001B[44m"), VERT("\u001B[42m"), GRIS("\u001B[47m"), CYAN("\u001B[46m"), VIOLET("\u001B[45m");

    private String couleur;
    private static String texteNoir = "\u001B[30m";
    private static String annuler = "\u001B[0m";


    Couleur(String couleur) {
        this.couleur = couleur;
    }

    public String ColorString(String texte){
        return this.couleur + texteNoir + texte + annuler;
    }
    
    public String ColorStringE(String texte){
        return "\u001B[51m"+this.couleur + texteNoir + texte + annuler;
    }

    public static void test(){
        System.out.print(ROUGE.ColorString(" Je ")+JAUNE.ColorString(" suis ")+BLEU.ColorString(" multi")+VERT.ColorString("color "));
    }
}

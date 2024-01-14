package E3CeteBase;

public enum Couleur {

    /**
     * Représente la couleur d'une E3Cete.Carte : jaune, rouge ...
     * En plus de donner une liste énumérative des couleurs possibles,
     * cette enumération doit permettre à la méthode toString d'une E3Cete.Carte de réaliser un affichage en couleur.
     */
    ROUGE("\u001B[41m"), JAUNE("\u001B[43m"), VERT("\u001B[42m");
    /**BLANC("\u001B[47m"), CYAN("\u001B[46m"), VIOLET("\u001B[45m"), BLEU("\u001B[44m")**/
    private final String couleur;
    private static final String texteNoir = "\u001B[30m";
    private static final String annuler = "\u001B[00m";

    Couleur(String couleur) {
        this.couleur = couleur;
    }

    public String ColorString(String texte) {
        String value = this.couleur + texteNoir; // 15
        int counter = 0;
        while (counter < texte.length()) {
            if (texte.charAt(counter) == '\n') {
                value = value + annuler + '\n' + this.couleur + texteNoir; // 16
            } else {
                value = value + texte.charAt(counter);
            }
            counter = counter + 1;
        }
        return value + annuler;
    }

    public static String getAnnuler(){return annuler;}
}

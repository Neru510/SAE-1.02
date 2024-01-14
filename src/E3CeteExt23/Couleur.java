package E3CeteExt23;

public enum Couleur {

    /**
     * Représente la couleur d'une E3Cete.Carte : jaune, rouge ...
     * En plus de donner une liste énumérative des couleurs possibles,
     * cette enumération doit permettre à la méthode toString d'une E3Cete.Carte de réaliser un affichage en couleur.
     */
    ROUGE("\033[48;5;196m"), Grena("\033[48;5;124m", true), OR("\033[48;5;220m"), Rose("\033[48;5;198m"), VERT("\033[48;5;10m"),
    BLANC("\033[48;5;15m"), VIOLET("\033[48;5;54m", true), BLEU("\033[48;5;20m", true), MARRON("\033[48;5;94m", true), ORANGE("\033[48;5;214m"),
    GRIS("\033[48;5;244m"), JACINTHE("\033[48;5;105m", true), VERT_SPRING("\033[48;5;29m", true), LIME("\033[48;5;83m"), JAUNE_CLAIRE("\033[48;5;228m"),
    CIEL("\033[48;5;159m"), CYAN("\033[48;5;51m"), ROUGE_INDIEN("\033[48;5;131m", true), SAUMON("\033[48;5;173m"), VERT_JAUNE("\033[48;5;100m", true)
    ;

    private final String couleur;
    private final String texte;
    private static final String texteNoir = "\033[38;5;16m";
    private static final String texteBlanc = "\033[38;5;15m";
    private static final String annuler = "\u001B[00m";

    Couleur(String couleur) {
        this.couleur = couleur;
        this.texte = texteNoir;
    }
    Couleur(String couleur, boolean fonce) {
        this.couleur = couleur;
        if (fonce) {
            this.texte = texteBlanc;
        }else {
            this.texte = texteNoir;
        }
    }
    public String getTexteColor(){
        return texte;
    }


    public String ColorString(String texte) {
        String value = this.couleur + this.texte + texte;
        return value + annuler;
    }

    public static String getAnnuler(){return annuler;}
}

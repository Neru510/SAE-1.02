package E3CeteExt23;

public enum Texture {

    /**
     * Représente la texture d'une E3Cete.Carte : pleine , à pois...
     */
    HACHURE("\\"), PLEINE(" "), POIS("°"), VAGUE("~"), BANDE("|"),
    LIGNE("-"), ECAILLE("C"), HACHURE_INVERSE("/"), POINT(":"), FLECHE(">"),
    INVERTION_HACHURE("\033[7m\\"),INVERTION_PLEIN("\033[7m "),INVERTION_POIS("\033[7m°"),INVERTION_VAGUE("\033[7m~"), INVERTION_BANDE("\033[7m|"),
    INVERTION_LIGNE("\033[7m "), INVERTION_ECAILLE("\033[7mC"), INVERTION_HACHURE_INVERSE("\033[7m/"), INVERTION_POINT("\033[7m:"), INVERTION_FLECHE("\033[7m>")
    ;
    private String forme;
    Texture(String forme){ this.forme = forme;}

    public String getForme(){return this.forme;}
}

package E3CeteExt23;

public enum Figure {

    /**
     * Représente la figure (forme) d'une E3Cete.Carte : ovale , triangle ...
     */
    CARRE("\u25A0"), CARRE_VIDE("\u25A1"), CARRE_TOURNE("\u25C6"),CARRE_TOURNE_VIDE("\u25C7"), CARRE_TOURNE_PLEIN_VIDE("\u25C8"),
    TRIANGLE("\u25B2"), TRIANGLE_VIDE("\u25B3"), TRIANGLE_TOURNE("\u25B6"), TRIANGLE_TOURNE_VIDE("\u25B7"),TRIANGLE_TOURNE_REVERSE("\u25C0"),
    TRIANGLE_TOURNE_REVERSE_VIDE("\u25C1"), VIDE(" "), CERCLE("\u25CF"),CERCLE_VIDE("\u25CB"), LOSANGE("\u25ca"),
    ECLAIR("\u26A1"), A("A"), B("B"), C("C"), D("D")
    ;
    private String figure;
    Figure(String figure){
        this.figure = figure;
    }
    public String getFigure(){
        return figure;
    }

}

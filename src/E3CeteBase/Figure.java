package E3CeteBase;

public enum Figure {

    /**
     * Représente la figure (forme) d'une E3Cete.Carte : ovale , triangle ...
     */
    CARRE("\u25A0"), TRIANGLE("\u25B2"), LOSANGE("\u25ca");
    // ECLAIR("\u26A1"), OVALE("O")
    private String figure;
    Figure(String figure){
        this.figure = figure;
    }
    public String getFigure(){
        return figure;
    }

}

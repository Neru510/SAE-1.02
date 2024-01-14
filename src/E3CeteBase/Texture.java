package E3CeteBase;

public enum Texture {

    /**
     * Représente la texture d'une E3Cete.Carte : pleine , à pois...
     */
    HACHURE('\\'), PLEINE(' '), POIS('°') ;
    // VAGUE('~'), BANDE('|'), LIGNE('-')
    private char forme;
    Texture(char forme){ this.forme = forme;}

    public char getForme(){return this.forme;}
}

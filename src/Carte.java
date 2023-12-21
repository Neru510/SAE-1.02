/**
 * La classe Carte représente une carte possèdant une figure répétée un certain nombre de fois avec une texture et une couleur.
 * On a besoin de connaître :
 * - La figure représentée,
 * - Le nombre de fois où la figure est représentée,
 * - La couleur de la figure,
 * - La texture de la figure.
 */
public class Carte  {

    private Couleur couleur;
    private Texture texture;
    private Figure figure;
    private int nbFigures;

    /**
     * Pre-requis : nbFigures > 0
     * Action : Construit une carte contenant nbFigures "figures" qui possèdent une "texture" et une "couleur"
     * Exemple : new Carte(Couleur.ROUGE, 2, Figure.OVALE, Texture.PLEIN) représente une carte contenant 2 figures ovales rouge et pleines
     */

    public Carte(Couleur couleur, int nbFigures, Figure figure, Texture texture) {
        this.couleur = couleur;
        this.nbFigures = nbFigures;
        this.figure = figure;
        this.texture = texture;
    }

    /**
     * Résultat : Le nombre de fois où la figure est représentée sur la carte.
     */

    public int getNbFigures() {
        return this.nbFigures;
    }

    /**
     * Résultat : La figure représentée sur la carte.
     */

    public Figure getFigure() {
        return this.figure;
    }

    /**
     * Résultat : La couleur représentée sur les figures de la carte.
     */
    public Couleur getCouleur() {
        return this.couleur;
    }

    /**
     * Résultat : La texture représentée sur les figures de la carte.
     */
    public Texture getTexture() {
        return this.texture;
    }

    /**
     * Action : compare les attributs de "this" et de "carte"
     * afin de déterminer si this est plus petit, égal ou plus grand que "carte"
     *
     * L'odre d'importance des attrbiuts est celui donné dans le constructeur (du plus prioritaire au moins prioritaire) :
     * Couleur, nombre de figures, figure, texture.
     * Pour comparer les couleurs, les figures et les textures, on utilisera leur position (ordinal) dans
     * leurs énumérations respectives.
     * Ainsi, pour toute paire {c1,c2} de Carte, c1 est inférieure à c2 si et seulement si
     * la valeur de c1 est inférieure à celle de c2 pour la caractéristique ayant la plus grande priorité
     * parmi celles pour lesquelles c1 et c2 ont des valeurs différentes.
     *
     *
     * Résultat :
     *  0 si "this" est égal à "carte"
     *  Un nombre négatif si "this" est inférieur à "carte"
     *  Un nombre strictement positif si "this "est supérieur à "carte"
     */

    public int compareTo(Carte carte) {
        int value;
        if (this.couleur.compareTo(carte.couleur)==0){ // compare les couleurs
            if (this.nbFigures == carte.nbFigures){ // compare le nombre de figures
                if (this.figure.compareTo(carte.figure)==0){
                    value = this.texture.compareTo(carte.texture);
                }else {
                    value = this.figure.compareTo(carte.figure);
                }
            } else {
                value = this.nbFigures - carte.nbFigures;
            }
        }else {
            value = this.couleur.compareTo(carte.couleur);
        }
        return value;
    }

    private String ligne(int position, int value){
        String ligne = "";
        int max = 9;
        if (value > max){
            max = 8;
            if (position == 7){
                position = 6;
            }
        }
        for (int c = 0; c < max; c++){
            if (c == position){
                ligne = ligne + value;
            }else {
                ligne = ligne + texture.getForme();
            }
        }
        return ligne +'\n';
    }
    private String ligne(int position, String value) {
        String ligne = "";
        for (int c = 0; c < 9; c++) {
            if (c == position) {
                ligne = ligne + value;
            } else {
                ligne = ligne + texture.getForme();
            }
        }
        return ligne + '\n';
    }
    private String ligne() {
        String ligne = "";
        for (int c = 0; c < 9; c++) {
            ligne = ligne + texture.getForme();
        }
        return ligne + '\n';
    }

    /**
     * Résultat :
     * Une chaîne de caractères représentant la carte de la manière suivante :
     *  - Le texte est coloré selon la couleur de la carte
     *  - La chaîne de caractères retournée doit faire apparaitre toutes les caractériqtiques d'une carte sauf la couleur puisque le texte est affiché en couleur
     *  (Vous devez choisir une représentation agréable pour l'utilisateur)
     */


    @Override
    public String toString() {
        String carte = ligne(1, nbFigures);
        carte = carte+ligne();
        carte = carte+ligne(4, figure.getFigure());
        carte = carte+ligne();
        carte = carte+ligne(7, nbFigures);
        return couleur.ColorString(carte);
    }
}

package AutomatesCellulaires.td;

import AutomatesCellulaires.td.EtatCellule;
import AutomatesCellulaires.td.Cellule;
import AutomatesCellulaires.td.Coordonnee;

/**
 * The Grille class represents a grid of cells in the AutomatesCellulaires
 * application.
 * Each cell in the grid has a state represented by an instance of the
 * EtatCellule class.
 */
public class Grille {
    private EtatCellule etat;
    public int nbCol; // number of columns
    public int nbLine; // number of lines
    private Cellule[][] cellules;

    /**
     * Constructs a new Grille with the specified dimensions and initial state.
     *
     * @param dimension the dimension of the grid
     * @param nbColones the number of columns in the grid
     * @param lignes    the number of lines in the grid
     * @param etat      the initial state of the cells in the grid
     */
    public Grille(int dimension, int nbColones, int lignes, EtatCellule etat) {
        // this.dimension = dimension;
        this.nbCol = nbColones;
        this.nbLine = lignes;
        if (nbColones < 1) {
            this.nbCol = 1;
        }
        if (lignes < 1) {
            this.nbLine = 1;
        }

        // this.nombreCellules = nombreCellules;
        this.etat = etat;

        this.cellules = new Cellule[nbLine][nbCol];
        for (int i = 0; i < nbLine; i++) {
            for (int j = 0; j < nbCol; j++) {
                this.cellules[i][j] = new Cellule(
                        etat.getEtatByIndex((int) (Math.random() * etat.getEtatChoisie().size())));
            }
        }
    }

    /**
     * Returns a string representation of the grid.
     *
     * @return a string representation of the grid
     */

    public String toString() {
        String grilleString = "---------------------------------------\n";
        for (int i = 0; i < nbLine; i++) {
            for (int j = 0; j < nbCol; j++) {
                grilleString += this.cellules[i][j].getEtat();
                grilleString += "\t" + " | ";
            }
            grilleString += "\n";
        }
        grilleString += "\n---------------------------------------\n";

        return grilleString;
    }

    /**
     * @param coordonneeX coordonnée en X de la cellule
     * @param coordonneeY coordonnée en Y de la cellule
     * @return valeur de la cellule
     */

    public String getValeurCellule(int coordonneeX, int coordonneeY) {

        if (coordonneeX < 0 || coordonneeX >= nbLine || coordonneeY < 0 || coordonneeY >= nbCol) {
            return etat.getEtatByIndex(0);
        }
        return this.cellules[coordonneeX][coordonneeY].getEtat();
    }

    /**
     * @param coordonneeX coordonnée en X de la cellule
     * @param coordonneeY coordonnée en Y de la cellule
     * @param valeur      valeur à donner à la cellule
     */

    public void setValeurCellule(int coordonneeX, int coordonneeY, String valeur) {
        this.cellules[coordonneeX][coordonneeY].setEtat(valeur);
    }

    /**
     * Constructeur a partir de une autre grille
     * 
     * @param Oj une grille deja initialiser
     */
    public Grille(Grille Oj) {
        this.etat = Oj.etat;
        this.nbLine = Oj.nbLine;
        this.nbCol = Oj.nbCol;
        // this.dimension = Oj.getDimension();
        // this.nombreCellules = Oj.nombreCellules;

        this.cellules = new Cellule[nbLine][nbCol];
        // Coordonnee coordonnee;
        String etat;
        for (int i = 0; i < nbLine; i++) {
            for (int j = 0; j < nbCol; j++) {
                this.cellules[i][j] = new Cellule(Oj.getValeurCellule(i, j));
            }
        }

    }

    /**
     * Copie le contenue de la table cellules qui est en parametres
     * 
     * @param Oj une grille deja initialiser
     */
    public void copieEtatCellules(Grille Oj) {
        for (int i = 0; i < nbLine; i++) {
            for (int j = 0; j < nbCol; j++) {
                this.cellules[i][j].setEtat(Oj.getValeurCellule(i, j));
            }
        }
    }

}

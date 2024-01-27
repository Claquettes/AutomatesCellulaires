package AutomatesCellulaires.td;

import AutomatesCellulaires.td.EtatCellule;
import AutomatesCellulaires.td.Cellule;
import AutomatesCellulaires.td.Coordonnee;

/**
 * This class represents a Grid in an Automaton.
 * It has a number of columns and lines, and a 2D array of Cells.
 */
public class Grille {
    private EtatCellule etat;
    public int nbCol;
    public int nbLine;
    private Cellule[][] cellules;


    /**
     * Constructor for the Grille class.
     * It initializes the Grille with a given dimension, number of columns, number
     * of lines, and state.
     * 
     * @param dimension The dimension of the Grille.
     * @param nbColones The number of columns in the Grille.
     * @param lignes    The number of lines in the Grille.
     * @param etat      The state of the Grille.
     */
    public Grille(int dimension, int nbColones, int lignes, EtatCellule etat) {
        this.nbCol = nbColones;
        this.nbLine = lignes;
        if (nbColones < 1) {
            this.nbCol = 1;
        }
        if (lignes < 1) {
            this.nbLine = 1;
        }
        this.etat = etat;

        this.cellules = new Cellule[nbLine][nbCol];
        for (int i = 0; i < nbLine; i++) {
            if (etat.getEtatChoisie().get(0).equals("Feu")) {
                for (int j = 0; j < nbCol; j++) {
                    if (Math.random() < 0.9) {
                        this.cellules[i][j] = new Cellule(etat.getEtatByIndex(0));
                    } else {
                        this.cellules[i][j] = new Cellule(etat.getEtatByIndex(1));
                    }
                }
            } else {
                for (int j = 0; j < nbCol; j++) {
                    this.cellules[i][j] = new Cellule(etat.getEtatByIndex((int) (Math.random() * etat.getEtatChoisie().size())));
                }

            }

        }
    }

    /**
     * Returns a string representation of the Grille.
     * 
     * @return A string representation of the Grille.
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
     * Gets the value of the Cell at the given coordinates.
     * 
     * @param coordonneeX The x-coordinate of the Cell.
     * @param coordonneeY The y-coordinate of the Cell.
     * @return The value of the Cell at the given coordinates.
     */
    public String getValeurCellule(int coordonneeX, int coordonneeY) { 
        if (coordonneeX < 0 || coordonneeX >= nbLine || coordonneeY < 0 || coordonneeY >= nbCol) {
            return etat.getEtatByIndex(0);
        }
        return this.cellules[coordonneeX][coordonneeY].getEtat();
    }

    /**
     * Sets the value of the Cell at the given coordinates to a new value.
     * 
     * @param coordonneeX The x-coordinate of the Cell.
     * @param coordonneeY The y-coordinate of the Cell.
     * @param valeur      The new value of the Cell.
     */
    public void setValeurCellule(int coordonneeX, int coordonneeY, String valeur) {
        this.cellules[coordonneeX][coordonneeY].setEtat(valeur);
    }

    /**

     * Constructor for the Grille class.
     * It initializes the Grille with another Grille object.
     * 
     * @param Oj The Grille object to copy.
     */
    public Grille(Grille Oj) {
        this.etat = Oj.etat;
        this.nbLine = Oj.nbLine;
        this.nbCol = Oj.nbCol;
        this.cellules = new Cellule[nbLine][nbCol];
        String etat;
        for (int i = 0; i < nbLine; i++) {
            for (int j = 0; j < nbCol; j++) {
                this.cellules[i][j] = new Cellule(Oj.getValeurCellule(i, j));
            }
        }
    }

    /**
     * Copies the state of the Cells from another Grille object.
     * 
     * @param Oj The Grille object to copy from.
     */
    public void copieEtatCellules(Grille Oj) {
        for (int i = 0; i < nbLine; i++) {
            for (int j = 0; j < nbCol; j++) {
                this.cellules[i][j].setEtat(Oj.getValeurCellule(i, j));
            }
        }
    }

    /**
     * @return true if the cell is neighbour of an Arbre
     * @param x x coordiante of the cell
     * @param y y coordiante of the cell
     */
    private boolean isNeighbourArbre(int x, int y) {
        int[] dx = {-1, 0, 1, -1, 1, -1, 0, 1};
        int[] dy = {-1, -1, -1, 0, 0, 1, 1, 1};

        for (int i = 0; i < 8; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];

            if (nx >= 0 && nx < nbLine && ny >= 0 && ny < nbCol && cellules[nx][ny] != null && cellules[nx][ny].getEtat().equals("Arbre")) {
                if (Math.random() < 0.75) { // 3x chance to be Arbre
                    return true;
                }
            }
        }

        return false;
    }
}

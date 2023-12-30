package AutomatesCellulaires.td;

import AutomatesCellulaires.td.EtatCellule;
import AutomatesCellulaires.td.Cellule;
import AutomatesCellulaires.td.Coordonnee;

public class Grille {
    private EtatCellule etat;
    private int dimension;
    private int nombreCellules;
    private Cellule[] cellules; // ON UTILISERA % POUR PASSER D'UNE DIMENSION A L'AUTRE

    public Grille(int dimension, int nombreCellules, EtatCellule etat) {
        this.dimension = dimension;
        this.nombreCellules = nombreCellules;
        this.etat = etat;

        if (nombreCellules % dimension != 0) {
            System.out.println("Le nombre de cellules n'est pas un multiple de la dimension.");
            System.exit(0);
        }

        this.cellules = new Cellule[nombreCellules];
        for (int i = 0; i < nombreCellules; i++) {
            int coordonnees[] = new int[dimension];
            coordonnees[0] = i / dimension;
            for (int j = 1; j < dimension; j++) {
                coordonnees[j] = i % dimension;
            }

            this.cellules[i] = new Cellule(new Coordonnee(coordonnees),
                    etat.getEtatByIndex((int) (Math.random() * etat.getEtatChoisie().size())));
        }
    }

    public int getDimension() {
        return this.dimension;
    }

    public Cellule[] getCellules() {
        return this.cellules;
    }

    public String toString() {
        String grilleString = "---------------------------------------\n";
        for (int i = 0; i < this.nombreCellules; i++) {
            grilleString += this.cellules[i].getEtat() + " (" + this.cellules[i].getCoordonnee() + ")";
            if (i % this.dimension == this.dimension - 1) {
                grilleString += "\n---------------------------------------\n";
            } else {
                grilleString += " | ";
            }
        }
        return grilleString;
    }

    /**
     * @param coordonneeX coordonnée en X de la cellule
     * @param coordonneeY coordonnée en Y de la cellule
     * @return valeur de la cellule
     */

    public String getValeurCellule(int coordonneeX, int coordonneeY) {
        return this.cellules[coordonneeX * this.dimension + coordonneeY].getEtat();
    }

    /**
     * @param coordonneeX coordonnée en X de la cellule
     * @param coordonneeY coordonnée en Y de la cellule
     * @param valeur      valeur à donner à la cellule
     */

    public void setValeurCellule(int coordonneeX, int coordonneeY, String valeur) {
        this.cellules[coordonneeX * this.dimension + coordonneeY].setEtat(valeur);
    }

}

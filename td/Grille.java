package AutomatesCellulaires.td;

import AutomatesCellulaires.td.EtatCellule;
import AutomatesCellulaires.td.Cellule;
import AutomatesCellulaires.td.Coordonnee;

public class Grille {
    private EtatCellule etat;

    //private int dimension;
    //private int nombreCellules;
    public int nbCol; //nombre de nbColones
    public int nbLine; //nombre de lignes
    private Cellule[][] cellules; // ON UTILISERA % POUR PASSER D'UNE DIMENSION A L'AUTRE

    public Grille(int dimension, int nbColones, int lignes, EtatCellule etat) {
        //this.dimension = dimension;
        this.nbCol=nbColones;
        this.nbLine =lignes;
        if(nbColones <1 ){
            this.nbCol=1;
        }
        if(lignes<1){
            this.nbLine=1;
        }

        //this.nombreCellules = nombreCellules;
        this.etat = etat;

       /* if (nombreCellules % dimension != 0) {
            System.out.println("Le nombre de cellules n'est pas un multiple de la dimension.");
            System.exit(0);
        }*/

        this.cellules = new Cellule[nbLine][nbCol];
        for (int i = 0; i < nbLine; i++) {
            for (int j = 0; j < nbCol; j++) {
                this.cellules[i][j] = new Cellule( etat.getEtatByIndex((int) (Math.random() * etat.getEtatChoisie().size())));
            }
        }
    }


    public String toString() {
        String grilleString = "---------------------------------------\n";
        for (int i = 0; i < nbLine ; i++) {
            for (int j = 0; j < nbCol; j++) {
                grilleString +=this.cellules[i][j].getEtat();
                grilleString += "\t"+" | ";
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

        if(coordonneeX<0 || coordonneeX>=nbLine || coordonneeY<0 || coordonneeY>=nbCol ){
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
    * @param Oj une grille deja initialiser
    * */
    public Grille(Grille Oj) {
        this.etat = Oj.etat;
        this.nbLine  = Oj.nbLine ;
        this.nbCol = Oj.nbCol;
        //this.dimension = Oj.getDimension();
        //this.nombreCellules = Oj.nombreCellules;

        this.cellules  = new Cellule[nbLine][nbCol];
        //Coordonnee coordonnee;
        String etat;
        for (int i = 0; i < nbLine; i++) {
            for (int j = 0; j < nbCol; j++) {
                this.cellules[i][j] = new Cellule( Oj.getValeurCellule(i,j));
            }
        }

    }

    /**
     * Copie le contenue de la table cellules qui est en parametres
     * @param Oj une grille deja initialiser
     * */
    public void copieEtatCellules(Grille Oj){
        for (int i = 0; i < nbLine; i++) {
            for (int j = 0; j < nbCol; j++) {
                this.cellules[i][j].setEtat(Oj.getValeurCellule(i,j));
            }
        }
    }


}

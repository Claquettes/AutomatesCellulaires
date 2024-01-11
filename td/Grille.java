package AutomatesCellulaires.td;

import AutomatesCellulaires.td.EtatCellule;
import AutomatesCellulaires.td.Cellule;
import AutomatesCellulaires.td.Coordonnee;

public class Grille {
    private EtatCellule etat;

    //private int dimension;
    //private int nombreCellules;
    public int col; //nombre de colones
    public int line; //nombre de lignes
    private Cellule[][] cellules; // ON UTILISERA % POUR PASSER D'UNE DIMENSION A L'AUTRE

    public Grille(int dimension, int colones, int lignes, EtatCellule etat) {
        //this.dimension = dimension;
        this.col=colones;
        this.line=lignes;
        if(colones <1 ){
            this.col=1;
        }
        if(lignes<1){
            this.line=1;
        }

        //this.nombreCellules = nombreCellules;
        this.etat = etat;

       /* if (nombreCellules % dimension != 0) {
            System.out.println("Le nombre de cellules n'est pas un multiple de la dimension.");
            System.exit(0);
        }*/

        this.cellules = new Cellule[line][col];
        for (int i = 0; i < line; i++) {
            for (int j = 0; j < col; j++) {
                this.cellules[i][j] = new Cellule( etat.getEtatByIndex((int) (Math.random() * etat.getEtatChoisie().size())));
            }
        }
    }


    public String toString() {
        String grilleString = "---------------------------------------\n";
        for (int i = 0; i < line; i++) {
            for (int j = 0; j < col; j++) {
                grilleString +=this.cellules[i][j].getEtat();
                grilleString += " | ";
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
        this.line = Oj.line;
        this.col = Oj.col;
        //this.dimension = Oj.getDimension();
        //this.nombreCellules = Oj.nombreCellules;

        this.cellules  = new Cellule[line][col];
        //Coordonnee coordonnee;
        String etat;
        for (int i = 0; i < line; i++) {
            for (int j = 0; j < col; j++) {
                this.cellules[i][j] = new Cellule( Oj.getValeurCellule(i,j));
            }
        }

    }

    /**
     * Copie le contenue de la table cellules qui est en parametres
     * @param Oj une grille deja initialiser
     * */
    public void copieEtatCellules(Grille Oj){
        for (int i = 0; i < line; i++) {
            for (int j = 0; j < col; j++) {
                this.cellules[i][j].setEtat(Oj.getValeurCellule(i,j));
            }
        }
    }


}

package AutomatesCellulaires.td;

import AutomatesCellulaires.td.LocalRule;

import java.util.Scanner;
//import java.util.concurrent.Thread;

public class Automate {
    public Grille grid;
    public LocalRule localRule;
    // public LocalRule lr; a rajouter quand la classe LocalRule sera créée
    // nombre de voisins détérminé ( 1 0 1 -> cellule actuelle "1" et 2 voisines)
    public Integer nombreVoisins;

    // etats possibles des cellules = Q, un ensemble fini, est son alphabet
    public EtatCellule etatCellules;

    private Grille gridCopy; // qui va contenier la grille à l'état N pour pouvoir calculer l'etat N+1
    private Integer nbCol = 10;
    private Integer nbLigne = 5;

    // constructeur en mode prédéfini,
    // NE PAS OUBLIER DE INITIALISER GRIDCOPY !!!!!
    public Automate(String name, Integer nbCol,Integer nbLigne) {
        this.nbCol = nbCol;
        this.nbLigne = nbLigne;
        if (name.toUpperCase().equals("FEU")) {
            // predefini avec 4 voisins
            this.nombreVoisins = 4;
            EtatCellule etatFeu = new EtatCellule("FEU");
            this.etatCellules = etatFeu;
            System.out.println("45425 WE HAVE THIS ETATCELLULES : " + this.etatCellules);
            this.localRule = new LocalRule(etatFeu.getEtatChoisie(), this.nombreVoisins, "FEU");
            // this.grid = new Grille(2, 8, new EtatCellule("FEU"));
            System.out.println("Création de l'automate " + name + " en mode prédéfini");

            //Grille grille = new Grille(2, 8, etatFeu);
            //8
            this.grid = new Grille(2, this.nbCol, this.nbLigne, etatFeu);
            this.gridCopy = new Grille(grid);

            System.out.println(grid);
            System.out.print(gridCopy);

            for (int i = 0; i < 5; i++) {
                miseAJour();
                System.out.println(grid);
                /*try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }*/
            }

            //System.out.println(grid.getCellules());
            // we quit the program
            System.exit(0);
        }
        if (name.equals("CONWAY")) {
            // this.grid = new Grille(2, 8, new EtatCellule("CONWAY"));
            System.out.println("Création de l'automate " + name + " en mode prédéfini");
        } else {
            System.out.println("Erreur : nom d'automate inconnu...");
        }
    }

    // constructeur en mode 1D, donc custom vu qu'on passe le numero de la
    // regle
    public Automate(String name, Integer nbVoisins, Integer ruleNumber, Integer nbCol,Integer nbLigne) {
        this.nbCol = nbCol;
        this.nbLigne = nbLigne;
        if (name.toUpperCase().equals("1D")) {
            // predefini avec 4 voisins
            this.nombreVoisins = 1;
            EtatCellule etat1D = new EtatCellule("1D");
            this.etatCellules = etat1D;
            this.localRule = new LocalRule(etat1D.getEtatChoisie(), this.nombreVoisins, ruleNumber);
            // this.grid = new Grille(2, 8, new EtatCellule("FEU"));
            System.out.println("Création de l'automate " + name + " en mode prédéfini");
            // Grille grille = new Grille(2, 8, etatFeu);
            this.grid = new Grille(1, this.nbCol,1,  etat1D);
            this.gridCopy = new Grille(grid);

            //System.out.println(grid);

            for (int i = 0; i < 5; i++) {
                miseAJour();
                System.out.println(grid);
            }

            //System.out.print(gridCopy);
            //System.out.println(grid.getCellules());
            // we quit the program
            System.exit(0);
        } else {
            System.out.println("Erreur : nom d'automate inconnu...");
        }
    }

    /**
     * Trouve l'etat suivant selon la configuration de l'etat de la cellule et de
     * ces voisines
     * 
     * @param Configuration String qui contient le "voisinage" (etat cellule ; etat
     *                      cellules voisines)
     * @retun String donnant l'etat suivant
     */
    public String getEtatSuivantCellule(String Configuration) {
        String[] listeTemp = Configuration.split(";");
        int nbReeldeCellules = listeTemp.length;
        if (nbReeldeCellules - 1 < this.nombreVoisins) {
            while (nbReeldeCellules - 1 < this.nombreVoisins) {
                Configuration = Configuration + this.etatCellules.getEtatByIndex(0) + ";";
                listeTemp = Configuration.split(";");
                nbReeldeCellules = listeTemp.length;
            }
        }

        String etatSuivant = this.localRule.getEtatSuivant(Configuration);

        return etatSuivant;
    }


    /**
     * Trouve la configuration des voisins de la cellule
     * 
     * @param x coordonnee x de la cellule
     * @ruturn String qui contient la configuration des voisins
     */
    private String getConfigVoisin(int x, int y){

        String configVoisin = this.gridCopy.getValeurCellule(x, y) + ";";

        switch (this.nombreVoisins) {
            case 1:
                
                configVoisin = configVoisin + this.gridCopy.getValeurCellule(x, y+1) + ";" ;
                return configVoisin;

            case 2:

                configVoisin = configVoisin + this.gridCopy.getValeurCellule(x, y+1) + ";" 
                + this.gridCopy.getValeurCellule(x, y-1) + ";";
                return configVoisin;

            case 3:
                
                configVoisin = configVoisin + this.gridCopy.getValeurCellule(x, y+1) + ";" 
                + this.gridCopy.getValeurCellule(x, y-1) + ";" 
                + this.gridCopy.getValeurCellule(x+1, y)+ ";"  ;
                return configVoisin;

            case 4:

                configVoisin = configVoisin + this.gridCopy.getValeurCellule(x, y+1) + ";" 
                + this.gridCopy.getValeurCellule(x, y-1) + ";" 
                + this.gridCopy.getValeurCellule(x+1, y) + ";" 
                + this.gridCopy.getValeurCellule(x-1, y)+ ";"  ;
                return configVoisin;

            case 5:

                configVoisin = configVoisin + this.gridCopy.getValeurCellule(x, y+1) + ";"
                + this.gridCopy.getValeurCellule(x, y-1) + ";"
                + this.gridCopy.getValeurCellule(x+1, y) + ";"
                + this.gridCopy.getValeurCellule(x-1, y) + ";"
                + this.gridCopy.getValeurCellule(x-1, y-1)+ ";"  ;
                return configVoisin;

            case 6: 

                configVoisin = configVoisin + this.gridCopy.getValeurCellule(x, y+1) + ";"
                + this.gridCopy.getValeurCellule(x, y-1) + ";"
                + this.gridCopy.getValeurCellule(x+1, y) + ";"
                + this.gridCopy.getValeurCellule(x-1, y) + ";"
                + this.gridCopy.getValeurCellule(x-1, y-1) + ";"
                + this.gridCopy.getValeurCellule(x-1, y+1) + ";" ;
                return configVoisin;

            case 7:

                configVoisin = configVoisin + this.gridCopy.getValeurCellule(x, y+1) + ";"
                + this.gridCopy.getValeurCellule(x, y-1) + ";"
                + this.gridCopy.getValeurCellule(x+1, y) + ";"
                + this.gridCopy.getValeurCellule(x-1, y) + ";"
                + this.gridCopy.getValeurCellule(x-1, y-1) + ";"
                + this.gridCopy.getValeurCellule(x-1, y+1) + ";"
                + this.gridCopy.getValeurCellule(x+1, y-1)+ ";"  ;
                return configVoisin;

            case 8:

                configVoisin = configVoisin + this.gridCopy.getValeurCellule(x, y+1) + ";"
                + this.gridCopy.getValeurCellule(x, y-1) + ";"
                + this.gridCopy.getValeurCellule(x+1, y) + ";"
                + this.gridCopy.getValeurCellule(x-1, y) + ";"
                + this.gridCopy.getValeurCellule(x-1, y-1) + ";"
                + this.gridCopy.getValeurCellule(x-1, y+1) + ";"
                + this.gridCopy.getValeurCellule(x+1, y-1) + ";"
                + this.gridCopy.getValeurCellule(x+1, y+1) + ";" ;
                return configVoisin;
            
            default:

                configVoisin = "NONE";
                return configVoisin;

            
        }

    }

    /**
     * Calcule le nouveau etat de la grille selon l'etat actuel (avec localRule)
     * a la fin la grille de l'objet sera mise a jour
     */
    public void miseAJour() {
        // copie Grille gridCopie;
        this.gridCopy.copieEtatCellules(this.grid);

        // pour parcouir la grille 
        for(int i=0; i<this.gridCopy.nbLine; i++){

            for( int j = 0; j < this.gridCopy.nbCol; j++){

                // recupere etat de la cellule et etat cellules voisines
                String etatCellule = this.gridCopy.getValeurCellule(i, j);
                String etatVoisines = getConfigVoisin(i,j);

                // calcule l'etat suivant et le stoque dans la copie
                String etatSuivant = getEtatSuivantCellule(etatVoisines);

                this.grid.setValeurCellule(i, j, etatSuivant);

            }

        }

    }

}

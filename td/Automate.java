package AutomatesCellulaires.td;

import AutomatesCellulaires.td.LocalRule;

import java.util.Scanner;

/**
 * This class represents an Automaton which is a self-operating machine.
 * It has a grid, a local rule, a number of neighbors, a cell state, a grid
 * copy, and dimensions for the grid.
 */
public class Automate {
    public Grille grid;
    public LocalRule localRule;
    public Integer nombreVoisins;
    public EtatCellule etatCellules;

    protected Grille gridCopy; // qui va contenier la grille à l'état N pour pouvoir calculer l'etat N+1
    protected Integer nbCol = 10;
    protected Integer nbLigne = 5;


    public Automate(String name, Integer nbCol,Integer nbLigne) {
        this.nbCol = nbCol;
        this.nbLigne = nbLigne;
        if (name.toUpperCase().equals("FEU")) {
            this.nombreVoisins = 4;
            EtatCellule etatFeu = new EtatCellule("FEU");
            this.localRule = new LocalRule(etatFeu.getEtatChoisie(), this.nombreVoisins, "FEU");

            this.grid = new Grille(2, this.nbCol, this.nbLigne, etatFeu);
            this.gridCopy = new Grille(grid);

        } else {
            if (name.equals("CONWAY")) {
                // this.grid = new Grille(2, 8, new EtatCellule("CONWAY"));
                System.out.println("Création de l'automate " + name + " en mode prédéfini");
            } else {
                System.out.println("Erreur : nom d'automate inconnu...");
            }
        }
    }

   /**
     * Constructor for the Automate class.
     * It initializes the Automate with a given name, number of neighbors, and rule
     * number.
     * 
     * @param name       The name of the Automate.
     * @param nbVoisins  The number of neighbors.
     * @param ruleNumber The rule number.
     */
    public Automate(String name, Integer nbVoisins, Integer ruleNumber, Integer nbCol) {
        this.nbCol = nbCol;

        if (name.toUpperCase().equals("1D")) {
            this.nombreVoisins = 1;
            EtatCellule etat1D = new EtatCellule("1D");
            this.localRule = new LocalRule(etat1D.getEtatChoisie(), this.nombreVoisins, ruleNumber);

            this.grid = new Grille(1, this.nbCol,1,  etat1D);
            this.gridCopy = new Grille(grid);
        } else {
            System.out.println("Erreur : nom d'automate inconnu...");
        }
    }

    /**
     * Constructeur générique de Automate
     */
    public Automate(LocalRule localRule, Integer nombreVoisins, EtatCellule etatCellules, Integer nbCol, Integer nbLigne) {
        this.etatCellules = etatCellules;

        if(nbLigne==1){
            this.grid = new Grille(1,nbCol,nbLigne,etatCellules);
        }else{
            this.grid = new Grille(2,nbCol,nbLigne,etatCellules);
        }
        this.nbCol = nbCol;
        this.nbLigne = nbLigne;

        this.localRule = localRule;
        this.nombreVoisins = nombreVoisins;

        this.gridCopy = new Grille(grid);

    }

    public Automate(Integer nombreVoisins, Integer nbCol, Integer nbLigne, String localRuleName ) {
        EtatCellule etatCellulesIci = new EtatCellule("1D");
        if(etatCellulesIci.getEtatChoisie().isEmpty()){
            return;
            //erreur de construction
        }

        LocalRule LocalRuleM;
        if(localRuleName.equals("MAJORITE")){
            LocalRuleM = new LocalRule(etatCellulesIci.getEtatChoisie(),nombreVoisins,"MAJORITE");
            this.localRule = LocalRuleM;
        }

        this.etatCellules = etatCellulesIci;


        this.grid = new Grille(2,nbCol,nbLigne,etatCellules);

        this.nbCol = nbCol;
        this.nbLigne = nbLigne;


        this.nombreVoisins = nombreVoisins;

        this.gridCopy = new Grille(grid);

    }

    /**
     * Constructeur générique de Automate
     */
    /*public Automate(String nom, Integer nombreVoisins, Integer nbCol, Integer nbLigne) {
        EtatCellule etat = new EtatCellule(nom);
        if(etat == null){
            return ;
        }
        LocalRule localRule1 = new LocalRule(etat.getEtatChoisie(),nombreVoisins,nom);
        this.etatCellules = etatCellules;

        if(nbLigne==1){
            this.grid = new Grille(1,nbCol,nbLigne,etatCellules);
        }else{
            this.grid = new Grille(2,nbCol,nbLigne,etatCellules);
        }
        this.nbCol = nbCol;
        this.nbLigne = nbLigne;

        this.localRule = localRule;
        this.nombreVoisins = nombreVoisins;

        this.gridCopy = new Grille(grid);

    }

    /**
     * constructeur partiel qui met le nbLigne et nbCol selon les parametres et le reste à null
     * @param nbCol
     * @param nbLigne
     */
    public Automate(Integer nbCol, Integer nbLigne) {
        this.etatCellules = null;
        if( nbCol <= 0){
            nbCol = 1;
        }
        if( nbLigne < 1){
            nbLigne = 1;
        }

        this.nbCol = nbCol;
        this.nbLigne = nbLigne;

    }

    /**
     * Initializes the Automate with a given cell state.
     * 
     * @param etatCellule The state of the cell.
     */
    private void initializeAutomate(EtatCellule etatCellule) {
        askForGridSize();
        this.etatCellules = etatCellule;
        this.gridCopy = new Grille(grid);
        System.out.println(grid);

        for (int i = 0; i < 5; i++) {
            miseAJour();
            System.out.println(grid);
        }
        System.exit(0);
    }

    /**
     * Gets the next state of the cell based on its current configuration.
     * 
     * @param Configuration The current configuration of the cell.
     * @return The next state of the cell.
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
     * Gets the configuration of the neighbors of a cell.
     *
     * @param x The x-coordinate of the cell.
     * @param y The y-coordinate of the cell.
     * @return The configuration of the neighbors of the cell.
     */
    protected String getConfigVoisin(int x, int y) {
        //modif ordre directions !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
        int[][] directions = { { 0, 1 }, { 0, -1 }, { 1, 0 }, { -1, 0 }, { -1, -1 }, { 1, -1 }, { -1, 1 },  { 1, 1 } };
        StringBuilder configVoisin = new StringBuilder(this.gridCopy.getValeurCellule(x, y) + ";");
        for (int i = 0; i < this.nombreVoisins; i++) {
            int newX = x + directions[i][0];
            int newY = y + directions[i][1];
            configVoisin.append(this.gridCopy.getValeurCellule(newX, newY)).append(";");
        }

        return configVoisin.toString();
    }


    /**
     * Updates the state of the grid based on the current state and the local rule.
     */
    public void miseAJour() {
        // copie Grille gridCopie;
        this.gridCopy.copieEtatCellules(this.grid);

        // pour parcouir la grille
        for (int i = 0; i < this.gridCopy.nbLine; i++) {
            for (int j = 0; j < this.gridCopy.nbCol; j++) {

                // recupere etat de la cellule et etat cellules voisines
                String etatCellule = this.gridCopy.getValeurCellule(i, j);
                String etatVoisines = getConfigVoisin(i, j);

                // calcule l'etat suivant et le stoque dans la copie
                String etatSuivant = getEtatSuivantCellule(etatVoisines);

                this.grid.setValeurCellule(i, j, etatSuivant);

            }
        }
    }

    /**
     * Asks the user to choose the size of the grid.
     */
    public void askForGridSize() {
        System.out.println("Voulez vous utiliser la taille par défault pour la grille (10*5) ? (Y/N)");
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        if (input.equals("Y") || input.equals("y")) {
            this.nbCol = 10;
            this.nbLigne = 5;
        } else {
            System.out.println("Veuillez entrer le nombre de colonnes de la grille");
            Scanner scannerNbCol = new Scanner(System.in);
            this.nbCol = scannerNbCol.nextInt();
            if (nbCol < 1) {
                System.out.println("Le nombre de colonnes doit être supérieur à 0");
                System.exit(0);
            }
            System.out.println("Veuillez entrer le nombre de lignes de la grille");
            Scanner scannerNbLigne = new Scanner(System.in);
            if (nbLigne < 0) {
                System.out.println("Le nombre de lignes doit être supérieur à 0");
                System.exit(0);
            }
            this.nbLigne = scannerNbLigne.nextInt();
            scannerNbLigne.close();
            scannerNbCol.close();
        }
    }


    @Override
    public String toString() {
        String esp = "  ";
        String diff = " \t";
        if( nombreVoisins == 6 ){
            diff = "  ";
        }
        String grilleString = "---------------------------------------\n";
        for (int i = 0; i < nbLigne ; i++) {
            esp ="  ";
            //pour l'affichage hexagonal
            if( nombreVoisins ==6 && (i % 2 == 0 )){
                grilleString += "   ";
                esp = "  ";
            }
            if( nombreVoisins != 6){
                esp="";
            }

            for (int j = 0; j < nbCol; j++) {
                //grilleString += this.grid.getValeurCellule(i,j);
                grilleString += esp + this.grid.getValeurCellule(i,j);
                grilleString += diff;
                //grilleString += " \t";
            }
            grilleString += "\n";
        }
        grilleString += "\n---------------------------------------\n";

        return grilleString;
    }

}


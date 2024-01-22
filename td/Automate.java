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
    private Grille gridCopy;
    private Integer nbCol = 10;
    private Integer nbLigne = 5;

    /**
     * Constructor for the Automate class.
     * It initializes the Automate with a given name.
     * 
     * @param name The name of the Automate.
     */
    public Automate(String name) {
        if (name.toUpperCase().equals("FEU")) {
            this.nombreVoisins = 4;
            EtatCellule etatFeu = new EtatCellule("FEU");
            this.localRule = new LocalRule(etatFeu.getEtatChoisie(), this.nombreVoisins, "FEU");
            this.grid = new Grille(2, 3, 2, etatFeu);
            initializeAutomate(etatFeu);
        } else {
            System.out.println("Erreur : nom d'automate inconnu...");
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
    public Automate(String name, Integer nbVoisins, Integer ruleNumber) {
        if (name.toUpperCase().equals("1D")) {
            this.nombreVoisins = 1;
            EtatCellule etat1D = new EtatCellule("1D");
            this.localRule = new LocalRule(etat1D.getEtatChoisie(), this.nombreVoisins, ruleNumber);
            this.grid = new Grille(1, this.nbCol, 1, etat1D);
            initializeAutomate(etat1D);
        } else {
            System.out.println("Erreur : nom d'automate inconnu...");
        }
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
    private String getConfigVoisin(int x, int y) {
        int[][] directions = { { 0, 1 }, { 0, -1 }, { 1, 0 }, { -1, 0 }, { -1, -1 }, { -1, 1 }, { 1, -1 }, { 1, 1 } };
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

}

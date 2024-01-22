package AutomatesCellulaires.td;

import AutomatesCellulaires.td.LocalRule;

import java.util.Scanner;
//import java.util.concurrent.Thread;

public class Automate {
    public Grille grid;
    public LocalRule localRule;
    public Integer nombreVoisins;
    public EtatCellule etatCellules;
    private Grille gridCopy;
    private Integer nbCol = 10;
    private Integer nbLigne = 5;

    public Automate(String name) {
        askForGridSize();
        if (name.toUpperCase().equals("FEU")) {
            this.nombreVoisins = 4;
            EtatCellule etatFeu = new EtatCellule("FEU");
            this.etatCellules = etatFeu;
            this.localRule = new LocalRule(etatFeu.getEtatChoisie(), this.nombreVoisins, "FEU");
            this.grid = new Grille(2, 3, 2, etatFeu);
            this.gridCopy = new Grille(grid);
            System.out.println(grid);

            for (int i = 0; i < 5; i++) {
                miseAJour();
                System.out.println(grid);
            }
            System.exit(0);
        } else {
            System.out.println("Erreur : nom d'automate inconnu...");
        }
    }

    public Automate(String name, Integer nbVoisins, Integer ruleNumber) {
        askForGridSize();
        if (name.toUpperCase().equals("1D")) {
            this.nombreVoisins = 1;
            EtatCellule etat1D = new EtatCellule("1D");
            this.etatCellules = etat1D;
            this.localRule = new LocalRule(etat1D.getEtatChoisie(), this.nombreVoisins, ruleNumber);
            this.grid = new Grille(1, this.nbCol, 1, etat1D);
            this.gridCopy = new Grille(grid);
            for (int i = 0; i < 5; i++) {
                miseAJour();
                System.out.println(grid);
            }
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
    private String getConfigVoisin(int x, int y) {

        String configVoisin = this.gridCopy.getValeurCellule(x, y) + ";";

        switch (this.nombreVoisins) {
            case 1:

                configVoisin = configVoisin + this.gridCopy.getValeurCellule(x, y + 1) + ";";
                return configVoisin;

            case 2:

                configVoisin = configVoisin + this.gridCopy.getValeurCellule(x, y + 1) + ";"
                        + this.gridCopy.getValeurCellule(x, y - 1) + ";";
                return configVoisin;

            case 3:

                configVoisin = configVoisin + this.gridCopy.getValeurCellule(x, y + 1) + ";"
                        + this.gridCopy.getValeurCellule(x, y - 1) + ";"
                        + this.gridCopy.getValeurCellule(x + 1, y) + ";";
                return configVoisin;

            case 4:

                configVoisin = configVoisin + this.gridCopy.getValeurCellule(x, y + 1) + ";"
                        + this.gridCopy.getValeurCellule(x, y - 1) + ";"
                        + this.gridCopy.getValeurCellule(x + 1, y) + ";"
                        + this.gridCopy.getValeurCellule(x - 1, y) + ";";
                return configVoisin;

            case 5:

                configVoisin = configVoisin + this.gridCopy.getValeurCellule(x, y + 1) + ";"
                        + this.gridCopy.getValeurCellule(x, y - 1) + ";"
                        + this.gridCopy.getValeurCellule(x + 1, y) + ";"
                        + this.gridCopy.getValeurCellule(x - 1, y) + ";"
                        + this.gridCopy.getValeurCellule(x - 1, y - 1) + ";";
                return configVoisin;

            case 6:

                configVoisin = configVoisin + this.gridCopy.getValeurCellule(x, y + 1) + ";"
                        + this.gridCopy.getValeurCellule(x, y - 1) + ";"
                        + this.gridCopy.getValeurCellule(x + 1, y) + ";"
                        + this.gridCopy.getValeurCellule(x - 1, y) + ";"
                        + this.gridCopy.getValeurCellule(x - 1, y - 1) + ";"
                        + this.gridCopy.getValeurCellule(x - 1, y + 1) + ";";
                return configVoisin;

            case 7:

                configVoisin = configVoisin + this.gridCopy.getValeurCellule(x, y + 1) + ";"
                        + this.gridCopy.getValeurCellule(x, y - 1) + ";"
                        + this.gridCopy.getValeurCellule(x + 1, y) + ";"
                        + this.gridCopy.getValeurCellule(x - 1, y) + ";"
                        + this.gridCopy.getValeurCellule(x - 1, y - 1) + ";"
                        + this.gridCopy.getValeurCellule(x - 1, y + 1) + ";"
                        + this.gridCopy.getValeurCellule(x + 1, y - 1) + ";";
                return configVoisin;

            case 8:

                configVoisin = configVoisin + this.gridCopy.getValeurCellule(x, y + 1) + ";"
                        + this.gridCopy.getValeurCellule(x, y - 1) + ";"
                        + this.gridCopy.getValeurCellule(x + 1, y) + ";"
                        + this.gridCopy.getValeurCellule(x - 1, y) + ";"
                        + this.gridCopy.getValeurCellule(x - 1, y - 1) + ";"
                        + this.gridCopy.getValeurCellule(x - 1, y + 1) + ";"
                        + this.gridCopy.getValeurCellule(x + 1, y - 1) + ";"
                        + this.gridCopy.getValeurCellule(x + 1, y + 1) + ";";
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
     * On demande a l'utilisateur de choisir le colonnes et de lignes de la grille
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
        }
    }

}

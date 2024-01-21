package AutomatesCellulaires.td;

import AutomatesCellulaires.td.LocalRule;

import java.util.Scanner;

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
    public Automate(String name) {
        askForGridSize();
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
            this.grid = new Grille(2,3,2, etatFeu);
            this.gridCopy = new Grille(grid);

            System.out.println(grid);
            System.out.print(gridCopy);

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
    public Automate(String name, Integer nbVoisins, Integer ruleNumber) {
        askForGridSize();
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

            System.out.println(grid);
            System.out.print(gridCopy);
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
     * Calcule le nouveau etat de la grille selon l'etat actuel (avec localRule)
     * a la fin la grille de l'objet sera mise a jour
     */
    public void miseAJour() {
        // copie Grille gridCopie;
        this.gridCopy.copieEtatCellules(this.grid);
        // Selon de nombre de voisins

        // recupere etat de la cellule et etat cellules voisines
        // calcule l'etat suivant et le stoque dans la copie
        // remplace this.grid par la copie et libere les stocage inutil
    }

    /**
     * On demande a l'utilisateur de choisir le colonnes et de lignes de la grille
     */

     public void askForGridSize(){
        System.out.println("Voulez vous utiliser la taille par défault pour la grille (10*5) ? (Y/N)");
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        if(input.equals("Y") || input.equals("y")){
            this.nbCol = 10;
            this.nbLigne = 5;
        }else{

            System.out.println("Veuillez entrer le nombre de colonnes de la grille");
            Scanner scannerNbCol = new Scanner(System.in);
            this.nbCol = scannerNbCol.nextInt();
            if(nbCol < 1){
                System.out.println("Le nombre de colonnes doit être supérieur à 0");
                System.exit(0);
            }
            System.out.println("Veuillez entrer le nombre de lignes de la grille");
            Scanner scannerNbLigne = new Scanner(System.in);
            if(nbLigne < 0){
                System.out.println("Le nombre de lignes doit être supérieur à 0");
                System.exit(0);
            }
            this.nbLigne = scannerNbLigne.nextInt();
        }
     }

}

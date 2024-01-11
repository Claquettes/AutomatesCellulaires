package AutomatesCellulaires.td;
import AutomatesCellulaires.td.LocalRule;

public class Automate {
    public Grille grid;
    public LocalRule localRule;
    // public LocalRule lr; a rajouter quand la classe LocalRule sera créée
    //nombre de voisins détérminé ( 1 0 1 -> cellule actuelle "1" et 2 voisines)
    public int nombreVoisins;

    //etats possibles des cellules = Q, un ensemble fini, est son alphabet
    public EtatCellule etatCellules;

    private Grille gridCopy; //qui va contenier la grille à l'état N pour pouvoir calculer l'etat  N+1


    // constructeur en mode prédéfini,
    // NE PAS OUBLIER DE INITIALISER GRIDCOPY  !!!!!
    public Automate(String name) {
        if (name.toUpperCase().equals("FEU")) {
            //predefini avec 4 voisins
            this.nombreVoisins = 4;
            EtatCellule etatFeu = new EtatCellule("FEU");
            this.etatCellules = etatFeu;
            this.localRule = new LocalRule(etatFeu.getEtatChoisie(),this.nombreVoisins,"FEU");
            // this.grid = new Grille(2, 8, new EtatCellule("FEU"));
            System.out.println("Création de l'automate " + name + " en mode prédéfini");
            //Grille grille = new Grille(2, 8, etatFeu);
            this.grid = new Grille(2, 8, etatFeu);
            this.gridCopy = new Grille(grid);

            System.out.println(grid);
            System.out.print(gridCopy);
            System.out.println(grid.getCellules());
            //we quit the program
            System.exit(0);
        }
        if (name.equals("CONWAY")) {
            // this.grid = new Grille(2, 8, new EtatCellule("CONWAY"));
            System.out.println("Création de l'automate " + name + " en mode prédéfini");
        }  
        else {
            System.out.println("Erreur : nom d'automate inconnu...");
        }
    }

    // constructeur en mode 1D, donc semi-custom vu qu'on passe le numero de la regle
    public Automate(String name, Integer ruleNumber){
        if (name.toUpperCase().equals("1D")) {
            //predefini avec 4 voisins
            this.nombreVoisins = 1;
            EtatCellule etat1D = new EtatCellule("1D");
            this.etatCellules = etat1D;
            this.localRule = new LocalRule(etat1D.getEtatChoisie(),this.nombreVoisins,ruleNumber);
            // this.grid = new Grille(2, 8, new EtatCellule("FEU"));
            System.out.println("Création de l'automate " + name + " en mode prédéfini");
            //Grille grille = new Grille(2, 8, etatFeu);
            this.grid = new Grille(1, 8, etat1D);
            this.gridCopy = new Grille(grid);

            System.out.println(grid);
            System.out.print(gridCopy);
            System.out.println(grid.getCellules());
            //we quit the program
            System.exit(0);
        }
        else {
            System.out.println("Erreur : nom d'automate inconnu...");
        }
    }





    // constructeur en mode custom,
    public Automate(int dimension, int nombreCellules, String name, LocalRule LR) {
        // this.grid = new Grille(dimension, nombreCellules, new EtatCellule(name));
        // this.lr = LR; a rajouter quand la classe LocalRule sera créée
        System.out.println("Création de l'automate " + name + " en mode custom");
        System.exit(0);
    }

    /**
     * Trouve l'etat suivant selon la configuration de l'etat de la cellule et de ces voisines
     * @param Configuration String qui contient le "voisinage" (etat cellule ; etat cellules voisines)
     * @retun String donnant l'etat suivant
     * */
    public String getEtatSuivantCellule(String Configuration){
        String[] listeTemp = Configuration.split(";");
        int nbReeldeCellules = listeTemp.length;
        if(nbReeldeCellules-1 < this.nombreVoisins){
            while(nbReeldeCellules-1 < this.nombreVoisins){
                Configuration=Configuration+this.etatCellules.getEtatByIndex(0)+";";
                listeTemp = Configuration.split(";");
                nbReeldeCellules = listeTemp.length;
            }
        }

        String etatSuivant= this.localRule.getEtatSuivant(Configuration);

        return etatSuivant;
    }

    /**
     * Calcule le nouveau etat de la grille selon l'etat actuel (avec localRule)
     * a la fin la grille de l'objet sera mise a jour
     */
    public void miseAJour(){
        //copie Grille gridCopie;
        this.gridCopy.copieEtatCellules(this.grid);
        //Selon de nombre de voisins

        // recupere etat de la cellule et etat cellules voisines
        // calcule l'etat suivant et le stoque dans la copie
        // remplace this.grid par la copie et libere les stocage inutil

    }


}

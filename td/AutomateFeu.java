package AutomatesCellulaires.td;

public class AutomateFeu extends Automate{

    public AutomateFeu(String name, Integer nbVoisins, Integer ruleNumber) {
        super(name, nbVoisins, ruleNumber);
    }

    /**
     * Constructeur générique de AutomateFEU
     */
    /*public AutomateFeu(LocalRule localRule, Integer nombreVoisins, EtatCellule etatCellules, Integer nbCol, Integer nbLigne) {
        if(nbLigne==1){
            this.grid = new Grille(1,nbCol,nbLigne,etatCellules);
        }else{
            this.grid = new Grille(2,nbCol,nbLigne,etatCellules);
        }
        //this.nbCol = nbCol;
        //this.nbLigne = nbLigne;

        this.localRule = localRule;
        this.nombreVoisins = nombreVoisins;
        this.etatCellules = etatCellules;
        //this.gridCopy = new Grille(grid);

    }
    
     */
}

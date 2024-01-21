package AutomatesCellulaires.td;

import java.util.Random;

public class AutomateFeu extends Automate{

    public String directionVent;
    public double forceVent;
    public double pProbaPropagationFeu;
    public double qProbaCombustion;


    /**
     * Constructeur de AutomateFeu
     * @param nombreDeVoisins
     * @param nbCol
     * @param nbLigne
     * @param densiteForet
     * @param p_directionVent
     * @param p_forceVent
     * @param p_ProbaPropagationFeu
     * @param q_ProbaCombustion
     */
    public AutomateFeu(Integer nombreDeVoisins, Integer nbCol, Integer nbLigne , double densiteForet, String p_directionVent, double p_forceVent, double p_ProbaPropagationFeu, double q_ProbaCombustion ) {
        super( nbCol, nbLigne);
        this.etatCellules = new EtatCellule("FEU");
        //nombreDeVoisins est obligatoirement 4, 6 ou  8
        if( nombreDeVoisins != 4 && nombreDeVoisins != 6 && nombreDeVoisins != 8){
            nombreDeVoisins = 4;
        }
        this.nombreVoisins = nombreDeVoisins;
        this.localRule = new LocalRule(etatCellules.getEtatChoisie(),nombreVoisins,"FEU");

        if(this.nbLigne==1){
            this.grid = new Grille(1,this.nbCol,this.nbLigne,etatCellules);
        }else{
            this.grid = new Grille(2,this.nbCol,this.nbLigne,etatCellules);
        }

        //met toutes les case à vide
        for (int i = 0; i < this.grid.nbLigne; i++) {
            for (int j = 0; j < this.grid.nbCol; j++) {
                grid.setValeurCellule(i,j,etatCellules.getEtatByIndex(0));
            }
        }


        //densiteForet bornée à 1
        if(densiteForet>1){
            densiteForet = 1;
        }
        //par defaut 0.2
        if(densiteForet<0){
            densiteForet = 0.2;
        }

        //prise en compte de la densité de la forêt

        int nbTotalCellules = grid.nbLigne * grid.nbCol;
        int nbArbres = (int) (nbTotalCellules * densiteForet);
        if(nbArbres>nbTotalCellules){ nbArbres=nbTotalCellules; }
        Random r = new Random();
        int X,Y;
        int a = 0;
        while( a < nbArbres ){
            X = r.nextInt(0, grid.nbLigne-1);
            Y = r.nextInt(0,grid.nbCol-1);

            if( !grid.getValeurCellule(X,Y).equals(etatCellules.getEtatByIndex(1)) ){
                grid.setValeurCellule(X,Y,etatCellules.getEtatByIndex(1));
                a++;
            }

        }

        System.out.println(grid);

        ///GRID COPY



        /* DENSITE init foret
        Force et dir du vent

        cellule forˆet peut passer en feu avec une probabilit´e q mˆeme si aucune cellule voisine n’est en feu (i.e. avec une
        probabilit´e q + k ∗ p avec k le nombre de voisins en feu).

        */





    }

}

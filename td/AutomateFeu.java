package AutomatesCellulaires.td;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class AutomateFeu extends Automate{

    public String directionVent;
    public double forceVent;
    public double pProbaPropagationFeu;
    public double qProbaCombustion;


    /**
     * Constructeur de AutomateFeu
     * @param nombreDeVoisins int nb de voisins (4,6 ou 8)
     * @param nbCol nb de col (3 min)
     * @param nbLigne nb de lignes (3min)
     * @param densiteForet double pour initialiser la grille
     * @param p_directionVent string avec la direction du vent
     * @param p_forceVent double force vent f
     * @param p_ProbaPropagationFeu double probabilité de propagation du feu
     * @param q_ProbaCombustion double probabilité autocombustion du feu (forcement <1)
     */
    public AutomateFeu(Integer nombreDeVoisins, Integer nbCol, Integer nbLigne , double densiteForet, String p_directionVent, double p_forceVent, double p_ProbaPropagationFeu, double q_ProbaCombustion, int nbEnFeu ) {
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

        int f = 0;
        if(nbEnFeu<0){nbEnFeu = 0;}
        while( f < nbEnFeu ){
            X = r.nextInt(0, grid.nbLigne-1);
            Y = r.nextInt(0,grid.nbCol-1);

            if( !grid.getValeurCellule(X,Y).equals(etatCellules.getEtatByIndex(2)) ){
                grid.setValeurCellule(X,Y,etatCellules.getEtatByIndex(2));
                f++;
            }
        }

        System.out.println(grid);
        this.gridCopy = new Grille(grid);

        this.forceVent = p_forceVent;

        String[] directions = {"Nord",  "Est",  "Sud",  "Ouest","Nord-Est","Sud-Est", "Nord-Ouest","Sud-Ouest"};
        String[] directions_6 = {  "Est",  "Ouest","Nord-Est","Sud-Est", "Nord-Ouest","Sud-Ouest"};
        String[] directions_4 = {"Nord",  "Est",  "Sud",  "Ouest"};
        if(Arrays.asList(directions).contains(p_directionVent)){
            if(this.nombreVoisins==8){
                this.directionVent=p_directionVent;
            }

            if(this.nombreVoisins==6) {
                if (Arrays.asList(directions_6).contains(p_directionVent)) {
                    this.directionVent = p_directionVent;
                } else {
                    this.directionVent = "Est";
                    this.forceVent = 0;
                }
            }
            if(this.nombreVoisins==4) {
                if (Arrays.asList(directions_4).contains(p_directionVent)) {
                    this.directionVent = p_directionVent;
                } else {
                    this.directionVent = "Est";
                    this.forceVent = 0;
                }
            }
        }

        if(p_ProbaPropagationFeu<=1 && p_ProbaPropagationFeu>0){
            this.pProbaPropagationFeu=p_ProbaPropagationFeu;
        }else{
            this.pProbaPropagationFeu=1;
        }
        if(q_ProbaCombustion<1 && q_ProbaCombustion>0){
            this.qProbaCombustion=q_ProbaCombustion;
        }else{
            this.qProbaCombustion=0;
        }

        /*
        Force et dir du vent

        cellule forˆet peut passer en feu avec une probabilit´e q mˆeme si aucune cellule voisine n’est en feu (i.e. avec une
        probabilit´e q + k ∗ p avec k le nombre de voisins en feu).

        */

    }

    private int getIndexesVentEtOpposee(){
        if(this.nombreVoisins==8){
            int[][] directionsCard = { { 0, 1 }, { 0, -1 }, { 1, 0 }, { -1, 0 }, { -1, -1 }, { 1, -1 }, { -1, 1 },  { 1, 1 } };
            String[] directions = { "Est",  "Ouest" , "Sud", "Nord", "Nord-Ouest" , "Sud-Ouest" , "Nord-Est" , "Sud-Est"};
            String[] directions_6 = { "Est",  "Ouest" , "Sud-Est" , "Nord-Est",  "Nord-Ouest" , "Sud-Ouest"  };
            String[] directions_4 = {"Est",  "Ouest" , "Sud", "Nord",};

            Map<String, Integer> listeClesValeursIndexVent8 = new HashMap<>(); ;
            int compteur = 0;
            for (String dir : directions) {
                listeClesValeursIndexVent8.put(dir, compteur);

            }
        }
        return -1;
    }

    private double calculeForceVent(String configVoisinage){
        int[][] directionsCard = { { 0, 1 }, { 0, -1 }, { 1, 0 }, { -1, 0 }, { -1, -1 }, { 1, -1 }, { -1, 1 },  { 1, 1 } };
        String[] directions = { "Est",  "Ouest" , "Sud", "Nord", "Nord-Ouest" , "Sud-Ouest" , "Nord-Est" , "Sud-Est"};
        String[] directions_6 = { "Est",  "Ouest" , "Sud-Est" , "Nord-Est",  "Nord-Ouest" , "Sud-Ouest"  };
        String[] directions_4 = {"Est",  "Ouest" , "Sud", "Nord"};

        /*if(this.nombreVoisins==8){
            //this.directionVent=p_directionVent;
            //vent
            //configurationeVoisines

        }

        if(this.nombreVoisins==6) {
            if (Arrays.asList(directions_6).contains(p_directionVent)) {
                this.directionVent = p_directionVent;
            } else {
                this.directionVent = "Est";
                this.forceVent = 0;
            }
        }
        if(this.nombreVoisins==4) {
            if (Arrays.asList(directions_4).contains(p_directionVent)) {
                this.directionVent = p_directionVent;
            } else {
                this.directionVent = "Est";
                this.forceVent = 0;
            }
        }*/
        return -1.0;
    }

    @Override public void miseAJour() {
        // copie Grille gridCopie;
        this.gridCopy.copieEtatCellules(this.grid);
        int nbVoisinsEnFeu;
        String etatCelFeu = etatCellules.getEtatByIndex(2);

        Random probaGeneree = new Random();
        int valProba;
        double ceuil;

        // pour parcouir la grille
        for(int i=0; i<this.gridCopy.nbLigne; i++){

            for( int j = 0; j < this.gridCopy.nbCol; j++){

                // recupere etat de la cellule et etat cellules voisines
                String etatCellule = this.gridCopy.getValeurCellule(i, j);

                if( etatCellule.equals(etatCellules.getEtatByIndex(1))){
                    String configurationeVoisines = this.getConfigVoisin(i,j);
                    String[] etatVoisines = configurationeVoisines.split(";");

                    //q + k ∗ p etat feu avec k=nbde vois en feu si etat actuel = arbre
                    nbVoisinsEnFeu = 0;

                    for ( int v=1 ; v< this.nombreVoisins+1 ; v++ ) {
                        if (etatVoisines[v].equals(etatCelFeu)) {
                            nbVoisinsEnFeu++;
                        }
                    }

                    valProba = probaGeneree.nextInt(1, 100);
                    //prise en compte du vent et sa force

                    int forceVentAppliquee;
                    if(this.forceVent!=0){
                        //calcule force vent

                    }

                    ceuil = (qProbaCombustion + nbVoisinsEnFeu*pProbaPropagationFeu)*100;

                    if(valProba<ceuil){
                        this.grid.setValeurCellule(i, j, etatCelFeu);
                    }
                }else{
                    String etatCellule_a_calculer = this.gridCopy.getValeurCellule(i, j);
                    String etatVoisines = getConfigVoisin(i,j);

                    // calcule l'etat suivant et le stoque dans la copie
                    String etatSuivant = getEtatSuivantCellule(etatVoisines);

                    this.grid.setValeurCellule(i, j, etatSuivant);

                }


                // calcule l'etat suivant et le stoque dans la copie
                //String etatSuivant = getEtatSuivantCellule(etatVoisines);

                //this.grid.setValeurCellule(i, j, etatSuivant);

            }

        }

    }

}

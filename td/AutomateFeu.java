package AutomatesCellulaires.td;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class AutomateFeu extends Automate{

    //le vent vien de cette direction
    public String directionVent;
    public double forceVent;
    public double pProbaPropagationFeu;
    public double qProbaCombustion;

    //pour la gestion du vent  (+f)
    public Map<String, Integer> listeClesValeursIndexVent;
    //pour la gestion du vent opposé (-f)
    public Map<String, Integer> listeClesValeursIndexVentOppose;

    // pour l'affichage avec couleurs
    public static final String RESET = "\u001B[0m";
    public static final String VERT = "\u001B[32m";
    public static final String ROUGE = "\u001B[31m";
    public static final String NOIR = "\u001B[90m";
    public static final String BLANC = "\u001B[37m";




    /**
     * Constructeur de AutomateFeu Initialise la grille selon les parametres et tout les champs
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

        //System.out.println(this);
        this.gridCopy = new Grille(grid);

        this.forceVent = p_forceVent;

        String[] directions = {"Nord",  "Est",  "Sud",  "Ouest","Nord-Est","Sud-Est", "Nord-Ouest","Sud-Ouest"};
        String[] directions_6 = { "Est",  "Ouest","Nord-Est","Sud-Est", "Nord-Ouest","Sud-Ouest"};
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

        init_map_vent_et_oppose();
        /*
        Force et dir du vent

        cellule forˆet peut passer en feu avec une probabilit´e q mˆeme si aucune cellule voisine n’est en feu (i.e. avec une
        probabilit´e q + k ∗ p avec k le nombre de voisins en feu).

        */

    }

    /**
     * initialise les Map qui vont etre utilisées pour tester si il y a feu
     * dans la dirrection d'ou vient le vent ou la dirrection opposée
     */
    private void init_map_vent_et_oppose(){
        String[] directions = {"CelActuelle","Est", "Ouest", "Sud","Nord", "Nord-Ouest", "Sud-Ouest","Nord-Est","Sud-Est"};
        String[] directionsOPPOSE = {"CelActuelle", "Ouest","Est","Nord", "Sud","Sud-Est", "Nord-Est", "Sud-Ouest","Nord-Ouest"};

        String[] directions_6 = {"CelActuelle","Est", "Ouest","Sud-Est","Nord-Est", "Nord-Ouest", "Sud-Ouest"};
        String[] directions_6OPPOSE = {"CelActuelle", "Ouest","Est","Nord-Est", "Sud-Ouest","Sud-Est", "Nord-Ouest"};

        String[] directions_4 = {"CelActuelle","Est",  "Ouest", "Sud", "Nord"};
        String[] directions_4OPPOSE = {"CelActuelle",  "Ouest","Est", "Nord", "Sud"};

        String[] directionsCorrespondantes;
        String[] directionsOPPOSEESCorrespondantes;

        switch (this.nombreVoisins){
            case 4: directionsCorrespondantes=directions_4;
                    directionsOPPOSEESCorrespondantes=directions_4OPPOSE;
                break;
            case 6: directionsCorrespondantes=directions_6;
                    directionsOPPOSEESCorrespondantes=directions_6OPPOSE;
                break;
            default:
                directionsCorrespondantes=directions;
                directionsOPPOSEESCorrespondantes=directionsOPPOSE;
        }


        this.listeClesValeursIndexVent= new HashMap<>();
        this.listeClesValeursIndexVentOppose = new HashMap<>();
        int compteur = 0;
        for (String dir : directions) {
            listeClesValeursIndexVent.put(dir, compteur);
            compteur++;
        }
        compteur = 0;
        for (String dirOPOSEE : directionsOPPOSE) {
            listeClesValeursIndexVentOppose.put(dirOPOSEE, compteur);
            compteur++;
        }
    }

    /**
     * Calcule la force du vent qui est appliqué
     * @param configVoisinage String contenat tout le voisinage (etat cellule reference a l'indice 0 )
     * @return double le resultat de la force effective du vent
     */
    private double getForceVentCalculee(String configVoisinage){
        //si Feu en index donne par dir alors +forceVent si Feu dans dir oppo alors -force vent
        Integer indexVent = listeClesValeursIndexVent.get(this.directionVent);
        Integer indexVentOppose = listeClesValeursIndexVentOppose.get(this.directionVent);
        double resultatVent = 0;
        String[] etatVoisines = configVoisinage.split(";");
        if(indexVent!=null){
            if(indexVent<nombreVoisins+1){
                if(etatVoisines[indexVent].equals(etatCellules.getEtatByIndex(2))){
                    resultatVent = this.forceVent;
                }
            }
        }
        if(indexVentOppose!=null){
            if(indexVentOppose<nombreVoisins+1){
                if(etatVoisines[indexVentOppose].equals(etatCellules.getEtatByIndex(2))){
                    resultatVent = resultatVent - this.forceVent;
                }
            }
        }

        return resultatVent;
    }

    /**
     * Effectue la mise a jour de la grille selon les parametres de l'automate feu en
     * prenannt en compte les propabilites
     */
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

                    double forceVentAppliquee = 0;
                    if(this.forceVent!=0){
                        //calcule force vent
                        forceVentAppliquee = getForceVentCalculee(configurationeVoisines);
                    }

                    ceuil = (qProbaCombustion + nbVoisinsEnFeu*pProbaPropagationFeu + forceVentAppliquee)*100;

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

    /**
     * Renvois une chaine de caracteres avec couleur et caracter corespondant
     * @param e String contenu cellule qu'on veu afficher
     * @return String
     */
    public String cellToStringCouleur(String e){
        /*String v = this.etatCellules.getEtatByIndex(0), a = this.etatCellules.getEtatByIndex(1);
        String f = this.etatCellules.getEtatByIndex(2), c = this.etatCellules.getEtatByIndex(3);*/

        switch (e){
            case "VIDE": return BLANC + "0" + RESET ;
            case "ARBRE": return VERT + "T" + RESET;
            case "FEU": return ROUGE + "W" + RESET;
            default: return NOIR + "." + RESET;
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
                grilleString += esp + cellToStringCouleur(this.grid.getValeurCellule(i,j));
                grilleString += diff;
                //grilleString += " \t";
            }
            grilleString += "\n";
        }
        grilleString += "\n---------------------------------------\n";

        return grilleString;
    }
}
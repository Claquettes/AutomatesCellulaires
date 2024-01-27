package AutomatesCellulaires.td;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * Classe AutomateFeu
 * Cette classe est un automate qui simule la propagation du feu dans une foret
 * Elle herite de la classe Automate
 * Elle contient les parametres de l'automate et les methodes pour le faire
 * fonctionner
 * 
 * @see Automate
 */

public class AutomateFeu extends Automate {

    // le vent viens de cette direction
    public String directionVent;
    public double forceVent;
    public double pProbaPropagationFeu;
    public double qProbaCombustion;

    // pour la gestion du vent (+f)
    public Map<String, Integer> listeClesValeursIndexVent;
    // pour la gestion du vent opposé (-f)
    public Map<String, Integer> listeClesValeursIndexVentOppose;

    // pour l'affichage avec couleurs
    public static final String RESET = "\u001B[0m";
    public static final String VERT = "\u001B[32m";
    public static final String ROUGE = "\u001B[31m";
    public static final String NOIR = "\u001B[90m";
    public static final String BLANC = "\u001B[37m";

    /**
     * Constructor for AutomateFeu. Initializes the grid according to the parameters
     * and all fields.
     * 
     * @param nombreDeVoisins       int number of neighbors (4,6 or 8)
     * @param nbCol                 number of columns (minimum 3)
     * @param nbLigne               number of lines (minimum 3)
     * @param densiteForet          double to initialize the grid
     * @param p_directionVent       string with the wind direction
     * @param p_forceVent           double wind force
     * @param p_ProbaPropagationFeu double probability of fire propagation
     * @param q_ProbaCombustion     double probability of fire self-combustion (must
     *                              be <1)
     */
    public AutomateFeu(Integer nombreDeVoisins, Integer nbCol, Integer nbLigne, double densiteForet,
            String p_directionVent, double p_forceVent, double p_ProbaPropagationFeu, double q_ProbaCombustion,
            int nbEnFeu) {
        super(nbCol, nbLigne);
        this.etatCellules = new EtatCellule("FEU");
        // nombreDeVoisins est obligatoirement 4, 6 ou 8
        if (nombreDeVoisins != 4 && nombreDeVoisins != 6 && nombreDeVoisins != 8) {
            nombreDeVoisins = 4;
        }
        this.nombreVoisins = nombreDeVoisins;
        this.localRule = new LocalRule(etatCellules.getEtatChoisie(), nombreVoisins, "FEU");

        if (this.nbLigne == 1) {
            this.grid = new Grille(1, this.nbCol, this.nbLigne, etatCellules);
        } else {
            this.grid = new Grille(2, this.nbCol, this.nbLigne, etatCellules);
        }
        // met a "vide"
        met_toutes_cells_a(etatCellules.getEtatByIndex(0));

        // densiteForet bornée à 1
        if (densiteForet > 1) {
            densiteForet = 1;
        }
        // par defaut 0.2
        if (densiteForet < 0) {
            densiteForet = 0.2;
        }

        // prise en compte de la densité de la forêt

        int nbTotalCellules = grid.nbLine * grid.nbCol;
        int nbArbres = (int) (nbTotalCellules * densiteForet);

        met_arbres_avec_densite(densiteForet);

        Random r = new Random();
        int X, Y;

        int f = 0;
        if (nbEnFeu < 0) {
            nbEnFeu = 0;
        }
        while (f < nbEnFeu) {
            X = r.nextInt(0, grid.nbLine);
            Y = r.nextInt(0, grid.nbCol);

            if (!grid.getValeurCellule(X, Y).equals(etatCellules.getEtatByIndex(2))) {
                grid.setValeurCellule(X, Y, etatCellules.getEtatByIndex(2));
                f++;
            }
        }

        // System.out.println(this);
        this.gridCopy = new Grille(grid);

        this.forceVent = p_forceVent;

        String[] directions = { "Nord", "Est", "Sud", "Ouest", "Nord-Est", "Sud-Est", "Nord-Ouest", "Sud-Ouest" };
        String[] directions_6 = { "Est", "Ouest", "Nord-Est", "Sud-Est", "Nord-Ouest", "Sud-Ouest" };
        String[] directions_4 = { "Nord", "Est", "Sud", "Ouest" };
        if (Arrays.asList(directions).contains(p_directionVent)) {
            if (this.nombreVoisins == 8) {
                this.directionVent = p_directionVent;
            }

            if (this.nombreVoisins == 6) {
                if (Arrays.asList(directions_6).contains(p_directionVent)) {
                    this.directionVent = p_directionVent;
                } else {
                    this.directionVent = "Est";
                    this.forceVent = 0;
                }
            }
            if (this.nombreVoisins == 4) {
                if (Arrays.asList(directions_4).contains(p_directionVent)) {
                    this.directionVent = p_directionVent;
                } else {
                    this.directionVent = "Est";
                    this.forceVent = 0;
                }
            }
        }

        if (p_ProbaPropagationFeu <= 1 && p_ProbaPropagationFeu > 0) {
            this.pProbaPropagationFeu = p_ProbaPropagationFeu;
        } else {
            this.pProbaPropagationFeu = 1;
        }
        if (q_ProbaCombustion < 1 && q_ProbaCombustion > 0) {
            this.qProbaCombustion = q_ProbaCombustion;
        } else {
            this.qProbaCombustion = 0;
        }

        init_map_vent_et_oppose();
        /*
         * Force et dir du vent
         * 
         * cellule forˆet peut passer en feu avec une probabilit´e q mˆeme si aucune
         * cellule voisine n’est en feu (i.e. avec une
         * probabilit´e q + k ∗ p avec k le nombre de voisins en feu).
         * 
         */

    }

    /**
     * Sets all cells in the grid to the state passed as a parameter
     * 
     * @param state string
     */

    public void met_toutes_cells_a(String etat) {
        // met toutes les case à vide
        for (int i = 0; i < this.grid.nbLine; i++) {
            for (int j = 0; j < this.grid.nbCol; j++) {
                grid.setValeurCellule(i, j, etat);
            }
        }
    }

    /**
     * Puts trees in the grid to reach the given density
     * 
     * @param forestDensity double between 0 and 1
     */
    public void met_arbres_avec_densite(double densiteForet) {
        if (densiteForet < 0) {
            densiteForet = 0;
            return;
        }
        if (densiteForet > 1) {
            densiteForet = 1;
        }

        int nbTotalCellules = grid.nbLine * grid.nbCol;
        int nbArbres = (int) (nbTotalCellules * densiteForet);
        Random r = new Random();
        int X, Y;

        if (densiteForet <= 0.5) {
            int a = 0;
            while (a < nbArbres) {
                X = r.nextInt(0, grid.nbLine - 1);
                Y = r.nextInt(0, grid.nbCol - 1);

                if (!grid.getValeurCellule(X, Y).equals(etatCellules.getEtatByIndex(1))) {
                    grid.setValeurCellule(X, Y, etatCellules.getEtatByIndex(1));
                    a++;
                }

            }
        }

        if (densiteForet > 0.5) {
            met_toutes_cells_a(this.etatCellules.getEtatByIndex(1));
            int a_enlev = 0;
            int nbArbres_a_enlev = (int) (nbTotalCellules * (1 - densiteForet));

            while (a_enlev < nbArbres_a_enlev) {
                X = r.nextInt(0, grid.nbLine);
                Y = r.nextInt(0, grid.nbCol);

                if (!grid.getValeurCellule(X, Y).equals(etatCellules.getEtatByIndex(0))) {
                    grid.setValeurCellule(X, Y, etatCellules.getEtatByIndex(0));
                    a_enlev++;
                }

            }
        }

    }

    /**
     * Initializes the Maps that will be used to test if there is fire
     * in the direction from where the wind comes or the opposite direction
     */
    private void init_map_vent_et_oppose() {
        String[] directions = { "CelActuelle", "Est", "Ouest", "Sud", "Nord", "Nord-Ouest", "Sud-Ouest", "Nord-Est",
                "Sud-Est" };
        String[] directionsOPPOSE = { "CelActuelle", "Ouest", "Est", "Nord", "Sud", "Sud-Est", "Nord-Est", "Sud-Ouest",
                "Nord-Ouest" };

        String[] directions_6 = { "CelActuelle", "Est", "Ouest", "Sud-Est", "Nord-Est", "Nord-Ouest", "Sud-Ouest" };
        String[] directions_6OPPOSE = { "CelActuelle", "Ouest", "Est", "Nord-Est", "Sud-Ouest", "Sud-Est",
                "Nord-Ouest" };

        String[] directions_4 = { "CelActuelle", "Est", "Ouest", "Sud", "Nord" };
        String[] directions_4OPPOSE = { "CelActuelle", "Ouest", "Est", "Nord", "Sud" };

        String[] directionsCorrespondantes;
        String[] directionsOPPOSEESCorrespondantes;

        switch (this.nombreVoisins) {
            case 4:
                directionsCorrespondantes = directions_4;
                directionsOPPOSEESCorrespondantes = directions_4OPPOSE;
                break;
            case 6:
                directionsCorrespondantes = directions_6;
                directionsOPPOSEESCorrespondantes = directions_6OPPOSE;
                break;
            default:
                directionsCorrespondantes = directions;
                directionsOPPOSEESCorrespondantes = directionsOPPOSE;
        }

        this.listeClesValeursIndexVent = new HashMap<>();
        this.listeClesValeursIndexVentOppose = new HashMap<>();
        int compteur = 0;
        for (String dir : directionsCorrespondantes) {
            listeClesValeursIndexVent.put(dir, compteur);
            compteur++;
        }
        compteur = 0;
        for (String dirOPOSEE : directionsOPPOSEESCorrespondantes) {
            listeClesValeursIndexVentOppose.put(dirOPOSEE, compteur);
            compteur++;
        }
    }

    /**
     * Calculates the force of the wind that is applied
     * 
     * @param neighborhoodConfig String containing all the neighborhood (state cell
     *                           reference at index 0 )
     * @return double the result of the effective wind force
     */
    private double getForceVentCalculee(String configVoisinage) {
        // si Feu en index donne par dir alors +forceVent si Feu dans dir oppo alors
        // -force vent
        Integer indexVent = listeClesValeursIndexVent.get(this.directionVent);
        Integer indexVentOppose = listeClesValeursIndexVentOppose.get(this.directionVent);
        double resultatVent = 0;
        String[] etatVoisines = configVoisinage.split(";");
        if (indexVent != null) {
            if (indexVent < nombreVoisins + 1) {
                if (etatVoisines[indexVent].equals(etatCellules.getEtatByIndex(2))) {
                    resultatVent = this.forceVent;
                }
            }
        }
        if (indexVentOppose != null) {
            if (indexVentOppose < nombreVoisins + 1) {
                if (etatVoisines[indexVentOppose].equals(etatCellules.getEtatByIndex(2))) {
                    resultatVent = resultatVent - this.forceVent;
                }
            }
        }

        return resultatVent;
    }

    /**
     * Performs the update of the grid according to the parameters of the fire
     * automaton
     * taking into account the probabilities
     */
    @Override
    public void miseAJour() {
        // copie Grille gridCopie;
        this.gridCopy.copieEtatCellules(this.grid);
        int nbVoisinsEnFeu;
        String etatCelFeu = etatCellules.getEtatByIndex(2);

        Random probaGeneree = new Random();
        int valProba;
        double ceuil;

        // pour parcouir la grille
        for (int i = 0; i < this.gridCopy.nbLine; i++) {

            for (int j = 0; j < this.gridCopy.nbCol; j++) {

                // recupere etat de la cellule et etat cellules voisines
                String etatCellule = this.gridCopy.getValeurCellule(i, j);

                if (etatCellule.equals(etatCellules.getEtatByIndex(1))) {
                    String configurationeVoisines = this.getConfigVoisin(i, j);
                    String[] etatVoisines = configurationeVoisines.split(";");

                    // q + k ∗ p etat feu avec k=nbde vois en feu si etat actuel = arbre
                    nbVoisinsEnFeu = 0;

                    for (int v = 1; v < this.nombreVoisins + 1; v++) {
                        if (etatVoisines[v].equals(etatCelFeu)) {
                            nbVoisinsEnFeu++;
                        }
                    }

                    valProba = probaGeneree.nextInt(1, 100);
                    // prise en compte du vent et sa force

                    double forceVentAppliquee = 0;
                    if (this.forceVent != 0) {
                        // calcule force vent
                        forceVentAppliquee = getForceVentCalculee(configurationeVoisines);
                    }

                    ceuil = (qProbaCombustion + nbVoisinsEnFeu * pProbaPropagationFeu + forceVentAppliquee) * 100;

                    if (valProba < ceuil) {
                        this.grid.setValeurCellule(i, j, etatCelFeu);
                    }
                } else {
                    String etatCellule_a_calculer = this.gridCopy.getValeurCellule(i, j);
                    String etatVoisines = getConfigVoisin(i, j);

                    // calcule l'etat suivant et le stoque dans la copie
                    String etatSuivant = getEtatSuivantCellule(etatVoisines);

                    this.grid.setValeurCellule(i, j, etatSuivant);

                }

                // calcule l'etat suivant et le stoque dans la copie
                // String etatSuivant = getEtatSuivantCellule(etatVoisines);

                // this.grid.setValeurCellule(i, j, etatSuivant);

            }

        }

    }

    /**
     * Returns a string with corresponding color and character
     * 
     * @param e String cell content we want to display
     * @return String
     */
    public String cellToStringCouleur(String e) {

        switch (e) {
            case "VIDE":
                return BLANC + "0" + RESET;
            case "ARBRE":
                return VERT + "T" + RESET;
            case "FEU":
                return ROUGE + "W" + RESET;
            default:
                return NOIR + "." + RESET;
        }
    }

    @Override
    /**
     * Returns a string with the grid
     */
    public String toString() {
        String esp = "  ";
        String diff = " \t";
        if (nombreVoisins == 6) {
            diff = "  ";
        }

        String grilleString = "---------------------------------------\n";
        for (int i = 0; i < nbLigne; i++) {
            esp = "  ";
            // pour l'affichage hexagonal
            if (nombreVoisins == 6 && (i % 2 == 0)) {
                grilleString += "   ";
                esp = "  ";
            }
            if (nombreVoisins != 6) {
                esp = "";
            }

            for (int j = 0; j < nbCol; j++) {
                // grilleString += this.grid.getValeurCellule(i,j);
                grilleString += esp + cellToStringCouleur(this.grid.getValeurCellule(i, j));
                grilleString += diff;
                // grilleString += " \t";
            }
            grilleString += "\n";
        }
        grilleString += "\n---------------------------------------\n";

        return grilleString;
    }
}

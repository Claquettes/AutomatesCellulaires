package AutomatesCellulaires.td;

import java.util.*;
import java.lang.Math;

/**
 * This class represents a Local Rule in an Automaton.
 * It has a map of keys and values representing the rule.
 */
public class LocalRule {

    Map<String, String> listeClesValeurs;

    /**
     * Generates all combinations of a given size from a list of values.
     * 
     * @param values             The list of values to generate combinations from.
     * @param combinationSize    The size of the combinations to generate.
     * @param sep                The separator to use in the combinations.
     * @param initialCombination The initial combination.
     * @return A list of all generated combinations.
     */
    public static List<String> genererToutesLesCombinaisons(List<String> valeurs, int tailleCombinaison, String sep,
            String CombinaisonInit) {
        List<String> resultats = new ArrayList<>();
        genererCombinaisons(valeurs, tailleCombinaison, CombinaisonInit, resultats, sep);
        return resultats;
    }

    /**
     * Creates combinations of desired length with the elements contained in the
     * values list
     * result in parameter/result named results
     * 
     * @param values             List String which contains the strings to combine
     * @param combinationSize    int length of desired combinations
     * @param currentCombination contains the combination under construction
     * @param results            accumulator of type List String containing the
     *                           different combinations
     * @param sep                String separator of elements in combinations
     */
    private static void genererCombinaisons(List<String> valeurs, int tailleCombinaison, String combinaisonActuelle,
            List<String> resultats, String sep) {
        if (tailleCombinaison == 0) {
            resultats.add(combinaisonActuelle);
            return;
        }

        for (int i = 0; i < valeurs.size(); i++) {
            String valeurChoisie = valeurs.get(i);
            genererCombinaisons(valeurs, tailleCombinaison - 1, combinaisonActuelle + valeurChoisie + sep, resultats,
                    sep);
        }
    }

    /**
     * Puts semicolons after each char of the string containing a number in binary
     * 
     * @param binary string containing a number in binary
     * @return Binary with String List containing the configurations that must
     *         be set to 1
     */

    private static String BinToConf(String Binaire) {
        String AvecPointVirgule = "";
        for (int i = 0; i < Binaire.length(); i++) {
            AvecPointVirgule = AvecPointVirgule + Binaire.charAt(i) + ";";
        }
        // System.out.print(" str bin ." + Binaire + ". result ." +AvecPointVirgule);
        return AvecPointVirgule;
    }

    /**
     * Calculates the key/configurations for which the value must be 1 for
     * rules named by a number
     * 
     * @param binaryRule string containing a number in binary
     * @param numBits    number of characters for the binary rule
     * @return list String List containing the configurations that must be
     *         set to 1
     */
    private static List<String> ConfigurationsDonnant1(String regleBinaire, int nbBits) {

        List<String> resultatsA1 = new ArrayList<>();
        int facteur = 0;
        for (int i = regleBinaire.length() - 1; i >= 0; i--) {
            char charbitActuel = regleBinaire.charAt(i);
            if (charbitActuel == '1') {
                String factBin = Integer.toBinaryString(facteur);
                while (factBin.length() < nbBits) {
                    factBin = "0" + factBin;
                }
                String bin = BinToConf(factBin);
                resultatsA1.add(bin);
            }
            facteur = facteur + 1;
        }
        return resultatsA1;
    }

    /**
     * Creates a rule for the FEU Automate.
     * 
     * @param etatsPossibles
     * @param nbDeVoisins
     * @return
     */
    private static Map<String, String> CreationRegleFEU(ArrayList<String> etatsPossibles, int nbDeVoisins) {
        List<String> toutesLesCombinaisons = genererToutesLesCombinaisons(etatsPossibles, nbDeVoisins + 1, ";", "");
        Map<String, String> resCleValeurs = new HashMap<>();
        for (String combinaison : toutesLesCombinaisons) {
            String EtatPremiereCellule = combinaison.split(";")[0];
            switch (EtatPremiereCellule) {
                case "FEU":
                    resCleValeurs.put(combinaison, "CENDRE");
                    break;
                case "CENDRE":
                    resCleValeurs.put(combinaison, "CENDRE");
                    break;
                case "VIDE":
                    resCleValeurs.put(combinaison, "VIDE");
                    break;
                case "ARBRE":
                    boolean estContenue = combinaison.contains("FEU");
                    if (estContenue) {
                        resCleValeurs.put(combinaison, "FEU");
                    } else {
                        resCleValeurs.put(combinaison, EtatPremiereCellule);
                    }
                    break;
                default:
                    resCleValeurs.put(combinaison, "eindef");
            }

        }
        return resCleValeurs;
    }

    /**
     * Creates the HashMap for a local rule of type LIFE (game of life) with the
     * given parameters
     * 
     * @param possibleStates ArrayList string containing the possible states of a
     *                       cell
     * @param numNeighbors   int, number of neighbors per cell (here it must always
     *                       be 8 !!!!
     * @return keyValuesResult
     */
    private static Map<String, String> CreationRegleVIE(ArrayList<String> etatsPossibles, int nbDeVoisins) {

        nbDeVoisins = 8;
        String v = "1";
        String m = "0";
        int nbVivants;
        List<String> toutesLesCombinaisons = genererToutesLesCombinaisons(etatsPossibles, nbDeVoisins + 1, ";", "");
        Map<String, String> resCleValeurs = new HashMap<>();
        for (String combinaison : toutesLesCombinaisons) {
            String EtatPremiereCellule = combinaison.split(";")[0];
            String[] voisinage = combinaison.split(";");
            nbVivants = 0;
            for (int i = 1; i < nbDeVoisins + 1; i++) {
                if (voisinage[i].equals(v)) {
                    nbVivants++;
                }
            }
            if (nbVivants == 3 || (nbVivants == 2 && voisinage[0].equals(etatsPossibles.get(1)))) {
                resCleValeurs.put(combinaison, etatsPossibles.get(1)); // vivant
            } else {

                resCleValeurs.put(combinaison, etatsPossibles.get(0));
            }
        }
        return resCleValeurs;
    }

    /**
     * Creates the HashMap for a local rule of type MAJORITY with the given
     * parameters
     * 
     * @param possibleStates ArrayList string containing the possible states of a
     *                       cell
     * @param numNeighbors   int, number of neighbors per cell ALWAYS ODD !!!
     * @return keyValuesResult
     */

    private static Map<String, String> CreationRegleMAJORITE(ArrayList<String> etatsPossibles, int nbDeVoisins) {
        // ICI ON ASSUME que voisinage Q = {0, 1} OU que on a que deux etats
        String val0 = etatsPossibles.get(0);
        int nbVal0, nbVal1;
        String val1 = etatsPossibles.get(1);
        int nbVivants;
        List<String> toutesLesCombinaisons = genererToutesLesCombinaisons(etatsPossibles, nbDeVoisins + 1, ";", "");
        Map<String, String> resCleValeurs = new HashMap<>(); // on initialise la liste des clés valeurs
        for (String combinaison : toutesLesCombinaisons) {
            String EtatPremiereCellule = combinaison.split(";")[0];
            String[] voisinage = combinaison.split(";");

            nbVal0 = 0;
            nbVal1 = 0;
            // on ne prend pas en compte l'etat de la cellule actuelle
            for (int i = 1; i < nbDeVoisins + 1; i++) {
                if (voisinage[i].equals(val0)) {
                    nbVal0++;
                }
                if (voisinage[i].equals(val1)) {
                    nbVal1++;
                }
            }

            if (nbVal0 > nbVal1) {
                resCleValeurs.put(combinaison, etatsPossibles.get(0));
            } else {
                resCleValeurs.put(combinaison, etatsPossibles.get(1));
            }

        }
        return resCleValeurs;
    }

    /**
     * Constructor of LocalRule with name (String) of the Rule
     * 
     * @param possibleStates ArrayList string containing the possible states of a
     *                       cell
     * @param numNeighbors   int, number of neighbors per cell
     * @param ruleName       String containing the name of the Rule (for now
     *                       FIRE)
     */
    LocalRule(ArrayList<String> etatsPossibles, int nbDeVoisins, String NomRegle) {

        // etatsPossibles = ["FEU", "CENDRE", "VIDE", "ARBRE"] par exemple pour le feu
        // ensuite ca créé locale rule pour le feu ou pour ce qu'on veux
        switch (NomRegle) {
            case "FEU":
                listeClesValeurs = CreationRegleFEU(etatsPossibles, nbDeVoisins);
                break;
            case "VIE":
                listeClesValeurs = CreationRegleVIE(etatsPossibles, 8); // tjs 8 voisins
                break;
            case "MAJORITE":
                // nbDeVoisins TOUJOURS IMPAIR
                if (nbDeVoisins % 2 == 0) {
                    // ajout de 1 pour le faire impaire
                    nbDeVoisins++;
                }
                listeClesValeurs = CreationRegleMAJORITE(etatsPossibles, nbDeVoisins);
                break;
            default:
                System.out.println("Erreur : nom de regle inconnu...");

        }

    }

    /**
     * Constructor for the LocalRule class.
     * 
     * @param etatsPossibles
     * @param nbDeVoisins
     * @param NumeroRegle
     */
    LocalRule(ArrayList<String> etatsPossibles, int nbDeVoisins, int NumeroRegle) {
        String sep = ";";
        String regleEnBin = Integer.toBinaryString(NumeroRegle);
        double nbBitsVoisins = Math.pow(2, nbDeVoisins + 1);

        if (regleEnBin.length() != nbBitsVoisins) {
            if (regleEnBin.length() < nbBitsVoisins) {
                while (regleEnBin.length() < nbBitsVoisins) {
                    regleEnBin = "0" + regleEnBin;
                }
            } else {
                int longueur = regleEnBin.length();
                regleEnBin = regleEnBin.substring(longueur - (int) nbBitsVoisins, longueur);
            }
        }

        System.out.print("regle en binaire : " + regleEnBin + '\n');

        List<String> toutesLesCombinaisons = genererToutesLesCombinaisons(etatsPossibles, nbDeVoisins + 1, sep, "");
        listeClesValeurs = new HashMap<>();
        List<String> listeDesConfigurationsDonnann1 = ConfigurationsDonnant1(regleEnBin, nbDeVoisins + 1);
        for (String combinaison : toutesLesCombinaisons) {
            boolean estContenue = listeDesConfigurationsDonnann1.contains(combinaison);
            if (estContenue) {
                listeClesValeurs.put(combinaison, "1");
            } else {
                listeClesValeurs.put(combinaison, "0");

            }
        }
    }

    /**
     * Prints the rule.
     */
    public void afficher() {
        for (Map.Entry<String, String> entry : listeClesValeurs.entrySet()) {
            System.out.println("Clé : " + entry.getKey() + ", Valeur : " + entry.getValue());
        }
    }

    /**
     * Gets the next state of the Automaton.
     * 
     * @param Configuration
     * @return
     */
    public String getEtatSuivant(String Configuration) {

        return listeClesValeurs.get(Configuration);
    }
}

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
     * @param valeurs           The list of values to generate combinations from.
     * @param tailleCombinaison The size of the combinations to generate.
     * @param sep               The separator to use in the combinations.
     * @param CombinaisonInit   The initial combination.
     * @return A list of all generated combinations.
     */
    public static List<String> genererToutesLesCombinaisons(List<String> valeurs, int tailleCombinaison, String sep,
            String CombinaisonInit) {
        List<String> resultats = new ArrayList<>();
        genererCombinaisons(valeurs, tailleCombinaison, CombinaisonInit, resultats, sep);
        return resultats;
    }

    /**
     * Generates all combinations of a given size from a list of values.
     * 
     * @param valeurs
     * @param tailleCombinaison
     * @param combinaisonActuelle
     * @param resultats
     * @param sep
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
     * Transform a binary string into a string with spaces between each character.
     * 
     * @param Binaire
     * @return
     */
    private static String BinToConf(String Binaire) {
        String AvecEspaces = "";
        for (int i = 0; i < Binaire.length(); i++) {
            AvecEspaces = AvecEspaces + Binaire.charAt(i) + ";";
        }
        return AvecEspaces;
    }

    /**
     * Returns a list of all configurations that give 1 in the rule.
     * 
     * @param regleBinaire
     * @param nbBits
     * @return
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
     * Creates a rule for the GOL Automate.
     * 
     * @param etatsPossibles
     * @param nbDeVoisins
     * @return
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
            if (nbVivants == 3) {
                resCleValeurs.put(combinaison, etatsPossibles.get(1)); // vivant
            } else {
                resCleValeurs.put(combinaison, etatsPossibles.get(0));
            }
        }
        return resCleValeurs;
    }

    /**
     * Constructor for the LocalRule class.
     * 
     * @param etatsPossibles
     * @param nbDeVoisins
     * @param NomRegle
     */
    LocalRule(ArrayList<String> etatsPossibles, int nbDeVoisins, String NomRegle) {
        if (NomRegle == "FEU") {
            listeClesValeurs = CreationRegleFEU(etatsPossibles, nbDeVoisins);
        }
        if (NomRegle == "VIE") {
            listeClesValeurs = CreationRegleVIE(etatsPossibles, 3);
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

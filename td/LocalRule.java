package AutomatesCellulaires.td;

import java.util.*;
/*
EtatCellule etat1 = new EtatCellule("FEU");
int nbDeVoisins = 4;
LocalRule LocalRule1 = new LocalRule(etat1.getEtatChoisie(), nbDeVoisins,"FEU");
LocalRule1.afficher();
* */

public class LocalRule {

    Map<String, String> listeClesValeurs ;

    public static List<String> genererToutesLesCombinaisons(List<String> valeurs, int tailleCombinaison) {
        List<String> resultats = new ArrayList<>();
        genererCombinaisons(valeurs, tailleCombinaison, "", resultats);
        return resultats;
    }

    private static void genererCombinaisons(List<String> valeurs, int tailleCombinaison, String combinaisonActuelle, List<String> resultats) {
        String sep = ";";
        if (tailleCombinaison == 0) {
            // Ajouter la combinaison actuelle à la liste des résultats
            resultats.add(combinaisonActuelle);
            return;
        }

        for (int i = 0; i < valeurs.size(); i++) {
            // Choisir une valeur
            String valeurChoisie = valeurs.get(i);

            // Récursivement générer la suite de la combinaison avec la même taille
            genererCombinaisons(valeurs, tailleCombinaison - 1, combinaisonActuelle + valeurChoisie + sep, resultats);
        }
    }

    /**
     * met des escpaces entre chaque char et avant et apres du string contenant un binaire
     * @param Binaire string contenant un nb en binaire
     * @return Binaire avec des  String List contenant les configurations qui soivent être mises à 1
     **/
    private static String BinToConf (String Binaire){
        String AvecEspaces = " ";
        for (int i = 0; i < Binaire.length() ; i++) {
            AvecEspaces = AvecEspaces + Binaire.charAt(i) + " ";
        }
        //System.out.print(" str bin " + Binaire + " result " + AvecEspaces);
        return AvecEspaces;
    }

    /**
     * Calcule les clé/configurations pour les quelles la valeur doit être à 1 pour les regles només par un nombre
     * @param regleBinaire string contenant un nb en binaire
     * @return liste String List contenant les configurations qui soivent être mises à 1
     **/
    private static List<String> ConfigurationsDonnant1 (String regleBinaire ,int nbBits){
        List<String> resultatsA1 = new ArrayList<>();
        int facteur = 0;
        for (int i = regleBinaire.length()-1; i >= 0 ; i--) {
            // parcours du string de puis les bit de poids faible
            char charbitActuel = regleBinaire.charAt(i);
            //System.out.print("char " + charbitActuel + " \n");
            if(charbitActuel=='1'){

                //on converti le facteur en configuration en binaire
                //et on l'ajoute a la liste resultante

                String factBin= Integer.toBinaryString(facteur);
                while (factBin.length()<nbBits){
                    factBin="0" + factBin;
                }
                //System.out.print("facteur "+facteur + "  " +factBin+ " \n");
                String bin = BinToConf(factBin);
                //System.out.print(bin + " \n");
                resultatsA1.add(bin);

            }
            facteur = facteur +1;
        }
        return resultatsA1;
    }


    LocalRule(ArrayList<String> etatsPossibles, int nbDeVoisins,String NomRegle){
        if(NomRegle == "FEU") {
            //System.out.println("Le Nom en string de la regle n'existe pas.");
            //System.exit(0);
            //}
            List<String> toutesLesCombinaisons = genererToutesLesCombinaisons(etatsPossibles, nbDeVoisins);
            listeClesValeurs = new HashMap<>();
            for (String combinaison : toutesLesCombinaisons) {
                String EtatPremiereCellule =  combinaison.split(";")[0];
                //System.out.print(" etat cellule ref : " + EtatPremiereCellule +"\n");

                switch(EtatPremiereCellule) {
                    case "FEU":
                        listeClesValeurs.put(combinaison, "CENDRE");
                        break;
                    case "CENDRE":
                        listeClesValeurs.put(combinaison, "CENDRE");
                        break;
                    case "VIDE":
                        listeClesValeurs.put(combinaison, "VIDE");
                        break;
                    case "ARBRE":
                        boolean estContenue = combinaison.contains("FEU");
                        if (estContenue) {
                            listeClesValeurs.put(combinaison, "FEU");
                        } else {
                            listeClesValeurs.put(combinaison, EtatPremiereCellule);
                        }
                        break;
                    default:
                        listeClesValeurs.put(combinaison,"eindef");
                }

            }

        }
        else{
            int nbRegle=Integer.parseInt(NomRegle);
            String regleEnBin = Integer.toBinaryString(nbRegle);
            //TESTER SI LE NOM DE BITS SONT COMPATIBLES AVEC LE NB DE VOISINS ET LA CELLULE ACTUELLE NB + 1
            System.out.print("regle en binaire : " + regleEnBin + '\n');

            List<String> toutesLesCombinaisons = genererToutesLesCombinaisons(etatsPossibles, nbDeVoisins);
            listeClesValeurs = new HashMap<>();
            List<String>  listeDesConfigurationsDonnann1 = ConfigurationsDonnant1(regleEnBin,nbDeVoisins);
            //System.out.print(listeDesConfigurationsDonnann1);
            for (String combinaison : toutesLesCombinaisons) {
                boolean estContenue = listeDesConfigurationsDonnann1.contains(combinaison);

                if (estContenue) {
                    listeClesValeurs.put(combinaison, "1");
                } else {
                    listeClesValeurs.put(combinaison, "0");
                }
            }
        }
    }

    public void afficher(){
        for (Map.Entry<String, String> entry : listeClesValeurs.entrySet()) {
            System.out.println("Clé : " + entry.getKey() + ", Valeur : " + entry.getValue());
        }
    }
}


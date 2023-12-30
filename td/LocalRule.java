package AutomatesCellulaires.td;

import java.util.*;
import java.lang.Math;

/*
EtatCellule etat1 = new EtatCellule("FEU");
int nbDeVoisins = 4;
LocalRule LocalRule1 = new LocalRule(etat1.getEtatChoisie(), nbDeVoisins,"FEU");
LocalRule1.afficher();
* */

public class LocalRule {

    Map<String, String> listeClesValeurs ;

    public static List<String> genererToutesLesCombinaisons(List<String> valeurs, int tailleCombinaison,String sep,String CombinaisonInit) {
        List<String> resultats = new ArrayList<>();
        //CombinaisonInit=" "FEU et ""pour 1D
        genererCombinaisons(valeurs, tailleCombinaison,CombinaisonInit, resultats,sep);
        return resultats;
    }

    private static void genererCombinaisons(List<String> valeurs, int tailleCombinaison, String combinaisonActuelle, List<String> resultats,String sep) {
        //String sep = ";";
        if (tailleCombinaison == 0) {
            // Ajouter la combinaison actuelle à la liste des résultats
            resultats.add(combinaisonActuelle);
            return;
        }

        for (int i = 0; i < valeurs.size(); i++) {
            // Choisir une valeur
            String valeurChoisie = valeurs.get(i);

            // Récursivement générer la suite de la combinaison avec la même taille
            genererCombinaisons(valeurs, tailleCombinaison - 1, combinaisonActuelle + valeurChoisie + sep, resultats,sep);
        }
    }

    /**
     * met des escpaces entre chaque char et avant et apres du string contenant un binaire
     * @param Binaire string contenant un nb en binaire
     * @return Binaire avec des  String List contenant les configurations qui soivent être mises à 1
     **/
    private static String BinToConf (String Binaire){
        String AvecEspaces = "";
        for (int i = 0; i < Binaire.length() ; i++) {
            AvecEspaces = AvecEspaces + Binaire.charAt(i) + ";";
        }
        //System.out.print(" str bin ." + Binaire + ". result ." + AvecEspaces);
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

                String bin = BinToConf(factBin);
                //System.out.print("facteur "+facteur + "  " +factBin+".bin." + bin +  ". \n");
                //System.out.print(bin + " \n");
                resultatsA1.add(bin);

            }
            facteur = facteur +1;
        }
        return resultatsA1;
    }

    /**
     * Cree le HashMap pour une regle locale de type FEU avec les parametres donnés
     * @param etatsPossibles ArrayList string contenant les etats possibles d'une celule
     * @param nbDeVoisins int, nombre de voisins par cellule (si FEU VIDE CENDRE => 2 voisins)
     * @return resCleValeurs
     * */
    private static Map<String, String> CreationRegleFEU(ArrayList<String> etatsPossibles, int nbDeVoisins){
        List<String> toutesLesCombinaisons = genererToutesLesCombinaisons(etatsPossibles, nbDeVoisins+1,";","");
        Map<String, String> resCleValeurs = new HashMap<>(); // on initialise la liste des clés valeurs
        for (String combinaison : toutesLesCombinaisons) {
            String EtatPremiereCellule =  combinaison.split(";")[0];
            //System.out.print(" etat cellule ref : " + EtatPremiereCellule +"\n");

            switch(EtatPremiereCellule) {
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
                    resCleValeurs.put(combinaison,"eindef");
            }

        }
        return resCleValeurs;

    }



    /**
     * Constructeur de LocalRule avec nom (String) de la Regle
     * @param etatsPossibles ArrayList string contenant les etats possibles d'une celule
     * @param nbDeVoisins int, nombre de voisins par cellule
     * @param NomRegle String contenenant le nom de la Regle (pour le moment FEU)
     * */
    LocalRule(ArrayList<String> etatsPossibles, int nbDeVoisins,String NomRegle){ 

        //etatsPossibles = ["FEU", "CENDRE", "VIDE", "ARBRE"] par exemple pour le feu
        //ensuite ca créé locale rule pour le feu  ou pour ce qu'on veux 

        if(NomRegle == "FEU") {
            listeClesValeurs = CreationRegleFEU(etatsPossibles,nbDeVoisins);
        }
    }

    /**
     * Constructeur de LocalRule avec numero (int) de la Regle
     * @param etatsPossibles ArrayList string contenant les etats possibles d'une celule
     * @param nbDeVoisins int, nombre de voisins par cellule
     * @param NumeroRegle String contenenant le nom de la Regle (pour le moment FEU)
     * */
    LocalRule(ArrayList<String> etatsPossibles, int nbDeVoisins,int NumeroRegle){
        String sep=";";

        //etatsPossibles = ["0","1"] par exemple pour le feu
        //ensuite ca créé locale rule pour le nombre qu'on a donné

        String regleEnBin = Integer.toBinaryString(NumeroRegle);
        double nbBitsVoisins = Math.pow(2, nbDeVoisins+1);

        //TESTER SI LE NB DE BITS SONT COMPATIBLES AVEC LE NB DE VOISINS ET LA CELLULE ACTUELLE NB + 1
        //Si regleEnBin a un nb de bits > nbBitsVoisins => on le tronque
        //Si regleEnBin a un nb de bits < nbBitsVoisins => on le rajoute des zeros

        if(regleEnBin.length()!= nbBitsVoisins){
            //System.out.print(" nb de bit de regle Bin est different de nb de voisins +1  ");
            if(regleEnBin.length() < nbBitsVoisins){
                //System.out.print("ajout de zeros");
                while(regleEnBin.length() < nbBitsVoisins){
                    regleEnBin="0"+regleEnBin;
                }
            }else{
                int longueur=regleEnBin.length();
                regleEnBin = regleEnBin.substring(longueur-(int)nbBitsVoisins, longueur);
                //System.out.print("nouvelle regle bin coupée est:"+regleEnBin);
            }
        }

        System.out.print("regle en binaire : " + regleEnBin + '\n');

        List<String> toutesLesCombinaisons = genererToutesLesCombinaisons(etatsPossibles, nbDeVoisins+1,sep,"");
        listeClesValeurs = new HashMap<>();
        List<String>  listeDesConfigurationsDonnann1 = ConfigurationsDonnant1(regleEnBin,nbDeVoisins+1);
        //System.out.print(listeDesConfigurationsDonnann1 + "\n");
        for (String combinaison : toutesLesCombinaisons) {
            //System.out.print("Combinaison:"+combinaison+".");
            boolean estContenue = listeDesConfigurationsDonnann1.contains(combinaison);
            if (estContenue) {
                listeClesValeurs.put(combinaison, "1");
            } else {
                listeClesValeurs.put(combinaison, "0");
            }
        }
    }

    public void afficher(){
        for (Map.Entry<String, String> entry : listeClesValeurs.entrySet()) {
            System.out.println("Clé : " + entry.getKey() + ", Valeur : " + entry.getValue());
        }
    }
    /**
     * Trouve l'etat suivant selon la configuration de l'etat de la cellule et de ces voisines
     * @param etatsPossibles ArrayList string contenant les etats possibles d'une celule
     * @param nbDeVoisins int, nombre de voisins par cellule (selon déclaration)
     * @retun String donnant l'etat suivant
     * */
    public String getEtatSuivant(String Configuration,EtatCellule etatsPossibles,int nbDeVoisins){
        String[] listeTemp = Configuration.split(";");
        int nbReeldeCellules = listeTemp.length;
        if(nbReeldeCellules-1 < nbDeVoisins){
            while(nbReeldeCellules-1 < nbDeVoisins){
                Configuration=Configuration+etatsPossibles.getEtatByIndex(0)+";";
                listeTemp = Configuration.split(";");
                nbReeldeCellules = listeTemp.length;
            }
        }

        String etatSuivant= listeClesValeurs.get(Configuration);

        return etatSuivant;
    }

}


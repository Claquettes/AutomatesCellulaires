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

    Map<String, String> listeClesValeurs;

    /**
     * Cree les differents combinaisons de tailleCombinaison longueur avec les elements contenus dans la lsite valeurs
     * @param valeurs List String qui contient les string à combiner
     * @param tailleCombinaison int longueur des combinaisons desirées
     * @param sep String separateur des elements dans les combinaisons
     * @param CombinaisonInit String qui contient comment on commence la combinaison
     * @return resultats List String avec les combinaisons de tailleCombianison elements separés avec sep
     * */
    public static List<String> genererToutesLesCombinaisons(List<String> valeurs, int tailleCombinaison,String sep,String CombinaisonInit) {
        List<String> resultats = new ArrayList<>();
        //CombinaisonInit=" "FEU et ""pour 1D
        genererCombinaisons(valeurs, tailleCombinaison,CombinaisonInit, resultats,sep);
        return resultats;
    }

    /**
     * Cree les combinaisons de tailleCombinaison longueur avec les elements contenus dans la lsite valeurs
     * resultat en parametre/resultat nomé resultats
     * @param valeurs List String qui contient les string à combiner
     * @param tailleCombinaison int longueur des combinaisons desirées
     * @param combinaisonActuelle contient la combinaison en construction
     * @param resultats accummulateur de type List String contenant les dif combinaisons
     * @param sep String separateur des elements dans les combinaisons
     * */
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
     * met des points virgule apres chaque char  du string contenant un nb en binaire
     * @param Binaire string contenant un nb en binaire
     * @return Binaire avec des String List contenant les configurations qui doivent
     *         être mises à 1
     **/

    private static String BinToConf (String Binaire){
        String AvecPointVirgule = "";
        for (int i = 0; i < Binaire.length() ; i++) {
           AvecPointVirgule =AvecPointVirgule + Binaire.charAt(i) + ";";
        }
        //System.out.print(" str bin ." + Binaire + ". result ." +AvecPointVirgule);
        return AvecPointVirgule;
    }

    /**
     * Calcule les clé/configurations pour les quelles la valeur doit être à 1 pour
     * les regles només par un nombre
     * @param regleBinaire string contenant un nb en binaire
     * @param nbBits nb de caracteres pour la regle binaire
     * @return liste String List contenant les configurations qui soivent être mises
     *         à 1
     **/
    private static List<String> ConfigurationsDonnant1(String regleBinaire, int nbBits) {

        List<String> resultatsA1 = new ArrayList<>();
        int facteur = 0;
        for (int i = regleBinaire.length() - 1; i >= 0; i--) {
            // parcours du string de puis les bit de poids faible
            char charbitActuel = regleBinaire.charAt(i);
            // System.out.print("char " + charbitActuel + " \n");
            if (charbitActuel == '1') {

                // on converti le facteur en configuration en binaire
                // et on l'ajoute a la liste resultante

                String factBin = Integer.toBinaryString(facteur);
                while (factBin.length() < nbBits) {
                    factBin = "0" + factBin;
                }

                String bin = BinToConf(factBin);
                //System.out.print("facteur "+facteur + "  " +factBin+".bin." + bin +  ". \n");
                //System.out.print(bin + " \n");
                resultatsA1.add(bin);

            }
            facteur = facteur + 1;
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
     * Cree le HashMap pour une regle locale de type VIE (jeu de la vie) avec les parametres donnés
     * @param etatsPossibles ArrayList string contenant les etats possibles d'une celule
     * @param nbDeVoisins int, nombre de voisins par cellule (ici il doit toujours etre de 8 !!!!
     * @return resCleValeurs
     * */
    private static Map<String, String> CreationRegleVIE(ArrayList<String> etatsPossibles, int nbDeVoisins){
        nbDeVoisins = 8;
        String v="1";
        String m="0";
        int nbVivants;
        List<String> toutesLesCombinaisons = genererToutesLesCombinaisons(etatsPossibles, nbDeVoisins+1,";","");
        Map<String, String> resCleValeurs = new HashMap<>(); // on initialise la liste des clés valeurs
        for (String combinaison : toutesLesCombinaisons) {
            String EtatPremiereCellule =  combinaison.split(";")[0];
            String[] voisinage = combinaison.split(";");
            nbVivants = 0;

            for ( int i=1 ; i< nbDeVoisins+1 ; i++ ) {
                if (voisinage[i].equals(v)) {
                    nbVivants++;
                }
            }

            if( nbVivants == 3 ||  (nbVivants == 2 && voisinage[0].equals(etatsPossibles.get(1)) )  ){
                resCleValeurs.put(combinaison, etatsPossibles.get(1)); //vivant
            }else{
                resCleValeurs.put(combinaison, etatsPossibles.get(0));
            }

            //System.out.print(" etat cellule ref : " + EtatPremiereCellule +"\n");
        }
        return resCleValeurs;
    }

    /**
     * Cree le HashMap pour une regle locale de type MAJORITE avec les parametres donnés
     * @param etatsPossibles ArrayList string contenant les etats possibles d'une celule
     * @param nbDeVoisins int, nombre de voisins par cellule TJS IMPAIRE !!!
     * @return resCleValeurs
     * */

    private static Map<String, String> CreationRegleMAJORITE(ArrayList<String> etatsPossibles, int nbDeVoisins){
        // ICI ON ASSUME que voisinage Q = {0, 1} OU que on a que deux etats
        String val0 = etatsPossibles.get(0);
        int nbVal0, nbVal1;
        String val1 = etatsPossibles.get(1);
        int nbVivants;
        List<String> toutesLesCombinaisons = genererToutesLesCombinaisons(etatsPossibles, nbDeVoisins+1,";","");
        Map<String, String> resCleValeurs = new HashMap<>(); // on initialise la liste des clés valeurs
        for (String combinaison : toutesLesCombinaisons) {
            String EtatPremiereCellule =  combinaison.split(";")[0];
            String[] voisinage = combinaison.split(";");

            nbVal0 = 0;
            nbVal1 = 0;
            //on ne prend pas en compte l'etat de la cellule actuelle
            for ( int i=1 ; i< nbDeVoisins+1 ; i++ ) {
                if (voisinage[i].equals(val0)) {
                    nbVal0++;
                }
                if (voisinage[i].equals(val1)) {
                    nbVal1++;
                }
            }

            if( nbVal0 > nbVal1 ){
                resCleValeurs.put(combinaison, etatsPossibles.get(0));
            }else{
                resCleValeurs.put(combinaison, etatsPossibles.get(1));
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
        switch (NomRegle) {
            case "FEU":
                listeClesValeurs = CreationRegleFEU(etatsPossibles,nbDeVoisins);
                break;
            case "VIE":
                listeClesValeurs = CreationRegleVIE(etatsPossibles,8); //tjs 8 voisins
                break;
            case "MAJORITE":
                // nbDeVoisins TOUJOURS IMPAIR
                if(nbDeVoisins % 2 == 0){
                    //ajout de 1 pour le faire impaire
                    nbDeVoisins++;
                }
                listeClesValeurs = CreationRegleMAJORITE(etatsPossibles,nbDeVoisins);
                break;
            default:
                System.out.println("Erreur : nom de regle inconnu...");
        }

    }


    /**
     * Constructeur de LocalRule avec numero (int) de la Regle
     * @param etatsPossibles ArrayList string contenant les etats possibles d'une celule
     * @param nbDeVoisins int, nombre de voisins par cellule
     * @param NumeroRegle int contenenant le numero de la Regle (pour le moment 0;1)
     * */
    LocalRule(ArrayList<String> etatsPossibles, int nbDeVoisins,int NumeroRegle){
        String sep=";";

        //etatsPossibles = ["0","1"] 
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

    public void afficher() {
        for (Map.Entry<String, String> entry : listeClesValeurs.entrySet()) {
            System.out.println("Clé : " + entry.getKey() + ", Valeur : " + entry.getValue());
        }
    }
    /**
     * Trouve l'etat suivant selon la configuration de l'etat de la cellule et de ces voisines
     * @param Configuration String qui contient le "voisinage" (etat cellule ; etat cellules voisines)
     * @retun String donnant l'etat suivant
     * */
    public String getEtatSuivant(String Configuration){

        return listeClesValeurs.get(Configuration);
    }

}

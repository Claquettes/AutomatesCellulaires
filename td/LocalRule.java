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
        genererCombinaisons(valeurs, tailleCombinaison, " ", resultats);
        return resultats;
    }

    private static void genererCombinaisons(List<String> valeurs, int tailleCombinaison, String combinaisonActuelle, List<String> resultats) {
        if (tailleCombinaison == 0) {
            // Ajouter la combinaison actuelle à la liste des résultats
            resultats.add(combinaisonActuelle);
            return;
        }

        for (int i = 0; i < valeurs.size(); i++) {
            // Choisir une valeur
            String valeurChoisie = valeurs.get(i);

            // Récursivement générer la suite de la combinaison avec la même taille
            genererCombinaisons(valeurs, tailleCombinaison - 1, combinaisonActuelle + valeurChoisie + " ", resultats);
        }
    }

    LocalRule(ArrayList<String> etatsPossibles, int nbDeVoisins,String NomRegle){
        if(NomRegle != "FEU"){
            System.out.println("Le Nom en string de la regle n'existe pas.");
            System.exit(0);
        }
        List<String> toutesLesCombinaisons = genererToutesLesCombinaisons(etatsPossibles, nbDeVoisins);
        listeClesValeurs = new HashMap<>();
        for (String combinaison : toutesLesCombinaisons) {
            boolean estContenue = combinaison.contains(" FEU ");

            if(estContenue){
                listeClesValeurs.put(combinaison, "FEU");
            }else{
                listeClesValeurs.put(combinaison, "ei");
            }
        }
    }

    public void afficher(){
        for (Map.Entry<String, String> entry : listeClesValeurs.entrySet()) {
            System.out.println("Clé : " + entry.getKey() + ", Valeur : " + entry.getValue());
        }
    }
}


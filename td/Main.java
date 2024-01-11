package AutomatesCellulaires.td;

import AutomatesCellulaires.td.EtatCellule;
import AutomatesCellulaires.td.Cellule;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        UserInterface ui = new UserInterface();

        /*EtatCellule etat1 = new EtatCellule("FEU");
        System.out.print(etat1.getEtatChoisie());
        int nbDeVoisins = 2;
        LocalRule LocalRule1 = new LocalRule(etat1.getEtatChoisie(), nbDeVoisins,"FEU");
        LocalRule1.afficher();

        EtatCellule etat0et1 = new EtatCellule("1D");
        //170
        LocalRule LocalRule2 = new LocalRule(etat0et1.getEtatChoisie(), nbDeVoisins,170);

        System.out.print("\n");
        LocalRule2.afficher();

        System.out.print("etat: 1;0;"+ " etat suivant "+LocalRule2.getEtatSuivant("1;0;",etat0et1,nbDeVoisins));
        /*String valStr= LocalRule2.listeClesValeurs.get(" 0 0 0 ");
        String t =" 0 0 0 ";
        t ="0;0;0;;;";
        String[] a = t.split(";");
        System.out.print(a.length);
        System.out.print(" array ");//+valStr.isEmpty()+" empty \n");
        if(!valStr.isEmpty()){
            System.out.print(valStr);
        }*/


    }
}
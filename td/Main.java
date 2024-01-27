package AutomatesCellulaires.td;

import AutomatesCellulaires.td.EtatCellule;
import AutomatesCellulaires.td.Cellule;

import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        UserInterface ui = new UserInterface();

        /*EtatCellule etat0et1 = new EtatCellule("FEU");
        LocalRule LocalRuleVie = new LocalRule(etat0et1.getEtatChoisie(), 4,"FEU");
        LocalRule testMajorite  =  new LocalRule(etat0et1.getEtatChoisie(),2,"MAJORITE");
        //test.afficher();
         */
        /*EtatCellule etat0et1 = new EtatCellule("1D");
        LocalRule testMajorite  =  new LocalRule(etat0et1.getEtatChoisie(),3,"MAJORITE");
        Automate nouveau = new Automate(testMajorite,3,etat0et1,12,12);

        AutomateFeu FeuAutomateTest = new AutomateFeu(8, 20, 20, 0.9, "Est", 1, 1, 0.01,6);
        SaveConfiRead ecrit = new SaveConfiRead();
        int resultatSauvegarde = ecrit.sauvegardeConfig("sauvegarde1Test",nouveau);
        if(resultatSauvegarde == 1) {
            System.out.println(" sauvegarde reussie !");
            Automate automateLu = ecrit.lireConfig("sauvegarde1Test");
            if (!automateLu.equals(null)) {
                System.out.println(automateLu);
                for (int i = 0; i < 10; i++) {
                    System.out.println(automateLu);
                    automateLu.miseAJour();
                    int millis = 3000;
                    try {
                        Thread.sleep(millis);
                    } catch (InterruptedException ie) {
                        // ...
                    }
                }

            }
        }*/
    }
}
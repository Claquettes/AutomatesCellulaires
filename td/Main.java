package AutomatesCellulaires.td;

import AutomatesCellulaires.td.EtatCellule;
import AutomatesCellulaires.td.Cellule;

import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        /*UserInterface ui = new UserInterface();

        EtatCellule etat0et1 = new EtatCellule("FEU");
        LocalRule LocalRuleVie = new LocalRule(etat0et1.getEtatChoisie(), 4,"FEU");
        LocalRule testMajorite  =  new LocalRule(etat0et1.getEtatChoisie(),2,"MAJORITE");
        //test.afficher();
         */

        AutomateFeu FeuAutomateTest = new AutomateFeu(4, 6, 10 , 0.5, "nan", 0, 0.1, 0,3);

        /*Automate automTest = new Automate(LocalRuleVie,4,etat0et1,6,6);
        for (int i = 0; i < 10; i++) {
            automTest.miseAJour();
            System.out.println(automTest.grid);
            int millis = 5000;

            try {
                Thread.sleep(millis);
            } catch (InterruptedException ie) {
                // ...
            }
        }
         */



    }
}
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

        AutomateFeu FeuAutomateTest = new AutomateFeu(4, 12, 12 , 0.5, "Est", 1, 0.8, 0,4);
        //System.out.println(FeuAutomateTest);
        for (int i = 0; i < 10; i++) {
            System.out.println(FeuAutomateTest);
            FeuAutomateTest.miseAJour();
            int millis = 4500;
            try {
                Thread.sleep(millis);
            } catch (InterruptedException ie) {
                // ...
            }

        }




    }
}
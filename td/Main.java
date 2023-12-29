package AutomatesCellulaires.td;
import AutomatesCellulaires.td.EtatCellule;
import AutomatesCellulaires.td.Cellule;

public class Main {
    public static void main(String[] args) {
        //UserInterface ui = new UserInterface();

        EtatCellule etat1 = new EtatCellule("FEU");
        System.out.print(etat1.getEtatChoisie());
        int nbDeVoisins = 3;
        LocalRule LocalRule1 = new LocalRule(etat1.getEtatChoisie(), nbDeVoisins,"FEU");
        LocalRule1.afficher();

        EtatCellule etat0et1 = new EtatCellule("1D");
        LocalRule LocalRule2 = new LocalRule(etat0et1.getEtatChoisie(), nbDeVoisins,"170");
        //LocalRule2.afficher();

    }
}
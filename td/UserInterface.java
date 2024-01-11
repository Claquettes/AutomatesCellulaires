package AutomatesCellulaires.td;

import java.util.Scanner;

public class UserInterface {

    UserInterface() {
        System.out.println("Interface créée");
        System.out.println("Appuyez sur F pour lancer le modèle de feu");
        System.out.println("Appuyez sur C pour lancer le modèle de conway");
        System.out.println("Appuyez sur Q pour quitter");

        Scanner scanner = new Scanner(System.in);
        String input;
        do {
            input = scanner.nextLine();
            if (input.equals("F")) {
                Automate automateFeu = new Automate("Feu");
            }
            if (input.equals("C")) {
                System.out.println("Lancement du modèle de conway");
            }
            if  (input.equals("1D")) {
                System.out.println("Lancement du modèle 1D");
                System.out.println("Quel est le numéro de la règle que vous voulez utiliser ?");
                Scanner scanner1DRuleNumber = new Scanner(System.in);
                Automate automate1D = new Automate("1D", scanner1DRuleNumber.nextInt());
            }
            if (input.equals("custom")) {
                //on récupère les paramètres nécessaires a la création de la local rule custome
                System.out.println("Donnez l'ensemble des états possibles des cellules, séparés par des virgules");
                System.out.println("Et avec l'état NEUTRE en premier");
                System.out.println("Exemple : VIDE,ARBRE,FEU,CENDRE");
                Scanner scannerCustomEtatsPossibles = new Scanner(System.in);
                String etatsPossibles = scannerCustomEtatsPossibles.nextLine();
                System.out.println("Donnez le nombre de voisins par cellule");
                Scanner scannerCustomNombreVoisins = new Scanner(System.in);
                int nombreVoisins = scannerCustomNombreVoisins.nextInt();
                System.out.println("Donnez le numéro de la règle que vous voulez utiliser");
                Scanner scannerCustomRuleNumber = new Scanner(System.in);
                int ruleNumber = scannerCustomRuleNumber.nextInt();
                //
                Automate automateCustom = new Automate(etatsPossibles, nombreVoisins, ruleNumber);
                





                




                       
            } else if (input.equals("q") || input.equals("Q") || input.equals("quit") || input.equals("QUIT")
                    || input.equals("Quit")) {
                System.out.println("Fermeture de l'interface");
            } else {
                System.out.println("Commande inconnue, veuillez réessayer");
            }
        } while (!isAKnownCommand(input));
    }

    void effacerConsole() {
        System.out.print("\033[H\033[2J"); // Clear console
        System.out.flush(); // Clear console
    }

    boolean isAKnownCommand(String input) {
        return input.equals("F") || input.equals("C") || input.equals("q") || input.equals("Q")
                || input.equals("quit") || input.equals("QUIT") || input.equals("Quit");
    }
}
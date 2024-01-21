package AutomatesCellulaires.td;

import java.util.Scanner;

import javax.swing.text.Style;

public class UserInterface {

    UserInterface() {
        System.out.println("Interface créée");
        System.out.println("Appuyez sur F pour lancer le modèle de feu");
        System.out.println("Appuyez sur C pour lancer le modèle de conway");
        System.out.println("Appuyez sur 1D pour lancer le modèle 1D");
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
                System.out.println("Quel est le nombre de voisins que vous voulez utiliser (1 ou 2) ?");
                System.out.println("Par default 1 veut dire uniquement le voisin de droite et 2 veut dire celui de gauche et celui de droite");
                Scanner scanner1DNeighbours = new Scanner(System.in);
                
                System.out.println("Quel est le numéro de la règle que vous voulez utiliser ?");
                Scanner scanner1DRuleNumber = new Scanner(System.in);
                Automate automate1D = new Automate("1D", scanner1DNeighbours.nextInt(), scanner1DRuleNumber.nextInt());
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
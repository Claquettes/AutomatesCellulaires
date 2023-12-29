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
                System.out.println("Lancement du modèle de feu");
                effacerConsole();
                Grille grille = new Grille(2, 8, new EtatCellule("FEU"));
                System.out.println(grille);
                System.out.println(grille.getCellules());
            }
            if (input.equals("C")) {
                effacerConsole();
                System.out.flush(); // Clear console
                System.out.println("Lancement du modèle de conway");
            }

            else if (input.equals("q") || input.equals("Q") || input.equals("quit") || input.equals("QUIT")
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
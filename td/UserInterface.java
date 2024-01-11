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
            if (input.equals("custom")) {
                System.out.println("Lancement du modèle custom");
                Automate automateCustom = new Automate(2, 8, "custom",
                        new LocalRule(new EtatCellule("FEU").getEtatChoisie(), 3, "FEU"));
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
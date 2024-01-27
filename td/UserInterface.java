package AutomatesCellulaires.td;

import java.util.Scanner;

/**
 * This class represents the UserInterface of the AutomatesCellulaires
 * application.
 * It provides a menu for the user to choose which Automate to launch.
 */
public class UserInterface {

    private Scanner scanner;

    /**
     * Constructor for the UserInterface class.
     * It initializes the UserInterface and displays the menu.
     */
    UserInterface() {
        scanner = new Scanner(System.in);
        displayMenu();
        handleUserInput();
    }

    /**
     * Displays the menu to the user.
     */
    private void displayMenu() {
        System.out.println("Interface créée");
        System.out.println("Appuyez sur F pour lancer le modèle de feu");
        System.out.println("Appuyez sur C pour lancer le modèle de conway");
        System.out.println("Appuyez sur 1D pour lancer le modèle 1D");
        System.out.println("Appuyez sur Q pour quitter");
    }

    /**
     * Handles the user input.
     * It performs actions based on the user's input.
     */
    private void handleUserInput() {
        String input;
        do {
            input = scanner.nextLine().toUpperCase();
            switch (input) {
                case "F":
                    Automate automateFeu = new Automate("Feu");
                    break;
                case "C":
                    System.out.println("Lancement du modèle de conway");
                    break;
                case "1D":
                    handle1DModel();
                    break;
                case "Q":
                case "QUIT":
                    System.out.println("Fermeture de l'interface");
                    break;
                default:
                    System.out.println("Commande inconnue, veuillez réessayer");
            }
        } while (!isAKnownCommand(input));
    }

    /**
     * Handles the creation of the 1D model.
     * It asks the user for the number of neighbors and the rule number.
     */
    private void handle1DModel() {
        System.out.println("Lancement du modèle 1D");
        System.out.println("Quel est le nombre de voisins que vous voulez utiliser (1 ou 2) ?");
        System.out.println(
                "Par default 1 veut dire uniquement le voisin de droite et 2 veut dire celui de gauche et celui de droite");
        int neighbours = scanner.nextInt();

        System.out.println("Quel est le numéro de la règle que vous voulez utiliser ?");
        int ruleNumber = scanner.nextInt();
        Automate automate1D = new Automate("1D", neighbours, ruleNumber);
    }

    /**
     * Checks if the input is a known command.
     * 
     * @param input The user's input.
     * @return True if the input is a known command, false otherwise.
     */
    private boolean isAKnownCommand(String input) {
        return input.equals("F") || input.equals("C") || input.equals("Q") || input.equals("QUIT");
    }

    /**
     * Clears the console.
     */
    private void clearConsole() {
        System.out.print("\033[H\033[2J"); // Clear console
        System.out.flush(); // Clear console
    }
}
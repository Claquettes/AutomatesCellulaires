/**
 * The Main class of the AutomatesCellulaires application.
 * This class is responsible for initializing the UserInterface.
 */
package AutomatesCellulaires.td;

import AutomatesCellulaires.td.EtatCellule;
import AutomatesCellulaires.td.Cellule;
import AutomatesCellulaires.td.Automate;
import AutomatesCellulaires.td.UserInterface;
import AutomatesCellulaires.td.GUI;
import AutomatesCellulaires.td.Grille;

import java.util.Arrays;
import java.util.Scanner;

public class Main {
    /**
     * The main method of the AutomatesCellulaires application.
     * This method is the entry point of the application and is responsible for creating an instance of the UserInterface.
     *
     * @param args An array of command-line arguments for the application.
     */
    public static void main(String[] args) {

        // demande user pour choix de l'interface 
        System.out.println("Appuyez sur 1 pour lancer l'interface console ou 2 pour lancer l'interface graphique");
        java.util.Scanner scanner = new java.util.Scanner(System.in);
        String input = scanner.nextLine();
        if (input.equals("1")) {
            UserInterface ui = new UserInterface();
        } else if (input.equals("2")) {
            GUI uig = new GUI();
        } else {
            System.out.println("Commande inconnue, veuillez réessayer");
        }
    }
}
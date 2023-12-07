package AutomatesCellulaires.td;
import java.util.Scanner;

public class UserInterface {

    UserInterface() {
        System.out.println("Interface créée");
        System.out.println("Appuyez sur q à tout moment pour quitter");
        System.out.println("Appuyez sur F pour lancer le modèle de feu");

        Scanner scanner = new Scanner(System.in);

        if (scanner.nextLine().equals("F")) {
            System.out.println("Lancement du modèle de feu");
            System.out.print("\033[H\033[2J");  // Clear console
            System.out.flush();  // Clear console
            Grille grille = new Grille(2, 8, new EtatCellule("FEU"));
            System.out.println(grille);
            System.out.println(grille.getCellules());
        } else {
            System.out.println("Commande inconnue");
        }
    }
}
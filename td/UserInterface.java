package AutomatesCellulaires.td;

import java.util.Scanner;

public class UserInterface {

    UserInterface() {
        System.out.println("Interface créée");
        System.out.println("Appuyez sur F pour lancer le modèle de feu");
        System.out.println("Appuyez sur C pour lancer le modèle de conway");
        System.out.println("Appuyez sur 1D pour lancer le modèle 1D");
        System.out.println("Appuyez sur Q pour quitter");

        Scanner scanner = new Scanner(System.in);
        String input;
        int nbCol;
        int nbLigne;
        do {
            input = scanner.nextLine();
            if (input.equals("F")) {
                System.out.println("Voulez vous utiliser la taille par défault pour la grille (10*5) ? (Y/N)");
                String input2 = scanner.nextLine();
                if(input2.equals("Y") || input2.equals("y")){
                    nbCol = 10;
                    nbLigne = 5;
                }else{
                    System.out.println("Veuillez entrer le nombre de colonnes de la grille");
                    nbCol = scanner.nextInt();
                    if(nbCol < 1){
                        System.out.println("Le nombre de colonnes doit être supérieur à 0");
                        System.exit(0);
                    }
                    System.out.println("Veuillez entrer le nombre de lignes de la grille");
                    nbLigne = scanner.nextInt();
                    if(nbLigne < 0){
                        System.out.println("Le nombre de lignes doit être supérieur à 0");
                        System.exit(0);
                    }
                }
                Automate automateFeu = new Automate("Feu", nbCol, nbLigne);

                // La boucle qui fait tourner le modèle
                for (int i = 0; i < 100; i++) {
                    automateFeu.miseAJour();
                    System.out.println(automateFeu.grid);
                }

                // Exit the program
                System.exit(0);
            }
            if (input.equals("C")) {
                System.out.println("Lancement du modèle de conway");
            }
            if  (input.equals("1D")) {
                System.out.println("Lancement du modèle 1D");
                System.out.println("Quel est le nombre de voisins que vous voulez utiliser (1 ou 2) ?");
                System.out.println("Par default 1 veut dire uniquement le voisin de droite et 2 veut dire celui de gauche et celui de droite");
                int neighbours = scanner.nextInt();
                System.out.println("Quel est le numéro de la règle que vous voulez utiliser ?");
                int ruleNumber = scanner.nextInt();
                System.out.println("Voulez vous utiliser la taille par défault pour la grille (10*5) ? (Y/N)");
                scanner.nextLine(); // Consume newline left-over
                String input2 = scanner.nextLine();
                if(input2.equals("Y") || input2.equals("y")){
                    nbCol = 10;
                    nbLigne = 5;
                }else{
                    System.out.println("Veuillez entrer le nombre de colonnes de la grille");
                    String inputNbCol = scanner.nextLine();
                    nbCol = 0;
                    try {
                        nbCol = Integer.parseInt(inputNbCol);
                        if(nbCol < 1){
                            System.out.println("Le nombre de colonnes doit être supérieur à 0");
                            System.exit(0);
                        }
                    } catch (NumberFormatException e) {
                        System.out.println("Erreur : vous devez entrer un nombre entier.");
                        

                    }

                    System.out.println("Veuillez entrer le nombre de lignes de la grille");
                    String inputNbLigne = scanner.nextLine();
                    try {
                        nbLigne = Integer.parseInt(inputNbLigne);
                        if(nbLigne < 0){
                            System.out.println("Le nombre de lignes doit être supérieur à 0");
                            System.exit(0);
                        }
                    } catch (NumberFormatException e) {
                        System.out.println("Erreur : vous devez entrer un nombre entier.");
                        
                    }
                }
                Automate automate1D = new Automate("1D", neighbours, ruleNumber, nbCol);

                // La boucle qui fait tourner le modèle
                for (int i = 0; i < 100; i++) {
                    automate1D.miseAJour();
                    System.out.println(automate1D.grid);
                }

                // Exit the program
                System.exit(0);

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
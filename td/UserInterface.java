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
        System.out.println("Appuyez sur Maj pour lancer le modèle avec la Regle de");
        System.out.println("Appuyez sur Q pour quitter");
    }


    /**
     * Handles the user input.
     * It performs actions based on the user's input.
     */
    private void handleUserInput() {
        String input;
        int nbCol;
        int nbLigne;
        do {
            input = scanner.nextLine();
            if (input.equals("F")) {
                System.out.println("Les parametres par défault sont: ");
                System.out.println("grille de 12*12, 4 voisins, vent qui vient du Est, force du vent de 0.8,  densite de la foret de 0.75");
                System.out.println(" 4 arbres en Feu, probabilité de autocombustion de 0 et probailite de propagation de feu 0.6 ");
                System.out.println("Voulez vous utiliser les parametres par défaut ? (Y/N)");
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
                System.out.println("Quel est le nombre de iterations que vous voulez (min 1) ?");
                int nbIt = scanner.nextInt();
                boolean nbItOk = (nbIt>0 );
                while (nbItOk != true){
                    System.out.println("Donner un nombre possitif non nul");
                    nbIt = scanner.nextInt();
                    nbItOk = (nbIt>0 );
                }
                //Integer nombreDeVoisins, Integer nbCol, Integer nbLigne , double densiteForet, String p_directionVent, double p_forceVent, double p_ProbaPropagationFeu, double q_ProbaCombustion, int nbEnFeu
                Automate automateFeu = new AutomateFeu(4, 12, 12 , 0.75, "Est", 0.8, 0.6, 0, 4);

                // La boucle qui fait tourner le modèle
                System.out.println(automateFeu);
                for (int i = 0; i < nbIt; i++) {
                    automateFeu.miseAJour();
                    int millis = 3000;
                    try {
                        Thread.sleep(millis);
                    } catch (InterruptedException ie) {
                        // ...
                    }
                    System.out.println(automateFeu);

                }

                // Exit the program
                System.exit(0);



                System.out.println("Quel est le nombrede voisins que vous voulez utiliser soit 4,6 ou 8 ?");
                int neighbours = scanner.nextInt();
                boolean nbNeighboursOk = (neighbours == 4 || neighbours==6 || neighbours==8 );
                while (nbNeighboursOk != true){
                    System.out.println("Donner un nombre de voisins 4, 6 ou 8");
                    neighbours = scanner.nextInt();
                    nbNeighboursOk = (neighbours == 4 || neighbours==6 || neighbours==8 );
                }

                //Integer nombreDeVoisins, Integer nbCol, Integer nbLigne , double densiteForet, String p_directionVent, double p_forceVent, double p_ProbaPropagationFeu, double q_ProbaCombustion, int nbEnFeu
                Automate automateFeuDef = new Automate("Feu", nbCol, nbLigne);

                // La boucle qui fait tourner le modèle
                for (int i = 0; i < 100; i++) {
                    automateFeu.miseAJour();
                    System.out.println(automateFeuDef.grid);
                }

                // Exit the program
                System.exit(0);
            }
            if (input.equals("C")) {
                System.out.println("Lancement du modèle de conway");
            }
            if (input.equals("Maj")) {
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
                System.out.println("Quel est le nombre impaire de voisins que vous voulez utiliser   (1 à 7) ?");
                int neighbours = scanner.nextInt();
                boolean nbNeighboursOk = (neighbours >=1 && neighbours<=7 && (neighbours%2 == 1));
                while (nbNeighboursOk != true){
                    System.out.println("Donner un nombre de voisins (1 à 7) ");
                    neighbours = scanner.nextInt();
                    nbNeighboursOk = (neighbours >=1 && neighbours<=7 && (neighbours%2 == 1));
                }
                Automate automateMAJO = new Automate(neighbours, nbCol,nbLigne, "MAJORITE" );

                // La boucle qui fait tourner le modèle
                for (int i = 0; i < 10; i++) {
                    automateMAJO.miseAJour();
                    System.out.println(automateMAJO.grid);
                }

                // Exit the program
                System.exit(0);
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
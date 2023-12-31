package AutomatesCellulaires.td;

public class Automate {
    public Grille grid;
    // public LocalRule lr; a rajouter quand la classe LocalRule sera créée

    // constructeur en mode prédéfini,
    public Automate(String name) {
        if (name.toUpperCase().equals("FEU")) {
            // this.grid = new Grille(2, 8, new EtatCellule("FEU"));
            System.out.println("Création de l'automate " + name + " en mode prédéfini");
            Grille grille = new Grille(2, 8, new EtatCellule("FEU"));
            System.out.println(grille);
            System.out.println(grille.getCellules());
            //we quit the program
            System.exit(0);
        }
        if (name.equals("CONWAY")) {
            // this.grid = new Grille(2, 8, new EtatCellule("CONWAY"));
            System.out.println("Création de l'automate " + name + " en mode prédéfini");
        } else {
            System.out.println("Erreur : nom d'automate inconnu...");
        }
    }

    // constructeur en mode custom,
    public Automate(int dimension, int nombreCellules, String name, LocalRule LR) {
        // this.grid = new Grille(dimension, nombreCellules, new EtatCellule(name));
        // this.lr = LR; a rajouter quand la classe LocalRule sera créée
        System.out.println("Création de l'automate " + name + " en mode custom");
        System.exit(0);
    }
}

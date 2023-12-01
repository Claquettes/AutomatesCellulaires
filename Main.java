package AutomatesCellulaires;

public class Main {
    public static void main(String[] args) {

        Grille grille = new Grille(2, 8, new EtatCellule("FEU"));

        System.out.println(grille);

        System.out.println(grille.getCellules());

    }
}

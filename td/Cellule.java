package AutomatesCellulaires.td;

public class Cellule {
    private Coordonnee coordonnee;
    private String etat;

    public Cellule(Coordonnee coordonnee, String etat) {
        this.coordonnee = coordonnee;
        this.etat = etat;
    }

    public Coordonnee getCoordonnee() {
        return this.coordonnee;
    }

    public String getEtat() {
        return this.etat;
    }

    public void setEtat(String etat) {
        this.etat = etat;
    }

    public String toString() {
        return "CELLULE : \n"+ this.coordonnee + " : " + this.etat + "\n";
    }
}

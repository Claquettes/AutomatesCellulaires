package AutomatesCellulaires.td;

public class Cellule {

    private String etat;

    public Cellule(String etat) {
        this.etat = etat;
    }


    public String getEtat() {
        return this.etat;
    }

    public void setEtat(String etat) {
        this.etat = etat;
    }

    public String toString() {
        return "CELLULE : \n" + " : " + this.etat + "\n";
    }
}

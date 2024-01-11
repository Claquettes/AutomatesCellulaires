package AutomatesCellulaires.td;

public class Cellule {
    private String etat;

    public Integer x;
    public Integer y;

    public Cellule(int x, int y, String etat) {
        this.x = x;
        this.y = y;
        this.etat = etat;
    }

    public String getEtat() {
        return this.etat;
    }

    public void setEtat(String etat) {
        this.etat = etat;
    }

    public String toString() {
        return "CELLULE : \n" + this.x + this.y + "\n" + this.getEtat() + "\n";
    }
}

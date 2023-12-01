package AutomatesCellulaires;

public class Coordonnee {
    private int dimension;
    private int[] coordonnees;

    public Coordonnee(int[] coordonnees) {
        this.dimension = coordonnees.length;
        this.coordonnees = coordonnees;
    }

    public int getDimension() {
        return this.dimension;
    }

    public int[] getCoordonnees() {
        return this.coordonnees;
    }

    public String toString() {
        String coordonneesString ="[";
        for (int i = 0; i < this.dimension; i++) {
            coordonneesString += this.coordonnees[i];
            if (i < this.dimension - 1) {
                coordonneesString += ", ";
            }
        }
        coordonneesString += "]";
        return coordonneesString;
    }
}

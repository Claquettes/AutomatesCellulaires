package AutomatesCellulaires.td;

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
        String coordonneesString = "[";
        for (int i = 0; i < this.dimension; i++) {
            coordonneesString += this.coordonnees[i];
            if (i < this.dimension - 1) {
                coordonneesString += ", ";
            }
        }
        coordonneesString += "]";
        return coordonneesString;
    }

    /**
     * constructeur avec une coordonnée comme parametre
     * @param CoordonneeOj Coordonnee
     */
    public Coordonnee(Coordonnee CoordonneeOj) {
        this.dimension = CoordonneeOj.getDimension();
        this.coordonnees = CoordonneeOj.getCoordonnees();
    }

}

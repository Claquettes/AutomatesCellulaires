package AutomatesCellulaires.td;
import AutomatesCellulaires.td.EtatCellule;
import AutomatesCellulaires.td.Cellule;
import AutomatesCellulaires.td.Automate;
import AutomatesCellulaires.td.UserInterface;
import AutomatesCellulaires.td.GUI;
import AutomatesCellulaires.td.Grille;


/**
 * This class represents a Coordinate in a multi-dimensional space.
 * It has a dimension and an array of coordinates.
 */
public class Coordonnee {
    private int dimension;
    private int[] coordonnees;

    /**
     * Constructor for the Coordonnee class.
     * It initializes the Coordonnee with a given array of coordinates.
     * 
     * @param coordonnees The initial coordinates.
     */
    public Coordonnee(int[] coordonnees) {
        this.dimension = coordonnees.length;
        this.coordonnees = coordonnees;
    }

    /**
     * Gets the dimension of the Coordonnee.
     * 
     * @return The dimension of the Coordonnee.
     */
    public int getDimension() {
        return this.dimension;
    }

    /**
     * Gets the dimension of the Coordonnee.
     * 
     * @return The dimension of the Coordonnee.
     */
    public int[] getCoordonnees() {
        return this.coordonnees;
    }

    /**
     * Returns a string representation of the Coordonnee.
     * 
     * @return A string representation of the Coordonnee.
     */
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
     * Constructor for the Coordonnee class.
     * It initializes the Coordonnee with another Coordonnee object.
     * 
     * @param CoordonneeOj The Coordonnee object to copy.
     */
    public Coordonnee(Coordonnee CoordonneeOj) {
        this.dimension = CoordonneeOj.getDimension();
        this.coordonnees = CoordonneeOj.getCoordonnees();
    }

}

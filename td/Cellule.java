package AutomatesCellulaires.td;

/**
 * This class represents a Cell in an Automaton.
 * It has a state which can be updated and retrieved.
 */
public class Cellule {

    private String etat;

    /**
     * Constructor for the Cellule class.
     * It initializes the Cellule with a given state.
     * 
     * @param etat The initial state of the Cellule.
     */
    public Cellule(String etat) {
        this.etat = etat;
    }

    /**
     * Gets the current state of the Cellule.
     * 
     * @return The current state of the Cellule.
     */
    public String getEtat() {
        return this.etat;
    }

    /**
     * Sets the state of the Cellule to a new state.
     * 
     * @param etat The new state of the Cellule.
     */
    public void setEtat(String etat) {
        this.etat = etat;
    }

    /**
     * Returns a string representation of the Cellule.
     * 
     * @return A string representation of the Cellule.
     */
    public String toString() {
        return "CELLULE : \n" + " : " + this.etat + "\n";
    }
}
package AutomatesCellulaires.td;
import AutomatesCellulaires.td.EtatCellule;
import AutomatesCellulaires.td.Cellule;
import AutomatesCellulaires.td.Automate;
import AutomatesCellulaires.td.UserInterface;
import AutomatesCellulaires.td.GUI;
import AutomatesCellulaires.td.Grille;


import java.util.ArrayList;
import java.util.Arrays;

import java.util.Map;

/**
 * This class represents the state of a Cell in an Automaton.
 * It has a map of possible states and a chosen state.
 */

public class EtatCellule {
    private ArrayList<String> etatChoisie;

    /**
     * Constructor for the EtatCellule class.
     * It initializes the EtatCellule with a given type.
     * 
     * @param type The type of the EtatCellule.
     */
    public EtatCellule(String type) {
        Map<String, ArrayList<String>> etats = Map.of(
                "1D", new ArrayList<>(Arrays.asList("0", "1")),
                "FEU", new ArrayList<>(Arrays.asList("VIDE", "ARBRE", "FEU", "CENDRE")),
                "AUTRE", new ArrayList<>(Arrays.asList("VIDE", "PLEINE")));

        if (etats.containsKey(type)) {
            this.etatChoisie = etats.get(type);
        } else {
            System.out.println("Le type d'état n'existe pas.");
            System.exit(0);
        }
    }

    /**
     * Gets the chosen state of the EtatCellule.
     * 
     * @return The chosen state of the EtatCellule.
     */
    public ArrayList<String> getEtatChoisie() {
        return this.etatChoisie;
    }

    /**
     * Gets the state of the EtatCellule by index.
     * 
     * @param index The index of the state.
     * @return The state of the EtatCellule at the given index.
     */
    public String getEtatByIndex(int index) {
        return this.etatChoisie.get(index);
    }
}

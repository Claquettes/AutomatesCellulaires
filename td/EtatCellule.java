package AutomatesCellulaires.td;

import java.util.ArrayList;
import java.util.Arrays;

import java.util.Map;

public class EtatCellule {
    private Map<String, ArrayList<String>> etats = Map.of(
            "1D", new ArrayList<>(Arrays.asList("0", "1")),
            "FEU", new ArrayList<>(Arrays.asList("VIDE", "ARBRE", "FEU", "CENDRE")),
            "AUTRE", new ArrayList<>(Arrays.asList("VIDE", "PLEINE")));

    private ArrayList<String> etatChoisie;

    public EtatCellule(String type) {
        if (this.etats.containsKey(type)) {
            this.etatChoisie = this.etats.get(type);
        } else {
            System.out.println("Le type d'état n'existe pas.");
            System.exit(0);
        }
    }

    public ArrayList<String> getEtatChoisie() {
        return this.etatChoisie;
    }

    public String getEtatByIndex(int index) {
        return this.etatChoisie.get(index);
    }
}

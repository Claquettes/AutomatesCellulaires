package AutomatesCellulaires.td;

//import com.google.gson.Gson;
//import com.google.gson.GsonBuilder;

import java.io.*;

/**
 * Class that saves and reads the configuration of an Automate
 */

public class SaveConfiRead {

    /*public Gson gsonPretty;

    SaveConfiRead() {
        gsonPretty = new GsonBuilder().setPrettyPrinting().create();
    }

    /**
     * Converts the automaton to Json and writes it in a file whose name is in
     * parameter
     * 
     * @param fileName        string it is the name of the file where we will write
     * @param automatonToSave the automaton we want to save
     * @return -1 in case of error 1 if everything is ok
     *
    public int sauvegardeConfig(String nomFichier, Automate automateASauvegarder) {
        String jsonV2 = "";
        try {
            jsonV2 = gsonPretty.toJson(automateASauvegarder);
            jsonV2 = jsonV2.replace("},\n        {", "},{");
            jsonV2 = jsonV2.replace("\n" + "          ", " ");
            jsonV2 = jsonV2.replace("\n" + "        ", " ");

        } catch (Exception e) {
            System.err.println("Erreur lors de la conversion en JSON : " + e.getMessage());
            e.printStackTrace();
            return -1;
        }
        String cheminFichier = nomFichier + ".json";

        try (BufferedWriter ecrivain = new BufferedWriter(new FileWriter(cheminFichier))) {
            ecrivain.write(jsonV2);
        } catch (IOException e) {
            System.err.println("Erreur lors de l'écriture dans le fichier " + cheminFichier + " : " + e.getMessage());
            e.printStackTrace();
            return -1;
        }

        return 1;

    }

    /**
     * Reads the configuration of the automaton which is contained in the file
     * named.json
     * 
     * @param fileName String without extension
     * @return null in case of error and the automaton if everything is ok
     *


    public Automate lireConfig(String nomFichier) {
        String cheminFichier = nomFichier + ".json";
        String contenu = "", ligne = "";
        try (BufferedReader br = new BufferedReader(new FileReader(cheminFichier))) {
            System.out.println(" Lecture");
            while ((ligne = br.readLine()) != null) {
                contenu = contenu + ligne;
            }
        } catch (IOException e) {
            System.err.println("Erreur lors de la lecture du fichier " + cheminFichier + " : " + e.getMessage());
            e.printStackTrace();
            return null;
        }

        if (contenu != "") {
            // System.out.println("Creation automate");
            if (contenu.contains("forceVent")) {
                Automate automateLu;
                try {
                    automateLu = gsonPretty.fromJson(contenu, AutomateFeu.class);
                } catch (Exception e) {
                    System.err.println("Erreur lors de la conversion de JSON à AutomateFeu: " + e.getMessage());
                    e.printStackTrace();
                    return null;
                }
                return automateLu;
            } else {
                Automate automateLu;
                try {
                    automateLu = gsonPretty.fromJson(contenu, Automate.class);
                } catch (Exception e) {
                    System.err.println("Erreur lors de la conversion de JSON à Automate: " + e.getMessage());
                    e.printStackTrace();
                    return null;
                }
                return automateLu;

            }
        } else {
            // cas ou le contenu est vide
            return null;
        }
    }*/
}

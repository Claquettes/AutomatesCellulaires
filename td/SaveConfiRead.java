package AutomatesCellulaires.td;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.*;

/**
 * Classe qui permet la l'ecriture d'une sauvegade d'un automate et permet aussi l'écriture
 */

public class SaveConfiRead {

    public Gson gsonPretty;
    SaveConfiRead(){
        gsonPretty = new GsonBuilder().setPrettyPrinting().create();
    }

    /**
     * Converti l'automate en Json et l'ecrit dans un fichier dont le nom est en parametre
     * @param nomFichier string il est le nom du fichier ou on va ecrire
     * @param automateASauvegarder l'automate qu'on veux sauvegader
     * @return -1 en cas d'erreur 1 si tout est ok
     */
    public int sauvegardeConfig(String nomFichier, Automate automateASauvegarder){
        String jsonV2 = "";
        try {
            jsonV2 = gsonPretty.toJson(automateASauvegarder);
            jsonV2 = jsonV2.replace("},\n        {","},{");
            jsonV2 = jsonV2.replace("\n" + "          "," ");
            jsonV2 = jsonV2.replace("\n" + "        "," ");

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

        return  1;

    }

    /**
     * Lit la configutaion de l'automate qui est contenue dans le c=fichier de nom.json
     * @param nomFichier String sans exention
     * @return null en cas d'erreur et l'automate si tout est ok
     */
    public Automate lireConfig(String nomFichier){
        String cheminFichier = nomFichier + ".json";
        String contenu = "", ligne="";
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

        if(contenu != ""){
            //System.out.println("Creation automate");
            if(contenu.contains("forceVent")){
                Automate automateLu;
                try {
                    automateLu = gsonPretty.fromJson(contenu,AutomateFeu.class);
                } catch (Exception e) {
                    System.err.println("Erreur lors de la conversion de JSON à AutomateFeu: " + e.getMessage());
                    e.printStackTrace();
                    return null;
                }
                return automateLu ;
            }else{
                Automate automateLu;
                try {
                    automateLu = gsonPretty.fromJson(contenu,Automate.class);
                } catch (Exception e) {
                    System.err.println("Erreur lors de la conversion de JSON à Automate: " + e.getMessage());
                    e.printStackTrace();
                    return null;
                }
                return automateLu ;

            }
        }else{
            //cas ou le contenu est vide
            return null;
        }
    }
}

package java;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

class Tp3 {
    public static void main(String[] args) {
        String fichierInput = args[0];
        String fichierOutput = args[1];

        lectureFichier(fichierInput);
    }

    /**
     * Lecture d'un fichier entrant passé en argument avec {@code BufferedReader} et {@code FileReader}
     * @param in fichier en cours de lecture
     */
    static void lectureFichier(String in) {
        Carte carte = new Carte();

        try (BufferedReader br = new BufferedReader(new FileReader("../inputs/" + in))) {
            String ligne;
            boolean debut = true;

            while ((ligne = br.readLine()) != null) {
                if (debut) { // début de fichier?
                    if (ligne.contains("---")) { // oui mais désormais près à passer à la lecture des rues
                        debut = false;
                        continue;
                    } else { // oui
                        carte.ajouterSite(ligne);
                    }
                } else { // après "---", on lit les rues
                    carte.ajouterRue(ligne);
                }   
            }
        } catch (IOException e) {
            System.out.print("Erreur à la lecture du fichier: " + e.getMessage());
        }
    }
}
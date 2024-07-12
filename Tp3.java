import java.io.*;
import java.util.List;

class Tp3 {
    public static void main(String[] args) {
        String fichierInput = args[0];
        String fichierOutput = args[1];
        Carte carte = new Carte();

        lectureFichier(fichierInput, carte);
        int coutMin = carte.Kruskal();
        ecritureFichier(fichierOutput, carte, coutMin);

    }

    /**
     * Lecture d'un fichier entrant passé en argument avec {@code BufferedReader} et {@code FileReader}
     * @param in fichier en cours de lecture
     * @param carte graphe des installations
     */
    static void lectureFichier(String in, Carte carte) {
        try (BufferedReader br = new BufferedReader(new FileReader("./inputs/" + in))) {
            String ligne;
            boolean debut = true;

            while ((ligne = br.readLine()) != null) {
                if (ligne.contains("---")) {
                    debut = false;
                    continue;
                }
    
                if (debut) {
                    carte.ajouterSite(ligne);
                } else {
                    carte.ajouterRue(ligne);
                }
            }
        } catch (IOException e) {
            System.out.print("Erreur à la lecture du fichier: " + e.getMessage());
        }
    }

    static void ecritureFichier(String out, Carte carte, int coutMin) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter("./outputs/" + out))) {
            List<String> sites = carte.getSites();
            

            for (String site : sites) {
                bw.write(site);
                bw.newLine();
            }

            List<String> aretes = carte.getAretes();
            for (String arete : aretes) {
                bw.write(arete);
                bw.newLine();
            }

            bw.write("---");
            bw.newLine();

            bw.write(Integer.toString(coutMin));
        } catch (IOException e) {
            System.out.print("Erreur à l'écriture du fichier: " + e.getMessage());
        }
    }
}
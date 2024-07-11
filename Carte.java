
import java.util.List;
import java.util.ArrayList;

public class Carte {
    private class Arete implements Comparable<Arete> {
        String nomRue;
        String site1;
        String site2;
        int cout;

        Arete(String nomRue, String site1, String site2, int cout) {
            this.nomRue = nomRue;
            this.site1 = site1;
            this.site2 = site2;
            this.cout = cout;
        }

        @Override
        public int compareTo(Arete autre) {
            return 0;
        }

        public String toString() {
            return nomRue + " " + site1 + " " + site2 + " " + cout;
        }
    }

    private List<String> sites;
    private List<Arete> aretes;

    Carte() {
        sites = new ArrayList<>();
        aretes = new ArrayList<>();
    }

    void ajouterSite(String site) {
        sites.add(site.trim());
    }

    void ajouterRue(String rue) {
        Arete segment = nettoyerStringRue(rue.trim());
        aretes.add(segment);
    }

    private Arete nettoyerStringRue(String rue) {
        String[] stringSeparer = rue.split(":");
        String nomRue = stringSeparer[0].trim();
        String[] infoSegment = stringSeparer[1].trim().split("\\s+");
        String site1 = infoSegment[0];
        String site2 = infoSegment[1];
        int cout = Integer.parseInt(infoSegment[2]);
        
        return new Arete(nomRue, site1, site2, cout);
    }
}

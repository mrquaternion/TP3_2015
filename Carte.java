import java.util.List;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class Carte {
    private List<String> sites;
    private List<Arete> aretes;
    private List<Arete> ARM;

    public class Arete {
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
        public String toString() {
            return nomRue + " " + site1 + " " + site2 + " " + cout;
        }
    }

    Carte() {
        sites = new ArrayList<>();
        aretes = new ArrayList<>();
        ARM = new ArrayList<>();
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

    public int Kruskal() {
        Collections.sort(aretes, Comparator.comparingInt(a -> a.cout));

        EnsembleDisjoint ed = new EnsembleDisjoint(sites.size());

        int coutMin = 0;

        for (Arete arete : aretes) {
            int site1Index = sites.indexOf(arete.site1);
            int site2Index = sites.indexOf(arete.site2);

            if (ed.find(site1Index) != ed.find(site2Index)) {
                ed.union(site1Index, site2Index);
                coutMin += arete.cout;
                ARM.add(arete);
            }
        }

        return coutMin;
    }

    public List<String> getSites() {
        return sites;
    }

    public List<String> getAretes() {
        trierAretes();

        List<String> ARMString = new ArrayList<>();

        for (Arete arete : ARM) {
            ARMString.add(arete.toString());
        }

        return ARMString;
    }

    private void trierAretes() {
        Collections.sort(ARM, new Comparator<Arete>() {
            @Override
            public int compare(Arete a1, Arete a2) {
                int val = a1.site1.compareTo(a2.site1);

                if (val == 0) {
                    val = a1.site2.compareTo(a2.site2);
                }

                return val;
            }
        });
    }
}

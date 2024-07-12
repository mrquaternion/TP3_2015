import java.util.List;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class Carte {
    private List<String> sites;
    private List<Arete> aretes;
    private List<Arete> arbreRecouvrement;

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
        arbreRecouvrement = new ArrayList<>();
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
        // Step 1: Sort all edges in non-decreasing order of their weight
        Collections.sort(aretes, Comparator.comparingInt(a -> a.cout));

        // Step 2: Create disjoint sets for each site
        EnsembleDisjoint ed = new EnsembleDisjoint(sites.size());

        int coutARM = 0;

        // Step 3: Process each edge
        for (Arete arete : aretes) {
            int site1Index = sites.indexOf(arete.site1);
            int site2Index = sites.indexOf(arete.site2);

            if (ed.find(site1Index) != ed.find(site2Index)) {
                ed.union(site1Index, site2Index);
                coutARM += arete.cout;
                arbreRecouvrement.add(arete);
            }
        }

        return coutARM;
    }

    public List<String> getSites() {
        return sites;
    }

    public List<String> getAretes() {
        List<String> aretesStr = new ArrayList<>();

        trierAretes();

        for (Arete arete : arbreRecouvrement) {
            aretesStr.add(arete.toString());
        }

        return aretesStr;
    }

    public void trierAretes() {
        Collections.sort(arbreRecouvrement, new Comparator<Arete>() {
            @Override
            public int compare(Arete a1, Arete a2) {
                int cmp = a1.site1.compareTo(a2.site1);
                if (cmp == 0) {
                    cmp = a1.site2.compareTo(a2.site2);
                }
                return cmp;
            }
        });
    }
}

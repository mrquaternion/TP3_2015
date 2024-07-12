public class EnsembleDisjoint {
    private int[] parent;
    private int[] rang;

    public EnsembleDisjoint(int size) {
        parent = new int[size];
        rang = new int[size];
        for (int i = 0; i < size; i++) {
            parent[i] = i;  
            rang[i] = 0;    
        }
    }

    // Méthode find avec compression de chemin
    public int find(int x) {
        if (parent[x] != x) {
            parent[x] = find(parent[x]);
        }
        return parent[x];
    }

    // Méthode union avec union par rang
    public void union(int x, int y) {
        int racineX = find(x);
        int racineY = find(y);

        if (racineX != racineY) {
            if (rang[racineX] > rang[racineY]) {
                parent[racineY] = racineX; 
            } else if (rang[racineX] < rang[racineY]) {
                parent[racineX] = racineY; 
            } else {
                parent[racineY] = racineX; 
                rang[racineX]++;
            }
        }
    }
}

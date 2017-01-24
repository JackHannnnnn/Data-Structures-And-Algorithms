package graph;
import java.io.File;
import java.io.IOException;
import java.util.*;

public class AdjMatrixGraph {
	private static final String NEWLINE = System.getProperty("line.separator");
	int V;
	int E;
	boolean[][] adj;
	
	public AdjMatrixGraph(int V) {
		if (V < 0) throw new IllegalArgumentException();
		this.V = V;
		adj = new boolean[V][V];
	}
	
	public int V() { return V; }
	public int E() { return E; }
	
	public void addEdge(int u, int w) {
		if (!adj[u][w]) {
			E++;
			adj[u][w] = true;
			adj[w][u] = true;
		}
	}
	
	public boolean contains(int v, int u) {
		return adj[v][u];
	}
	
	public Iterable<Integer> adj(int v) {
		return new AdjIterator(v);
	}
	
	public class AdjIterator implements Iterable<Integer>, Iterator<Integer> {
		int v;
		int loc;
		
		public AdjIterator(int v) {
			this.v = v;
			this.loc = 0;
		}
		
		public Iterator<Integer> iterator() {
			return this;
		}
		
		public boolean hasNext() {
			// Key
			while (loc < V) {
				if (adj[v][loc]) return true;
				loc++;
			}
			return false;
		}
		
		public Integer next() {
			if (loc >= V) throw new NoSuchElementException();
			return loc++;
		}
	}
	
	public String toString() {
        StringBuilder s = new StringBuilder();
        s.append(V + " " + E + NEWLINE);
        for (int v = 0; v < V; v++) {
            s.append(v + ": ");
            for (int w : adj(v)) {
                s.append(w + " ");
            }
            s.append(NEWLINE);
        }
        return s.toString();
    }
	
	public static void main(String[] args) throws IOException {
		Scanner sc = new Scanner(new File(System.getProperty("user.dir") + "/src/tinyG.txt"));
		int V = Integer.parseInt(sc.nextLine());
		Graph test = new Graph(V);
		while (sc.hasNextLine()) {
			String[] line = sc.nextLine().split(" ");
			test.addEdge(Integer.parseInt(line[0]), Integer.parseInt(line[1]));
		}
		System.out.println(test.toString());
		
	}
}

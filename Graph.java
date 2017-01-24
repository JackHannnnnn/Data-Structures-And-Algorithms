package graph;
import java.util.*;
import java.io.*;

public class Graph {
	private final static String NEWLINE = System.getProperty("line.separator");
	private final int V;
	private int E;
	private LinkedList<Integer>[] adj;
	
	public Graph(int V) {
		if (V < 0) throw new IllegalArgumentException("v must be non-gegative");
		this.V = V;
		this.E = 0;
		this.adj = new LinkedList[V];
		for (int i = 0; i < V; i++) {
			adj[i] = new LinkedList<Integer>();
		}
	}
	
	public Graph(Graph g) {
		this.V = g.V();
		this.E = g.E();
		this.adj = (LinkedList<Integer>[]) new LinkedList[this.V];
		for (int i = 0; i < this.V; i++) {
			Stack<Integer> reverse = new Stack<>();
			for (int v : g.adj[i])
				reverse.push(v);
			for (int v : reverse)
				this.adj[i].addFirst(v);
		}
	}
	
	
	public int V() { return V; }
	public int E() { return E; }
	
	public int degree(int v) {
		return adj[v].size();
	}
	
	public void addEdge(int u, int w) {
		validateV(u);
		validateV(w);
		E++;
		adj[u].add(w);
		adj[w].add(u);
	}
	
	public Iterable<Integer> adj(int v) {
		validateV(v);
		return adj[v];
	}
	
	private void validateV(int v) {
		if (v < 0 || v >= V)
			throw new IllegalArgumentException("v must be between 0 and V - 1");
	}
	
	public String toString() {
        StringBuilder s = new StringBuilder();
        s.append(V + " vertices, " + E + " edges " + NEWLINE);
        for (int v = 0; v < V; v++) {
            s.append(v + ": ");
            for (int w : adj[v]) {
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

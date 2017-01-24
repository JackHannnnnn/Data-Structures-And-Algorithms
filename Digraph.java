package graph;
import java.io.File;
import java.io.IOException;
import java.util.*;

public class Digraph {
	private final static String NEWLINE = System.getProperty("line.separator");
	private final int V;
	private int E;
	private LinkedList<Integer>[] adj;
	private int[] indegree;
	
	public Digraph(int V) {
		this.V = V;
		this.E = 0;
		this.adj = new LinkedList[V];
		for (int i = 0; i < V; i++) 
			adj[i] = new LinkedList<>();
		this.indegree = new int[this.V];
	}
	
	public Digraph(Digraph g) {
		this.V = g.V();
		this.E = g.E();
		this.adj = new LinkedList[V];
		for (int i = 0; i < V; i++) 
			adj[i] = new LinkedList<>();
		for (int i = 0; i < this.V; i++) {
			Stack<Integer> reverse = new Stack<>();
			for (int each : g.adj(i))
				reverse.push(each);
			while (!reverse.isEmpty()) {
				adj[i].addFirst(reverse.pop());
			}
		}
		indegree = new int[this.V];
		for (int i = 0 ; i < this.V; i++)
			indegree[i] = g.indegree(i);
	}
	
	public int V() { return this.V; }
	public int E() { return this.E; }
	
	public void addEdge(int u, int w) {
		validateV(u);
		validateV(w);
		adj[u].addFirst(w);
		indegree[w]++;
	}
	
	public Iterable<Integer> adj(int v) {
		validateV(v);
		return adj[v];
	}
	
	public int indegree(int v) {
		validateV(v);
		return indegree[v];
	}
	
	public int outdegree(int v) {
		validateV(v);
		return adj[v].size();
	}
	
	private void validateV(int v) {
		if (v < 0 || v >= V)
			throw new IllegalArgumentException();
	}
	
	public String toString() {
        StringBuilder s = new StringBuilder();
        s.append(V + " vertices, " + E + " edges " + NEWLINE);
        for (int v = 0; v < V; v++) {
            s.append(String.format("%d: ", v));
            for (int w : adj[v]) {
                s.append(String.format("%d ", w));
            }
            s.append(NEWLINE);
        }
        return s.toString();
    }
	
	public static void main(String[] args) throws IOException {
		Scanner sc = new Scanner(new File(System.getProperty("user.dir") + "/src/tinyDG.txt"));
		int V = Integer.parseInt(sc.nextLine());
		Digraph test = new Digraph(V);
		while (sc.hasNextLine()) {
			String[] line = sc.nextLine().split(" ");
			test.addEdge(Integer.parseInt(line[0]), Integer.parseInt(line[1]));
		}
		System.out.println(test.toString());
		
		Digraph test1 = new Digraph(test);
		System.out.println(test1.toString());
		
	}
	
}

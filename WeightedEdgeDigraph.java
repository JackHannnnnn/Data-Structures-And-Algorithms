package graph;
import java.util.*;

public class WeightedEdgeDigraph {

	private final int V;
	private int E;
	private LinkedList<DirectedEdge>[] adj;
	private int[] indegree;
	
	public WeightedEdgeDigraph(int V) {
		if (V < 0) throw new IllegalArgumentException();
		this.V = V;
		adj = new LinkedList[V];
		for (int i = 0; i < this.V; i++)
			adj[i] = new LinkedList<>();
		indegree = new int[this.V];
	}
	
	public int V() { return V; }
	public int E() { return E; }
	public int indegree(int v) { return indegree[v]; }
	public int outdegree(int v) { return adj[v].size(); }
	
	public void addEdge(DirectedEdge e) {
		int from = e.from();
		int to = e.to();
		validateV(from);
		validateV(to);
		adj[from].add(e);
		E++;
		indegree[to]++;
	}
	
	public Iterable<DirectedEdge> adj(int v) {
		validateV(v);
		return adj[v];
	}
	
	public Iterable<DirectedEdge> edges() {
		LinkedList<DirectedEdge> edges = new LinkedList<>();
		for (int i = 0; i < this.V; i++)
			edges.addAll(adj[i]);
		return edges;
	}
	
	private void validateV(int v) {
		if (v < 0 || v >= this.V)
			throw new IllegalArgumentException();
	}
}

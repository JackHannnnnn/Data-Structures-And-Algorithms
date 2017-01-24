package graph;
import java.util.*;

public class BreadthFirstSearch {
	private static final int INFINITY = Integer.MAX_VALUE;
	boolean[] marked;
	int[] edgeTo;
	int[] distTo;
	
	
	public BreadthFirstSearch(Graph g) {
		marked = new boolean[g.V()];
		edgeTo = new int[g.V()];
		distTo = new int[g.V()];
		for (int i = 0; i < g.V(); i++)
			distTo[i] = INFINITY;
	}
	
	private void validateV(int v) {
		int V = marked.length;
		if (v < 0 || v >= V)
			throw new IllegalArgumentException();
	}
	
	public void bfs(Graph g, int s) {
		validateV(s);
		distTo[s] = 0;
		marked[s] = true;
		Queue<Integer> queue = new LinkedList<>();
		queue.add(s);
		while (!queue.isEmpty()) {
			int node = queue.remove();
			System.out.print(node + " => ");
			for (int each : g.adj(node)) {
				if (!marked[each]) {
					marked[each] = true;
					edgeTo[each] = node;
					distTo[each] = distTo[node] + 1;
					queue.add(each);
				}
			}
		}
	}
	
	public boolean hasPathTo(int v) {
		validateV(v);
		return marked[v];
	}
	
	public Iterable<Integer> pathTo(int v) {
		validateV(v);
		Stack<Integer> path = new Stack<>();
		int x;		// Key Key  interesting
		for (x = v; distTo[x] != 0; x = edgeTo[x]) {
			path.push(x);
		}
		path.push(x);
		return path;
	}
	
	public static void main(String[] args) {
		Graph g = new Graph(7);
		g.addEdge(0, 1);
		g.addEdge(0, 3);
		g.addEdge(1, 5);
		g.addEdge(1, 4);
		g.addEdge(3, 2);
		g.addEdge(3, 6);
		BreadthFirstSearch bfs = new BreadthFirstSearch(g);
		bfs.bfs(g, 0);
		System.out.println();
		
		for (int each : bfs.pathTo(6))
			System.out.print(each + " => ");	
	}
}

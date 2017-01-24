package graph;
import java.util.*;

public class DepthFirstSearch {

	boolean[] marked;   // marked[k]: a path from s to k
	int[] edgeTo;
	int count;		
	final int s;
	
	public DepthFirstSearch(Graph g, int s) {
		marked = new boolean[g.V()];
		edgeTo = new int[g.V()];
		this.s = s;
	}
	
	// O(|E| + |V|)
	public void dfsRec(Graph g, int v) {
		count++;
		marked[v] = true;
		System.out.print(v + " => ");
		for (int each : g.adj(v)) {
			if (!marked[each]) {
				edgeTo[each] = v;
				dfsRec(g, each);
			}
		}
	}
	
	public void dfsIte(Graph g, int v) {
		marked[v] = true;
		Stack<Integer> stack = new Stack<>();
		stack.push(v);
		while (!stack.isEmpty()) {
			int node = stack.pop();
			System.out.print(node + " => ");
			for (int each : g.adj(node)) {
				if (!marked[each]) {
					marked[each] = true;
					edgeTo[each] = node;
					stack.push(each);
				}
			}
		}
	}
	
	public int count() { return count; }
	
	public boolean hasPathTo(int v) {
		validateV(v);
		return marked[v];
	}
	
	public Iterable<Integer> pathTo(int v) {
		validateV(v);
		if (!hasPathTo(v)) return null;
		Stack<Integer> path = new Stack<>();
		for (int x = v; x != s; x = edgeTo[x])
			path.push(x);
		path.push(s);
		return path;
	}
	
	private void validateV(int v) {
		int V = marked.length;
		if (v < 0 || v >= V)
			throw new IllegalArgumentException();
	}
	
	public static void main(String[] args) {
		Graph g = new Graph(7);
		g.addEdge(0, 1);
		g.addEdge(0, 3);
		g.addEdge(1, 5);
		g.addEdge(1, 4);
		g.addEdge(3, 2);
		g.addEdge(3, 6);
		DepthFirstSearch dfs = new DepthFirstSearch(g, 0);
		dfs.dfsIte(g, 0);
		System.out.println();
		
		DepthFirstSearch dfs1 = new DepthFirstSearch(g, 0);
		dfs1.dfsRec(g, 0);
		System.out.println();		
		
		for (int each : dfs.pathTo(6))
			System.out.print(each + " => ");
		System.out.println(Integer.MAX_VALUE + 1);
	}
}

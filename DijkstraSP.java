package graph;
import java.util.*;

public class DijkstraSP {
	
	public class Node implements Comparable<Node> {
		private int index;
		private double cost;
		public Node(int index) {
			this(index, Double.POSITIVE_INFINITY);
		}
		public Node(int index, double cost) { 
			if (index < 0 || index >= DijkstraSP.this.edgeTo.length)
				throw new IllegalArgumentException();
			this.index = index; 
			this.cost = cost;
		}
		public int index() { return this.index; }
		public double cost() { return this.cost; }
		public void setCost(double v) { this.cost = v;}
		@Override
		public int compareTo(Node other) {
			if (cost < other.cost()) {
				return -1;
			} else
				return 1;
		}
		@Override
		public boolean equals(Object obj) {
			if (obj == null) return false;
			Node other = (Node) obj;
			return this.index == other.index();
		}
	}
	
	private DirectedEdge[] edgeTo;	// the last edge on the shorted path from s to v;
	private double[] distTo;	// the cost of the shorted path from s to v
	
	public DijkstraSP(WeightedEdgeDigraph g, int s) {
		edgeTo = new DirectedEdge[g.V()];
		distTo = new double[g.V()];
		if (s < 0 || s >= g.V())
			throw new IllegalArgumentException();
		
		for (int i = 0; i < g.V(); i++) 
			distTo[i] = Double.POSITIVE_INFINITY;
		distTo[s] = 0;
		
		Node[] arr = new Node[g.V()];
		for (int i = 0; i < g.V(); i++) {
			if (i == s)
				arr[i] = new Node(i, 0);
			else
				arr[i] = new Node(i, Double.POSITIVE_INFINITY);
		}
		
		
		chaofan.BinaryHeap<Node> bh = new chaofan.BinaryHeap<>(arr);
		System.out.println("Order added to the known set: ");
		while (!bh.isEmpty()) {
			Node u = bh.remove();
			System.out.print(u.index() + " -> ");
			for (DirectedEdge each : g.adj(u.index())) {
				if (distTo[each.to()] > distTo[each.from()] + each.weight()) {
					distTo[each.to()] = distTo[each.from()] + each.weight();
					edgeTo[each.to()] = each;
					bh.decreaseKey(new Node(each.to()), new Node(each.to(), distTo[each.to()]));
					//System.out.println("edge: " + u.index() + "->"+ each.to());
				}
			}
		}
	}
	
	//public DirectedEdge[] edgeTo() { return edgeTo; }
	//public double[] distTo() { return distTo; }
	
	public boolean hasPathTo(int v) {
		validateV(v);
		return distTo[v] < Double.POSITIVE_INFINITY;
	}
	
	public Iterable<DirectedEdge> pathTo(int v) {
		validateV(v);
		if (!hasPathTo(v))
			return null;
		LinkedList<DirectedEdge> path = new LinkedList<>();
		for (DirectedEdge e = edgeTo[v]; e != null; e = edgeTo[e.from()]) {
			path.addFirst(e);
		}
		return path;
	}
	
	private void validateV(int v) {
		if (v < 0 || v >= this.edgeTo.length)
			throw new IllegalArgumentException();
	}
	
	public static void main(String[] args) {
		HashMap<String, Integer> data = new HashMap<>();
		data.put("A", 0);
		data.put("B", 1);
		data.put("C", 2);
		data.put("D", 3);
		data.put("E", 4);
		data.put("F", 5);
		data.put("G", 6);
		data.put("H", 7);
		WeightedEdgeDigraph g = new WeightedEdgeDigraph(data.size());
		g.addEdge(new DirectedEdge(data.get("A"), data.get("C"), 1));
		g.addEdge(new DirectedEdge(data.get("C"), data.get("A"), 9));
		g.addEdge(new DirectedEdge(data.get("A"), data.get("D"), 4));
		g.addEdge(new DirectedEdge(data.get("A"), data.get("B"), 2));
		g.addEdge(new DirectedEdge(data.get("D"), data.get("C"), 2));
		g.addEdge(new DirectedEdge(data.get("E"), data.get("D"), 7));
		g.addEdge(new DirectedEdge(data.get("B"), data.get("C"), 5));
		g.addEdge(new DirectedEdge(data.get("B"), data.get("E"), 10));
		g.addEdge(new DirectedEdge(data.get("B"), data.get("F"), 2));
		g.addEdge(new DirectedEdge(data.get("C"), data.get("E"), 11));
		g.addEdge(new DirectedEdge(data.get("E"), data.get("G"), 1));
		g.addEdge(new DirectedEdge(data.get("F"), data.get("H"), 3));
		g.addEdge(new DirectedEdge(data.get("G"), data.get("E"), 3));
		g.addEdge(new DirectedEdge(data.get("G"), data.get("F"), 2));
		g.addEdge(new DirectedEdge(data.get("H"), data.get("G"), 1));
		/*
		g.addEdge(new DirectedEdge(0, 3, 1));
		g.addEdge(new DirectedEdge(0, 2, 2));
		g.addEdge(new DirectedEdge(1, 0, 2));
		g.addEdge(new DirectedEdge(2, 3, 1));
		g.addEdge(new DirectedEdge(2, 5, 2));
		g.addEdge(new DirectedEdge(3, 5, 6));
		g.addEdge(new DirectedEdge(3, 1, 5));
		g.addEdge(new DirectedEdge(4, 1, 1));
		g.addEdge(new DirectedEdge(3, 4, 1));
		g.addEdge(new DirectedEdge(3, 6, 5));
		g.addEdge(new DirectedEdge(5, 6, 10));
		g.addEdge(new DirectedEdge(6, 4, 3));
		*/
		
		System.out.println("#V: " + g.V() + "\t#Edges: " + g.E());
		DijkstraSP dsp = new DijkstraSP(g, 0);
		System.out.println("\n" + "Cost of shortest path to v: ");
		System.out.println(Arrays.toString(dsp.distTo));
		System.out.println("\n" + "Previous node: ");
		for (DirectedEdge e : dsp.edgeTo) {
			if (e != null)
				System.out.print(e.from() + " -> ");
		}
		System.out.println();
		int path = 1;
		System.out.print(0 + " -> ");
		for (DirectedEdge each : dsp.pathTo(path)) {
			System.out.print(each.to() + " -> ");
		}
		
	}
}

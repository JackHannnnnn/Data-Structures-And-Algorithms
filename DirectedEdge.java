package graph;

public class DirectedEdge {
	private final int from;
	private final int to;
	private final double weight;
	
	public DirectedEdge(int v, int w, double weight) {
		from = v;
		to = w;
		this.weight = weight;
	}
	public int from() { return from; }
	public int to() { return to; }
	public double weight() { return weight; }
	
}

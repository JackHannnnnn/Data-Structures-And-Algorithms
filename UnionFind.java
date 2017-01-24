package graph;
import java.util.*;

public class UnionFind<T> {

	int[] parent;
	T[] data;		// map index to value; O(1); 甚至可以不需要，只要你知道key-value的对应关系，一般0 - (N-1)
	int count;		// number of disjoint subsets
	
	public UnionFind(Set<T> data) {
		parent = new int[data.size()];
		Arrays.fill(parent, -1);
		this.data = (T[]) new Object[data.size()];
		Iterator<T> iter = data.iterator();
		for (int i = 0; i < data.size(); i++)
			this.data[i] = iter.next();
		count = data.size();
	}
	
	/* Representative element:
	 * -- the identifier of a disjoint subset/component is the element at the root
	 * -- find returns the index of root element in the data array
	 */
	public int find(int loc) {
		validateIndex(loc);
		int id = loc;
		while (parent[id] >= 0)
			id = parent[id];
		if (id == loc)
			return id;
		int old_parent = parent[loc];
		while (old_parent != id) {
			parent[loc] = id;
			loc = old_parent;
			old_parent = parent[loc];
		}
		return id;
	}
	
	public void union(int p, int q) {
		validateIndex(p);
		validateIndex(q);
		int rootP = find(p);	// path compression
		int rootQ = find(q);
		if (rootP == rootQ)    // p and q in the same set
			return;
		if (parent[rootQ] < parent[rootP]) {
			parent[rootP] = rootQ;
			parent[rootQ]--;
		} else if (parent[rootQ] > parent[rootP]) {
			parent[rootQ] = rootP;
			parent[rootP]--;
		} else {
			parent[rootQ] = rootP;
			parent[rootP]--;
		}
		count--;
	}
	
	private void validateIndex(int i) {
		if (i < 0 || i >= data.length)
			throw new IndexOutOfBoundsException();
	}
	
	public void print() {
		System.out.println(String.format("Number of disjoint subsets: %d", count));
		System.out.println(Arrays.toString(parent));   // OR output values in data
	}
	
	public static void main(String[] args) {
		Set<String> data = new LinkedHashSet<>();
		data.add("mike");
		data.add("jack");
		data.add("julie");
		data.add("jay");
		data.add("michael");
		data.add("carlos");
		System.out.println("Set in order (key/index => value): " + data);
		UnionFind uf = new UnionFind(data);
		uf.print();
		System.out.println(uf.find(5));
		uf.union(3, 5);			// union jay and carlos
		uf.print();
		System.out.println(uf.find(5));
		uf.union(1, 4);
		uf.print();
		uf.union(1, 3);
		uf.print();
		System.out.println("The next union does nothing but interior find will compress path");
		uf.union(5, 1);
		uf.print();
		uf.union(4, 2);
		uf.print();
	}
	
}

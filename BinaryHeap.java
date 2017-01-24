package chaofan;
import java.util.Arrays;
import java.util.NoSuchElementException;

// This is a binary min-heap implementation of the priority queue ADT.

public class BinaryHeap<T extends Comparable<T>> {
	private final static int CAPACITY = 2;
	
	private T[] data;		// T can have fields: value, priority
	private int size;
	
	public BinaryHeap() { this(CAPACITY); }
	public BinaryHeap(int capacity) {
		data = (T[]) new Comparable[capacity+1];   // Do not use Object
	}
	
	public BinaryHeap(T[] array) {
		size = array.length;
		data = (T[]) new Comparable[size+1];
		System.arraycopy(array, 0, data, 1, array.length);
		buildHeap();
	}
	
	
	public int size() { return size; }
	public boolean isEmpty() { return size == 0; }

	public void add(T value) {
		if (size >= data.length - 1)
			resize();
		size++;
		data[size] = value;   // add at right most of the tree
		bubbleUp(size);
	}
	
	public T peek() throws NoSuchElementException {
		if (isEmpty())
			throw new NoSuchElementException();
		return data[1];
	}
	// Retrieves and removes the head of this queue.
	public T remove()  {
		T result = peek();
		data[1] = data[size];
		data[size] = null;
		size--;
		bubbleDown(1);
		
		return result;
	}
	
	// store a HashMap<T key, index> to get O(logN); Even if T has index filed, because T will move up down on the heap.
	public void decreaseKey(T key, T newValue) {
		for (int i = 1; i <= size; i++) {
			if (data[i].equals(key)) {
				data[i] = newValue;
				bubbleUp(i);
				break;
			}
		}
	}
	
	@Override
	public String toString() {
		String result = "";
		for (int i = 1; i <= size; i++)
			result += data[i] + " ";
		return result;
	}
	
	/*
	 * the order of the elements will be opposite to the order in the heap tree. 
		Hence, if we want the elements to be sorted in ascending order, we need to build the heap tree 
		in descending order - the greatest element will have the highest priority.
		
		http://faculty.simpson.edu/lydia.sinapova/www/cmsc250/LN250_Weiss/L13-HeapSortEx.htm
		https://en.wikipedia.org/wiki/Heapsort
		The buildMaxHeap() operation is run once, and is O(n) in performance. 
		The siftDown() function is O(log(n)), and is called n times. 
		Therefore, the performance of this algorithm is O(n + n * log(n)) which evaluates to O(n log n).
	 */	
	public void heapSort(T[] array) {
		size = array.length;
		data = (T[]) new Comparable[size+1];
		System.arraycopy(array, 0, data, 1, array.length);
		buildHeap();
		// A heap is built but the data is not sorted
		
		for (int k = size; k >= 1; k--) {
			T temp = data[k];
			data[k] = data[1];
			data[1] = temp;
			size--;
			bubbleDown(1);
		}
		
		for (int k = 0; k < array.length; k++) {
			//array[k] = data[k+1];    // decreasing order
			array[k] = data[data.length-1-k];    // increasing order
		}
		
	}
	
	// runs at O(size):  The main idea is that in the build_heap algorithm the actual heapify cost is not O(log n)for all elements.
	// http://stackoverflow.com/questions/9755721/how-can-building-a-heap-be-on-time-complexity
	private void buildHeap() {
		for (int i = size/2; i >= 1; i--)
			// Go from lowest right parent (non-leaf) and proceed to left.
			// When finish one level go to next starting again from right.
			// at each node, percolate down the item to its proper place in this part of the subtree, e.g., subheap.
			// at level j from the bottom there are 2h−j nodes, and each might sift down j levels.
			// if we count from bottom to top, level-by-level, we see that the total time is proportional to 
			// the algorithm takes at least Omega(n) time (since it must access every element of the array at least once) 
			// so the total running time for BuildHeap is Theta(n).
			bubbleDown(i);  
	}
	

	
	private void bubbleDown(int k) {
		int index = k;
		while (hasLeftChild(index)) {    // the complete binary tree is filled from left to right
			int smallerChild = leftIndex(index);
			
			// bubble with the smaller child, if I have a smaller child
			// don't have to bubble down twice if bubbling with the smaller child
			if (hasRightChild(index) && data[rightIndex(index)].compareTo(data[smallerChild]) < 0) 
				smallerChild = rightIndex(index);
			if (data[smallerChild].compareTo(data[index]) < 0)
				swap(index, smallerChild);
			else
				break;
			index = smallerChild;
		}
	}
	
	private void bubbleUp(int k) {
		int index = k;
		while (hasParent(index) && data[index].compareTo(parent(index)) < 0) {
			swap(index, parentIndex(index));
			index = parentIndex(index);
		}
		
	}
	
	private void swap(int i, int j) {
		T temp = data[i];
		data[i] = data[j];
		data[j] = temp;
	}
	
	/*
	 * if we add a new value, it must go in position N+1; 
	 * if we delete a value, we must re-organize the tree so that the `gap' created by deleting is filled.
	   -- can traverse the tree by going through the array from first to Nth position.
      			for(i=1;i<N;i++) { process node in position i; }
			This gets us 'level-by-level' traversal!
	 */
	private int parentIndex(int i) {
		return i / 2;
	}
	
	private int leftIndex(int i) {
		return 2 * i;
	}
	
	private int rightIndex(int i) {
		return 2 * i + 1;
	}
	
	private boolean hasParent(int i) {
		return i > 1;     // non-root node always has a parent
	}
	
	private boolean hasLeftChild(int i) {
		return leftIndex(i) <= size;
	}
	
	private boolean hasRightChild(int i) {
		return rightIndex(i) <= size;
	}
	
	private T parent(int i) {
		return data[parentIndex(i)];
	}
	
	private void resize() {
		T[] temp = Arrays.copyOf(data, data.length*2);
		data = temp;
	}
	
	public void print() {
		for (int i = 1; i < data.length; i++)
			System.out.print(data[i] + " ");
	}
	
	public static void main(String[] args) {
		BinaryHeap<String> heap = new BinaryHeap<>();
		heap.add("p");
		heap.add("i");
		heap.add("r");
		heap.add("t");
		heap.add("y");
		System.out.println(heap.toString());
		heap.remove();
		System.out.println(heap.toString());
		
		BinaryHeap<Integer> heap1 = new BinaryHeap<>();
		Integer[] arr = { 10, 3, 7, 11, 4, 18, 5, 7, 89 };
		heap1.heapSort(arr);
		System.out.println(Arrays.toString(arr));
	}
	
}

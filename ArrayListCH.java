package chaofan;
import java.util.NoSuchElementException;
import java.util.List;
import java.util.Iterator;
import java.lang.Iterable;

/*
 * java.lang package is imported by default, no need to explicitly import it.
 * java.lang.IndexOutOfBoundsException;
 * java.lang.IllegalStateException;
 */

/*
 * A list has a 'size' (number of elements that have been added)
 * 		In Java, a list can be represented as an ArrayList object
 * 		1. Internally, the list is implemented using 'an array and a size field'.
 * 		2. Automatically resizes to fit its contents.
 * 		3. When a list is created, it is initially empty.
 * 
 * 1. ArrayList<Type> name = new ArrayList<Type>();
 * 2. As a parameter:
 * 				public static void name(ArrayList<Type> name) {}
 * 3. As a return:
 * 				public static ArrayList<Type> methodName(params)
 */

public class ArrayListCH<E> implements Iterable<E> {
	
	private class ArrayIterator implements Iterator<E> {
		private int j = 0;			// next element index
		private boolean removable = false;
		
		public boolean hasNext() {
			return j < size;
		}
		
		public E next() throws NoSuchElementException {
			if (!hasNext()) 
				throw new NoSuchElementException("No element left");
			removable = true;
			return data[j++];  // avoid temp = data[j], j++, return temp
		}
		
		public void remove() throws IllegalStateException {
			if (!removable) 
				throw new IllegalStateException();
			ArrayListCH.this.remove(j-1);		// next element has shifted one cell to the left 
			j--;
		}
	}
	
	/* public static final type name = value;
	 * Class constant: a global, unchangeable value in a class used to store and give names to important values used in code
	 * 		Math.PI
	 *		Integer.MAX_VALUE, Integer.MIN_VALUE
	 *		Color.GREEN
	 */
	private static final int CAPACITY = 16;
	private E[] data;
	private int size = 0;
	
	public ArrayListCH() { this(CAPACITY); }
	public ArrayListCH(int capacity) {
		data = (E[]) new Object[capacity];
	}
	
	public int size() {
		return size;
	}
	
	public boolean isEmpty() {
		return size == 0;
	}
	
	public void add(E e) {
		if (size == data.length)
			resize(data.length * 2);
		data[size] = e;
		size++;
	}
	
	public void add(int index, E e) throws IndexOutOfBoundsException {
		// cannot add data at an arbitrary location
		if (size == data.length)
			resize(data.length * 2);
		checkIndex(index, size + 1);
		for (int k = size; k > index; k--)
			data[k] = data[k-1];
		data[index] = e;
		size++;
	}
	
	public E get(int index) throws IndexOutOfBoundsException {
		checkIndex(index, size);
		return data[index];
	}
	
	public E remove(int index) throws IndexOutOfBoundsException {
		checkIndex(index, size);
		E temp = data[index];
		for (int k = index; k < size - 1; k++)
			data[k] = data[k+1];
		data[size-1] = null;		// DO NOT forget to clear data at the last location
		size--;
		return temp;
	}
	
	public boolean remove(E e) {
		for (int k = 0; k < size; k++) {
			if (data[k] == e) {
				for (int j = k; j < size -1; j++) {
					data[j] = data[j+1];
				}
				data[size-1] = null;
				size--;
				return true;
			}
		}
		return false;
	}
	
	public E set(int index, E e) throws IndexOutOfBoundsException {
		checkIndex(index, size);
		E temp = data[index];
		data[index] = e;
		return temp;
	}
	
	public boolean contains(E e) {
		for (int k = 0; k < size; k++) {
			if (data[k] == e)
				return true;
		}
		return false;
	}
	
	public int indexOf(E e) {
		for (int k = 0; k < size; k++) {
			if (data[k] == e)
				return k;
		}
		return -1;
	}
	
	public void clear() {
		for (int k = 0; k < size; k++) 
			data[k] = null;
		size = 0;
	}
	
	public Iterator<E> iterator() {
		return new ArrayIterator();
	}
	
	public E[] toArray() {
		return data;
	}
	
	public void print() {
		System.out.print("[");
		for (int k = 0; k < size - 1; k++) {
			System.out.print(data[k] + ", ");
		}
		if (size != 0)
			System.out.print(data[size-1]);
		System.out.print("]\n");
	}
	
	private void checkIndex(int index, int n) throws IndexOutOfBoundsException {
		if (index < 0 || index >= n)
			throw new IndexOutOfBoundsException("Invalid index: " + index);
	}
	
	private void resize(int capacity) {
		E[] temp = (E[]) new Object[capacity];
		for (int k = 0; k < size; k++) 
			temp[k] = data[k];
		data = temp;
	}
	
	
}

package chaofan;
import java.util.NoSuchElementException;

/*
 * Summary:
 * 1. checkIndex given an index
 * 2. consider corner case
 * 		a. consider head in terms of add or remove update method
 * 		b. consider (Empty list) size == 0 when getting an element
 * 3. consider size when changing the list
 * 4. move to the current node for getter and the previous node for update method
 * 
 * Tips on Test:
 * 		1. Boundary Cases:
 * 				Positive; zero; negative numbers
 * 				Right at the edge of an array or collection's size
 * 		2. Error Cases or Empty Cases:
 * 				0, -1, null;  an empty list or array
 */

public class SinglyLinkedList<E> {
	private static class Node<E> {
		private E val;
		private Node<E> next;
		public Node(E e) {
			this.val = e;
		}
		
		public E getElement() { return val; }
		public void setElement(E e) { val = e; }
		public Node<E> getNext() { return next; }    // Use Node<E> instead of Node
		public void setNext(Node<E> node) { next = node; }
	}
	
	private Node<E> head = null;
	private int size = 0;
	
	// constructs an empty list
	public SinglyLinkedList() {}
	
	public int size() {
		return size;
	}
	
	public void add(int index, E e) throws IndexOutOfBoundsException {
		// find the node at index-1 first
		checkIndex(index, size+1);
		Node<E> newest = new Node<>(e);
		// corner case
		if (index == 0) {
			newest.setNext(head);
			head = newest;
		} else {
			Node<E> prev = head;
			// set index = 3, think about how many steps this loop needs to walk
			for (int i = 1; i < index; i++) {
				prev = prev.getNext();
			}
			newest.setNext(prev.getNext());
			prev.setNext(newest);
		}
		size++;
	}
	
	public void addFirst(E e) {
		Node<E> newest = new Node<>(e);
		newest.setNext(head);
		head = newest;
		size++;
	}
	
	public void addLast(E e) {
		if (size == 0)
			head = new Node<>(e);
		else {
			Node<E> cur = head;
			while (cur.getNext() != null)
				cur = cur.getNext();
			cur.setNext(new Node<>(e));
		}
		size++;
	}
	
	public E get(int index ) throws IndexOutOfBoundsException {
		checkIndex(index, size);
		Node<E> cur = head;
		for (int i = 0; i < index; i++) {
			cur = cur.getNext();
		}
		return cur.getElement();
	}
	
	public E getFirst() throws NoSuchElementException {
		if (size == 0)
			throw new NoSuchElementException("The list is empty.");
		return head.getElement();
	}
	
	public E getLast() throws NoSuchElementException {
		if (size == 0)
			throw new NoSuchElementException("The list is empty.");
		Node<E> cur = head;
		while (cur.getNext() != null) 
			cur = cur.getNext();
		return cur.getElement();
	}
	
	public E remove(int index) throws IndexOutOfBoundsException {
		// Key: you need to create a local variable result first even if you can create one in the 'if'.
		// Block scoping: variable result declared in the if curly braces will be inaccessible after the {}
		E result = null;
		checkIndex(index, size);
		if (index == 0) {
			result = head.getElement();
			head = head.getNext();
		} else {
			Node<E> prev = head;
			for (int i = 1; i < index; i++) {
				prev = prev.getNext();
			}
			result = prev.getNext().getElement();
			prev.setNext(prev.getNext().getNext());
		}
		size--;
		return result;
	}
	
	public boolean remove(E e) throws NoSuchElementException {
		if (size == 0) 
			throw new NoSuchElementException("The list is empty");
		Node<E> dummy = new Node<>(null);
		dummy.setNext(head);
		Node<E> prev = dummy;
		while (prev.getNext() != null) {
			if (prev.getNext().getElement() == e) {
				prev.setNext(prev.getNext().getNext());
				// Always consider corner case: in case it removes the head
				head = dummy.getNext();
				size--;			// DO NOT forget size for update method
				return true; 	// Only remove the first occurrence
			}
			prev = prev.getNext();
		}
		return false;
	}
	
	public E removeFirst() throws NoSuchElementException {
		if (size == 0)
			throw new NoSuchElementException("List is empty");
		E result = head.getElement();
		head = head.getNext();
		return result;
	}
	
	public E removeLast() throws NoSuchElementException {
		if (size == 0)
			throw new NoSuchElementException("List is empty");
		if (size == 1) {
			E result = head.getElement();
			head = null;
			return result;
		} else {
			Node<E> prev = head;
			while (prev.getNext().getNext() != null)
				prev = prev.getNext();
			E result = prev.getNext().getElement();
			prev.setNext(null);
			return result;
		}
	}
	
	public E set(int index, E e) throws IndexOutOfBoundsException {
		checkIndex(index, size);
		Node<E> cur = head;
		for (int i = 0; i < index; i++) 
			cur = cur.getNext();
		E result = cur.getElement();
		cur.setElement(e);
		return result;
	}
	
	public boolean contains(E e) {
		Node<E> cur = head;
		while (cur != null) {
			if (cur.getElement() == e)
				return true;
			cur = cur.getNext();
		}
		return false;
	}
	
	public int indexOf(E e) {
		int index = 0;
		Node<E> cur = head;
		while (cur != null) {
			if (cur.getElement() == e)
				return index;
			cur = cur.getNext();
			index++;
		}
		return -1;
	}
	
	public boolean equals(Object o) {
		if (o == null) return false;
		if (this.getClass() != o.getClass()) return false;
		SinglyLinkedList<E> other = (SinglyLinkedList<E>) o;    // use non-parameterized type
		if (this.size() != other.size()) return false;
		Node<E> walkA = this.head;
		Node<E> walkB = other.head;
		while (walkA != null) {
			if (walkA.getElement() != walkB.getElement()) return false;
			walkA = walkA.getNext();
			walkB = walkB.getNext();
		}
		return true;
	}
	
	public void clear() {
		head = null;
		size = 0;
	}
	
	public SinglyLinkedList<E> clone() throws CloneNotSupportedException {
		// always use inherited Object.clone() to create the initial copy
		// Object.clone() is protected and invisible
		// The author of a class must explicitly declare support for cloning by formally declaring that the class 
		// implements the Cloneable interface, and by declaring a public version of the clone() method.
		SinglyLinkedList<E> other = (SinglyLinkedList<E>) super.clone();
		if (size > 0) {
			other.head = new Node<>(head.getElement());
			Node<E> walkA = head.getNext();
			Node<E> walkB = other.head;
			while (walkA != null) {
				walkB.setNext(new Node<>(walkA.getElement()));
				walkA = walkA.getNext();
				walkB = walkB.getNext();
			}
		}
		return other;
	}
	
	public void print() {
		System.out.print("[");
		if (size == 0)
			System.out.print("");
		else {
			Node<E> cur = head;
			while (cur.getNext() != null) {
				System.out.print(cur.getElement() + ", ");
				cur = cur.getNext();
			}
			System.out.print(cur.getElement());
		}
		System.out.print("]\n");
	}
	
	// check whether the index is in the range [0, n-1]
	private void checkIndex(int index, int n) throws IndexOutOfBoundsException {
		if (index < 0 || index >= n)
			throw new IndexOutOfBoundsException("Invalid index " + index);
	}
	
}

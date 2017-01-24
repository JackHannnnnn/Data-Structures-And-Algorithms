package chaofan;

public class ListTest {
	public static void main(String[] args) {
		SinglyLinkedList<Integer> list = new SinglyLinkedList<>();
		list.print();
		
		list.addFirst(1);
		list.addFirst(10);
		list.addFirst(11);
		list.addFirst(15);
		list.print();
		
		list.addLast(3);
		list.addLast(7);
		list.print();
		
		list.add(0, 0);
		list.add(1, 1);
		list.add(list.size() - 1, 10);
		list.add(list.size(), 9);
		// list.add(list.size() + 1, 0);
		// list.add(-1, -1);
		list.print();
		
		System.out.println(list.getFirst());
		System.out.println(list.getLast());
		System.out.println(list.get(0));
		System.out.println(list.get(6));
		System.out.println(list.get(list.size() - 1));
		list.print();
		// list.get(-1);
		// list.get(list.size());
		
		System.out.println(list.remove(0));
		System.out.println(list.remove(3));
		System.out.println(list.remove(list.size()-1));
		// list.remove(list.size());
		// list.remove(-1);
		list.print();
		
		System.out.println(list.remove((Integer) 10));
		System.out.println(list.remove((Integer) 8));
		list.print();
		
		list.set(0, 0);
		list.set(list.size()-1, 9);
		// list.set(-1, -1);
		// list.set(list.size(), 9);
		list.print();
		
		System.out.println(list.contains((Integer) 9));
		System.out.println(list.contains((Integer) 10));
		
		System.out.println(list.indexOf((Integer) 15));
		System.out.println(list.indexOf((Integer) 9));
		System.out.println(list.indexOf((Integer) 10));
		
		list.clear();
		list.print();
		System.out.println(list.indexOf((Integer) 9));
		System.out.println(list.contains((Integer) 10));
	}
}

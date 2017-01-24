package chaofan;
import java.util.Arrays;
import java.util.Iterator;

public class ArrayListCHTest {
	public static void main(String[] args) {
		ArrayListCH<Integer> list = new ArrayListCH<>(3);
		list.print();
		
		list.add(1);
		list.add(10);
		list.add(11);
		list.add(15);
		list.add(3);
		list.add(7);
		list.print();
		
		list.add(0, 0);
		list.add(1, 1);
		list.add(list.size() - 1, 10);
		list.add(list.size(), 9);
		// list.add(list.size() + 1, 0);
	    // list.add(-1, -1);
		list.print();
		
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
		System.out.println(list.indexOf((Integer) 4));
		
		System.out.println("Iterator is being tested.");
		Iterator<Integer> iter = list.iterator();
		while (iter.hasNext()) {
			int val = iter.next();
			System.out.print(val + " ");
		}
		System.out.println();
		
		// Another traversal
		for (Integer i : list)    // ArrayListCH has to implement the interface java.lang.Iterable to be iterable
			System.out.print(i + " ");
		System.out.println();
		
		Iterator<Integer> filter = list.iterator();
		while (filter.hasNext()) {
			if (filter.next() > 3)
				filter.remove();
		}
		list.print();
		
		System.out.println(list.getClass());
		System.out.println(Arrays.toString(list.toArray()));
		
		
		list.clear();
		list.print();
		System.out.println(list.indexOf((Integer) 9));
		System.out.println(list.contains((Integer) 10));
		
		
	}
}

package chaofan;

public class Sorting {
	// Stability of Sorting algorithm: relative order of same elements doesn't change
	
	public static void insertionSort(int[] data) {
		if (data == null) return;
		/* rearranged in nondecreasing order
		 * for k from 1 to n − 1 do
		 * 	   Insert A[k] at its proper location within A[0], A[1], ..., A[k].
		 * 		- The first k elements are sorted
		 */
		
		for (int i = 1; i < data.length; i++) {
			int cur = data[i];
			int j = i;
			while (j > 0 && data[j-1] > cur) {
				data[j] = data[j-1];
				j--;
			}
			data[j] = cur;
		}
	}
	
	public static int binarySearch(int[] data, int x) {
		if (data == null) return -1;
		int low = 0;
		int high = data.length - 1;
		int mid;
		while (low <= high) {
			mid = (low + high) / 2;
			if (data[mid] == x) {
				return mid;
			} else if (data[mid] < x) {
				low = mid + 1;
			} else {
				high = mid - 1;
			}
		}
		return -1;
	}
	
	public static void reverseArray(int[] data) {
		if (data == null) return;
		for (int i = 0; i < data.length / 2; i++)
			swap(data, i, data.length - 1 - i );
	}
	
	public static void selectionSort(int[] data) {
		if (data == null)
			return;
		for (int i = 0; i < data.length; i++) {
			int minIndex = i;			// no need to store a min value;  in place
			for (int j = i + 1; j < data.length; j++) {
				if (data[j] < data[minIndex]) {
					minIndex = j;
				}
			}
			swap(data, i, minIndex);
		}
	}
	
	public static void bubbleSort(int[] data) {
		for (int i = data.length - 1; i >= 1; i--) {
			for (int j = 0; j < i; j++) {
				if (data[j] > data[j+1]) {
					int temp = data[j];
					data[j] = data[j+1];
					data[j+1] = temp;
				}
			}
		}
	}
	
	public static void shortBubbleSort(int[] data) {
		int pass_num = data.length - 1;
		boolean exchange = true;
		while (pass_num > 0 && exchange) {
			exchange = false;
			for (int i = 0; i < pass_num; i++) {
				if (data[i] > data[i+1]) {
					exchange = true;
					int temp = data[i];
					data[i] = data[i+1];
					data[i+1] = temp;
				}
			}
		}
	}
	
	public static void mergeSort(int[] data) {
		if (data != null) {
			int[] temp = new int[data.length];
			mergeSortHelper(data, temp, 0, data.length-1);
		}
	}
	
	private static void mergeSortHelper(int[] data, int[] temp, int low, int high) {
		if (low >= high)
			return;
		int mid = (low + high) / 2;
		mergeSortHelper(data, temp, low, mid);
		mergeSortHelper(data, temp, mid+1, high);
		
		int i = low;
		int j = mid + 1;
		// start from 0 only takes advantage of the first high-low cells in temp
		for (int k = 0; k <= high - low; k++) {
			if (j <= high && (i > mid || data[i] > data[j])) {      // use i > mid
				temp[k] = data[j++];
			} else {
				temp[k] = data[i++];
			}
		}
		
		for (int k = 0; k <= high - low; k++)
			data[low+k] = temp[k];
	}
	
	
	
	
	public static void quickSort(int[] data) {
		if (data != null) {
			quickSortHelper(data, 0, data.length-1);
		}
	}
	
	private static void quickSortHelper(int[] data, int low, int high) {
		if (low >= high) 
			return;
		int i = low;
		int j = high;
		int pivot = data[(low + high) / 2];
		while (i <= j) {
			/**
             * In each iteration, we will identify a number from left side which
             * is greater than the pivot value, and also we will identify a number
             * from right side which is less than the pivot value. Once the search
             * is done, then we exchange both numbers.
             */
			while (i <= j && data[i] < pivot) {
				i++;
			}
			while (i <= j && data[j] > pivot) {
				j--;
			}
			if (i <= j) {
				swap(data, i++, j--);		// move index to next position on both sides
			}
		}
		
		quickSortHelper(data, low, j);
		quickSortHelper(data, i, high);
	}
	
	private static void swap(int[] arr, int i, int j) {
		int temp = arr[i];
		arr[i] = arr[j];
		arr[j] = temp;
	}
}

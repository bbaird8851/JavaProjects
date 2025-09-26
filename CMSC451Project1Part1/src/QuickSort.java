/**
 * CMSC451 Project 1
 * @author Brandon Baird
 * Source: GeeksForGeeks.org/dsa/java-program-for-quicksort
 * 
 * Utilize the QuickSort Algorithm for the Benchmark and record its efficiency.
 */

public class QuickSort extends AbstractSort{

	@Override
	public void sort(int[] array) {
		startSort();
		quickSort(array, 0, array.length - 1);
		endSort();

	}

	private void quickSort(int[] arr, int low, int high) {
		if(low < high) {
			int pi = partition(arr, low, high);
			quickSort(arr, low, pi - 1);
			quickSort(arr, pi + 1, high);
		}
	}

	private int partition(int[] arr, int low, int high) {
		int pivot = arr[high];
		int i = low - 1;

		for (int j = low; j < high; j++) {
			incrementCount(); //critical operation
			
			// If current element is smaller than or
            // equal to pivot
			if (arr[j] <= pivot) {
				i++;
				int temp = arr[i]; 
				arr[i] = arr[j]; 
				arr[j] = temp;
			}
		}
		int temp = arr[i + 1]; 
		arr[i + 1] = arr[high]; 
		arr[high] = temp;
		
		return i + 1;
	}
}

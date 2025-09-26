/**
 * CMSC451 Project 1
 * @author Brandon Baird
 * Source: GeeksForGeeks.org/dsa/java-program-for-quicksort
 * 
 * Utilize the BubbleSort Algorithm for the Benchmark and record its efficiency.
 */

public class BubbleSort extends AbstractSort{

	@Override
	public void sort(int[] array) {
		startSort();
		bubbleSort(array, array.length);
		endSort();
	}

	// An optimized version of Bubble Sort
	private void bubbleSort(int[] arr, int n) {
		if (n <= 1) {
			return;
		}

		int i, j, temp;
		boolean swapped;
		// After each pass, the largest element is at the end; shrink the pass bound.
		for (i = 0; i < n - 1; i++) {
			swapped = false;
			for (j = 0; j < n - i - 1; j++) {
				incrementCount();//critical operation
				
				if (arr[j] > arr[j + 1]) {

					// Swap arr[j] and arr[j+1]
					temp = arr[j];
					arr[j] = arr[j + 1];
					arr[j + 1] = temp;
					swapped = true;
				}
			}
			if (!swapped) break; // already sorted; stop early
		}
	}
}


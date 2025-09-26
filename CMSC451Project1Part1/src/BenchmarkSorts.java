/**
 * CMSC451 Project 1
 * @author Brandon Baird
 * BenchmarkSorts: creates the testing environment for both algorithms, 
 * uses a random number generator for the datasets.
 */

import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.Random;

public class BenchmarkSorts {

	public static void main(String[] args) throws Exception {
		AbstractSort algorithm1 = new QuickSort();
		AbstractSort algorithm2 = new BubbleSort();

		//12 sizes
		int[] sizes = {100, 200, 300, 400, 500, 600, 700, 800, 900, 1000, 5000, 10000};

		runBenchmark(algorithm1, "QuickSort.txt", sizes);
		runBenchmark(algorithm2, "BubbleSort.txt", sizes);
	}

	private static void runBenchmark(AbstractSort sorter, String fileName, int[] sizes) throws Exception {
		try (PrintWriter out = new PrintWriter(new FileWriter(fileName))) {
			//For each value of n
			for (int n: sizes) {
				out.print(n);
				//Produce 40 data sets
				for (int i = 0; i < 40; i++) {
					int[] data = generateRandomSets(n);
					int[] copy = data.clone();

					//should examine the results of each call to verify if the data is properly sorted
					sorter.sort(copy);
					if (!sorted(copy)) {
						throw new UnsortedException("Array not sorted");
					} 

					out.print(" " + sorter.getCount() + " " + sorter.getTime());
				}
				out.println();
			}
			//sanity checks for generated testing files.
			System.out.println("Finished writing: " + fileName);
		} catch(Exception e) {
			System.err.println("Error writing file: " + fileName);
			e.printStackTrace();
		}
	}

	//RNG
	private static int[] generateRandomSets(int n) {
		Random rng = new Random();
		int[] arr = new int[n];
		for (int i = 0; i < n; i++) arr[i] = rng.nextInt();
		return arr;
	}

	//check
	private static boolean sorted(int[] arr) {
		for (int i = 1; i < arr.length; i++) if (arr[i - 1] > arr[i]) return false;
		return true;
	}

}

/**
 * CMSC451 Project 1
 * @author Brandon Baird
 * 
 * Create the abstract class to aid in the benchmarking of both algorithms.
 *
 */

public abstract class AbstractSort {
	private long count;
	private long startTime;
	private long endTime;
	
	public abstract void sort(int[] array);
	
	protected void startSort() {
		count = 0;
		startTime = System.nanoTime();
	}
	
	protected void endSort() {
		endTime = System.nanoTime();
	}
	
	protected void incrementCount() {
		count++;
	}
	
	public long getCount() {
		return count;
	}
	
	public long getTime() {
		return endTime - startTime;
	}
	
}

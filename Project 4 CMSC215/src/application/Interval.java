package application;

/**
 * @author Brandon Baird
 * @date 10/06/24
 * 
 * @function generic skeleton object that 
 * can be used for chronological comparison
 * 
 */
public class Interval<T extends Comparable<T>> implements Comparable<Interval<T>> {
	private T start;
	private T end;

	public Interval(T start, T end) {
		if(start.compareTo(end) > 0) {
			throw new IllegalArgumentException("The start can't be greater than the end.");
		}
		this.start = start;
		this.end = end;
	}

	public T getStart() {
		return start;
	}
	public T getEnd() {
		return end;
	}

	public boolean within(T obj) {
		return (obj.compareTo(this.start) >= 0 && obj.compareTo(this.end) <= 0);
	}

	public boolean subinterval(Interval<T> other) {
		return (other.start.compareTo(this.start) >= 0 && other.end.compareTo(this.end) <= 0);
	}

	public boolean overlaps(Interval<T> other) {
		return (this.start.compareTo(other.end) <= 0 && other.start.compareTo(this.end) <= 0);
	}


	@Override
	public int compareTo(Interval<T> other) {
		if(this.start.compareTo(other.start) != 0) {
			return this.start.compareTo(other.start);
		} else {
			return this.end.compareTo(other.end);
		}
	}
}

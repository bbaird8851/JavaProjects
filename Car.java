package myLinkedList;

public class Car {
	private final int tag;
	private final String owner;

	public Car(int tag, String owner) {
		this.tag = tag;
		this.owner = owner;
	}

	@Override
	public String toString() {
		return "Car [tag=" + tag + ", owner=" + owner + "]";
	}
}

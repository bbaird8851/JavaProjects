package myLinkedList;

public class Driver {
	public static void main(String[] args) {
		MyLinkedList<String> newList = new MyLinkedList<String>();
		newList.add("Sarah").add("Rose").add("Peter").add("Kelly").add("Albert");
		System.out.println(newList);
		System.out.println(newList.find("Rose") ? "Found Rose" : "Did not find Rose");
		System.out.println(newList.find("Carlos") ? "Found Carlos" : "Did not find Carlos");
		System.out.println("Last Element: " + newList.getLastElement());

		newList.insertElementAfter("Peter", "Rick");
		System.out.println("Result of inserting Rick after Peter: " + newList);

		newList.insertElementBefore("Peter", "Patrick");
		System.out.println("Result of inserting Patrick before Peter: " + newList);

		newList.delete("Kelly");
		System.out.println("Result after deleting Kelly: " + newList);

		MyLinkedList<String> secondList = new MyLinkedList<String>();
		secondList.add("John").add("Carlos").add("Jeremy").add("Kyle").add("Sandra").add("Zoe");
		System.out.println(secondList);

		MyLinkedList<String> inBetween = secondList.getListWithDataInBetween("Jeremy", "Sandra");
		System.out.println("InBetween: " + inBetween);

		/* Why the following assignment does not compile? */
		/* MyLinkedList<Car> c = new MyLinkedList<Car>(); */
	}
}

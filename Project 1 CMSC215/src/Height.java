/**
 * 
 * @author: Brandon Baird
 *
 */
public class Height {
	private int feet;
	private int inches;
	
	public Height(int feet, int inches) {
		if(inches >= 12) {
			feet += inches / 12;
			inches = inches % 12;
		}
		
		this.feet = feet;
		this.inches = inches;
	}
	
	public Height() {
		this(0, 0);
	}
	
	public int toInches() {
		int totalInches = (feet * 12) + inches;
		
		return totalInches;
	}
	
	public int getFeet() {
		return feet;
	}
	
	public int getInches(){
		return inches;
	}
	
	@Override
	public String toString() {
		return feet + "' " + inches + "\" ";
	}
}

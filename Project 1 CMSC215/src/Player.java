/**
 * 
 * @author: Brandon Baird
 *
 */
public class Player {
	private String name;
	private Height height;
	private int age;
	
	//default constructor
	public Player() {
		this("NONAME", new Height(), 0);
	}
	
	//immutable constructor add a new immutable height object.
	public Player (String name, Height height, int age) {
		if(name == null || height == null) {
			throw new IllegalArgumentException("Values cannot be null.");
		}
		
		this.name = name;
		this.height = new Height(height.getFeet(), height.getInches());
		this.age = age;
	}
	
	//getters
	public String getName() {
		return name;
	}

	public Height getHeight() {
		return new Height(height.getFeet(), height.getInches());
	}

	public int getAge() {
		return age;
	}
	
	@Override
	public String toString() {
		return "Player name: " + name + " Height: " + height.toString() + " Age: " + age;
	}
}

/**
 * @author Brandon Baird
 * Undergraduate Class
 *
 */
public class Undergraduate extends Student{

	private String classRank;

	public Undergraduate(String name, int creditHours, double qualityPoints, String classRank) {
		super(name, creditHours, qualityPoints);
		this.classRank = classRank;
	}
	
	@Override
	public boolean eligibleForHonorSociety() {
		if(!super.eligibleForHonorSociety()) {
			return false;
		} else {
			return classRank.equalsIgnoreCase("Junior") || classRank.equalsIgnoreCase("Senior");
		}
	}
	
	@Override
	public String toString() {
		return super.toString() +  " " + classRank;
	}
	

}

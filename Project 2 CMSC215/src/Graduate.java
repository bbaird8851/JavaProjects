/**
 * @author Brandon Baird
 * Graduate Class
 *
 */
public class Graduate extends Student{
	private String degreeSought;
	
	public Graduate(String name, int creditHours, double qualityPoints, String degreeSought) {
		super(name, creditHours, qualityPoints);
		this.degreeSought = degreeSought;
	}
	
	@Override
	public boolean eligibleForHonorSociety() {
		if(!super.eligibleForHonorSociety()) {
			return false;
		} else {
			return degreeSought.equalsIgnoreCase("Masters");
		}
	}
	
	@Override
	public String toString() {
		return super.toString() +  " " + degreeSought;
	}
}

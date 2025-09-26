/**
 * @author Brandon Baird
 * @date 09/21/24
 * 
 * @function provide a class to calculate the 
 * multiple units available for the user to input.
 */
package application;

public final class TripCost {
	private static final double KILOMETERS_PER_MILE = 1.609347;
	private static final double LITERS_PER_GALLON = 3.78541178;

	private final double distance, gasCost, gasMileage, hotelCost, foodCost, attractionsCost;
	private final int numOfDays;
	private final String distanceUnit, gasCostUnit, gasMileageUnit;


	public TripCost(double distance, double gasCost, double gasMileage, int numOfDays,
			double hotelCost, double foodCost, double attractionsCost, String distanceUnit, 
			String gasCostUnit, String gasMileageUnit) {
		this.distance = distance;
		this.gasCost = gasCost;
		this.gasMileage = gasMileage;
		this.numOfDays = numOfDays;
		this.hotelCost = hotelCost;
		this.foodCost = foodCost;
		this.attractionsCost = attractionsCost;
		this.distanceUnit = distanceUnit;
		this.gasCostUnit = gasCostUnit;
		this.gasMileageUnit = gasMileageUnit;
	}

	public double getTotalTripCost() {
		double convertedDistance = distance;
		double convertedGasCost = gasCost;
		double convertedGasMileage = gasMileage;

		if(distanceUnit.equals("kilometers")) {
			convertedDistance = distance * KILOMETERS_PER_MILE;
		}

		if(gasCostUnit.equals("dollars/liter")) {
			convertedGasCost = gasCost / LITERS_PER_GALLON;
		}

		if (gasMileageUnit.equals("kms/liter")) {
			convertedGasMileage = gasMileage * (KILOMETERS_PER_MILE / LITERS_PER_GALLON);
		}

		double gasolineCost = (convertedDistance/convertedGasMileage) * convertedGasCost;
		double totalTripCost = gasolineCost + ((hotelCost* numOfDays) + (foodCost* numOfDays)) + attractionsCost;
		return totalTripCost;
	}

}

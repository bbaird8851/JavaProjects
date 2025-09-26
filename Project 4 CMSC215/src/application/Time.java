/**
 * @author Brandon Baird
 * @date 10/06/24
 * 
 * @function Time object to be used in place of the generic
 * 
 */
package application;

public final class Time implements Comparable<Time>{
	private int hours;
	private int minutes;
	private String meridian;

	public Time(int hours, int minutes, String meridian) throws InvalidTime {
		checkIfTimeIsValid(hours, minutes, meridian);
		this.hours = hours;
		this.minutes = minutes;
		this.meridian = meridian;
	}

	public Time (String time) throws InvalidTime {
		
		if (time == null || time.length() != 8 && 
			time == null || !time.matches("\\d{2}:\\d{2} (AM|PM)")) {
			throw new InvalidTime("Time string must be in the format 'HH:MM AM/PM'.");
		}
		//Separate the 00:00 AM/PM part first
		String[] fullPart = time.split(" ");
		//Separate the numbers in between ":"
		String[] numberParts = fullPart[0].split(":");

		int parsedHours = Integer.parseInt(numberParts[0]);
		int parsedMins = Integer.parseInt(numberParts[1]);
		String parsedMeridian = fullPart[1];

		//check the format
		checkIfTimeIsValid(parsedHours, parsedMins, parsedMeridian);

		this.hours = parsedHours;
		this.minutes = parsedMins;
		this.meridian = parsedMeridian;
	}

	public int getHours() {
		return hours;
	}

	public int getMinutes() {
		return minutes;
	}

	public String getMeridian() {
		return meridian;
	}

	@Override
	public int compareTo(Time other) {
		
		if (!this.meridian.equals(other.meridian)) {
			if(this.meridian.equals("AM")) {
				return -1;
			} else {
				return 1;
			}
		}
		
		int this24Hour = format24HoursHelper(this.hours, this.meridian);
		int other24Hour = format24HoursHelper(other.hours, other.meridian);
		
		
		if (this24Hour != other24Hour) {
			return Integer.compare(this24Hour, other24Hour);
		}
		return Integer.compare(this.minutes, other.minutes);
		 
	}

	
	private int format24HoursHelper(int hours, String meridian) {
		//If Midnight, 12:00AM = 00:00 in 24 hour format
		//add 12 hours to whatever time in the PM.
		if (hours == 12 && meridian.equals("AM")) {
			return 0;
		} else if (hours != 12 && meridian.equals("PM")) {
			return hours + 12;
		}
		return hours;
	}
	
	 

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {return true;}
		if (obj == null || getClass() != obj.getClass()) return false;

		Time time = (Time) obj;

		if (hours != time.hours) {return false;}
		if (minutes != time.minutes) {return false;}
		return meridian.equals(time.meridian);
	}

	public String toString() {
		return String.format("%02d:%02d %s", hours, minutes, meridian);
	}


	private void checkIfTimeIsValid(int hours, int mins, String meridian) {
		if (hours < 1 || hours > 12) {
			throw new IllegalArgumentException("Range must be between 1 and 12");
		}
		if (minutes < 0 || minutes > 59) {
			throw new IllegalArgumentException("Range must be between 0 and 59");
		}
		if (!meridian.equals("AM") && !meridian.equals("PM")) {
			throw new IllegalArgumentException("Meridian must be either 'AM' or 'PM'.");
		}
	}

}

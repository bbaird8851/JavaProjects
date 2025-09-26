/**
 * @author Brandon Baird
 * @date 10/06/24
 * 
 * @function extension of Exception
 * 
 */
package application;

public final class InvalidTime extends Exception {

	private String errorMessage;

	public InvalidTime(String errorMessage) {
		this.errorMessage = errorMessage;
	}


	@Override
	public String getMessage() {
		return errorMessage;
	}
}
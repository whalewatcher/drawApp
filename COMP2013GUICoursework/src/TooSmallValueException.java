
public class TooSmallValueException extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public TooSmallValueException(String str, double value){
		super("The value " + value + " is too small for " + str);
	}
}

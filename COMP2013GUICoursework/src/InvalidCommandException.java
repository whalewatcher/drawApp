
public class InvalidCommandException extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public InvalidCommandException(String str){
		super("The command is invalid: " + str);
	}
}

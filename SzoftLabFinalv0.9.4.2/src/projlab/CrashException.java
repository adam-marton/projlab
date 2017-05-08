package projlab;

/**
 * Kivetelkezeleshez
 * 
 * @author Adam
 */
public class CrashException extends Exception {

	private static final long serialVersionUID = 1L;

	public CrashException() {
	}

	public CrashException(String message) {
		super(message);
	}
}

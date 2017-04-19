package szkeleton;

/**
 * Kivetelkezeleshez
 * @author Adam
 */
public class CrashException extends Exception {

    public CrashException() {
    }

    public CrashException(String message) {
        System.out.println(message);
        System.exit(0);
    	//super(message);
    }
}

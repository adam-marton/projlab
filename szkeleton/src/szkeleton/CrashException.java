package szkeleton;

/**
 *
 * @author Ádám
 */
public class CrashException extends Exception {

    public CrashException() {
    }

    public CrashException(String message) {
        super(message);
    }
}
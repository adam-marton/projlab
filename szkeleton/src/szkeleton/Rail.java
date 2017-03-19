package szkeleton;

/**
 * 
 */
public class Rail extends Tile {

    /**
     * Default constructor
     */
    public Rail() {
    	System.out.println("Je suis Rail, peut-etre Tunnel"); 
    				//(Rail vagyok, talán Alagút) ha visible akk Rail, amúgy Alagút
    }

    /**
     * 
     */
    private boolean visible;

    /**
     * @return
     */
    public boolean isVisible() {
        // TODO implement here
        return false;
    }

    /**
     * 
     */
    @Override
    public void move() {
        // TODO implement here
    }
}
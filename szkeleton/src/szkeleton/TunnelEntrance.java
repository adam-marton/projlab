package szkeleton;

/**
 * 
 */
public class TunnelEntrance extends Tile {

    /**
     * Default constructor
     */
    public TunnelEntrance() {
    	System.out.println("Mi nombre es TunnelEntrance"); //A nevem TunnelEntrance
    }

    /**
     * 
     */
    private boolean state;

    /**
     * @return
     */
    public boolean isActive() {
        // TODO implement here
        return false;
    }

    /**
     * 
     */
    @Override
    public void changeState() {
        state = !state;
    }

    /**
     * 
     */
    @Override
    public void move() {
        // TODO implement here
    }
}
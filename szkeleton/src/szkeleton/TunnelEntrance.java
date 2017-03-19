package szkeleton;

/**
 * 
 */
public class TunnelEntrance extends Tile {

    /**
     * Az alagútbejárat állapotát tárolja. (Nyitva/Zárva)
     */
    private boolean state;

    /**
     * Default constructor
     */
    public TunnelEntrance() {
    }

    /**
     * @return
     */
    public boolean isActive() {
        
        return false;
    }

    /**
     * A state field értékét változtatja.
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
        if(!isActive()) {
            
        }
    }
}
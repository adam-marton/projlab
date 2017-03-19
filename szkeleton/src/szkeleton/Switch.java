package szkeleton;

/**
 * 
 */
public class Switch extends Tile {

    /**
     * Default constructor
     */
    public Switch() {
    	System.out.println("Én lenni Switch"); //érthető...
    }

    /**
     * 
     */
    private Tile activeDir;

    /**
     * 
     */
    private Tile cDir;

    /**
     * visszaadja az éppen aktuális kimeneti irányt
     * @return
     */
    public Tile getActiveDir() {
        return activeDir;
    }

    /**
     * @param t
     */
    public void setDirC(Tile t) {
        // TODO implement here
    }

    /**
     * 
     */
    @Override
    public void changeState() {
        // TODO implement here
    }

    /**
     * 
     */
    @Override
    public void move() {
        // TODO implement here
    }
}
package szkeleton;

/**
 * 
 */
public abstract class Tile {

    /**
     * Default constructor
     */
    public Tile() {
    }

    /**
     * 
     */
    private Tile aDir;

    /**
     * 
     */
    private Tile bDir;

    /**
     * 
     */
    private Train train;

    /**
     * 
     */
    private TrainElement ele;

    /**
     * 
     */
    private boolean moveable;


    /**
     * @param t
     */
    public void setDirA(Tile t) {
        // TODO implement here
    }

    /**
     * @param t
     */
    public void setDirB(Tile t) {
        // TODO implement here
    }

    /**
     * @return
     */
    public Tile getDirA() {
        // TODO implement here
        return null;
    }

    /**
     * @return
     */
    public Tile getDirB() {
        // TODO implement here
        return null;
    }

    /**
     * @param t
     */
    public void setTrain(Train t) {
        // TODO implement here
    }

    /**
     * @param e
     */
    public void setElement(TrainElement e) {
        // TODO implement here
    }

    /**
     * @return
     */
    public boolean checkCrash() {
        // TODO implement here
        return false;
    }

    /**
     * @return
     */
    public boolean isMovable() {
        // TODO implement here
        return false;
    }

    /**
     * @return
     */
    public boolean isFree() {
        // TODO implement here
        return false;
    }

    /**
     * 
     */
    public void changeState() {
        // TODO implement here
    }

    /**
     * 
     */
    public void move() {
        // TODO implement here
    }

    /**
     * 
     */
    public void enablemove() {
        // TODO implement here
    }
}
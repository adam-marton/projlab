package szkeleton;

/**
 * 
 */
public class TrainElement {

    /**
     * Default constructor
     */
    public TrainElement() {
    	System.out.println("Mi chiamo TrainElement");
    }

    /**
     * @param pos 
     * @param color
     */
    public TrainElement(Tile pos, Color color) {
    	System.out.println("Mi chiamo TrainElement, con colore: " + color); //TrainElement vagyok ezzel a sz�nnel: ...
    }
    
    /**
     * 
     */
    private Tile pos;

    /**
     * 
     */
    private Tile prevPos;

    /**
     * 
     */
    private Color color;

    /**
     * 
     */
    private boolean empty;

    /**
     * 
     */
    private boolean nextToFree;

    /**
     * @return
     */
    public Tile getPosition() {
        // TODO implement here
        return null;
    }

    /**
     * @return
     */
    public Tile getPrevPos() {
        // TODO implement here
        return null;
    }

    /**
     * @param pos
     */
    public void move(Tile pos) {
        // TODO implement here
    }

    /**
     * 
     */
    public void getOff() {
        // TODO implement here
    }

    /**
     * @return
     */
    public Color getColor() {
        // TODO implement here
        return color;
    }

    /**
     * @return
     */
    public boolean isEmpty() {
        // TODO implement here
        return false;
    }

    /**
     * @return
     */
    public boolean isNextToFree() {
        // TODO implement here
        return false;
    }
}
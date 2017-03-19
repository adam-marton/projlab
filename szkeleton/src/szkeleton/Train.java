package szkeleton;

import java.util.List;

/**
 * 
 */
public class Train {

    /**
     * Default constructor
     */
    public Train() {
    	System.out.println("Ich bin egy Train");
    }
    
    /**
     * @param pos 
     * @param color
     */
    public Train(Tile pos, Color color) {
    	System.out.println("Ich bin egy Train, mit viele TrainElement"); //Train vagyok sok TrainElement-el
    }

    /**
     * 
     */
    private List<TrainElement> elements;

    /**
     * 
     */
    private Tile pos;

    /**
     * 
     */
    private Tile prevPos;

    /**
     * @param e
     */
    public void addElement(TrainElement e) {
        // TODO implement here
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
    public void setPos(Tile pos) {
        // TODO implement here
    }

    /**
     * @param pos
     */
    public void moveTrain(Tile pos) {
        // TODO implement here
    }
}
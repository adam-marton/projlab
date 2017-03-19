package szkeleton;

/**
 * 
 */
public class Station extends Tile {

    /**
     * Default constructor
     */
    public Station() {
    	System.out.println("Én lenni Station"); //ez tökéletesen érthető
    }
    
    /**
     * @param color
     */
    public Station(String color) {
    	System.out.println("Én lenni Station, ilyen színnel: " + color); //ez tökéletesen érthető
    }

    /**
     * 
     */
    private String color;

    /**
     * @return
     */
    public String getColor() {
        // TODO implement here
        return "";
    }

    /**
     * 
     */
    @Override
    public void move() {
        // TODO implement here
    }

    /**
     * @param e
     */
    @Override
    public void setElement(TrainElement e) {
        // TODO implement here
    }
}
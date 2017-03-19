package szkeleton;

import java.util.ArrayList;

/**
 * 
 */
public class PlottingBoard {
	
	/**
     * 
     */
    private ArrayList<Train> trains;

    /**
     * 
     */
    private Level currentLevel;

    /**
     * Default constructor
     */
    public PlottingBoard() {
    	this.trains = new ArrayList<Train>();
    }

    
    /**
     * 
     */
    public void deleteTrains() {
    	System.out.println("deleteTrains");
    	this.trains = new ArrayList<Train>();
    }

    /**
     * 
     */
    public void setNextLevel() {
    	System.out.println("setNextLevel");
    }

    /**
     * @return
     */
    public Level getLevel() {
    	System.out.println("getLevel");
        return this.currentLevel;
    }

    /**
     * 
     */
    public void startGame() {
        System.out.println("startGame");
    }

    /**
     * 
     */
    public void endGame() {
        System.out.println("endGame");
    }

    /**
     * 
     */
    public void run() {
    	System.out.println("run");
    	this.currentLevel.moveAll();
    }

    /**
     * @param startingPos 
     * @param color
     */
    public void addTrain(Tile startingPos, ArrayList<Color> colors) {
    	System.out.println("addTrain");
    	Train train = new Train();
    	train.setPos(startingPos);
    	Tile currentTile= startingPos;
    	for (Color color : colors) {
    		currentTile = currentTile.getDirA();
			TrainElement trainelement = new TrainElement(currentTile, color);
			train.addElement(trainelement);
		}
    	this.trains.add(train);
    	
    }
}
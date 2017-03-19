package szkeleton;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Scanner;
import java.util.logging.Logger;

/**
 * 
 */
public class PlottingBoard {
	
    /**
     * 
     */
    private List<Train> trains;
  
    private final Level currentLevel;

    /**
     * Default constructor
     */
    public PlottingBoard() {
    	this.trains = new ArrayList<Train>();
        currentLevel = new Level();
    }

    
    /**
     * 
     */
    public void deleteTrains() {
    	System.out.println("[PlottingBoard].deleteTrains()");
    	this.trains = new ArrayList<Train>();
    }

    /**
     * 
     */
    public void setNextLevel() {
    	System.out.println("[PlottingBoard].setNextLevel()");
    }

    /**
     * @return
     */
    public Level getLevel() {
    	System.out.println("[PlottingBoard].getLevel()");
        return this.currentLevel;
    }

    /**
     * 
     */
    public void startGame() {
        System.out.println("[PlottingBoard].startGame()");
        currentLevel.setReferences();
    }

    /**
     * 
     * @param s
     */
    public void endGame(String s) {
        System.out.println("[PlottingBoard].endGame()");
        if("Exit".equals(s)) {
            System.exit(0);
        }
    }

    /**
     * 
     */
    public void run() {
    	System.out.println("[PlottingBoard].run()");
    	this.currentLevel.moveAll();
    }

    /**
     * @param startingPos 
     */
// adam.marton

    public void addTrain(Tile startingPos) {
        System.out.println("[PlottingBoard].addTrain()");
        System.out.println("2.1 Hány vagonból áll a vonat?");
        Scanner s = new Scanner(System.in);
        Integer input = 0;
        try {
        input = s.nextInt();
        } catch(NoSuchElementException e) {
            Logger.getLogger(PlottingBoard.class.getName()).severe(e.toString());
        }
        Train train = new Train(startingPos);
        TrainElement trainElement = new TrainElement(startingPos, Color.BLUE);
        for(int i = 0; i < input; i++) {
            train.addElement(trainElement);
        }
        trains.add(train);
    }

  // aviscii 
  /*
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
    }*/
}
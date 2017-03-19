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
     * Default constructor
     */
    public PlottingBoard() {
    }

    /**
     * 
     */
    private List<Train> trains = new ArrayList<Train>();

    /**
     * 
     */
    private Level currentLevel;

    
    /**
     * 
     */
    public void deleteTrains() {
        // TODO implement here
    }

    /**
     * 
     */
    public void setNextLevel() {
        // TODO implement here
    }

    /**
     * @return
     */
    public Level getLevel() {
        // TODO implement here
        return null;
    }

    /**
     * 
     */
    public void startGame() {
        // TODO implement here
    }

    /**
     * 
     * @param s
     */
    public void endGame(String s) {
        if("Exit".equals(s)) {
            System.exit(0);
        }
    }

    /**
     * 
     */
    public void run() {
        // TODO implement here
    }

    /**
     * @param startingPos 
     */
    public void addTrain(Tile startingPos) {
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
}
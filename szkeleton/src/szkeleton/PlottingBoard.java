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
    private List<Train> trains = new ArrayList<Train>();
  
    private Level currentLevel;

    /**
     * Alapértelmezett konstruktor
     */
    public PlottingBoard() {
    	this.trains = new ArrayList<Train>();
    }

    
    /**
     * Törli a tárolt vonatokat
     */
    public void deleteTrains() {
    	System.out.println("deleteTrains");
    	this.trains = new ArrayList<Train>();
    }

    /**
     * Beállítja a következő pályát
     */
    public void setNextLevel() {
    	System.out.println("setNextLevel");
    }

    /**
     * Vissza adja jelenleg beállított pályát
     * @return currentLevel
     */
    public Level getLevel() {
    	System.out.println("getLevel");
        return this.currentLevel;
    }

    /**
     * Elindítja a játékot
     */
    public void startGame() {
        System.out.println("startGame");
    }

    /**
     * Lezárja a játékot és kilép ha exit lett átadva
     * @param s
     */
    public void endGame(String s) {
        System.out.println("endGame");
        if("Exit".equals(s)) {
            System.exit(0);
        }
    }

    /**
     * A Clock által megadott időnként hívott metódus ami a játékot eggyel "lépteti"
     */
    public void run() {
    	System.out.println("run");
    	this.currentLevel.moveAll();
    }

    /**
     * Példányosít és hozzáad egy új vonatot és hozuzátartozó kocsikat
     * @param startingPos 
     */
// adam.marton
/*
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
*/
  // aviscii
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
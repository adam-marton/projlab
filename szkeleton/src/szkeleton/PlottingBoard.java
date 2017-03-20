package szkeleton;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

/**
 * 
 */
public class PlottingBoard {
	
    /**
     * A vonatokat tároló lista.
     */
    private List<Train> trains;
    
    /**
     * Az aktuális szintet tároló field.
     */
    private final Level currentLevel;

    /**
     * Alapértelmezett konstruktor
     */
    public PlottingBoard() {
    	this.trains = new ArrayList<Train>();
        currentLevel = new Level();
    }

    
    /**
     * Törli a tárolt vonatokat
     */
    public void deleteTrains() {
    	System.out.println(">[PlottingBoard].deleteTrains()");
    	this.trains = new ArrayList<Train>();
    	System.out.println("<[PlottingBoard].deleteTrains()");
    }

    /**
     * Beállítja a következő pályát, külső adatbázissal lesz használva
     */
    public void setNextLevel() {
    	System.out.println(">[PlottingBoard].setNextLevel()");
    	System.out.println("<[PlottingBoard].setNextLevel()");
    }

    /**
     * Vissza adja jelenleg beállított pályát
     * @return currentLevel
     */
    public Level getLevel() {
    	System.out.println(">[PlottingBoard].getLevel()");
    	System.out.println("<[PlottingBoard].getLevel()");
        return this.currentLevel;
    }

    /**
     * Elindítja a játékot, összeköti a mezőket
     */
    public void startGame() {
        System.out.println(">[PlottingBoard].startGame()");
        currentLevel.setReferences();
        System.out.println("<[PlottingBoard].startGame()");
    }

    /**
     * Lezárja a játékot és kilép ha exit lett átadva
     * @param s
     */
    public void endGame(String s) {
        System.out.println(">[PlottingBoard].endGame()");
        System.out.println("s");
        System.out.println("<[PlottingBoard].endGame()");
        if("Exit".equals(s)) {
            System.exit(0);
        }
    }

    /**
     * A Clock által megadott időnként hívott metódus ami a játékot eggyel "lépteti"
     */
    public void run() {
    	System.out.println(">[PlottingBoard].run()");
        try {
        	currentLevel.preMove();
            currentLevel.moveAll();
        } catch (CrashException ex) {
            Logger.getLogger(PlottingBoard.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
            endGame(ex.getMessage());
        }
    	System.out.println("<[PlottingBoard].run()");
    }

    /**
     * Létrehoz egy új vonatot és hozzáadja a tárolóhoz
     * @param startingPos a mozdony kezdőpozíciója
     * @param colors a vagonok színeit tárolja
     */
    public void addTrain(Tile startingPos, List<Color> colors) {
        System.out.println(">[PlottingBoard].addTrain()");
        trains.add(new Train(startingPos, colors));
        System.out.println("<[PlottingBoard].addTrain()");
    }
}
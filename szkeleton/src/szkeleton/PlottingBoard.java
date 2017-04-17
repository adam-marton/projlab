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
	private Level currentLevel;

	/**
	 * Alapértelmezett konstruktor
	 */
	public PlottingBoard() {
		startGame();
	}

	/**
	 * Törli a tárolt vonatokat
	 */
	public void deleteTrains() {
		this.trains = new ArrayList<Train>();
	}

	/**
	 * Beállítja a következő pályát
	 */
	public void setNextLevel() {
		deleteTrains();
		currentLevel = new Level(currentLevel.getLevelId());
	}

	/**
	 * Vissza adja jelenleg beállított pályát
	 * 
	 * @return currentLevel
	 */
	public Level getLevel() {
		return this.currentLevel;
	}

	/**
	 * Elindítja a játékot, összeköti a mezőket
	 */
	public void startGame() {
		deleteTrains();
		currentLevel = new Level(1);
	}

	/**
	 * Lezárja a játékot és kilép ha exit lett átadva
	 * 
	 * @param s
	 */
	public void endGame(String s) {

		System.out.println(s);
		if ("Ütközés történt, vesztettél!".equals(s) || "Zárva van a bejárat, vesztettél!".equals(s)) {
			startGame();
		} else if ("Nyertél".equals(s)) {
			setNextLevel();
		} else {
			System.exit(0);
		}
	}

	/**
	 * A Clock által megadott időnként hívott metódus ami a játékot eggyel
	 * "lépteti"
	 */
	public void run() {
		try {
			//TODO itt kéne majd ütemezni a vonatok pályához adását
			currentLevel.preMove();
			currentLevel.moveAll();
		} catch (CrashException ex) {
			Logger.getLogger(PlottingBoard.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
			endGame(ex.getMessage());
		}
	}

	/**
	 * Létrehoz egy új vonatot és hozzáadja a tárolóhoz
	 * 
	 * @param x a mozdony kezdőpozíciójának x koordinátája 
	 *       
	 * @param y a mozdony kezdőpozíciójának y koordinátája 
	 * 
	 * @param colors
	 *            a vagonok színeit tárolja
	 */
	public void addTrain(int x, int y, List<Color> colors) {
		Tile startingPos = currentLevel.getTile(x, y);
		trains.add(new Train(startingPos, colors));
	}
}
package szkeleton;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Logger;

/**
 * 
 */
public class PlottingBoard {

	/**
	 * A vonatokat tároló lista.
	 */
	private List<Train> trains = new ArrayList<Train>();

	/**
	 * Az aktuális szintet tároló field.
	 */
	private Level currentLevel;
	
	/**
	 * Az összes soron következő vonat adatait tárolja
	 */	
	private List<Train> allTrains = new ArrayList<Train>();
	
	/**
	 * A létrejövő vonatokhoz tartozó időzítést tárolja
	 */
	private List<Integer> times = new ArrayList<Integer>();
	
	private int clock = 0;
	
	/**
	 * Alapértelmezett konstruktor
	 * @throws FileNotFoundException 
	 */
	public PlottingBoard(int n) throws FileNotFoundException {
		startGame(n);
	}

	/**
	 * Törli a tárolt vonatokat
	 */
	public void deleteTrains() {
		this.trains = new ArrayList<Train>();
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
	 * @throws FileNotFoundException 
	 */
	
	public void startGame(int n) throws FileNotFoundException {
		deleteTrains();
		currentLevel = new Level(n);
		Scanner scan = new Scanner(new File("train" + n + ".txt"));
	   	while(scan.hasNextLine())
	   	{	   		
	   		String[] splitLine= scan.nextLine().split("><");
	   		splitLine[0] = splitLine[0].replace("<", "");
	   		times.add(Integer.parseInt(splitLine[0]));
	   		String[] coords = splitLine[1].split("-");
	   		int coordX = Integer.parseInt(coords[0]);
			int coordY = Integer.parseInt(coords[1]);
			Tile startingPos = getLevel().getTile(coordX, coordY);
			String[] colors = splitLine[2].split("-");
			colors[colors.length-1] = colors[colors.length-1].replace(">", "");
			List<Color> elementColors = new ArrayList<Color>();
			for(int i = 0; i < colors.length; i++)
			{
				elementColors.add(Color.valueOf(colors[i]));
			}
			allTrains.add(new Train(startingPos, elementColors));
		}	
	   	scan.close();
	}

	/**
	 * Lezárja a játékot és kilép ha exit lett átadva
	 * 
	 * @param s
	 * @throws FileNotFoundException 
	 */
	public void endGame(String s) throws FileNotFoundException {

		System.out.println(s);
		if ("Ütközés történt, vesztettél!".equals(s) || "Zárva van a bejárat, vesztettél!".equals(s)) {
			startGame(1);
		} else if ("Nyertél".equals(s)) {
			startGame(1);
		} else {
			System.exit(0);
		}
	}

	/**
	 * A Clock által megadott időnként hívott metódus ami a játékot eggyel
	 * "lépteti"
	 * @throws FileNotFoundException 
	 */
	public void run() throws FileNotFoundException {
		try {
			if(clock == times.get(0))
			{
				trains.add(allTrains.get(0));
				allTrains.remove(0);
			}
			clock++;
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
	 * @param startingPos
	 *            a mozdony kezdőpozíciója
	 * @param colors
	 *            a vagonok színeit tárolja
	 */
	/*public void addTrain(Train t) {
		Tile startingPos = getLevel().getTile(x, y);        
		trains.add(new Train(startingPos, colors));
	}*/
}
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
	 * Az összes soron következő vonat adatait tárolja
	 */	
	private int[] trainCoords;
	private List<List<Color>> trainColors = new ArrayList<List<Color>>();
	
	/**
	 * A létrejövő vonatokhoz tartozó időzítést tárolja
	 */
	private int[] times;
	
	/**
	 * Indexelés a létrejövő vonatokhoz
	 */
	static private int trainCounter = 0;

	/**
	 * Időzítés a vonatokhoz
	 */
	static private int clock = 0;
	
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
		Scanner scan = new Scanner(new File("train"+currentLevel.getLevelId()+".txt"));
		int timesCounter = 0;
	   	while(scan.hasNextLine())
	   	{	   		
	   		String[] splitLine= scan.nextLine().split("><");
	   		splitLine[0].replace("<", "");
	   		times[timesCounter] = Integer.parseInt(splitLine[0]);
	   		timesCounter++;
	   		String[] coords = splitLine[1].split("-");
	   		int coordX = Integer.parseInt(coords[0]);
			int coordY = Integer.parseInt(coords[1]);
			String[] colors = splitLine[2].split("-");
			List<Color> elementColors = new ArrayList<Color>();
			for(int i = 0; i < colors.length; i++)
			{
				elementColors.get(i).valueOf(colors[i]);
			}
			trainCoords[2*timesCounter] = coordX;
			trainCoords[2*timesCounter + 1] = coordY;
			trainColors.add(elementColors);
		}
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
		Scanner scan = new Scanner(new File("train1.txt"));
		int timesCounter = 0;
	   	while(scan.hasNextLine())
	   	{	   		
	   		String[] splitLine= scan.nextLine().split("><");
	   		splitLine[0].replace("<", "");
	   		times[timesCounter] = Integer.parseInt(splitLine[0]);
	   		timesCounter++;
	   		String[] coords = splitLine[1].split("-");
	   		int coordX = Integer.parseInt(coords[0]);
			int coordY = Integer.parseInt(coords[1]);
			String[] colors = splitLine[2].split("-");
			List<Color> elementColors = new ArrayList<Color>();
			for(int i = 0; i < colors.length; i++)
			{
				elementColors.get(i).valueOf(colors[i]);
			}
		}
	}
	public void startGame(int n) {
		deleteTrains();
		currentLevel = new Level(n);
		Scanner scan = new Scanner(new File("train1.txt"));
		int timesCounter = 0;
	   	while(scan.hasNextLine())
	   	{	   		
	   		String[] splitLine= scan.nextLine().split("><");
	   		splitLine[0].replace("<", "");
	   		times[timesCounter] = Integer.parseInt(splitLine[0]);
	   		timesCounter++;
	   		String[] coords = splitLine[1].split("-");
	   		int coordX = Integer.parseInt(coords[0]);
			int coordY = Integer.parseInt(coords[1]);
			String[] colors = splitLine[2].split("-");
			List<Color> elementColors = new ArrayList<Color>();
			for(int i = 0; i < colors.length; i++)
			{
				elementColors.get(i).valueOf(colors[i]);
			}
		}
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
			if(clock == times[trainCounter])
			{
				addTrain(trainCoords[2*trainCounter], trainCoords[2*trainCounter + 1], trainColors.get(trainCounter));
				trainCounter++;
				clock = 0;
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
	public void addTrain(int x, int y, List<Color> colors) {
		Tile startingPos=getLevel().getTile(x,y);
		trains.add(new Train(startingPos, colors));
	}
}

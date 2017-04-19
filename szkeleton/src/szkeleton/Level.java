package szkeleton;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 *
 */
public class Level {

	/**
	 * Konstruktor létrehozza a mezőket
	 * @throws FileNotFoundException 
	 */
	public void printAll(){
		for(int i = 0; i < 20; i++){
			for(int j = 0; j < 20;j++){
				if(tiles[i][j] != null){
					tiles[i][j].printTile(i,j);
				}
			}
		}
	}
	
	public Level(int id) throws FileNotFoundException {
		this.id = id;
		TunnelEntranceCounter.getInstance().addCounter(0);
		tiles = new Tile[20][20]; // TODO ez egy külső forrásból fog történni
									// jelenleg nincs implementálva
		Scanner scanC;
		scanC = new Scanner(new File("map" + id + ".txt"));
		while (scanC.hasNextLine()) {
		String[] splitLine = scanC.nextLine().split("><");
		if (splitLine[0].equals("<rail")) {
			String[] coords = splitLine[1].split("-");
			int coordX = Integer.parseInt(coords[0]);
			int coordY = Integer.parseInt(coords[1]);
			tiles[coordX][coordY] = new Rail(true);
		} else if (splitLine[0].equals("<switch")) {
			String[] coords = splitLine[1].split("-");
			int coordX = Integer.parseInt(coords[0]);
			int coordY = Integer.parseInt(coords[1]);
			tiles[coordX][coordY] = new Switch();
		} else if (splitLine[0].equals("<station")) {
			String[] coords = splitLine[1].split("-");
			int coordX = Integer.parseInt(coords[0]);
			int coordY = Integer.parseInt(coords[1]);
			tiles[coordX][coordY] = new Station(Color.valueOf(splitLine[2]));
		} else if (splitLine[0].equals("<crossrail")) {
			String[] coords = splitLine[1].split("-");
			int coordX = Integer.parseInt(coords[0]);
			int coordY = Integer.parseInt(coords[1]);
			tiles[coordX][coordY] = new CrossRail();
			} else if (splitLine[0].equals("<tunnel")) {
				String[] coords = splitLine[1].split("-");
				int coordX = Integer.parseInt(coords[0]);
				int coordY = Integer.parseInt(coords[1]);
				tiles[coordX][coordY] = new Rail(false);
			} else if (splitLine[0].equals("<tunnelentrance")) {
				String[] coords = splitLine[1].split("-");
				int coordX = Integer.parseInt(coords[0]);
				int coordY = Integer.parseInt(coords[1]);
				tiles[coordX][coordY] = new TunnelEntrance();
			}
		}
		scanC.close();
		setReferences(id);

	}

	/**
	 * A mezőket tároló tömb
	 */
	private final Tile[][] tiles;

	/**
	 * A szint száma
	 */
	private int id;

	/**
	 * @return visszadja a szint számát
	 */
	public int getLevelId() {
		return id;
	}
	/**
	 * @return visszaadegy mezőt
	 */
	public Tile getTile(int x, int y) {
		return tiles[x][y];
	}

	/**
	 * Beállítja a kapcsolatokat a mezők között
	 */
	public void setReferences(int mapID) {
		Scanner scanC;
		try {
			scanC = new Scanner(new File("map" + mapID + ".txt"));

			while (scanC.hasNextLine()) {
				String[] splitLine = scanC.nextLine().split("><");
				if (splitLine[0].equals("<rail") || splitLine[0].equals("<tunnel") || splitLine[0].equals("<tunnelentrance")) {
					String[] coords = splitLine[1].split("-");
					int coordX = Integer.parseInt(coords[0]);
					int coordY = Integer.parseInt(coords[1]);

					String[] coordADir = splitLine[2].split("-");
					splitLine[3] = splitLine[3].replaceAll(">", "");
					String[] coordBDir = splitLine[3].split("-");
					////////////////////////////////////
					if(!coordADir[0].equals("null"))
					{
						int coordADirX = Integer.parseInt(coordADir[0]);
						int coordADirY = Integer.parseInt(coordADir[1]);
						tiles[coordX][coordY].setDirA(tiles[coordADirX][coordADirY]);
					}
					else
					{
						tiles[coordX][coordY].setDirA(null);
					}
					/////////////////////////////////////
					if(!coordBDir[0].equals("null"))
					{
						int coordBDirX = Integer.parseInt(coordBDir[0]);
						int coordBDirY = Integer.parseInt(coordBDir[1]);				
						tiles[coordX][coordY].setDirB(tiles[coordBDirX][coordBDirY]);
					}
					else
					{
						tiles[coordX][coordY].setDirB(null);
					}
					////////////////////////////////////////
				} else if (splitLine[0].equals("<switch")) {
					String[] coords = splitLine[1].split("-");
					int coordX = Integer.parseInt(coords[0]);
					int coordY = Integer.parseInt(coords[1]);

					String[] coordADir = splitLine[2].split("-");
					String[] coordBDir = splitLine[3].split("-");
					splitLine[4] = splitLine[4].replaceAll(">", "");
					String[] coordCDir = splitLine[4].split("-");
					///////////////////////////////////
					if(!coordADir[0].equals("null"))
					{
						int coordADirX = Integer.parseInt(coordADir[0]);
						int coordADirY = Integer.parseInt(coordADir[1]);
						tiles[coordX][coordY].setDirA(tiles[coordADirX][coordADirY]);
					}
					else
					{
						tiles[coordX][coordY].setDirA(null);
					}
					//////////////////////////////////
					if(!coordBDir[0].equals("null"))
					{
						int coordBDirX = Integer.parseInt(coordBDir[0]);
						int coordBDirY = Integer.parseInt(coordBDir[1]);
						tiles[coordX][coordY].setDirB(tiles[coordBDirX][coordBDirY]);
					}
					else
					{
						tiles[coordX][coordY].setDirB(null);
					}
					////////////////////////////////////
					if(!coordCDir[0].equals("null"))
					{
						int coordCDirX = Integer.parseInt(coordCDir[0]);
						int coordCDirY = Integer.parseInt(coordCDir[1]);
						((Switch) tiles[coordX][coordY]).setDirC(tiles[coordCDirX][coordCDirY]);
					}
					else
					{
						((Switch) tiles[coordX][coordY]).setDirC(null);
					}
					
					tiles[coordX][coordY].changeState();
					
					/////////////////////////////////////
				} else if (splitLine[0].equals("<station")) {
					String[] coords = splitLine[1].split("-");
					int coordX = Integer.parseInt(coords[0]);
					int coordY = Integer.parseInt(coords[1]);
					
					String[] coordADir = splitLine[3].split("-");
					splitLine[4] = splitLine[4].replaceAll(">", "");
					String[] coordBDir = splitLine[4].split("-");
					//////////////////////////////////
					if(!coordADir[0].equals("null"))
					{
						int coordADirX = Integer.parseInt(coordADir[0]);
						int coordADirY = Integer.parseInt(coordADir[1]);
						tiles[coordX][coordY].setDirA(tiles[coordADirX][coordADirY]);
					}
					else
					{
						tiles[coordX][coordY].setDirA(null);
					}
					////////////////////////////////////
					if(!coordBDir[0].equals("null"))
					{
						int coordBDirX = Integer.parseInt(coordBDir[0]);
						int coordBDirY = Integer.parseInt(coordBDir[1]);
						tiles[coordX][coordY].setDirB(tiles[coordBDirX][coordBDirY]);
					}
					else
					{
						tiles[coordX][coordY].setDirB(null);
					}
					///////////////////////////////////////					
				} else if (splitLine[0].equals("<crossrail")) {
					String[] coords = splitLine[1].split("-");
					int coordX = Integer.parseInt(coords[0]);
					int coordY = Integer.parseInt(coords[1]);

					String[] coordADir = splitLine[2].split("-");
					String[] coordBDir = splitLine[3].split("-");
					String[] coordCDir = splitLine[4].split("-");
					splitLine[5] = splitLine[5].replaceAll(">", "");
					String[] coordDDir = splitLine[5].split("-");
					////////////////////////////////////////
					if(!coordADir[0].equals("null"))
					{
						int coordADirX = Integer.parseInt(coordADir[0]);
						int coordADirY = Integer.parseInt(coordADir[1]);
						tiles[coordX][coordY].setDirA(tiles[coordADirX][coordADirY]);
					}
					else
					{
						tiles[coordX][coordY].setDirA(null);
					}
					////////////////////////////////////////
					if(!coordBDir[0].equals("null"))
					{
						int coordBDirX = Integer.parseInt(coordBDir[0]);
						int coordBDirY = Integer.parseInt(coordBDir[1]);
						tiles[coordX][coordY].setDirB(tiles[coordBDirX][coordBDirY]);
					}
					else
					{
						tiles[coordX][coordY].setDirB(null);
					}
					////////////////////////////////////////
					if(!coordCDir[0].equals("null"))
					{
						int coordCDirX = Integer.parseInt(coordCDir[0]);
						int coordCDirY = Integer.parseInt(coordCDir[1]);
						((CrossRail) tiles[coordX][coordY]).setDirC(tiles[coordCDirX][coordCDirY]);
					}
					else
					{
						((CrossRail) tiles[coordX][coordY]).setDirC(null);
					}
					////////////////////////////////////////
					if(!coordDDir[0].equals("null"))
					{
						int coordDDirX = Integer.parseInt(coordDDir[0]);
						int coordDDirY = Integer.parseInt(coordDDir[1]);
						((CrossRail) tiles[coordX][coordY]).setDirD(tiles[coordDDirX][coordDDirY]);						
					}
					else
					{
						((CrossRail) tiles[coordX][coordY]).setDirD(null);	
					}
					//////////////////////////////////////////
				}
			}
			scanC.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * Megvizsgálja, hogy volt-e ütközés Mozgathatóvá teszi a mezőket
	 *
	 * @throws CrashException
	 */
	public void preMove() throws CrashException {
		for (int i = 0; i < 20; i++) {
			for (int j = 0; j < 20; j++) {
				if (tiles[i][j] != null) {
					if (tiles[i][j].checkCrash()) {
						throw new CrashException("Ütközés történt, vesztettél!");
					}
					tiles[i][j].enableMove();
				}
			}
		}
	}

	/**
	 * Mozgatja a mezőkön található mozdonyokat Ha hibát detektál akkor azt
	 * tovább dobja
	 *
	 * @throws szkeleton.CrashException
	 */
	public void moveAll() throws CrashException {
		for (int i = 0; i < 20; i++) {
			for (int j = 0; j < 20; j++) {
				if (tiles[i][j] != null && tiles[i][j].isMovable())
					tiles[i][j].move();
			}
		}
	}

	/**
	 * @param x
	 *            koordináta
	 * @param y
	 *            koordináta A grafikus interfészen egy mezőre kattintva
	 *            meghívja annak change state függvényét
	 */
	public void changeState(int x, int y) {
		if (tiles[x][y] != null)
			tiles[x][y].changeState();
	}
}
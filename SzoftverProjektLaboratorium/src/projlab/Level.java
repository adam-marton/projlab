package projlab;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 *
 */
public class Level {

	/**
	 * Konstruktor lĂ©trehozza a mezĹ‘ket
	 * @throws FileNotFoundException 
	 */
	public void printAll(){
		for(int i = 0; i < 40; i++){
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
		tiles = new Tile[40][20]; // TODO ez egy kĂĽlsĹ‘ forrĂˇsbĂłl fog tĂ¶rtĂ©nni
									// jelenleg nincs implementĂˇlva
		Scanner scanC;
		scanC = new Scanner(new File("data/maps/map" + id + ".txt"));
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
	 * A mezĹ‘ket tĂˇrolĂł tĂ¶mb
	 */
	private final Tile[][] tiles;

	/**
	 * A szint szĂˇma
	 */
	private int id;

	/**
	 * @return visszadja a szint szĂˇmĂˇt
	 */
	public int getLevelId() {
		return id;
	}
	/**
	 * @return visszaadegy mezĹ‘t
	 */
	public Tile getTile(int x, int y) {
		return tiles[x][y];
	}

	/**
	 * BeĂˇllĂ­tja a kapcsolatokat a mezĹ‘k kĂ¶zĂ¶tt
	 */
	public void setReferences(int mapID) {
		Scanner scanC;
		try {
			scanC = new Scanner(new File("data/maps/map" + mapID + ".txt"));

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
						
						////////GRAPHICS CHANGE
						if(!coordBDir[0].equals("null")){
							int coordBDirX = Integer.parseInt(coordBDir[0]);
							int coordBDirY = Integer.parseInt(coordBDir[1]);
							
							if(splitLine[0].equals("<rail")){
								if(coordADirX < coordX && coordBDirX > coordX || coordADirX > coordX && coordBDirX < coordX){
									Main.drawer.addRailToList("leftToRightRail", coordX, coordY);
								}
								if(coordADirY < coordY && coordBDirY > coordY || coordADirY > coordY && coordBDirY < coordY){
									Main.drawer.addRailToList("upToDownRail", coordX, coordY);
								}
								if(coordADirX < coordX && coordBDirY > coordY || coordBDirX < coordX && coordADirY > coordY){
									Main.drawer.addRailToList("leftToDownRail", coordX, coordY);
								}
								if(coordADirX < coordX && coordBDirY < coordY || coordBDirX < coordX && coordADirY < coordY){
									Main.drawer.addRailToList("leftToUpRail", coordX, coordY);
								}
								if(coordADirY > coordY && coordBDirX > coordX || coordBDirY > coordY && coordADirX > coordX){
									Main.drawer.addRailToList("rightToDownRail", coordX, coordY);
								}
								if(coordADirX > coordX && coordBDirY < coordY || coordBDirX > coordX && coordADirY < coordY){
									Main.drawer.addRailToList("upToRightRail", coordX, coordY);
								}
							}
							else if(splitLine[0].equals("<tunnelentrance")){
								Main.drawer.addRailToList("tunnelEntranceOpened", coordX, coordY);
							}
							else if(splitLine[0].equals("<tunnel")){
								Main.drawer.addRailToList("tunnel", coordX, coordY);
							}
						}
						///////////GRAPHICS CHANGE END
					}
					else
					{
						tiles[coordX][coordY].setDirA(null);
						Main.drawer.addRailToList("leftToRightRail", coordX, coordY);
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
						
						///GRAPHICS CHANGE
						if(!coordBDir[0].equals("null")){
							if(!coordCDir[0].equals("null")){
								int coordBDirX = Integer.parseInt(coordBDir[0]);
								int coordBDirY = Integer.parseInt(coordBDir[1]);
								int coordCDirX = Integer.parseInt(coordCDir[0]);
								int coordCDirY = Integer.parseInt(coordCDir[1]);
								
								if(coordADirX < coordX && coordBDirX > coordX || coordBDirX < coordX && coordADirX > coordX){
									if(coordCDirY > coordY){
										Main.drawer.addRailToList("leftRightToDownSwitchActiveDirB", coordX, coordY);
									}
									if(coordCDirY < coordY){
										Main.drawer.addRailToList("leftRightToUpSwitchActiveDirB", coordX, coordY);
									}
								}
								if(coordADirY < coordY && coordBDirY > coordY || coordBDirY < coordY && coordADirY > coordY ){
									if(coordCDirX > coordX){
										Main.drawer.addRailToList("upDownToRightSwitchActiveDirB", coordX, coordY);
									}
									if(coordCDirX < coordX){
										Main.drawer.addRailToList("upDownToLeftSwitchActiveDirB", coordX, coordY);
									}
								}
							}
						}
						///GRAPHICSCHANGE END
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
					String[] coordBDir = splitLine[4].split("-");

					splitLine[5] = splitLine[5].replaceAll(">", "");
					String[] coordBuilding = splitLine[5].split("-");
					
					//////////////////////////////////
					if(!coordADir[0].equals("null"))
					{
						int coordADirX = Integer.parseInt(coordADir[0]);
						int coordADirY = Integer.parseInt(coordADir[1]);
						tiles[coordX][coordY].setDirA(tiles[coordADirX][coordADirY]);
						
						//GRAPHICSCHANGE
						if(!coordBDir[0].equals("null"))
						{
							String color = splitLine[2];
							
							int coordBuildingX = Integer.parseInt(coordBuilding[0]);
							int coordBuildingY = Integer.parseInt(coordBuilding[1]);
							
							int coordBDirX = Integer.parseInt(coordBDir[0]);
							int coordBDirY = Integer.parseInt(coordBDir[1]);
							
							if(coordADirX < coordX && coordBDirX > coordX || coordADirX > coordX && coordBDirX < coordX){
								Main.drawer.addRailToList("leftToRightRail", coordX, coordY);
							}
							if(coordADirY < coordY && coordBDirY > coordY || coordADirY > coordY && coordBDirY < coordY){
								Main.drawer.addRailToList("upToDownRail", coordX, coordY);
							}
							switch(color){
							case "RED": Main.drawer.addRailToList("redStation", coordBuildingX, coordBuildingY); break;
							case "BLUE": Main.drawer.addRailToList("blueStation", coordBuildingX, coordBuildingY); break;
							case "GREEN": Main.drawer.addRailToList("greenStation", coordBuildingX, coordBuildingY); break;
							case "PURPLE": Main.drawer.addRailToList("purpleStation", coordBuildingX, coordBuildingY); break;
							case "YELLOW": Main.drawer.addRailToList("yellowStation", coordBuildingX, coordBuildingY); break;
							}
						}
						//GRAPHICSCHANGE END
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
					
					////////GRAPHICS CHANGE
					Main.drawer.addRailToList("crossRail", coordX, coordY);
					////////GRAPHICS CHANGE END

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
	 * MegvizsgĂˇlja, hogy volt-e ĂĽtkĂ¶zĂ©s MozgathatĂłvĂˇ teszi a mezĹ‘ket
	 *
	 * @throws CrashException
	 */
	public void preMove() throws CrashException {
		for (int i = 0; i < 40; i++) {
			for (int j = 0; j < 20; j++) {
				if (tiles[i][j] != null) {
					if (tiles[i][j].checkCrash()) {
						throw new CrashException("ĂśtkĂ¶zĂ©s tĂ¶rtĂ©nt, vesztettĂ©l!");
					}
					tiles[i][j].enableMove();
				}
			}
		}
	}

	/**
	 * Mozgatja a mezĹ‘kĂ¶n talĂˇlhatĂł mozdonyokat Ha hibĂˇt detektĂˇl akkor azt
	 * tovĂˇbb dobja
	 *
	 * @throws projlab.CrashException
	 */
	public void moveAll() throws CrashException {
		for (int i = 0; i < 40; i++) {
			for (int j = 0; j < 20; j++) {
				if (tiles[i][j] != null && tiles[i][j].isMovable())
					tiles[i][j].move();
			}
		}
	}

	/**
	 * @param x
	 *            koordinĂˇta
	 * @param y
	 *            koordinĂˇta A grafikus interfĂ©szen egy mezĹ‘re kattintva
	 *            meghĂ­vja annak change state fĂĽggvĂ©nyĂ©t
	 */
	public void changeState(int x, int y) {
		if (tiles[x][y] != null)
			tiles[x][y].changeState();
	}
}

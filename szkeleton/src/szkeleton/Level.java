package szkeleton;

/**
 *
 */
public class Level {

	/**
	 * Konstruktor létrehozza a mezőket
	 */
	public Level(int id) {
		this.id = id;
		TunnelEntranceCounter.getInstance().addCounter(0);
		tiles = new Tile[10][20]; // TODO ez egy külső forrásból fog történni jelenleg nincs implementálva
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
	 * Beállítja a kapcsolatokat a mezők között
	 */
	public void setReferences() {
		// TODO ez egy külső forrásból fog történni jelenleg nincs implementálva
	}

	/**
	 * Megvizsgálja, hogy volt-e ütközés Mozgathatóvá teszi a mezőket
	 *
	 * @throws CrashException
	 */
	public void preMove() throws CrashException {
		for (int i = 0; i < 20; i++) {
			for (int j = 0; j < 20; j++) {
				if (tiles[i][j].checkCrash()) {
					throw new CrashException("Ütközés történt, vesztettél!");
				}
				tiles[i][j].enableMove();
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
				if (tiles[i][j].isMovable())
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
		tiles[x][y].changeState();
	}
}

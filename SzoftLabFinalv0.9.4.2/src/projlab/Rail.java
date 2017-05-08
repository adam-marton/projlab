package projlab;

/**
 * A síneket és alagút elemeket megvalósító osztály
 */
public class Rail extends Tile {

	/**
	 * ha látható, akkor egy sima Rail, ha nem látható akkor Tunnel-ben lévő
	 * láthatatlan Rail (alagútelem)
	 */
	private boolean visible;

	/**
	 * A rail konstruktora, ha a hamis értékkel hívjuk meg akkor alagútelem lesz
	 *
	 * @param visible
	 */
	public Rail(boolean visible) {
		this.visible = visible;
	}

	/**
	 * visszatér azzal, hogy látható-e a Rail vagy sem
	 *
	 * @return
	 */
	public boolean isVisible() {
		return visible;
	}

	/**
	 * a rajta álló mozdony mozgatásáért felelős függvényt hívja meg, ha az még
	 * nem mozgott ebben a ciklusban megnézi, hogy a rajta lévő vonat előző
	 * pozíciója melyik mezővel egyezik meg és a másik irányba küldi tovább ha
	 * egyik irányba se tud a vonat továbbmenni -> ütközés
	 *
	 * @throws projlab.CrashException
	 */
	@Override
	public void move() throws CrashException {
		if (train != null) {
			if (train.getPrevPos() == getDirA() && getDirB() != null && getDirB().isFree()) {
				train.moveTrain(getDirB());
			} else if (train.getPrevPos() == getDirB() && getDirA() != null && getDirA().isFree()) {
				train.moveTrain(getDirA());
			} else
				throw new CrashException("Utkozes tortent, vesztettel!");
		}
	}

	/**
	 * Az rajta tartozkodó vonatelemet hozzáadja a kirajzolandó elemek közé
	 *
	 */
	public void printTile(int i, int j) {
		if (isVisible()) {
			Main.drawer.addTrainToList(null, i, j);
			if (train != null) {
				Main.drawer.addTrainToList("locomotive", i, j);

			}
			if (trainElement != null) {
				switch (trainElement.getColor()) {
				case BLUE:
					Main.drawer.addTrainToList("blueTrain", i, j);
					break;
				case GREEN:
					Main.drawer.addTrainToList("greenTrain", i, j);
					break;
				case RED:
					Main.drawer.addTrainToList("redTrain", i, j);
					break;
				case BLACK:
					Main.drawer.addTrainToList("coalCarrier", i, j);
					break;
				case PURPLE:
					Main.drawer.addTrainToList("purpleTrain", i, j);
					break;
				case YELLOW:
					Main.drawer.addTrainToList("yellowTrain", i, j);
					break;
				default:
					break;
				}
				if (trainElement.isEmpty() && !trainElement.getColor().equals(Color.BLACK))
					Main.drawer.addTrainToList("emptyTrain", i, j);
			}
		}
	}

}

package projlab;

/**
 * Az állomásokat megvalósító osztály
 */
public class Station extends Tile {

	/**
	 * a Station színét tároljuk
	 */
	private Color color;

	/**
	 * Az állomás konstruktokiírja, hogy Station és a színét
	 *
	 * @param color
	 *            Az állomás színe
	 */
	public Station(Color color) {
		this.color = color;
		// System.out.println("Station, szín:" + color);
	}

	/**
	 * Az rajta tartozkodó vonatelemet hozzáadja a kirajzolandó elemek közé
	 *
	 */
	public void printTile(int i, int j) {
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

	/**
	 * visszaadja a Station színét, ami tökéletes az éppen az állomás előtt
	 * elhaladó kocsik színének az ellenőrzéséhez
	 *
	 * @return
	 */
	public Color getColor() {
		return color;
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
	 * beállítja a paraméterül kapott TrainElement-et a Tile TraimElement-jeként
	 * és megnézi, hogy megegyezik-e a színe a Station színével és ha igen,
	 * akkor meghívja a TrainElement getOff() függvényét
	 */
	@Override
	public void setElement(TrainElement e) {
		if (e != null) {
			trainElement = e;
			Color c = trainElement.getColor();
			color = getColor();
			if (c == color) {
				trainElement.getOff();
			}
		} else {
			trainElement = null;
		}
	}
}

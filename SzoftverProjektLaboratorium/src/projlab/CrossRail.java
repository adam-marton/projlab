package projlab;

public class CrossRail extends Tile {

	/**
	 * A mező egyik szomszédja.
	 */
	protected Tile cDir;

	/**
	 * A mező másik szomszédja.
	 */
	protected Tile dDir;

	/**
	 * A CrossRail konstruktora
	 *
	 * @param visible
	 */
	public CrossRail() {
	}

	/**
	 * Beállítja az aDir field értékét a kapott Tile értékére.
	 * 
	 * @param t
	 */
	public void setDirC(Tile t) {
		this.cDir = t;
	}

	/**
	 * Beállítja a bDir field értékét a kapott Tile értékére.
	 * 
	 * @param t
	 */
	public void setDirD(Tile t) {
		this.dDir = t;
	}

	/**
	 * Visszaadja az aDir field értékét.
	 * 
	 * @return
	 */
	public Tile getDirC() {
		return this.cDir;
	}

	/**
	 * Visszaadja a bDir field értékét.
	 * 
	 * @return
	 */
	public Tile getDirD() {
		return this.dDir;
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
		if(train != null){
			if (train.getPrevPos() == getDirA() && getDirB() != null && getDirB().isFree()) {
				train.moveTrain(getDirB());
			} 
			else if (train.getPrevPos() == getDirB() && getDirA() != null && getDirA().isFree()) {
				train.moveTrain(getDirA());
			} 
			else if (train.getPrevPos() == getDirC() && getDirD() != null && getDirD().isFree()) {
				train.moveTrain(getDirD());
			} 
			else if (train.getPrevPos() == getDirD() && getDirC() != null && getDirC().isFree()) {
				train.moveTrain(getDirC());
			}
			else
				throw new CrashException("Ütközés történt, vesztettél!");
		}
	}
	
	public void printTile(int i, int j){
		Main.drawer.addTrainToList(null, i, j);
		if(train != null){
			Main.drawer.addTrainToList("locomotive", i, j);
			
		}
		if (trainElement != null){
			switch(trainElement.getColor()){
    			case BLUE : Main.drawer.addTrainToList("blueTrain", i, j); break;
    			case GREEN : Main.drawer.addTrainToList("greenTrain", i, j); break;
    			case RED: Main.drawer.addTrainToList("redTrain", i, j); break;
    			case BLACK : Main.drawer.addTrainToList("coalCarrier", i, j); break;
    			case PURPLE: Main.drawer.addTrainToList("purpleTrain", i, j); break;
    			case YELLOW: Main.drawer.addTrainToList("yellowTrain", i, j); break;
    			default: break;
			}
			if(trainElement.isEmpty() && !trainElement.getColor().equals(Color.BLACK)) 
				Main.drawer.addTrainToList("emptyTrain", i, j);
		}
    }
}

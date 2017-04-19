package szkeleton;

import java.util.Scanner;

/**
 * 
 */
public abstract class Tile {

	/**
	 * A mező egyik szomszédja.
	 */
	protected Tile aDir;

	/**
	 * A mező másik szomszédja.
	 */
	protected Tile bDir;

	/**
	 * A mezőn lévő mozdony.
	 */
	protected Train train;

	/**
	 * A mezőn lévő vonatelem.
	 */
	protected TrainElement trainElement;

	/**
	 * Megmondja, hogy mozgatható-e a mezőn lévő vonat. Ez azért fontos, hogy
	 * egy mozdonyt ne lehessen egy körben kétszer mozgatni.
	 */
	protected boolean moveable;

	/**
	 * Default constructor
	 */
	public Tile() {
	}

	/**
	 * Beállítja az aDir field értékét a kapott Tile értékére.
	 * 
	 * @param t
	 */
	public void setDirA(Tile t) {
		this.aDir = t;
	}

	/**
	 * Beállítja a bDir field értékét a kapott Tile értékére.
	 * 
	 * @param t
	 */
	public void setDirB(Tile t) {
		this.bDir = t;
	}

	/**
	 * Visszaadja az aDir field értékét.
	 * 
	 * @return
	 */
	public Tile getDirA() {
		return this.aDir;
	}

	/**
	 * Visszaadja a bDir field értékét.
	 * 
	 * @return
	 */
	public Tile getDirB() {
		return this.bDir;
	}

	/**
	 * Beállítja a train field értékét a kapott Train értékére.
	 * 
	 * @param t
	 */
	public void setTrain(Train t) {
		this.train = t;
		moveable = false;
	}

	/**
	 * Beállítja a trainElement field értékét a kapott TrainElement értékére.
	 * 
	 * @param e
	 */
	public void setElement(TrainElement e) {
		this.trainElement = e;
	}

	/**
	 * Megvizsgálja, hogy történt-e ütközés. Jelen esetben bekérjük a
	 * tesztelőtől, hogy van-e a vonat előtt akadály, ennek függvényében döntjük
	 * el. Ha van valami, akkor történik ütközés, ha nincs, akkor megy tovább a
	 * vonat.
	 * 
	 * @return True, ha történik ütközés. False, ha nem.
	 */
	public boolean checkCrash() {
		if (trainElement != null && train != null)
			return true;
		return false;
	}

	/**
	 * Visszaadja a moveable field értékét.
	 * 
	 * @return
	 */
	public boolean isMovable() {
		return moveable;
	}

	/**
	 * @return visszadja hogy van-e mozdony a mezőn
	 */
	public boolean isFree() {
		return (null == train && null == trainElement);
	}

	public void printTile(int i, int j){
		if(train != null){
			System.out.println("Train "+i+"-"+j);
		}
		else if (trainElement != null){
			System.out.println("TrainElement "+i+"-"+j+" "+trainElement.getColor()+","+trainElement.isEmpty()+" ");
		}
	}
	/**
	 * Üres metódus, a leszármazottak felülírják, ha szükséges.
	 */
	public void changeState() {
		// empty on purpose
	}

	/**
	 * A léptető függvény a mezők felülírják
	 * 
	 * @throws szkeleton.CrashException
	 */
	public void move() throws CrashException {
		// empty on purpose
	}

	/**
	 * A moveable field értékét igazra állítja.
	 */
	public void enableMove() {
		this.moveable = true;
	}
}
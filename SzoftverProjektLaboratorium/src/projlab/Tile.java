package projlab;

/**
 * 
 */
public abstract class Tile {

	/**
	 * A mezĹ‘ egyik szomszĂ©dja.
	 */
	protected Tile aDir;

	/**
	 * A mezĹ‘ mĂˇsik szomszĂ©dja.
	 */
	protected Tile bDir;

	/**
	 * A mezĹ‘n lĂ©vĹ‘ mozdony.
	 */
	protected Train train;

	/**
	 * A mezĹ‘n lĂ©vĹ‘ vonatelem.
	 */
	protected TrainElement trainElement;

	/**
	 * Megmondja, hogy mozgathatĂł-e a mezĹ‘n lĂ©vĹ‘ vonat. Ez azĂ©rt fontos, hogy
	 * egy mozdonyt ne lehessen egy kĂ¶rben kĂ©tszer mozgatni.
	 */
	protected boolean moveable;

	/**
	 * Default constructor
	 */
	public Tile() {
	}

	/**
	 * BeĂˇllĂ­tja az aDir field Ă©rtĂ©kĂ©t a kapott Tile Ă©rtĂ©kĂ©re.
	 * 
	 * @param t
	 */
	public void setDirA(Tile t) {
		this.aDir = t;
	}

	/**
	 * BeĂˇllĂ­tja a bDir field Ă©rtĂ©kĂ©t a kapott Tile Ă©rtĂ©kĂ©re.
	 * 
	 * @param t
	 */
	public void setDirB(Tile t) {
		this.bDir = t;
	}

	/**
	 * Visszaadja az aDir field Ă©rtĂ©kĂ©t.
	 * 
	 * @return
	 */
	public Tile getDirA() {
		return this.aDir;
	}

	/**
	 * Visszaadja a bDir field Ă©rtĂ©kĂ©t.
	 * 
	 * @return
	 */
	public Tile getDirB() {
		return this.bDir;
	}

	/**
	 * BeĂˇllĂ­tja a train field Ă©rtĂ©kĂ©t a kapott Train Ă©rtĂ©kĂ©re.
	 * 
	 * @param t
	 */
	public void setTrain(Train t) {
		this.train = t;
		moveable = false;
	}

	/**
	 * BeĂˇllĂ­tja a trainElement field Ă©rtĂ©kĂ©t a kapott TrainElement Ă©rtĂ©kĂ©re.
	 * 
	 * @param e
	 */
	public void setElement(TrainElement e) {
		this.trainElement = e;
	}

	/**
	 * MegvizsgĂˇlja, hogy tĂ¶rtĂ©nt-e ĂĽtkĂ¶zĂ©s. Jelen esetben bekĂ©rjĂĽk a
	 * tesztelĹ‘tĹ‘l, hogy van-e a vonat elĹ‘tt akadĂˇly, ennek fĂĽggvĂ©nyĂ©ben dĂ¶ntjĂĽk
	 * el. Ha van valami, akkor tĂ¶rtĂ©nik ĂĽtkĂ¶zĂ©s, ha nincs, akkor megy tovĂˇbb a
	 * vonat.
	 * 
	 * @return True, ha tĂ¶rtĂ©nik ĂĽtkĂ¶zĂ©s. False, ha nem.
	 */
	public boolean checkCrash() {
		if (trainElement != null && train != null)
			return true;
		return false;
	}

	/**
	 * Visszaadja a moveable field Ă©rtĂ©kĂ©t.
	 * 
	 * @return
	 */
	public boolean isMovable() {
		return moveable;
	}

	/**
	 * @return visszadja hogy van-e mozdony a mezĹ‘n
	 */
	public boolean isFree() {
		return (null == train && null == trainElement);
	}

	public void printTile(int i, int j){
		
	}
	/**
	 * Ăśres metĂłdus, a leszĂˇrmazottak felĂĽlĂ­rjĂˇk, ha szĂĽksĂ©ges.
	 */
	public void changeState() {
		// empty on purpose
	}

	/**
	 * A lĂ©ptetĹ‘ fĂĽggvĂ©ny a mezĹ‘k felĂĽlĂ­rjĂˇk
	 * 
	 * @throws projlab.CrashException
	 */
	public void move() throws CrashException {
		// empty on purpose
	}

	/**
	 * A moveable field Ă©rtĂ©kĂ©t igazra ĂˇllĂ­tja.
	 */
	public void enableMove() {
		this.moveable = true;
	}
}

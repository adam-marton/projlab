package projlab;

public class CrossRail extends Tile {

	/**
	 * A mezĹ‘ egyik szomszĂ©dja.
	 */
	protected Tile cDir;

	/**
	 * A mezĹ‘ mĂˇsik szomszĂ©dja.
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
	 * BeĂˇllĂ­tja az aDir field Ă©rtĂ©kĂ©t a kapott Tile Ă©rtĂ©kĂ©re.
	 * 
	 * @param t
	 */
	public void setDirC(Tile t) {
		this.cDir = t;
	}

	/**
	 * BeĂˇllĂ­tja a bDir field Ă©rtĂ©kĂ©t a kapott Tile Ă©rtĂ©kĂ©re.
	 * 
	 * @param t
	 */
	public void setDirD(Tile t) {
		this.dDir = t;
	}

	/**
	 * Visszaadja az aDir field Ă©rtĂ©kĂ©t.
	 * 
	 * @return
	 */
	public Tile getDirC() {
		return this.cDir;
	}

	/**
	 * Visszaadja a bDir field Ă©rtĂ©kĂ©t.
	 * 
	 * @return
	 */
	public Tile getDirD() {
		return this.dDir;
	}

	/**
	 * a rajta ĂˇllĂł mozdony mozgatĂˇsĂˇĂ©rt felelĹ‘s fĂĽggvĂ©nyt hĂ­vja meg, ha az mĂ©g
	 * nem mozgott ebben a ciklusban megnĂ©zi, hogy a rajta lĂ©vĹ‘ vonat elĹ‘zĹ‘
	 * pozĂ­ciĂłja melyik mezĹ‘vel egyezik meg Ă©s a mĂˇsik irĂˇnyba kĂĽldi tovĂˇbb ha
	 * egyik irĂˇnyba se tud a vonat tovĂˇbbmenni -> ĂĽtkĂ¶zĂ©s
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
				throw new CrashException("Utkozes tortent, vesztettel!");
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

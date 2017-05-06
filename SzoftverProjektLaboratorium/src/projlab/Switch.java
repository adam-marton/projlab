package projlab;

/**
 *
 */
public class Switch extends Tile {

    /**
     * a Switch aktĂ­v kimeneti irĂˇnya
     */
    private Tile activeDir;

    /**
     * a Switch harmadik lehetsĂ©ges irĂˇnya
     */
    private Tile cDir;

    /**
     * Default konstruktor
     */
    public Switch() {
    	this.activeDir = this.bDir;
    }
    
    /**
     * A switch konstruktora, megkapja paramĂ©terkĂ©nt az aktĂ­v irĂˇnyt **csak tesztelĂ©shez**
     *
     * @param activeDir
     */
    public Switch(Tile activeDir) {
        this.activeDir = activeDir;
    }

    /**
     * visszaadja az Ă©ppen aktuĂˇlis kimeneti irĂˇnyt
     *
     * @return
     */
    public Tile getActiveDir() {
        return activeDir;
    }

    /**
     * beĂˇllĂ­tja a harmadik irĂˇnyt a kapott paramĂ©ternek
     *
     * @param t
     */
    public void setDirC(Tile t) {
        cDir = t;
    }
    /**
     * visszaadja a C kimeneti irĂˇnyt
     *
     * @return
     */
    public Tile getDirC() {
        return cDir;
    }

    /**
     * a vĂˇltĂł ĂˇllĂˇsĂˇt vĂˇltoztatja meg
     */
    @Override
    public void changeState() {
    	if(activeDir == null) {
    		activeDir = getDirB();
    	}
    	else if (activeDir == getDirB()) {
            activeDir = getDirC();
        } 
    	else if (activeDir == getDirC()) {
            activeDir = getDirB();
        }
    }
    
    public void printTile(int i, int j){
    	Main.drawer.addTrainToList(null, i, j);
    	if(train != null){
    		Main.drawer.addTrainToList("locomotive", i, j);
    	}
    	else if (trainElement != null){
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

    /**
     * a rajta ĂˇllĂł mozdony mozgatĂˇsĂˇĂ©rt felelĹ‘s fĂĽggvĂ©nyt hĂ­vja meg, ha az mĂ©g
     * nem mozgott ebben a ciklusban megnĂ©zi, hogy a rajta lĂ©vĹ‘ vonat elĹ‘zĹ‘
     * pozĂ­ciĂłja melyik mezĹ‘vel egyezik meg Ă©s a mĂˇsik irĂˇnyba kĂĽldi tovĂˇbb ha
     * egyik irĂˇnyba se tud a vonat tovĂˇbbmenni -> ĂĽtkĂ¶zĂ©s
     * @throws projlab.CrashException
     */
    @Override
    public void move() throws CrashException {
    	if(train != null){
	    	 if (train.getPrevPos() == getDirA() && activeDir!=null && getActiveDir().isFree()) {
	         	train.moveTrain(getActiveDir());
	         }
	         else if (train.getPrevPos() == getDirB()  && getDirA()!=null && getDirA().isFree() && getDirB() == getActiveDir()) {
	             train.moveTrain(getDirA());
	         }
	         else if(train.getPrevPos() == getDirB()  && getDirA()!=null && getDirA().isFree() && getDirC() == getActiveDir()){
	        	 train.moveTrain(getDirC());
	         }
	         else if(train.getPrevPos() == getDirC()  && getDirA()!=null && getDirA().isFree() && getDirB() == getActiveDir()){
	        	 train.moveTrain(getDirB());
	         }
	         else if(train.getPrevPos() == getDirC()  && getDirA()!=null && getDirA().isFree() && getDirC() == getActiveDir()){
	        	 train.moveTrain(getDirA());
	         }
	         else
	             throw new CrashException("Utkozes tortent, vesztettel!");
    	}
    }
}

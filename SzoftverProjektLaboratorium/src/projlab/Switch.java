package projlab;

/**
 *
 */
public class Switch extends Tile {

    /**
     * a Switch aktív kimeneti iránya
     */
    private Tile activeDir;

    /**
     * a Switch harmadik lehetséges iránya
     */
    private Tile cDir;

    /**
     * Default konstruktor
     */
    public Switch() {
    	this.activeDir = this.bDir;
    }
    
    /**
     * A switch konstruktora, megkapja paraméterként az aktív irányt **csak teszteléshez**
     *
     * @param activeDir
     */
    public Switch(Tile activeDir) {
        this.activeDir = activeDir;
    }

    /**
     * visszaadja az éppen aktuális kimeneti irányt
     *
     * @return
     */
    public Tile getActiveDir() {
        return activeDir;
    }

    /**
     * beállítja a harmadik irányt a kapott paraméternek
     *
     * @param t
     */
    public void setDirC(Tile t) {
        cDir = t;
    }
    /**
     * visszaadja a C kimeneti irányt
     *
     * @return
     */
    public Tile getDirC() {
        return cDir;
    }

    /**
     * a váltó állását változtatja meg
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
     * a rajta álló mozdony mozgatásáért felelős függvényt hívja meg, ha az még
     * nem mozgott ebben a ciklusban megnézi, hogy a rajta lévő vonat előző
     * pozíciója melyik mezővel egyezik meg és a másik irányba küldi tovább ha
     * egyik irányba se tud a vonat továbbmenni -> ütközés
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
	             throw new CrashException("Ütközés történt, vesztettél!");
    	}
    }
}

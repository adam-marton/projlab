package szkeleton;

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
            activeDir = cDir;
        } 
    	else if (activeDir == cDir) {
            activeDir = getDirB();
        }
    }
    
    public void printTile(int i, int j){
    	System.out.print("Switch "+i+"-"+j + " ");
    	if(getActiveDir() == getDirB()){
    		System.out.println("B");
    	} else System.out.println("C");
    	
    	if(train != null){
    		System.out.println("Train "+i+"-"+j);
    	}
    	else if(trainElement != null){
    		System.out.println("TrainElement "+i+"-"+j+" "+trainElement.getColor()+","+trainElement.isEmpty()+" ");
    	}
    }

    /**
     * a rajta álló mozdony mozgatásáért felelős függvényt hívja meg, ha az még
     * nem mozgott ebben a ciklusban megnézi, hogy a rajta lévő vonat előző
     * pozíciója melyik mezővel egyezik meg és a másik irányba küldi tovább ha
     * egyik irányba se tud a vonat továbbmenni -> ütközés
     * @throws szkeleton.CrashException
     */
    @Override
    public void move() throws CrashException {
    	if(train != null){
	    	 if (train.getPrevPos() == getDirA() && activeDir!=null && getActiveDir().isFree()) {
	         	train.moveTrain(getActiveDir());
	         }
	         else if (( train.getPrevPos() == getDirB() || train.getPrevPos() == getDirC() ) && getDirA()!=null && getDirA().isFree()) {
	             train.moveTrain(getDirA());
	         }
	         else
	             throw new CrashException("Ütközés történt, vesztettél!");
    	}
    }
}

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
    }
    
    /**
     * A switch konstruktora, megkapja paraméterként az aktív irányt
     *
     * @param activeDir
     */
    public Switch(Tile activeDir) {
        System.out.println("Switch");
        this.activeDir = activeDir;
    }

    /**
     * visszaadja az éppen aktuális kimeneti irányt
     *
     * @return
     */
    public Tile getActiveDir() {
        System.out.println(">[Switch].getActiveDir()");
        System.out.println("<[Switch].getActiveDir()");
        return activeDir;
    }

    /**
     * beállítja a harmadik irányt a kapott paraméternek
     *
     * @param t
     */
    public void setDirC(Tile t) {
        System.out.println(">[Switch].setDirC()");
        cDir = t;
        System.out.println("<[Switch].setDirC()");
    }
    /**
     * visszaadja a C kimeneti irányt
     *
     * @return
     */
    public Tile getDirC() {
        System.out.println(">[Switch].getActiveDir()");
        System.out.println("<[Switch].getActiveDir()");
        return cDir;
    }

    /**
     * a váltó állását változtatja meg
     */
    @Override
    public void changeState() {
    	System.out.println(">[Switch].changeState()");
        if (activeDir == getDirB()) {
            activeDir = cDir;
        } else if (activeDir == cDir) {
            activeDir = getDirB();
        }
    	System.out.println("<[Switch].changeState()");
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
    	System.out.println(">[Switch].move()");
    	 if (train.getPrevPos() == getDirA() && activeDir!=null && getActiveDir().isFree()) {
         	train.moveTrain(getActiveDir());
         }
         else if (train.getPrevPos() == getDirB() ||train.getPrevPos() == getDirC() && getDirA()!=null && getDirA().isFree()) {
             train.moveTrain(getDirA());
         }
         else
             throw new CrashException("Ütközés");
    	System.out.println("<[Switch].move()");
    }
}

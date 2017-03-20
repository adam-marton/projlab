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
        if (train.getPrevPos() == getDirA() && activeDir.isFree()) {
            train.moveTrain(activeDir);
        } else if (train.getPrevPos() == getDirB() && activeDir.isFree()) {
            train.moveTrain(activeDir);
        } else {
            throw new CrashException();
        }
    	System.out.println("<[Switch].move()");
    }
}

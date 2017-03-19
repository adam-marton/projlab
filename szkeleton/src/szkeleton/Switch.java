package szkeleton;

/**
 * 
 */
public class Switch extends Tile {

    /**
     * Default constructor
     * kiírja a konzolra, hogy Switch
     */
    public Switch(Tile activeDir) {
    	System.out.println("Switch");
        this.activeDir = activeDir;
    }

    /**
     * a Switch aktív kimeneti iránya
     */
    private Tile activeDir;

    /**
     * a Switch harmadik lehetséges iránya
     */
    private Tile cDir;

    /**
     * visszaadja az éppen aktuális kimeneti irányt
     * @return
     */
    public Tile getActiveDir() {
        return activeDir;
    }

    /**
     * beállítja a harmadik irányt a kapott paraméternek
     * @param t
     */
    public void setDirC(Tile t) {
        cDir = t;
    }

    /**
     * a váltó állását változtatja meg
     */
    @Override
    public void changeState() {
        if(activeDir == getDirB())
        {
        	activeDir = cDir;
        }
        else if(activeDir == cDir)
        {
        	activeDir = getDirB();
        }
    }

    /**
     *	a rajta álló mozdony mozgatásáért felelős függvényt hívja meg,
     * ha az még nem mozgott ebben a ciklusban
     * megnézi, hogy a rajta lévő vonat előző pozíciója melyik mezővel
     * egyezik meg és a másik irányba küldi tovább
     * ha egyik irányba se tud a vonat továbbmenni -> ütközés
     */
    @Override
    public void move() {
    	if(train.getPrevPos() == getDirA() && activeDir.isFree())
    		train.moveTrain(activeDir);
    	else if(train.getPrevPos() == getDirB() && activeDir.isFree())
    		train.moveTrain(activeDir);
    	else
    	{
    		//throw new Exception("Ütközés történt");
    	}
    }
}
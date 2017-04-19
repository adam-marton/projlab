package szkeleton;

import java.util.Scanner;

/**
 *
 */
public class Rail extends Tile {

	/**
	 * ha látható, akkor egy sima Rail, ha nem látható akkor Tunnel-ben lévő
	 * láthatatlan Rail (alagútelem)
	 */
	private boolean visible;

	/**
	 * A rail konstruktora, ha a hamis értékkel hívjuk meg akkor alagútelem lesz
	 *
	 * @param visible
	 */
	public Rail(boolean visible) {
		this.visible = visible;
	}

	/**
	 * visszatér azzal, hogy látható-e a Rail vagy sem
	 *
	 * @return
	 */
	public boolean isVisible() {
		return visible;
	}

	/**
     * a rajta álló mozdony mozgatásáért felelős függvényt hívja meg, ha az még
     * nem mozgott ebben a ciklusban megnézi, hogy a rajta lévő vonat előző
     * pozíciója melyik mezővel egyezik meg és a másik irányba küldi tovább ha
     * egyik irányba se tud a vonat továbbmenni -> ütközés
     *
     * @throws szkeleton.CrashException
     */
    @Override
    public void move() throws CrashException{
    	if(train != null){
	        if (train.getPrevPos() == getDirA() && getDirB()!=null && getDirB().isFree()) {
	        	train.moveTrain(getDirB());
	        }
	        else if (train.getPrevPos() == getDirB() && getDirA()!=null && getDirA().isFree()) {
	            train.moveTrain(getDirA());
	        }
	        else
	            throw new CrashException("Ütközés történt, vesztettél!");
    	}
    }
}

package szkeleton;

import java.util.Scanner;

/**
 * 
 */
public class Rail extends Tile {

    /**
     * Default constructor
     * kiírja, hogy Rail
     */
    public Rail(boolean visible) {
    	this.visible = visible;
    	System.out.println("Rail");
    }

    /**
     * ha látható, akkor egy sima Rail, ha nem látható
     * akkor Tunnel-ben lévő láthatatlan Rail (alagútelem)
     */
    private boolean visible;

    /**
     * visszatér azzal, hogy látható-e a Rail vagy sem,
     * amit a kedves felhasználótól kérdezünk meg
     */
    public boolean isVisible() {
    	System.out.println("Látható a sín?(Ha alagútban van csak akkor nem látható)");
        Scanner s = new Scanner(System.in);
        String input = s.nextLine();
        if("I".equals(input)) {
            return true;
        } else if("N".equals(input)) {
            return false;
        }
        throw new IllegalArgumentException("Invalid input");
    }

    /**
     * a rajta álló mozdony mozgatásáért felelős függvényt hívja meg,
     * ha az még nem mozgott ebben a ciklusban
     * megnézi, hogy a rajta lévő vonat előző pozíciója melyik mezővel
     * egyezik meg és a másik irányba küldi tovább
     * ha egyik irányba se tud a vonat továbbmenni -> ütközés
     */
    @Override
    public void move() {
    	if(train.getPrevPos() == getDirA() && getDirB().isFree())
    		train.moveTrain(getDirB());
    	else if(train.getPrevPos() == getDirB() && getDirA().isFree())
    		train.moveTrain(getDirA());
    	else
    	{
    		//throw new Exception("Ütközés történt");
    	}
    }
}
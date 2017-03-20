package szkeleton;

import java.util.Scanner;

/**
 * 
 */
public abstract class Tile {

    /**
     * A mező egyik szomszédja.
     */
    private Tile aDir;

    /**
     * A mező másik szomszédja.
     */
    private Tile bDir;

    /**
     * A mezőn lévő mozdony.
     */
    protected Train train;

    /**
     * A mezőn lévő vonatelem.
     */
    protected TrainElement trainElement;

    /**
     * Megmondja, hogy mozgatható-e a mezőn lévő vonat.
     * Ez azért fontos, hogy egy mozdonyt ne lehessen egy körben kétszer mozgatni.
     */
    private boolean moveable;

    /**
     * Default constructor
     */
    public Tile() {
    }

    /**
     * Beállítja az aDir field értékét a kapott Tile értékére.
     * @param t
     */
    public void setDirA(Tile t) {
    	System.out.println(">[Tile].setDirA()");
        this.aDir = t;
    	System.out.println("<[Tile].setDirA()");
    }

    /**
     * Beállítja a bDir field értékét a kapott Tile értékére.
     * @param t
     */
    public void setDirB(Tile t) {
    	System.out.println(">[Tile].setDirB()");
        this.bDir = t;
    	System.out.println("<[Tile].setDirB()");
    }

    /**
     * Visszaadja az aDir field értékét.
     * @return
     */
    public Tile getDirA() {
    	System.out.println(">[Tile].getDirA()");
    	System.out.println("<[Tile].getDirA()");
        return this.aDir;
    }

    /**
     * Visszaadja a bDir field értékét.
     * @return
     */
    public Tile getDirB() {
    	System.out.println(">[Tile].getDirB()");
    	System.out.println("<[Tile].getDirB()");
        return this.bDir;
    }

    /**
     * Beállítja a train field értékét a kapott Train értékére. 
     * @param t
     */
    public void setTrain(Train t) {
    	System.out.println(">[Tile].setTrain()");
        this.train = t;
        moveable = false;
    	System.out.println("<[Tile].setTrain()");
    }

    /**
     * Beállítja a trainElement field értékét a kapott TrainElement értékére.
     * @param e
     */
    public void setElement(TrainElement e) {
    	System.out.println(">[Tile].setElement()");
        this.trainElement = e;
    	System.out.println("<[Tile].setElement()");
    }

    /**
     * Megvizsgálja, hogy történt-e ütközés.
     * Jelen esetben bekérjük a tesztelőtől, hogy van-e a vonat előtt akadály, ennek függvényében döntjük el.
     * Ha van valami, akkor történik ütközés, ha nincs, akkor megy tovább a vonat.
     * @return True, ha történik ütközés. False, ha nem.
     */
    public boolean checkCrash() {
    	System.out.println(">[Tile].chechCrash()");
        System.out.println("Van a vonat előtt valamilyen akadály?");
        Scanner s = new Scanner(System.in);
        String input = s.nextLine();
    	System.out.println("<[Tile].checkCrash()");
        if("I".equals(input.toUpperCase())) {
            return true;
        } else if("N".equals(input.toUpperCase())) {
            return false;
        }
        throw new IllegalArgumentException("Invalid input");
    }

    /**
     * Visszaadja a moveable field értékét.
     * @return
     */
    public boolean isMovable() {
    	System.out.println(">[Tile].isMovable()");
    	System.out.println("<[Tile].isMovable()");
        return moveable;
    }

    /**
     * @return visszadja hogy van-e mozdony a mezőn
     */
    public boolean isFree() {
    	System.out.println(">[Tile].isFree()");
    	System.out.println("<[Tile].isFree()");
        return null == train;
    }

    /**
     * Üres metódus, a leszármazottak felülírják, ha szükséges.
     */
    public void changeState() {
    	System.out.println(">[Tile].changeState()");
        // empty on purpose
    	System.out.println("<[Tile].changeState()");
    }

    /**
     *  A léptető függvény a mezők felülírják
     * @throws szkeleton.CrashException
     */
    public void move() throws CrashException {
    	System.out.println(">[Tile].move()");
    	System.out.println("<[Tile].move()");
    }

    /**
     * A moveable field értékét igazra állítja.
     */
    public void enableMove() {
    	System.out.println(">[Tile].enableMove()");
        this.moveable = true;
    	System.out.println("<[Tile].enableMove()");
    }
}
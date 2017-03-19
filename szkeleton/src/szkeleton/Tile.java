package szkeleton;

import java.util.Scanner;

/**
 * 
 */
public abstract class Tile {

    /**
     * Default constructor
     */
    public Tile() {
    }

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
     * Beállítja az aDir field értékét a kapott Tile értékére.
     * @param t
     */
    public void setDirA(Tile t) {
        this.aDir = t;
    }

    /**
     * Beállítja a bDir field értékét a kapott Tile értékére.
     * @param t
     */
    public void setDirB(Tile t) {
        this.bDir = t;
    }

    /**
     * Visszaadja az aDir field értékét.
     * @return
     */
    public Tile getDirA() {
        return this.aDir;
    }

    /**
     * Visszaadja a bDir field értékét.
     * @return
     */
    public Tile getDirB() {
        return this.bDir;
    }

    /**
     * Beállítja a train field értékét a kapott Train értékére. 
     * @param t
     */
    public void setTrain(Train t) {
        this.train = t;
    }

    /**
     * Beállítja a trainElement field értékét a kapott TrainElement értékére.
     * @param e
     */
    public void setElement(TrainElement e) {
        this.trainElement = e;
    }

    /**
     * Megvizsgálja, hogy történt-e ütközés.
     * Jelen esetben bekérjük a tesztelőtől, hogy van-e a vonat előtt akadály, ennek függvényében döntjük el.
     * Ha van valami, akkor történik ütközés, ha nincs, akkor megy tovább a vonat.
     * @return True, ha történik ütközés. False, ha nem.
     */
    public boolean checkCrash() {
        System.out.println("Van a vonat előtt valamilyen akadály?");
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
     * Visszaadja a moveable field értékét.
     * @return
     */
    public boolean isMovable() {
        return moveable;
    }

    /**
     * @return
     */
    public boolean isFree() {
        return null == train;
    }

    /**
     * Üres metódus, a leszármazottak felülírják, ha szükséges.
     */
    public void changeState() {
        // empty on purpose
    }

    /**
     * 
     */
    public void move() {
        this.train.moveTrain(bDir);
    }

    /**
     * A moveable field értékét igazra állítja.
     */
    public void enableMove() {
        this.moveable = true;
    }
}
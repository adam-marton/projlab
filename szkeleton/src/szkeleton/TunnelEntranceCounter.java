package szkeleton;

/**
 *
 * @author Ádám
 */
public class TunnelEntranceCounter {

   private static TunnelEntranceCounter instance = null;
   private int counter = 0;
   
   private TunnelEntranceCounter() {
   }
   
    /**
     *
     * @return
     */
    public static TunnelEntranceCounter getInstance() {
      if(null == instance) {
         instance = new TunnelEntranceCounter();
      }
      return instance;
   }

    /**
     *
     * Visszaadja a counter számláló értékét.
     * @return
     */
    public int getCounter() {
        return counter;
    }

    /**
     * Beállítja a counter számlálónak a paraméterül kapott int értékét.
     * @param counter
     */
    public void setCounter(int counter) {
        this.counter = counter;
    }
}

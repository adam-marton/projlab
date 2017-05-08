
package projlab;

/**
 * A nyitott alagút bejáratok számlálásához használt osztály
 * 
 * @author Adam
 */
public class TunnelEntranceCounter {

	private static TunnelEntranceCounter instance = null;
	private int counter = 0;
	boolean toDraw = true;

	private TunnelEntranceCounter() {
	}

	/**
	 * @return
	 */
	public static TunnelEntranceCounter getInstance() {
		if (null == instance) {
			instance = new TunnelEntranceCounter();
		}
		return instance;
	}

	/**
	 *
	 * Visszaadja a counter számláló értékét.
	 * 
	 * @return
	 */
	public int getCounter() {
		return counter;
	}

	public boolean shouldIDraw() {
		return toDraw;
	}

	public void drawPlease() {
		toDraw = true;
	}

	public void dontDrawPlease() {
		toDraw = false;
	}

	/**
	 * Beállítja a counter számlálónak a paraméterül kapott int értékét.
	 * 
	 * @param counter
	 */
	public void incCounter() {
		counter++;
		System.out.println(counter);
	}

	public void decCounter() {
		counter--;
		System.out.println(counter);
	}

	public void resetCounter() {
		counter = 0;
	}
}

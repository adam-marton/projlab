package projlab;

/**
 * A szenes kocsit megvalósító osztály
 */

public class CoalCarrier extends TrainElement {

	/**
	 * A szenesvagon konstruktora, létrehozza a szenesvagont
	 * 
	 * @param pos
	 * @param color
	 *
	 */
	public CoalCarrier(Tile pos, Color color) {
		super(pos, color);
		this.empty = true;
	}

	/**
	 * nincsenek utasok ezért ez nem csinál semmit
	 */
	@Override
	public void getOff() {
		// empty on purpose
	}

	/**
	 * nincsenek utasok ezért ez nem csinál semmit
	 */
	@Override
	public void getOn() {
		// empty on purpose
	}

}

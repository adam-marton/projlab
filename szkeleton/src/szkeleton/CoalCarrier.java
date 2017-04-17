package szkeleton;

/**
*
*/

public class CoalCarrier extends TrainElement {

	/**
	 * @param pos
	 * @param color
	 *            A szenesvagon konstruktora, létrehozza a szenesvagont
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

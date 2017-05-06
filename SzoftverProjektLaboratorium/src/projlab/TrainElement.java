package projlab;

/**
 *
 */
public class TrainElement {

	/**
	 * a TrainElement jelenlegi pozĂ­ciĂłja
	 */
	protected Tile pos;

	/**
	 * a TrainElement elĹ‘zĹ‘ pozĂ­ciĂłja
	 */
	protected Tile prevPos;

	/**
	 * a TrainElement szĂ­ne
	 */
	protected Color color;

	/**
	 * tĂˇrolja, hogy mĂˇr lezsĂˇlltak-e az utasok vagyis, hogy a kocsi ĂĽres
	 */
	protected boolean empty;

	/**
	 * megadja, hogy a kocsit megelĹ‘zĹ‘ Ă¶sszes vagon ĂĽres-e
	 */
	protected boolean nextToFree;

	/**
	 * @param pos
	 * @param color
	 *            A vagon konstruktora, lĂ©trehozza a vagont a kapott szĂ­nnel
	 */
	public TrainElement(Tile pos, Color color) {
		this.pos = pos;
		if (this.pos != null)
			this.pos.setElement(this);
		this.color = color;
		this.empty = false;
		this.nextToFree = false;
	}

	/**
	 * visszatĂ©r a vagon jelenlegi pozĂ­ciĂłjĂˇval
	 *
	 * @return
	 */
	public Tile getPosition() {
		return this.pos;
	}

	/**
	 * visszatĂ©r a vagon elĹ‘zĹ‘ pozĂ­ciĂłjĂˇval
	 *
	 * @return
	 */
	public Tile getPrevPos() {
		return this.prevPos;
	}

	/**
	 * @param pos
	 */
	public void move(Tile pos) {
		this.prevPos = this.pos;
		if (this.prevPos != null)
			this.prevPos.setElement(null);
		this.pos = pos;
		if (this.pos != null)
			this.pos.setElement(this);
		;
	}

	/**
	 * megprĂłbĂˇlja leszĂˇllĂ­tani az utasokat, ha ĂĽres akkor felrakni
	 */
	public void getOff() {
		if (isEmpty()) {
			getOn();
		} 
		else {
			if (isNextToFree()) {
				this.empty = true;
			}
		}
	}

	/**
	 *  utasok felszĂˇllĂˇsa
	 */
	public void getOn() {
		this.empty = false;
	}

	/**
	 * visszaadja a vagon szĂ­nĂ©t
	 * 
	 * @return
	 */
	public Color getColor() {
		return this.color;
	}

	/**
	 * igazzal tĂ©r vissza ha a vagon ĂĽres,
	 * 
	 * @return
	 */
	public boolean isEmpty() {
		return this.empty;
	}

	/**
	 * csak a tesztelĂ©shez kell
	 */
	public void setEmpty() {
		this.empty = true;
	}

	/**
	 * igazzal tĂ©r vissza ha az Ă¶sszes ezt megelĹ‘zĹ‘ vagon ĂĽres. egyĂ©bkĂ©nt
	 * hamissal
	 * 
	 * @return
	 */
	public boolean isNextToFree() {
		return this.nextToFree;
	}

	/**
	 * Igazra ĂˇllĂ­tja a vĂˇltozĂł Ă©rtĂ©kĂ©t ha az Ă¶sszes ezt megelĹ‘zĹ‘ vagon ĂĽres
	 */
	public void SetNextToFree() {
		this.nextToFree = true;
	}
}

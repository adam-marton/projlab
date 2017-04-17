package szkeleton;

/**
 *
 */
public class TrainElement {

	/**
	 * a TrainElement jelenlegi pozíciója
	 */
	protected Tile pos;

	/**
	 * a TrainElement előző pozíciója
	 */
	protected Tile prevPos;

	/**
	 * a TrainElement színe
	 */
	protected Color color;

	/**
	 * tárolja, hogy már lezsálltak-e az utasok vagyis, hogy a kocsi üres
	 */
	protected boolean empty;

	/**
	 * megadja, hogy a kocsit megelőző összes vagon üres-e
	 */
	protected boolean nextToFree;

	/**
	 * @param pos
	 * @param color
	 *            A vagon konstruktora, létrehozza a vagont a kapott színnel
	 */
	public TrainElement(Tile pos, Color color) {
		System.out.println("TrainElement");
		this.pos = pos;
		if (this.pos != null)
			this.pos.setElement(this);
		this.color = color;
		this.empty = false;
		this.nextToFree = false;
	}

	/**
	 * visszatér a vagon jelenlegi pozíciójával
	 *
	 * @return
	 */
	public Tile getPosition() {
		return this.pos;
	}

	/**
	 * visszatér a vagon előző pozíciójával
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
		if (prevPos != null)
			this.prevPos.setElement(null);
		this.pos = pos;
		if (this.pos != null)
			this.pos.setElement(this);
		;
	}

	/**
	 * megpróbálja leszállítani az utasokat, ha üres akkor felrakni
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
	 *  utasok felszállása
	 */
	public void getOn() {
		this.empty = false;
	}

	/**
	 * visszaadja a vagon színét
	 * 
	 * @return
	 */
	public Color getColor() {
		return this.color;
	}

	/**
	 * igazzal tér vissza ha a vagon üres,
	 * 
	 * @return
	 */
	public boolean isEmpty() {
		return this.empty;
	}

	/**
	 * csak a teszteléshez kell
	 */
	public void setEmpty() {
		this.empty = true;
	}

	/**
	 * igazzal tér vissza ha az összes ezt megelőző vagon üres. egyébként
	 * hamissal
	 * 
	 * @return
	 */
	public boolean isNextToFree() {
		return this.nextToFree;
	}

	/**
	 * Igazra állítja a változó értékét ha az összes ezt megelőző vagon üres
	 */
	public void SetNextToFree() {
		this.nextToFree = true;
	}
}

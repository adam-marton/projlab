package projlab;

import java.util.*;

/**
 * A mozdonyt megvalósító osztály
 */
public class Train {

	/**
	 * A vagonokat tároló lista
	 */
	private final List<TrainElement> elements = new ArrayList<TrainElement>();

	/**
	 * A mozdony jelenlegi tartózkodási helye
	 */
	private Tile pos;

	/**
	 * A mozdony előző tartózkodási helye
	 */
	private Tile prevPos = null;

	/**
	 * Konstruktor, létrehozza a vonatot a kapott paraméterekkel
	 * 
	 * @param pos
	 *            a vonat pozíciója
	 * @param color
	 *            a vagonok színeit tároló tömb
	 */
	public Train(Tile pos, List<Color> color) {
		this.pos = pos;
		this.pos.setTrain(this);
		for (Color i : color) {
			if (i == Color.BLACK) {
				this.addElement(new CoalCarrier(null, i));
			} else
				this.addElement(new TrainElement(null, i));
		}
	}

	/**
	 * Hozzáad egy paraméterként kapott vagont a tárolóhoz
	 * 
	 * @param e
	 *            Egy vagon
	 */
	public void addElement(TrainElement e) {
		elements.add(e);
	}

	/**
	 * Visszaadja azt a mezőt ahol az előző ciklusban állt a mozdony
	 * 
	 * @return prevPos Az előző pozíció
	 */
	public Tile getPrevPos() {
		return prevPos;
	}

	/**
	 * Beállítja, hogy jelenleg hol áll a mozdony
	 * 
	 * @param pos
	 *            Amező ahova a mozdonyt állítjuk
	 */
	public void setPos(Tile pos) {
		this.prevPos = this.pos;
		this.prevPos.setTrain(null);
		this.pos = pos;
		this.pos.setTrain(this);
	}

	public boolean isTheTrainEmpty() {
		for (int i = 0; i < elements.size(); i++) {
			if (!elements.get(i).isEmpty())
				return false;
		}
		return true;
	}

	/**
	 * Mozgatja a vonatot a következő mezőre, kijelöli hogy melyik vagonról
	 * szállhatnak le az utasok.
	 * 
	 * @param pos
	 *            A kovetkező mező
	 */
	public void moveTrain(Tile pos) {
		this.prevPos = this.pos;
		if (prevPos != null)
			this.prevPos.setTrain(null);
		this.pos = pos;
		this.pos.setTrain(this);
		int j = 0;
		while (j < elements.size()) {
			elements.get(j).nextToFree = false;
			j++;
		}
		j = 0;
		while (elements.get(j).isEmpty()) {
			j++;
			if (j == elements.size()) {
				j--;
				break;
			}
		}
		elements.get(j).SetNextToFree();
		elements.get(0).move(this.prevPos);
		for (int i = 1; i < elements.size(); i++) {
			elements.get(i).move(elements.get(i - 1).getPrevPos());
		}
	}
}

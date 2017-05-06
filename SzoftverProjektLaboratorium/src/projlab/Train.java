package projlab;

import java.util.*;

/**
 *
 */
public class Train {

	/**
	 * A vagonokat tĂˇrolĂł lista
	 */
	private final List<TrainElement> elements = new ArrayList<TrainElement>();

	/**
	 * A mozdony jelenlegi tartĂłzkodĂˇsi helye
	 */
	private Tile pos;

	/**
	 * A mozdony elĹ‘zĹ‘ tartĂłzkodĂˇsi helye
	 */
	private Tile prevPos = null;

	/**
	 * @param pos
	 *            a vonat pozĂ­ciĂłja
	 * @param color
	 *            a vagonok szĂ­neit tĂˇrolĂł tĂ¶mb Konstruktor, lĂ©trehozza a
	 *            vonatot a kapott paramĂ©terekkel
	 */
	public Train(Tile pos, List<Color> color) {
		this.pos = pos;
		this.pos.setTrain(this);
		for (Color i : color) {
			if (i == Color.BLACK){
				this.addElement(new CoalCarrier(null, i));
			}
			else this.addElement(new TrainElement(null, i));
		}
	}

	/**
	 * @param e
	 *            Egy vagon HozzĂˇad egy paramĂ©terkĂ©nt kapott vagont a tĂˇrolĂłhoz
	 */
	public void addElement(TrainElement e) {
		elements.add(e);
	}

	/**
	 * @return prevPos Az elĹ‘zĹ‘ pozĂ­ciĂł Visszaadja azt a mezĹ‘t ahol az elĹ‘zĹ‘
	 *         ciklusban Ăˇllt a mozdony
	 */
	public Tile getPrevPos() {
		return prevPos;
	}

	/**
	 * @param pos
	 *            AmezĹ‘ ahova a mozdonyt ĂˇllĂ­tjuk BeĂˇllĂ­tja, hogy jelenleg hol
	 *            Ăˇll a mozdony
	 */
	public void setPos(Tile pos) {
		this.prevPos = this.pos;
		this.prevPos.setTrain(null);
		this.pos = pos;
		this.pos.setTrain(this);
	}
	
	public boolean isTheTrainEmpty(){
		for(int i = 0; i < elements.size(); i++){
			if(!elements.get(i).isEmpty()) return false;
		}
		return true;
	}

	/**
	 * @param pos
	 *            A kovetkezĹ‘ mezĹ‘ Mozgatja a vonatot a kĂ¶vetkezĹ‘ mezĹ‘re,
	 *            kijelĂ¶li hogy melyik vagonrĂłl szĂˇllhatnak le az utasok.
	 */
	public void moveTrain(Tile pos) {
		this.prevPos = this.pos;
		if (prevPos != null)
			this.prevPos.setTrain(null);
		this.pos = pos;
		this.pos.setTrain(this);
		int j = 0;
		
		while (elements.get(j).isEmpty()) {
			j++;
			if(j == elements.size()) {
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

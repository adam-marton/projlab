package szkeleton;

import java.util.*;

/**
 *
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
     * @param pos a vonat pozíciója
     * @param color a vagonok színeit tároló tömb Konstruktor, létrehozza a
     * vonatot a kapott paraméterekkel
     */
    public Train(Tile pos, List<Color> color) {
        System.out.println("Ich bin egy Train, mit viele TrainElement"); //Train vagyok sok TrainElement-el
        this.pos = pos;
        this.pos.setTrain(this);
        for (Color i : color) {
            this.addElement(new TrainElement(null, i));
        }
    }

    /**
     * @param e Egy vagon Hozzáad egy paraméterként kapott vagont a tárolóhoz
     */
    public void addElement(TrainElement e) {
    	System.out.println(">[Train].addElement()");
        elements.add(e);
    	System.out.println("<[Train].addElement()");
    }

    /**
     * @return prevPos Az előző pozíció Visszaadja azt a mezőt ahol az előző
     * ciklusban állt a mozdony
     */
    public Tile getPrevPos() {
    	System.out.println(">[Train].getPrevPos()");
    	System.out.println("<[Train].getPrevPos()");
        return prevPos;
    }

    /**
     * @param pos Amező ahova a mozdonyt állítjuk Beállítja, hogy jelenleg hol
     * áll a mozdony
     */
    public void setPos(Tile pos) {
    	System.out.println(">[Train].setPos()");
        this.prevPos = this.pos;
        this.prevPos.setTrain(null);
        this.pos = pos;
        this.pos.setTrain(this);
    	System.out.println("<[Train].setPos()");
    }

    /**
     * @param pos A kovetkező mező Mozgatja a vonatot a következő mezőre,
     * kijelöli hogy melyik vagonról szállhatnak le az utasok.
     */
    public void moveTrain(Tile pos) {
    	System.out.println(">[Train].moveTrain()");
        this.prevPos = this.pos;
        this.prevPos.setTrain(null);
        this.pos = pos;
        this.pos.setTrain(this);
        int j = 0;
        while (elements.get(j).isEmpty()) {
            j++;
        }
        elements.get(j).SetNextToFree();
        elements.get(0).move(this.prevPos);
        for (int i = 1; i < elements.size(); i++) {
            elements.get(i).move(elements.get(i - 1).getPrevPos());
        }
    	System.out.println("<[Train].moveTrain()");
    }
}

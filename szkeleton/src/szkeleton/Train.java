package szkeleton;

import java.util.*;

/**
 * 
 */
public class Train {
   
    /**
     * @param pos  a vonat pozíciója
     * @param color a vagonok színeit tároló tömb
     * Konstruktor, létrehozza a vonatot a kapott paraméterekkel
     */
    public Train(Tile ps, List<Color> color) {
    	System.out.println("Ich bin egy Train, mit viele TrainElement"); //Train vagyok sok TrainElement-el
    	pos=ps;
    	for (Color i : color)
    	{
    		addElement(new TrainElement(null,i));
    	}
    	}

    /**
     *  A vagonokat tároló lista
     */
    private List<TrainElement> elements=new ArrayList<TrainElement>();

    /**
     * A mozdony jelenlegi tartózkodási helye
     */
    private Tile pos;

    /**
     * A mozdony előző tartózkodási helye
     */
    private Tile prevPos=null;

    /**
     * @param e Egy vagon
     * Hozzáad egy paraméterként kapott vagont a tárolóhoz
     */
    public void addElement(TrainElement e) {
        elements.add(e);
    }

    /**
     * @return prevPos Az előző pozíció
     * Visszaadja azt a mezőt ahol az előző ciklusban állt a mozdony
     */
    public Tile getPrevPos() {
        // TODO implement here
        return prevPos;
    }

    /**
     * @param pos Amező ahova a mozdonyt állítjuk
     * Beállítja, hogy jelenleg hol áll a mozdony
     */
    public void setPos(Tile ps) {
        prevPos=pos;
    	pos=ps;
        
    }

    /**
     * @param pos A kovetkező mező
     * Mozgatja a vonatot a következő mezőre
     */
    public void moveTrain(Tile ps) {
        setPos(ps);
        elements.get(0).move(prevPos);
        for (int i=1; i<elements.size(); i++)
        {
        	 elements.get(i).move(elements.get(i-1).getPrevPos());
        }
    }
}

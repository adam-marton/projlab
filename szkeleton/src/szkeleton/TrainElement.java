package szkeleton;

/**
 * 
 */
public class TrainElement {

    /**
     * @param pos 
     * @param color
     * A vagon konstruktora, létrehozza a vagont a kapott színnel
     */
    public TrainElement(Tile ps, Color clr) {
    	System.out.println("[TrainElement].TrainElement()");
    	pos=ps;
    	pos.setElement(this);
    	color=clr;
    }
    
    /**
     * a TrainElement jelenlegi pozíciója
     */
    private Tile pos;

    /**
     * a TrainElement előző pozíciója
     */
    private Tile prevPos;

    /**
     * a TrainElement színe
     */
    private Color color;

    /**
     * tárolja, hogy már lezsálltak-e az utasok
     * vagyis, hogy a kocsi üres
     */
    private boolean empty;

    /**
     * megadja, hogy a kocsit megelőző összes vagon üres-e
     */
    private boolean nextToFree;

    /**
     * visszatér a vagon jelenlegi pozíciójával
     */
    public Tile getPosition() {
    	System.out.println("[TrainElement].getPosition()");
        return pos;
    }

    /**
     * visszatér a vagon előző pozíciójával
     */
    public Tile getPrevPos() {
    	System.out.println("[TrainElement].getPrevPos()");
        return prevPos;
    }

    /**
     * @param pos
     */
    public void move(Tile ps) {
    	System.out.println("[TrainElement].move()");
    	prevPos = pos;
    	prevPos.setElement(null);
    	pos = ps;
    	pos.setElement(this);
    }

    /**
     * megpróbálja leszállítani az utasokat
     */
    public void getOff() {
    	System.out.println("[TrainElement].getOff()");
    	if (isNextToFree())
    		{
    		empty=true;
    		}
    }

    /**
     * visszaadja a vagon színét
     */
    public Color getColor() {
    	System.out.println("[TrainElement].getColor()");
        return color;
    }

    /**
     * igazzal tér vissza ha a vagon üres,
     */
    public boolean isEmpty() {
    	System.out.println("[TrainElement].isEmpty()");
        return empty;
    }

    /**
     * igazzal tér vissza ha az összes ezt megelőző vagon üres.
     * egyébként hamissal
     */
    public boolean isNextToFree() {
    	System.out.println("[TrainElement].isNextToFree()");
        return nextToFree;
    }

    /**
     * Igazra állítja a változó értékét ha az összes ezt megelőző vagon üres
     */
    public void SetNextToFree() {
    	nextToFree=true;
    }
}
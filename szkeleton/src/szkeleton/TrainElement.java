package szkeleton;

/**
 *
 */
public class TrainElement {

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
     * tárolja, hogy már lezsálltak-e az utasok vagyis, hogy a kocsi üres
     */
    private boolean empty;

    /**
     * megadja, hogy a kocsit megelőző összes vagon üres-e
     */
    private boolean nextToFree=false;

    /**
     * @param pos
     * @param color A vagon konstruktora, létrehozza a vagont a kapott színnel
     */
    public TrainElement(Tile pos, Color color) {
        System.out.println("TrainElement");
        this.pos = pos;
        this.pos.setElement(this);
        this.color = color;
        this.empty=false;
    }

    /**
     * visszatér a vagon jelenlegi pozíciójával
     *
     * @return
     */
    public Tile getPosition() {
        System.out.println(">[TrainElement].getPosition()");
        System.out.println("<[TrainElement].getPosition()");
        return this.pos;
    }

    /**
     * visszatér a vagon előző pozíciójával
     *
     * @return
     */
    public Tile getPrevPos() {
        System.out.println(">[TrainElement].getPrevPos()");
        System.out.println("<[TrainElement].getPrevPos()");
        return this.prevPos;
    }

    /**
     * @param pos
     */
    public void move(Tile pos) {
        System.out.println(">[TrainElement].move()");
        this.prevPos = this.pos;
        this.prevPos.setElement(null);
        this.pos = pos;
        this.pos.setElement(this);
        System.out.println("<[TrainElement].move()");
    }

    /**
     * megpróbálja leszállítani az utasokat
     */
    public void getOff() {
        System.out.println(">[TrainElement].getOff()");
        if (isNextToFree()) {
            this.empty = true;
        }
        System.out.println("<[TrainElement].getOff()");
    }

    /**
     * visszaadja a vagon színét
     * @return 
     */
    public Color getColor() {
        System.out.println(">[TrainElement].getColor()");
        System.out.println("<[TrainElement].getColor()");
        return this.color;
    }

    /**
     * igazzal tér vissza ha a vagon üres,
     * @return 
     */
    public boolean isEmpty() {
        System.out.println(">[TrainElement].isEmpty()");
        System.out.println("<[TrainElement].isEmpty()");
        return this.empty;
    }

    /**
     * igazzal tér vissza ha az összes ezt megelőző vagon üres. egyébként
     * hamissal
     * @return 
     */
    public boolean isNextToFree() {
        System.out.println(">[TrainElement].isNextToFree()");
        System.out.println("<[TrainElement].isNextToFree()");
        return this.nextToFree;
    }

    /**
     * Igazra állítja a változó értékét ha az összes ezt megelőző vagon üres
     */
    public void SetNextToFree() {
        System.out.println(">[TrainElement].setNextToFree()");
        this.nextToFree = true;
        System.out.println("<[TrainElement].setNextToFree()");
    }
}

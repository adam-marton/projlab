package szkeleton;

/**
 *
 */
public class Station extends Tile {

    /**
     * a Station színét tároljuk
     */
    private Color color;

    /**
     * Az állomás konstruktokiírja, hogy Station és a színét
     *
     * @param color Az állomás színe
     */
    public Station(Color color) {
        this.color = color;
        System.out.println("Station, szín:" + color);
    }

    /**
     * visszaadja a Station színét, ami tökéletes az éppen az állomás előtt
     * elhaladó kocsik színének az ellenőrzéséhez
     *
     * @return
     */
    public Color getColor() {
        System.out.println(">[Station].getColor()");
        System.out.println("<[Station].getColor()");
        return color;
    }

    /**
     * a rajta álló mozdony mozgatásáért felelős függvényt hívja meg, ha az még
     * nem mozgott ebben a ciklusban megnézi, hogy a rajta lévő vonat előző
     * pozíciója melyik mezővel egyezik meg és a másik irányba küldi tovább ha
     * egyik irányba se tud a vonat továbbmenni -> ütközés
     *
     * @throws szkeleton.CrashException
     */
    @Override
    public void move() throws CrashException {
    	System.out.println(">[Station].move()");
    	if (getDirB()!=null){
        	if (train.getPrevPos() == getDirA() && getDirB().isFree()) {
        		train.moveTrain(getDirB());
        	}
        } else if (getDirA()!=null){
        	if (train.getPrevPos() == getDirB() && getDirA().isFree()) {
            train.moveTrain(getDirA());
        	} 
        } else {
            throw new CrashException("Ütközés");
        }
    	System.out.println("<[Station].move()");
    }

    /**
     * beállítja a paraméterül kapott TrainElement-et a Tile TraimElement-jeként
     * és megnézi, hogy megegyezik-e a színe a Station színével és ha igen,
     * akkor meghívja a TrainElement getOff() függvényét
     */
    @Override
    public void setElement(TrainElement e) {
    	System.out.println(">[Station].setElement()");
        trainElement = e;
        Color c = trainElement.getColor();
        color = getColor();
        if (c == color) {
            trainElement.getOff();
        }
    	System.out.println("<[Station].setElement()");
    }
}

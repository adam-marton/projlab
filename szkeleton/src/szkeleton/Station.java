package szkeleton;

/**
 * 
 */
public class Station extends Tile {

    /**
     * Default constructor
     * kiírja, hogy Station
     */
    public Station() {
    	System.out.println("Station");
    }
    
    /**
     * kiírja, hogy Station és a színét
     * @param color
     */
    public Station(Color color) {
    	System.out.println("Station, szín:" + color);
    }

    /**
     * a Station színét tároljuk
     */
    private Color color;

    /**
     * visszaadja a Station színét, ami tökéletes az éppen az állomás előtt
     * elhaladó kocsik színének az ellenőrzéséhez
     */
    public Color getColor() {
        return color;
    }

    /**
     * a rajta álló mozdony mozgatásáért felelős függvényt hívja meg,
     * ha az még nem mozgott ebben a ciklusban
     * megnézi, hogy a rajta lévő vonat előző pozíciója melyik mezővel
     * egyezik meg és a másik irányba küldi tovább
     * ha egyik irányba se tud a vonat továbbmenni -> ütközés
     */
    @Override
    public void move() {
    	if(train.getPrevPos() == getDirA() && getDirB().isFree())
    		train.moveTrain(getDirB());
    	else if(train.getPrevPos() == getDirB() && getDirA().isFree())
    		train.moveTrain(getDirA());
    	else
    	{
    		//throw new Exception("Ütközés történt");
    	}
    	trainElement.getOff();
    }

    /**
     * beállítja a paraméterül kapott TrainElement-et a Tile TraimElement-jeként
     * és megnézi, hogy megegyezik-e a színe a Station színével
     * és ha igen, akkor meghívja a TrainElement getOff() függvényét
     */
    @Override
    public void setElement(TrainElement e) {
        trainElement = e;
        Color c = trainElement.getColor();
        color = getColor();
        if(c == color)
        {
        	trainElement.getOff();
        }
    }
}
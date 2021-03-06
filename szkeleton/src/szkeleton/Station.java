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
        //System.out.println("Station, szín:" + color);
    }

    public void printTile(int i, int j){
    	if(train != null){
    		System.out.println("Train "+i+"-"+j);
    	}
    	else if(trainElement != null){
    		System.out.println("TrainElement "+i+"-"+j+" "+trainElement.getColor()+","+trainElement.isEmpty()+" ");
    	}
    }
    
    /**
     * visszaadja a Station színét, ami tökéletes az éppen az állomás előtt
     * elhaladó kocsik színének az ellenőrzéséhez
     *
     * @return
     */
    public Color getColor() {
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
    	if(train != null){
    		if (train.getPrevPos() == getDirA() && getDirB()!=null && getDirB().isFree()) {
         	train.moveTrain(getDirB());
         }
         else if (train.getPrevPos() == getDirB() && getDirA()!=null && getDirA().isFree()) {
             train.moveTrain(getDirA());
         }
         else
             throw new CrashException("Ütközés történt, vesztettél!");
    	}
    }

    /**
     * beállítja a paraméterül kapott TrainElement-et a Tile TraimElement-jeként
     * és megnézi, hogy megegyezik-e a színe a Station színével és ha igen,
     * akkor meghívja a TrainElement getOff() függvényét
     */
    @Override
    public void setElement(TrainElement e) {
        if(e != null){
	    	trainElement = e;
	        Color c = trainElement.getColor();
	        color = getColor();
	        if (c == color) {
	            trainElement.getOff();
	        }
        }
        else{
        	trainElement = null;
        }
    }
}

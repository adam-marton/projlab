package projlab;

/**
 *
 */
public class Station extends Tile {

    /**
     * a Station szĂ­nĂ©t tĂˇroljuk
     */
    private Color color;

    /**
     * Az ĂˇllomĂˇs konstruktokiĂ­rja, hogy Station Ă©s a szĂ­nĂ©t
     *
     * @param color Az ĂˇllomĂˇs szĂ­ne
     */
    public Station(Color color) {
        this.color = color;
        //System.out.println("Station, szĂ­n:" + color);
    }

    public void printTile(int i, int j){
    	Main.drawer.addTrainToList(null, i, j);
		if(train != null){
			Main.drawer.addTrainToList("locomotive", i, j);
			
		}
		if (trainElement != null){
			switch(trainElement.getColor()){
    			case BLUE : Main.drawer.addTrainToList("blueTrain", i, j); break;
    			case GREEN : Main.drawer.addTrainToList("greenTrain", i, j); break;
    			case RED: Main.drawer.addTrainToList("redTrain", i, j); break;
    			case BLACK : Main.drawer.addTrainToList("coalCarrier", i, j); break;
    			case PURPLE: Main.drawer.addTrainToList("purpleTrain", i, j); break;
    			case YELLOW: Main.drawer.addTrainToList("yellowTrain", i, j); break;
    			default: break;
			}
			if(trainElement.isEmpty() && !trainElement.getColor().equals(Color.BLACK)) 
				Main.drawer.addTrainToList("emptyTrain", i, j);
		}
    }
    
    /**
     * visszaadja a Station szĂ­nĂ©t, ami tĂ¶kĂ©letes az Ă©ppen az ĂˇllomĂˇs elĹ‘tt
     * elhaladĂł kocsik szĂ­nĂ©nek az ellenĹ‘rzĂ©sĂ©hez
     *
     * @return
     */
    public Color getColor() {
        return color;
    }

    /**
     * a rajta ĂˇllĂł mozdony mozgatĂˇsĂˇĂ©rt felelĹ‘s fĂĽggvĂ©nyt hĂ­vja meg, ha az mĂ©g
     * nem mozgott ebben a ciklusban megnĂ©zi, hogy a rajta lĂ©vĹ‘ vonat elĹ‘zĹ‘
     * pozĂ­ciĂłja melyik mezĹ‘vel egyezik meg Ă©s a mĂˇsik irĂˇnyba kĂĽldi tovĂˇbb ha
     * egyik irĂˇnyba se tud a vonat tovĂˇbbmenni -> ĂĽtkĂ¶zĂ©s
     *
     * @throws projlab.CrashException
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
             throw new CrashException("Utkozes tortent, vesztettel!");
    	}
    }

    /**
     * beĂˇllĂ­tja a paramĂ©terĂĽl kapott TrainElement-et a Tile TraimElement-jekĂ©nt
     * Ă©s megnĂ©zi, hogy megegyezik-e a szĂ­ne a Station szĂ­nĂ©vel Ă©s ha igen,
     * akkor meghĂ­vja a TrainElement getOff() fĂĽggvĂ©nyĂ©t
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

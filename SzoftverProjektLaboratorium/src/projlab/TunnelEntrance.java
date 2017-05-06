package projlab;

/**
 *
 */
public class TunnelEntrance extends Tile {

    /**
     * Az alagĂştbejĂˇrat ĂˇllapotĂˇt tĂˇrolja. (Nyitva/ZĂˇrva)
     */
    private boolean state;

    /**
     * Az alagĂşt bejĂˇrat konstruktora, alap esetben zĂˇrva van az alagĂşt.
     */
    public TunnelEntrance() {
        this.state = false;
    }
    /**
     * Visszaadja az alagĂştbejĂˇrat ĂˇllapotĂˇt. True, ha nyitva van, false, ha zĂˇrva.
     * @return
     */
    public boolean isActive() {
        /*System.out.println("Az alagĂşt nyitva van?");
        Scanner s = new Scanner(System.in);
        String input = s.nextLine();
        if ("I".equals(input.toUpperCase())) {
            state = true;
        } else if ("N".equals(input.toUpperCase())) {
            state = false;
        } else {
            throw new IllegalArgumentException("Invalid input!");
        }*/
        return state;
    }

    /**
     * Nyitja, csukja az alagutat, ha a feltĂ©telek teljesĂĽlnek
     */
    @Override
    public void changeState() {
        if(!state) {
            if(TunnelEntranceCounter.getInstance().getCounter() < 2) {
                state = true;
                TunnelEntranceCounter.getInstance().addCounter(TunnelEntranceCounter.getInstance().getCounter()+1);
            }
        } else {
            state = false;
            TunnelEntranceCounter.getInstance().addCounter(TunnelEntranceCounter.getInstance().getCounter()-1);
        }
    }

    /**
     * MegvizsgĂˇlja, hogy nyitva van-e az alagĂşt bejĂˇrata. Ha nyitva van,
     * mozgatja a vonatot, ha zĂˇrva, exceptiont dob.
     * @throws projlab.CrashException
     */
    @Override
    public void move() throws CrashException {
    	if (!this.isActive() && train != null) {
            throw new CrashException("Alagutbejarat zarva, utkozes tortent, vesztettel!");
        }
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
}

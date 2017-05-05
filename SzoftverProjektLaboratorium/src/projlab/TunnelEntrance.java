package projlab;

/**
 *
 */
public class TunnelEntrance extends Tile {

    /**
     * Az alagútbejárat állapotát tárolja. (Nyitva/Zárva)
     */
    private boolean state;

    /**
     * Az alagút bejárat konstruktora, alap esetben zárva van az alagút.
     */
    public TunnelEntrance() {
        this.state = false;
    }
    /**
     * Visszaadja az alagútbejárat állapotát. True, ha nyitva van, false, ha zárva.
     * @return
     */
    public boolean isActive() {
        /*System.out.println("Az alagút nyitva van?");
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
     * Nyitja, csukja az alagutat, ha a feltételek teljesülnek
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
     * Megvizsgálja, hogy nyitva van-e az alagút bejárata. Ha nyitva van,
     * mozgatja a vonatot, ha zárva, exceptiont dob.
     * @throws projlab.CrashException
     */
    @Override
    public void move() throws CrashException {
    	if (!this.isActive() && train != null) {
            throw new CrashException("Zárva van a bejárat, vesztettél!");
        }
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
}

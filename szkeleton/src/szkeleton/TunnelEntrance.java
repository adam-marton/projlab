package szkeleton;

import java.util.Scanner;

/**
 * 
 */
public class TunnelEntrance extends Tile {

    /**
     * Az alagútbejárat állapotát tárolja. (Nyitva/Zárva)
     */
    private boolean state;

    /**
     * Default constructor
     */
    public TunnelEntrance() {
    }

    /**
     * Visszaadja az alagútbejárat állapotát.
     * True, ha nyitva van, false, ha zárva.
     * Jelen esetben bekérjük a tesztelőtől az állapotát.
     * @return
     */
    public boolean isActive() {
        System.out.println("Az alagút nyitva van?");
        Scanner s = new Scanner(System.in);
        String input = s.nextLine();
        if("I".equals(input)) {
            state = true;
        } else if("N".equals(input)) {
            state = false;
        } else {
            throw new IllegalArgumentException("Invalid input!");
        }
        return state;
    }

    /**
     * A state field értékét változtatja.
     */
    @Override
    public void changeState() {
        state = !state;
    }

    /**
     * Megvizsgálja, hogy nyitva van-e az alagút bejárata.
     * Ha nyitva van, mozgatja a vonatot, ha zárva, exceptiont dob.
     * @throws szkeleton.CrashException
     */
    @Override
    public void move() throws CrashException {
        if(!isActive()) {
            throw new CrashException("TunnelEntrance is closed!");
        }
        this.train.moveTrain(getDirB());
    }
}
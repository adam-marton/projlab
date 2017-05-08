package projlab;

/**
 * Az alagút bejáratokat megvalósító osztály
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
	 * Visszaadja az alagútbejárat állapotát. True, ha nyitva van, false, ha
	 * zárva.
	 * 
	 * @return
	 */
	public boolean isActive() {
		return state;
	}

	/**
	 * Nyitja, csukja az alagutat, ha a feltételek teljesülnek
	 */
	@Override
	public void changeState() {
		if (!state) {
			if (TunnelEntranceCounter.getInstance().getCounter() < 2) {
				state = true;
				TunnelEntranceCounter.getInstance().drawPlease();
				TunnelEntranceCounter.getInstance().incCounter();
			} else
				TunnelEntranceCounter.getInstance().dontDrawPlease();
		} else {
			state = false;
			TunnelEntranceCounter.getInstance().decCounter();
			TunnelEntranceCounter.getInstance().drawPlease();
		}
	}

	/**
	 * Megvizsgálja, hogy nyitva van-e az alagút bejárata. Ha nyitva van,
	 * mozgatja a vonatot, ha zárva, exceptiont dob.
	 * 
	 * @throws projlab.CrashException
	 */
	@Override
	public void move() throws CrashException {
		if (!this.isActive() && train != null) {
			throw new CrashException("Alagutbejarat zarva, utkozes tortent, vesztettel!");
		}
		if (train != null) {
			if (train.getPrevPos() == getDirA() && getDirB() != null && getDirB().isFree()) {
				train.moveTrain(getDirB());
			} else if (train.getPrevPos() == getDirB() && getDirA() != null && getDirA().isFree()) {
				train.moveTrain(getDirA());
			} else
				throw new CrashException("Utkozes tortent, vesztettel!");

		}
	}
}

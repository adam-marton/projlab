package szkeleton;

/**
 *
 */
public class Level {

    /**
     * Konstruktor létrehozza a mezőket
     */
    public Level() {
    	 tiles= new Tile[20][20];
        // TODO ez egy külső forrásból fog történni jelenleg nincs implementálva
    }

    /**
     * A mezőket tároló tömb
     */
    private final Tile[][] tiles;

    /**
     * A szint száma
     */
    private int id;

    /**
     * @return visszadja a szint számát
     */
    public int getLevelId() {
        System.out.println(">[Level].getLevelId()");
        System.out.println("<[Level].getLevelId()");
        return id;
    }

    /**
     * Beállítja a kapcsolatokat a mezők között
     */
    public void setReferences() {
        System.out.println(">[Level].setReferences()");
        // TODO ez egy külső forrásból fog történni jelenleg nincs implementálva
        System.out.println("<[Level].setReferences()");
    }

    /**
     * Megvizsgálja, hogy volt-e ütközés Mozgathatóvá teszi a mezőket
     *
     * @throws CrashException
     */
    public void preMove() throws CrashException {
        System.out.println(">[Level].preMove()");
        for (int i = 0; i < 20; i++) {
            for (int j = 0; j < 20; j++) {
                if (tiles[i][j].checkCrash()) {
                    throw new CrashException("Ütközés történt!");
                }
                tiles[i][j].enableMove();
            }
        }
        System.out.println("<[Level].preMove()");
    }

    /**
     * Mozgatja a mezőkön található mozdonyokat Ha hibát detektál akkor azt
     * tovább dobja
     *
     * @throws szkeleton.CrashException
     */
    public void moveAll() throws CrashException {
        System.out.println(">[Level].moveAll()");
        for (int i = 0; i < 20; i++) {
            for (int j = 0; j < 20; j++) {
                if (tiles[i][j].isMovable())
            	tiles[i][j].move();
            }
        }
        System.out.println("<[Level].moveAll()");
    }

    /**
     * @param x koordináta
     * @param y koordináta A grafikus interfészen egy mezőre kattintva meghívja
     * annak change state függvényét
     */
    public void changeState(int x, int y) {
        System.out.println(">[Level].changeState()");
        tiles[x][y].changeState();
        System.out.println("<[Level].changeState()");
    }
}

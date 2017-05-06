package projlab;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * 
 */
public class PlottingBoard {

	/**
	 * A vonatokat tĂˇrolĂł lista.
	 */
	private List<Train> trains = new ArrayList<Train>();

	/**
	 * Az aktuĂˇlis szintet tĂˇrolĂł field.
	 */
	private Level currentLevel;
	
	/**
	 * Az Ă¶sszes soron kĂ¶vetkezĹ‘ vonat adatait tĂˇrolja
	 */	
	private List<ArrayList<Color>> elementColors = new ArrayList<ArrayList<Color>>();
	
	/**
	 * A lĂ©trejĂ¶vĹ‘ vonatokhoz tartozĂł idĹ‘zĂ­tĂ©st tĂˇrolja
	 */
	private List<Integer> times = new ArrayList<Integer>();
	
	private int clock = 0;
	
	/**
	 * AlapĂ©rtelmezett konstruktor
	 * @throws FileNotFoundException 
	 */
	public PlottingBoard(int n) throws FileNotFoundException {
		startGame(n);
	}

	/**
	 * TĂ¶rli a tĂˇrolt vonatokat
	 */
	
	public boolean isWon(){
		for (int i=0; i <trains.size();i++){
			if(!trains.get(i).isTheTrainEmpty()) return false;
		}
		return true;
	}
	public void deleteTrains() {
		this.trains = new ArrayList<Train>();
		this.elementColors =  new ArrayList<ArrayList<Color>>();
		this.times = new ArrayList<Integer>();
	}

	/**
	 * Vissza adja jelenleg beĂˇllĂ­tott pĂˇlyĂˇt
	 * 
	 * @return currentLevel
	 */
	public Level getLevel() {
		return this.currentLevel;
	}

	/**
	 * ElindĂ­tja a jĂˇtĂ©kot, Ă¶sszekĂ¶ti a mezĹ‘ket
	 * @throws FileNotFoundException 
	 */
	
	public void startGame(int n) throws FileNotFoundException {
		deleteTrains();
		clock = 0;
		currentLevel = new Level(n);
		Scanner scan = new Scanner(new File("data/maps/train" + n + ".txt"));
	   	while(scan.hasNextLine())
	   	{	   		
	   		String[] splitLine= scan.nextLine().split("><");
	   		splitLine[0] = splitLine[0].replace("<", "");
	   		times.add(Integer.parseInt(splitLine[0]));
	   		String[] coords = splitLine[1].split("-");
	   		int coordX = Integer.parseInt(coords[0]);
			int coordY = Integer.parseInt(coords[1]);
			//Tile startingPos = getLevel().getTile(coordX, coordY);
			String[] colors = splitLine[2].split("-");
			colors[colors.length-1] = colors[colors.length-1].replace(">", "");
			ArrayList<Color> elementColorsTemp = new ArrayList<Color>();
			for(int i = 0; i < colors.length; i++)
			{
				elementColorsTemp.add(Color.valueOf(colors[i]));
			}
			elementColors.add(elementColorsTemp);
		}	
	   	scan.close();
                Main.pause = false;
	}

	/**
	 * LezĂˇrja a jĂˇtĂ©kot Ă©s kilĂ©p ha exit lett Ăˇtadva
	 * 
	 * @param s
	 * @throws FileNotFoundException 
	 */
	public void endGame(String s) throws FileNotFoundException {
            JFrame frame = new JFrame("Jatek vege!");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setResizable(false);
            frame.setLocationRelativeTo(null);
            frame.setSize(300, 150);
            frame.setMinimumSize(frame.getSize());
            frame.setLayout(new BorderLayout());
            JPanel footPanel = new JPanel(new FlowLayout());
            frame.add(footPanel, BorderLayout.PAGE_END);
            frame.add(new JLabel(s), BorderLayout.PAGE_START);
            JButton restart = new JButton("Ujrakezdes");
            JButton nextLevel = new JButton("Kovetkezo‘");
            JButton exit = new JButton("Kilepes");
            if ("Utkozes tortent, vesztettel!".equals(s) || "Alagutbejarat zarva, utkozes tortent, vesztettel!".equals(s)) {
                footPanel.add(restart);
                footPanel.add(exit);
            } else if("Nyertel!".equals(s)) {
                footPanel.add(nextLevel);
                footPanel.add(exit);
            }
            frame.setVisible(true);
            restart.addActionListener(new ActionListener()
	    {
	    	public void actionPerformed(ActionEvent e)
	    	{
                        try {
                            frame.dispose();
                            System.out.println(currentLevel.getLevelId());
                            startGame(currentLevel.getLevelId());
                        } catch (FileNotFoundException ex) {
                            Logger.getLogger(PlottingBoard.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
                        }
	    	}
	    });
	    
            nextLevel.addActionListener(new ActionListener()
	    {
	        public void actionPerformed(ActionEvent e)
	        {
                    try {
                        frame.dispose();
                        startGame(currentLevel.getLevelId()+1);
                    } catch (FileNotFoundException ex) {
                        Logger.getLogger(PlottingBoard.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
                    }
	        }
	    });
            
	    exit.addActionListener(new ActionListener()
	    {
	        public void actionPerformed(ActionEvent e)
	        {
                    System.exit(0);
	        }
	    });
            Main.pause = true;
	}

	/**
	 * A Clock Ăˇltal megadott idĹ‘nkĂ©nt hĂ­vott metĂłdus ami a jĂˇtĂ©kot eggyel
	 * "lĂ©pteti"
	 * @throws FileNotFoundException 
	 */
	public void run() throws FileNotFoundException {
		try {
			if(!elementColors.isEmpty()){
				
				if(clock == times.get(0))
				{
					System.out.println(clock +"  " +times.get(0));
						trains.add(new Train(getLevel().getTile(0, 1), elementColors.get(0)));
						elementColors.remove(0);
					times.remove(0);
				}
			}
			clock++;
			currentLevel.preMove();
			currentLevel.moveAll();
		} catch (CrashException ex) {
                        endGame(ex.getMessage());
                }
	}

	/**
	 * LĂ©trehoz egy Ăşj vonatot Ă©s hozzĂˇadja a tĂˇrolĂłhoz
	 * 
	 * @param startingPos
	 *            a mozdony kezdĹ‘pozĂ­ciĂłja
	 * @param colors
	 *            a vagonok szĂ­neit tĂˇrolja
	 */
	/*public void addTrain(Train t) {
		Tile startingPos = getLevel().getTile(x, y);        
		trains.add(new Train(startingPos, colors));
	}*/
}

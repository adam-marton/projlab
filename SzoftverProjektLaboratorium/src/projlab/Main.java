package projlab;

import java.awt.event.MouseListener;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import javax.swing.JFrame;

public class Main {

	public static Draw drawer = new Draw();
	public static PlottingBoard pb;
	private static JFrame gameFrame;
	private static MouseListener ch = new ClickHandling();
	public static boolean pause = false;
	public static int speed = 500;
	
	
	public static void main(String[] args) throws IOException {

	
		pb = new PlottingBoard(1);
		
		gameFrame = new JFrame("musketasok");
		gameFrame.setResizable(false);
		gameFrame.setSize(1200, 600);
		gameFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		gameFrame.setMinimumSize(gameFrame.getSize());
		gameFrame.setVisible(true);
		

		
		gameFrame.add(drawer);
		gameFrame.addMouseListener(ch);
		gameFrame.pack();
		
		while(true){
			while(!pause){
				try {
					TimeUnit.MILLISECONDS.sleep(speed);
					pb.run();
					pb.getLevel().printAll();
					drawer.drawing();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			try {
				TimeUnit.MILLISECONDS.sleep(100);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}

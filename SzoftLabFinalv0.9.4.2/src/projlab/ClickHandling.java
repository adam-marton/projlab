package projlab;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JPanel;

/**
 * A kattintást lekezelő osztály megvalósítása
 *
 */
public class ClickHandling extends JPanel implements MouseListener {

	private static final long serialVersionUID = 1L;

	/**
	 * ClickEvent
	 *
	 */
	@Override
	public void mouseClicked(MouseEvent e) {
		int x = (e.getX() - 3) / 30;
		int y = (e.getY() - 25) / 30;
		if (x == 38 && y == 0 && !Main.pause) {
			Main.pause = true;
			Main.drawer.addBackgroundToList("paused", x, y);
		} else if (x == 38 && y == 0 && Main.pause) {
			Main.pause = false;
			Main.drawer.addBackgroundToList("pause", x, y);
		} else if (x == 37 && y == 0 && Main.speed > 100) {
			Main.speed -= 100;
		} else if (x == 36 && y == 0 && Main.speed < 1000) {
			Main.speed += 100;
		}

		System.out.print("<" + x + "-" + y + ">");
		if (Main.pb.getLevel().getTile(x, y) != null) {
			Main.pb.getLevel().getTile(x, y).changeState();
			Main.drawer.changeState(x, y);
			Main.drawer.drawing();
		}

	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub

	}

}

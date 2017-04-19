package szkeleton;

import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) throws FileNotFoundException {

	
		Scanner m = new Scanner(System.in);
		String input;
		PlottingBoard pb = new PlottingBoard(1);
		
		while(m.hasNextLine()){
				String nextLine = m.nextLine();
				String[] splitLine = nextLine.split(" ");
				if(splitLine[0].equals("loadMapAndtrains")){
					pb = new PlottingBoard(Integer.parseInt(splitLine[1]));
				}
				if(splitLine[0].equals("move")){
					pb.run();
				}
				if(splitLine[0].equals("listObjects")){
					pb.getLevel().printAll();
				}
				if(splitLine[0].equals("changeSwitch") || splitLine[0].equals("changeTunnelEntrance")) {
					pb.getLevel().changeState(Integer.parseInt(splitLine[1]), Integer.parseInt(splitLine[2]));
				}
			}
		System.exit(0);
	}
}

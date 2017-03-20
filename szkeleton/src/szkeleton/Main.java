package szkeleton;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Scanner;
import java.util.logging.Logger;

public class Main {

	public static void main(String[] args) {

		Scanner m = new Scanner(System.in);
		String input;
		System.out.println("1: Játék indítása");
		System.out.println("2: Vonat hozzáadása");
		System.out.println("3: Váltó állítása");
		System.out.println("4: Alagút építése/bezárása");
		System.out.println("5: Utasok leszállítása");
		System.out.println("6: Vonatok mozgatása");
		System.out.println("7: Kilépés");
		input = m.nextLine();
		int command = Integer.parseInt(input);
		switch (command) {
		case 1:
			test1();
			break;
		case 2:
			test2();
			break;
		case 3:
			test3();
			break;
		case 4:
			test4();
			break;
		case 5:
			test5();
			break;
		case 6:
			test6();
			break;
		case 7:
			test7();
			break;
		default:
			System.out.println("Invalid input!");
		}
		System.out.println("Terminate");
	}

	public static void test1() {
		PlottingBoard pb = new PlottingBoard();
		pb.startGame();
	}

	public static void test2() {
		PlottingBoard pb = new PlottingBoard();
		pb.startGame();
		Tile tile = new Rail(true);
		System.out.println("2.1 Hány vagonból áll a vonat?");
		Scanner s = new Scanner(System.in);
		Integer input = 0;
		try {
			input = s.nextInt();
		} catch (NoSuchElementException e) {
			Logger.getLogger(PlottingBoard.class.getName()).severe(e.toString());
		}
		List<Color> colors = new ArrayList<Color>();
		for (int i = 0; i < input; i++) {
			colors.add(Color.RED);
		}
		pb.addTrain(tile, colors);
	}

	public static void test3() {
		Tile bDir = new Rail(true);
		Tile cDir = new Rail(true);
		Switch sw;
		Integer input = 0;
		try {
			System.out.println("3.1 Merre áll a váltó? (1/2)");
			Scanner s = new Scanner(System.in);
			input = s.nextInt();
		} catch (NoSuchElementException e) {
			Logger.getLogger(Main.class.getName()).severe(e.toString());
		}
		if (1 == input) {
			sw = new Switch(bDir);
		} else if (2 == input) {
			sw = new Switch(cDir);
		} else {
			throw new IllegalArgumentException("Invalid input!");
		}
		sw.setDirB(bDir);
		sw.setDirC(cDir);
		if (sw.getActiveDir() == sw.getDirB())
			System.out.println("A váltónak a váltás előtt az első kijárata aktív");
		else if (sw.getActiveDir() == sw.getDirC())
			System.out.println("A váltónak a váltás előtt a második kijárata aktív");
		sw.changeState();
		if (sw.getActiveDir() == sw.getDirB())
			System.out.println("A váltónak a váltás után az első kijárata aktív");
		else if (sw.getActiveDir() == sw.getDirC())
			System.out.println("A váltónak a váltás után a második kijárata aktív");

	}

	public static void test4() {
		TunnelEntrance te = new TunnelEntrance();
		System.out.println("4.1 Az alagút nyitva van? (I/N)");
		Scanner s1 = new Scanner(System.in);
		String input1 = s1.nextLine();
		Integer input2 = 0;
		try {
			System.out.println("4.2 Jelenleg hány alagút bejárat aktív? (0/1/2)");
			Scanner s2 = new Scanner(System.in);
			input2 = s2.nextInt();
		} catch (NoSuchElementException e) {
			Logger.getLogger(Main.class.getName()).severe(e.toString());
		}
		if ("I".equals(input1.toUpperCase())) {
			te.changeState();
			TunnelEntranceCounter.getInstance().addCounter(input2 - 1);
		} else if ("N".equals(input1.toUpperCase())) {
			TunnelEntranceCounter.getInstance().addCounter(input2);
		}
		te.changeState();
	}

	public static void test5() {
		System.out.println("5.1 A vagon állomáson van? (I/N)");
		Scanner s1 = new Scanner(System.in);
		String input1 = s1.nextLine();
		if ("N".equals(input1.toUpperCase())) // 5.1
		{
			TrainElement vagon = new TrainElement(null, Color.RED);
		} else if ("I".equals(input1.toUpperCase())) {
			Tile tile = new Station(Color.RED);
			System.out.println("5.2 A vagon színe megegyezik az állomás Szinével? (I/N)");
			Scanner s2 = new Scanner(System.in);
			String input2 = s2.nextLine();
			if ("N".equals(input2.toUpperCase())) // 5.2
			{
				TrainElement vagon = new TrainElement(null, Color.BLUE);
				vagon.move(tile);
				System.out.println("A vonat áthaladt");
			} else if ("I".equals(input2.toUpperCase())) {
				TrainElement vagon = new TrainElement(null, Color.RED);
				System.out.println("5.3 A vagon előtt van másik vagon, amin vannak utasok? (I/N)");
				Scanner s3 = new Scanner(System.in);
				String input3 = s3.nextLine();
				if ("N".equals(input3.toUpperCase())) // 5.3
				{
					vagon.SetNextToFree();
					System.out.println("5.4 A vagonon vannak utasok? (I/N)");
					Scanner s4 = new Scanner(System.in);
					String input4 = s4.nextLine();
					if ("N".equals(input4.toUpperCase())) // 5.4
					{
						vagon.setEmpty();
						vagon.move(tile);
						System.out.println("Nincsenek utasok");

					} else if ("I".equals(input4.toUpperCase())) {
						vagon.move(tile);
						System.out.println("Az utasok leszálltak");
					} else {
						throw new IllegalArgumentException("Invalid input!");
					}

				} else if ("I".equals(input3.toUpperCase())) {
					vagon.move(tile);
					System.out.println("Az utasok nem szálltak le");
				} else {
					throw new IllegalArgumentException("Invalid input!");
				}
			} else {
				throw new IllegalArgumentException("Invalid input!");
			}
		} else {
			throw new IllegalArgumentException("Invalid input!");
		}
	}

	public static void test6() {
		
		PlottingBoard pb = new PlottingBoard();
		pb.startGame();
		Tile tileA = new Rail(true);
		Tile tileB = new Rail(true);
		Tile alagut;
		Tile valto;
		Train alma;
		Train almab;
		List<Color> colors = new ArrayList<Color>();
		for (int i = 0; i < 2; i++) {
			colors.add(Color.RED);
		}
		System.out.println("Áthalad váltón a vonat? (I/N)");
		Scanner s1 = new Scanner(System.in);
		String input1 = s1.nextLine();
		if ("I".equals(input1.toUpperCase())) {
			Integer input2 = 0;
			try {
				System.out.println("6.1.1 Merre áll a váltó? (1/2)");
				Scanner s2 = new Scanner(System.in);
				input2 = s2.nextInt();
			} catch (NoSuchElementException e) {
				Logger.getLogger(Main.class.getName()).severe(e.toString());
			}
			if (1 == input2) {
				valto = new Switch(tileA);
			} else if (2 == input2) {
				valto = new Switch(tileB);
			} else {
				throw new IllegalArgumentException("Invalid input!");
			}
			alma = new Train(valto, colors);
			try {
				valto.move();
				if (alma.getPrevPos() == valto)
					System.out.println("A vonat lépett");
			} catch (CrashException ex) {
				pb.endGame(ex.getMessage());
			}
		} else if ("N".equals(input1.toUpperCase())) {
			alma = new Train(tileA, colors);
			tileA.setDirB(tileB);
			System.out.println("6.2 Van a vonat előtt valamilyen akadály? (I/N)");
			Scanner s4 = new Scanner(System.in);
			String input4 = s4.nextLine();
			if ("N".equals(input4.toUpperCase())) {
				try {
					tileA.move();
					if (alma.getPrevPos() == tileA)
						System.out.println("A vonat lépett");
				} catch (CrashException ex) {
					pb.endGame(ex.getMessage());
				}
			} else if ("I".equals(input4.toUpperCase())) {
				try {
					almab = new Train(tileB, colors);
					tileA.move();
					if (alma.getPrevPos() == tileA)
						System.out.println("A vonat lépett");
				} catch (CrashException ex) {
					pb.endGame(ex.getMessage());
				}
			} else {
				throw new IllegalArgumentException("Invalid input!");
			}
		} else {
			throw new IllegalArgumentException("Invalid input!");
		}
		System.out.println("6.3 A vonat bement alagút bejáraton? (I/N)");
		Scanner s5 = new Scanner(System.in);
		String input5 = s5.nextLine();
		if ("N".equals(input5.toUpperCase())) {
		} else if ("I".equals(input5.toUpperCase())) {
			
			alagut=new TunnelEntrance();
			alagut.setDirB(tileA);
			alma = new Train(alagut, colors);
			try {
				alagut.move();
				if (alma.getPrevPos() == alagut)
					System.out.println("A vonat belépett az alagútba");
			} catch (CrashException ex) {
				pb.endGame(ex.getMessage());
			}
			
		} else {

		}
	}

	public static void test7() {
		PlottingBoard pb = new PlottingBoard();
		System.out.println("7.1 Nyerés, vesztés, kilépés? (N/V/K)");
		Scanner s = new Scanner(System.in);
		String input = s.nextLine();
		if ("N".equals(input.toUpperCase())) {
			pb.endGame("Win");
		} else if ("V".equals(input.toUpperCase())) {
			pb.endGame("Lose");
		} else if ("K".equals(input.toUpperCase())) {
			pb.endGame("Exit");
		} else {
			throw new IllegalArgumentException("Invalid input!");
		}
	}
}

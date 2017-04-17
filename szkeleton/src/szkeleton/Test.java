package szkeleton;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;

public class Test {
	
	PlottingBoard boardUnderTest;
	OutputStreamWriter writer;
	
	Test(FileOutputStream fos) {
		this.boardUnderTest = new PlottingBoard();
		this.writer = new OutputStreamWriter(fos);
	}
	
	public void loadMap(List<String> param) {
		
	}
	
	public void addTrain(List<String> param) {
		if(param.size() != 2) {
			throw new IllegalArgumentException("Input Language syntax error");
		}
		try {
			int X = Integer.parseInt(param.get(0));
			int Y = Integer.parseInt(param.get(1));
			List<Color> colors = new ArrayList<>();
			for (int i = 2; i < param.size(); i++) {
				String color = param.get(i);
				switch (color) {
				case "blue":
					colors.add(Color.BLUE);
					break;
				case "purple":
					colors.add(Color.PURPLE);
					break;
				case "green":
					colors.add(Color.GREEN);
					break;
				case "yellow":
					colors.add(Color.YELLOW);
					break;
				case "red":
					colors.add(Color.RED);
					break;
				case "black":
					colors.add(Color.BLACK);
					break;
				default:
					break;
				}
			}
			this.boardUnderTest.addTrain(X, Y, colors);
        	
        } catch (NumberFormatException ex) {
        	throw new IllegalArgumentException("Input Language syntax error");
        }
	}
	
	public void setSwitch(List<String> param) {
		Level lv = this.boardUnderTest.getLevel();
		if(param.size() != 2) {
			throw new IllegalArgumentException("Input Language syntax error");
		}
		try {
			int X = Integer.parseInt(param.get(0));
			int Y = Integer.parseInt(param.get(1));
			lv.changeState(X, Y);
        	
        } catch (NumberFormatException ex) {
        	throw new IllegalArgumentException("Input Language syntax error");
        }
	}
	
	public void setTunnelEntrance(List<String> param) {
		Level lv = this.boardUnderTest.getLevel();
		if(param.size() != 2) {
			throw new IllegalArgumentException("Input Language syntax error");
		}
		try {
			int X = Integer.parseInt(param.get(0));
			int Y = Integer.parseInt(param.get(1));
			lv.changeState(X, Y);
        	
        } catch (NumberFormatException ex) {
        	throw new IllegalArgumentException("Input Language syntax error");
        }
	}
	
	public void move(List<String> param) throws IOException {
		if(param.size() != 1) {
			throw new IllegalArgumentException("Input Language syntax error");
		}
		Level lv = this.boardUnderTest.getLevel();
		try {
			int steps = Integer.parseInt(param.get(0));
			for(int i = 0; i < steps; i++) {
				lv.preMove();
				lv.moveAll();
			}
        	
        } catch (CrashException ex) {
            this.writer.write("Crash occured!");
        } catch (NumberFormatException ex) {
        	throw new IllegalArgumentException("Input Language syntax error");
        }
	}
	
	public void getTrains(List<String> param) {
		
	}
	
	public void getSwitches(List<String> param) {
		
	}
	
	public void getTunnelEntrances(List<String> param) {
		
	}
	
	
}

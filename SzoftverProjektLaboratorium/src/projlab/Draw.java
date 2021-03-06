package projlab;

import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.Scanner;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class Draw extends JPanel{

	private static final long serialVersionUID = 1L;
	String[][] backgroundToDraw = new String[40][20];
	String[][] railsToDraw = new String[40][20];
	String[][] trainsToDraw = new String[40][20];
	
	Map<String,Image> pictureLinks = new HashMap<String,Image>();
	
	public Draw(){
		try {
			loadImages();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void drawing(){
		repaint();
	}
	
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		drawBackground(g);
		drawRails(g);
		drawTrains(g);
	}
	
	public void drawBackground(Graphics g){
		if(backgroundToDraw[0][0] == null){
			for(int i = 0; i < 40; i++){
				for(int j = 0; j < 20;j++){
					Random rand = new Random();
					int myRand = rand.nextInt(10);
					if(myRand == 0){
						backgroundToDraw[i][j] = "tree";
					}
					else if(myRand == 1){
						backgroundToDraw[i][j] = "bush";
					}
					else backgroundToDraw[i][j] = "background";
					
				}
			}
			backgroundToDraw[38][0] = "pause";
			backgroundToDraw[37][0] = "speedUp";
			backgroundToDraw[36][0] = "speedDown";
		}
		else{
			for(int i = 0; i < 40; i++){
				for(int j = 0; j < 20;j++){
					if(backgroundToDraw[i][j] != null)
						g.drawImage(pictureLinks.get(backgroundToDraw[i][j]), i*30, j*30, null);
				}
			}
		}
	}
	public void loadImages() throws IOException{
		@SuppressWarnings("resource")
		Scanner m = new Scanner(new File("data/pictures/picLinks.txt"));
		while(m.hasNextLine()){
			String input = m.nextLine();
			String[] KeyV = input.split(";");
			pictureLinks.put(KeyV[0], ImageIO.read(new File(KeyV[1])));
		}
	}
	
	public void drawRails(Graphics g){
			for(int i = 0; i < 40; i++){
				for(int j = 0; j < 20;j++){
					if(railsToDraw[i][j] != null)
						g.drawImage(pictureLinks.get(railsToDraw[i][j]), i*30, j*30, null);
						
				}
			}
	}
	
	public void drawTrains(Graphics g){
		for(int i = 0; i < 40; i++){
			for(int j = 0; j < 20;j++){
				if(trainsToDraw[i][j] != null){
					g.drawImage(pictureLinks.get(trainsToDraw[i][j]), i*30, j*30, null);
				}
					
			}
		}
	}
	
	public void changeState(int x, int y){
		if(railsToDraw[x][y] == null) {}
		else if(railsToDraw[x][y].contains("Closed")){
			railsToDraw[x][y] = railsToDraw[x][y].replace("Closed", "Opened");
		}
		else if(railsToDraw[x][y].contains("Opened")){
			railsToDraw[x][y] = railsToDraw[x][y].replace("Opened", "Closed");
		}
		else if(railsToDraw[x][y].contains("DirB")){
			railsToDraw[x][y] = railsToDraw[x][y].replace("DirB", "DirC");
		}
		else if(railsToDraw[x][y].contains("DirC")){
			railsToDraw[x][y] = railsToDraw[x][y].replace("DirC", "DirB");
		}
		
	}
	
	public void addBackgroundToList(String backgroundType, int x, int y){
		backgroundToDraw[x][y] = backgroundType;
	}
	public void addRailToList(String railType, int x, int y){
		railsToDraw[x][y] = railType;
	}
	
	public void addTrainToList(String trainType, int x, int y){
		trainsToDraw[x][y] = trainType;
	}
	
	
}

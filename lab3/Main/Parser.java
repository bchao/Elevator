package Main;

import java.util.ArrayList;
import java.io.FileNotFoundException;
import java.util.Scanner;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

import Buildings.*;
import Riders.Rider;

public class Parser {
	public Parser() {

	}
	
	private static void build(Scanner s){
		String[] initParam = s.nextLine().split(" ");
		ArrayList<Rider> ridersList = new ArrayList<Rider>();
		
		int F = Integer.parseInt(initParam[0]);
		int E = Integer.parseInt(initParam[1]);
		int R = Integer.parseInt(initParam[2]);
		int N = Integer.parseInt(initParam[3]);
		
		Building building = new PartTwoBuilding(F, E, N);
//		Building building = new PartOneBuilding(F, E, N);

		while(s.hasNextLine()) {
			String[] params = s.nextLine().split(" ");
			int riderNumber = Integer.parseInt(params[0]);
			int startingFloor = Integer.parseInt(params[1]);
			int destinationFloor = Integer.parseInt(params[2]);
			
			Rider r = new Rider(riderNumber, building, startingFloor);
			r.setDestination(destinationFloor);
			ridersList.add(r);
		}
		
		for(Rider r : ridersList) {
			r.start();
		}
		
		building.runElevators();
	}
	
	public static void openFile() {
		JFileChooser fileChooser = new JFileChooser(".");
		int fileSelect = JFileChooser.ERROR_OPTION;
		
		while(fileSelect == JFileChooser.ERROR_OPTION) {
			fileSelect = fileChooser.showOpenDialog(null);
			
			if(fileSelect == JFileChooser.APPROVE_OPTION) {
				try {
					build(new Scanner(fileChooser.getSelectedFile()));
				}
				catch (FileNotFoundException e) {
					JOptionPane.showMessageDialog(null, 
							"File not found: " + fileChooser.getSelectedFile().getName(), 
							"IO Error", 
							JOptionPane.ERROR_MESSAGE);
				}
			}
		}
	}
	
	public static void main(String[] args) {
		Parser parser = new Parser();
		parser.openFile();
	}
}

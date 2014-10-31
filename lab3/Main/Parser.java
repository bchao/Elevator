package Main;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.Scanner;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

public class Parser {
	private static int numFloors;
	private static int numElevators;
	private static int numRiders;
	private static int maxCap;
	private static HashMap<Integer, ArrayList<Integer>> riderMap;	
	private static int[] riderStarts;
	public static PrintWriter writer;
	private static String[] riderBehavior;
	
	public Parser() {
		try {
			writer = new PrintWriter("output.txt", "UTF-8");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		openFile();
	}
	
	private static void build(Scanner s){
		String[] initParam = s.nextLine().split(" ");
		
		numFloors = Integer.parseInt(initParam[0]);
		numElevators = Integer.parseInt(initParam[1]);
		numRiders = Integer.parseInt(initParam[2]);
		maxCap = Integer.parseInt(initParam[3]);
		
		riderStarts = new int[numRiders];
		riderBehavior = new String[numRiders];
		riderMap = new HashMap<Integer, ArrayList<Integer>>();
		
		while(s.hasNextLine()) {
			String[] params = s.nextLine().split(" ");
			int riderNumber = Integer.parseInt(params[0]) - 1;
			int startingFloor = Integer.parseInt(params[1]) - 1;
			int destinationFloor = Integer.parseInt(params[2]) - 1;
			String behavior;
			if (params.length > 3) {
				behavior = params[3];
			} else {
				behavior = "WELL_BEHAVED";
			}

			
			int[] riderAttributes = {startingFloor, destinationFloor};
			
			riderStarts[riderNumber] = startingFloor;
			riderBehavior[riderNumber] = behavior;
			if(riderMap.get(riderNumber) == null) {
				ArrayList<Integer> temp = new ArrayList<Integer>();
				temp.add(destinationFloor);
				riderMap.put(riderNumber, temp);
			}
			else {
				riderMap.get(riderNumber).add(destinationFloor);
			}			
		}		
	}
	
	private static void openFile() {
		String currentDir = System.getProperty("user.dir") + "\\elevator\\lab3\\ElevatorTests";
		JFileChooser fileChooser = new JFileChooser(currentDir);
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
	
	public int getFloors() {
		return numFloors;
	}
	
	public int getElevators() {
		return numElevators;
	}
	
	public int getRiders() {
		return numRiders;
	}
	
	public int getCapacity() {
		return maxCap;
	}

	public HashMap<Integer, ArrayList<Integer>> getRiderMap() {
		return riderMap;
	}
	
	public int[] getRiderStarts() {
		return riderStarts;
	}
	public String[] getRiderBehaviors() {
		return riderBehavior;
	}
}

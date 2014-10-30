package Main;

import java.util.ArrayList;
import java.util.HashMap;
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
	private static HashMap<Integer, ArrayList<int[]>> riderMap; 	
	public static PrintWriter writer;
	
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

		riderMap = new HashMap<Integer, ArrayList<int[]>>();
		
		while(s.hasNextLine()) {
			String[] params = s.nextLine().split(" ");
			int riderNumber = Integer.parseInt(params[0]);
			int startingFloor = Integer.parseInt(params[1]);
			int destinationFloor = Integer.parseInt(params[2]);
			
			int[] riderAttributes = {startingFloor, destinationFloor};
			
			if(!riderMap.containsKey(riderNumber)) {
				ArrayList<int[]> newList = new ArrayList<int[]>();
				newList.add(riderAttributes);
				riderMap.put(riderNumber, newList);
			}
			else {
				riderMap.get(riderNumber).add(riderAttributes);
			}
		}		
	}
	
	private static void openFile() {
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
	
	public HashMap<Integer, ArrayList<int[]>> getRiderMap() {
		return riderMap;
	}
}

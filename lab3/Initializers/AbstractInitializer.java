package Initializers;

import Main.Parser;

public abstract class AbstractInitializer {
	
	Parser p;
	public AbstractInitializer() {}
	
	// this method should instantiate the parser with the given file name
	public abstract void beginSimulation(String filename);

}

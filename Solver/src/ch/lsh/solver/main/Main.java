package ch.lsh.solver.main;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
	
	public static void main(String[] args) {
		// Setting up util-class with a 16 char big StringBuilder
		Util.setup(16);

		// Setting up modes for easier handling later
		ModesHandler.setupModes();

		// BufferedReader that can read from the shell
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

		// Printing welcome and star messages
		Util.startPrint();
		Util.mainSB.append("This is a Solver for a mathematical \"problem\", more info here:\n");
		Util.mainSB.append("https://github.com/KREATIV-LSH/Solver\n");
		Util.mainSB.append("\nWhich mode do you want to use?\n");
		ModesHandler.addModesOptionsToBuf();
		Util.endPrint();
	}

}

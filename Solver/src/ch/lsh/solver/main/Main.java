package ch.lsh.solver.main;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import ch.lsh.solver.solver.SolverHandler;

public class Main {

	
	public static void main(String[] args) {
		// Setting up util-class with a 16 char big StringBuilder
		Util.setup(16);

		// Setting up modes for easier handling later
		ModesHandler.setupModes();

		// Setting up SolverHandler
		SolverHandler.setupSolvers();

		// BufferedReader that can read from the shell
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

		// Printing welcome and star messages
		Util.startPrint();
		Util.mainSB.append("This is a Solver for a mathematical \"problem\", more info here:\n");
		Util.mainSB.append("https://github.com/KREATIV-LSH/Solver\n");
		Util.mainSB.append("\nWhich mode do you want to use?\n");
		ModesHandler.addModesOptionsToBuf();
		Util.mainSB.append("\nSelect mode: ");
		Util.endPrint();

		// Reading user input and handling it
		String mode_;
		int  mode = 0;
		try {
			mode_ = bf.readLine();
			if(mode_ != null) {
				while (mode_.length() == 0) {
					Util.startPrint();
					Util.mainSB.append("Select mode: ");
					Util.endPrint();
					mode_ = bf.readLine();
				}
				mode_ = mode_.replace("\n", "").replace("\r", "");
				if(mode_.chars().allMatch(Character::isDigit)) {
					mode = Integer.parseInt(mode_);
				} else {
					System.err.println("Not able to parse input!");
					System.exit(1);
				}
			} else {
				System.err.println("Not able to parse input!");
				System.exit(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.exit(1);
		}
		
		// Passing user input to ModesHandler
		ModesHandler.handelModeSelection(mode);
	}

}

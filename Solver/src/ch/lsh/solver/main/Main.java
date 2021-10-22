package ch.lsh.solver.main;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.util.Arrays;

import ch.lsh.solver.solver.SolverHandler;

public class Main {

	// BufferedReader that can read from the shell
	public static BufferedReader inputBuffReader = new BufferedReader(new InputStreamReader(System.in));

	public static void main(String[] args) {
		// Setting up util-class with a 16 char big StringBuilder
		Util.setup(16);

		// Setting up SolverHandler
		SolverHandler.setupSolvers();

		// Setting up modes for easier handling later
		ModesHandler.setupModes();

		if(args.length == 0 ) {
			handleWelocome();
		} else {
			// Functionality to run program without interface but instead using the arguments
			// This is a feature for advanceder users and is not guaranteed to work always
			// In this mode there is no protection against wrong inputs so they will crash the program and yield an error
			// This functionality will put all args for the program onto the input-reader
			String params = "";
			for (int i = 0; i < args.length; i++) {
				params += args[i] + "\n";
			}
			inputBuffReader = new BufferedReader(new StringReader(params));
			handleWelocome();
		}
	}

	private static void handleWelocome() {
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
			mode_ = inputBuffReader.readLine();
			if(mode_ != null) {
				while (mode_.length() == 0) {
					Util.startPrint();
					Util.mainSB.append("Select mode: ");
					Util.endPrint();
					mode_ = inputBuffReader.readLine();
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

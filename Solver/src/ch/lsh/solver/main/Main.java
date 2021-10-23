package ch.lsh.solver.main;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.util.Arrays;

import ch.lsh.solver.solver.SolverHandler;

public class Main {

	// BufferedReader that can read from the shell
	public static BufferedReader inputBuffReader = new BufferedReader(new InputStreamReader(System.in));


	// LUT = Look Up Table
	// Pre calculated the half's of possible numbers for j (2-201) rounded up
	public static int[] halfLUT_int = { 1, 2, 2, 3, 3, 4, 4, 5, 5, 6, 6, 7, 7, 8,8, 9, 9, 10, 10, 11, 11, 12, 12, 13, 13, 14, 14, 15, 15, 16, 16, 17, 17, 18, 18, 19, 19, 20, 20, 21, 21, 22, 22, 23, 23, 24, 24, 25, 25, 26, 26, 27, 27, 28, 28, 29, 29, 30, 30, 31, 31, 32, 32, 33, 33, 34, 34, 35, 35, 36, 36, 37, 37, 38, 38, 39, 39, 40, 40, 41, 41, 42, 42, 43, 43, 44, 44, 45, 45, 46, 46, 47, 47, 48, 48, 49, 49, 50, 50, 51, 51, 52, 52, 53, 53, 54, 54, 55, 55, 56, 56, 57, 57, 58, 58, 59, 59, 60, 60, 61, 61, 62, 62, 63, 63, 64, 64, 65, 65, 66, 66, 67, 67, 68, 68, 69, 69, 70, 70, 71, 71, 72, 72, 73, 73, 74, 74, 75, 75, 76, 76, 77, 77, 78, 78, 79, 79, 80, 80, 81, 81, 82, 82, 83, 83, 84, 84, 85, 85, 86, 86, 87, 87, 88, 88, 89, 89, 90, 90, 91, 91, 92, 92, 93, 93, 94, 94, 95, 95, 96, 96, 97, 97, 98, 98, 99, 99, 100, 100 };
	public static long[] halfLUT_long;

	public static void main(String[] args) {
		// Initialize halfLUT_long array with values of halfLUT_int array
		halfLUT_long = Arrays.stream(halfLUT_int).asLongStream().toArray();

		// Setting up util-class with a 16 char big StringBuilder
		Util.setup(16);

		// Setting up SolverHandler
		SolverHandler.setupSolvers();

		// Setting up modes for easier handling later
		ModesHandler.setupModes();

		if(args.length == 0 ) {
			handleWelocome();
		} else {
			// Functionality to run program without interface but instead using command line arguments
			// This is a feature for advanceder users and is not guaranteed to work always

			// This feature was specifically designed for the benchmark script and redirecting program output to a file
			// The redirecting of the output will save much time because there is no time spend printing to the console
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

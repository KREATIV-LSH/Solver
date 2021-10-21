package ch.lsh.solver.main;

import java.util.HashMap;
import java.util.stream.Stream;

import ch.lsh.solver.solver.Solver;
import ch.lsh.solver.solver.SolverHandler;

public class ModesHandler {

	// Constructor to satisfy sonarlint S111
	private ModesHandler() {
		throw new IllegalStateException("Utility class");
	}

	// HashMap to store name of mode and description
	private static HashMap<String, String> modes;

	public static void setupModes() {
		// Creating the HashMap for the modes Table
		modes = new HashMap<>();

		// Adding the different modes
		for(Solver solver : SolverHandler.getSolverList()) {
			modes.put(solver.getName(), solver.getDescription());
		}
	}
	
	public static void addModesOptionsToBuf() {
		String[] modeNames = modes.keySet().toArray(new String[0]);

		// Get length of longest mode name and adding 2 extra spaces for the padding
		int padd = Stream.of(modeNames).map(String::length).max(Integer::compareTo).get() + 2;

		for(int i = 0; i < modeNames.length; i++) {
			Util.mainSB.append(i);
			Util.mainSB.append(". ");
			Util.mainSB.append(modeNames[i]);
			Util.mainSB.append(Util.padd(padd, modeNames[i].length(), " "));
			Util.mainSB.append(modes.get(modeNames[i]));
			Util.mainSB.append("\n");
		}

		// Adds option to the end for exiting the program
		Util.mainSB.append(modeNames.length);
		Util.mainSB.append(". Exit");
		Util.mainSB.append(Util.padd(padd, 4, " "));
		Util.mainSB.append("Exits the program");
		Util.mainSB.append("\n");
	}

	public static void handelModeSelection(int modeInput) {
		String[] modeNames = modes.keySet().toArray(new String[0]);
		if(modeInput == modes.size()) {
			Util.startPrint();
			Util.mainSB.append("Exiting...");
			Util.endPrint();
			System.exit(0);
		}
		if (modeInput <= modes.size()) {
			String mode = modeNames[modeInput];

			Solver solver = SolverHandler.getSolverByName(mode);
			if(solver == null) {
				System.err.println("ERROR: not able to get corresponding solver for name '" + mode + "'");
				System.exit(1);
			}
			solver.execute();
		} else {
			System.err.println("Provided mode is not a valid mode!");
			System.out.println("Exiting...");
			System.exit(0);
		}
	}

}

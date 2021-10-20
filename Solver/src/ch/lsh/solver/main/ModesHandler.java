package ch.lsh.solver.main;

import java.util.HashMap;

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
		modes.put("Old", "Old implementation of solver");
		modes.put("IntOptimized", "Integer-based version of solver with optimization");
		modes.put("LongOptimized", "Long-based version of solver with optimization");
	}
	
	public static void addModesOptionsToBuf() {
		String[] modeNames = modes.keySet().toArray(new String[0]);
		
		for(int i = 0; i < modeNames.length; i++) {
			Util.mainSB.append(i);
			Util.mainSB.append(". ");
			Util.mainSB.append(modeNames[i]);
			Util.mainSB.append("\t");
			Util.mainSB.append(modes.get(modeNames[i]));
			Util.mainSB.append("\n");
		}
	}

}

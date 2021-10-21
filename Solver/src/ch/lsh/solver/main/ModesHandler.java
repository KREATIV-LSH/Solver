package ch.lsh.solver.main;

import java.util.HashMap;
import java.util.stream.Stream;

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

}

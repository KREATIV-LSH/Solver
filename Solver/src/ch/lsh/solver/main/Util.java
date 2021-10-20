package ch.lsh.solver.main;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Set;

public class Util {

	public static StringBuilder mainSB;

	// Constructor to satisfy sonarlint S111
	private Util() {
		throw new IllegalStateException("Utility class");
	}

	// Sets up the StringBuilder
	public static void setup(int sbCapacity) {
		mainSB = new StringBuilder(sbCapacity);
	}

	// Force Garbage-Collection cycle in jvm
	public static void garbageCollector() {
		System.gc();
		System.runFinalization();
	}

	// Start printing by resetting StringBuilder
	public static void startPrint() {
		mainSB.setLength(0);
	}

	// End printing by printing data that is in the StringBuilder
	public static void endPrint() {
		System.out.println(mainSB.toString());
	}

}

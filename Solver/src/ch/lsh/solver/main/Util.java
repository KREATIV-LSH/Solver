package ch.lsh.solver.main;

public class Util {
	
	public static StringBuilder mainSB;

	// Sets up the stringbuilder
	public static void setup(int sbCapacity) {
		mainSB = new StringBuilder(sbCapacity);
	}

	// Force gc cycle in jvm
	public static void garbageCollector() {
		System.gc();
		System.runFinalization();
	}

	// Start printing by reseting StringBuilder
	public static void startPrint() {
		mainSB.setLength(0);
	}

	// End printing by printing data that is in the StringBuiler
	public static void endPrint() {
		System.out.println(mainSB.toString());
	}

}

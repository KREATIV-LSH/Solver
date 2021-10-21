package ch.lsh.solver.main;

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

	// Custom padding for string, used mainly for printing
	public static String padd(int padding, int prevLen, String padder) {
		if(padding == 0 || padder.length() == 0) return null;
		StringBuilder tempSb = new StringBuilder((padding-prevLen)/padder.length()+2);
		for(int j = 0; j < (padding-prevLen)/padder.length(); j += padder.length()) { tempSb.append(padder); }
		return tempSb.toString();
	}

	// Start printing by resetting StringBuilder
	public static void startPrint() {
		mainSB.setLength(0);
	}

	// End printing by printing data that is in the StringBuilder
	public static void endPrint() {
		System.out.print(mainSB.toString());
	}

}

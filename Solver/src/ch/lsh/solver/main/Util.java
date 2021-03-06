package ch.lsh.solver.main;

public class Util {

	public static StringBuilder mainSB;
	private static boolean printEnable = true;

	// Constructor to satisfy sonarlint S111
	private Util() {
		throw new IllegalStateException("Utility class");
	}

	// Sets up the StringBuilder
	public static void setup(int sbCapacity) {
		mainSB = new StringBuilder(sbCapacity);
	}

	public static void enablePrintingGlobaly() {
		printEnable = true;
	}

	public static void disablePrintingGlobaly() {
		printEnable = false;
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

	// Check if input string can be converted to number
	public static boolean isNumber(String input) {
		input = input.replace("_", "").replace(",", "").replace(".", "").replace("`", "").replace("'", "");
		if(input.length() == 0) return false;
		return input.chars().allMatch(Character::isDigit);
	}

	// Parses a String to an int
	public static int parseInt(String in) {
		in = in.replace("_", "").replace(",", "").replace(".", "").replace("`", "").replace("'", "");
		return Integer.parseInt(in);
	}

	// Parses a String to a long
	public static long parseLong(String in) {
		in = in.replace("_", "").replace(",", "").replace(".", "").replace("`", "").replace("'", "");
		return Long.parseLong(in);
	}

	// Start printing by resetting StringBuilder
	public static void startPrint() {
		mainSB.setLength(0);
	}

	// End printing by printing data that is in the StringBuilder
	public static void endPrint() {
		if (printEnable) System.out.print(mainSB.toString());
	}

}

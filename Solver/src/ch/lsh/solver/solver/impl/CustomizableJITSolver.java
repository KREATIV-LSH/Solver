package ch.lsh.solver.solver.impl;

import java.io.IOException;
import java.util.Arrays;
import java.util.Random;

import ch.lsh.solver.main.Main;
import ch.lsh.solver.main.Util;
import ch.lsh.solver.solver.Solver;

public class CustomizableJITSolver extends Solver {

	public CustomizableJITSolver() {
		super("Long-Customizable", "A fully customizable long solver with JIT-Warmup functionality");
	}

	@Override
	public void execute() {
		String[] inputs = new String[0];
		try {
			inputs = getUserSettings(true, true, true, true, true);
		} catch (Exception e) {
			e.printStackTrace();
			System.exit(1);
		}
		checkIfStart9(inputs[1]);
		long start = Util.parseLong(inputs[0]);
		long stop = Util.parseLong(inputs[1]);
		long step = Util.parseLong(inputs[2]);
		boolean distance = Boolean.parseBoolean(inputs[3]);
		int lineBufSize = Util.parseInt(inputs[4]);

		int[] range = checkRange();
		range[1] = range[1] + 1;

		if(doJITWarmup()) {
			Util.startPrint();
			Util.mainSB.append("Performing warmup for JIT optimization...\n");
			Util.endPrint();
			if(range[1] == 10) {
				jitWarmup(0, 5000, 100_000, 50_000_000, lineBufSize, step, range[0], range[1]);
			} else {
				jitWarmup(0, 1000, 1_000, 500_000, lineBufSize, step, range[0], range[1]);
			}
			Util.startPrint();
			Util.mainSB.append("\nWarming up finished...\n");
			Util.endPrint();
			startingCountdown(3);
		} else {
			startingCountdown(5);
		}

		long startTime = System.nanoTime();
		calculate(start, stop, distance, lineBufSize, step, range[0], range[1]);
		long elapsedTime = System.nanoTime() - startTime;
		Util.startPrint();
		Util.mainSB.append("Finished!\n");
		Util.mainSB.append("Time of calculation:\n");
		formatAppendTimeToBuff(elapsedTime);
		Util.endPrint();
	}

	private static boolean doJITWarmup() {
		try {
			Util.startPrint();
			Util.mainSB.append("Run JIT-Warmup? (yes/no): ");
			Util.endPrint();
			String _input = Main.inputBuffReader.readLine();
			if(_input != null) {
				while(_input.length() > 3 || (!_input.equalsIgnoreCase("yes") && !_input.equalsIgnoreCase("no"))) {
					Util.startPrint();
					Util.mainSB.append("Run JIT-Warmup? (yes/no): ");
					Util.endPrint();
					_input = Main.inputBuffReader.readLine();
				}
				if(_input.equalsIgnoreCase("yes")) return true;
				return false;
			} else {
				System.err.println("Could not parse input!");
				System.exit(1);
			}
		} catch (IOException e) {
			e.printStackTrace();
			System.exit(1);
		}
		return false;
	}

	private static int[] checkRange() {
		int[] temp = new int[1];
		try {
			Util.startPrint();
			Util.mainSB.append("Check range(Default 2-10): ");
			Util.endPrint();
			String _input = Main.inputBuffReader.readLine();
			if(_input != null) {
				while(!isRange(_input)) {
					Util.startPrint();
					Util.mainSB.append("Check range(Default 2-10): ");
					Util.endPrint();
					_input = Main.inputBuffReader.readLine();
				}
				temp = convertToRange(_input);
			} else {
				System.err.println("Could not parse input!");
				System.exit(1);
			}
		} catch (IOException e) {
			e.printStackTrace();
			System.exit(1);
		}
		return temp;
	}

	private static boolean isRange(String input) {
		if(input.contains("-")) {
			if(input.split("-").length == 2) {
				return Util.isNumber(input.split("-")[0]) && Util.isNumber(input.split("-")[1]);
			}
		}
		return false;
	}

	private static int[] convertToRange(String input) {
		int[] temp = new int[2];
		String[] tempS = input.split("-");
		temp[0] = Util.parseInt(tempS[0]);
		temp[1] = Util.parseInt(tempS[1]);
		return temp;
	}
	
	private static void jitWarmup(long start1, long start2, long end1, long end2, int lineBufSize, long stepSize, int rangeStart, int rangeStop) {
		Random rmd = new Random();
		for (int i = 0; i < 51; i++) {
			long start = (long) rmd.nextInt((int) start2 - (int) start1) + start2;
			long stop = (long) rmd.nextInt((int) end2 - (int) end1) + end2;
			stop = lastDigit9(stop);
			Util.disablePrintingGlobaly();
			calculate(start, stop, true, lineBufSize, stepSize, rangeStart, rangeStop);
			calculate(start, stop, false, lineBufSize, stepSize, rangeStart, rangeStop);
			Util.enablePrintingGlobaly();
			Util.startPrint();
			Util.mainSB.append(i * 2);
			Util.mainSB.append("% ");
			Util.endPrint();
		}
	}

	private static long lastDigit9(long in) {
		String[] num = Long.toString(in).split("");
		num[num.length-1] = "9";
		return Util.parseLong(Arrays.toString(num).replace("[", "").replace("]", "").replace(" ", ""));
	}
	
	private static void calculate(long start, long end, boolean distance, int lineBufSize, long stepSize, int rangeStart, int rangeStop) {
		int printBufCount = 0;
		if(distance) {
			long prev = 0;
			for (long i = end; i > start; i -= stepSize) {
				boolean is = true;
				for (int j = rangeStart; j < rangeStop; j++) {
					if (i % j < Main.halfLUT_long[j - 2]) { is = false; break; }
				}
				if (is) {
					printBufCount++;
					Util.mainSB.append(i);
					Util.mainSB.append("\t");
					Util.mainSB.append(prev - i);
					prev = i;
					Util.mainSB.append("\n");
					if(printBufCount > lineBufSize ) {
						printBufCount = 0;
						Util.endPrint();
						Util.startPrint();
					}
				}
			}
		} else {
			for (long i = end; i > start; i -= stepSize) {
				boolean is = true;
				for (int j = rangeStart; j < rangeStop; j++) {
					if (i % j < Main.halfLUT_long[j - 2]) { is = false; break; }
				}
				if (is) {
					printBufCount++;
					Util.mainSB.append(i);
					Util.mainSB.append("\n");
					if(printBufCount > lineBufSize ) {
						printBufCount = 0;
						Util.endPrint();
						Util.startPrint();
					}
				}
			}
		}
		Util.endPrint();
		Util.startPrint();
	}
	
}

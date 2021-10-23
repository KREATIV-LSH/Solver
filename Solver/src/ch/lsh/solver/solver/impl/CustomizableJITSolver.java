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

		if(doJITWarmup()) {
			Util.startPrint();
			Util.mainSB.append("Performing warmup for JIT optimization...\n");
			Util.endPrint();
			jitWarmup(0, 5000, 100_000, 50_000_000, lineBufSize, step);
			Util.startPrint();
			Util.mainSB.append("\nWarming up finished...\n");
			Util.endPrint();
		}

		startingCountdown(5);

		long startTime = System.nanoTime();
		calculate(start, stop, distance, lineBufSize, step);
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
	
	private static void jitWarmup(long start1, long start2, long end1, long end2, int lineBufSize, long stepSize) {
		Random rmd = new Random();
		for (int i = 0; i < 51; i++) {
			long start = (long) rmd.nextInt((int) start2 - (int) start1) + start2;
			long stop = (long) rmd.nextInt((int) end2 - (int) end1) + end2;
			stop = lastDigit9(stop);
			Util.disablePrintingGlobaly();
			calculate(start, stop, true, lineBufSize, stepSize);
			calculate(start, stop, false, lineBufSize, stepSize);
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
	
	private static void calculate(long start, long end, boolean distance, int lineBufSize, long stepSize) {
		int printBufCount = 0;
		if(distance) {
			long prev = 0;
			for (long i = end; i > 0; i -= stepSize) {
				boolean is = true;
				for (int j = 2; j < 11; j++) {
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
			for (long i = end; i > 0; i -= stepSize) {
				boolean is = true;
				for (int j = 2; j < 11; j++) {
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

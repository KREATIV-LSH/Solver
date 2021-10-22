package ch.lsh.solver.solver.impl;

import ch.lsh.solver.main.Main;
import ch.lsh.solver.main.Util;
import ch.lsh.solver.solver.Solver;

public class LongOptimizedSolver extends Solver {

	public LongOptimizedSolver() {
		super("LongOptimized", "Long-based version of solver with optimization");
	}

	@Override
	public void execute() {
		String[] inputs = new String[0];
		try {
			inputs = getUserSettings(true, true, false, true, true);
		} catch (Exception e) {
			e.printStackTrace();
			System.exit(1);
		}
		this.checkIfStart9(inputs[1]);
		long start = Util.parseLong(inputs[0]);
		long stop = Util.parseLong(inputs[1]);
		boolean distance = Boolean.parseBoolean(inputs[2]);
		int lineBufSize = Util.parseInt(inputs[3]);

		this.startingCountdown(5);

		long startTime = System.nanoTime();
		calculate(start, stop, distance, lineBufSize);
		long elapsedTime = System.nanoTime() - startTime;
		Util.startPrint();
		Util.mainSB.append("Finished!\n");
		Util.mainSB.append("Time of calculation:\n");
		this.formatAppendTimeToBuff(elapsedTime);
		Util.endPrint();
	}

	private static void calculate(long start, long end, boolean distance, int lineBufSize) {
		int printBufCount = 0;
		if(distance) {
			long prev = 0;
			for (long i = end; i > 0; i -= 10) {
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
			for (long i = end; i > 0; i -= 10) {
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

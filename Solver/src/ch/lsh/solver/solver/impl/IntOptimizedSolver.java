package ch.lsh.solver.solver.impl;

import ch.lsh.solver.main.Main;
import ch.lsh.solver.main.Util;
import ch.lsh.solver.solver.Solver;

public class IntOptimizedSolver extends Solver {

	public IntOptimizedSolver() {
		super("IntOptimized", "Integer-based version of solver with optimization");
	}

	@Override
	public void execute() {
		String[] inputs = new String[0];
		try {
			inputs = this.getUserSettings(true, true, false, true, true);
		} catch (Exception e) {
			e.printStackTrace();
			System.exit(1);
		}
		// Assigning and checking input values
		this.checkIfStart9(inputs[1]);
		int start = Util.parseInt(inputs[0]);
		int stop = Util.parseInt(inputs[1]);
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

	private static void calculate(int start, int end, boolean distance, int lineBufSize) {
		// Running different loop when distance is true, will improve performance
		int printBufCount = 0;
		if(distance) {
			int prev = 0;
			for (int i = end; i > start; i-=10) {
				// Initialization of is Boolean to true so the input is correct by default
				// this allows to instantly abort the checking process if it finds the first false value
				boolean is = true;
				// Inner loop
				for(int j = 2; j < 11; j++) {
					// Checking if the remainder of the division between i and j
					// is smaller then the half of j using the halfLUT look up table
					// and if that's the case the is bool gets set to false and the inner loop gets escaped
					if(i % j < Main.halfLUT_int[j-2]) { is = false; break; }
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
			for (int i = end; i > start; i-=10) {
				// Initialization of is Boolean to true so the input is correct by default
				// this allows to instantly abort the checking process if it finds the first false value
				boolean is = true;
				// Inner loop
				for(int j = 2; j < 11; j++) {
					// Checking if the remainder of the division between i and j
					// is smaller then the half of j using the halfLUT look up table
					// and if that's the case the is bool gets set to false and the inner loop gets escaped
					if(i % j < Main.halfLUT_int[j-2]) { is = false; break; }
				}
				if (is) {
					printBufCount++;
					Util.mainSB.append(i);
					Util.mainSB.append("\t");
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

package ch.lsh.solver.solver.impl;

import ch.lsh.solver.main.Util;
import ch.lsh.solver.solver.Solver;

public class OldSolver extends Solver {

	public OldSolver() {
		super("Old", "Old implementation of solver");
	}

	@Override
	public void execute() {
		String[] inputs = new String[3];
		try {
			inputs = this.getUserSettings(true, true, false, true, false);
		} catch (Exception e) {
			e.printStackTrace();
			System.exit(1);
		}

		this.checkIfStart9(inputs[0]);

		int start = Util.parseInt(inputs[0]);
		int stop = Util.parseInt(inputs[1]);
		boolean distance = Boolean.parseBoolean(inputs[2]);
		Util.startPrint();
		Util.mainSB.append("Settings:\n");
		Util.mainSB.append("Start: ");
		Util.mainSB.append(start);
		Util.mainSB.append("\nStop: ");
		Util.mainSB.append(stop);
		Util.mainSB.append("\nDistanceToPrev: ");
		Util.mainSB.append(distance);
		Util.mainSB.append("\n");
		Util.endPrint();

		this.startingCountdown(5);

		// Calculation
		long startTime = System.nanoTime();
		int prev = 599;
		int printBufCount = 0;

		Util.startPrint();
		for(int i = start; i <= stop; i+=10) {
			boolean is = true;
			for(int j = 2; j <= 10; j++) {
				if(!((i % j) >= (j / 2))) { is = false; }
			}
			if(is) {
				printBufCount++;
				Util.mainSB.append(i);
				Util.mainSB.append("\t");
				if(distance) { Util.mainSB.append(i - prev); prev = i; }
				Util.mainSB.append("\n");
				if(printBufCount > 8 ) {
					printBufCount = 0;
					Util.endPrint();
					Util.startPrint();
				}
			}
		}
		long elapsedTime = System.nanoTime() - startTime;
		Util.startPrint();
		Util.mainSB.append("Finished!\n");
		Util.mainSB.append("Time of calculation:\n");
		this.formatAppendTimeToBuff(elapsedTime);
		Util.endPrint();

	}
	
}

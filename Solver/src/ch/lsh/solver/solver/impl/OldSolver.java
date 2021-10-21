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
			inputs = this.getUserSettings(true, true, false, true);
		} catch (Exception e) {
			e.printStackTrace();
			System.exit(1);
		}
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
		long startTime = System.currentTimeMillis();
		int prev = 599;
		for(int i = start; i <= stop; i+=10) {
			boolean is = true;
			for(int j = 2; j <= 10; j++) {
				if(!((i % j) >= (j / 2))) { is = false; }
			}
			if(is) { System.out.println(i + "\t" + (distance ? i - prev : "")); prev = i;}
		}
		long elapsedTime = System.currentTimeMillis() - startTime;
		Util.startPrint();
		Util.mainSB.append("Finished!\n");
		Util.mainSB.append("Time of calculation: ");
		Util.mainSB.append(elapsedTime);
		Util.mainSB.append("ms\n");
		Util.endPrint();

	}
	
}

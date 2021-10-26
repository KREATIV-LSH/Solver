package ch.lsh.solver.solver.impl;

import ch.lsh.solver.main.Util;
import ch.lsh.solver.solver.Solver;

public class SetDifSolver extends Solver {

	public SetDifSolver() {
		super("SetDifSolver", "Solver that uses pre-defined distances to generate the next value");
	}

	@Override
	public void execute() {
		String[] inputs = new String[0];
		try {
			inputs = getUserSettings(false, true, false, false, true);
		} catch (Exception e) {
			e.printStackTrace();
			System.exit(1);
		}
		long stop = Util.parseLong(inputs[0]);
		int lineBufSize = Util.parseInt(inputs[1]);

		startingCountdown(5);

		long startTime = System.nanoTime();
		calculate(stop, lineBufSize);
		long elapsedTime = System.nanoTime() - startTime;
		Util.startPrint();
		Util.mainSB.append("\nFinished!\n");
		Util.mainSB.append("Time of calculation:\n");
		this.formatAppendTimeToBuff(elapsedTime);
		Util.endPrint();
	}

	private void calculate(long stop, int lineBufferSize) {
		long steps = (stop) / 420l;
		Long[] distanceLUT = {0l, 120l, 720l, 240l, 720l, 120l, 600l};
		long counter = 599;
		int printBufCount = 0;
		for(long i = 0; i < steps; i++) {
			Util.mainSB.append(counter);
			Util.mainSB.append(" ");
			Util.mainSB.append(i % 5);
			Util.mainSB.append(" ");
			Util.mainSB.append(i);
			Util.mainSB.append("\n");
			if(printBufCount > lineBufferSize) {
				Util.startPrint();
				Util.endPrint();
			}
			counter += distanceLUT[(int) i % 6 + 1];
		}
		Util.endPrint();
	}
	
}

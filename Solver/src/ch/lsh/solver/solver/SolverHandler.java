package ch.lsh.solver.solver;

import java.util.ArrayList;

public class SolverHandler {

	private static ArrayList<Solver> solverList;
	
	// Constructor to satisfy sonarlint S111
	private SolverHandler() {
		throw new IllegalStateException("Utility class");
	}

	public static void setupSolvers() {
		solverList = new ArrayList<>();

	}

	private static void addSolver(Solver solver) {
		solverList.add(solver);
	}

	public static ArrayList<Solver> getSolverList() {
		return solverList;
	}

	public static String[] getSolverNames() {
		ArrayList<String> temp = new ArrayList<>();
		for (Solver solver : getSolverList()) {
			temp.add(solver.getName());
		}
		return temp.toArray(new String[0]);
	}

	public static Solver getSolverByName(String name) {
		for (Solver solver : getSolverList()) {
			if(solver.getName().equalsIgnoreCase(name)) {
				return solver;
			}
		}
		return null;
	}

}

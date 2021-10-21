package ch.lsh.solver.solver;

public abstract class Solver {

	private String name;
	private String description;

	public Solver(String name, String description) {
		this.name = name;
		this.description = description;
	}

	public abstract void execute();

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}

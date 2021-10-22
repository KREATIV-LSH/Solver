package ch.lsh.solver.solver;

import java.io.IOException;
import java.util.ArrayList;

import ch.lsh.solver.main.Main;
import ch.lsh.solver.main.Util;

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

	public static void startingCountdown(int time) {
		Util.startPrint();
		Util.mainSB.append("Starting in ");
		Util.endPrint();
		for (int i = time; i > 0; i--) {
			Util.startPrint();
			Util.mainSB.append(i);
			Util.mainSB.append(" ");
			Util.endPrint();
			try {
				Thread.sleep(1000);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		Util.startPrint();
		Util.mainSB.append("\nStarting now...");
		Util.endPrint();
	}

	// Checks if the start value ends with a 9
	// And when not will ask the user if he wants to continue
	public static void checkIfStart9(String start) {
		try {
			if (start.toCharArray()[start.length()-1] != '9') {
				Util.startPrint();
				Util.mainSB.append(
						"\nYour provided start point does not end with the digit '9' this will often cause errors!\n");
				Util.mainSB.append("Do you want to continue anyways(could yield wrong results or errors)? (y/N): ");
				Util.endPrint();
				String input = "";

				input = Main.inputBuffReader.readLine();
				while (input.length() != 0 && !input.equalsIgnoreCase("y") && !input.equalsIgnoreCase("n")) {
					Util.startPrint();
					Util.mainSB.append("Do you want to continue anyways(could yield wrong results or errors)? (y/N): ");
					Util.endPrint();
					input = Main.inputBuffReader.readLine();
				}
				if(!input.equalsIgnoreCase("y")) {
					Util.startPrint();
					Util.mainSB.append("Exiting...");
					Util.endPrint();
					System.exit(0);
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static String[] getUserSettings(boolean start, boolean stop, boolean step, boolean distanceToPrev,
			boolean lineBufSize) throws IOException {
		ArrayList<String> temp = new ArrayList<>();
		if (start) {
			Util.startPrint();
			Util.mainSB.append("Input start-point of solver: ");
			Util.endPrint();
			String _start = Main.inputBuffReader.readLine();
			if (_start != null) {
				while (_start.length() == 0 || !Util.isNumber(_start)) {
					Util.startPrint();
					Util.mainSB.append("Input start-point of solver: ");
					Util.endPrint();
					_start = Main.inputBuffReader.readLine();
				}
				temp.add(_start);
			} else {
				System.err.println("Not able to process input!");
				System.exit(1);
			}
		}
		if (stop) {
			Util.startPrint();
			Util.mainSB.append("Input end-point of solver: ");
			Util.endPrint();
			String _stop = Main.inputBuffReader.readLine();
			if (_stop != null) {
				while (_stop.length() == 0 || !Util.isNumber(_stop)) {
					Util.startPrint();
					Util.mainSB.append("Input end-point of solver: ");
					Util.endPrint();
					_stop = Main.inputBuffReader.readLine();
				}
				temp.add(_stop);
			} else {
				System.err.println("Not able to process input!");
				System.exit(1);
			}
		}
		if (step) {
			Util.startPrint();
			Util.mainSB.append("Input step-size of solver: ");
			Util.endPrint();
			String _step = Main.inputBuffReader.readLine();
			if (_step != null) {
				while (_step.length() == 0 || !Util.isNumber(_step)) {
					Util.startPrint();
					Util.mainSB.append("Input step-size of solver: ");
					Util.endPrint();
					_step = Main.inputBuffReader.readLine();
				}
				temp.add(_step);
			} else {
				System.err.println("Not able to process input!");
				System.exit(1);
			}
		}
		if (distanceToPrev) {
			Util.startPrint();
			Util.mainSB.append("Display distance to previous result(true or false): ");
			Util.endPrint();
			String _bool = Main.inputBuffReader.readLine();
			if (_bool != null) {
				while (_bool.length() < 4 || (!_bool.equals("true") && !_bool.equals("false"))) {
					Util.startPrint();
					Util.mainSB.append("Display distance to previous result(true or false): ");
					Util.endPrint();
					_bool = Main.inputBuffReader.readLine();
				}
				temp.add(_bool);
			} else {
				System.err.println("Not able to process input!");
				System.exit(1);
			}
		}
		if (lineBufSize) {
			Util.startPrint();
			Util.mainSB.append("Line buffer size(how many lines will be stored in the buffer before printing):");
			Util.endPrint();
			String _lineBufSize = Main.inputBuffReader.readLine();
			if (_lineBufSize != null) {
				while (_lineBufSize.length() == 0 || !Util.isNumber(_lineBufSize)) {
					Util.startPrint();
					Util.mainSB
							.append("Line buffer size(how many lines will be stored in the buffer before printing):");
					Util.endPrint();
					_lineBufSize = Main.inputBuffReader.readLine();
				}
				temp.add(_lineBufSize);
			} else {
				System.err.println("Not able to process input!");
				System.exit(1);
			}
		}

		return temp.toArray(new String[0]);
	}

}

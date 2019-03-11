package com.fdm.CarparkSimulation;

public class RunCarparkSimulation {

	public static void main(String[] args) {

		CarparkView view = new CarparkView();
		CarparkValidator validator = new CarparkValidator();
		CarparkParser carparkParser = new CarparkParser();

		String command;
		int maxSpaces = -1;
		do
			try {
				System.out.println("How many spaces does the carpark have?");
				command = view.getCommand().toLowerCase();
				validator.validateInitialSize(command);
				maxSpaces = carparkParser.parseInt(command);
			} catch (CannotProcessException cpe) {
				view.display(cpe.getMessage());
			}
		while (maxSpaces < 0);
		CarparkController controller = new CarparkController(new CarparkModel(maxSpaces), view, validator,
				carparkParser);

		while (controller.isRunning()) {
			controller.handleInput();
		}
	}
}

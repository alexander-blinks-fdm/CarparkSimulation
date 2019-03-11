package com.fdm.CarparkSimulation;

public class CarparkValidator implements iValidator {

	private StringBuffer fault;

	@Override
	public void validate(String command) throws CannotProcessException {
		if (initNullCheck(command))
			;
		else if (!command.matches("(enter|exit|report|quit).*")) {
			fault.append("a command must start with either 'enter', 'exit', 'report' or 'quit'.");
		} else if (command.matches("(enter|exit).*") && !command.matches("(enter|exit) (car|truck).*")) {
			fault.append("'enter' or 'exit' commands must be followed by either 'car' or 'truck'.");
		} else if (command.matches("enter.*") && command.matches("enter (car|truck).+")) {
			fault.append("'enter' only takes one further argument ('car' or 'truck')");
		} else if (command.matches("exit.*") && !command.matches("exit (car|truck) \\d+")) {
			fault.append("'exit' commands must have a duration in whole hours (e.g. '5').");
		} else if (command.matches("(report|quit).+")) {
			fault.append("'report' and 'quit' must not be followed by other arguments.");
		} else
			return;
		throw new CannotProcessException(fault.toString());
	}

	public void validateInitialSize(String command) throws CannotProcessException {
		if (initNullCheck(command))
			;
		else if (!command.matches("\\d+")) {
			fault.append("initial size must be a whole, positive number (in digits).");
		} else
			return;
		throw new CannotProcessException(fault.toString());
	}

	private boolean initNullCheck(String command) {
		fault = new StringBuffer("Couldn't process command as ");
		if (command == null) {
			fault.append(" as the command is null.");
			return true;
		}
		return false;
	}

}

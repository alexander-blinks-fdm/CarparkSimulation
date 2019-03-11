package com.fdm.CarparkSimulation;

public class CarparkParser implements iParser{
	
	public String parse(String command, CarparkModel model) {
		String result = "";
		String[] words = command.split(" ", 3);
		switch (words[0]) {
		case ("enter"):
			if (!model.enter(words[1].charAt(0)))
				result = "Not enough spaces avaliable to insert a " + words[1] + '.';
			break;
		case ("exit"):
			if (!model.remove(words[1].charAt(0), parseInt(words[2])))
				result = "No parked " + words[1] + "s found.";
			break;
		case ("report"):
			result = model.report();
			break;
		case ("quit"):
			result = null;
			break;
		}
		return result;
	}

	public int parseInt(String expression) {
		try {
			return Integer.parseInt(expression);
		} catch (NumberFormatException nfe) {
			System.out.println("Couldn't parse " + expression + " as digit/s");
		}
		return -1;
	}
}

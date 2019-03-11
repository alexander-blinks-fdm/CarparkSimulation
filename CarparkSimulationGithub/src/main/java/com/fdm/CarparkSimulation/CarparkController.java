package com.fdm.CarparkSimulation;

public class CarparkController {

	private final iView view;
	private final iValidator validator;
	private final iParser parser;
	private final CarparkModel carparkModel;
	private boolean isRunning;

	public boolean isRunning() {
		return isRunning;
	}
	
	public CarparkController(CarparkModel carparkModel, iView view, iValidator validator, CarparkParser parser) {
		this.view = view;
		this.carparkModel = carparkModel;
		this.validator = validator;
		this.isRunning = true;
		this.parser = parser;
	}

	public void handleInput() {
		try {
			String command = view.getCommand().toLowerCase();
			validator.validate(command);
			String result = parser.parse(command, carparkModel);
			if (result == null)
				isRunning = false;
			else
				view.display(result);
		} catch (CannotProcessException cpe) {
			view.display(cpe.getMessage());
		}
	}
}

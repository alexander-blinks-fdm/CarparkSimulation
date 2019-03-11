package com.fdm.CarparkSimulation;

public interface iParser {
	String parse(String command, CarparkModel model);
	
	int parseInt(String expression);
}

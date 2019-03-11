package com.fdm.CarparkSimulation;

import java.io.InputStream;
import java.util.Scanner;

public class CarparkView implements iView{

	private Scanner scanner;
	
	public CarparkView(InputStream inStream) {
		scanner = new Scanner(inStream);
	}

	public CarparkView() {
		this(System.in);
	}

	@Override
	public String getCommand() {
		if (scanner.hasNextLine())
			return scanner.nextLine();
		return "";
	}

	@Override
	public void display(String output) {
		System.out.println(output);
		
	}

}

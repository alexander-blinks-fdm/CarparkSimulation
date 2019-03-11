package com.fdm.CarparkSimulation;

import static org.junit.Assert.assertEquals;

import java.io.ByteArrayInputStream;

import org.junit.Before;
import org.junit.Test;

public class CarparkViewTest {
	
	private CarparkView carparkView;
	
	@Before
	public void setup() {
		carparkView = new CarparkView();
	}

	@Test
	public void get_command_with_no_input_returns_nothing() {
		assertLineInput("");
	}
	
	@Test
	public void add_car_command_returns_appropriate_output() {
		assertLineInput("ENTER car");
	}
	
	@Test
	public void get_command_with_garbage_input_returns_garbage() {
		assertLineInput("jdfshdfkjsh23 b");
	}
	
	@Test
	public void scanner_with_no_next_line_returns_empty_string() {
		carparkView = new CarparkView(new ByteArrayInputStream(("").getBytes()));
		assertEquals("", carparkView.getCommand());
	}

	private void assertLineInput(String data) {
		carparkView = new CarparkView(new ByteArrayInputStream((data + "\r\n").getBytes()));
		assertEquals(data, carparkView.getCommand());
	}
}

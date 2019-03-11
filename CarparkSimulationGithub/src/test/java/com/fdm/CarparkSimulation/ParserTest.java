package com.fdm.CarparkSimulation;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Before;
import org.junit.Test;

public class ParserTest {

	
	private CarparkParser parser;
	
	@Before
	public void setup() {
		parser = new CarparkParser();
	}
	
	@Test
	public void can_be_instanced() {
		CarparkParser parser = new CarparkParser();
		assertNotNull(parser);
	}
	
	@Test
	public void parseInt_can_read_int_correctly() {
		assertEquals(6, parser.parseInt("6"));	
	}
	
	@Test
	public void parseInt_will_return_negative_oneif_cannotConvert() {
		assertEquals(-1, parser.parseInt("seven"));
	}
}

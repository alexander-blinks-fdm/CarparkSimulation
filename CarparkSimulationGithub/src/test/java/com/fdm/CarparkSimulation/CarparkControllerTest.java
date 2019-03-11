package com.fdm.CarparkSimulation;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InOrder;

import static org.junit.Assert.assertFalse;
import static org.mockito.Mockito.*;

public class CarparkControllerTest {
	
	private iView view;
	private CarparkModel carparkModel;
	private CarparkController simulator;
	private InOrder inOrder;
	private iValidator validator;
	private CarparkParser parser;
	
	@Before
	public void setup() {
		view = mock(iView.class);
		carparkModel = mock(CarparkModel.class);
		validator = mock(CarparkValidator.class);
		parser = new CarparkParser();
		simulator = new CarparkController(carparkModel, view, validator, parser);
		inOrder = inOrder(carparkModel);
	}
	
	@Test
	public void verify_gets_user_input() {
		when(view.getCommand()).thenReturn("foo");
		simulator.handleInput();
		verify(view).getCommand();
	}
	
	@Test
	public void if_exception_occurs_does_not_proceed() throws CannotProcessException{
		when(view.getCommand()).thenReturn("ent van");
		doThrow(new CannotProcessException(null)).when(validator).validate("ent van");
		inOrder = inOrder(validator, view, carparkModel);
		
		simulator.handleInput();
		
		inOrder.verify(validator).validate("ent van");
		inOrder.verify(view).display(anyString());
		inOrder.verifyNoMoreInteractions();
	}
	
	@Test
	public void verify_can_enter_vehicle() {
		when(view.getCommand()).thenReturn("enter car");
		when(carparkModel.enter('c')).thenReturn(true);
		
		simulator.handleInput(); 
		verify(view).getCommand();
		verify(view).display("");
	}
	
	@Test
	public void verify_cannot_enter_vehicle_if_no_room() {
		when(view.getCommand()).thenReturn("enter truck");
		when(carparkModel.enter('t')).thenReturn(false);
		
		simulator.handleInput(); 
		verify(view).getCommand();
		verify(view).display(startsWith("Not enough spaces"));
	}
	
	@Test
	public void verify_can_remove_car() {
		when(view.getCommand()).thenReturn("enter car", "exit car 3");
		when(carparkModel.enter('c')).thenReturn(true);
		when(carparkModel.remove('c', 3)).thenReturn(true);
		
		handleInputs(2);
		
		verify(view, times(2)).getCommand();
		verify(view, times(2)).display("");
	}
	
	@Test
	public void will_not_remove_if_cannot_find() {
		when(view.getCommand()).thenReturn("exit car 9");
		when(carparkModel.remove('c', 9)).thenReturn(false);
		
		handleInputs(1);
		
		verify(view, times(1)).getCommand();
		verify(view, times(1)).display(startsWith("No parked"));
	}
	
	@Test
	public void can_respond_to_quit_command() {
		when(view.getCommand()).thenReturn("quit");
		
		simulator.handleInput();
		
		verify(view).getCommand();
		assertFalse(simulator.isRunning());
	}
	
	@Test
	public void can_generate_report() {
		when(view.getCommand()).thenReturn("report");
		when(carparkModel.report()).thenReturn("Cars Entered: 0\\nTrucks Entered: 0\\nCars");
		
		simulator.handleInput();
		
		verify(view).display(startsWith("Cars Entered:"));
	}
	
	private void handleInputs(int times) {
		for (int i = 0; i < times; i += 1)
			simulator.handleInput();
	}
}

package com.fdm.CarparkSimulation;

import static org.junit.Assert.fail;

import org.junit.Before;
import org.junit.Test;

public class CarparkValidatorTest {

	private CarparkValidator carparkValidator;

	@Before
	public void setup() {
		carparkValidator = new CarparkValidator();
	}

	@Test(expected = CannotProcessException.class)
	public void can_throw_cannot_process_exception() throws CannotProcessException {
		carparkValidator.validate(null);
	}

	@Test
	public void will_not_throw_on_good_enter_input() throws CannotProcessException {
		assertNoException("enter car");
	}

	@Test
	public void first_word_is_either_enter_exit_report_or_quit() throws CannotProcessException {
		assertNoException("enter truck");
		assertNoException("report");
		assertNoException("quit");
	}

	@Test(expected = CannotProcessException.class)
	public void first_word_cant_be_garbage() throws CannotProcessException {
		carparkValidator.validate("fsdjsfdf");
	}

	@Test(expected = CannotProcessException.class)
	public void will_throw_if_second_word_if_present_isnt_car_or_truck() throws CannotProcessException {
		carparkValidator.validate("enter bus");
	}

	@Test(expected = CannotProcessException.class)
	public void will_throw_if_enter_doesnt_have_second_word_present() throws CannotProcessException {
		carparkValidator.validate("enter");
	}

	@Test(expected = CannotProcessException.class)
	public void will_throw_if_exit_doesnt_have_second_word_present() throws CannotProcessException {
		carparkValidator.validate("exit");
	}

	@Test(expected = CannotProcessException.class)
	public void will_throw_if_exit_doesnt_have_third_word_present() throws CannotProcessException {
		carparkValidator.validate("exit car");
	}

	@Test(expected = CannotProcessException.class)
	public void will_throw_if_exit_duration_is_not_whole_hour() throws CannotProcessException {
		carparkValidator.validate("exit car 2.4");
	}

	@Test
	public void will_not_throw_on_good_input() throws CannotProcessException {
		assertNoException("exit truck 5");
	}

	@Test(expected = CannotProcessException.class)
	public void will_throw_if_given_more_than_three_arguments() throws CannotProcessException {
		carparkValidator.validate("exit car 2 hours");
	}

	@Test(expected = CannotProcessException.class)
	public void will_throw_if_report_is_followed_by_more_words() throws CannotProcessException {
		carparkValidator.validate("report 6");
	}

	@Test(expected = CannotProcessException.class)
	public void will_throw_if_quit_is_followed_by_more_words() throws CannotProcessException {
		carparkValidator.validate("quit now");
	}

	@Test(expected = CannotProcessException.class)
	public void can_throw_cannot_process_exception_on_initialisation() throws CannotProcessException {
		carparkValidator.validateInitialSize(null);
	}

	@Test
	public void will_not_throw_initalising_proper_size() throws CannotProcessException {
		assertNoExceptionDuringIntialisation("6");
	}

	@Test(expected = CannotProcessException.class)
	public void will_throw_when_initialising_with_non_digit() throws CannotProcessException {
		carparkValidator.validateInitialSize("seven");
	}
	
	@Test(expected = CannotProcessException.class)
	public void will_throw_if_enter_is_given_too_many_arguments() throws CannotProcessException {
		carparkValidator.validate("enter truck abc123");
	}

	private void assertNoException(String command) {
		try {
			carparkValidator.validate(command);
		} catch (CannotProcessException cpe) {
			fail("CannotProcessException occured, but wanted no exception.\n" + cpe.getMessage());
		}
	}

	private void assertNoExceptionDuringIntialisation(String command) {
		try {
			carparkValidator.validateInitialSize(command);
		} catch (CannotProcessException cpe) {
			fail("CannotProcessException occured, but wanted no exception.\n" + cpe.getMessage());
		}
	}

}

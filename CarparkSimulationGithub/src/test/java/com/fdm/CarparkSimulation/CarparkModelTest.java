package com.fdm.CarparkSimulation;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

public class CarparkModelTest {

	private CarparkModel manager;

	@Before
	public void setup() {
		manager = new CarparkModel(5);
	}

	@Test
	public void manager_can_set_spaces_at_construction_and_get_them() {
		assertEquals(5, manager.getTotalSpaces());
	}

	@Test
	public void adding_a_car_decrements_avaliable_spaces_and_adds_one_to_cars_parked() {
		manager.enter('c');
		assertEquals(4, manager.getAvaliableSpaces());
		assertEquals(1, manager.getCars());
	}
	
	@Test
	public void adding_a_truck_decrements_avaliable_spaces_by_two_and_adds_one_to_trucks_parked() {
		manager.enter('t');
		assertEquals(3, manager.getAvaliableSpaces());
		assertEquals(1, manager.getTrucks());
	}

	@Test
	public void cannot_add_a_car_if_carpark_is_full() {
		assertTrue(manager.enter('c'));
		assertTrue(manager.enter('c'));
		assertTrue(manager.enter('c'));
		assertTrue(manager.enter('c'));
		assertTrue(manager.enter('c'));
		assertFalse(manager.enter('c'));
	}

	@Test
	public void manager_can_record_car_removed() {
		manager.enter('c');
		assertTrue(manager.remove('c', 1));
		assertEquals(0, manager.getCars());
		assertEquals(2, manager.getRevenue());
	}
	
	@Test
	public void manager_can_record_truck_removed() {
		manager.enter('t');
		assertTrue(manager.remove('t', 1));
		assertEquals(0, manager.getTrucks());
		assertEquals(3, manager.getRevenue());
	}

	@Test
	public void manager_can_add_a_vehicle_larger_than_car() {
		manager.enter('t');

		assertEquals(3, manager.getAvaliableSpaces());
		assertEquals(0, manager.getCars());
		assertEquals(1, manager.getTrucks());
	}

	@Test
	public void manager_can_block_a_vehicle_too_big_from_entering_when_only_small_space_is_left() {
		assertTrue(manager.enter('t'));
		assertTrue(manager.enter('t'));
		assertFalse(manager.enter('t'));
		assertTrue(manager.enter('c'));
	}
	
	@Test
	public void can_generate_report_for_no_activity() {
		System.out.println("\n-----\n");
		String output = String.format(
				"Cars Entered: %d\nTrucks Entered: %d\nCars Exited: %d\nTrucks Exited: %d\nParking Cars: %d\nParking Trucks: %d\nSpaces available: %d\nFees paid: $%d",
				0, 0, 0, 0, 0, 0, 5, 0
		);
		String report = manager.report();
		System.out.println(report);
		assertEquals(output, report);
	}
	
	@Test
	public void can_generate_report_for_varied_activity() {
		manager.enter('c');
		manager.enter('t');
		manager.enter('t');
		manager.remove('t', 3);
		manager.enter('c');
		manager.enter('c');
		manager.remove('c', 6);
		
		System.out.println("\n-----\n");
		String output = String.format(
				"Cars Entered: %d\nTrucks Entered: %d\nCars Exited: %d\nTrucks Exited: %d\nParking Cars: %d\nParking Trucks: %d\nSpaces available: %d\nFees paid: $%d",
				3, 2, 1, 1, 2, 1, 1, 21
		);
		String report = manager.report();
		System.out.println(report);
		assertEquals(output, report);
	}
	
	@Test
	public void cannot_remove_truck_when_no_trucks_present() {
		assertFalse(manager.remove('t', 4));
	}
	
	@Test
	public void cannot_remove_car_when_no_trucks_present() {
		assertFalse(manager.remove('c', 7));
	}
}

package com.fdm.CarparkSimulation;

public class CarparkModel {

	private final int totalSpaces;
	private int revenue;
	private int[] vehicleMovements;

	public int getTotalSpaces() {
		return totalSpaces;
	}

	public int getRevenue() {
		return revenue;
	}

	private int getCarsIn() {
		return vehicleMovements[0];
	}

	private int getTrucksIn() {
		return vehicleMovements[1];
	}

	private int getCarsOut() {
		return vehicleMovements[2];
	}

	private int getTrucksOut() {
		return vehicleMovements[3];
	}

	public CarparkModel(int totalSpaces) {
		this.totalSpaces = totalSpaces;
		revenue = 0;
		vehicleMovements = new int[] { 0, 0, 0, 0 };
	}

	public int getAvaliableSpaces() {
		int result = totalSpaces;
		result -= getCars();
		result -= getTrucks() * 2;
		return result;
	}

	public boolean enter(char kind) {
		switch (kind) {
		case ('c'):
			if (getAvaliableSpaces() < 1)
				return false;
			vehicleMovements[0] += 1;
			break;
		case ('t'):
			if (getAvaliableSpaces() < 2)
				return false;
			vehicleMovements[1] += 1;
		}
		return true;
	}

	public boolean remove(char kind, int i) {
		switch (kind) {
		case ('c'):
			if (getCars() == 0)
				return false;
			revenue += i * 2;
			vehicleMovements[2] += 1;
			break;
		case ('t'):
			if (getTrucks() == 0)
				return false;
			revenue += i * 3;
			vehicleMovements[3] += 1;
		}
		return true;
	}

	public String report() {
		return String.format(
				"Cars Entered: %d\nTrucks Entered: %d\nCars Exited: %d\nTrucks Exited: %d\nParking Cars: %d\nParking Trucks: %d\nSpaces available: %d\nFees paid: $%d",
				getCarsIn(), getTrucksIn(), getCarsOut(), getTrucksOut(), getCars(), getTrucks(), getAvaliableSpaces(),
				getRevenue());
	}

	public int getCars() {
		return getCarsIn() - getCarsOut();
	}

	public int getTrucks() {
		return getTrucksIn() - getTrucksOut();
	}

}

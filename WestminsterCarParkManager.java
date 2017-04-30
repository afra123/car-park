package com.carpark.app;

import java.util.ArrayList;

import com.carpark.util.DateTime;
import com.carpark.util.Validate;

public class WestminsterCarParkManager implements CarParkManager {
	private final int NO_OF_LOTS = 20;
	private Vehicle[] carPark = new Vehicle[NO_OF_LOTS]; 
	private int noOfRemainingParkingLots = NO_OF_LOTS;
	private ArrayList<Vehicle> parkingChronology = new ArrayList<Vehicle>();
	private ArrayList<Vehicle> parkingHistory = new ArrayList<Vehicle>();

	@Override
	public void addVehicle() {
		String userInput = "";
		int parkingLot = -1;
		
		while (true) {
			userInput = Validate.getUserInputString("Please enter vehicle type: ");
			
			if ((userInput.equalsIgnoreCase(VehicleType.CAR.toString())) || (userInput.equalsIgnoreCase(VehicleType.VAN.toString())) || (userInput.equalsIgnoreCase(VehicleType.MOTORBIKE.toString()))) {
				
				String vehicleId = Validate.getUserInputString("Please enter vehicle id:\t\t");
				
				if (!checkExists(vehicleId)) {
					String vehicleBrand = Validate.getUserInputString("Please enter vehicle brand:\t\t");
					
					if (userInput.equalsIgnoreCase(VehicleType.CAR.toString())) {
						
						int carNoOfDoors = Validate.getUserInputInt("Please enter number of doors:\t\t");
						String carColor = Validate.getUserInputString("Please enter color of the vehicle:\t");
						
						parkingLot = isFreeParkingSpaceAvailable(VehicleType.CAR.toString());
						
						if (parkingLot != -1) {
							Car car = new Car(vehicleId, vehicleBrand, new DateTime(), null, carNoOfDoors, carColor);
							this.carPark[parkingLot] = car;
							this.parkingChronology.add(car);
			
							this.noOfRemainingParkingLots = this.noOfRemainingParkingLots - 1;
							
							System.out.println("\n Vehicle with id \"" + vehicleId + "\" parked in lot " + (parkingLot + 1) + ".");
							System.out.println(" There are " + this.noOfRemainingParkingLots + " free parking lot(s) available...\n");
						} else {
							System.out.println(" There is no parking space available...");
						}
						
					} else if (userInput.equalsIgnoreCase(VehicleType.VAN.toString())) {
						
						String vanCargoVolume = Validate.getUserInputString("Please enter vehicle cargo volume:\t");
						
						parkingLot = isFreeParkingSpaceAvailable(VehicleType.VAN.toString());
						
						if (parkingLot != -1) {
							Van van = new Van(vehicleId, vehicleBrand, new DateTime(), null, vanCargoVolume);
							this.carPark[parkingLot] = van;
							this.carPark[parkingLot + 1] = van;
							this.parkingChronology.add(van);
			
							this.noOfRemainingParkingLots = this.noOfRemainingParkingLots - 2;
							
							System.out.println("\n Vehicle with id \"" + vehicleId + "\" parked in lot " + (parkingLot + 1) + " and " + (parkingLot + 2) + ".");
							System.out.println(" There are " + this.noOfRemainingParkingLots + " free parking lot(s) available...\n");
						} else {
							System.out.println(" There is no parking space available...");
						}
						
					} else if (userInput.equalsIgnoreCase(VehicleType.MOTORBIKE.toString())) {
						
						String motorbikeEnginSize = Validate.getUserInputString("Please enter engin size of the vehicle:\t");
						
						parkingLot = isFreeParkingSpaceAvailable(VehicleType.MOTORBIKE.toString());
						
						if (parkingLot != -1) {
							Motorbike motorbike = new Motorbike(vehicleId, vehicleBrand, new DateTime(), null, motorbikeEnginSize);
							this.carPark[parkingLot] = motorbike;
							this.parkingChronology.add(motorbike);
			
							this.noOfRemainingParkingLots = this.noOfRemainingParkingLots - 1;
							
							System.out.println("\n Vehicle with id \"" + vehicleId + "\" parked in lot " + (parkingLot + 1) + ".");
							System.out.println(" There are " + this.noOfRemainingParkingLots + " free parking lot(s) available...\n");
						} else {
							System.out.println(" There is no parking space available...");
						}
						
					}
					
					break;
				} else {
					System.out.println("\n Vehicle with id " + vehicleId + " is already parked...\n");
					break;
				}
			} else {
				System.out.println("\n Invalid vehicle type. Please retry...\n");
			}
		}
	}

	@Override
	public void deleteVehicle() {
		String userInput = Validate.getUserInputString("Please enter vehicle id: ");
		int vehicleParkedLot = this.getVehicleParkedLot(userInput);
		
		if (vehicleParkedLot != -1) {
			this.carPark[vehicleParkedLot].setVehicleLeaveDateTime(new DateTime());
			Vehicle vehicle = this.carPark[vehicleParkedLot];
			
			String vehicleType = vehicle.getVehicleType();
			
			if (vehicleType.equalsIgnoreCase(VehicleType.VAN.toString())) {
				noOfRemainingParkingLots = (noOfRemainingParkingLots + 2);
				this.carPark[vehicleParkedLot] = null;
				this.carPark[vehicleParkedLot + 1] = null;
			} else {
				noOfRemainingParkingLots = (noOfRemainingParkingLots + 1);
				this.carPark[vehicleParkedLot] = null;
			}

			for (int i = 0; i < this.parkingChronology.size(); i++) {
				if (this.parkingChronology.get(i).getVehicleId().equalsIgnoreCase(vehicle.getVehicleId())) {
					this.parkingChronology.remove(i);
				}
			}
			
			this.parkingHistory.add(vehicle);
			
			System.out.println("\n Following vehicle leaved the car park...\n");
			vehicle.display();
			System.out.println("\nParking Charge: " + this.calculateParkingCharge(vehicle) + "£\n");
		} else {
			System.out.println("\nThere is no such vehicle (with id \"" + userInput + "\") parked in the car park...\n");
		}
	}

	@Override
	public void printCarParkStatus() {
		ArrayList<String> vehicleList = new ArrayList<String>();
		int recordCount = 0;
		
		for (int i = (this.parkingChronology.size() - 1); i >= 0; i--) {
			vehicleList.add(this.parkingChronology.get(i).toString());
			recordCount++;
		}
		
		if (recordCount > 0) {
			System.out.println("Vehicle Id - Vehicle Brand - Vehicle Type - Entry DateTime - Leave DateTime - <<Specific Attributes>>\n");
			for (int i = 0; i < vehicleList.size(); i++) {
				System.out.println(vehicleList.get(i));
			}
		} else {
			System.out.println(" No vehicles parked in the car park currently...");
		}
		
		System.out.println();
	}

	@Override
	public void printCarParkStatus(String date) {
		ArrayList<String> vehicleList = new ArrayList<String>();
		DateTime specificDate = new DateTime(date, "00:00:00");
		int recordCount = 0;
		
		for (int i = (this.parkingChronology.size() - 1); i >= 0; i--) {
			if (this.parkingChronology.get(i).getVehicleEntryDateTime().getDate().equals(specificDate.getDate())) {
				vehicleList.add(this.parkingChronology.get(i).toString());
				recordCount++;
			}
		}
		
		for (int i = (this.parkingHistory.size() - 1); i >= 0; i--) {
			if (this.parkingChronology.get(i).getVehicleEntryDateTime().getDate().equals(specificDate.getDate())) {
				vehicleList.add(this.parkingHistory.get(i).toString());
				recordCount++;
			}
		}
		
		if (recordCount > 0) {
			System.out.println("Vehicle Id - Vehicle Brand - Vehicle Type - Entry DateTime - Leave DateTime - <<Specific Attributes>>\n");
			for (int i = 0; i < vehicleList.size(); i++) {
				System.out.println(vehicleList.get(i));
			}
		} else {
			System.out.println(" No vehicles parked on " + date + "...");
		}
		
		System.out.println();
	}

	@Override
	public void printCarParkStats() {
		int noOfCars = 0;
		int noOfVans = 0;
		int noOfMotorBikes = 0;
		
		for (int i = 0; i < this.carPark.length; i++) {
			if (this.carPark[i] != null) {
				if (this.carPark[i].getVehicleType().equalsIgnoreCase(VehicleType.CAR.toString())) {
					noOfCars++;
				} else if (this.carPark[i].getVehicleType().equalsIgnoreCase(VehicleType.VAN.toString())) {
					noOfVans++;
				} else if (this.carPark[i].getVehicleType().equalsIgnoreCase(VehicleType.MOTORBIKE.toString())) {
					noOfMotorBikes++;
				}
			}
		}
		
		double percentageCars = (((double)noOfCars / (double)20) * 100);
		double percentageVans = (((double)noOfVans / (double)20) * 100);
		double percentageMotorBikes = (((double)noOfMotorBikes / (double)20) * 100);
		
		System.out.println("\nWestminster Car Park Stats");
		System.out.println("Percentage of Cars:\t\t" + percentageCars + "%");
		System.out.println("Percentage of Vans:\t\t" + percentageVans + "%");
		System.out.println("Percentage of Motor Bikes:\t" + percentageMotorBikes + "%");
	}

	@Override
	public void printLongestParkedVehicle() {
		DateTime currentDateTime = new DateTime();
		int longestTimeDiff = 0;
		Vehicle longestParkedVehicle = null;
		
		for (int i = 0; i < this.carPark.length; i++) {
			if (this.carPark[i] != null) {
				DateTime entryTime = this.carPark[i].getVehicleEntryDateTime();
				int tempTimeDiff = DateTime.getNumberOfMilliSecondssBetweenTwoDates(entryTime, currentDateTime);
				
				if (longestTimeDiff < tempTimeDiff) {
					longestTimeDiff = tempTimeDiff;
					longestParkedVehicle = this.carPark[i];
				}
			}
		}
		
		if (longestParkedVehicle != null) {
			System.out.println("\nLongest Parked Vehicle:");
			System.out.println("Vehicle Id - Vehicle Brand - Vehicle Type - Entry DateTime - Leave DateTime - <<Specific Attributes>>\n");
			System.out.println(longestParkedVehicle.toString());
		} else {
			System.out.println(" Car Park is empty...");
		}
		
		System.out.println();
	}

	@Override
	public void printLastVehicleParked() {
		if (this.parkingChronology.size() > 0) {
			Vehicle lastParkedVehicle = this.parkingChronology.get(this.parkingChronology.size() - 1);
			System.out.println("\nLast Parked Vehicle:");
			System.out.println("Vehicle Id - Vehicle Brand - Vehicle Type - Entry DateTime - Leave DateTime - <<Specific Attributes>>\n");
			System.out.println(lastParkedVehicle.toString());
		} else {
			System.out.println(" Car Park is empty...");
		}
		
		System.out.println();
	}

	@Override
	public int isFreeParkingSpaceAvailable(String vehicleType) {
		int neededLotSize = 0;
		boolean isParkingSpaceAvailable = false;
		int availableLotId = -1;
		
		if (vehicleType.equalsIgnoreCase(VehicleType.CAR.toString())) {
			neededLotSize = CarParkManager.CAR_LOT;
		} else if (vehicleType.equalsIgnoreCase(VehicleType.VAN.toString())) {
			neededLotSize = CarParkManager.VAN_LOT;
		} else if (vehicleType.equalsIgnoreCase(VehicleType.MOTORBIKE.toString())) {
			neededLotSize = CarParkManager.MOTORBIKE_LOT;
		} else {
			neededLotSize = CarParkManager.CAR_LOT;
		}
		
		for (int i = 0; i < carPark.length; i++) {
			if (!isParkingSpaceAvailable) {
				if (carPark[i] == null) {
					if (neededLotSize == CarParkManager.VAN_LOT) {
						if (i < (carPark.length - 1)) {
							if (carPark[i + 1] == null) {
								isParkingSpaceAvailable = true;
								availableLotId = i;
								break;
							}
						}
					} else {
						isParkingSpaceAvailable = true;
						availableLotId = i;
						break;
					}
				}
			} else {
				break;
			}
		}
		
		return availableLotId;
	}

	@Override
	public int calculateParkingCharge(Vehicle vehicle) {
		int charge = 0;
		
		int totalNoOfParkedHours = DateTime.getNumberOfHoursBetweenTwoDates(vehicle.getVehicleEntryDateTime(), vehicle.getVehicleLeaveDateTime());
		int noOfDaysParked = (totalNoOfParkedHours / 24);
		int noOfHoursParked = (totalNoOfParkedHours % 24);
		
		charge = (noOfDaysParked * 30);
		
		if (noOfHoursParked > 0) {
			charge = (charge + 3);
			noOfHoursParked = (noOfHoursParked - 3);
		}
		
		if (noOfHoursParked > 0) {
			if (vehicle.getVehicleType().equalsIgnoreCase(VehicleType.VAN.toString())) {
				charge = ((charge + noOfHoursParked) * 2);
			} else {
				charge = (charge + noOfHoursParked);
			}
		}
		
		return charge;
	}

	@Override
	public int getVehicleParkedLot(String vehicleId) {
		for (int i = 0; i < this.carPark.length; i++) {
			if (this.carPark[i] != null) {
				if (this.carPark[i].getVehicleId().equals(vehicleId)) {
					return i;
				}
			}
		}
		return -1;
	}
	
	@Override
	public boolean checkExists(String vehicleId) {
		for (int i = 0; i < this.carPark.length; i++) {
			if (this.carPark[i] != null) {
				if (this.carPark[i].getVehicleId().equalsIgnoreCase(vehicleId)) {
					return true;
				}
			}
		}
		return false;
	}
	
	public static void main(String[] args) {
		int userChoice = -1;
		WestminsterCarParkManager westminsterCarParkManager = new WestminsterCarParkManager();
		
		// Initialize car park with null. Null means the lot is empty
		for (int i = 0; i < westminsterCarParkManager.carPark.length; i++) {
			westminsterCarParkManager.carPark[i] = null;
		}

		System.out.println("Welcome to Westminster Car Park");
		
		System.out.println("Notes: Press 'e' or 'q' at anytime to exit the application \n");
		
		while (true) {
			System.out.println("-----------------------------------------------------------");
			System.out.println("1. Add Vehicle");
			System.out.println("2. Delete Vehicle");
			System.out.println("3. Display Vehicles Currently Parked");
			System.out.println("4. Display Vehicles Parked Specific Date");
			System.out.println("5. Display Car Park Stats (Percentage of Each Vehicle Type)");
			System.out.println("6. Display Last and Longest Parked Vehicles");
			System.out.println("-----------------------------------------------------------\n");
			
			userChoice = Validate.getUserInputInt("Please enter your choice: ", 1, 6);
			
			switch (userChoice) {
			case 1:
				westminsterCarParkManager.addVehicle();
				break;
			case 2:
				westminsterCarParkManager.deleteVehicle();
				break;
			case 3:
				westminsterCarParkManager.printCarParkStatus();
				break;
			case 4:
				int year = Validate.getUserInputInt("Please enter year:\t", 1, 9999);
				int month = Validate.getUserInputInt("Please enter month:\t", 1, 12);
				int day = Validate.getUserInputInt("Please enter day:\t", 1, 31);
				
				String specificDate = (year + "/" + month + "/" + day);
				
				westminsterCarParkManager.printCarParkStatus(specificDate);
				break;
			case 5:
				westminsterCarParkManager.printCarParkStats();
				break;
			case 6:
				westminsterCarParkManager.printLongestParkedVehicle();
				westminsterCarParkManager.printLastVehicleParked();
				break;
				
			default:
				break;
			}
		}
	}
}

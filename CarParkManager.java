package com.carpark.app;

public interface CarParkManager {
	public static final int CAR_LOT = 1;
	public static final int VAN_LOT = 2;
	public static final int MOTORBIKE_LOT = 1;
	
	public void addVehicle();
	public void deleteVehicle();
	public void printCarParkStatus();
	public void printCarParkStatus(String date);
	public void printCarParkStats();
	public void printLongestParkedVehicle();
	public void printLastVehicleParked();
	
	public int isFreeParkingSpaceAvailable(String vehicleType);
	public int calculateParkingCharge(Vehicle vehicle);
	public int getVehicleParkedLot(String vehicleId);
	public boolean checkExists(String vehicleId);
}

package com.carpark.app;

import com.carpark.util.DateTime;

public class Car extends Vehicle {
	// Instance variables
	private int carNoOfDoors;
	private String carColor;

	// Constructors
	public Car() {
		this("", "", new DateTime(), null, 0, "");
	}
	
	public Car(String vehicleId, String vehicleBrand, DateTime vehicleEntryDateTime, DateTime vehicleLeaveDateTime,
			int carNoOfDoors, String carColor) {
		super(vehicleId, vehicleBrand, VehicleType.CAR.toString(), vehicleEntryDateTime, vehicleLeaveDateTime);
		this.carNoOfDoors = carNoOfDoors;
		this.carColor = carColor;
	}
	
	// Getters and setters
	public int getCarNoOfDoors() {
		return carNoOfDoors;
	}
	
	public void setCarNoOfDoors(int carNoOfDoors) {
		this.carNoOfDoors = carNoOfDoors;
	}
	
	public String getCarColor() {
		return carColor;
	}
	
	public void setCarColor(String carColor) {
		this.carColor = carColor;
	}
	

	@Override
	public void display() {
		System.out.println("Vehicle Id:\t\t\t" + this.getVehicleId());
		System.out.println("Vehicle Brand:\t\t\t" + this.getVehicleBrand());
		System.out.println("Vehicle Type:\t\t\t" + this.getVehicleType());
		if (this.getVehicleEntryDateTime() != null) {
			System.out.println("Vehicle Entry DateTime:\t\t" + this.getVehicleEntryDateTime().toString());
		}
		if (this.getVehicleLeaveDateTime() != null) {
			System.out.println("Vehicle Leave DateTime:\t\t" + this.getVehicleLeaveDateTime().toString());
		}
		System.out.println("No. of Doors of Vehicle:\t" + this.carNoOfDoors);
		System.out.println("Vehicle Color:\t\t\t" + this.carColor);
	}
	
	@Override
	public String toString() {
		String vehicleEntryDateTime = "N/A";
		String vehicleLeaveDateTime = "N/A";
		if (this.getVehicleEntryDateTime() != null) {
			vehicleEntryDateTime = this.getVehicleEntryDateTime().toString();
		}
		if (this.getVehicleLeaveDateTime() != null) {
			vehicleLeaveDateTime = this.getVehicleLeaveDateTime().toString();
		}
		return (this.getVehicleId() + " - " + this.getVehicleBrand() + " - " + this.getVehicleType() + " - " + vehicleEntryDateTime + " - " + vehicleLeaveDateTime + " - " + this.carNoOfDoors + " - " + this.carColor);
	}
}

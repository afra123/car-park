package com.carpark.app;

import com.carpark.util.DateTime;

public class Motorbike extends Vehicle {
	// Instance variables
	private String motorbikeEnginSize;

	// Constructors
	public Motorbike() {
		this("", "", new DateTime(), null, "");
	}
	
	public Motorbike(String vehicleId, String vehicleBrand, DateTime vehicleEntryDateTime,
			DateTime vehicleLeaveDateTime, String motorbikeEnginSize) {
		super(vehicleId, vehicleBrand, VehicleType.MOTORBIKE.toString(), vehicleEntryDateTime, vehicleLeaveDateTime);
		this.motorbikeEnginSize = motorbikeEnginSize;
	}
	
	// Getters and setters
	public void setMotorbikeEnginSize(String motorbikeEnginSize) {
		this.motorbikeEnginSize = motorbikeEnginSize;
	}
	
	public String getMotorbikeEnginSize() {
		return motorbikeEnginSize;
	}

	@Override
	public void display() {
		System.out.println("Vehicle Id:\t\t" + this.getVehicleId());
		System.out.println("Vehicle Brand:\t\t" + this.getVehicleBrand());
		System.out.println("Vehicle Type:\t\t" + this.getVehicleType());
		if (this.getVehicleEntryDateTime() != null) {
			System.out.println("Vehicle Entry DateTime:\t" + this.getVehicleEntryDateTime().toString());
		}
		if (this.getVehicleLeaveDateTime() != null) {
			System.out.println("Vehicle Leave DateTime:\t" + this.getVehicleLeaveDateTime().toString());
		}
		System.out.println("Vehicle Engin Size:\t" + this.motorbikeEnginSize);
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
		return (this.getVehicleId() + " - " + this.getVehicleBrand() + " - " + this.getVehicleType() + " - " + vehicleEntryDateTime + " - " + vehicleLeaveDateTime + " - " + this.motorbikeEnginSize);
	}
	
}

package com.carpark.app;

import com.carpark.util.DateTime;

public class Van extends Vehicle {
	// Instance variables
	private String vanCargoVolume;
	
	// Constructors
	public Van() {
		this("", "", new DateTime(), null, "");
	}
	
	public Van(String vehicleId, String vehicleBrand, DateTime vehicleEntryDateTime, DateTime vehicleLeaveDateTime,
			String vanCargoVolume) {
		super(vehicleId, vehicleBrand, VehicleType.VAN.toString(), vehicleEntryDateTime, vehicleLeaveDateTime);
		this.vanCargoVolume = vanCargoVolume;
	}
	
	// Getters and setters
	public String getVanCargoVolume() {
		return vanCargoVolume;
	}

	public void setVanCargoVolume(String vanCargoVolume) {
		this.vanCargoVolume = vanCargoVolume;
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
		System.out.println("Vehicle Cargo Volume:\t" + this.vanCargoVolume);
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
		return (this.getVehicleId() + " - " + this.getVehicleBrand() + " - " + this.getVehicleType() + " - " + vehicleEntryDateTime + " - " + vehicleLeaveDateTime + " - " + this.vanCargoVolume);
	}
}

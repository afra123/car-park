package com.carpark.app;

import com.carpark.util.DateTime;

public abstract class Vehicle {
	// Instance variables
	private String vehicleId;
	private String vehicleBrand;
	private String vehicleType;
	private DateTime vehicleEntryDateTime;
	private DateTime vehicleLeaveDateTime;

	// Constructors
//	public Vehicle() {
//		this("", "", "", new DateTime(), null);
//	}

	public Vehicle(String vehicleId, String vehicleBrand, String vehicleType, DateTime vehicleEntryDateTime,
			DateTime vehicleLeaveDateTime) {
		this.vehicleId = vehicleId;
		this.vehicleBrand = vehicleBrand;
		this.vehicleType = vehicleType;
		this.vehicleEntryDateTime = vehicleEntryDateTime;
		this.vehicleLeaveDateTime = vehicleLeaveDateTime;
	}

	// Getters and setters
	public String getVehicleId() {
		return vehicleId;
	}
	
	public void setVehicleId(String vehicleId) {
		this.vehicleId = vehicleId;
	}
	
	public String getVehicleBrand() {
		return vehicleBrand;
	}
	
	public void setVehicleBrand(String vehicleBrand) {
		this.vehicleBrand = vehicleBrand;
	}

	public String getVehicleType() {
		return vehicleType;
	}

	public void setVehicleType(String vehicleType) {
		this.vehicleType = vehicleType;
	}
	
	public DateTime getVehicleEntryDateTime() {
		return vehicleEntryDateTime;
	}
	
	public void setVehicleEntryDateTime(DateTime vehicleEntryDateTime) {
		this.vehicleEntryDateTime = vehicleEntryDateTime;
	}
	
	public DateTime getVehicleLeaveDateTime() {
		return vehicleLeaveDateTime;
	}
	
	public void setVehicleLeaveDateTime(DateTime vehicleLeaveDateTime) {
		this.vehicleLeaveDateTime = vehicleLeaveDateTime;
	}
	
	public abstract void display();
}

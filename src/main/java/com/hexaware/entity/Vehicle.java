package com.hexaware.entity;

import com.hexaware.constant.Constants;

public class Vehicle {
    private int vehicleID;
    private String make;
    private String model;
    private int year;
    private double dailyRate;
    private String status;
    private int passengerCapacity;
    private int engineCapacity;

    public Vehicle() {

    }

    public Vehicle(int vehicleID, String make, String model, int year, double dailyRate,
                   String status, int passengerCapacity, int engineCapacity) {
        this.vehicleID = vehicleID;
        this.make = make;
        this.model = model;
        this.year = year;
        this.dailyRate = dailyRate;
        this.status = status;
        this.passengerCapacity = passengerCapacity;
        this.engineCapacity = engineCapacity;
    }

    public Vehicle(String make, String model, int year, double dailyRate, String status, int passengerCapacity, int engineCapacity) {
        this.make = make;
        this.model = model;
        this.year = year;
        this.dailyRate = dailyRate;
        this.status = status;
        this.passengerCapacity = passengerCapacity;
        this.engineCapacity = engineCapacity;
    }

    public int getVehicleID() {
        return vehicleID;
    }

    public String getMake() {
        return make;
    }

    public String getModel() {
        return model;
    }

    public int getYear() {
        return year;
    }

    public double getDailyRate() {
        return dailyRate;
    }

    public String getStatus() {
        return status;
    }

    public int getPassengerCapacity() {
        return passengerCapacity;
    }

    public int getEngineCapacity() {
        return engineCapacity;
    }

    public String toString() {
        return Constants.SQUARE_BRACKET_OPEN + vehicleID +
                Constants.SEPARATOR + make + Constants.SEPARATOR +
                model + Constants.SEPARATOR + year + Constants.SEPARATOR +
                dailyRate + Constants.SEPARATOR +
                status + Constants.SEPARATOR + passengerCapacity +
                Constants.SEPARATOR + engineCapacity + Constants.SQUARE_BRACKET_CLOSE;
    }
}

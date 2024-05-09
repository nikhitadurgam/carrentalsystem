package com.hexaware.entity;

import com.hexaware.constant.Constants;

public class Lease {
    private int leaseID;
    private int vehicleID;
    private int customerID;
    private String startDate;
    private String endDate;
    private String type;

    public Lease() {
    }

    public Lease(int leaseID) {
        this.leaseID = leaseID;
    }

    public Lease(int vehicleID, int customerID, String startDate, String endDate, String type) {
        this.vehicleID = vehicleID;
        this.customerID = customerID;
        this.startDate = startDate;
        this.endDate = endDate;
        this.type = type;
    }

    public Lease(int leaseID, int vehicleID, int customerID, String startDate, String endDate,
                 String type) {
        this.leaseID = leaseID;
        this.vehicleID = vehicleID;
        this.customerID = customerID;
        this.startDate = startDate;
        this.endDate = endDate;
        this.type = type;
    }

    public int getLeaseID() {
        return leaseID;
    }

    public void setLeaseID(int leaseID) {
        this.leaseID = leaseID;
    }

    public int getVehicleID() {
        return vehicleID;
    }

    public void setVehicleID(int vehicleID) {
        this.vehicleID = vehicleID;
    }

    public int getCustomerID() {
        return customerID;
    }

    public void setCustomerID(int customerID) {
        this.customerID = customerID;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }


    public String toString() {
        return Constants.SQUARE_BRACKET_OPEN +leaseID + Constants.SEPARATOR +
                vehicleID + Constants.SEPARATOR + customerID + Constants.SEPARATOR +
                startDate + Constants.SEPARATOR + endDate + Constants.SEPARATOR + type + Constants.SQUARE_BRACKET_CLOSE;
    }
}

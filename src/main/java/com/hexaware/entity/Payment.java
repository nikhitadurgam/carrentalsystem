package com.hexaware.entity;
import com.hexaware.constant.Constants;

import java.util.Date;

public class Payment {
    private int paymentID;
    private int leaseID;
    private Date paymentDate;
    private double amount;

    public Payment() {
    }

    public Payment(int leaseID, Date paymentDate, double amount) {
        this.leaseID = leaseID;
        this.paymentDate = paymentDate;
        this.amount = amount;
    }

    public Payment(int paymentID, int leaseID, Date paymentDate, double amount) {
        this.paymentID = paymentID;
        this.leaseID = leaseID;
        this.paymentDate = paymentDate;
        this.amount = amount;
    }

    public int getPaymentID() {
        return paymentID;
    }

    public void setPaymentID(int paymentID) {
        this.paymentID = paymentID;
    }

    public int getLeaseID() {
        return leaseID;
    }

    public void setLeaseID(int leaseID) {
        this.leaseID = leaseID;
    }

    public Date getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(Date paymentDate) {
        this.paymentDate = paymentDate;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }


    public String toString() {
        return Constants.SQUARE_BRACKET_OPEN + paymentID +
                Constants.SEPARATOR + leaseID + Constants.SEPARATOR +
                paymentDate + Constants.SEPARATOR + amount  + Constants.SQUARE_BRACKET_CLOSE;
    }

}

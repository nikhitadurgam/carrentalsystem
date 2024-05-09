package com.hexaware;

import com.hexaware.dao.CarLeaseRepositoryImpl;
import com.hexaware.entity.Customer;
import com.hexaware.entity.Lease;
import com.hexaware.entity.Vehicle;
import com.hexaware.exception.CarNotFoundException;
import com.hexaware.exception.CustomerNotFoundException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


public class CarRentalApp {
    public static void main(String[] args) throws IOException {
        var br = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Select an option from the below menu: ");
        System.out.println("1. Car Management");
        System.out.println("2. Customer Management");
        System.out.println("3. Lease Management");
        System.out.println("4. Payment Handling");
        System.out.println("Enter your choice: ");
        var choice = Integer.parseInt(br.readLine());

        switch (choice) {
            case 1 -> {
                System.out.println("Car Management");
                System.out.println("1. Add Car: ");
                System.out.println("2. Remove Car: ");
                System.out.println("3. List of Available Cars: ");
                System.out.println("4. List of Rented Cars: ");
                System.out.println("5. Get car by carID: ");
                System.out.println("Enter your choice: ");
                var innerChoice = Integer.parseInt(br.readLine());
                switch (innerChoice) {
                     case 1 -> {
                         var carLeaseRepoObj = new CarLeaseRepositoryImpl();
                         System.out.println("To insert a new car you must enter the following " +
                                 "details: ");
                         System.out.println("Enter make of the vehicle: ");
                         var make = br.readLine();

                         System.out.println("Enter model of the vehicle: ");
                         var model = br.readLine();

                         System.out.println("Enter year of the vehicle: ");
                         var year = Integer.parseInt(br.readLine());

                         System.out.println("Enter daily rate of the vehicle: ");
                         var dailyRate = Double.parseDouble(br.readLine());

                         System.out.println("Enter status of the vehicle: ");
                         var status = br.readLine();

                         System.out.println("Enter passenger capacity of the vehicle: ");
                         var passengerCapacity = Integer.parseInt(br.readLine());

                         System.out.println("Enter engine capacity of the vehicle: ");
                         var engineCapacity = Integer.parseInt(br.readLine());

                         carLeaseRepoObj.addCar(new Vehicle(make, model, year, dailyRate, status,
                          passengerCapacity, engineCapacity));
                     }

                     case 2 -> {
                         var carLeaseRepoObj = new CarLeaseRepositoryImpl();
                         System.out.println("Please enter the vehicle id you want to remove: ");
                         var vehicleID = Integer.parseInt(br.readLine());
                         try {
                             carLeaseRepoObj.removeCar(vehicleID);
                             System.out.println("Vehicle removed successfully");
                         } catch (CarNotFoundException e) {
                             throw new RuntimeException(e);
                         }
                     }

                     case 3 -> {
                         var carLeaseRepoObj = new CarLeaseRepositoryImpl();
                         System.out.println("List of Available Cars");
                         var availableCars = carLeaseRepoObj.listAvailableCars();
                         for (Vehicle car : availableCars) {
                             System.out.println(car);
                         }
                     }

                     case 4 -> {
                         var carLeaseRepoObj = new CarLeaseRepositoryImpl();
                         System.out.println("List of Rented Cars: ");
                         var rentedCars = carLeaseRepoObj.listRentedCars();
                         for (Vehicle car : rentedCars) {
                             System.out.println(car);
                         }
                     }

                     case 5 -> {
                         System.out.println("Please enter the car ID: ");
                         var carID = Integer.parseInt(br.readLine());

                         var carLeaseRepoObj = new CarLeaseRepositoryImpl();
                         try {
                             var car = carLeaseRepoObj.findCarById(carID);
                             System.out.println(car);
                         } catch (CarNotFoundException e) {
                             throw new RuntimeException(e);
                         }
                     }

                    default -> System.out.println("Invalid choice");
                }

            }

            case 2 -> {
                System.out.println("Customer Management Menu: ");
                System.out.println("1. Add Customer: ");
                System.out.println("2. Remove Customer: ");
                System.out.println("3. List of Customers: ");
                System.out.println("4. Get car by customerID: ");

                System.out.println("Enter your choice: ");
                var innerChoice = Integer.parseInt(br.readLine());

                switch (innerChoice) {
                    case 1 -> {
                        System.out.println("Enter the first name of the customer: ");
                        var firstName = br.readLine();

                        System.out.println("Enter the last name of the customer: ");
                        var lastName = br.readLine();

                        System.out.println("Enter the email of the customer: ");
                        var email = br.readLine();

                        System.out.println("Enter the phone number of the customer: ");
                        var phoneNumber = br.readLine();

                        var carLeaseRepoObj = new CarLeaseRepositoryImpl();
                        carLeaseRepoObj.addCustomer(new Customer(firstName, lastName, email, phoneNumber));
                    }

                    case 2 -> {
                        System.out.println("Enter the customer id you want to remove: ");
                        var customerID = Integer.parseInt(br.readLine());

                        var carLeaseRepoObj = new CarLeaseRepositoryImpl();
                        try {
                            carLeaseRepoObj.removeCustomer(customerID);
                        } catch (CustomerNotFoundException e) {
                            throw new RuntimeException(e);
                        }
                    }

                    case 3 -> {
                        System.out.println("List of Customers: ");
                        var carLeaseRepoObj = new CarLeaseRepositoryImpl();
                        var customers = carLeaseRepoObj.listCustomers();
                        for (Customer customer: customers) {
                            System.out.println(customer);
                        }
                    }

                    case 4 -> {
                        System.out.println("Please enter the customerID: ");
                        var customerID = Integer.parseInt(br.readLine());

                        var carLeaseRepoObj = new CarLeaseRepositoryImpl();
                        try {
                            var customer = carLeaseRepoObj.findCustomerById(customerID);
                            System.out.println(customer);
                        } catch (CustomerNotFoundException e) {
                            throw new RuntimeException(e);
                        }

                    }

                    default -> System.out.println("Invalid choice");
                }
            }

            case 3 -> {
                System.out.println("Lease Management");
                System.out.println("1. Create Lease: ");
                System.out.println("2. Return Lease: ");
                System.out.println("3. List of Active Leases: ");
                System.out.println("4.List of history of Leases: ");

                System.out.println("Enter your choice: ");
                var innerChoice = Integer.parseInt(br.readLine());

                switch (innerChoice) {
                    case 1 -> {
                        System.out.println("To create Lease please enter the following details: ");

                        System.out.println("Enter vehicle id: ");
                        var vehicleID = Integer.parseInt(br.readLine());

                        System.out.println("Enter customer id: ");
                        var customerID = Integer.parseInt(br.readLine());

                        System.out.println("Enter start date of lease in YYYY-MM-DD format:");
                        var startDate = br.readLine();

                        System.out.println("Enter end date of lease in YYYY-MM-DD format:");
                        var endDate = br.readLine();

                        System.out.println("Enter the type of lease (MonthlyLease/ DailyLease): ");
                        var type = br.readLine();

                        var carLeaseRepoObj = new CarLeaseRepositoryImpl();
                        carLeaseRepoObj.createLease(customerID, vehicleID, startDate, endDate);
                    }

                    case 2 -> {
                        System.out.println("Please enter the lease id to return the lease: ");
                        var leaseID = Integer.parseInt(br.readLine());

                        var carLeaseRepoObj = new CarLeaseRepositoryImpl();
                        carLeaseRepoObj.returnCar(leaseID);
                        System.out.println("Returned successfully");
                    }

                    case 3 -> {
                        System.out.println("List of Active Leases: ");
                        var carLeaseRepoObj = new CarLeaseRepositoryImpl();
                        var activeLeases = carLeaseRepoObj.listActiveLeases();

                        for (Lease activeLease: activeLeases) {
                            System.out.println(activeLease);
                        }
                    }

                    case 4 -> {
                        System.out.println("List of history of Leases: ");
                        var carLeaseRepoObj = new CarLeaseRepositoryImpl();

                        var leaseHistory = carLeaseRepoObj.listLeaseHistory();
                        for (Lease lease: leaseHistory) {
                            System.out.println(lease);
                        }
                    }

                    default -> System.out.println("Invalid choice");
                }
            }

            case 4 -> {
                System.out.println("Payment Handling");
                System.out.println("Record payment");
                System.out.println("Please enter the lease id: ");
                var leaseID = Integer.parseInt(br.readLine());

                System.out.println("Enter the amount: ");
                var amount = Double.parseDouble(br.readLine());

                var carLeaseRepoObj = new CarLeaseRepositoryImpl();
                carLeaseRepoObj.recordPayment(new Lease(leaseID), amount);
            }

            default -> System.out.println("Invalid choice, enter a valid choice");
        }
    }
}
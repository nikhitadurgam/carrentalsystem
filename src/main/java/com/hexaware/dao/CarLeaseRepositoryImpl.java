package com.hexaware.dao;

import com.hexaware.date.util.DateUtil;
import com.hexaware.entity.Customer;
import com.hexaware.entity.Lease;
import com.hexaware.entity.Vehicle;
import com.hexaware.exception.CarNotFoundException;
import com.hexaware.exception.CustomerNotFoundException;
import com.hexaware.exception.LeaseNotFoundException;
import com.hexaware.util.DBConnUtil;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CarLeaseRepositoryImpl implements ICarLeaseRepository{
    // Car Management
    private final String SELECT_VEHICLE_BY_ID = "SELECT * FROM VEHICLE WHERE vehicle_id = ?";
    private final String SELECT_VEHICLE_BY_AVAILABILITY = "SELECT * FROM VEHICLE WHERE status = ?";
    private final String DELETE_VEHICLE_BY_ID = "DELETE FROM VEHICLE WHERE vehicle_id = ?";
    private final String INSERT_VEHICLE = "INSERT INTO vehicle " + "(make, model, year, daily_rate, status, " +
            "passenger_capacity, engine_capacity) " +
            "VALUES " +
            "(?, ?, ?, ?, ?, ?, ?)";

    // Customer Management
    private final String SELECT_CUSTOMER = "SELECT * FROM CUSTOMER";
    private final String SELECT_CUSTOMER_BY_ID = "SELECT * FROM CUSTOMER WHERE customer_id = ?";
    private final String INSERT_CUSTOMER = "INSERT INTO customer (first_name, last_name, email, phone_number) " +
            "VALUES " +
            "(?, ?, ?, ?)";
    private final String DELETE_CUSTOMER_BY_ID = "DELETE FROM CUSTOMER WHERE customer_id = ?";

    // Lease Management
    private final String SELECT_LEASE = "SELECT * FROM LEASE";
    private final String SELECT_LEASE_BY_ID = "SELECT * FROM LEASE WHERE lease_id = ?";
    private final String SELECT_ACTIVE_LEASES = "SELECT * FROM LEASE WHERE end_date > ?";
    private final String INSERT_LEASE = "INSERT INTO lease (vehicle_id, customer_id, start_date, end_date, type) " +
            "VALUES " +
            "(?, ?, ?, ?, ?)";
    private final String GET_LEASE_FROM_CUSTOMER_ID_AND_VEHICLE_ID = "SELECT lease_id, type FROM " +
            "LEASE WHERE customer_id = ? AND vehicle_id = ?";
    private final String DELETE_LEASE_BY_ID = "DELETE FROM LEASE WHERE lease_id = ?";

    // Payment Handling
    private final String RECORD_PAYMENT = "INSERT INTO payment (lease_id, payment_date, amount) " +
                                          "VALUES " +
                                         "(?, ?, ?)";

    // Car Management
    @Override
    public void addCar(Vehicle car) {
         try(var con = DBConnUtil.getConnection()) {
            var preparedStatement = con.prepareStatement(INSERT_VEHICLE);

            var make = car.getMake();
            preparedStatement.setString(1, make);

            var model = car.getModel();
            preparedStatement.setString(2, model);

            var year = car.getYear();
            preparedStatement.setInt(3, year);

            var dailyRate = car.getDailyRate();
            preparedStatement.setDouble(4, dailyRate);

            var status = car.getStatus();
             preparedStatement.setString(5, status);

            var passengerCapacity = car.getPassengerCapacity();
             preparedStatement.setInt(6, passengerCapacity);

            var engineCapacity = car.getEngineCapacity();
            preparedStatement.setInt(7, engineCapacity);

            var success = preparedStatement.execute();
            if (success) System.out.println("Inserted successfully");

         } catch (SQLException e) {
             throw new RuntimeException(e);
         } catch (Exception e) {
             throw new RuntimeException(e);
         }
    }

    @Override
    public void removeCar(int carID) throws CarNotFoundException{
          try (var con = new DBConnUtil().getConnection()) {
              var preparedStatement = con.prepareStatement(DELETE_VEHICLE_BY_ID);
              preparedStatement.setInt(1, carID);
              var success = preparedStatement.execute();
              if (!success) {
                  throw new CarNotFoundException("Car not found");
              } else {
                  System.out.println("Deleted successfully");
              }
          } catch (SQLException e) {
              throw new RuntimeException(e);
          } catch (Exception e) {
              throw new RuntimeException(e);
          }
    }

    @Override
    public List<Vehicle> listAvailableCars() {
        try (var con = DBConnUtil.getConnection()) {
            var preparedStatement = con.prepareStatement(SELECT_VEHICLE_BY_AVAILABILITY);
            preparedStatement.setString(1, "available");

            ResultSet rs = preparedStatement.executeQuery();

            List<Vehicle> cars = new ArrayList<>();
            while (rs.next()) {
               var carID = rs.getInt("vehicle_id");
               var make = rs.getString("make");
               var model = rs.getString("model");
               var year = rs.getInt("year");
               var dailyRate = rs.getDouble("daily_rate");
               var status = rs.getString("status");
               var passengerCapacity = rs.getInt("passenger_capacity");
               var engineCapacity = rs.getInt("engine_capacity");

               var car = new Vehicle(carID, make, model, year, dailyRate, status,
                       passengerCapacity,
                       engineCapacity);
               cars.add(car);
           }
           return cars;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Vehicle> listRentedCars() {
        try (var con = DBConnUtil.getConnection()) {
            var preparedStatement = con.prepareStatement(SELECT_VEHICLE_BY_AVAILABILITY);
            preparedStatement.setString(1, "notAvailable");
            ResultSet rs = preparedStatement.executeQuery();
            List<Vehicle> cars = new ArrayList<>();
            while (rs.next()) {
                var carID = rs.getInt("vehicle_id");
                var make = rs.getString("make");
                var model = rs.getString("model");
                var year = rs.getInt("year");
                var dailyRate = rs.getDouble("daily_rate");
                var status = rs.getString("status");
                var passengerCapacity = rs.getInt("passenger_capacity");
                var engineCapacity = rs.getInt("engine_capacity");
                var car = new Vehicle(carID, make, model, year, dailyRate, status,
                        passengerCapacity,
                        engineCapacity);
                cars.add(car);
            }
            return cars;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Vehicle findCarById(int carID) throws CarNotFoundException {
        try (var con = DBConnUtil.getConnection()) {
            var preparedStatement = con.prepareStatement(SELECT_VEHICLE_BY_ID);
            preparedStatement.setInt(1, carID);
            ResultSet rs = preparedStatement.executeQuery();
            Vehicle car = null;
            while (rs.next()) {
                var make = rs.getString("make");
                var model = rs.getString("model");
                var year = rs.getInt("year");
                var dailyRate = rs.getDouble("daily_rate");
                var status = rs.getString("status");
                var passengerCapacity = rs.getInt("passenger_capacity");
                var engineCapacity = rs.getInt("engine_capacity");
                car = new Vehicle(carID, make, model, year, dailyRate, status,
                        passengerCapacity,
                        engineCapacity);
            }
            return car;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (Exception e) {
            throw new CarNotFoundException("Car not found: " + e.getMessage());
        }
    }

    // Customer Management
    @Override
    public void addCustomer(Customer customer) {
         try(var con = DBConnUtil.getConnection()) {
             var preparedStatement = con.prepareStatement(INSERT_CUSTOMER);
             preparedStatement.setString(1, customer.getFirstName());
             preparedStatement.setString(2, customer.getLastName());
             preparedStatement.setString(3, customer.getEmail());
             preparedStatement.setString(4, customer.getPhoneNumber());
             var success = preparedStatement.execute();
         } catch (SQLException e) {
             throw new RuntimeException(e);
         } catch (Exception e) {
             throw new RuntimeException(e);
         }
    }

    @Override
    public void removeCustomer(int customerID) throws CustomerNotFoundException{
         try(var con = DBConnUtil.getConnection()) {
             var preparedStatement = con.prepareStatement(DELETE_CUSTOMER_BY_ID);
             preparedStatement.setInt(1, customerID);
             var success = preparedStatement.execute();
         } catch (SQLException e) {
             throw new RuntimeException(e);
         } catch (Exception e) {
             throw new RuntimeException(e);
         }
    }

    @Override
    public List<Customer> listCustomers() {
        List<Customer> customers = new ArrayList<>();

        try(var con = DBConnUtil.getConnection()) {
            PreparedStatement preparedStatement = con.prepareStatement(SELECT_CUSTOMER);
           ResultSet rs = preparedStatement.executeQuery();
           while (rs.next()) {
               int customerID = rs.getInt("customer_id");
               String firstName = rs.getString("first_name");
               String lastName = rs.getString("last_name");
               String email = rs.getString("email");
               String phoneNumber = rs.getString("phone_number");
               customers.add(new Customer(customerID, firstName, lastName, email, phoneNumber));
           }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return customers;
    }

    @Override
    public Customer findCustomerById(int customerID) throws CustomerNotFoundException {
        try(var con = DBConnUtil.getConnection()) {
            PreparedStatement preparedStatement = con.prepareStatement(SELECT_CUSTOMER_BY_ID);
            preparedStatement.setInt(1, customerID);
            ResultSet rs = preparedStatement.executeQuery();
            Customer customer = null;
            while (rs.next()) {
                String firstName = rs.getString("first_name");
                String lastName = rs.getString("last_name");
                String email = rs.getString("email");
                String phoneNumber = rs.getString("phone_number");
                customer = new Customer(customerID, firstName, lastName, email, phoneNumber);
            }

            return customer;

        } catch (SQLException e) {
            throw new CustomerNotFoundException("Customer not Found");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    // Lease Management
    @Override
    public Lease createLease(int customerID, int carID, String startDate,
                             String endDate) {
        try(var con = DBConnUtil.getConnection()){
            var preparedStatement = con.prepareStatement(INSERT_LEASE);
            preparedStatement.setInt(1, customerID);
            preparedStatement.setInt(2, carID);
            preparedStatement.setString(3, startDate);
            preparedStatement.setString(4, endDate);
            preparedStatement.setString(5, "DailyLease");
            var success = preparedStatement.execute();
            var leaseID = 0;
            var type = "DailyLease";
            Lease lease = null;
            if (success) {
                preparedStatement = con.prepareStatement(GET_LEASE_FROM_CUSTOMER_ID_AND_VEHICLE_ID);
                ResultSet rs = preparedStatement.executeQuery();
                while (rs.next()) {
                    leaseID = rs.getInt("lease_id");
                    type = rs.getString("type");
                    lease = new Lease(leaseID, customerID, carID, startDate, endDate, type);
                }
            }
            return lease;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Lease returnCar(int leaseID)  {
        Lease lease = null;
        try(var con = DBConnUtil.getConnection()) {
            var preparedStatement = con.prepareStatement(SELECT_LEASE_BY_ID);
            preparedStatement.setInt(1, leaseID);
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                var vehicleID = rs.getInt("vehicle_id");
                var customerID = rs.getInt("customer_id");
                var startDate = DateUtil.convertDateToString(rs.getDate("start_date"));
                var endDate = DateUtil.convertDateToString(rs.getDate("end_date"));
                var type = rs.getString("type");
                lease = new Lease(leaseID, vehicleID, customerID, startDate, endDate, type);
            }
            preparedStatement = con.prepareStatement(DELETE_LEASE_BY_ID);
            preparedStatement.setInt(1, leaseID);
            preparedStatement.execute();
            return lease;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        finally {
            return lease;
        }
    }

    @Override
    public List<Lease> listActiveLeases() {
        try(var con = DBConnUtil.getConnection()) {
            List<Lease> leaseList = new ArrayList<>();
            var preparedStatement = con.prepareStatement(SELECT_ACTIVE_LEASES);
            // current date
            var currentTimeMillis = System.currentTimeMillis();
            java.sql.Date currentDate = new java.sql.Date(currentTimeMillis);
            var date = DateUtil.convertDateToString(currentDate);

            preparedStatement.setString(1, date);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                var leaseID = rs.getInt("lease_id");
                var vehicleID = rs.getInt("vehicle_id");
                var customerID = rs.getInt("customer_id");
                var startDate = DateUtil.convertDateToString(rs.getDate("start_date"));
                var endDate = DateUtil.convertDateToString(rs.getDate("end_date"));
                var type = rs.getString("type");
                leaseList.add(new Lease(leaseID, vehicleID, customerID, startDate, endDate, type));
            }
            return leaseList;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Lease> listLeaseHistory() {
        try(var con = DBConnUtil.getConnection()) {
            List<Lease> leaseList = new ArrayList<>();
            var preparedStatement = con.prepareStatement(SELECT_LEASE);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                var leaseID = rs.getInt("lease_id");
                var vehicleID = rs.getInt("vehicle_id");
                var customerID = rs.getInt("customer_id");
                var startDate = DateUtil.convertDateToString(rs.getDate("start_date"));
                var endDate = DateUtil.convertDateToString(rs.getDate("end_date"));
                var type = rs.getString("type");
                leaseList.add(new Lease(leaseID, vehicleID, customerID, startDate, endDate, type));
            }
            return leaseList;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    // Payment Handling
    @Override
    public void recordPayment(Lease lease, double amount) {
          try (var con = DBConnUtil.getConnection()){
              var leaseID = lease.getLeaseID();
              var currentDate = DateUtil.convertDateToString(DateUtil.getCurrentDate());
              var preparedStatement = con.prepareStatement(RECORD_PAYMENT);
              preparedStatement.setInt(1, leaseID);
              preparedStatement.setString(2, currentDate);
              preparedStatement.setDouble(3, amount);
              preparedStatement.execute();
           } catch (SQLException e) {
              throw new RuntimeException(e);
          } catch (Exception e) {
              throw new RuntimeException(e);
          }
    }
}

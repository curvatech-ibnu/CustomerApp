package id.co.bankmandiri.customerapp.dao;

import id.co.bankmandiri.customerapp.domain.Customer;
import id.co.bankmandiri.customerapp.domain.CustomerException;
import id.co.bankmandiri.customerapp.service.CustomerService;
import id.co.bankmandiri.customerapp.util.DbUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CustomerDao implements CustomerService {

    private Connection connection;
    private final String queryDispAllCust = "SELECT * FROM customers";
    private final String queryFindCustById = "SELECT * FROM customers WHERE customerid = ?";
    private final String queryEditCust =
            "UPDATE customers " +
            "SET firstname = ?, lastname = ?, dateofbirth = ?" +
            "WHERE customerid = ?";
    private final String queryDelCust =
            "DELETE FROM customers " +
            "WHERE customerid = ?";
    private final String queryInsCust = "INSERT INTO customers(firstname,lastname,dateofbirth) VALUES (?,?,?)";

    public CustomerDao() {
        connection = DbUtil.getConnection();
    }
    public CustomerDao(Connection connection){
        this.connection = connection;
    }

    @Override
    public List<Customer> displayAllCust() {
        List<Customer> customers = new ArrayList<>();
        try(
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery(queryDispAllCust);
        )
        {
            while (resultSet.next()) {
                customers.add(new Customer(
                resultSet.getInt("customerid"),
                resultSet.getString("firstname"),
                resultSet.getString("lastname"),
                resultSet.getDate("dateofbirth").toLocalDate()
                ));
            }
        }catch (SQLException sqlException){
            sqlException.printStackTrace();
        }

        return customers;
    }

    @Override
    public void addCust(Customer c) throws CustomerException {
        try(
            PreparedStatement ps = connection.prepareStatement(queryInsCust);
        ){
            ps.setString(1, c.getCustFirstName());
            ps.setString(2, c.getCustLastName());
            ps.setDate(3, Date.valueOf(c.getCustDob()));
            ps.executeUpdate();
        }catch (SQLException sqle){
            sqle.printStackTrace();
            throw new CustomerException("Add Customer Failed");
        }
    }

    @Override
    public void editCust(Customer c) throws CustomerException {
        try(
                PreparedStatement ps = connection.prepareStatement(queryEditCust);
        ){
            ps.setString(1, c.getCustFirstName());
            ps.setString(2, c.getCustLastName());
            ps.setDate(3, Date.valueOf(c.getCustDob()));
            ps.setInt(4, c.getCustomerId());
            ps.executeUpdate();
        }catch (SQLException sqle){
            sqle.printStackTrace();
            throw new CustomerException("Edit Customer Failed");
        }
    }


    @Override
    public Customer findCustbyId(int id) throws CustomerException {
        Customer customer = null;
        try (
                PreparedStatement ps = connection.prepareStatement(queryFindCustById);
        ) {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                customer = (new Customer(
                        rs.getInt("customerid"),
                        rs.getString("firstname"),
                        rs.getString("lastname"),
                        rs.getDate("dateofbirth").toLocalDate()
                ));
            }
        } catch (SQLException sqle) {
            sqle.printStackTrace();
            throw new CustomerException("Find Customer by Id Failed");
        }
        if(customer !=null) {
            return customer;
        }else {
            throw new CustomerException("customer tidak ditemukan");
        }

    }

//    @Override
//    public Customer findCustbyName(String name) throws CustomerException {
//        return null;
//    }

    @Override
    public void delCust(int id) throws CustomerException {
        try(
                PreparedStatement ps = connection.prepareStatement(queryDelCust);
        ){
            ps.setInt(1, id);
            ps.executeUpdate();
        }catch (SQLException sqle){
            sqle.printStackTrace();
            throw new CustomerException("Delete Customer Failed");
        }
    }

}

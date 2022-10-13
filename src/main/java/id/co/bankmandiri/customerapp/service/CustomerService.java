package id.co.bankmandiri.customerapp.service;

import id.co.bankmandiri.customerapp.domain.Customer;
import id.co.bankmandiri.customerapp.domain.CustomerException;

import java.util.List;

public interface CustomerService {
    List<Customer> displayAllCust();
    void addCust(Customer c) throws CustomerException;
    void editCust(Customer c) throws CustomerException;
    Customer findCustbyId(int id) throws CustomerException;
//    Customer findCustbyName(String name) throws CustomerException;
    void delCust(int id) throws CustomerException;
}

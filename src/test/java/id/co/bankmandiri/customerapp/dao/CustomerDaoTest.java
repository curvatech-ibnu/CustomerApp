package id.co.bankmandiri.customerapp.dao;

import id.co.bankmandiri.customerapp.domain.Customer;
import id.co.bankmandiri.customerapp.domain.CustomerException;
import id.co.bankmandiri.customerapp.util.DbUtil;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class CustomerDaoTest {
    private static CustomerDao dao;
    @BeforeEach
    void setUp() {
        Connection connection = DbUtil.getConnection();
        dao = new CustomerDao(connection);
    }

    @Test
    void displayAllCust() {
    }

    @Test
    void addCust() {
        Customer customer = new Customer("Asep","Doe2",
                LocalDate.of(1998,9,9));
        try{
            dao.addCust(customer);
            Customer result = dao.findCustbyId(2);
            Assertions.assertEquals("John", result.getCustFirstName());
        }catch (CustomerException e){
            e.printStackTrace();
        }
    }

    @Test
    void editCust() {
        try {
            Customer customer = dao.findCustbyId(2);
            customer.setCustFirstName("Tom");
            customer.setCustLastName("Hanks");
            customer.setCustDob(LocalDate.now());
            dao.editCust(customer);
            dao.addCust(customer);
            Customer result = dao.findCustbyId(2);
            Assertions.assertEquals("Tom", result.getCustFirstName());
            Assertions.assertEquals("Hanks", result.getCustLastName());
            Assertions.assertEquals(LocalDate.now(), result.getCustDob());
        } catch (CustomerException e){
            e.printStackTrace();
        }
    }

    @Test
    void findCustbyId() {
        Exception e = Assertions.assertThrows(
                CustomerException.class,
                () -> dao.findCustbyId(100)
        );
        Assertions.assertEquals("customer tidak ditemukan", e.getMessage());
    }

    @Test
    void delCust() {
        try{
            dao.delCust(1);
            Exception e = Assertions.assertThrows(
                    CustomerException.class,
                    () -> dao.findCustbyId(1)
            );
            Assertions.assertEquals("customer tidak ditemukan", e.getMessage());
        } catch (CustomerException e){
            e.printStackTrace();
        }

    }
    }

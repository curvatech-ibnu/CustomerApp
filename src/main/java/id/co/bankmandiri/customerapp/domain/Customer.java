package id.co.bankmandiri.customerapp.domain;

import java.time.LocalDate;
import java.util.Objects;

public class Customer {
    private int customerId;
    private String custFirstName;
    private String custLastName;
    private LocalDate custDob;

    public Customer(String custFirstName, String custLastName, LocalDate custDob) {
        this.custFirstName = custFirstName;
        this.custLastName = custLastName;
        this.custDob = custDob;
    }
    public Customer(int customerId, String custFirstName, String custLastName, LocalDate custDob) {
        this(custFirstName, custLastName, custDob);
        this.customerId = customerId;
    }

    public int getCustomerId() {
        return customerId;
    }

    public String getCustFirstName() {
        return custFirstName;
    }

    public String getCustLastName() {
        return custLastName;
    }

    public LocalDate getCustDob() {
        return custDob;
    }

    public void setCustFirstName(String custFirstName) {
        this.custFirstName = custFirstName;
    }

    public void setCustLastName(String custLastName) {
        this.custLastName = custLastName;
    }

    public void setCustDob(LocalDate custDob) {
        this.custDob = custDob;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "customerId=" + customerId +
                ", custFirstName='" + custFirstName + '\'' +
                ", custLastName='" + custLastName + '\'' +
                ", custDob=" + custDob +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Customer customer = (Customer) o;
        return customerId == customer.customerId && Objects.equals(custFirstName, customer.custFirstName) && Objects.equals(custLastName, customer.custLastName) && Objects.equals(custDob, customer.custDob);
    }

    @Override
    public int hashCode() {
        return Objects.hash(customerId, custFirstName, custLastName, custDob);
    }
}

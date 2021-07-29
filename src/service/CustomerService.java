package service;

import model.Customer;


import java.util.Collection;
import java.util.HashSet;


public class CustomerService {
    private static CustomerService customerService = null;
    private CustomerService() {}
    public static CustomerService getInstance() {
        if (null == customerService) {
            customerService = new CustomerService();
        }
        return customerService;
    }

    Collection<Customer> allCustomer = new HashSet<>();


    public void addCustomer(String email,String firstName,String lastName) throws IllegalArgumentException {
        Customer customer = new Customer(firstName,lastName,email);
        allCustomer.add(customer);

    }
    public Customer getCustomer(String customerEmail) {
        for (Customer customer: allCustomer) {
            if (customer.getEmail().equals(customerEmail)) {
                return customer;
            }
        }
        return null;
    }

    public Collection<Customer> getAllCustomer() {
        return allCustomer;

    }

}

package model;

import java.util.regex.Pattern;

public class Customer {
     final String firstName;
     final String lastName;
     final String email;
    public Customer(String firstName, String lastName, String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        Validate(email);
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }

    public void Validate(String email) {
        String emailRegex = "^(.+)@(.+).(.+)$";
        Pattern pattern = Pattern.compile(emailRegex);
        if (!pattern.matcher(email).matches()) {
            throw new IllegalArgumentException("This is not an email format!");
        }
    }

    @Override
    public String toString() {
        return "The customer is " + firstName + " " + lastName + " and the email is " + email;
    }
}

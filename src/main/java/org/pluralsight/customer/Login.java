package org.pluralsight.customer;

import java.util.ArrayList;
import java.util.List;

public class Login {

    private String firstname;
    private String lastname;
    private String email;
    private String phoneNumber;
    private String password;
    private List<Sandwich> sandwich;
    private boolean isGuest;

    public Login(String firstname, String lastname, String email, String phoneNumber, String password) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.password = password;
    }

    public Login(String firstname, String email) {
        this.firstname = firstname;
        this.email = email;
        this.sandwich = new ArrayList<>();
        this.isGuest = true;
    }

    public boolean isGuest() {
        return isGuest;
    }

    public void setGuest(boolean guest) {
        isGuest = guest;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<Sandwich> getOrderDetails() {
        return sandwich;
    }

    public void addSandwich(Sandwich order) {
        sandwich.add(order);
    }
}

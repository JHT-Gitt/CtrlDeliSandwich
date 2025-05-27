package org.example.customer;

import java.util.ArrayList;
import java.util.List;

public class CustomerCheckDetails {
    private String date;
    private int orderNumber;
    private List<Login> logDetails;
    private List<Sandwich> orderDetails;

    public CustomerCheckDetails(String date, int orderNumber) {
        this.date = date;
        this.orderNumber = orderNumber;
        this.logDetails = new ArrayList<>();
        this.orderDetails = new ArrayList<>();
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(int orderNumber) {
        this.orderNumber = orderNumber;
    }

    public List<Login> getLogDetails() {
        return logDetails;
    }

    public void setLogDetails(List<Login> logDetails) {
        this.logDetails = logDetails;
    }

    public List<Sandwich> getOrderDetails() {
        return orderDetails;
    }

    public void setOrderDetails(List<Sandwich> orderDetails) {
        this.orderDetails = orderDetails;
    }
    public void addCust(Login log){
        logDetails.add(log);
    }
    public void addSandwich(Sandwich s){
        orderDetails.add(s);
    }

}

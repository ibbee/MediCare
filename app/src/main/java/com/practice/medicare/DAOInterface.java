package com.practice.medicare;

import java.util.ArrayList;

public interface DAOInterface {
    public void register(String username, String email, String password);
    public int login (String username, String password);
    public void addCart(String username, String product, float price, String otype);
    public int checkCart(String username, String product);
    public void removeCart(String username, String otype);
    public ArrayList getCartData(String username, String otype);
    public void addOrder(String username, String fullname, String address, String contact, int pincode, String date, String time, float price, String otype);
    public ArrayList getOrderData(String username);
    public int checkAppointmentExists(String username, String fullname,String otype, String address, String contact, String date, String time);
}

package com.example.project5;

public class OrderView {
    private String name;

    private double subtotal;

    private double total;

    public OrderView(String name, double subtotal) {
        this.name = name;
        this.subtotal = subtotal;
    }

    public String getName() {
        return name;
    }

//    public int getQuantity() {
//        return quantity;
//    }

    public double getSubtotal() {
        return subtotal;
    }

    public double getTotal() {
        return subtotal;
    }

//    public void setQuantity(int quantity) {
//        this.quantity = quantity;
//    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSubtotal(double subtotal) {
        this.subtotal = subtotal;
    }



    public void setTotal(double total) {
        this.total = total;
    }
}

package com.example.project5;

public class OrderView {
    private String name;
    private int quantity;
    private double subtotal;

    private double total;

    public OrderView(String name, int quantity, double subtotal, double total) {
        this.name = name;
        this.quantity = quantity;
        this.subtotal = subtotal;
        this.total=total;
    }

    public String getName() {
        return name;
    }

    public int getQuantity() {
        return quantity;
    }

    public double getSubtotal() {
        return subtotal;
    }

    public double getTotal() {
        return total;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

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

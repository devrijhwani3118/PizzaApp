package com.example.project5;

public class OrderView {
    private String name;

    private double subtotal;

    private String pizzaStringDescription;
    private int orderNumber;

    public OrderView(String name, double subtotal, String pizzaString, int orderNum) {
        this.name = name;
        this.subtotal = subtotal;
        pizzaStringDescription=pizzaString;
        orderNumber=orderNum;
    }

    public String getPizzaStringDescription() {
        return pizzaStringDescription;
    }

    public int getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(int orderNumber) {
        this.orderNumber = orderNumber;
    }

    public void setPizzaStringDescription(String pizzaStringDescription) {
        this.pizzaStringDescription = pizzaStringDescription;
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

//    public double getTotal() {
//        return subtotal*1.06625;
//    }

//    public void setQuantity(int quantity) {
//        this.quantity = quantity;
//    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSubtotal(double subtotal) {
        this.subtotal = subtotal;
    }


}

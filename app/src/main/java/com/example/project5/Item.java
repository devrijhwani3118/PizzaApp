package com.example.project5;

public class Item {

    public String order;
    public int cost;
    public int orderNumber;

    public Item(String order, int cost, int orderNumber) {
        this.order = order;
        this.cost = cost;
        this.orderNumber = orderNumber;
    }

    public String getOrder() {
        return order;
    }

    public void setOrder(String order) {
        this.order = order;
    }

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    public int getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(int orderNumber) {
        this.orderNumber = orderNumber;
    }
}

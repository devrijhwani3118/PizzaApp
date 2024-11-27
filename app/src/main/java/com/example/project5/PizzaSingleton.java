package com.example.project5;

import android.content.Context;
import android.content.Intent;
import android.widget.Button;
import android.widget.ImageButton;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModel;

import java.util.ArrayList;
import java.util.List;


public class PizzaSingleton {
    private static PizzaSingleton instance;

    private static ArrayList<String> pizzasString;
    private static ArrayList<Pizza> pizzas;
    private static ArrayList<String> orderList;
    private static ArrayList<Integer> orderNumberList;
    private static ArrayList<Order> orders;

    private PizzaSingleton() {
        pizzasString = new ArrayList<>();
        orderList = new ArrayList<>();
        orderNumberList = new ArrayList<>();
        pizzas = new ArrayList<>();
        orders = new ArrayList<>();

    }

    public static synchronized PizzaSingleton getInstance() {
        if (instance == null) {
            instance = new PizzaSingleton();
        }
        return instance;
    }

    public static void setInstance(PizzaSingleton instance) {
        PizzaSingleton.instance = instance;
    }

    public static ArrayList<String> getPizzasString() {
        return pizzasString;
    }

    public static void setPizzasString(ArrayList<String> pizzasString) {
        PizzaSingleton.pizzasString = pizzasString;
    }

    public static ArrayList<Pizza> getPizzas() {
        return pizzas;
    }

    public static void setPizzas(ArrayList<Pizza> pizzas) {
        PizzaSingleton.pizzas = pizzas;
    }

    public static ArrayList<String> getOrderList() {
        return orderList;
    }

    public static void setOrderList(ArrayList<String> orderList) {
        PizzaSingleton.orderList = orderList;
    }

    public static ArrayList<Integer> getOrderNumberList() {
        return orderNumberList;
    }

    public static void setOrderNumberList(ArrayList<Integer> orderNumberList) {
        PizzaSingleton.orderNumberList = orderNumberList;
    }

    public static ArrayList<Order> getOrders() {
        return orders;
    }

    public static void setOrders(ArrayList<Order> orders) {
        PizzaSingleton.orders = orders;
    }
}

package com.example.project5;

import android.content.Context;
import android.content.Intent;
import android.media.Image;
import android.widget.Button;
import android.widget.ImageButton;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModel;

import java.util.ArrayList;
import java.util.List;


/**
 * Singleton class for managing shared pizza-related data across the application.
 * This class ensures a single instance is used throughout the app for tracking orders, pizzas, and related details.
 * @author Sri Akshara Kollu, Dev Rijhwani
 */
public class PizzaSingleton {
    private static PizzaSingleton instance;

    private static ArrayList<String> pizzasString;

    private static final int ZERO=0;
    private static final int ONE=1;
    private static double subtotalPizzas = ZERO;
    //for the order
    private static ArrayList<Pizza> pizzas;
    private static ArrayList<String> orderList;
    private static ArrayList<Integer> orderNumberList;
    private static ArrayList<Order> orders;
    private static int orderNumber;

    /**
     * Private constructor to prevent instantiation from other classes.
     * Initializes all collections and sets the starting order number.
     */
    private PizzaSingleton() {
        pizzasString = new ArrayList<>();
        orderList = new ArrayList<>();
        orderNumberList = new ArrayList<>();
        pizzas = new ArrayList<>();
        orders = new ArrayList<>();
        orderNumber=ONE;

    }

    /**
     * Gets the current order number.
     * @return the current order number.
     */
    public static int getOrderNumber() {
        return orderNumber;
    }

    /**
     * Gets the subtotal for all pizzas in the current order.
     * @return the subtotal for pizzas.
     */
    public static double getSubtotalPizzas() {
        return subtotalPizzas;
    }

    /**
     * Sets the subtotal for all pizzas in the current order.
     * @param subtotalPizzas the new subtotal for pizzas.
     */
    public static void setSubtotalPizzas(double subtotalPizzas) {
        PizzaSingleton.subtotalPizzas = subtotalPizzas;
    }

    /**
     * Sets the current order number.
     * @param orderNumber the new order number.
     */
    public static void setOrderNumber(int orderNumber) {
        PizzaSingleton.orderNumber = orderNumber;
    }

    /**
     * Gets the singleton instance of the class.
     * Ensures thread safety using synchronization.
     * @return the singleton instance of PizzaSingleton.
     */
    public static synchronized PizzaSingleton getInstance() {
        if (instance == null) {
            instance = new PizzaSingleton();
        }
        return instance;
    }

    /**
     * Sets the singleton instance (useful for testing or resetting).
     * @param instance the new singleton instance.
     */
    public static void setInstance(PizzaSingleton instance) {
        PizzaSingleton.instance = instance;
    }

    /**
     * Gets the list of string descriptions for pizzas.
     * @return the list of pizza descriptions.
     */
    public static ArrayList<String> getPizzasString() {
        return pizzasString;
    }

    /**
     * Sets the list of string descriptions for pizzas.
     * @param pizzasString the new list of pizza descriptions.
     */
    public static void setPizzasString(ArrayList<String> pizzasString) {
        PizzaSingleton.pizzasString = pizzasString;
    }

    /**
     * Gets the list of Pizza objects in the current order.
     * @return the list of pizzas.
     */
    public static ArrayList<Pizza> getPizzas() {
        return pizzas;
    }

    /**
     * Sets the list of Pizza objects in the current order.
     * @param pizzas the new list of pizzas.
     */
    public static void setPizzas(ArrayList<Pizza> pizzas) {
        PizzaSingleton.pizzas = pizzas;
    }

    /**
     * Gets the list of string descriptions for orders.
     * @return the list of order descriptions.
     */
    public static ArrayList<String> getOrderList() {
        return orderList;
    }

    /**
     * Sets the list of string descriptions for orders.
     * @param orderList the new list of order descriptions.
     */
    public static void setOrderList(ArrayList<String> orderList) {
        PizzaSingleton.orderList = orderList;
    }

    /**
     * Gets the list of order numbers.
     * @return the list of order numbers.
     */
    public static ArrayList<Integer> getOrderNumberList() {
        return orderNumberList;
    }

    /**
     * Sets the list of order numbers.
     * @param orderNumberList the new list of order numbers.
     */
    public static void setOrderNumberList(ArrayList<Integer> orderNumberList) {
        PizzaSingleton.orderNumberList = orderNumberList;
    }

    /**
     * Gets the list of completed orders.
     * @return the list of completed orders.
     */
    public static ArrayList<Order> getOrders() {
        return orders;
    }

    /**
     * Sets the list of completed orders.
     * @param orders the new list of completed orders.
     */
    public static void setOrders(ArrayList<Order> orders) {
        PizzaSingleton.orders = orders;
    }

}

package com.example.project5;

import java.util.ArrayList;

/**
 * Represents a customer's order containing a collection of pizzas.
 * Each order is associated with a unique order number and calculates
 * the total cost including applicable sales tax.
 * The Order class maintains a list of pizzas, allows the addition
 * and removal of pizzas, and computes the total price for the order.
 * @author Sri Akshara Kollu, Dev Rijhwani
 */
public class Order {
    private int number;
    private final int ZERO=0;
    private final double SALES_TAX=1.06625;
    private ArrayList<Pizza> pizzas;

    /**
     * Constructs an Order object with a specified order number and list of pizzas.
     * @param number the unique order number
     * @param pizzas the list of pizzas in the order
     */
    public Order(int number, ArrayList<Pizza> pizzas) {
        this.number = number;
        this.pizzas = pizzas;
    }

    /**
     * Retrieves the order number.
     * @return the unique order number
     */
    public int getNumber() {
        return number;
    }

    /**
     * Sets a new order number.
     * @param number the new unique order number
     */
    public void setNumber(int number) {
        this.number = number;
    }

    /**
     * Retrieves the list of pizzas in the order.
     * @return the list of pizzas
     */
    public ArrayList<Pizza> getPizzas() {
        return pizzas;
    }

    /**
     * Sets a new list of pizzas for the order.
     * @param pizzas the new list of pizzas
     */
    public void setPizzas(ArrayList<Pizza> pizzas) {
        this.pizzas = pizzas;
    }

    /**
     * Adds a new list of pizzas for the order.
     * @param piz the new list of pizzas
     */
    public void addPizzas(Pizza piz) {
        pizzas.add(piz);
    }

    /**
     * Removes a specified pizza from the order.
     * @param pizza the pizza to be removed from the order
     */
    public void removePizza(Pizza pizza) {
        pizzas.remove(pizza);
    }


    /**
     * Calculates the total cost of the order, including sales tax.
     * @return the total cost of the order after applying sales tax
     */
    public double getOrderTotal() {
        double total = ZERO;
        for (int i = 0; i< pizzas.size(); i++) {
            total += pizzas.get(i).price();
        }
        return total * SALES_TAX;
    }
}
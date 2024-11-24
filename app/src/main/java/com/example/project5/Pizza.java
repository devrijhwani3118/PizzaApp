package com.example.project5;

import java.util.ArrayList;

/**
 * Represents an abstract base class for pizzas with specific crusts, sizes,
 * and toppings. The Pizza class manages the list of toppings and provides
 * methods to add or remove toppings and calculate the pizza's price.
 * This class is intended to be subclassed by specific pizza types.
 * @author Sri Akshara Kollu, Dev Rijhwani
 */
public abstract class Pizza {
    private final int ZERO=0;
    private final int SEVEN=7;
    private final int TWO=2;
    private ArrayList<Topping> toppings;
    private Crust crust;
    private Size size;

    /**
     * Constructs a Pizza object with the specified crust and size.
     * Initializes the toppings list as an empty ArrayList.
     * @param crust the type of crust for the pizza
     * @param size the size of the pizza
     */
    public Pizza(Crust crust, Size size) {
        this.crust = crust;
        this.size = size;
        this.toppings = new ArrayList<>();
    }

    /**
     * Retrieves the crust of the pizza.
     * @return the type of crust for the pizza
     */
    public Crust getCrust() {
        return crust;
    }

    /**
     * Retrieves the size of the pizza.
     * @return the size of the pizza
     */
    public Size getSize() {
        return size;
    }

    /**
     * Retrieves the list of toppings on the pizza.
     * @return the list of toppings
     */
    public ArrayList<Topping> getToppings() {
        return toppings;
    }

    /**
     * Adds a topping to the pizza if the maximum number of toppings
     * (seven) has not been reached.
     * @param topping the topping to be added
     */
    public void addTopping(Topping topping) {
        if (toppings.size() < SEVEN) {
            toppings.add(topping);
        }
    }

    /**
     * Removes a specified topping from the pizza.
     * @param topping the topping to be removed
     */
    public void removeTopping(Topping topping) {
        toppings.remove(topping);
    }

    /**
     * Abstract method to calculate the price of the pizza.
     * This method must be implemented by subclasses to provide
     * the specific pricing logic.
     * @return the price of the pizza
     */
    public abstract double price();

    /**
     * Returns a string representation of the pizza, including
     * its toppings, crust, and size.
     * @return a string detailing the pizza's attributes
     */
    @Override
    public String toString() {
        StringBuilder toppingsList = new StringBuilder();
        for (Topping topping : toppings) {
            toppingsList.append(topping).append(", ");
        }
        if (toppingsList.length() > ZERO) {
            toppingsList.setLength(toppingsList.length() - TWO);
        }
        return "Pizza{" +
                "toppings=" + toppingsList +
                ", crust=" + crust +
                ", size=" + size +
                '}';
    }
}

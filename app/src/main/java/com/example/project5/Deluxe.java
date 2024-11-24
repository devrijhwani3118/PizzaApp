package com.example.project5;


/**
 * Represents a Deluxe pizza with a specified crust, size, and style.
 * Deluxe pizzas come with a standard set of toppings: sausage, pepperoni, green pepper, onion, and mushroom.
 * The price varies based on the pizza size.
 * @author Sri Akshara Kollu, Dev Rijhwani
 */
public class Deluxe extends Pizza {

    private final double SMALL_ORDER = 16.99;
    private final double MEDIUM_ORDER = 18.99;
    private final double LARGE_ORDER = 20.99;
    private final int ZERO=0;
    private final int TWO=2;
    private final String STYLE;

    /**
     * Constructs a Deluxe pizza with the given crust, size, and style.
     * Adds the default toppings for a Deluxe pizza.
     * @param crust the crust type of the pizza
     * @param size the size of the pizza (small, medium, or large)
     * @param style the style of the pizza (e.g., "New York", "Chicago")
     */
    public Deluxe(Crust crust, Size size, String style) {
        super(crust, size);
        this.STYLE = style;

        addTopping(Topping.SAUSAGE);
        addTopping(Topping.PEPPERONI);
        addTopping(Topping.GREEN_PEPPER);
        addTopping(Topping.ONION);
        addTopping(Topping.MUSHROOM);
    }

    /**
     * Returns the price of the Deluxe pizza based on its size.
     * @return the price of the pizza as a double
     */
    @Override
    public double price() {
        switch (getSize()) {
            case SMALL: return SMALL_ORDER;
            case MEDIUM: return MEDIUM_ORDER;
            case LARGE: return LARGE_ORDER;
            default: return ZERO;
        }
    }

    /**
     * Provides a string representation of the Deluxe pizza, including style, crust, toppings, size, and price.
     * Formats the toppings list and price.
     * @return a string description of the Deluxe pizza
     */
    @Override
    public String toString() {
        StringBuilder toppingsList = new StringBuilder();
        for (Topping topping : getToppings()) {
            toppingsList.append(topping).append(", ");
        }
        if (toppingsList.length() > ZERO) {
            toppingsList.setLength(toppingsList.length() - TWO); // Remove trailing comma and space
        }
        return "Deluxe (" + STYLE + " - " + getCrust() + "), " +
                toppingsList + ", " + getSize().toString().toLowerCase() + ", $" + String.format("%.2f", price());
    }
}

package com.example.project5;

/**
 * Represents a Meatzza pizza with a specified crust, size, and style.
 * Meatzza pizzas come with a standard set of meat toppings: sausage, pepperoni, beef, and ham.
 * The price varies based on the pizza size.
 * @author Sri Akshara Kollu, Dev Rijhwani
 */
public class Meatzza extends Pizza {

    private final String STYLE;
    private final int ZERO=0;
    private final int TWO=2;
    private final double SMALL_ORDER=17.99;
    private final double MEDIUM_ORDER=19.99;
    private final double LARGE_ORDER=21.99;


    /**
     * Constructs a Meatzza pizza with the given crust, size, and style.
     * Adds the default meat toppings for a Meatzza pizza.
     * @param crust the crust type of the pizza
     * @param size the size of the pizza (small, medium, or large)
     * @param style the style of the pizza (e.g., "New York", "Chicago")
     */
    public Meatzza(Crust crust, Size size, String style) {
        super(crust, size);
        this.STYLE = style;

        addTopping(Topping.SAUSAGE);
        addTopping(Topping.PEPPERONI);
        addTopping(Topping.BEEF);
        addTopping(Topping.HAM);
    }

    /**
     * Returns the price of the Meatzza pizza based on its size.
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
     * Provides a string representation of the Meatzza pizza, including style, crust, toppings, size, and price.
     * Formats the toppings list and price.
     * @return a string description of the Meatzza pizza
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
        return "Meatzza (" + STYLE + " - " + getCrust() + "), " +
                toppingsList + ", " + getSize().toString().toLowerCase() + ", $" + String.format("%.2f", price());
    }
}

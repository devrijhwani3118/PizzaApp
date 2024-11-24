package com.example.project5;

/**
 * Represents a customizable "Build Your Own" pizza with a specific crust, size, and style.
 * Allows customers to add their choice of toppings and calculates the price accordingly.
 * This class provides methods to calculate the price based on size and toppings,
 * and to represent the pizza as a string.
 * @author Sri Akshara Kollu, Dev Rijhwani
 */
public class BuildYourOwn extends Pizza {

    private final double SMALL_ORDER = 8.99;
    private final double MEDIUM_ORDER = 10.99;
    private final double LARGE_ORDER = 12.99;
    private final double TOPPING_COST=1.69;
    private final int ZERO=0;
    private final int TWO=2;
    private final String STYLE;


    /**
     * Constructs a BuildYourOwn pizza with the specified crust, size, and style.
     * @param crust the crust style for the pizza
     * @param size the size of the pizza
     * @param style the style of the Build Your Own pizza
     */
    public BuildYourOwn(Crust crust, Size size, String style) {
        super(crust, size);
        this.STYLE = style;
    }

    /**
     * Calculates and returns the price of the Build Your Own pizza based on its size
     * and the number of toppings added.
     * @return the price of the pizza
     */
    @Override
    public double price() {
        double basePrice;
        switch (getSize()) {
            case SMALL: basePrice = SMALL_ORDER; break;
            case MEDIUM: basePrice = MEDIUM_ORDER; break;
            case LARGE: basePrice = LARGE_ORDER; break;
            default: basePrice = ZERO;
        }
        return basePrice + (getToppings().size() * TOPPING_COST);
    }

    /**
     * Returns a string representation of the Build Your Own pizza, including its style,
     * crust, toppings, size, and price.
     * @return a string describing the Build Your Own pizza
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
        return "Build Your Own (" + STYLE + " - " + getCrust() + "), " +
                toppingsList + ", " + getSize().toString().toLowerCase() + ", $" + String.format("%.2f", price());
    }
}

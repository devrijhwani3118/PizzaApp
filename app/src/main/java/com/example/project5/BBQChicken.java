package com.example.project5;

/**
 * Represents a BBQ Chicken pizza with a specific crust, size, and style.
 * This class defines default toppings for BBQ Chicken pizza and provides
 * methods to calculate its price and to represent it as a string.
 * @author Sri Akshara Kollu, Dev Rijhwani
 */
public class BBQChicken extends Pizza {

    private final double SMALL_ORDER = 14.99;
    private final double MEDIUM_ORDER = 16.99;
    private final double LARGE_ORDER = 18.99;
    private final int ZERO = 0;
    private final int TWO=2;
    private final String STYLE;


    /**
     * Constructs a BBQChicken pizza with the specified crust, size, and style.
     * Initializes the pizza with specific toppings.
     * @param crust the crust style for the pizza
     * @param size the size of the pizza
     * @param style the style of the BBQ Chicken pizza
     */
    public BBQChicken(Crust crust, Size size, String style) {
        super(crust, size);
        this.STYLE = style;
        addTopping(Topping.BBQ_CHICKEN);
        addTopping(Topping.GREEN_PEPPER);
        addTopping(Topping.ONION);
        addTopping(Topping.PROVOLONE);
        addTopping(Topping.CHEDDAR);
    }

    /**
     * Calculates and returns the price of the BBQ Chicken pizza based on its size.
     * @return the price of the pizza
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
     * Returns a string representation of the BBQ Chicken pizza, including its style,
     * crust, toppings, size, and price.
     * @return a string describing the BBQ Chicken pizza
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
        return "BBQ Chicken (" + STYLE + " - " + getCrust() + "), " +
                toppingsList + ", " + getSize().toString().toLowerCase() + ", $" + String.format("%.2f", price());
    }
}

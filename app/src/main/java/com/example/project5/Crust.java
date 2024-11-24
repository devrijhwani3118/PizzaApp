package com.example.project5;

/**
 * Enumeration representing different types of pizza crusts available.
 * Each enum value represents a specific crust style, either for Chicago or New York style pizzas.
 * This enum can be used to specify the type of crust for different pizza orders.
 *
 * @author Sri Akshara Kollu, Dev Rijhwani
 */
public enum Crust {
    DEEP_DISH,
    PAN,
    STUFFED,

    BROOKLYN,
    THIN,
    HAND_TOSSED;

    /**
     * Returns the name of the crust type in uppercase format.
     * @return the name of the crust in uppercase
     */
    @Override
    public String toString() {
        return this.name().toUpperCase();
    }
}

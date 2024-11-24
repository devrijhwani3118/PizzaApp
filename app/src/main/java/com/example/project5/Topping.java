package com.example.project5;

/**
 * Enum representing the various toppings available for pizzas.
 * @author Sri Akshara Kollu, Dev Rijhwani
 */
public enum Topping {
    SAUSAGE,
    PEPPERONI,
    GREEN_PEPPER,
    ONION,
    MUSHROOM,
    BBQ_CHICKEN,
    PROVOLONE,
    CHEDDAR,
    BEEF,
    HAM,
    PINEAPPLE,
    OLIVES,
    SPINACH;

    /**
     * Returns the name of the enum constant in uppercase format.
     * This method overrides the default toString method to provide
     * a user-friendly string representation of the topping.
     * @return the uppercase name of the topping
     */
    @Override
    public String toString() {
        return this.name().toUpperCase();
    }
}
package com.example.project5;

/**
 * Enum representing the possible sizes of pizzas.
 * Author: Sri Akshara Kollu, Dev Rijhwani
 */
public enum Size {
    SMALL, MEDIUM, LARGE;

    /**
     * Returns the name of the enum constant in uppercase format.
     * This method overrides the default toString method to provide
     * a more user-friendly string representation of the size.
     * @return the uppercase name of the pizza size
     */
    @Override
    public String toString() {
        return this.name().toUpperCase();
    }
}

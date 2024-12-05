package com.example.project5;

/**
 * Represents a view model for a pizza topping.
 * Holds the topping name and provides methods to access and modify it.
 * @author Sri Akshara Kollu, Dev Rijhwani
 */
public class ToppingView {
    private String toppingName;

    /**
     * Constructor to initialize the ToppingView with a topping name.
     * @param topName The name of the topping.
     */
    public ToppingView(String topName) {
        toppingName = topName;
    }

    /**
     * Retrieves the name of the topping.
     * @return The name of the topping.
     */
    public String getToppingName() {
        return toppingName;
    }

    /**
     * Updates the name of the topping.
     * @param toppingName The new name of the topping.
     */
    public void setToppingName(String toppingName) {
        this.toppingName = toppingName;
    }
}
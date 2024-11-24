package com.example.project5;

/**
 * Represents a factory interface for creating various types of pizzas.
 * This interface defines methods for creating different styles of pizzas,
 * including Deluxe, Meatzza, BBQ Chicken, and Build Your Own, with a specified size.
 * Implementing classes must provide concrete implementations for these methods.
 * @author Sri Akshara Kollu, Dev Rijhwani
 */
public interface PizzaFactory {

    /**
     * Creates a Deluxe pizza of the specified size.
     * @param size the size of the pizza (small, medium, large)
     * @return a Deluxe pizza with the specified size
     */
    Pizza createDeluxe(Size size);

    /**
     * Creates a Meatzza pizza of the specified size.
     * @param size the size of the pizza (small, medium, large)
     * @return a Meatzza pizza with the specified size
     */
    Pizza createMeatzza(Size size);

    /**
     * Creates a BBQ Chicken pizza of the specified size.
     * @param size the size of the pizza (small, medium, large)
     * @return a BBQ Chicken pizza with the specified size
     */
    Pizza createBBQChicken(Size size);

    /**
     * Creates a Build Your Own pizza of the specified size.
     * @param size the size of the pizza (small, medium, large)
     * @return a Build Your Own pizza with the specified size
     */
    Pizza createBuildYourOwn(Size size);
}

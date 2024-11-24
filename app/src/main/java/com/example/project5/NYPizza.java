package com.example.project5;

/**
 * Represents a NY-style pizza factory that creates pizzas with specific
 * Chicago-style crusts. This factory can create various types of pizzas,
 * including Deluxe, Meatzza, BBQ Chicken, and Build Your Own, each with its
 * corresponding crust style and Chicago style name.
 * Implements the PizzaFactory interface.
 * @author Sri Akshara Kollu, Dev Rijhwani
 */
public class NYPizza implements PizzaFactory {

    /**
     * Creates a New York-style Deluxe pizza with the specified size and a Brooklyn crust.
     * @param size the size of the pizza (small, medium, large)
     * @return a Deluxe pizza with New York-style Brooklyn crust
     */
    @Override
    public Pizza createDeluxe(Size size) {
        return new Deluxe(Crust.BROOKLYN, size, "New York Style-Brooklyn");
    }

    /**
     * Creates a New York-style Meatzza pizza with the specified size and a Hand Tossed crust.
     * @param size the size of the pizza (small, medium, large)
     * @return a Meatzza pizza with New York-style Hand Tossed crust
     */
    @Override
    public Pizza createMeatzza(Size size) {
        return new Meatzza(Crust.HAND_TOSSED, size, "New York Style-Hand Tossed");
    }

    /**
     * Creates a New York-style BBQ Chicken pizza with the specified size and a Thin crust.
     * @param size the size of the pizza (small, medium, large)
     * @return a BBQ Chicken pizza with New York-style Thin crust
     */
    @Override
    public Pizza createBBQChicken(Size size) {
        return new BBQChicken(Crust.THIN, size, "New York Style-Thin");
    }

    /**
     * Creates a New York-style Build-Your-Own pizza with the specified size and a Hand Tossed crust.
     * @param size the size of the pizza (small, medium, large)
     * @return a Build-Your-Own pizza with New York-style Hand Tossed crust
     */
    @Override
    public Pizza createBuildYourOwn(Size size) {
        return new BuildYourOwn(Crust.HAND_TOSSED, size, "New York Style-Hand Tossed");
    }
}

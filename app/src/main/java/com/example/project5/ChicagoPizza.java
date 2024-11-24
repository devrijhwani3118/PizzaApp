package com.example.project5;

/**
 * Represents a Chicago-style pizza factory that creates pizzas with specific
 * Chicago-style crusts. This factory can create various types of pizzas,
 * including Deluxe, Meatzza, BBQ Chicken, and Build Your Own, each with its
 * corresponding crust style and Chicago style name.
 * Implements the PizzaFactory interface.
 * @author Sri Akshara Kollu, Dev Rijhwani
 */
public class ChicagoPizza implements PizzaFactory {

    /**
     * Creates a Deluxe pizza with a deep dish crust in Chicago style.
     * @param size the size of the pizza
     * @return a new Deluxe pizza with a deep dish crust and Chicago style
     */
    @Override
    public Pizza createDeluxe(Size size) {
        return new Deluxe(Crust.DEEP_DISH, size, "Chicago Style-Deep Dish");
    }

    /**
     * Creates a Meatzza pizza with a stuffed crust in Chicago style.
     * @param size the size of the pizza
     * @return a new Meatzza pizza with a stuffed crust and Chicago style
     */
    @Override
    public Pizza createMeatzza(Size size) {
        return new Meatzza(Crust.STUFFED, size, "Chicago Style-Stuffed");
    }

    /**
     * Creates a BBQ Chicken pizza with a pan crust in Chicago style.
     * @param size the size of the pizza
     * @return a new BBQ Chicken pizza with a pan crust and Chicago style
     */
    @Override
    public Pizza createBBQChicken(Size size) {
        return new BBQChicken(Crust.PAN, size, "Chicago Style-Pan");
    }

    /**
     * Creates a Build Your Own pizza with a pan crust in Chicago style.
     * @param size the size of the pizza
     * @return a new Build Your Own pizza with a pan crust and Chicago style
     */
    @Override
    public Pizza createBuildYourOwn(Size size) {
        return new BuildYourOwn(Crust.PAN, size, "Chicago Style-Pan");
    }
}

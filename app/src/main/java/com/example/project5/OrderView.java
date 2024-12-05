package com.example.project5;

/**
 * Represents a view of an order, containing details about a pizza and its subtotal price.
 * Provides methods to parse the pizza description and calculate subtotals
 * @author Sri Akshara Kollu, Dev Rijhwani
 */
public class OrderView {

    private double subtotal;

    private String pizzaStringDescription;
    /**
     * Constructor to create an OrderView object with a specified subtotal and pizza description.
     * @param subtotal the subtotal price of the order.
     * @param pizzaString the string description of the pizza.
     */
    public OrderView(double subtotal, String pizzaString) {
        this.subtotal = subtotal;
        pizzaStringDescription=pizzaString;
    }

    /**
     * Gets the string description of the pizza.
     * @return the pizza string description.
     */
    public String getPizzaStringDescription() {
        return pizzaStringDescription;
    }

    /**
     * Parses the pizza string description and reconstructs a Pizza object.
     * This method determines the size, type, and toppings of the pizza based on the description.
     * @return a Pizza object constructed from the string description.
     */
    public Pizza getPizza(){
        Pizza pizza = null;
        Size size;
        if(pizzaStringDescription.contains("small")) size=Size.SMALL;
        else if(pizzaStringDescription.contains("medium")) size=Size.MEDIUM;
        else size=Size.LARGE;
        if(pizzaStringDescription.contains("Chicago")){
            if(pizzaStringDescription.contains("Deluxe")) pizza = new ChicagoPizza().createDeluxe(size);
            else if(pizzaStringDescription.contains("BBQ Chicken"))pizza = new ChicagoPizza().createBBQChicken(size);
            else if(pizzaStringDescription.contains("Meatzza")) pizza = new ChicagoPizza().createMeatzza(size);
            else{
                pizza = new ChicagoPizza().createBuildYourOwn(size);
                int startIndex=pizzaStringDescription.indexOf(",");
                int endIndex=pizzaStringDescription.indexOf(size.toString().toLowerCase());
                String [] selectedToppings = pizzaStringDescription.substring(startIndex, endIndex).split(",");
                for(int i = 0; i<selectedToppings.length; i++){
                    Topping topping = Topping.valueOf(selectedToppings[i].toUpperCase().replace(" ", "_"));
                    pizza.addTopping(topping);
                }
            }
        }
        else{
            if(pizzaStringDescription.contains("Deluxe")) pizza = new NYPizza().createDeluxe(size);
            else if(pizzaStringDescription.contains("BBQ Chicken")) pizza = new NYPizza().createBBQChicken(size);
            else if(pizzaStringDescription.contains("Meatzza")) pizza = new NYPizza().createMeatzza(size);
            else{
                pizza = new NYPizza().createBuildYourOwn(size);
                int startIndex=pizzaStringDescription.indexOf(",");
                int endIndex=pizzaStringDescription.indexOf(size.toString().toLowerCase());
                String [] selectedToppings = pizzaStringDescription.substring(startIndex, endIndex).split(",");
                for(int i = 0; i<selectedToppings.length; i++){
                    Topping topping = Topping.valueOf(selectedToppings[i].toUpperCase().replace(" ", "_"));
                    pizza.addTopping(topping);
                }
            }
        }
        return pizza;
    }

    /**
     * Gets the subtotal price of the order.
     * @return the subtotal price.
     */
    public double getSubtotal() {
        return subtotal;
    }

    /**
     * Sets the subtotal price of the order.
     * @param subtotal the new subtotal price.
     */
    public void setSubtotal(double subtotal) {
        this.subtotal = subtotal;
    }

}

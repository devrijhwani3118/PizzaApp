package com.example.project5;

import java.util.List;

public class OrderView {

    private double subtotal;

    private String pizzaStringDescription;
    private int orderNumber;

    public OrderView(double subtotal, String pizzaString, int orderNum) {
        this.subtotal = subtotal;
        pizzaStringDescription=pizzaString;
        orderNumber=orderNum;
    }

    public String getPizzaStringDescription() {
        return pizzaStringDescription;
    }

    public Pizza getPizza(){
        Pizza pizza = null;
        Size size;
        if(pizzaStringDescription.contains("small")) size=Size.SMALL;
        else if(pizzaStringDescription.contains("medium")) size=Size.MEDIUM;
        else size=Size.LARGE;

        if(pizzaStringDescription.contains("Chicago")){
            if(pizzaStringDescription.contains("Deluxe")){
                pizza = new ChicagoPizza().createDeluxe(size);
            }
            else if(pizzaStringDescription.contains("BBQ Chicken")){
                pizza = new ChicagoPizza().createBBQChicken(size);
            }
            else if(pizzaStringDescription.contains("Meatzza")){
                pizza = new ChicagoPizza().createMeatzza(size);
            }
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
            if(pizzaStringDescription.contains("Deluxe")){
                pizza = new NYPizza().createDeluxe(size);
            }
            else if(pizzaStringDescription.contains("BBQ Chicken")){
                pizza = new NYPizza().createBBQChicken(size);
            }
            else if(pizzaStringDescription.contains("Meatzza")){
                pizza = new NYPizza().createMeatzza(size);
            }
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

    public int getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(int orderNumber) {
        this.orderNumber = orderNumber;
    }

    public void setPizzaStringDescription(String pizzaStringDescription) {
        this.pizzaStringDescription = pizzaStringDescription;
    }



    public double getSubtotal() {
        return subtotal;
    }

//    public double getTotal() {
//        return subtotal*1.06625;
//    }

//    public void setQuantity(int quantity) {
//        this.quantity = quantity;
//    }


    public void setSubtotal(double subtotal) {
        this.subtotal = subtotal;
    }


}

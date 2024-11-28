package com.example.project5;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class CurrentOrder extends AppCompatActivity {

    private RecyclerView currentOrdersRecyclerView;
    private RecyclerView storeOrdersRecyclerView;
    private OrderAdapter currentOrdersAdapter;

    private List<String> pizzaStringList = PizzaSingleton.getPizzasString();
    private List<Pizza> pizzas = PizzaSingleton.getPizzas();
    private final int ZERO=0;
    private double subtotalPizzas=ZERO;
    private double salesTaxPizzas=ZERO;
    private double totalCostPizzas=ZERO;
    private final int ONE=1;

    private final double TAX=0.06625;






    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_current_order_page);

        // Initialize RecyclerViews
        currentOrdersRecyclerView = findViewById(R.id.currentOrdersRecyclerView);


        // Set Layout Managers
        currentOrdersRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Initialize Data
        List<OrderView> currentOrders = getCurrentOrders();

        // Initialize Adapters
        currentOrdersAdapter = new OrderAdapter(currentOrders);

        // Set Adapters
        currentOrdersRecyclerView.setAdapter(currentOrdersAdapter);
    }

    private List<OrderView> getCurrentOrders() {
        List<OrderView> orders = new ArrayList<>();

        // Fetch data from PizzaSingleton
        List<Pizza> pizzas = PizzaSingleton.getPizzas();
        List<String> pizzaStrings = PizzaSingleton.getPizzasString();

        // Iterate through the pizzas and create OrderView objects
        for (int i = 0; i < pizzas.size(); i++) {
            Pizza pizza = pizzas.get(i);
            String name = pizza.toString(); // Assuming `Pizza` has a getName() method
//            int quantity = pizza.getQuantity(); // Assuming `Pizza` has a getQuantity() method
            double subtotal = pizza.price(); // Assuming `Pizza` has a getSubtotal() method
//            double total = pizza.getTotal(); // Assuming `Pizza` has a getTotal() method

            // Create an OrderView object
            orders.add(new OrderView(name, subtotal));
        }

        return orders;
    }

    public void subTotal() {
        for (int i = 0; i < PizzaSingleton.getPizzas().size(); i++) {
            subtotalPizzas += PizzaSingleton.getPizzas().get(i).price();
        }
    }

    public void salesTax() {
        salesTaxPizzas = subtotalPizzas * TAX;
    }



    public void totalCost() {
        totalCostPizzas = subtotalPizzas + salesTaxPizzas;
    }

    public void placeOrderOnButtonClicked(){
        if(PizzaSingleton.getPizzasString().isEmpty()){
//            showAlert("Error", "Please add a pizza to place an order.");
            return;
        }
        String order = "";
        for(int i=0; i<PizzaSingleton.getPizzasString().size(); i++){
            order+=PizzaSingleton.getPizzasString().get(i)+"\n";
        }
        PizzaSingleton.getOrderList().add(order);
        PizzaSingleton.setOrderNumber(PizzaSingleton.getOrderNumber()+1);;
        PizzaSingleton.getOrderNumberList().add(PizzaSingleton.getOrderNumber());
        PizzaSingleton.getPizzas().clear();
        PizzaSingleton.getPizzasString().clear();
        recalculateTotals();
        int orderNum=PizzaSingleton.getOrderNumber()+1;
//        orderNumberField.setText(orderNum+"");
    }

    private void recalculateTotals() {
        subtotalPizzas = ZERO;
        salesTaxPizzas = ZERO;
        totalCostPizzas = ZERO;
        for (int i = 0; i < PizzaSingleton.getPizzas().size(); i++) {
            subtotalPizzas += PizzaSingleton.getPizzas().get(i).price();
        }
        salesTaxPizzas = subtotalPizzas * TAX;
        totalCostPizzas = subtotalPizzas + salesTaxPizzas;
//        subtotalField.setText(String.format("%.2f", subtotalPizzas));
//        salesTaxField.setText(String.format("%.2f", salesTaxPizzas));
//        totalCostField
    }


}

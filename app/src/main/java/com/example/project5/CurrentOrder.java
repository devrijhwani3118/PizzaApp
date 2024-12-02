package com.example.project5;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class CurrentOrder extends AppCompatActivity {

    private RecyclerView currentOrdersRecyclerView;
    private OrderAdapter currentOrdersAdapter;
    private int orderNumber;

    private List<OrderView> currentOrders; // Store current orders locally
    private final int ZERO = 0;
    private double subtotalPizzas = ZERO;
    private double salesTaxPizzas = ZERO;
    private double totalCostPizzas = ZERO;
    private final double TAX = 0.06625;

    private Button backButton;
    private ImageView pizzaPicture;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_current_order_page);
        backButton=findViewById(R.id.back_button_current_order);
        // Initialize RecyclerView
        currentOrdersRecyclerView = findViewById(R.id.currentOrdersRecyclerView);
        currentOrdersRecyclerView.setLayoutManager(new LinearLayoutManager(this));


        // Initialize Data
        currentOrders = getCurrentOrders();
        currentOrdersAdapter = new OrderAdapter(currentOrders);

        if(PizzaSingleton.getOrderNumber()==0) PizzaSingleton.setOrderNumber(1);
        orderNumber=PizzaSingleton.getOrderNumber();
        TextView orderNumberText = findViewById(R.id.order_number_int);
        orderNumberText.setText(String.valueOf(orderNumber));

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Create an intent to navigate back to MainActivity
                Intent intent = new Intent(CurrentOrder.this, MainActivity.class);
                startActivity(intent);
//                finish(); // Optional, if you don't want the user to return to this activity
            }
        });

        // Set Adapter
        currentOrdersRecyclerView.setAdapter(currentOrdersAdapter);

        // Handle "Add to Order" button click
        Button addToOrderButton = findViewById(R.id.add_to_order_button);
        addToOrderButton.setOnClickListener(v -> addAllItemsToOrder());
    }

    private List<OrderView> getCurrentOrders() {
        List<OrderView> orders = new ArrayList<>();

        // Fetch data from PizzaSingleton
        List<Pizza> pizzas = PizzaSingleton.getPizzas();
        List<String> pizzaStrings = PizzaSingleton.getPizzasString();
        List<Integer> orderNumberList = PizzaSingleton.getOrderNumberList();

        if (pizzas == null || pizzaStrings == null) return orders;

        // Create OrderView objects
        for (int i = 0; i < pizzas.size(); i++) {
            Pizza pizza = pizzas.get(i);
            String pizzaString = pizzaStrings.get(i);
            int orderNum = orderNumberList.get(i);

            orders.add(new OrderView(pizza.price(), pizzaString, orderNum));
        }

        return orders;
    }

    private void addAllItemsToOrder() {
        if (currentOrders.isEmpty()) {
            Toast.makeText(this, "No items to add!", Toast.LENGTH_SHORT).show();
            return;
        }

        // Add items to PizzaSingleton
//        for (OrderView orderView : currentOrders) {
//            if (!PizzaSingleton.getPizzasString().contains(orderView.getPizzaStringDescription())) {
//                PizzaSingleton.getPizzasString().add(orderView.getPizzaStringDescription());
//                PizzaSingleton.getPizzas().add(orderView.getPizza());
//            }
//        }

        // Update order list and order number


        String order = "";
        for(int i=0; i<PizzaSingleton.getInstance().getPizzasString().size(); i++){
            order+=PizzaSingleton.getInstance().getPizzasString().get(i)+"\n";
        }
        PizzaSingleton.getInstance().getOrderList().add(order);
        PizzaSingleton.getInstance().setOrderNumber(PizzaSingleton.getInstance().getOrderNumber() + 1);
        PizzaSingleton.getInstance().getOrderNumberList().add(PizzaSingleton.getInstance().getOrderNumber());
        PizzaSingleton.getInstance().getOrders().add(new Order (PizzaSingleton.getInstance().getOrderNumber(), PizzaSingleton.getInstance().getPizzas()));

        // Clear current orders
        currentOrders.clear();
        currentOrdersAdapter.notifyDataSetChanged();

        // Show confirmation
        Toast.makeText(this, "Order placed successfully!", Toast.LENGTH_SHORT).show();
        PizzaSingleton.getInstance().setPizzasString(new ArrayList<String>());
        PizzaSingleton.getInstance().setPizzas(new ArrayList<Pizza>());
        // Recalculate totals
        recalculateTotals();
    }

    private void recalculateTotals() {
        subtotalPizzas = ZERO;
        salesTaxPizzas = ZERO;
        totalCostPizzas = ZERO;

        for (Pizza pizza : PizzaSingleton.getInstance().getPizzas()) {
            subtotalPizzas += pizza.price();
        }
        salesTaxPizzas = subtotalPizzas * TAX;
        totalCostPizzas = subtotalPizzas + salesTaxPizzas;
    }
}

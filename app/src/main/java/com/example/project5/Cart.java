package com.example.project5;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class Cart extends AppCompatActivity {

    private RecyclerView currentOrdersRecyclerView;
    private RecyclerView storeOrdersRecyclerView;
    private OrderAdapter currentOrdersAdapter;
    private OrderAdapter storeOrdersAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cart);

        // Initialize RecyclerViews
        currentOrdersRecyclerView = findViewById(R.id.currentOrdersRecyclerView);
        storeOrdersRecyclerView = findViewById(R.id.storeOrdersRecyclerView);

        // Set Layout Managers
        currentOrdersRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        storeOrdersRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Initialize Data
        List<OrderView> currentOrders = getCurrentOrders();
        List<OrderView> storeOrders = getStoreOrders();

        // Initialize Adapters
        currentOrdersAdapter = new OrderAdapter(currentOrders);
        storeOrdersAdapter = new OrderAdapter(storeOrders);

        // Set Adapters
        currentOrdersRecyclerView.setAdapter(currentOrdersAdapter);
        storeOrdersRecyclerView.setAdapter(storeOrdersAdapter);
    }

    private List<OrderView> getCurrentOrders() {
        // Mock data for current orders
        List<OrderView> orders = new ArrayList<>();
        //orders.add(new Order("Pizza Margherita", 1, 12.99));
        //orders.add(new Order("Pepperoni Pizza", 2, 15.99));
        return orders;
    }

    private List<OrderView> getStoreOrders() {
        // Mock data for store orders
        List<OrderView> orders = new ArrayList<>();
        //orders.add(new Order("Veggie Pizza", 1, 10.99));
        //orders.add(new Order("BBQ Chicken Pizza", 3, 18.99));
        return orders;
    }
}

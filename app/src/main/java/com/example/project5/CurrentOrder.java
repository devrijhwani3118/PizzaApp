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
        // Mock data for current orders
        List<OrderView> orders = new ArrayList<>();
        //orders.add(new Order("Pizza Margherita", 1, 12.99));
        //orders.add(new Order("Pepperoni Pizza", 2, 15.99));
        return orders;
    }

}

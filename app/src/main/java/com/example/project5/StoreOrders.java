package com.example.project5;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import java.util.ArrayList;

public class StoreOrders extends AppCompatActivity {

    private static final int NOT_FOUND = -1;
    private static final double TAX = 0.06625;
    private Spinner orderNumberSpinner;
    private ListView orderListView;
    private TextView orderTotalWithTax;
    private Button cancelButton;
    private Button backButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_store_orders);

        // Adjust window insets for edge-to-edge
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Initialize UI components
        orderNumberSpinner = findViewById(R.id.spinner);
        orderListView = findViewById(R.id.listView);
        orderTotalWithTax = findViewById(R.id.priceWordTextViewStoreOrders);
        cancelButton = findViewById(R.id.cancelOrderStoreOrders);
        backButton = findViewById(R.id.back_button_store_orders);

        // Setup and populate views
        initializeOrders();
        setupListeners();

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Create an intent to navigate back to MainActivity
                Intent intent = new Intent(StoreOrders.this, MainActivity.class);
                startActivity(intent);
//                finish(); // Optional, if you don't want the user to return to this activity
            }
        });
    }

    private void initializeOrders() {
        ArrayAdapter<Integer> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, PizzaSingleton.getInstance().getOrderNumberList());
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        orderNumberSpinner.setAdapter(adapter);

        if (!PizzaSingleton.getInstance().getOrderNumberList().isEmpty()) {
            orderNumberSpinner.setPrompt("Choose an Order");
        } else {
            orderNumberSpinner.setPrompt("No Orders Placed");
        }
    }

    private void setupListeners() {
        cancelButton.setOnClickListener(view -> cancelOrder());

        orderNumberSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                displayOrder();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // Handle the case where nothing is selected, if necessary
            }
        });
    }


    private void displayOrder() {
        int selectedOrderIndex = orderNumberSpinner.getSelectedItemPosition();
        if (selectedOrderIndex == NOT_FOUND) {
            return;
        }

        ArrayList<String> orderList = PizzaSingleton.getOrderList();
        String selectedOrderDetails = orderList.get(selectedOrderIndex);

        // Populate the ListView with order items
        String[] items = selectedOrderDetails.split("\n");
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, items);
        orderListView.setAdapter(adapter);

        // Calculate and display the total cost
        //calculateTotalCost(items);
    }

    private void calculateTotalCost(String[] orderItems) {
        double subtotal = 0;

        for (String order : orderItems) {
            String money = order.substring(order.indexOf("$") + 1);
            subtotal += Double.parseDouble(money);
        }

        double salesTax = subtotal * TAX;
        double totalCost = subtotal + salesTax;
        orderTotalWithTax.setText(String.format("$%.2f", totalCost));
    }

    private void cancelOrder() {
        int selectedOrderIndex = orderNumberSpinner.getSelectedItemPosition();
        if (selectedOrderIndex == NOT_FOUND) {
            showAlert("No Order Selected", "Please select an order to cancel.");
            return;
        }

        ArrayList<Integer> orderNumbers = PizzaSingleton.getOrderNumberList();
        ArrayList<String> orderList = PizzaSingleton.getOrderList();

        // Remove the order and update the views
        orderNumbers.remove(selectedOrderIndex);
        orderList.remove(selectedOrderIndex);
        initializeOrders();
        orderListView.setAdapter(null);
        orderTotalWithTax.setText("");

        showAlert("Order Canceled", "The selected order has been successfully canceled.");
    }


    private void showAlert(String title, String message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(title)
                .setMessage(message)
                .setPositiveButton("OK", (dialog, which) -> dialog.dismiss())
                .show();
    }
}

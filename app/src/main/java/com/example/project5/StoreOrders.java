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

    private Spinner orderNumberSpinner;
    private ListView orderListView;
    private TextView orderTotalWithTax;
    private Button cancelButton;
    private Button backButton;

    private static final int NOT_FOUND = -1;
    private static int orderNumber = 0;
    private final int ZERO=0;
    private final double TAX=0.06625;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_store_orders);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        orderNumberSpinner = findViewById(R.id.spinner);
        orderListView = findViewById(R.id.listView);
        orderTotalWithTax = findViewById(R.id.priceWordTextViewStoreOrders2);
        cancelButton = findViewById(R.id.cancelOrderStoreOrders);
        backButton = findViewById(R.id.back_button_store_orders);
        initializeOrders();
        setupListeners();
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(StoreOrders.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }

    private void calculateTotalCost(ArrayList<String> orderItems) {
        double subtotalPizzas=0;
        for(int i = 0; i < orderItems.size(); i++) {
            String order=orderItems.get(i);
            String money=order.substring(order.indexOf("$")+1);
            subtotalPizzas += Double.parseDouble(money);
        }
        double salesTaxPizzas = subtotalPizzas * TAX;
        double totalCostPizzas = subtotalPizzas + salesTaxPizzas;
        orderTotalWithTax.setText(String.format("%.2f", totalCostPizzas));
    }

    private void initializeOrders() {
        ArrayAdapter<Integer> adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_item, PizzaSingleton.getInstance().getOrderNumberList());
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
        String[] items = selectedOrderDetails.split("\n");
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, items);
        orderListView.setAdapter(adapter);
        ArrayList orderItems = new ArrayList();
        for (String item : items) {
            orderItems.add(item);
        }
        calculateTotalCost(orderItems);
    }

    private void cancelOrder() {
        int selectedOrderIndex = orderNumberSpinner.getSelectedItemPosition();
        if (selectedOrderIndex == NOT_FOUND) {
            showAlert("No Order Selected", "Please select an order to cancel.");
            return;
        }
        ArrayList<Integer> orderNumbers = PizzaSingleton.getOrderNumberList();
        ArrayList<String> orderList = PizzaSingleton.getOrderList();
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

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

/**
 * Activity class for managing and viewing store orders.
 * Provides functionality to display orders, calculate totals, and cancel orders.
 * @author Sri Akshara Kollu, Dev Rijhwani
 */
public class StoreOrders extends AppCompatActivity {

    private Spinner orderNumberSpinner;
    private ListView orderListView;
    private TextView orderTotalWithTax;
    private Button cancelButton;
    private Button backButton;

    private static final int NOT_FOUND = -1;
    private static int orderNumber = 0;
    private final int ZERO=0;
    private final int ONE=1;
    private final double TAX=0.06625;

    /**
     * Calculates and displays the total cost (subtotal + tax) of the selected order.
     * @param orderItems List of order items.
     */
    private void calculateTotalCost(ArrayList<String> orderItems) {
        double subtotalPizzas=ZERO;
        for(int i = 0; i < orderItems.size(); i++) {
            String order=orderItems.get(i);
            String money=order.substring(order.indexOf("$")+ONE);
            subtotalPizzas += Double.parseDouble(money);
        }
        double salesTaxPizzas = subtotalPizzas * TAX;
        double totalCostPizzas = subtotalPizzas + salesTaxPizzas;
        orderTotalWithTax.setText(String.format("$%.2f", totalCostPizzas));
    }

    /**
     * Initializes the spinner with available order numbers.
     * Updates the prompt based on whether there are any orders.
     */
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

    /**
     * Sets up event listeners for UI components like buttons and spinner.
     */
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

    /**
     * Displays the details of the selected order in the list view.
     * Calculates and shows the total cost of the order.
     */
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

    /**
     * Cancels the selected order and updates the UI accordingly.
     */
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

    /**
     * Displays an alert dialog with the specified title and message.
     * @param title The title of the alert dialog.
     * @param message The message of the alert dialog.
     */
    private void showAlert(String title, String message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(title)
                .setMessage(message)
                .setPositiveButton("OK", (dialog, which) -> dialog.dismiss())
                .show();
    }

    /**
     * Called when the activity is created.
     * Initializes UI components, sets up event listeners, and loads orders.
     */
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
}

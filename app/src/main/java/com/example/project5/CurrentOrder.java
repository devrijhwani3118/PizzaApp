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

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

public class CurrentOrder extends AppCompatActivity implements OrderAdapter.OrderUpdateListener  {

    private RecyclerView currentOrdersRecyclerView;
    private OrderAdapter currentOrdersAdapter;
    private int orderNumber;

    private List<OrderView> currentOrders; // Store current orders locally
    private static final int ZERO = 0;
    private final int ONE =1;
    private double salesTaxPizzas = ZERO;
    private double totalCostPizzas = ZERO;
    private final double TAX = 0.06625;

    private Button backButton;
    private Button clearOrderButton;
    private ImageView pizzaPicture;
    private TextView totalCostField;
    private TextView subtotalField;
    private TextView salesTaxField;
    private Button addToOrderButton;
    private TextView orderNumberText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_current_order_page);
        setIDs();
        currentOrdersRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        currentOrders = getCurrentOrders();
        currentOrdersAdapter = new OrderAdapter(currentOrders, this);
        currentOrdersRecyclerView.setAdapter(currentOrdersAdapter);
        if(PizzaSingleton.getOrderNumber()==0) PizzaSingleton.setOrderNumber(1);
        orderNumber=PizzaSingleton.getOrderNumber();
        orderNumberText.setText(String.valueOf(orderNumber));
        clearOrderButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clearingOrder();
            }
        });
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CurrentOrder.this, MainActivity.class);
                startActivity(intent);
            }
        });
        PizzaSingleton.getInstance().setSubtotalPizzas(ZERO);
        recalculateTotals();
        currentOrdersRecyclerView.setAdapter(currentOrdersAdapter);
        addToOrderButton.setOnClickListener(v -> addAllItemsToOrder());
    }

    private void setIDs(){
        backButton=findViewById(R.id.back_button_current_order);
        subtotalField=findViewById(R.id.subtotalCurrentView2);
        salesTaxField=findViewById(R.id.taxTotalCurrentView2);
        totalCostField=findViewById(R.id.totalCurrentView2);
        clearOrderButton = findViewById(R.id.clear_orders_current_order);
        currentOrdersRecyclerView = findViewById(R.id.currentOrdersRecyclerView);
        addToOrderButton = findViewById(R.id.add_to_order_button);
        orderNumberText = findViewById(R.id.order_number_int);
    }

    private void clearingOrder(){
        PizzaSingleton.setSubtotalPizzas(ZERO);
        PizzaSingleton.setPizzasString(new ArrayList<String>());
        PizzaSingleton.setPizzas(new ArrayList<Pizza>());
        if(currentOrders.isEmpty()) Toast.makeText(this, "No items to clear!", Toast.LENGTH_SHORT).show();
        else Toast.makeText(this, "Items cleared!", Toast.LENGTH_SHORT).show();
        currentOrders.clear();
        currentOrdersAdapter.notifyDataSetChanged();
        recalculateTotals();
    }

    private List<OrderView> getCurrentOrders() {
        List<OrderView> orders = new ArrayList<>();
        List<Pizza> pizzas = PizzaSingleton.getPizzas();
        List<String> pizzaStrings = PizzaSingleton.getPizzasString();
        if (pizzas == null || pizzaStrings == null) return orders;
        for (int i = 0; i < pizzas.size(); i++) {
            Pizza pizza = pizzas.get(i);
            String pizzaString = pizzaStrings.get(i);
            orders.add(new OrderView(pizza.price(), pizzaString));
        }
        return orders;
    }

    private void addAllItemsToOrder() {
        if (currentOrders.isEmpty()) {
            Toast.makeText(this, "No items to add!", Toast.LENGTH_SHORT).show();
            return;
        }
        String order = "";
        for (int i = 0; i < PizzaSingleton.getInstance().getPizzasString().size(); i++) {
            order += PizzaSingleton.getInstance().getPizzasString().get(i) + "\n";
        }
        PizzaSingleton.getInstance().getOrderList().add(order);
        PizzaSingleton.getInstance().getOrderNumberList().add(PizzaSingleton.getInstance().getOrderNumber());
        PizzaSingleton.getInstance().setOrderNumber(PizzaSingleton.getInstance().getOrderNumber() + 1);
        PizzaSingleton.getInstance().getOrders().add(new Order(PizzaSingleton.getInstance().getOrderNumber(), PizzaSingleton.getInstance().getPizzas()));
        currentOrders.clear();
        currentOrdersAdapter.notifyDataSetChanged();
        TextView orderNumberText = findViewById(R.id.order_number_int);
        orderNumberText.setText(String.valueOf(PizzaSingleton.getInstance().getOrderNumber()));
        PizzaSingleton.getInstance().setPizzasString(new ArrayList<>());
        PizzaSingleton.getInstance().setPizzas(new ArrayList<>());
        PizzaSingleton.setSubtotalPizzas(0.0);
        recalculateTotals();
        Toast.makeText(this, "Order placed successfully!", Toast.LENGTH_SHORT).show();
    }
    private void recalculateTotals() {
        salesTaxPizzas = ZERO;
        totalCostPizzas = ZERO;
        for (Pizza pizza : PizzaSingleton.getInstance().getPizzas()) {
            PizzaSingleton.getInstance().setSubtotalPizzas(pizza.price()+PizzaSingleton.getInstance().getSubtotalPizzas());
        }
        salesTaxPizzas = PizzaSingleton.getInstance().getSubtotalPizzas() * TAX;
        totalCostPizzas = PizzaSingleton.getInstance().getSubtotalPizzas() + salesTaxPizzas;
        subtotalField.setText(String.format("$%.2f", PizzaSingleton.getInstance().getSubtotalPizzas()));
        salesTaxField.setText(String.format("$%.2f", salesTaxPizzas));
        totalCostField.setText(String.format("$%.2f", totalCostPizzas));
    }

    public void onOrderUpdated() {
        PizzaSingleton.setSubtotalPizzas(ZERO);
        recalculateTotals();
        Toast.makeText(this, "Pizza canceled successfully!", Toast.LENGTH_SHORT).show();
    }
}

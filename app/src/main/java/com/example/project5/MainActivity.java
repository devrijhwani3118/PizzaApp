package com.example.project5;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    private ImageView mainPizzaPicture;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        mainPizzaPicture=findViewById(R.id.imageView4);
        int pizzaImage= R.drawable.pizza_chef;
        mainPizzaPicture.setImageResource(pizzaImage);
        Button button1 = findViewById(R.id.orderHereButton);
        button1.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, OrderPage.class);
            startActivity(intent);
        });
        Button button2  = findViewById(R.id.currentOrdersButton);
        button2.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, CurrentOrder.class);
            startActivity(intent);
        });
        Button button3  = findViewById(R.id.storeOrdersButton);
        button3.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, StoreOrders.class);
            startActivity(intent);
        });
        Button buttonTopping  = findViewById(R.id.topping_button);
        buttonTopping.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, ToppingMenu.class);
            startActivity(intent);
        });
    }
}
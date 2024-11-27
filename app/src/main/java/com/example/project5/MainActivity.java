package com.example.project5;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageButton;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

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

        Button button1 = findViewById(R.id.orderHereButton);

        // Set OnClickListener
        button1.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, OrderPage.class);
            startActivity(intent);
        });


        // Find the ImageButton
        Button button2  = findViewById(R.id.currentOrdersButton);

        // Set OnClickListener
        button2.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, Cart.class);
            startActivity(intent);
        });

    }



}
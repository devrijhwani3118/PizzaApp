package com.example.project5;

import android.content.Intent;
import android.os.Bundle;
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

        ImageButton imageButton1 = findViewById(R.id.imageButton1);

        // Set OnClickListener
        imageButton1.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, OrderPage.class);
            startActivity(intent);
        });


        // Find the ImageButton
        ImageButton imageButton2 = findViewById(R.id.imageButton2);

        // Set OnClickListener
        imageButton2.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, Cart.class);
            startActivity(intent);
        });

    }



}
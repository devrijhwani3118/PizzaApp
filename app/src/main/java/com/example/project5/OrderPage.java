package com.example.project5;

import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.chip.ChipGroup;

public class OrderPage extends AppCompatActivity {

    private final double basePrice = 8.99;
    private final double SMALLDELUXE = 16.99;
    private final double MIDDELUXE = 18.99;
    private final double LARGEDELUXE = 20.99;
    private final double SMALLBBQ = 14.99;
    private final double MIDBBQ = 16.99;
    private final double LARGEBBQ = 19.99;
    private final double SMALLMEAT = 17.99;
    private final double MIDMEAT = 19.99;
    private final double LARGEMEAT = 21.99;
    private final double SMALLBUILD = 8.99;
    private final double MIDBUILD = 10.99;
    private final double LARGEBUILD = 12.99;
    private final double TOPPING_COST = 1.69;
    private final int MAX_TOPPINGS = 7;
    private double pizzaCost = 0;
    private Spinner pizzaTypeSpinner;
    private RadioGroup radioGroup;
    private TextView crustTextView;
    private TextView pizzaPriceTextView;
    private Button addToOrderButton;
    private ImageView pizzaImageView;
    private ChipGroup chipGroup;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_order_page);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }


}
package com.example.project5;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

public class ToppingMenu extends AppCompatActivity {
    private RecyclerView toppingsRV;

    private Button backButton;

    private ToppingAdapter toppingAdapter ;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_toppings_menu);
        toppingsRV = findViewById(R.id.toppingRV);
        backButton = findViewById(R.id.back_button_topping_menu);
        toppingsRV.setLayoutManager(new LinearLayoutManager(this));
        ArrayList<ToppingView> toppingList = new ArrayList<>();
        toppingList.add(new ToppingView("Sausage"));
        toppingList.add(new ToppingView("Pepperoni"));
        toppingList.add(new ToppingView("Green Pepper"));
        toppingList.add(new ToppingView("Onion"));
        toppingList.add(new ToppingView("Mushroom"));
        toppingList.add(new ToppingView("BBQ Chicken"));
        toppingList.add(new ToppingView("Provolone"));
        toppingList.add(new ToppingView("Cheddar"));
        toppingList.add(new ToppingView("Beef"));
        toppingList.add(new ToppingView("Ham"));
        toppingList.add(new ToppingView("Pineapple"));
        toppingList.add(new ToppingView("Olives"));
        toppingList.add(new ToppingView("Spinach"));
        toppingAdapter = new ToppingAdapter(toppingList);
        toppingsRV.setAdapter(toppingAdapter);

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ToppingMenu.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }



}

package com.example.project5;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.chip.Chip;
import com.google.android.material.chip.ChipGroup;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class OrderPage extends AppCompatActivity {

    private final double basePrice = 8.99;
    private double pizzaCost = 0;

    private final String[] allToppings = {"Sausage", "Pepperoni", "Green Pepper", "Onion", "Mushroom", "BBQ Chicken", "Provolone", "Cheddar", "Beef", "Ham"};
    private final Set<String> deluxeToppings = new HashSet<>(Arrays.asList("Sausage", "Pepperoni", "Green Pepper", "Onion", "Mushroom"));
    private final Set<String> bbqToppings = new HashSet<>(Arrays.asList("BBQ Chicken", "Green Pepper", "Provolone", "Cheddar"));
    private final Set<String> meatzzaToppings = new HashSet<>(Arrays.asList("Sausage", "Pepperoni", "Beef", "Ham"));

    private int selectedToppingsCount = 0;
    private static final int MAX_TOPPINGS = 7;

    private Chip sausage;
    private Chip pepperoni;
    private Chip greenPepper;
    private Chip onion;
    private Chip mushroom;
    private Chip bbqChicken;
    private Chip provolone;
    private Chip cheddar;
    private Chip beef;
    private Chip ham;


    private RadioGroup radioGroup;
    private RadioButton smallRadioButton;
    private RadioButton mediumRadioButton;
    private RadioButton largeRadioButton;
    private TextView crustTextView;
    private TextView pizzaPriceTextView;
    private ChipGroup chipGroup;
    private Spinner typeOfPizzaSpinner;
    private Spinner typepizzaChoiceSpinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_page);

        typeOfPizzaSpinner = findViewById(R.id.typeOfPizzaSpinner);
        typepizzaChoiceSpinner = findViewById(R.id.typepizzaChoiceSpinner);
        radioGroup = findViewById(R.id.radioGroup);
        smallRadioButton = findViewById(R.id.smallRadioButton);
        mediumRadioButton = findViewById(R.id.mediumRadioButton);
        largeRadioButton = findViewById(R.id.largeRadioButton3);
        chipGroup = findViewById(R.id.chipGroup);
        crustTextView = findViewById(R.id.crustTextView);
        pizzaPriceTextView = findViewById(R.id.priceTextView);
        sausage = findViewById(R.id.chipSausage);
        pepperoni= findViewById(R.id.chipPepperoni);
        greenPepper= findViewById(R.id.chipGreenPepper);
        onion= findViewById(R.id.chipOnion);
        mushroom= findViewById(R.id.chipMushroom);
        bbqChicken= findViewById(R.id.chipBBQChicken);
        provolone= findViewById(R.id.chipProvolone);
        cheddar= findViewById(R.id.chipCheddar);
        beef= findViewById(R.id.chipBeef);
        ham= findViewById(R.id.chipHam);

        smallRadioButton.setChecked(true);

        ArrayAdapter<String> pizzaTypeAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, new String[]{"ChicagoPizza", "NYPizza"});
        pizzaTypeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        typeOfPizzaSpinner.setAdapter(pizzaTypeAdapter);

        ArrayAdapter<String> pizzaChoiceAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, new String[]{"Build Your Own", "Deluxe", "Meatzza", "BBQ Chicken"});
        pizzaChoiceAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        typepizzaChoiceSpinner.setAdapter(pizzaChoiceAdapter);

        typeOfPizzaSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                updateCrustTextView(typeOfPizzaSpinner.getSelectedItem().toString(), typepizzaChoiceSpinner.getSelectedItem().toString());
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
            }
        });

        typepizzaChoiceSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                String selectedPizza = parentView.getItemAtPosition(position).toString();
                updateToppingChips(selectedPizza);
                updateCrustTextView(typeOfPizzaSpinner.getSelectedItem().toString(), selectedPizza);
                updatePizzaPrice();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
            }
        });
    }

    private void updateToppingChips(String selectedPizza) {
        Set<String> enabledToppings = new HashSet<>();
        if ("Deluxe".equals(selectedPizza)) {
            enabledToppings = deluxeToppings;
            enableDeluxeTopping(enabledToppings);
        } else if ("BBQ Chicken".equals(selectedPizza)) {
            enabledToppings = bbqToppings;
            enableBBQTopping(enabledToppings);
        } else if ("Meatzza".equals(selectedPizza)) {
            enabledToppings = meatzzaToppings;
            enableMeatzzaTopping(enabledToppings);
        }
        else enableAllChips();
//        else if ("Build Your Own".equals(selectedPizza)) {
//            enabledToppings.addAll(Arrays.asList(allToppings));
//        }
//
//        selectedToppingsCount = 0;
//        for (int i = 0; i < chipGroup.getChildCount(); i++) {
//            Chip chip = (Chip) chipGroup.getChildAt(i);
//            String topping = chip.getText().toString();
//            boolean isEnabled = enabledToppings.contains(topping);
//            chip.setEnabled(isEnabled);
//            if (isEnabled) {
//                chip.setChecked(enabledToppings.contains(topping));
//                if (chip.isChecked()) {
//                    selectedToppingsCount++;
//                }
//            }
//        }
//        if ("Build Your Own".equals(selectedPizza)) {
//            enableAllChips();
//        }
    }

//
//    private final Set<String> deluxeToppings = new HashSet<>(Arrays.asList("Sausage", "Pepperoni", "Green Pepper", "Onion", "Mushroom"));
    private void enableMeatzzaTopping(Set<String> enabledToppings) {
        for (int i = 0; i < chipGroup.getChildCount(); i++) {
            Chip chip = (Chip) chipGroup.getChildAt(i);
            chip.setChipBackgroundColorResource(android.R.color.white);
        }
        for (int i = 0; i < chipGroup.getChildCount(); i++) {
            Chip chip = (Chip) chipGroup.getChildAt(i);
            if(chip.equals(sausage) || chip.equals(pepperoni)|| chip.equals(beef)|| chip.equals(ham)) {
                chip.setEnabled(true);
                chip.setChipBackgroundColorResource(android.R.color.holo_blue_light);
            }
            else chip.setEnabled(false);
        }
    }

    private void enableBBQTopping(Set<String> enabledToppings) {
        for (int i = 0; i < chipGroup.getChildCount(); i++) {
            Chip chip = (Chip) chipGroup.getChildAt(i);
            chip.setChipBackgroundColorResource(android.R.color.white);
        }
        for (int i = 0; i < chipGroup.getChildCount(); i++) {
            Chip chip = (Chip) chipGroup.getChildAt(i);
            if(chip.equals(bbqChicken) || chip.equals(greenPepper)|| chip.equals(provolone)|| chip.equals(cheddar)) {
                chip.setEnabled(true);
                chip.setChipBackgroundColorResource(android.R.color.holo_blue_light);
            }
            else chip.setEnabled(false);
        }
    }

    private void enableDeluxeTopping(Set<String> enabledToppings) {
        for (int i = 0; i < chipGroup.getChildCount(); i++) {
            Chip chip = (Chip) chipGroup.getChildAt(i);
            chip.setChipBackgroundColorResource(android.R.color.white);
        }
        for (int i = 0; i < chipGroup.getChildCount(); i++) {
            Chip chip = (Chip) chipGroup.getChildAt(i);
            if(chip.equals(sausage) || chip.equals(pepperoni) || chip.equals(greenPepper) || chip.equals(onion) || chip.equals(mushroom)){
                chip.setEnabled(true);
                chip.setChipBackgroundColorResource(android.R.color.holo_blue_light);
            }
            else chip.setEnabled(false);
        }
    }

    private void enableAllChips() {
        for (int i = 0; i < chipGroup.getChildCount(); i++) {
            Chip chip = (Chip) chipGroup.getChildAt(i);
            chip.setEnabled(true);
        }
    }

    private void handleToppingSelection(Chip chip) {
        if (chip.isChecked()) {
            selectedToppingsCount++;
            if (selectedToppingsCount >= MAX_TOPPINGS) {
                disableUncheckedChips();
            }
        } else {
            selectedToppingsCount--;
            if (selectedToppingsCount < MAX_TOPPINGS) {
                enableAllChips();
            }
        }
    }

    private void disableUncheckedChips() {
        for (int i = 0; i < chipGroup.getChildCount(); i++) {
            Chip chip = (Chip) chipGroup.getChildAt(i);
            if (!chip.isChecked()) {
                chip.setEnabled(false);
            }
        }
    }

    private void updateCrustTextView(String pizzaType, String pizzaChoice) {
        if (pizzaChoice.equals("Build Your Own")) {
            crustTextView.setText(pizzaType.equals("ChicagoPizza") ? "Pan" : "Hand-tossed");
        } else if (pizzaChoice.equals("Deluxe")) {
            crustTextView.setText(pizzaType.equals("ChicagoPizza") ? "Deep Dish" : "Brooklyn");
        } else if (pizzaChoice.equals("BBQ Chicken")) {
            crustTextView.setText(pizzaType.equals("ChicagoPizza") ? "Pan" : "Thin");
        } else if (pizzaChoice.equals("Meatzza")) {
            crustTextView.setText(pizzaType.equals("ChicagoPizza") ? "Stuffed" : "Hand-tossed");
        } else {
            crustTextView.setText(""); // Clear text if no valid selection
        }
        crustTextView.setEnabled(false);
    }

    private void updatePizzaPrice() {
        pizzaCost = basePrice;
        if (mediumRadioButton.isChecked()) {
            pizzaCost += 2.00;
        } else if (largeRadioButton.isChecked()) {
            pizzaCost += 4.00;
        }
        pizzaCost += selectedToppingsCount * 1.50;
        pizzaPriceTextView.setText(String.format("$%.2f", pizzaCost));
    }
}

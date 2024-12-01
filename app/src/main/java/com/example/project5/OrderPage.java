package com.example.project5;

import android.content.Intent;
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
import android.widget.Toast;

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
    private TextView pizzaPrice;
    private ChipGroup chipGroup;
    private Spinner pizzaStyleSpinner;
    private Spinner pizzaTypeSpinner;

    private ImageView pizzaPicture;

    private TextView price;

    private Button backButton;
    private Button orderButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_page);

        pizzaStyleSpinner = findViewById(R.id.typeOfPizzaSpinner);
        pizzaTypeSpinner = findViewById(R.id.typepizzaChoiceSpinner);
        radioGroup = findViewById(R.id.radioGroup);
        smallRadioButton = findViewById(R.id.smallRadioButton);
        mediumRadioButton = findViewById(R.id.mediumRadioButton);
        largeRadioButton = findViewById(R.id.largeRadioButton3);
        chipGroup = findViewById(R.id.chipGroup);
        crustTextView = findViewById(R.id.crustTextView);
        pizzaPrice = findViewById(R.id.priceTextView);
        sausage = findViewById(R.id.chipSausage);
        pepperoni = findViewById(R.id.chipPepperoni);
        greenPepper = findViewById(R.id.chipGreenPepper);
        onion = findViewById(R.id.chipOnion);
        mushroom = findViewById(R.id.chipMushroom);
        bbqChicken = findViewById(R.id.chipBBQChicken);
        provolone = findViewById(R.id.chipProvolone);
        cheddar = findViewById(R.id.chipCheddar);
        beef = findViewById(R.id.chipBeef);
        ham = findViewById(R.id.chipHam);
        pizzaPicture=findViewById(R.id.imageView);
        backButton=findViewById(R.id.back_button);
        orderButton=findViewById(R.id.order_button);


        smallRadioButton.setChecked(true);
        clearAllChipColor();
        setupChipClickListeners();
        ArrayAdapter<String> pizzaTypeAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, new String[]{"ChicagoPizza", "NYPizza"});
        pizzaTypeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        pizzaStyleSpinner.setAdapter(pizzaTypeAdapter);

        ArrayAdapter<String> pizzaChoiceAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, new String[]{"Build Your Own", "Deluxe", "Meatzza", "BBQ Chicken"});
        pizzaChoiceAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        pizzaTypeSpinner.setAdapter(pizzaChoiceAdapter);
        radioGroup.setOnCheckedChangeListener((group, checkedId) -> updatePizzaPrice());

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Create an intent to navigate back to MainActivity
                Intent intent = new Intent(OrderPage.this, MainActivity.class);
                startActivity(intent);
//                finish(); // Optional, if you don't want the user to return to this activity
            }
        });


        pizzaStyleSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                String pizzaStyle= pizzaStyleSpinner.getSelectedItem().toString();
                String pizzaType = pizzaTypeSpinner.getSelectedItem().toString();
                updateCrustTextView(pizzaStyle, pizzaType);
                int pizzaImage=getImage(pizzaStyle, pizzaType);
                pizzaPicture.setImageResource(pizzaImage);
                updatePizzaPrice();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
            }
        });

        pizzaTypeSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                String selectedPizza = parentView.getItemAtPosition(position).toString();
                String pizzaStyle= pizzaStyleSpinner.getSelectedItem().toString();
                String pizzaType = pizzaTypeSpinner.getSelectedItem().toString();
                updateToppingChips(selectedPizza);
                updateCrustTextView(pizzaStyleSpinner.getSelectedItem().toString(), selectedPizza);
                updatePizzaPrice();
                int pizzaImage=getImage(pizzaStyle, pizzaType);
                pizzaPicture.setImageResource(pizzaImage);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
            }
        });

        orderButton.setOnClickListener(v -> {
            createAndSavePizza();
            Intent intent = new Intent(OrderPage.this, CurrentOrder.class);
            startActivity(intent);
        });

    }
//"Build Your Own", "Deluxe", "Meatzza", "BBQ Chicken"
    private int getImage(String pizzaStyle, String pizzaType) {
        if(pizzaStyle.equals("ChicagoPizza")){
            if(pizzaType.equalsIgnoreCase("Build Your Own")){
                return R.drawable.chicago_style_build_your_own;
            }
            else if(pizzaType.equalsIgnoreCase("Deluxe")){
                return R.drawable.chicago_style_deluxe;
            }
            else if(pizzaType.equalsIgnoreCase("Meatzza")){
                return R.drawable.chicago_style_meatzza;
            }
            else{
                return R.drawable.chicago_style_bbq_chicken;
            }
        }
        else{
            if(pizzaType.equalsIgnoreCase("Build Your Own")){
                return R.drawable.ny_style_build_your_own;
            }
            else if(pizzaType.equalsIgnoreCase("Deluxe")){
                return R.drawable.ny_style_deluxe;
            }
            else if(pizzaType.equalsIgnoreCase("Meatzza")){
                return R.drawable.ny_style_meatzza;
            }
            else{
                return R.drawable.ny_style_bbq_chicken;
            }
        }
    }

    private void clearAllChipColor() {
        for (int i = 0; i < chipGroup.getChildCount(); i++) {
            Chip chip = (Chip) chipGroup.getChildAt(i);
            chip.setChipBackgroundColorResource(android.R.color.white); // Reset to default color
        }
    }

    private void setupChipClickListeners() {
        // Set up click listeners for each chip inside the ChipGroup
        for (int i = 0; i < chipGroup.getChildCount(); i++) {
            Chip chip = (Chip) chipGroup.getChildAt(i);
            chip.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Chip clickedChip = (Chip) v;
//                    clickedChip.isChecked();
                    //clearAllChipColor();
                    // If the pizza choice is "Build Your Own", handle topping selection
                    if(pizzaTypeSpinner.getSelectedItem().toString().equals("Build Your Own")) {
                        if (!clickedChip.isSelected()) {
                            // If the chip is selected, change its color and update the topping count
                            clickedChip.setSelected(true);
                            clickedChip.setChipBackgroundColorResource(android.R.color.holo_purple);
                            handleToppingSelection(clickedChip, true);  // Increase count when selected
                        } else {
                            // If the chip is unselected, revert its color and update the topping count
                            clickedChip.setChipBackgroundColorResource(android.R.color.white);
                            handleToppingSelection(clickedChip, false);  // Decrease count when unselected
                            clickedChip.setSelected(false);
                        }
                    }
                }
            });
        }
    }

    private void handleToppingSelection(Chip chip, boolean isSelected) {
        if (isSelected) {
            selectedToppingsCount++;
            // Disable other chips if the limit is reached
            if (selectedToppingsCount >= MAX_TOPPINGS) {
                disableUncheckedChips();
            }
        } else {
            selectedToppingsCount--;
            // Enable chips if the limit is no longer reached
            if (selectedToppingsCount < MAX_TOPPINGS) {
                enableAllChips();
            }
        }
        updatePizzaPrice();
    }


//    private void changeChipColor(Chip clickedChip) {
//        clickedChip.setChipBackgroundColorResource(android.R.color.holo_blue_light); // Set your desired color
//    }

    private void updateToppingChips(String selectedPizza) {
        Set<String> enabledToppings = new HashSet<>();
//        clearAllChipColor();
//        enableAllChips();
//        selectedToppingsCount=0;
        if ("Deluxe".equals(selectedPizza)) {
            enabledToppings = deluxeToppings;
            enableDeluxeTopping(enabledToppings);
        } else if ("BBQ Chicken".equals(selectedPizza)) {
            enabledToppings = bbqToppings;
            enableBBQTopping(enabledToppings);
        } else if ("Meatzza".equals(selectedPizza)) {
            enabledToppings = meatzzaToppings;
            enableMeatzzaTopping(enabledToppings);
        } else{
            enableAllChips();
            clearAllChipColor();
            selectedToppingsCount=0;
            setupChipClickListeners();
        }

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
            if (chip.equals(sausage) || chip.equals(pepperoni) || chip.equals(beef) || chip.equals(ham)) {
                chip.setEnabled(true);
                chip.setChipBackgroundColorResource(android.R.color.holo_purple);
            } else chip.setEnabled(false);
        }
    }

    private void enableBBQTopping(Set<String> enabledToppings) {
        for (int i = 0; i < chipGroup.getChildCount(); i++) {
            Chip chip = (Chip) chipGroup.getChildAt(i);
            chip.setChipBackgroundColorResource(android.R.color.white);
        }
        for (int i = 0; i < chipGroup.getChildCount(); i++) {
            Chip chip = (Chip) chipGroup.getChildAt(i);
            if (chip.equals(bbqChicken) || chip.equals(greenPepper) || chip.equals(provolone) || chip.equals(cheddar)) {
                chip.setEnabled(true);
                chip.setChipBackgroundColorResource(android.R.color.holo_purple);
            } else chip.setEnabled(false);
        }
    }

    private void enableDeluxeTopping(Set<String> enabledToppings) {
        for (int i = 0; i < chipGroup.getChildCount(); i++) {
            Chip chip = (Chip) chipGroup.getChildAt(i);
            chip.setChipBackgroundColorResource(android.R.color.white);
        }
        for (int i = 0; i < chipGroup.getChildCount(); i++) {
            Chip chip = (Chip) chipGroup.getChildAt(i);
            if (chip.equals(sausage) || chip.equals(pepperoni) || chip.equals(greenPepper) || chip.equals(onion) || chip.equals(mushroom)) {
                chip.setEnabled(true);
                chip.setChipBackgroundColorResource(android.R.color.holo_purple);
            } else chip.setEnabled(false);
        }
    }

    private void enableAllChips() {
        for (int i = 0; i < chipGroup.getChildCount(); i++) {
            Chip chip = (Chip) chipGroup.getChildAt(i);
            chip.setEnabled(true);
            chip.setSelected(true);
            chip.setSelected(false);
        }
    }


    private void disableUncheckedChips() {
        for (int i = 0; i < chipGroup.getChildCount(); i++) {
            Chip chip = (Chip) chipGroup.getChildAt(i);
            if (!chip.isSelected()) {
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
        String selectedPizza = pizzaTypeSpinner.getSelectedItem().toString();
        double price = basePrice;

        // Determine base price based on pizza type and size
        switch (selectedPizza) {
            case "Build Your Own":
                price = smallRadioButton.isChecked() ? 8.99 :
                        mediumRadioButton.isChecked() ? 10.99 : 12.99;
                price += (selectedToppingsCount * 1.69);
                break;
            case "Deluxe":
                price = smallRadioButton.isChecked() ? 16.99 :
                        mediumRadioButton.isChecked() ? 18.99 : 20.99;
                break;
            case "Meatzza":
                price = smallRadioButton.isChecked() ? 17.99 :
                        mediumRadioButton.isChecked() ? 19.99 : 21.99;
                break;
            case "BBQ Chicken":
                price = smallRadioButton.isChecked() ? 14.99 :
                        mediumRadioButton.isChecked() ? 16.99 : 19.99;
                break;
            default:
                break;
        }

        // Update the TextView with formatted price
        pizzaPrice.setText(String.format("$%.2f", price));
    }

    private void createAndSavePizza() {
        // Determine the selected crust and size
        String crustType = crustTextView.getText().toString();
        Crust crust = Crust.valueOf(crustType.toUpperCase().replace(" ", "_")); // Adjust if Crust enum naming differs

        Size size;
        if (smallRadioButton.isChecked()) {
            size = Size.SMALL;
        } else if (mediumRadioButton.isChecked()) {
            size = Size.MEDIUM;
        } else {
            size = Size.LARGE;
        }
        String pizzaToStringType = "";

        String pizzaStyle = pizzaStyleSpinner.getSelectedItem().toString();
        String pizzaType = pizzaTypeSpinner.getSelectedItem().toString();
        Pizza pizza;
        if(pizzaStyle.equals("NYPizza")){
            if(pizzaType.equals("Deluxe")){
                pizza = new NYPizza().createDeluxe(size);
                pizzaToStringType = "New York Style-Brooklyn";
            }
            else if(pizzaType.equals("BBQ Chicken")){
                pizza = new NYPizza().createBBQChicken(size);
                pizzaToStringType = "New York Style-Thin";

            }
            else if (pizzaType.equals("Meatzza")){
                pizza = new NYPizza().createMeatzza(size);
                pizzaToStringType = "New York Style-HandTossed";
            } else{
                pizza = new NYPizza().createBuildYourOwn(size);
                for (int i = 0; i < chipGroup.getChildCount(); i++) {
                    Chip chip = (Chip) chipGroup.getChildAt(i);
                    if (chip.isSelected()) {
                        Topping topping  = getSelectedToppings(chip.getText().toString());
                        pizza.addTopping(topping);
                    }
                }
                pizzaToStringType = "New York Style-HandTossed";
            }
        } else{
            if(pizzaType.equals("Deluxe")){
                pizza = new ChicagoPizza().createDeluxe(size);
                pizzaToStringType = "Chicago Style-Deep Dish";
            }
            else if(pizzaType.equals("BBQ Chicken")){
                pizzaToStringType = "Chicago Style-Pan";
                pizza = new ChicagoPizza().createBBQChicken(size);

            }
            else if (pizzaType.equals("Meatzza")){
                pizzaToStringType = "Chicago Style-Stuffed";
                pizza = new ChicagoPizza().createMeatzza(size);
            } else{
                pizza = new ChicagoPizza().createBuildYourOwn(size);
                for (int i = 0; i < chipGroup.getChildCount(); i++) {
                    Chip chip = (Chip) chipGroup.getChildAt(i);
                    if (chip.isSelected()) {
                        Topping topping  = getSelectedToppings(chip.getText().toString());
                        pizza.addTopping(topping);
                    }
                }
            }
            pizzaToStringType = "Chicago Style-Pan";
        }
        String pizzaString = pizzaTypeSpinner.getSelectedItem().toString() + " (" + pizzaToStringType + ")" +
                ", " + pizza.getToppings() + ", " + size.toString() + ", "  + pizzaPrice.getText();

        // Save to singleton
        PizzaSingleton.getInstance().getPizzas().add(pizza);
        PizzaSingleton.getInstance().getPizzasString().add(pizzaString);
        PizzaSingleton.getInstance().setOrderNumber(PizzaSingleton.getInstance().getOrderNumber()+1);
        PizzaSingleton.getInstance().getOrderNumberList().add(PizzaSingleton.getInstance().getOrderNumber());
        Toast.makeText(this, "Pizza Added to Current Orders!", Toast.LENGTH_SHORT).show();
    }

    public Topping getSelectedToppings(String chipString) {
       String topping = chipString.toUpperCase().replace(" ", "_");
       return Topping.valueOf(topping);
    }

}

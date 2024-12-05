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

/**
 * OrderPage Activity class for customizing and placing pizza orders.
 * This class handles the selection of pizza style, type, size, and toppings.
 * It also calculates the price of the pizza based on the user’s choices.
 * @author Sri Akshara Kollu, Dev Rijhwani
 */
public class OrderPage extends AppCompatActivity {
    private final double EIGHT_NINE_NINE = 8.99;
    private final double TEN_NINE_NINE=10.99;
    private final double TWELVE_NINE_NINE=12.99;
    private final double TOPPING_COST = 1.69;
    private final double NINETEEN_NINE_NINE=19.99;
    private final double TWENTY_NINE_NINE=20.99;
    private final double FOURTEEN_NINE_NINE=14.99;
    private final double SIXTEEN_NINE_NINE=16.99;
    private final double SEVENTEEN_NINE_NINE=17.99;
    private final double EIGHTEEN_NINE_NINE=18.99;
    private final double TWENTYONE_NINE_NINE=21.99;
    private final int ZERO=0;

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

    /**
     * Initializes all UI components and sets up initial states.
     * Sets up listeners for spinners, radio buttons, and chips.
     */
    private void setID(){
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
    }

    /**
     * Sets up listeners for the pizza style and pizza type spinners.
     * Updates the crust type, pizza image, and pizza price accordingly.
     */
    private void setUpSpinners(){
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
    }


    /**
     * Returns the corresponding pizza image resource ID based on the selected style and type.
     * @param pizzaStyle The style of the pizza (e.g., Chicago or NY).
     * @param pizzaType  The type of pizza (e.g., Build Your Own, Deluxe, etc.).
     * @return The image resource ID for the pizza.
     */
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

    /**
     * Clears the color of all topping chips to indicate they are unselected.
     */
    private void clearAllChipColor() {
        for (int i = 0; i < chipGroup.getChildCount(); i++) {
            Chip chip = (Chip) chipGroup.getChildAt(i);
            chip.setChipBackgroundColorResource(android.R.color.white);
        }
    }

    /**
     * Sets up chip click listeners to handle topping selections.
     * When a chip is clicked, it either adds or removes the topping.
     */
    private void setupChipClickListeners() {
        for (int i = 0; i < chipGroup.getChildCount(); i++) {
            Chip chip = (Chip) chipGroup.getChildAt(i);
            chip.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Chip clickedChip = (Chip) v;
                    if(pizzaTypeSpinner.getSelectedItem().toString().equals("Build Your Own")) {
                        if (!clickedChip.isSelected()) {
                            clickedChip.setSelected(true);
                            clickedChip.setChipBackgroundColorResource(android.R.color.holo_purple);
                            handleToppingSelection(clickedChip, true);
                        } else {
                            clickedChip.setChipBackgroundColorResource(android.R.color.white);
                            handleToppingSelection(clickedChip, false);
                            clickedChip.setSelected(false);
                        }
                    }
                }
            });
        }
    }

    /**
     * Handles the selection or deselection of a topping chip.
     * Updates the count of selected toppings and adjusts the enabled state of other chips accordingly.
     * Triggers an update to the pizza price.
     * @param chip the chip representing the topping
     * @param isSelected whether the topping is selected or deselected
     */
    private void handleToppingSelection(Chip chip, boolean isSelected) {
        if (isSelected) {
            selectedToppingsCount++;
            if (selectedToppingsCount >= MAX_TOPPINGS) {
                disableUncheckedChips();
            }
        } else {
            selectedToppingsCount--;
            if (selectedToppingsCount < MAX_TOPPINGS) {
                enableUnselectedChips();
            }
        }
        updatePizzaPrice();
    }

    /**
     * Updates the enabled state and appearance of topping chips based on the selected pizza type.
     * For predefined pizzas, it enables specific toppings while disabling others.
     * For custom pizzas, it resets all chips to their default state.
     * @param selectedPizza the type of pizza selected
     */
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
        } else{
            enableAllChips();
            clearAllChipColor();
            selectedToppingsCount=ZERO;
            setupChipClickListeners();
        }

    }

    /**
     * Enables and highlights the specific toppings allowed for a "Meatzza" pizza.
     * Other topping chips are disabled and set to their default appearance.
     * @param enabledToppings the set of toppings to be enabled
     */
    private void enableMeatzzaTopping(Set<String> enabledToppings) {
        for (int i = 0; i < chipGroup.getChildCount(); i++) {
            Chip chip = (Chip) chipGroup.getChildAt(i);
            chip.setChipBackgroundColorResource(android.R.color.white);
        }
        for (int i = 0; i < chipGroup.getChildCount(); i++) {
            Chip chip = (Chip) chipGroup.getChildAt(i);
            if (chip.equals(sausage) || chip.equals(pepperoni) || chip.equals(beef)
                    || chip.equals(ham)) {
                chip.setEnabled(true);
                chip.setChipBackgroundColorResource(android.R.color.holo_purple);
            } else chip.setEnabled(false);
        }
    }


    /**
     * Enables and highlights the specific toppings allowed for a "BBQ Chicken" pizza.
     * Other topping chips are disabled and set to their default appearance.
     * @param enabledToppings the set of toppings to be enabled
     */
    private void enableBBQTopping(Set<String> enabledToppings) {
        for (int i = 0; i < chipGroup.getChildCount(); i++) {
            Chip chip = (Chip) chipGroup.getChildAt(i);
            chip.setChipBackgroundColorResource(android.R.color.white);
        }
        for (int i = 0; i < chipGroup.getChildCount(); i++) {
            Chip chip = (Chip) chipGroup.getChildAt(i);
            if (chip.equals(bbqChicken) || chip.equals(greenPepper) || chip.equals(provolone) ||
                    chip.equals(cheddar)) {
                chip.setEnabled(true);
                chip.setChipBackgroundColorResource(android.R.color.holo_purple);
            } else chip.setEnabled(false);
        }
    }

    /**
     * Enables and highlights the specific toppings allowed for a "Deluxe" pizza.
     * Other topping chips are disabled and set to their default appearance.
     * @param enabledToppings the set of toppings to be enabled
     */
    private void enableDeluxeTopping(Set<String> enabledToppings) {
        for (int i = 0; i < chipGroup.getChildCount(); i++) {
            Chip chip = (Chip) chipGroup.getChildAt(i);
            chip.setChipBackgroundColorResource(android.R.color.white);
        }
        for (int i = 0; i < chipGroup.getChildCount(); i++) {
            Chip chip = (Chip) chipGroup.getChildAt(i);
            if (chip.equals(sausage) || chip.equals(pepperoni) || chip.equals(greenPepper)
                    || chip.equals(onion) || chip.equals(mushroom)) {
                chip.setEnabled(true);
                chip.setChipBackgroundColorResource(android.R.color.holo_purple);
            } else chip.setEnabled(false);
        }
    }

    /**
     * Enables all unselected topping chips, allowing them to be clicked and selected.
     * Resets their state to ensure they are selectable.
     */
    private void enableUnselectedChips() {
        for (int i = 0; i < chipGroup.getChildCount(); i++) {
            Chip chip = (Chip) chipGroup.getChildAt(i);
            if(!chip.isSelected()) {
                chip.setEnabled(true);
                chip.setSelected(true);
                chip.setSelected(false);
            }
        }
    }

    /**
     * Enables all topping chips, resetting their state and making them selectable.
     */
    private void enableAllChips() {
        for (int i = 0; i < chipGroup.getChildCount(); i++) {
            Chip chip = (Chip) chipGroup.getChildAt(i);
            chip.setEnabled(true);
            chip.setSelected(true);
            chip.setSelected(false);
        }
    }

    /**
     * Disables all topping chips that are not currently selected, preventing further selection.
     */
    private void disableUncheckedChips() {
        for (int i = 0; i < chipGroup.getChildCount(); i++) {
            Chip chip = (Chip) chipGroup.getChildAt(i);
            if (!chip.isSelected()) {
                chip.setEnabled(false);
            }
        }
    }

    /**
     * Updates the crust type displayed in the crust text view based on the selected pizza type and choice.
     * The crust type varies depending on whether the pizza is Chicago or New York style.
     * If no valid choice is made, the text view is cleared.
     * @param pizzaType the style of pizza ("ChicagoPizza" or "NYPizza")
     * @param pizzaChoice the selected pizza type (e.g., "Deluxe", "BBQ Chicken", etc.)
     */
    private void updateCrustTextView(String pizzaType, String pizzaChoice) {
        if (pizzaChoice.equals("Build Your Own")) {
            crustTextView.setText(pizzaType.equals("ChicagoPizza") ? "Pan" : "Hand Tossed");
        } else if (pizzaChoice.equals("Deluxe")) {
            crustTextView.setText(pizzaType.equals("ChicagoPizza") ? "Deep Dish" : "Brooklyn");
        } else if (pizzaChoice.equals("BBQ Chicken")) {
            crustTextView.setText(pizzaType.equals("ChicagoPizza") ? "Pan" : "Thin");
        } else if (pizzaChoice.equals("Meatzza")) {
            crustTextView.setText(pizzaType.equals("ChicagoPizza") ? "Stuffed" : "Hand Tossed");
        } else {
            crustTextView.setText("");
        }
        crustTextView.setEnabled(false);
    }

    /**
     * Calculates and updates the pizza price displayed in the UI based on the selected pizza type, size, and toppings.
     * The base price and additional costs vary for different pizza types.
     */
    private void updatePizzaPrice() {
        String selectedPizza = pizzaTypeSpinner.getSelectedItem().toString();
        double price = EIGHT_NINE_NINE;
        switch (selectedPizza) {
            case "Build Your Own":
                price = smallRadioButton.isChecked() ? EIGHT_NINE_NINE :
                        mediumRadioButton.isChecked() ? TEN_NINE_NINE : TWELVE_NINE_NINE;
                price += (selectedToppingsCount * TOPPING_COST);
                break;
            case "Deluxe":
                price = smallRadioButton.isChecked() ? SIXTEEN_NINE_NINE :
                        mediumRadioButton.isChecked() ? EIGHTEEN_NINE_NINE : TWENTY_NINE_NINE;
                break;
            case "Meatzza":
                price = smallRadioButton.isChecked() ? SEVENTEEN_NINE_NINE :
                        mediumRadioButton.isChecked() ? NINETEEN_NINE_NINE : TWENTYONE_NINE_NINE;
                break;
            case "BBQ Chicken":
                price = smallRadioButton.isChecked() ? FOURTEEN_NINE_NINE :
                        mediumRadioButton.isChecked() ? SIXTEEN_NINE_NINE : NINETEEN_NINE_NINE;
                break;
            default:
                break;
        }
        pizzaPrice.setText(String.format("$%.2f", price));
    }


    /**
     * Creates a Pizza object of the specified type and size.
     * For "Build Your Own" pizzas, selected toppings are added to the pizza.
     * @param pizzaStyle the pizza style ("NYPizza" or "ChicagoPizza")
     * @param pizzaType the specific pizza type (e.g., "Deluxe", "BBQ Chicken")
     * @param size the size of the pizza (SMALL, MEDIUM, or LARGE)
     * @return a Pizza object with the specified attributes
     */
    private Pizza typePizza( String pizzaStyle, String pizzaType, Size size){
        Pizza pizza;
        if(pizzaStyle.equals("NYPizza")){
            if(pizzaType.equals("Deluxe")) pizza = new NYPizza().createDeluxe(size);
             else if(pizzaType.equals("BBQ Chicken")) pizza = new NYPizza().createBBQChicken(size);
             else if (pizzaType.equals("Meatzza")) pizza = new NYPizza().createMeatzza(size);
             else{
                pizza = new NYPizza().createBuildYourOwn(size);
                for (int i = 0; i < chipGroup.getChildCount(); i++) {
                    Chip chip = (Chip) chipGroup.getChildAt(i);
                    if (chip.isSelected()) {
                        Topping topping  = getSelectedToppings(chip.getText().toString());
                        pizza.addTopping(topping);
                    }
                }
            }
        } else{
            if(pizzaType.equals("Deluxe")) pizza = new ChicagoPizza().createDeluxe(size);
            else if(pizzaType.equals("BBQ Chicken")) pizza = new ChicagoPizza().createBBQChicken(size);
             else if (pizzaType.equals("Meatzza")) pizza = new ChicagoPizza().createMeatzza(size);
             else{
                pizza = new ChicagoPizza().createBuildYourOwn(size);
                for (int i = 0; i < chipGroup.getChildCount(); i++) {
                    Chip chip = (Chip) chipGroup.getChildAt(i);
                    if (chip.isSelected()) {
                        Topping topping  = getSelectedToppings(chip.getText().toString());
                        pizza.addTopping(topping);
                    }
                }
            }
        }
        return pizza;
    }

    /**
     * Generates a string representation of the selected pizza's type and style for display purposes.
     * The output includes the style (New York or Chicago) and crust type.
     * @param pizzaStyle the pizza style ("NYPizza" or "ChicagoPizza")
     * @param pizzaType the specific pizza type (e.g., "Deluxe", "BBQ Chicken")
     * @return a string describing the pizza's type and style
     */
    private String pizzaStringCreator( String pizzaStyle, String pizzaType){
        String pizzaToStringType;
        if(pizzaStyle.equals("NYPizza")){
            if(pizzaType.equals("Deluxe")) pizzaToStringType = "New York Style-Brooklyn";
            else if(pizzaType.equals("BBQ Chicken")) pizzaToStringType = "New York Style-Thin";
            else if (pizzaType.equals("Meatzza"))  pizzaToStringType = "New York Style-HandTossed";
            else{ pizzaToStringType = "New York Style-HandTossed";}
        } else {
            if (pizzaType.equals("Deluxe")) pizzaToStringType = "Chicago Style-Deep Dish";

            else if (pizzaType.equals("BBQ Chicken"))
                pizzaToStringType = "Chicago Style-Pan";
            else if (pizzaType.equals("Meatzza")) pizzaToStringType = "Chicago Style-Stuffed";
            else pizzaToStringType = "Chicago Style-Pan";
        }
        return pizzaToStringType;
    }

    /**
     * Creates a pizza object based on user selections and saves it to a singleton order manager.
     * The pizza details are also saved as a string for display purposes.
     * Displays a toast message confirming the addition of the pizza to the order.
     */
    private void createAndSavePizza() {
        String crustType = crustTextView.getText().toString();
        Crust crust = Crust.valueOf(crustType.toUpperCase().replace(" ", "_"));
        Size size;
        if (smallRadioButton.isChecked()) {
            size = Size.SMALL;
        } else if (mediumRadioButton.isChecked()) {
            size = Size.MEDIUM;
        } else {
            size = Size.LARGE;
        }
        String pizzaStyle = pizzaStyleSpinner.getSelectedItem().toString();
        String pizzaType = pizzaTypeSpinner.getSelectedItem().toString();
        Pizza pizza = typePizza(pizzaStyle, pizzaType, size);
        String pizzaToStringType = pizzaStringCreator(pizzaStyle,pizzaType);
        String pizzaString = pizzaTypeSpinner.getSelectedItem().toString() + " (" + pizzaToStringType + ")" +
                ", " + pizza.getToppings() + ", " + size.toString() + ", "  + pizzaPrice.getText();
        PizzaSingleton.getInstance().getPizzas().add(pizza);
        PizzaSingleton.getInstance().getPizzasString().add(pizzaString);
        Toast.makeText(this, "Pizza Added to Current Orders!",
                Toast.LENGTH_SHORT).show();
    }
    /**
     * The onCreate method that is called when the activity is created.
     * It initializes the UI components and sets up listeners for interactions.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_page);
        setID();
        clearAllChipColor();
        setupChipClickListeners();
        ArrayAdapter<String> pizzaTypeAdapter = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_item, new String[]{"ChicagoPizza", "NYPizza"});
        pizzaTypeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        pizzaStyleSpinner.setAdapter(pizzaTypeAdapter);
        ArrayAdapter<String> pizzaChoiceAdapter = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_item, new String[]
                {"Build Your Own", "Deluxe", "Meatzza", "BBQ Chicken"});
        pizzaChoiceAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        pizzaTypeSpinner.setAdapter(pizzaChoiceAdapter);
        radioGroup.setOnCheckedChangeListener((group, checkedId) -> updatePizzaPrice());
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(OrderPage.this, MainActivity.class);
                startActivity(intent);
            }
        });
        setUpSpinners();
        orderButton.setOnClickListener(v -> {
            createAndSavePizza();
            Intent intent = new Intent(OrderPage.this, CurrentOrder.class);
            startActivity(intent);
        });
    }

    /**
     * Retrieves a Topping enum value based on the string representation of a chip's text.
     * The chip text is converted to uppercase and underscores for matching with the enum constants.
     * @param chipString the text of the selected chip
     * @return the corresponding Topping enum value
     */
    public Topping getSelectedToppings(String chipString) {
       String topping = chipString.toUpperCase().replace(" ", "_");
       return Topping.valueOf(topping);
    }

}

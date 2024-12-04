package com.example.project5;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import java.util.Locale;


import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class OrderAdapter extends RecyclerView.Adapter<OrderAdapter.OrderViewHolder> {

    private List<OrderView> orders;
    private static final int ZERO = 0;
    private final int ONE =1;
    private double salesTaxPizzas = ZERO;
    private double totalCostPizzas = ZERO;
    private final double TAX = 0.06625;



    public OrderAdapter(List<OrderView> orders) {
        this.orders = orders;
    }

    @Override
    public OrderViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // Inflate the item_view layout for each item in the RecyclerView
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_view, parent, false);
        return new OrderViewHolder(view);
    }

    @Override
    public void onBindViewHolder(OrderViewHolder holder, int position) {
        OrderView order = orders.get(position);

        // Set the order details
        holder.orderDetails.setText(order.getPizzaStringDescription());
        holder.subtotal.setText(String.format(Locale.US, "$%.2f", order.getSubtotal()));
//        holder.tax.setText(String.format(Locale.US, "$%.2f", order.getSubtotal() * 0.06625));
//        holder.total.setText(String.format(Locale.US, "$%.2f", order.getSubtotal() * 1.06625));


        int pizzaPicture=getImage(holder.orderDetails.getText().toString());
        holder.pizzaImage.setImageResource(pizzaPicture);

        // Cancel Order Logic
        holder.cancelButton.setOnClickListener(v -> {
            String selectedPizza = order.getPizzaStringDescription();
            for (int i = 0; i < PizzaSingleton.getPizzasString().size(); i++) {
                if (PizzaSingleton.getPizzasString().get(i).equals(selectedPizza)) {
                    PizzaSingleton.getPizzasString().remove(i);
                    PizzaSingleton.getPizzas().remove(i);
                    orders.remove(position); // Remove from the RecyclerView's dataset
                    notifyItemRemoved(position);
                    notifyItemRangeChanged(position, orders.size());
                    break;
                }
            }
            salesTaxPizzas = ZERO;
            totalCostPizzas = ZERO;
            for (Pizza pizza : PizzaSingleton.getInstance().getPizzas()) {
                PizzaSingleton.getInstance().setSubtotalPizzas(pizza.price()+PizzaSingleton.getInstance().getSubtotalPizzas());
            }
            salesTaxPizzas = PizzaSingleton.getInstance().getSubtotalPizzas() * TAX;
            totalCostPizzas = PizzaSingleton.getInstance().getSubtotalPizzas() + salesTaxPizzas;
            holder.subtotalField.setText(String.format("%.2f", PizzaSingleton.getInstance().getSubtotalPizzas()));
            holder.salesTaxField.setText(String.format("%.2f", salesTaxPizzas));
            holder.totalCostField.setText(String.format("%.2f", totalCostPizzas));
        });

    }

//    private void recalculateTotals(OrderViewHolder holder, int position) {
////        subtotalPizzas = ZERO;
//        salesTaxPizzas = ZERO;
//        totalCostPizzas = ZERO;
//
//        for (Pizza pizza : PizzaSingleton.getInstance().getPizzas()) {
//            PizzaSingleton.getInstance().setSubtotalPizzas(pizza.price()+PizzaSingleton.getInstance().getSubtotalPizzas());
//        }
//        salesTaxPizzas = PizzaSingleton.getInstance().getSubtotalPizzas() * TAX;
//        totalCostPizzas = PizzaSingleton.getInstance().getSubtotalPizzas() + salesTaxPizzas;
//        holder.subtotalField.setText(String.format("%.2f", PizzaSingleton.getInstance().getSubtotalPizzas()));
//        holder.salesTaxField.setText(String.format("%.2f", salesTaxPizzas));
//        holder.totalCostField.setText(String.format("%.2f", totalCostPizzas));
//    }



    @Override
    public int getItemCount() {
        return orders == null ? 0 : orders.size();
    }

    private static int getImage(String pizza) {
        if(pizza.contains("Chicago")){
            if(pizza.contains("Build")){
                return R.drawable.chicago_style_build_your_own;
            }
            else if(pizza.contains("Deluxe")){
                return R.drawable.chicago_style_deluxe;
            }
            else if(pizza.contains("Meatzza")){
                return R.drawable.chicago_style_meatzza;
            }
            else{
                return R.drawable.chicago_style_bbq_chicken;
            }
        }
        else{
            if(pizza.contains("Build")){
                return R.drawable.ny_style_build_your_own;
            }
            else if(pizza.contains("Deluxe")){
                return R.drawable.ny_style_deluxe;
            }
            else if(pizza.contains("Meatzza")){
                return R.drawable.ny_style_meatzza;
            }
            else{
                return R.drawable.ny_style_bbq_chicken;
            }
        }
    }


    public static class OrderViewHolder extends RecyclerView.ViewHolder {
        TextView orderDetails, subtotal, tax, total;
        Button cancelButton, addButton;
        ImageView pizzaImage;

        private TextView totalCostField;
        private TextView subtotalField;
        private TextView salesTaxField;

        public OrderViewHolder(View itemView) {
            super(itemView);
            // Initialize views from item_view.xml
            orderDetails = itemView.findViewById(R.id.order_details);
            subtotal = itemView.findViewById(R.id.subtotal2);
//            tax = itemView.findViewById(R.id.tax2);
//            total = itemView.findViewById(R.id.total2);
            cancelButton = itemView.findViewById(R.id.cancel_button);
            addButton = itemView.findViewById(R.id.add_to_order_button);
            pizzaImage=itemView.findViewById(R.id.imageView3);
            subtotalField=itemView.findViewById(R.id.subtotalCurrentView2);
            salesTaxField=itemView.findViewById(R.id.taxTotalCurrentView2);
            totalCostField=itemView.findViewById(R.id.totalCurrentView2);
        }
    }
}

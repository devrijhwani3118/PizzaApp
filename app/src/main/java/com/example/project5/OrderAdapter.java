package com.example.project5;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class OrderAdapter extends RecyclerView.Adapter<OrderAdapter.OrderViewHolder> {

    private List<OrderView> orders;

    public OrderAdapter(List<OrderView> orders) {
        this.orders = orders;
    }

    @Override
    public OrderViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.activity_current_order_page, parent, false);
        return new OrderViewHolder(view);
    }

    //@Override
//    public void onBindViewHolder(OrderViewHolder holder, int position) {
//        OrderView order = orders.get(position);
//        holder.orderName.setText("Order #" + order.getOrderNumber());
//        holder.orderDetails.setText(order.getPizzaStringDescription());
//        holder.subtotal.setText(String.format("Subtotal: $%.2f", order.getSubtotal()));
//        holder.tax.setText(String.format("Tax: $%.2f", order.getSubtotal() * 0.06625));
//        holder.total.setText(String.format("Total: $%.2f", order.getSubtotal() * 1.06625));
//
//        // Cancel Order Logic
//        holder.cancelButton.setOnClickListener(v -> {
//            String selectedPizza = order.getPizzaStringDescription();
//            for (int i = 0; i < PizzaSingleton.getPizzasString().size(); i++) {
//                if (PizzaSingleton.getPizzasString().get(i).equals(selectedPizza)) {
//                    PizzaSingleton.getPizzasString().remove(i);
//                    PizzaSingleton.getPizzas().remove(i);
//                    orders.remove(position); // Remove from the RecyclerView's dataset
//                    notifyItemRemoved(position);
//                    notifyItemRangeChanged(position, orders.size());
//                    break;
//                }
//            }
//        });
//
//        // Add Order Logic
////        holder.addButton.setOnClickListener(v -> {
////            Pizza newPizza = order.getPizza(); // Assuming OrderView has a `getPizza()` method
////            String newPizzaDescription = order.getPizzaStringDescription();
////            PizzaSingleton.getOrderNumberList().add(order.getOrderNumber());
////            PizzaSingleton.getPizzas().add(newPizza);
////            PizzaSingleton.getPizzasString().add(newPizzaDescription);
////            orders.add(new OrderView(newPizza.toString(), newPizza.price(), newPizzaDescription, orders.size() + 1));
////            notifyItemInserted(orders.size() - 1);
////        });
//    }

    @Override
    public void onBindViewHolder(OrderViewHolder holder, int position) {
        OrderView order = orders.get(position);

        // Display Order Details
        holder.orderDetails.setText(order.getPizzaStringDescription());
        holder.subtotal.setText(String.format("Subtotal: $%.2f", order.getSubtotal()));
        holder.tax.setText(String.format("Tax: $%.2f", order.getSubtotal() * 0.06625));
        holder.total.setText(String.format("Total: $%.2f", order.getSubtotal() * 1.06625));

        // Cancel Order Logic
        holder.cancelButton.setOnClickListener(v -> {
            // Remove the item from the PizzaSingleton lists
            String selectedPizza = order.getPizzaStringDescription();
            Pizza pizzaToRemove = null;

            for (int i = 0; i < PizzaSingleton.getPizzasString().size(); i++) {
                if (PizzaSingleton.getPizzasString().get(i).equals(selectedPizza)) {
                    pizzaToRemove = PizzaSingleton.getPizzas().get(i);
                    PizzaSingleton.getPizzasString().remove(i);
                    PizzaSingleton.getPizzas().remove(i);
                    break;
                }
            }

            if (pizzaToRemove != null) {
                // Remove from RecyclerView
                orders.remove(position);
                notifyItemRemoved(position);
                notifyItemRangeChanged(position, orders.size());
            }
        });
    }



    @Override
    public int getItemCount() {
        return orders == null ? 0 : orders.size();
    }

    public static class OrderViewHolder extends RecyclerView.ViewHolder {
        TextView orderDetails, subtotal, tax, total;
        Button cancelButton, addButton;

        public OrderViewHolder(View itemView) {
            super(itemView);
//            orderName = itemView.findViewById(R.id.order_name);
            orderDetails = itemView.findViewById(R.id.order_details);
            subtotal = itemView.findViewById(R.id.subtotal2);
            tax = itemView.findViewById(R.id.tax2);
            total = itemView.findViewById(R.id.total2);
            cancelButton = itemView.findViewById(R.id.cancel_button);
            addButton = itemView.findViewById(R.id.add_to_order_button);
        }

    }
}

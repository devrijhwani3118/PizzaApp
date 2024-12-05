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

/**
 * OrderAdapter is an adapter for the RecyclerView that displays a list of orders.
 * It binds order data to the UI, including pizza descriptions, subtotals, and images.
 * It also handles the removal of an order from the list when the cancel button is clicked.
 * The adapter communicates order updates through the OrderUpdateListener.
 * @author Sri Akshara Kollu, Dev Rijhwani
 */
public class OrderAdapter extends RecyclerView.Adapter<OrderAdapter.OrderViewHolder> {

    private List<OrderView> orders;
    private final int ZERO=0;
    private OrderUpdateListener updateListener;

    /**
     * Constructs an OrderAdapter with the list of orders and an update listener.
     * @param orders the list of orders to be displayed
     * @param updateListener the listener to be notified when the order list is updated
     */
    public OrderAdapter(List<OrderView> orders, OrderUpdateListener updateListener) {
        this.orders = orders;
        this.updateListener = updateListener;
    }

    /**
     * Returns the appropriate image resource based on the pizza description.
     * @param pizza the pizza description
     * @return the image resource ID based on the pizza type
     */
    private static int getImage(String pizza) {
        if (pizza.contains("Chicago")) {
            if (pizza.contains("Build")) return R.drawable.chicago_style_build_your_own;
            if (pizza.contains("Deluxe")) return R.drawable.chicago_style_deluxe;
            if (pizza.contains("Meatzza")) return R.drawable.chicago_style_meatzza;
            return R.drawable.chicago_style_bbq_chicken;
        } else {
            if (pizza.contains("Build")) return R.drawable.ny_style_build_your_own;
            if (pizza.contains("Deluxe")) return R.drawable.ny_style_deluxe;
            if (pizza.contains("Meatzza")) return R.drawable.ny_style_meatzza;
            return R.drawable.ny_style_bbq_chicken;
        }
    }


    /**
     * Creates and returns a new view holder for the order item.
     * @param parent the parent view group
     * @param viewType the type of the view
     * @return a new OrderViewHolder instance
     */
    @Override
    public OrderViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_view, parent, false);
        return new OrderViewHolder(view);
    }

    /**
     * Binds the data from the order at the specified position to the view holder.
     * Sets the pizza description, subtotal, and image, and handles the cancel button click.
     * @param holder the view holder to bind the data to
     * @param position the position of the item in the orders list
     */
    @Override
    public void onBindViewHolder(OrderViewHolder holder, int position) {
        OrderView order = orders.get(position);
        holder.orderDetails.setText(order.getPizzaStringDescription());
        holder.subtotal.setText(String.format(Locale.US, "$%.2f", order.getSubtotal()));
        int pizzaPicture = getImage(holder.orderDetails.getText().toString());
        holder.pizzaImage.setImageResource(pizzaPicture);

        holder.cancelButton.setOnClickListener(v -> {
            orders.remove(position);
            notifyItemRemoved(position);
            notifyItemRangeChanged(position, orders.size());
            PizzaSingleton.getInstance().getPizzas().remove(position);
            PizzaSingleton.getInstance().getPizzasString().remove(position);
            if (updateListener != null) {
                updateListener.onOrderUpdated();
            }
        });
    }

    /**
     * Returns the number of items in the orders list.
     * @return the number of items in the orders list
     */
    @Override
    public int getItemCount() {
        return orders == null ? ZERO : orders.size();
    }

    /**
     * ViewHolder class to hold the views for each order item in the RecyclerView.
     */
    public static class OrderViewHolder extends RecyclerView.ViewHolder {
        TextView orderDetails, subtotal;
        Button cancelButton;
        ImageView pizzaImage;
        /**
         * Constructs the OrderViewHolder and initializes the views.
         * @param itemView the view for the individual order item
         */
        public OrderViewHolder(View itemView) {
            super(itemView);
            orderDetails = itemView.findViewById(R.id.order_details);
            subtotal = itemView.findViewById(R.id.subtotal2);
            cancelButton = itemView.findViewById(R.id.cancel_button);
            pizzaImage = itemView.findViewById(R.id.imageView3);
        }
    }

    /**
     * Interface for updating the order list when changes occur (e.g., when an order is canceled).
     */
    public interface OrderUpdateListener {
        void onOrderUpdated();
    }
}

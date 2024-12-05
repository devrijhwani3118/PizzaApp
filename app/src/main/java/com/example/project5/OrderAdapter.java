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
    private OrderUpdateListener updateListener;

    public OrderAdapter(List<OrderView> orders, OrderUpdateListener updateListener) {
        this.orders = orders;
        this.updateListener = updateListener;
    }

    @Override
    public OrderViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_view, parent, false);
        return new OrderViewHolder(view);
    }

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

    @Override
    public int getItemCount() {
        return orders == null ? 0 : orders.size();
    }

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

    public static class OrderViewHolder extends RecyclerView.ViewHolder {
        TextView orderDetails, subtotal;
        Button cancelButton;
        ImageView pizzaImage;
        public OrderViewHolder(View itemView) {
            super(itemView);
            orderDetails = itemView.findViewById(R.id.order_details);
            subtotal = itemView.findViewById(R.id.subtotal2);
            cancelButton = itemView.findViewById(R.id.cancel_button);
            pizzaImage = itemView.findViewById(R.id.imageView3);
        }
    }

    public interface OrderUpdateListener {
        void onOrderUpdated();
    }
}

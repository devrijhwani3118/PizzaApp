package com.example.project5;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class OrderAdapter extends RecyclerView.Adapter<OrderAdapter.OrderViewHolder> {

    private List<OrderView> orders;
    private OnCancelOrderListener onCancelOrderListener;

    public interface OnCancelOrderListener {
        void onCancelOrder(int position);
    }

    public OrderAdapter(List<OrderView> orders) {
        this.orders = orders;
//        this.onCancelOrderListener = onCancelOrderListener;
    }

    @Override
    public OrderViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.activity_current_order_page, parent, false);
        return new OrderViewHolder(view);
    }

    @Override
    public void onBindViewHolder(OrderViewHolder holder, int position) {
        OrderView order = orders.get(position);
        holder.orderName.setText("Order #" + order.getOrderNumber());
        holder.orderDetails.setText(order.getPizzaStringDescription());
        holder.subtotal.setText(String.format("Subtotal: $%.2f", order.getSubtotal()));
        holder.tax.setText(String.format("Tax: $%.2f", order.getSubtotal() * 0.06625));
        holder.total.setText(String.format("Total: $%.2f", order.getSubtotal() * 1.06625));

        holder.cancelButton.setOnClickListener(v -> {
                    String selectedPizza = order.getPizzaStringDescription();
                    if (onCancelOrderListener != null) {
                        onCancelOrderListener.onCancelOrder(position);
                        for (int i = 0; i < PizzaSingleton.getPizzasString().size(); i++) {
                            if (PizzaSingleton.getPizzasString().get(i).equals(selectedPizza)) {
                                PizzaSingleton.getPizzasString().remove(i);
                                PizzaSingleton.getPizzas().remove(i);
//                                order.recalculateTotals();
                                return;
                            }
                        }
                    }
                });
    }

    @Override
    public int getItemCount() {
        if(orders==null)return 0;
        return orders.size();
    }

    public static class OrderViewHolder extends RecyclerView.ViewHolder {
        TextView orderName, orderDetails, subtotal, tax, total;
        Button cancelButton;

        public OrderViewHolder(View itemView) {
            super(itemView);
            orderName = itemView.findViewById(R.id.order_name);
            orderDetails = itemView.findViewById(R.id.order_details);
            subtotal = itemView.findViewById(R.id.subtotal);
            tax = itemView.findViewById(R.id.tax);
            total = itemView.findViewById(R.id.total);
            cancelButton = itemView.findViewById(R.id.cancel_button);
        }
    }
}

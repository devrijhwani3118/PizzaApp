package com.example.project5;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class OrderAdapter extends RecyclerView.Adapter<OrderAdapter.OrderViewHolder> {

    private List<OrderView> orders;

    public OrderAdapter(List<OrderView> orders) {
        this.orders = orders;
    }

    @NonNull
    @Override
    public OrderViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_view, parent, false);
        return new OrderViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull OrderViewHolder holder, int position) {
        OrderView order = orders.get(position);
        holder.nameTextView.setText(order.getName());
//        holder.quantityTextView.setText("Qty: " + order.getQuantity());
        holder.subtotalTextView.setText("$" + order.getSubtotal());
//        holder.totalTextView.setText("$"+order.getTotal());
    }

    @Override
    public int getItemCount() {
        return orders.size();
    }

    static class OrderViewHolder extends RecyclerView.ViewHolder {
        TextView nameTextView;
        TextView quantityTextView;
        TextView subtotalTextView;

        TextView totalTextView;

        public OrderViewHolder(@NonNull View itemView) {
            super(itemView);
            nameTextView = itemView.findViewById(R.id.order_name);
//            quantityTextView = itemView.findViewById(R.id.order_details);
            subtotalTextView = itemView.findViewById(R.id.subtotal);
//            totalTextView = itemView.findViewById((R.id.total));
        }
    }
}

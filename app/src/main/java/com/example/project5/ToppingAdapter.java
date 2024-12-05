package com.example.project5;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

/**
 * Adapter class for managing and displaying a list of pizza toppings in a RecyclerView.
 * @author Sri Akshara Kollu, Dev Rijhwani
 */
public class ToppingAdapter extends RecyclerView.Adapter<ToppingAdapter.ToppingViewHolder> {
    private ArrayList<ToppingView> toppingList;

    /**
     * Constructor to initialize the adapter with a list of toppings.
     * @param toppingList List of toppings to be displayed in the RecyclerView.
     */
    public ToppingAdapter(ArrayList<ToppingView> toppingList) {
        this.toppingList = toppingList;
    }

    /**
     * Inflates the view for a single item in the RecyclerView.
     * @param parent The parent ViewGroup into which the new view will be added.
     * @param viewType The view type of the new view (unused in this implementation).
     * @return A new ToppingViewHolder for the inflated view.
     */
    @NonNull
    @Override
    public ToppingViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.topping_view, parent, false);
        return new ToppingViewHolder(view);
    }

    /**
     * Binds data to the views for a specific position in the RecyclerView.
     * @param holder The ViewHolder containing the views to bind data to.
     * @param position The position of the item within the data set.
     */
    @Override
    public void onBindViewHolder(@NonNull ToppingViewHolder holder, int position) {
        ToppingView topping = toppingList.get(position);
        holder.toppingNameTextView.setText(topping.getToppingName());
        if (topping.getToppingName().equalsIgnoreCase("Sausage")) {
            holder.imageViewTopping.setImageResource(R.drawable.sausage_image);
        } else if (topping.getToppingName().equalsIgnoreCase("Pepperoni")) {
            holder.imageViewTopping.setImageResource(R.drawable.pepperoni_image);
        } else if (topping.getToppingName().equalsIgnoreCase("Green Pepper")) {
            holder.imageViewTopping.setImageResource(R.drawable.green_pepper_image);
        } else if (topping.getToppingName().equalsIgnoreCase("Onion")) {
            holder.imageViewTopping.setImageResource(R.drawable.onion_image);
        } else if (topping.getToppingName().equalsIgnoreCase("Mushroom")) {
            holder.imageViewTopping.setImageResource(R.drawable.mushroom_image);
        } else if (topping.getToppingName().equalsIgnoreCase("BBQ Chicken")) {
            holder.imageViewTopping.setImageResource(R.drawable.bbq_chicken_image);
        } else if (topping.getToppingName().equalsIgnoreCase("Provolone")) {
            holder.imageViewTopping.setImageResource(R.drawable.provolone_image);
        } else if (topping.getToppingName().equalsIgnoreCase("Cheddar")) {
            holder.imageViewTopping.setImageResource(R.drawable.cheddar_image);
        } else if (topping.getToppingName().equalsIgnoreCase("Beef")) {
            holder.imageViewTopping.setImageResource(R.drawable.beef_image);
        } else if (topping.getToppingName().equalsIgnoreCase("Ham")) {
            holder.imageViewTopping.setImageResource(R.drawable.ham_image);
        } else if (topping.getToppingName().equalsIgnoreCase("Pineapple")) {
            holder.imageViewTopping.setImageResource(R.drawable.pineapple_image);
        } else if (topping.getToppingName().equalsIgnoreCase("Olives")) {
            holder.imageViewTopping.setImageResource(R.drawable.olives_image);
        } else {
            holder.imageViewTopping.setImageResource(R.drawable.spinach_image);
        }
    }

    /**
     * Returns the total number of items in the RecyclerView.
     * @return The size of the topping list.
     */
    @Override
    public int getItemCount() {
        return toppingList.size();
    }

    /**
     * ViewHolder class to hold and manage the views for a single topping item.
     */
    public static class ToppingViewHolder extends RecyclerView.ViewHolder {
        TextView toppingNameTextView;
        ImageView imageViewTopping;

        /**
         * Constructor to initialize the views for a single topping item.
         * @param itemView The item view layout.
         */
        public ToppingViewHolder(@NonNull View itemView) {
            super(itemView);
            toppingNameTextView = itemView.findViewById(R.id.topping_name_text_view);
            imageViewTopping = itemView.findViewById(R.id.imageViewTopping);
        }
    }



}

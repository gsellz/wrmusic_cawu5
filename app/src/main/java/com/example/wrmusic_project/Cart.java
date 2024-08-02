package com.example.wrmusic_project;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import java.util.Arrays;
import java.util.List;

public class Cart extends AppCompatActivity {

    private LinearLayout linearLayout;
    private TextView totalTextView;
    private Button homeButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        linearLayout = findViewById(R.id.cart_list);
        totalTextView = findViewById(R.id.total_text_view);
        homeButton = findViewById(R.id.home_button);

        List<CartItem> cartItems = getCartItems();
        populateCartItems(cartItems);
        updateTotal(cartItems);

        homeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Cart.this, MainActivity.class); // Adjust as necessary
                startActivity(intent);
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed(); // Handle the back button press
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private List<CartItem> getCartItems() {
        // Using Arrays.asList instead of List.of
        return Arrays.asList(
                new CartItem(R.drawable.album_image, "Album Title 1", 2, 19.99),
                new CartItem(R.drawable.album_image, "Album Title 2", 1, 9.99)
        );
    }

    private void populateCartItems(List<CartItem> cartItems) {
        linearLayout.removeAllViews();
        for (CartItem item : cartItems) {
            View itemView = getLayoutInflater().inflate(R.layout.cart_item, linearLayout, false);
            // Set the item data
            ImageView imageView = itemView.findViewById(R.id.item_image);
            TextView titleTextView = itemView.findViewById(R.id.item_title);
            TextView quantityTextView = itemView.findViewById(R.id.item_quantity);
            TextView priceTextView = itemView.findViewById(R.id.item_price);

            imageView.setImageResource(item.getImageResId());
            titleTextView.setText(item.getTitle());
            quantityTextView.setText("Quantity: " + item.getQuantity());
            priceTextView.setText("Price: $" + String.format("%.2f", item.getPrice()));

            linearLayout.addView(itemView);
        }
    }

    private void updateTotal(List<CartItem> cartItems) {
        double total = 0.0;
        for (CartItem item : cartItems) {
            total += item.getPrice() * item.getQuantity();
        }
        totalTextView.setText("Total: $" + String.format("%.2f", total));
    }
}

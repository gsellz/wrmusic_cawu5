package com.example.wrmusic_project;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CenterCrop;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;

import java.util.Arrays;
import java.util.List;

public class Cart extends AppCompatActivity {

    private LinearLayout linearLayout;
    private TextView totalTextView;
    private Button homeButton;
    private ImageButton backButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

        linearLayout = findViewById(R.id.cart_list);
        totalTextView = findViewById(R.id.total_text_view);
        homeButton = findViewById(R.id.home_button);
        backButton = findViewById(R.id.back_icon);

        List<CartItem> cartItems = getCartItems();
        populateCartItems(cartItems);
        updateTotal(cartItems);

        homeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Cart.this, HomePage.class);
                startActivity(intent);
            }
        });

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private List<CartItem> getCartItems() {
        return Arrays.asList(
                new CartItem(R.drawable.image1, "Random Access Memories", 2, 10.4),
                new CartItem(R.drawable.image2, "The Dark Side Of The Moon", 1, 9.99),
                new CartItem(R.drawable.image3, "Good Kid, M.A.A.d City", 2, 35.7),
                new CartItem(R.drawable.image4, "Thriller", 3, 11.4),
                new CartItem(R.drawable.image5, "Rumours", 2, 23.6),
                new CartItem(R.drawable.image6, "Nevermind", 5, 10.9),
                new CartItem(R.drawable.image7, "The Dark Side Of The Moon", 2, 17.4),
                new CartItem(R.drawable.image8, "AM", 1, 30.1),
                new CartItem(R.drawable.image9, "Mezzanine", 4, 11.9)
        );
    }

    private void populateCartItems(List<CartItem> cartItems) {
        linearLayout.removeAllViews();
        for (CartItem item : cartItems) {
            View itemView = getLayoutInflater().inflate(R.layout.cart_item, linearLayout, false);

            ImageView imageView = itemView.findViewById(R.id.item_image);
            TextView titleTextView = itemView.findViewById(R.id.item_title);
            TextView quantityTextView = itemView.findViewById(R.id.item_quantity);
            TextView priceTextView = itemView.findViewById(R.id.item_price);

            Glide.with(this)
                    .load(item.getImageResId())
                    .transform(new CenterCrop(), new RoundedCorners(50))
                    .into(imageView);

            titleTextView.setText(item.getTitle());
            quantityTextView.setText("Quantity: " + item.getQuantity());
            priceTextView.setText("$ " + String.format("%.2f", item.getPrice()));

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

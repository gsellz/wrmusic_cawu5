package com.example.wrmusic_project;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CenterCrop;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

public class ItemPage extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private GridLayout gridLayout;
    private ImageView cartIcon;
    private TextView welcomeUser;
    private DrawerLayout drawerLayout;
    private Toolbar toolbarItem;
    private NavigationView navigationView;
    private ImageView hamburgerButton;
    private ImageButton closeDrawer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_page);

        gridLayout = findViewById(R.id.item_list);

        setupGenreCapsules();

        List<Item> items = getItem();
        populateItems(items);

        drawerLayout = findViewById(R.id.drawerMenu);
        navigationView = findViewById(R.id.nav_view);
        toolbarItem = findViewById(R.id.toolbarItem);

        setSupportActionBar(toolbarItem);

        // Inisialisasi Hamburger Button
        hamburgerButton = findViewById(R.id.hamburgerButton);
        hamburgerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
                    drawerLayout.closeDrawer(GravityCompat.START);
                } else {
                    drawerLayout.openDrawer(GravityCompat.START);
                }
            }
        });

        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayShowTitleEnabled(false);
        }

        // Ini perubahan variable username
        welcomeUser = findViewById(R.id.welcomeUser);
        View headerView = navigationView.getHeaderView(0);
        TextView headerUsername = headerView.findViewById(R.id.usernameDrawer);

        SharedPreferences sharedPref = getSharedPreferences("user_prefs", MODE_PRIVATE);
        String username = sharedPref.getString("username_key", "USERNAME");

        if (username != null) {
            headerUsername.setText(username);
        } else {
            headerUsername.setText("USERNAME");
        }

        closeDrawer = headerView.findViewById(R.id.close_drawer);

        closeDrawer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
                    drawerLayout.closeDrawer(GravityCompat.START);
                }
            }
        });

        navigationView.bringToFront();
        navigationView.setNavigationItemSelectedListener(this);

        cartIcon = findViewById(R.id.cart_icon);
        cartIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openCart();
            }
        });
    }

    private void openCart() {
        Intent intent = new Intent(this, Cart.class);
        startActivity(intent);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private List<Item> getItem() {
        return Arrays.asList(
                new Item(R.drawable.image1, "Random Access Memories", "Daft Punk", "Funk", "Blending electronic and disco, this album offers a nostalgic yet innovative sonic experience with infectious grooves and collaborations with iconic artists.", "$10", 13),
                new Item(R.drawable.image2, "The Dark Side Of The Moon", "Pink Floyd", "Rock", "A profound journey through introspective themes of life, death, and the human condition, set to hauntingly beautiful music and innovative soundscapes.", "$20", 10),
                new Item(R.drawable.image3, "Good Kid, M.A.A.d City", "Kendrick Lamar", "Hip Hop", "Captures the struggles and triumphs of inner-city life with raw lyricism and captivating storytelling.", "$30", 15),
                new Item(R.drawable.image4, "Thriller", "Michael Jackson", "Funk", "Revolutionized the music industry with its pop, funk, and R&B hits, solidifying the artist's status as a cultural icon.", "$20", 9),
                new Item(R.drawable.image5, "Rumours", "Fleetwood Mac", "Pop", "A poignant exploration of love and heartbreak, showcasing harmonious melodies and captivating songwriting.", "$10", 11),
                new Item(R.drawable.image6, "Nevermind", "Nirvana", "Rock", "Catapulted a music genre into the mainstream with its raw energy and iconic singles, forever changing the landscape of popular music.", "$10", 12),
                new Item(R.drawable.image7, "The Dark Side Of The Moon", "Pink Floyd", "Rock", "Introspective exploration of life, death, and the human condition with hauntingly beautiful music.", "$20", 10),
                new Item(R.drawable.image8, "AM", "Arctic Monkeys", "Rock", "A stylish blend of rock and R&B, evoking the allure of late-night adventures and romantic entanglements.", "$30", 12),
                new Item(R.drawable.image9, "Mezzanine", "Massive Attack", "Electronic", "Creates a dark and atmospheric sonic landscape with elements of trip-hop, electronica, and rock, captivating listeners with its brooding soundscapes.", "$40", 11)
        );
    }

    private void populateItems(List<Item> items) {
        gridLayout.removeAllViews();
        for (Item item : items) {
            View itemView = getLayoutInflater().inflate(R.layout.activity_item, gridLayout, false);

            ImageView imageView = itemView.findViewById(R.id.item_image);
            TextView titleTextView = itemView.findViewById(R.id.item_title);
            TextView descriptionTextView = itemView.findViewById(R.id.item_description);

            Glide.with(this)
                    .load(item.getImageResId())
                    .transform(new CenterCrop(), new RoundedCorners(50))
                    .into(imageView);

            titleTextView.setText(item.getTitle());
            descriptionTextView.setText(item.getDescription());

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(ItemPage.this, ItemDetail.class);
                    intent.putExtra("title", item.getTitle());
                    intent.putExtra("artist", item.getArtist());
                    intent.putExtra("genre", item.getGenre());
                    intent.putExtra("description", item.getDescription());
                    intent.putExtra("price", item.getPrice());
                    intent.putExtra("imageResId", item.getImageResId());
                    intent.putExtra("trackCount", item.getTrackCount());
                    startActivity(intent);
                }
            });

            gridLayout.addView(itemView);
        }
    }

    private void setupGenreCapsules() {
        LinearLayout genreContainer = findViewById(R.id.genre_container);
        String[] genres = {"All", "Rock", "Electronic", "Pop", "Country", "Jazz", "Funk"};

        Button allButton = null;

        for (String genre : genres) {
            Button genreButton = new Button(this);
            genreButton.setText(genre);
            genreButton.setAllCaps(false);
            genreButton.setBackgroundResource(R.drawable.capsule_background);
            genreButton.setTextColor(ContextCompat.getColorStateList(this, R.color.colorPrimary));
            genreButton.setPadding(32, 16, 32, 16);

            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.WRAP_CONTENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT
            );

            params.setMargins(16, 0, 16, 0);
            genreButton.setLayoutParams(params);

            genreButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    for (int i = 0; i < genreContainer.getChildCount(); i++) {
                        Button btn = (Button) genreContainer.getChildAt(i);
                        btn.setBackgroundResource(R.drawable.capsule_unselected);
                        btn.setTextColor(ContextCompat.getColor(ItemPage.this, R.color.colorPrimary));
                    }

                    genreButton.setBackgroundResource(R.drawable.capsule_selected);
                    genreButton.setTextColor(ContextCompat.getColor(ItemPage.this, R.color.white));

                    filterItemsByGenre(genre);
                }
            });

            genreContainer.addView(genreButton);

            if (genre.equals("All")) {
                allButton = genreButton;
                allButton.setBackgroundResource(R.drawable.capsule_selected);
                allButton.setTextColor(ContextCompat.getColor(this, R.color.white));
            }
        }
        filterItemsByGenre("All");
    }

    private void filterItemsByGenre(String genre) {
        List<Item> allItems = getItem();
        List<Item> filteredItems;

        if (genre.equals("All")) {
            filteredItems = allItems;
        } else {
            filteredItems = new ArrayList<>();
            for (Item item : allItems) {
                if (item.getGenre().equals(genre)) {
                    filteredItems.add(item);
                }
            }
        }

        populateItems(filteredItems);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.nav_logout){
            Intent logoutIntent = new Intent(ItemPage.this, MainActivity.class);
            startActivity(logoutIntent);
        } else if (item.getItemId() == R.id.nav_home){
            Intent aboutUsIntent = new Intent(ItemPage.this, HomePage.class);
            startActivity(aboutUsIntent);
        } else if(item.getItemId() == R.id.nav_about_us) {
            Intent albumIntent = new Intent(ItemPage.this, AboutUs.class);
            startActivity(albumIntent);
        }
        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }
}

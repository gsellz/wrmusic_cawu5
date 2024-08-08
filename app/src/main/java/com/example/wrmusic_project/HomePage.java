package com.example.wrmusic_project;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.widget.Toolbar;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Bundle;
import android.os.Handler;
import android.view.MenuItem;
import android.widget.TextView;


import com.google.android.material.navigation.NavigationView;

import java.util.Locale;

public class HomePage extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private TextView welcomeUser;
    private TextView username;
    DrawerLayout drawerLayout;
    Toolbar toolbar;
    NavigationView navigationView;
//    private ViewFlipper carousel;

    private static final int SLIDE_DELAY_MS = 3000; // Delay in milliseconds
    private ViewPager2 viewPager;
    private Handler handler;
    private Runnable runnable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);

        drawerLayout = findViewById(R.id.drawerMenu);

        // ini variable username
        welcomeUser = findViewById(R.id.welcomeUser);
        String username = getIntent().getStringExtra("USERNAME_KEY");

        if (username != null) {
            welcomeUser.setText(username.toUpperCase(Locale.ROOT) + "!");
        } else {
            welcomeUser.setText("USERNAME!");
        }


        // ini carousel
        viewPager = findViewById(R.id.viewPager);
        int[] images = { R.drawable.image1, R.drawable.image2, R.drawable.image3, R.drawable.image4, R.drawable.image5 };
        CarouselAdapter adapter = new CarouselAdapter(images);
        viewPager.setAdapter(adapter);


        setupAutoSlide();

        // ini toolbar
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // ini navigation
        navigationView = findViewById(R.id.nav_view);
        navigationView.bringToFront();
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawerLayout, toolbar,
                R.string.navigation_drawer_open, R.string.navigation_drawer_close
        );
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        navigationView.setNavigationItemSelectedListener(this);
    }
    private void setupAutoSlide() {
        handler = new Handler();
        runnable = new Runnable() {
            @Override
            public void run() {
                int currentItem = viewPager.getCurrentItem();
                int nextItem = (currentItem + 1) % viewPager.getAdapter().getItemCount();
                viewPager.setCurrentItem(nextItem, true);
                handler.postDelayed(this, SLIDE_DELAY_MS);
            }
        };
        handler.postDelayed(runnable, SLIDE_DELAY_MS);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (handler != null && runnable != null) {
            handler.removeCallbacks(runnable);
        }
    }
    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){

        }
        return true;
    }
}
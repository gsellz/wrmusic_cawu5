package com.example.wrmusic_project;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.widget.Toolbar;
import androidx.viewpager2.widget.ViewPager2;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.ViewFlipper;
import android.view.GestureDetector;

import com.google.android.material.navigation.NavigationView;

import java.util.Locale;

public class HomePage extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private TextView welcomeUser;
    private DrawerLayout drawerLayout;
    private Toolbar toolbar;
    private NavigationView navigationView;
    private ImageView hamburgerButton;

    private static final int FLIP_INTERVAL = 3000;
    private GestureDetector gestureDetector;
    private Handler handler;
    private Runnable runnable;
    private ViewFlipper carousel;
    private ViewPager2 viewPagerTop;
    private TextView topCategoryTitle;
    private ImageButton closeDrawer;
    private  ImageView cartIcon;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);

        drawerLayout = findViewById(R.id.drawerMenu);
        navigationView = findViewById(R.id.nav_view);
        toolbar = findViewById(R.id.toolbar);



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

//        String username = getIntent().getStringExtra("USERNAME_KEY");
        SharedPreferences sharedPref = getSharedPreferences("user_prefs", MODE_PRIVATE);
        String username = sharedPref.getString("username_key", "USERNAME");


        if (username != null) {
            welcomeUser.setText(username.toUpperCase(Locale.ROOT) + "!");
            headerUsername.setText(username);
        } else {
            welcomeUser.setText("USERNAME!");
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

        // Inisialisasi topCategoryTitle
        topCategoryTitle = findViewById(R.id.topCategoryTitle);

        // Ini carousel
        int[] images = { R.drawable.carousel1, R.drawable.carousel2, R.drawable.carousel3, R.drawable.carousel4, R.drawable.carousel5 };

        carousel = findViewById(R.id.viewFlipper);
        carousel.setFocusableInTouchMode(true);
        carousel.requestFocus();
        carousel.bringToFront();
        gestureDetector = new GestureDetector(this, new MyGestureListener());
        carousel.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                gestureDetector.onTouchEvent(event);
                return true;
            }
        });

        for (int image : images) {
            ImageView imageView = new ImageView(this);
            imageView.setBackgroundResource(image);
            carousel.addView(imageView);
        }

        // Initialize the handler and the runnable for auto-flip
        handler = new Handler(Looper.getMainLooper());
        runnable = new Runnable() {
            @Override
            public void run() {
                onSwipeLeft();
                handler.postDelayed(this, FLIP_INTERVAL);
            }
        };

        navigationView.bringToFront();
        navigationView.setNavigationItemSelectedListener(this);

        cartIcon = findViewById(R.id.cart_icon);
        cartIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openCart();
            }
        });

        // Set up ViewPager2 untuk bagian Top
        viewPagerTop = findViewById(R.id.viewPagerTop);
        TopPagerAdapter adapter = new TopPagerAdapter(this);
        viewPagerTop.setAdapter(adapter);

        viewPagerTop.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                switch (position) {
                    case 0:
                        topCategoryTitle.setText("Top");
                        break;
                    case 1:
                        topCategoryTitle.setText("Top Rock");
                        break;
                    case 2:
                        topCategoryTitle.setText("Top Pop");
                        break;
                }
            }
        });
    }

    private class MyGestureListener extends GestureDetector.SimpleOnGestureListener {

        private static final int SWIPE_THRESHOLD = 50;
        private static final int SWIPE_VELOCITY_THRESHOLD = 50;

        @Override
        public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
            float diffX = e2.getX() - e1.getX();
            float diffY = e2.getY() - e1.getY();
            if (Math.abs(diffX) > Math.abs(diffY)) {
                if (Math.abs(diffX) > SWIPE_THRESHOLD && Math.abs(velocityX) > SWIPE_VELOCITY_THRESHOLD) {
                    if (diffX > 0) {
                        onSwipeRight();
                    } else {
                        onSwipeLeft();
                    }
                    return true;
                }
            }
            return false;
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return gestureDetector.onTouchEvent(event);
    }

    private void onSwipeRight() {
        carousel.setInAnimation(this, R.anim.slide_in_left);
        carousel.setOutAnimation(this, R.anim.slide_out_right);
        carousel.showPrevious();
        resetAutoFlip();
    }

    private void onSwipeLeft() {
        carousel.setInAnimation(this, R.anim.slide_in_right);
        carousel.setOutAnimation(this, R.anim.slide_out_left);
        carousel.showNext();
        resetAutoFlip();
    }

    private void resetAutoFlip() {
        handler.removeCallbacks(runnable);
        handler.postDelayed(runnable, FLIP_INTERVAL);
    }

    @Override
    protected void onPause() {
        super.onPause();
        handler.removeCallbacks(runnable);
    }

    @Override
    protected void onResume() {
        super.onResume();
        handler.postDelayed(runnable, FLIP_INTERVAL);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        handler.removeCallbacks(runnable);
    }

    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    private void openCart() {
        Intent intent = new Intent(this, Cart.class);
        startActivity(intent);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.nav_logout){
            Intent logoutIntent = new Intent(HomePage.this, MainActivity.class);

            startActivity(logoutIntent);
        }
        else if (item.getItemId() == R.id.nav_about_us){
            Intent aboutUsIntent = new Intent(HomePage.this, AboutUs.class);
            startActivity(aboutUsIntent);
        }
        else if(item.getItemId() == R.id.nav_album)
        {
            Intent albumIntent = new Intent(HomePage.this, ItemPage.class);
            startActivity(albumIntent);
        }
        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }
}

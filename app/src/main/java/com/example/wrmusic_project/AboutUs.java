package com.example.wrmusic_project;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.navigation.NavigationView;
import com.google.android.material.tabs.TabLayout;

import java.util.Locale;

public class AboutUs extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private TextView welcomeUser;
    private DrawerLayout drawerLayout;
    private Toolbar toolbarAbout;
    private NavigationView navigationView;
    private ImageView hamburgerButton;
    private ImageButton closeDrawer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_us);

        TabLayout tabLayout = findViewById(R.id.tabLayout);
        ViewPager viewPager = findViewById(R.id.viewPager);
        ImageView cartIcon = findViewById(R.id.cart_icon);
        drawerLayout = findViewById(R.id.drawerMenu);
        navigationView = findViewById(R.id.nav_view);
        toolbarAbout = findViewById(R.id.toolbarAbout);

        setSupportActionBar(toolbarAbout);

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

        cartIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Handle cart icon click
                openCart();
            }
        });

        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(new AboutUsFragment(), "About Us");
        adapter.addFragment(new ContactUsFragment(), "Contact Us");
        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);

        // Set custom views for tabs
        for (int i = 0; i < tabLayout.getTabCount(); i++) {
            TabLayout.Tab tab = tabLayout.getTabAt(i);
            View customTab = null;
            if (tab != null) {
                if (i == 0) {
                    customTab = LayoutInflater.from(this).inflate(R.layout.tab_custom_view_left, null);
                    // Apply initial background for the first tab
                    customTab.setBackground(getResources().getDrawable(R.drawable.tab_background_selector));
                } else if (i == 1) {
                    // Apply initial background for the second tab
                    customTab = LayoutInflater.from(this).inflate(R.layout.tab_custom_view_right, null);
                    customTab.setBackground(getResources().getDrawable(R.drawable.tab_background_selector_right));
                }
                TextView tabText = customTab.findViewById(R.id.tabText);
                tabText.setText(adapter.getPageTitle(i));
                tab.setCustomView(customTab);
            }
        }

        // Add TabSelectedListener
        tabLayout.addOnTabSelectedListener(new TabLayout.ViewPagerOnTabSelectedListener(viewPager) {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                super.onTabSelected(tab);
                View tabView = tab.getCustomView();
                if (tabView != null) {
                    if (tab.getPosition() == 0) {
                        tabView.setBackground(getResources().getDrawable(R.drawable.tab_selected_left));
                    } else if (tab.getPosition() == 1) {
                        tabView.setBackground(getResources().getDrawable(R.drawable.tab_selected_right));
                    }
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                super.onTabUnselected(tab);
                View tabView = tab.getCustomView();
                if (tabView != null) {
                    if (tab.getPosition() == 0) {
                        tabView.setBackground(getResources().getDrawable(R.drawable.tab_unselected_left));
                    } else if (tab.getPosition() == 1) {
                        tabView.setBackground(getResources().getDrawable(R.drawable.tab_unselected_right));
                    }
                }
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
                super.onTabReselected(tab);
            }
        });
    }

    private void openCart() {
        // Logic to open cart
        Intent intent = new Intent(this, Cart.class);
        startActivity(intent);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.nav_logout){
            Intent logoutIntent = new Intent(AboutUs.this, MainActivity.class);
            startActivity(logoutIntent);
        }
        else if (item.getItemId() == R.id.nav_home){
            Intent aboutUsIntent = new Intent(AboutUs.this, HomePage.class);
            startActivity(aboutUsIntent);
        }
        else if(item.getItemId() == R.id.nav_album)
        {
            Intent albumIntent = new Intent(AboutUs.this, ItemPage.class);
            startActivity(albumIntent);
        }
        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {
        super.onPointerCaptureChanged(hasCapture);
    }
}

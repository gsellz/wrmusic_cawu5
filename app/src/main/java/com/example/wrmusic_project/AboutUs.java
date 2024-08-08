package com.example.wrmusic_project;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;

public class AboutUs extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_us);

        TabLayout tabLayout = findViewById(R.id.tabLayout);
        ViewPager viewPager = findViewById(R.id.viewPager);
        ImageButton cartIcon = findViewById(R.id.cart_icon);

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
}

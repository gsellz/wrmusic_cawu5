package com.example.wrmusic_project;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import java.util.Locale;

public class HomePage extends AppCompatActivity {

    private TextView welcomeUser;
    private TextView username;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);

        welcomeUser = findViewById(R.id.welcomeUser);

        String username = getIntent().getStringExtra("USERNAME_KEY");

        if (username != null){
            welcomeUser.setText("Welcome\n" + username.toUpperCase(Locale.ROOT) + "!");
        }
        else {
            welcomeUser.setText("Welcome!");
        }


    }
}
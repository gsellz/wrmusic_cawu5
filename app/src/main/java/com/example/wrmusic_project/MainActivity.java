package com.example.wrmusic_project;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private EditText usernameEt, passwordEt;
    private TextView usernameError, passwordError;
    private Button loginBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        usernameEt = findViewById(R.id.usernameEt);
        passwordEt = findViewById(R.id.passwordEt);
        usernameError = findViewById(R.id.usernameError);
        passwordError = findViewById(R.id.passwordError);
        loginBtn = findViewById(R.id.loginBtn);

        loginBtn.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                switch (motionEvent.getAction()){
                    case MotionEvent.ACTION_DOWN:
                        view.setAlpha(0.8f);
                        return false;
                    case MotionEvent.ACTION_UP:
                        view.setAlpha(1.0f);
                        return false;
                }
                return false; // Allow OnClickListener to handle the click
            }
        });

        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean isValid = true;

                String username = usernameEt.getText().toString().trim();
                if (username.isEmpty()) {
                    usernameError.setVisibility(View.VISIBLE);
                    usernameError.setText("*Username Must Be Filled");
                    isValid = false;
                } else if (username.length() <= 5 || username.length() >= 10) {
                    usernameError.setVisibility(View.VISIBLE);
                    usernameError.setText("*Username Length Must be >5 and <10");
                    isValid = false;
                } else {
                    usernameError.setVisibility(View.INVISIBLE);
                }

                String password = passwordEt.getText().toString().trim();
                if (password.isEmpty()) {
                    passwordError.setVisibility(View.VISIBLE);
                    passwordError.setText("Password Must Be Filled");
                    isValid = false;
                } else {
                    passwordError.setVisibility(View.INVISIBLE);
                }

                if (isValid) {
                    Intent intent = new Intent(MainActivity.this, HomePage.class);
                    intent.putExtra("USERNAME_KEY", username);
                    startActivity(intent);
                    //tes
                }
            }
        });
    }

    public static class CartItem {
    }
}

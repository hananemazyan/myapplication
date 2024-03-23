package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import android.widget.CheckBox;

public class Activity_login extends AppCompatActivity {

    EditText usernameEditText, passwordEditText;
    Button loginButton;
    TextView signUpTextView, forgotPasswordTextView;
    CheckBox sessionActiveCheckBox;
    DBHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        // Initialize views
        usernameEditText = findViewById(R.id.editTextEmail);
        passwordEditText = findViewById(R.id.editTextPassword);
        loginButton = findViewById(R.id.buttonSeConnecter);
        signUpTextView = findViewById(R.id.textViewSignUp);
        forgotPasswordTextView = findViewById(R.id.textViewForgotPassword);
        sessionActiveCheckBox = findViewById(R.id.checkBoxSessionActive);

        // Initialize DBHelper
        dbHelper = new DBHelper(this);

        // Set click listener for login button
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                login();
            }
        });

        // Set click listener for sign up text view
        signUpTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Navigate to sign up activity
                Intent intent = new Intent(Activity_login.this, SignupActivity.class);
                startActivity(intent);
            }
        });

        // Set click listener for forgot password text view
        forgotPasswordTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Implement your forgot password logic here
                Toast.makeText(Activity_login.this, "Forgot password clicked", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void login() {
        String username = usernameEditText.getText().toString().trim();
        String password = passwordEditText.getText().toString().trim();

        if (username.isEmpty() || password.isEmpty()) {
            Toast.makeText(this, "Please enter username and password", Toast.LENGTH_SHORT).show();
            return;
        }

        if (dbHelper.checkUser(username, password)) {
            Toast.makeText(this, "Login successful", Toast.LENGTH_SHORT).show();
            // Navigate to another activity or perform other actions
            // For example, navigate to MainActivity
            Intent intent = new Intent(Activity_login.this, Activity_main.class);
            startActivity(intent);
            finish(); // Finish the current activity
        } else {
            Toast.makeText(this, "Invalid username or password", Toast.LENGTH_SHORT).show();
        }
    }
}

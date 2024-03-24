package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class Activity_login extends AppCompatActivity {

    private DBHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        dbHelper = new DBHelper(this);
    }
    public void gererConnexion(View v) {
        EditText emailEditText = findViewById(R.id.editTextEmail); // Adapter l'ID au layout
        EditText motdepasseEditText = findViewById(R.id.editTextPassword); // Adapter l'ID au layout
        String mail = emailEditText.getText().toString();
        String password = motdepasseEditText.getText().toString();

        if (dbHelper.verifierConnexion(mail, password)) {
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
            finish(); // Fermer l'activité actuelle
        } else {
            Toast.makeText(this, "L'email ou le mot de passe saisi est incorrect!", Toast.LENGTH_SHORT).show();
        }
    }

    public void inscrire(View v) {
        Intent intent = new Intent(Activity_login.this, SignupActivity.class);
        startActivity(intent);
    }
}

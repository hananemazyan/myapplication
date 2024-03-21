package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);


        Spinner spinnerVille = findViewById(R.id.spinnerVille);


        String[] citiesArray = getResources().getStringArray(R.array.cities_array);


        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_item, citiesArray);


        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);


    }
}
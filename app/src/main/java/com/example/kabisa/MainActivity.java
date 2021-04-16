package com.example.kabisa;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private TextView _suivant;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //ActionBar actionBar = getSupportActionBar();
        //actionBar.setTitle("Kabisa");
        //actionBar.setDisplayHomeAsUpEnabled(true);
       // actionBar.setDisplayShowHomeEnabled(true);
        _suivant = findViewById(R.id.suivant);
        _suivant.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, Bienvenue.class));
                finish();
            }
        });
    }
}
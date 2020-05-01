package com.example.vehicle;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainMenuActivity extends AppCompatActivity {
    public Button btnLogin, btnRegister;



    public void init() {
        btnLogin = (Button) findViewById(R.id.btnLogin);
        btnRegister = (Button) findViewById(R.id.btnRegister);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mainmenu);
        init();

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentLogin = new Intent(MainMenuActivity.this, LoginActivity.class);
                startActivity(intentLogin);
            }
        });

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentRegister = new Intent(MainMenuActivity.this, AgeControlActivity.class);
                startActivity(intentRegister);
            }
        });
    }
}


package com.example.vehicle;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;

public class AgeControlActivity extends AppCompatActivity {
    public Button btnAdultUser, btnChildUser;
    public void init(){
        btnAdultUser = (Button) findViewById(R.id.btnAdultUser);
        btnChildUser = (Button) findViewById(R.id.btnChildUser);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agecontrol);

    }
    public void openRegActivity(View view) {
        init();
        Intent intentReg = new Intent(AgeControlActivity.this, RegisterActivity.class);
        startActivity(intentReg);
    }

}








package com.example.myfood;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

public class EmailActivity extends AppCompatActivity {

    String payload;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_email);
        payload = getIntent().getStringExtra("payload");
        Toast.makeText(this, ""+payload, Toast.LENGTH_SHORT).show();


        // POST THIS PAYLOAD


    }
}

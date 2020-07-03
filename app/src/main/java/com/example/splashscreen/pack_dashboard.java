package com.example.splashscreen;


import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class pack_dashboard extends AppCompatActivity {

    public Button btn;
    public ImageView mumbai,pune,nashik,auragabad,nagpur,kolhapur;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pack_dashboard);

        mumbai = (ImageView) findViewById(R.id.imgB2);
        mumbai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(getApplicationContext(), mumbai.class);
                startActivity(intent);


            }
        });

        pune = (ImageView) findViewById(R.id.imageButton4);
        pune.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), pune.class);
                startActivity(intent);
            }
        });

        auragabad = (ImageView) findViewById(R.id.imgB5);
        auragabad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),aurangabad.class);
                startActivity(intent);
            }
        });

        nagpur = (ImageView) findViewById(R.id.imgB6);
        nagpur.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), nagpur.class);
                startActivity(intent);
            }
        });

        nashik = (ImageView) findViewById(R.id.imgB7);
        nashik.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), nashik.class);
                startActivity(intent);
            }
        });
        kolhapur = (ImageView) findViewById(R.id.imgB8);
        kolhapur.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), kolhapur.class);
                startActivity(intent);
            }
        });



    }
}

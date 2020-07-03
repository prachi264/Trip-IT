package com.example.splashscreen;


import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class CarMainActivity extends AppCompatActivity {

    Button btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_car_main);
        //setContentView(R.layout);

        btn = (Button)findViewById(R.id.search);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                movetoAvailableCar();
            }

            private void movetoAvailableCar()
            {
                Intent intent = new Intent(CarMainActivity.this,ListOfAvailableCar.class);
                startActivity(intent);
            }
        });

    }
}


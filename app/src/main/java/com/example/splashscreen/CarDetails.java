package com.example.splashscreen;


import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class CarDetails extends AppCompatActivity {

    //    EditText cname, cnumber, ctype, seat, dname, dmoblie, rate;
    Button book;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_car_details);


        book = (Button) findViewById(R.id.book);

        book.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                moveToPassenger();
            }

            public void moveToPassenger() {
                Intent intent = new Intent(CarDetails.this, PassengerDetails.class);
                startActivity(intent);
            }
        });
    }
}

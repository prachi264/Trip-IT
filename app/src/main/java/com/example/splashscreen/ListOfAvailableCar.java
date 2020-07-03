package com.example.splashscreen;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class ListOfAvailableCar extends AppCompatActivity {

    Button btn, btn2,btn3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_of_available_car);


        btn = (Button)findViewById(R.id.car1);
        btn2 = (Button)findViewById(R.id.car2);
        btn3 = (Button)findViewById(R.id.car3);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                movetoCarDeatils();
            }

            public void movetoCarDeatils()
            {
                Intent intent = new Intent(ListOfAvailableCar.this, CarDetails.class);
                startActivity(intent);
            }
        });

        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                movetoCarDeatils2();
            }

            public void movetoCarDeatils2()
            {
                Intent intent = new Intent(ListOfAvailableCar.this, CarDetails.class);
                startActivity(intent);
            }
        });

        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                movetoCarDeatils3();
            }

            public void movetoCarDeatils3()
            {
                Intent intent = new Intent(ListOfAvailableCar.this, CarDetails.class);
                startActivity(intent);
            }
        });
    }
}

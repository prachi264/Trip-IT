package com.example.splashscreen;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class PassengerDetails extends AppCompatActivity {

    Button btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_passenger_details);

        btn = (Button)findViewById(R.id.submit1);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                movetoAvailableCar();
            }

            private void movetoAvailableCar()
            {
                Intent intent = new Intent(PassengerDetails.this,CarMainActivity.class);
                startActivity(intent);
            }
        });

    }
}

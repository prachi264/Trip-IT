package com.example.splashscreen;


import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.w3c.dom.Text;

public class tkykop extends AppCompatActivity {

    public String intentData;
    String cityname="";
    TextView text;
    TextView fare;
    int amount=0;
    int package_service=0;
    int package_tourguide=0;
    int traveller=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tkykop);

        text = (TextView)findViewById(R.id.textView19);
        fare = (TextView) findViewById(R.id.textView21);

        intentData = getIntent().getStringExtra("cityname");

        String data[] = intentData.split(":");
        cityname = data[0];
        cityname = data[1];
        amount = Integer.parseInt(data[1]);

        traveller = Integer.parseInt(Preferences.getStringPreferences(getApplicationContext(),"package_passanger"));

        text.setText("Your Booked For "+data[0]);

        package_service = Integer.parseInt(Preferences.getStringPreferences(getApplicationContext(),"package_service"));
        package_tourguide = Integer.parseInt(Preferences.getStringPreferences(getApplicationContext(),"package_tourguide"));
        if(package_service==1 ){

            amount = 1000+amount;

        }
        amount = amount*traveller;
        if (package_tourguide==1){

            amount = 2000+amount;

        }


        fare.setText(""+amount);

    }
}

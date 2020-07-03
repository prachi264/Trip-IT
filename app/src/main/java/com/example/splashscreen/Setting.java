package com.example.splashscreen;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Setting extends AppCompatActivity {

    EditText cab, flight,hotel;
    EditText port;
    String hotel_ip,flight_ip,cab_ip,port_addr;
    Button connectButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);

        flight = (EditText) findViewById(R.id.port);//Flight
        hotel = (EditText) findViewById(R.id.serverURI);//Hotel
        cab = (EditText) findViewById(R.id.TopicET);//cab
        port = (EditText) findViewById(R.id.pubTopicET);//port

        final Context c;

        if (Preferences.getStringPreferences(this, "flight") != null) {
            flight.setText(Preferences.getStringPreferences(this, "flight"));
        }
        if (Preferences.getStringPreferences(this, "hotel") != null) {
            hotel.setText(Preferences.getStringPreferences(this, "hotel"));
        }
        if (Preferences.getStringPreferences(this, "cab") != null) {
            cab.setText(Preferences.getStringPreferences(this, "cab"));
        }
        if (Preferences.getStringPreferences(this, "port") != null) {
            port.setText(Preferences.getStringPreferences(this, "port"));
        }

        connectButton = (Button) findViewById(R.id.connectButton);
        c = this;
        connectButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               connect();
            }
        });


    }


    public void connect() {

        hotel_ip = hotel.getText().toString();
        cab_ip = cab.getText().toString();
        flight_ip = flight.getText().toString();
        port_addr = port.getText().toString();
        if (hotel_ip == "" || hotel_ip == null) {
            Toast.makeText(this, "Enter the Hotel Ip Address", Toast.LENGTH_SHORT).toString();
        }  else if (cab_ip == "" || cab_ip == null) {
            Toast.makeText(this, "Enter the cab Ip Address", Toast.LENGTH_SHORT).toString();
        } else if (flight_ip == "" || flight_ip == null) {
            Toast.makeText(this, "Enter the Flight Ip Address", Toast.LENGTH_SHORT).toString();
        }else if (port_addr == "" || port_addr == null) {
            Toast.makeText(this, "Enter the Port Ip Address", Toast.LENGTH_SHORT).toString();
        } else {


            hotel_ip = ((AutoCompleteTextView) findViewById(R.id.serverURI))
                    .getText().toString();
            flight_ip = ((EditText) findViewById(R.id.port)).getText().toString();
            cab_ip = ((EditText) findViewById(R.id.TopicET)).getText().toString();
            port_addr = ((EditText) findViewById(R.id.pubTopicET)).getText().toString();
            try {
                Preferences.saveStringPreferences(Setting.this, "flight", flight_ip);
                Preferences.saveStringPreferences(Setting.this, "port", port_addr);
                Preferences.saveStringPreferences(Setting.this, "hotel", hotel_ip);
                Preferences.saveStringPreferences(Setting.this, "cab", cab_ip);
                //       Toast.makeText(this, "Configuration Save Successfully ", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(this, Dashboard1.class);
                startActivity(intent);

            } catch (Exception e) {
                //  Toast.makeText(this, "Configure Not Save Properly", Toast.LENGTH_SHORT).show();
            }
        }
    }


}

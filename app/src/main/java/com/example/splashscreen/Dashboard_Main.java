package com.example.splashscreen;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

public class Dashboard_Main extends AppCompatActivity {


    private ImageView bus,bus1,airplane,train,cycle,bike;
    private android.view.View.OnClickListener View;
    public TextView about_us;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard__main);


        ImageView profile=findViewById(R.id.profile);
        ImageView flight=findViewById(R.id.flight);
        ImageView schedule=findViewById(R.id.schedule);
        ImageView contactus=findViewById(R.id.contactus);
        // ImageView cycle=findViewById(R.id.cycle);
        //ImageView bike=findViewById(R.id.bike);
        about_us = (TextView) findViewById(R.id.about_us);


        profile.setOnClickListener(new android.view.View.OnClickListener() {
                                       @Override
                                       public void onClick(android.view.View v) {
                                           Intent intent=new Intent(Dashboard_Main.this, pack_mainpage.class);
                                           startActivity(intent);
                                       }
                                   }


        );

        flight.setOnClickListener(new View.OnClickListener() {
                                      @Override
                                      public void onClick(android.view.View v) {
                                          Intent intent=new Intent(Dashboard_Main.this, Dashboard1.class);
                                          startActivity(intent);
                                      }
                                  }


        );

        schedule.setOnClickListener(new View.OnClickListener() {
                                        @Override
                                        public void onClick(android.view.View v) {
                                            Intent intent=new Intent(Dashboard_Main.this, HotelBookingMain.class);
                                            startActivity(intent);
                                        }
                                    }


        );


        contactus.setOnClickListener(new View.OnClickListener() {
                                         @Override
                                         public void onClick(android.view.View v) {
                                             Intent intent=new Intent(Dashboard_Main.this, CarDetails.class);
                                             startActivity(intent);
                                         }
                                     }


        );
        about_us.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(android.view.View v) {

                Intent intent = new Intent(Dashboard_Main.this,About_Us.class);
                startActivity(intent);

            }
        });






    }


    public void getLogin(View view){

        Intent intent = new Intent(getApplicationContext(), Login.class);
        intent.putExtra("action","dashboard");
        startActivity(intent);

    }

    public void getRegistration(View view){

        Intent intent = new Intent(getApplicationContext(), Registration.class);
        startActivity(intent);

    }

}

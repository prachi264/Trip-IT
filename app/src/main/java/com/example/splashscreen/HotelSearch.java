package com.example.splashscreen;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.media.Rating;
import android.os.Build;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

public class HotelSearch extends AppCompatActivity {

    LinearLayout hotelDetails[];
    LinearLayout master;
    ImageView imageView[];
    RatingBar rating[];
    LinearLayout hotelbasic[];
    TextView name[],price[];
    public String image_name;


    @RequiresApi(api = Build.VERSION_CODES.Q)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hotel_search);

        String msg[] = getIntent().getStringExtra("hotel_details").split("\n");
        master = findViewById(R.id.masterlay);
        //Toast.makeText(getApplicationContext(),""+getIntent().getStringExtra("hotel_details"),Toast.LENGTH_LONG).show();

        //setting the data ka juggad
        hotelDetails = new LinearLayout[msg.length];
        imageView = new ImageView[msg.length];
        rating = new RatingBar[msg.length];
        hotelbasic = new LinearLayout[msg.length];
        name = new TextView[msg.length];
        price = new TextView[msg.length];

        for (int i=0;i<msg.length;i++){

            hotelDetails[i] = new LinearLayout(getApplicationContext());
            imageView[i] = new ImageView(getApplicationContext());
            name[i]= new TextView(getApplicationContext());
            rating[i] = new RatingBar(getApplicationContext());
            price[i] = new TextView(getApplicationContext());
            hotelbasic[i] = new LinearLayout(getApplicationContext());

            //setting of layout views
            hotelDetails[i].setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,0,(100/msg.length)));
            hotelDetails[i].setWeightSum(100);
            hotelDetails[i].setOrientation(LinearLayout.HORIZONTAL);
            hotelDetails[i].setId(i);

            //"hotel_name:trident hotel_rating:4.1 hotel_price:7000 room_cnt:50

            //hotelimage
            String hotelname[]  = msg[i].split(" ");
            String hname[] = hotelname[0].split(":");
            image_name = "@drawable/"+hname[1].toLowerCase();
            int resource = getResources().getIdentifier(image_name,null,getPackageName());
            Drawable res = getResources().getDrawable(resource);
            imageView[i] = new ImageView(getApplicationContext());
            imageView[i].setImageDrawable(res);
            //imageView[i].getLayoutParams(). = 40;
            imageView[i].setLayoutParams(new LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.MATCH_PARENT,43));


            //Hotelname
            name[i] = new TextView(getApplicationContext());
            name[i].setText(hname[1]);
            name[i].setAllCaps(true);
            name[i].setTextSize(20);
            name[i].setTypeface(null, Typeface.BOLD);
            name[i].setGravity(Gravity.CENTER_VERTICAL);
            name[i].setTextColor(Color.parseColor("#0000FF"));
            name[i].setLayoutParams(new LinearLayout.LayoutParams( LinearLayout.LayoutParams.MATCH_PARENT,0,30));


            //Hotel price
            String hprice[] = hotelname[2].split(":");

            price[i] = new TextView(getApplicationContext());
            price[i].setText(hprice[1]);
            price[i].setTextSize(15);
            price[i].setTypeface(null, Typeface.NORMAL);
            price[i].setGravity(Gravity.CENTER_VERTICAL);
            price[i].setTextColor(Color.parseColor("#0000FF"));
            price[i].setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,0,20));

            //RatingBar
            String hrating[] = hotelname[1].split(":");
            rating[i] = new RatingBar(getApplicationContext());
            rating[i].setRating(Float.parseFloat(hrating[1]));
            rating[i].setNumStars(5);
            rating[i].setIsIndicator(true);
            rating[i].setLayoutParams(new LinearLayout.LayoutParams( LinearLayout.LayoutParams.MATCH_PARENT,0,50));


            hotelbasic[i].setLayoutParams(new LinearLayout.LayoutParams(0,LinearLayout.LayoutParams.MATCH_PARENT,57));
            hotelbasic[i].setWeightSum(100);
            hotelbasic[i].setOrientation(LinearLayout.VERTICAL);





           //adding name and price
            hotelbasic[i].addView(name[i]);
            hotelbasic[i].addView(price[i]);
            hotelbasic[i].addView(rating[i]);

            //adding view to master
            hotelDetails[i].addView(imageView[i]);
            hotelDetails[i].addView(hotelbasic[i]);

            hotelDetails[i].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    setData(v.getId());

                }
            });

            master.addView(hotelDetails[i]);
            System.out.println("value of loop: "+rating[i].getRating());

        }


    }

    public void setData(int i){

        String hotel_name = name[i].getText().toString();
        String hotel_price = price[i].getText().toString();
        Preferences.saveStringPreferences(getApplicationContext(),"hotel_name",hotel_name);
        Preferences.saveStringPreferences(getApplicationContext(),"hotel_price",hotel_price);
        String logininfo="";

        //checking session / loged in or not
        logininfo = checkLogin();
        if (logininfo.equals("")){

            Toast.makeText(getApplicationContext(),"Not Logged In do login first",Toast.LENGTH_LONG ).show();
            //Intent code for Login has to be here
            Intent intent = new Intent(getApplicationContext(),Login.class);
            intent.putExtra("action","hotel");
            startActivity(intent);
        }else{


            //Intent code for booking and logininfo and flight details has to be passed
            Intent intent = new Intent(getApplicationContext(),Hotel_Booking.class);
            startActivity(intent);

        }


    }


    public String checkLogin(){

        String logininfo="";

        if (Preferences.getStringPreferences(this, "username") != null) {

            logininfo = Preferences.getStringPreferences(this, "username");
            logininfo = logininfo+" "+Preferences.getStringPreferences(this,"password");


        }else{

            //Preferences.saveStringPreferences(getApplicationContext(),"flight_company");

        }

        return logininfo;
    }

}

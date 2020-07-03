package com.example.splashscreen;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

public class Hotel_Success extends AppCompatActivity {
    TextView txt_succes;
    ImageView success_image;
    TextView data;
    public String boooking_id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hotel__success);

        boooking_id = getIntent().getStringExtra("booking_id");
        txt_succes=(TextView) findViewById(R.id.txt_succes);
        success_image = (ImageView) findViewById(R.id.img_success);
        data = (TextView) findViewById(R.id.data);

        setData();


    }

    public void setData(){

        String msg="";
        String price = Preferences.getStringPreferences(getApplicationContext(),"hotel_price");
        String hotel_name = Preferences.getStringPreferences(getApplicationContext(),"hotel_name");
        String check_in = Preferences.getStringPreferences(getApplicationContext(),"check_in");
        String check_out = Preferences.getStringPreferences(getApplicationContext(),"check_out");
        String room = Preferences.getStringPreferences(getApplicationContext(),"room_count");

        msg= "Booking_id = "+boooking_id;
        msg = msg+"\nHotel_name = "+hotel_name.toUpperCase()+"\n";
        msg = msg+"Check_in   = "+check_in+"\n";
        msg = msg+"Check_out  = "+check_out+"\n";
        msg = msg+"room_count = "+room+"\n";
        msg = msg+"price      = "+(Integer.parseInt(price)*Integer.parseInt(room))+"\n";

        data.setText(msg);

    }

}

package com.example.splashscreen;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class FlightSearch extends AppCompatActivity {

    TextView disp;
    LinearLayout flight_details[];
    ImageView imageView[];
    TextView data[];
    TextView amount[];
    LinearLayout master;
    LinearLayout.LayoutParams params;
    String source_city="",destination_city="",company_name="",take_time="",landing="",seat_category="",image_name="",flight_no="";
    int passenger_count=0,available_seat=0;
    float money=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flight_search);

        master = (LinearLayout)findViewById(R.id.master_layout);
        String response = getIntent().getStringExtra("flight_details");

        //Bifurcation of the response for getting the no of flight
        String no_flight[]= response.split("\n");
        //String flight_data[] = new String[no_flight.length];
        flight_details = new LinearLayout[no_flight.length];
        imageView = new ImageView[no_flight.length];
        data = new TextView[no_flight.length];
        amount = new TextView[no_flight.length];

        //Toast.makeText(getApplicationContext(),"length = "+take_time.length,Toast.LENGTH_SHORT).show();



        for (int i = 0;i<no_flight.length;i++){

            flight_details[i] = new LinearLayout(getApplicationContext());

            //setting basic attributes of layout
           // params.height = 0;
            //params.width = LinearLayout.LayoutParams.MATCH_PARENT;
            //params.weight = 20;
            flight_details[i].setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,0,20));
            flight_details[i].setWeightSum(100);
            flight_details[i].setOrientation(LinearLayout.HORIZONTAL);
            flight_details[i].setId(i);

            //parsing the json data
            String flight_data[] = no_flight[i].split(" ");
            String company[] = flight_data[0].split(":");
            company_name = company[1].toLowerCase();
            String take_off[] = flight_data[3].split("-");
            take_time = take_off[1];
            String landing_time[] = flight_data[4].split("-");
            landing = landing_time[1];
            String source[] = flight_data[1].split(":");
            source_city = source[1];
            String destination[] = flight_data[2].split(":");
            destination_city = destination[1];
            String seat[] = flight_data[5].split(":");
            available_seat = Integer.parseInt(seat[1]);
            String no_traveller[] = flight_data[6].split(":");
            passenger_count = Integer.parseInt(no_traveller[1]);
            String category[] = flight_data[7].split(":");
            seat_category = category[1];
            String flight_id[] = flight_data[8].split(":");
            flight_no = flight_id[1];
            System.out.println(company_name+"\n\n"+source_city+"-"+destination_city+"\n"+take_time+"-"+landing);

            image_name = "@drawable/"+company_name;
            int resource = getResources().getIdentifier(image_name,null,getPackageName());
            Drawable res = getResources().getDrawable(resource);
            imageView[i] = new ImageView(getApplicationContext());
            imageView[i].setImageDrawable(res);
            //imageView[i].getLayoutParams(). = 40;
            imageView[i].setLayoutParams(new LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.MATCH_PARENT,40));

            data[i] = new TextView(getApplicationContext());
            data[i].setText(company_name+"\n"+flight_no+"\n"+"traveller="+passenger_count+"\n"+source_city+"-"+destination_city+"\n"+take_time+"-"+landing);
            data[i].setAllCaps(true);
            data[i].setTextSize(15);
            data[i].setId(i);
            data[i].setTextColor(Color.parseColor("#0000FF"));
            data[i].setLayoutParams(new LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.MATCH_PARENT,50));

            //fare calculating algorithm
            if(seat_category.equals("E")){

                money = 2000+(200 * (10-(int)(available_seat/10)));
                money = money * passenger_count;

            }else {

                money = 3000+(200 * (10-(int)(available_seat/10)));
                money = money * passenger_count;

            }

            amount[i] = new TextView(getApplicationContext());
            amount[i].setText(""+money);
            amount[i].setAllCaps(true);
            amount[i].setTextSize(10);
            amount[i].setId(i);
            amount[i].setGravity(Gravity.CENTER);
            amount[i].setTextColor(Color.parseColor("#0000FF"));
            amount[i].setLayoutParams(new LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.MATCH_PARENT,10));


            //adding all views to linear layout;
            flight_details[i].addView(imageView[i]);
            flight_details[i].addView(data[i]);
            flight_details[i].addView(amount[i]);

            flight_details[i].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    setData(v.getId());
                }
            });

            //adding linear layout to master layout
            master.addView(flight_details[i]);

        }

        //disp = (TextView) findViewById(R.id.disp);
        //disp.setText(no_flight[1]);
    }

    public void setData(int i){

        String logininfo="";

        Toast.makeText(getApplicationContext(),""+data[i].getText().toString()+""+amount[i].getText().toString(),Toast.LENGTH_SHORT).show();
        //saving flight no, price, passenger_count for futher identification
        String flight_save[] = data[i].getText().toString().split("\n");
        flight_no = flight_save[1];
        String passenger[] = flight_save[2].split("=");

        passenger_count = Integer.parseInt(passenger[1]);
        System.out.println("flight no = "+flight_no);
        System.out.println("money = "+amount[i].getText().toString());
        System.out.println("passenger_count"+passenger_count);
        Preferences.saveStringPreferences(getApplicationContext(),"flight_no",flight_no);
        Preferences.saveStringPreferences(getApplicationContext(),"money",amount[i].getText().toString());
        Preferences.saveStringPreferences(getApplicationContext(),"passenger_count",""+passenger_count);
        //Intent code
        logininfo = checkLogin();
        if (logininfo.equals("")){

            Toast.makeText(getApplicationContext(),"Not Logged In do login first",Toast.LENGTH_LONG ).show();
            //Intent code for Login has to be here
            Intent intent = new Intent(getApplicationContext(),Login.class);
            intent.putExtra("action","booking");
            startActivity(intent);
        }else{


            //Intent code for booking and logininfo and flight details has to be passed
            Intent intent = new Intent(getApplicationContext(),FlightBooking.class);
            intent.putExtra("traveller",""+passenger_count);
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

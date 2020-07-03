package com.example.splashscreen;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;

public class FlightBooking extends AppCompatActivity {

    LinearLayout master;
    int traveller_count;
    LinearLayout customer_details[];
    EditText names[];
    Button selectDate[];
    TextView showDate[];
    RadioButton male[];
    RadioButton female[];
    public String date;
    public int z;
    Button confirm;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flight_booking);

        traveller_count = Integer.parseInt(Preferences.getStringPreferences(getApplicationContext(),"passenger_count"));

        master = (LinearLayout) findViewById(R.id.master);

        customer_details = new LinearLayout[traveller_count];
        names = new EditText[traveller_count];
        selectDate = new Button[traveller_count];
        showDate = new TextView[traveller_count];
        male = new RadioButton[traveller_count];
        female =new RadioButton[traveller_count];
        confirm = new Button(getApplicationContext());
        confirm.setText("Confirm");
        confirm.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT));
        confirm.setGravity(Gravity.CENTER_HORIZONTAL);
        //confirm.setLayoutParams(new LinearLayout.LayoutParams());


        //scrollView = new ScrollView(getApplicationContext());
        //scrollView.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT));

        for (int i = 0;i<traveller_count;i++){


            customer_details[i] = new LinearLayout(getApplicationContext());
            customer_details[i].setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,0,Math.round(100/traveller_count)));
            customer_details[i].setWeightSum(100);
            customer_details[i].setOrientation(LinearLayout.VERTICAL);

            names[i] = new EditText(getApplicationContext());
            names[i].setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,0,40));
            names[i].setHint("Enter Name");

            male[i] =new RadioButton(getApplicationContext());
            male[i].setText("Male");
            male[i].setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,0,20));
            male[i].setId(i);
            male[i].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    female[v.getId()].setChecked(false);

                }
            });

            female[i] = new RadioButton(getApplicationContext());
            female[i].setText("Female");
            female[i].setChecked(true);
            female[i].setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,0,20));
            female[i].setId(i);
            female[i].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    male[v.getId()].setChecked(false);

                }
            });

            showDate[i] = new TextView(getApplicationContext());
            showDate[i].setText("Date of birth");
            showDate[i].setGravity(Gravity.CENTER);
            showDate[i].setTextSize(15);
            showDate[i].setId(i);
            showDate[i].setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,0,20));
            showDate[i].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    setDate(v.getId());

                }
            });
            customer_details[i].addView(names[i]);
            customer_details[i].addView(male[i]);
            customer_details[i].addView(female[i]);
            customer_details[i].addView(showDate[i]);

            master.addView(customer_details[i]);

        }
        master.addView(confirm);

        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Toast.makeText(getApplicationContext(),getData(),Toast.LENGTH_LONG).show();
                System.out.println("Data  = "+getData());
                Intent intent = new Intent(getApplicationContext(),FlightPayment.class);
                intent.putExtra("customer_data",getData());
                startActivity(intent);

            }
        });


    }
    public void setDate(int i){

        z = i;
        final Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {

                date = dayOfMonth+"/"+(month+1)+"/"+year;
                showDate[z].setText(date);

            }
        },year,month,day);
        datePickerDialog.show();

    }

    public String getData(){

        String msg="";

        for (int i=0;i<traveller_count;i++){

                msg = msg+""+names[i].getText().toString().toLowerCase();
                if(male[i].isChecked()){

                    msg = msg+"-"+male[i].getText().toString().charAt(0);
                }else{

                    msg = msg+"-"+female[i].getText().toString().charAt(0);

                }

               msg = msg+"-"+ showDate[i].getText().toString();
               msg = msg+"_";
        }

        return msg;
    }

}

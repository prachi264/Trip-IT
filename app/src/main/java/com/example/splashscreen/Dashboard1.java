package com.example.splashscreen;


import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONException;
import org.json.JSONObject;

import java.net.URL;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;



public class Dashboard1 extends AppCompatActivity  {

    public String s_city = "",d_city = "",s_class,date;
    TextView show_date;
    RadioGroup seat_class;
    EditText passenger_count;
    Spinner source_city,destination_city;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard1);

        show_date = (TextView) findViewById(R.id.show_date);

        //seat category radio group code
        seat_class = (RadioGroup)findViewById(R.id.radioGroup);
        seat_class.clearCheck();
        seat_class.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {

                RadioButton radioButton = (RadioButton) group.findViewById(checkedId);
                s_class = radioButton.getText().toString();

            }
        });

        passenger_count = (EditText) findViewById(R.id.passenger_count);

        //code for drop down for selecting city
        source_city = findViewById(R.id.source);
        destination_city = findViewById(R.id.destination);

        ArrayList<String> city_list = new ArrayList<>();
        city_list.add("mumbai");
        city_list.add("pune");
        city_list.add("kolhapur");
        city_list.add("nashik");
        city_list.add("nagpur");
        city_list.add("aurangabad");

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,city_list);
        source_city.setAdapter(arrayAdapter);
        destination_city.setAdapter(arrayAdapter);
        source_city.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                s_city = parent.getItemAtPosition(position).toString();
                Toast.makeText(getApplicationContext(),"source city = "+s_city,Toast.LENGTH_LONG).show();

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        destination_city.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                d_city = parent.getItemAtPosition(position).toString();
                Toast.makeText(getApplicationContext(),"Destination city = "+d_city,Toast.LENGTH_LONG).show();

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    }
    //restrict single selection of radio
    public void disabletwo(View view){

        RadioButton radioButton = (RadioButton)findViewById(R.id.radio_two);
        radioButton.setChecked(false);
        RadioButton radioButton1 = (RadioButton)findViewById(R.id.radio_one);
        s_class = String.valueOf(radioButton1.getText().charAt(0));
        Toast.makeText(getApplicationContext(),""+s_class,Toast.LENGTH_LONG).show();

    }

    public void disableone(View view){

        RadioButton radioButton = (RadioButton)findViewById(R.id.radio_one);
        radioButton.setChecked(false);
        RadioButton radioButton1 = (RadioButton)findViewById(R.id.radio_two);
        s_class = String.valueOf(radioButton1.getText().charAt(0));
        Toast.makeText(getApplicationContext(),""+s_class,Toast.LENGTH_LONG).show();

    }
    //for getting the seat_category
    public void getSeat_category(){

        RadioButton radioButton = (RadioButton) seat_class.findViewById(seat_class.getCheckedRadioButtonId());
        s_class = String.valueOf(radioButton.getText().charAt(0));

    }


    public void dateChoose(View view){

        final Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {

                date = dayOfMonth+"/"+(month+1)+"/"+year;
                show_date.setText(date);

            }
        },year,month,day);
        datePickerDialog.show();
    }

    public String querryString(){

        //getSeat_category();
        String querry = "sample.php?source_city="+s_city+"&destination_city="+d_city+"&date="+date+"&passenger_count="+passenger_count.getText().toString()+"&seat_category="+s_class;
        return querry;
    }

    public void clearAllSelection(){

        seat_class.clearCheck();
        passenger_count.setText("");
        show_date.setText("");

    }

    public void btnClick(View view){

        //setting all the querry string parameter
        Constants.URL_REGISTER = Constants.URL_REGISTER+""+querryString();
        System.out.println(Constants.URL_REGISTER);
        StringRequest stringRequest = new StringRequest(Request.Method.POST,
                Constants.URL_REGISTER,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        //Hide Progress bar
                        //o/p
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            //Toast.makeText(getApplicationContext(),jsonObject.getString("message"),Toast.LENGTH_SHORT).show();
                            //modify if wifi changes


                            if(jsonObject.getString("error").equals("true")){
                                //clearAllSelection();
                                Toast.makeText(getApplicationContext(),jsonObject.getString("message"),Toast.LENGTH_SHORT).show();
                                Constants.URL_REGISTER = "http://192.168.1.105:80/Flight_Serivices/";
                            }else{
                                Constants.URL_REGISTER = "http://192.168.1.105:80/Flight_Serivices/";
                                Intent intent = new Intent(getApplicationContext(),FlightSearch.class);
                                intent.putExtra("flight_details",jsonObject.getString("message"));
                                startActivity(intent);

                            }

                            //passing response to display


                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                        Toast.makeText(getApplicationContext(),error.getMessage(),Toast.LENGTH_SHORT).show();

                    }
                }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> params = new HashMap<>();
                params.put("username","Sksahu");
                return params;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);

    }


}

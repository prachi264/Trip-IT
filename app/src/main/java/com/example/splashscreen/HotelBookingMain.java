package com.example.splashscreen;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Toast;


import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDialogFragment;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.DateFormat;
import java.util.Calendar;
import java.util.Map;


public class HotelBookingMain extends AppCompatActivity {

    public String s_city = "";
    Button button;
    Spinner city_spinner;
    public String selected_city;
    public EditText room,guesst;
    public TextView chk_in,chk_out;
    public  String check_in_date,check_out_date;
    public int guest_cnt,room_cnt;
    public int city_id;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hotel_booking_main);

        city_spinner = findViewById(R.id.city_spinner);
        room = (EditText) findViewById(R.id.room_quantity);
        guesst = (EditText)findViewById(R.id.guesst_count);
        chk_in = (TextView) findViewById(R.id.check_in);
        chk_out = (TextView) findViewById(R.id.check_out);


        //adding city to the spinner
        ArrayList<String> city_list = new ArrayList<>();
        city_list.add("choose city");
        city_list.add("mumbai");
        city_list.add("pune");
        city_list.add("kolhapur");
        city_list.add("nashik");
        city_list.add("nagpur");
        city_list.add("aurangabad");

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,city_list);
        city_spinner.setAdapter(arrayAdapter);

        city_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                selected_city = parent.getItemAtPosition(position).toString();
                Toast.makeText(getApplicationContext(),"city id = "+position,Toast.LENGTH_LONG).show();
                city_id = position;

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });





    }


    public void setCheckInDate(View view){

        final Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {

                check_in_date = dayOfMonth+"/"+(month+1)+"/"+year;
                chk_in.setText(check_in_date);

            }
        },year,month,day);
        datePickerDialog.show();

    }


    public void setCheck_out_date(View view){

        final Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {

                check_out_date = dayOfMonth+"/"+(month+1)+"/"+year;
                chk_out.setText(check_out_date);

            }
        },year,month,day);
        datePickerDialog.show();


    }



    public String querryString(){

        //getSeat_category();
        String querry = "hotelDetails.php?city="+city_id+"&check_in="+check_in_date+"&room_count="+room_cnt;
        return querry;
    }


    public void confirm(View view){

        room_cnt = Integer.parseInt(room.getText().toString());
        guest_cnt = Integer.parseInt(guesst.getText().toString());
        //Toast.makeText(getApplicationContext(),selected_city+""+check_in_date+""+check_out_date+""+room_cnt+""+guest_cnt,Toast.LENGTH_LONG).show();


        int ai_room_cnt = (guest_cnt/3)+((guest_cnt%3)-1);
        if(guest_cnt%3 == 0){

            ai_room_cnt = ai_room_cnt+1;

        }
        Toast.makeText(getApplicationContext(),""+ai_room_cnt,Toast.LENGTH_LONG).show();
        if(ai_room_cnt == room_cnt && guest_cnt >=  room_cnt){

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
                                    Preferences.saveStringPreferences(getApplicationContext(),"check_in",check_in_date);
                                    Preferences.saveStringPreferences(getApplicationContext(),"check_out",check_out_date);
                                    Preferences.saveStringPreferences(getApplicationContext(),"city_id",""+city_id);
                                    Preferences.saveStringPreferences(getApplicationContext(),"city_name",selected_city);
                                    Preferences.saveStringPreferences(getApplicationContext(),"guest_count",""+guest_cnt);
                                    Preferences.saveStringPreferences(getApplicationContext(),"room_count",""+room_cnt);
                                    Intent intent = new Intent(getApplicationContext(),HotelSearch.class);
                                    intent.putExtra("hotel_details",jsonObject.getString("message"));
                                    startActivity(intent);
                                    //Toast.makeText(getApplicationContext(),jsonObject.getString("message"),Toast.LENGTH_SHORT).show();

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
                    return params;
                }
            };
            RequestQueue requestQueue = Volley.newRequestQueue(this);
            requestQueue.add(stringRequest);


        }else {

            Toast.makeText(getApplicationContext(),"3 Max Guests are allowed in a single room\n suggested room count = "+ai_room_cnt,Toast.LENGTH_LONG).show();

        }

        //setting network related code



    }

}
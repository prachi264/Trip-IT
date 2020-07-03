package com.example.splashscreen;


import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.app.Activity;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class Login extends AppCompatActivity {


    EditText username;
    EditText password;
    Button register;
    String userid,pass;
    public String action;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        action = getIntent().getStringExtra("action");




        register = (Button) findViewById(R.id.travel_regi);
        username = (EditText) findViewById(R.id.travel_disp_username);
        password = (EditText) findViewById(R.id.travel_disp_set_password);


    }

    public String querryString(){

        //getSeat_category();
        String querry = "login.php?username="+userid+"&password="+pass;
        return querry;
    }

    public void loginData(View view){

        userid = username.getText().toString();
        pass = password.getText().toString();
        userid = userid.replaceAll("\\s","");
        pass = pass.replaceAll("\\s","");
        //Toast.makeText(getApplicationContext(),"button clicked",Toast.LENGTH_LONG).show();
        System.out.println("username = "+userid);
        System.out.println("password = "+pass);


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
                            System.out.println("Status = "+jsonObject.getString("error").toString());
                            if(jsonObject.getString("error").equals("true")){
                                //clearAllSelection();
                                System.out.println("user="+jsonObject.getString("user").toString());
                                Toast.makeText(getApplicationContext(),jsonObject.getString("message"),Toast.LENGTH_SHORT).show();
                                Constants.URL_REGISTER = "http://192.168.1.105:80/Flight_Serivices/";
                            }else{
                                Constants.URL_REGISTER = "http://192.168.1.105:80/Flight_Serivices/";
                                //Intent intent = new Intent(getApplicationContext(),FlightSearch.class);
                                //intent.putExtra("flight_details",jsonObject.getString("message"));
                                //startActivity(intent);
                                //Toast.makeText(getApplicationContext(),jsonObject.getString("message"),Toast.LENGTH_LONG).show();
                                String output[] = jsonObject.getString("message").split(":");
                                Preferences.saveStringPreferences(getApplicationContext(),"username",userid);
                                Preferences.saveStringPreferences(getApplicationContext(),"password",pass);
                                Preferences.saveStringPreferences(getApplicationContext(),"c_id",output[1]);
                                String op = Preferences.getStringPreferences(getApplicationContext(),"c_id")+" "+Preferences.getStringPreferences(getApplicationContext(),"username")+" "+Preferences.getStringPreferences(getApplicationContext(),"password");
                                Toast.makeText(getApplicationContext(),op,Toast.LENGTH_LONG).show();

                                if(action.equals("booking")){

                                    Intent intent = new Intent(getApplicationContext(),FlightBooking.class);
                                    startActivity(intent);

                                }else if(action.equals("hotel")){

                                    Intent intent = new Intent(getApplicationContext(),Hotel_Booking.class);
                                    startActivity(intent);

                                }
                                else {

                                    Intent intent = new Intent(getApplicationContext(),Dashboard_Main.class);
                                    startActivity(intent);

                                }

                                //check for if it is redirected fro the booking page



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

    }

}
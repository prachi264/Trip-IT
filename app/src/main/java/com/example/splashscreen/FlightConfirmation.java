package com.example.splashscreen;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
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
import org.w3c.dom.Text;

import java.util.HashMap;
import java.util.Map;

public class FlightConfirmation extends AppCompatActivity {

    TextView username;

    public String name[];
    public String pnr;
    public String source,destination;
    public String flight_id;
    public String amount;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flight_confirmation);

        flight_id = Preferences.getStringPreferences(getApplicationContext(),"flight_no");
        amount = Preferences.getStringPreferences(getApplicationContext(),"money");
        String customer_data[] = Preferences.getStringPreferences(getApplicationContext(),"user_data").split("_");

        name = new String[customer_data.length];

        for (int i = 0;i<customer_data.length;i++){

            String data[] =customer_data[i].split("-");
            name[i] = data[0];

        }

        getPnr(name[0]);
        getSourceDestination(flight_id);

        Toast.makeText(getApplicationContext(),pnr+"\n"+source+"-"+destination+"\n"+amount,Toast.LENGTH_LONG).show();

    }


    public void getPnr(String name){

        Constants.URL_REGISTER = Constants.URL_REGISTER+"getPnr.php?cust_name="+name;
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
                                pnr = jsonObject.getString("message");

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


    public void getSourceDestination(String flight_id){

        Constants.URL_REGISTER = "http://192.168.1.105:80/Flight_Serivices/";
        Constants.URL_REGISTER = Constants.URL_REGISTER+"getSourceDestination.php?flight_id="+flight_id;
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
                                String citysplit[] = jsonObject.getString("message").split(" ");
                                source = citysplit[0];
                                destination = citysplit[1];
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

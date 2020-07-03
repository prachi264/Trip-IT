package com.example.splashscreen;


import android.app.Activity;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
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
import java.util.regex.Pattern;


public class Registration extends AppCompatActivity {

    public EditText customer_name;
    public EditText customer_address;
    public EditText email;
    public EditText gender;
    public EditText age;
    public EditText mobile_no;
    public EditText set_username;
    public EditText set_password;
   public Button Sign_Up;
   public String cust_name,cust_mno,cust_email,cust_gender,cust_age,cust_address,cust_username,cust_password;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        Sign_Up = (Button) findViewById(R.id.Sign_Up);
        customer_name = (EditText) findViewById(R.id.travel_disp_cust_name);
        mobile_no = (EditText) findViewById(R.id.travel_disp_cust_mno);
        email = (EditText) findViewById(R.id.travel_disp_cust_email);
        gender = (EditText) findViewById(R.id.travel_disp_cust_gender);
        age = (EditText) findViewById(R.id.travel_disp_cust_age);
        customer_address = (EditText) findViewById(R.id.travel_disp_cust_addr);
        set_username = (EditText) findViewById(R.id.travel_disp_username);
        set_password = (EditText) findViewById(R.id.travel_disp_set_password);



    }


    public String querryString(){

        //getSeat_category();
        String querry = "registration.php?cname="+cust_name+"&cmno="+cust_mno+"&cemail="+cust_email+"&cgender="+cust_gender+"&cdob="+cust_age+"&caddr="+cust_address+"&cuserid="+cust_username+"&cpwd="+cust_password;
        return querry;
    }

    public void getRegistration(View view){

        cust_name = customer_name.getText().toString();
        cust_mno = mobile_no.getText().toString();
        cust_email = email.getText().toString();
        char g = gender.getText().toString().toUpperCase().charAt(0);
        cust_gender =  String.valueOf(g);
        cust_age = age.getText().toString();
        cust_address = customer_address.getText().toString();
        cust_username = set_username.getText().toString();
        cust_password = set_password.getText().toString();

        //Network code

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
                                Intent intent = new Intent(getApplicationContext(),Dashboard_Main.class);
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






package com.example.splashscreen;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
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


public class FlightPayment extends AppCompatActivity {
    EditText cardholdername, number, cvv, expiry, email;
    Button submit;
    TextView amount;
    public  int error_code=0;
    public String Name,word,cvv_no,exp_date;
    public String customer_data;
    public String username,flight_id,doj,passenger_count;
    String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flight_payment);
        customer_data = getIntent().getStringExtra("customer_data");
        System.out.println(customer_data);
        cardholdername = (EditText) findViewById(R.id.cardholdername);
        number = (EditText) findViewById(R.id.number);
        cvv = (EditText) findViewById(R.id.cvv);
        expiry = (EditText) findViewById(R.id.expiry);
        amount = (TextView) findViewById(R.id.amount);
        amount.setText(Preferences.getStringPreferences(getApplicationContext(),"money"));
        username = Preferences.getStringPreferences(getApplicationContext(),"username");
        flight_id = Preferences.getStringPreferences(getApplicationContext(),"flight_no");
        passenger_count = Preferences.getStringPreferences(getApplicationContext(),"passenger_count");
        // email = (EditText) findViewById(R.id.email);
        submit = (Button) findViewById(R.id.submit);

    }

    public void onSubmit(View view){

        Name = cardholdername.getText().toString();
        word = number.getText().toString();
        cvv_no = cvv.getText().toString();
        exp_date = expiry.getText().toString();


        if (Name.length() == 0) {

            error_code = 1;
            cardholdername.requestFocus();
            cardholdername.setError("FIELD CANNOT BE EMPTY");
        }
        if (!Name.matches("[a-zA-Z ]+")) {

            error_code = 1;
            cardholdername.requestFocus();
            cardholdername.setError("ENTER ONLY ALPHABETICAL CHARACTER");
        }
        if (word.length() == 0) {

            error_code = 1;
            number.requestFocus();
            number.setError("FIELD CANNOT BE EMPTY");
        }
        if (cvv_no.length() != 3){

            error_code = 1;
            cvv.requestFocus();
            cvv.setError("Cvv no has be 3 digit");

        }
        if (exp_date.equals("")){

            error_code = 1;
            expiry.requestFocus();
            expiry.setError("Expiry needed");

        }else{

            String exp[] = exp_date.split("/");
            if (exp.length != 2){

                error_code = 1;
                expiry.requestFocus();
                expiry.setError("Month and year both needed");


            }else {

                if (exp[0].length()!=2 && exp[1].length()!=2){

                    error_code = 1;
                    expiry.requestFocus();
                    expiry.setError("Accepted Format is = MM/YY");


                }

            }

        }


        if (error_code == 0){

            Toast.makeText(getApplicationContext(),"Succefully validated",Toast.LENGTH_SHORT).show();

            doBooking();

        }


    }



    public String querryString(){

        //getSeat_category();
        String querry = "booking.php?username="+username+"&flight_id="+flight_id+"&passenger_count="+passenger_count+"&customer_data="+customer_data+"&price="+amount.getText().toString();
        return querry;
    }


    public void doBooking(){

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
                                Preferences.saveStringPreferences(getApplicationContext(),"user_data",customer_data);
                                Intent intent = new Intent(getApplicationContext(),FlightConfirmation.class);
                                startActivity(intent);
                                Toast.makeText(getApplicationContext(),jsonObject.getString("message"),Toast.LENGTH_LONG).show();

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


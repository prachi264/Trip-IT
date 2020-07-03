package com.example.splashscreen;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
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

public class Hotel_Booking extends AppCompatActivity {

    TextView hotelname,city,guest_cnt,room_cnt,check_in,check_out;
    String price;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hotel__booking);


        hotelname = (TextView) findViewById(R.id.htl_disp_cust_name);
        city = (TextView) findViewById(R.id.htl_disp_cust_mno);
        guest_cnt = (TextView) findViewById(R.id.htl_disp_cust_gender);
        room_cnt = (TextView) findViewById(R.id.htl_disp_checkin);
        check_in = (TextView)findViewById(R.id.htl_disp_checkout);
        check_out = (TextView)findViewById(R.id.htl_disp_hotelname);


        hotelname.setText(Preferences.getStringPreferences(getApplicationContext(),"hotel_name").toUpperCase());
        city.setText(Preferences.getStringPreferences(getApplicationContext(),"city_name").toUpperCase());
        guest_cnt.setText(Preferences.getStringPreferences(getApplicationContext(),"guest_count"));
        room_cnt.setText(Preferences.getStringPreferences(getApplicationContext(),"room_count"));
        check_out.setText(Preferences.getStringPreferences(getApplicationContext(),"check_out"));
        check_in.setText(Preferences.getStringPreferences(getApplicationContext(),"check_in"));

        price = Preferences.getStringPreferences(getApplicationContext(),"hotel_price");




    }

    public String querryString(){

        //getSeat_category();
        String querry = "hotelBooking.php?city_id="+Preferences.getStringPreferences(getApplicationContext(),"city_id")+"&check_out="+check_out.getText()+"&check_in="+check_in.getText()+"&guest="+guest_cnt.getText().toString()+"&room="+room_cnt.getText()+"&price="+price+"&username="+Preferences.getStringPreferences(getApplicationContext(),"username")+"&hotel_name="+hotelname.getText();
        return querry;
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
                                Toast.makeText(getApplicationContext(),jsonObject.getString("booking_id"),Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(getApplicationContext(),Hotel_Success.class);
                                intent.putExtra("booking_id",jsonObject.getString("booking_id"));
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
                return params;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);

    }

}

package com.example.splashscreen;


import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;

public class pack_mainpage extends AppCompatActivity {

    Button btn;
    EditText editText;
    CheckBox checkBox20,checkBox21;
    RadioButton radioButton,radioButton2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pack_mainpage);

        btn= (Button)findViewById(R.id.bookCar);
        editText = (EditText) findViewById(R.id.editText);
        checkBox20 = (CheckBox) findViewById(R.id.checkBox20);
        checkBox21 = (CheckBox) findViewById(R.id.checkBox21);
        radioButton = (RadioButton) findViewById(R.id.radioButton);
        radioButton2 = (RadioButton) findViewById(R.id.radioButton2);


        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int service =0;
                int tourguide = 0;
                Preferences.saveStringPreferences(getApplicationContext(),"package_passanger",editText.getText().toString());
                if(checkBox20.isChecked()){

                    service=0;

                }else{

                    service=1;

                }

                if(radioButton.isChecked()){

                    tourguide=1;

                }else{

                    tourguide=0;
                }

                Preferences.saveStringPreferences(getApplicationContext(),"package_service",""+service);
                Preferences.saveStringPreferences(getApplicationContext(),"package_tourguide",""+tourguide);

                movetoPassenger();
            }

            private void movetoPassenger()
            {
                Intent intent = new Intent(pack_mainpage.this, pack_dashboard.class);
                startActivity(intent);
            }
        });
    }
}

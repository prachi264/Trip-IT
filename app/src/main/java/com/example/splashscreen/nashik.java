package com.example.splashscreen;


import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Toast;

public class nashik extends AppCompatActivity {
    Button btn;

    CheckBox chk1,chk2;
    public int cnt=0;
    public int fare=0;
    CheckBox chk[];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nashik);


        chk = new CheckBox[18];

        for(int i = 0;i<18;i++){

            chk[i] = new CheckBox(getApplicationContext());

        }

        chk[1]=(CheckBox) findViewById(R.id.chk1);
        chk[2]=(CheckBox) findViewById(R.id.chk2);
        chk[3]=(CheckBox) findViewById(R.id.chk3);
        chk[4]=(CheckBox) findViewById(R.id.chk4);
        chk[5]=(CheckBox) findViewById(R.id.chk5);
        chk[6]=(CheckBox) findViewById(R.id.chk6);
        chk[7]=(CheckBox) findViewById(R.id.chk7);
        chk[8]=(CheckBox) findViewById(R.id.chk8);
        chk[9]=(CheckBox) findViewById(R.id.chk9);
        chk[10]=(CheckBox) findViewById(R.id.chk10);
        chk[11]=(CheckBox) findViewById(R.id.chk11);
        chk[12]=(CheckBox) findViewById(R.id.chk12);

        for (int i=1;i<13;i++){



            //chk[i]= (CheckBox) findViewById(R.id.chk[i]);
            chk[i].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    cnt = cnt+1;
                }
            });

        }



    }

    public void getFare(View view){

        fare = cnt*400;


        System.out.println("Fare = "+cnt);
        Toast.makeText(getApplicationContext(),"Fare = "+fare,Toast.LENGTH_LONG).show();

        Intent intent = new Intent(getApplicationContext(),tkykop.class);
        intent.putExtra("cityname","Nashik:"+fare);
        startActivity(intent);





    }
}

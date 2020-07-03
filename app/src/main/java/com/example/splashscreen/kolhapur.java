package com.example.splashscreen;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.Toast;


public class kolhapur extends AppCompatActivity {
    Button btn;


    CheckBox chk1,chk2;
    public int cnt=0;
    public int fare=0;
    CheckBox chk[];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kolhapur);

        //checkbox initilization
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
        chk[13]=(CheckBox) findViewById(R.id.chk13);
        chk[14]=(CheckBox) findViewById(R.id.chk14);
        chk[15]=(CheckBox) findViewById(R.id.chk15);
        chk[16]=(CheckBox) findViewById(R.id.chk16);



        for (int i=1;i<17;i++){



            //chk[i]= (CheckBox) findViewById(R.id.chk[i]);
            chk[i].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    cnt = cnt+1;
                }
            });

        }




        //price calculating



        //Intent intent = new Intent(getApplicationContext(),);
        //intent.putExtra("fare",fare);
        //startActivity(intent);

    }

    public void getFare(View view){

        if(cnt == 1){

            fare =400;

        }else if(cnt < 5 && cnt > 1){

            fare = 850;
        }else if(cnt < 10 && cnt > 4){

            fare = 1950;

        }else if(cnt == 0){

            fare = 0;

        }
        else{

            fare = 2850;

        }


        System.out.println("Fare = "+fare);
        Toast.makeText(getApplicationContext(),"Fare = "+fare, Toast.LENGTH_LONG).show();


        //Intent code

        Intent intent = new Intent(getApplicationContext(),tkykop.class);
        intent.putExtra("cityname","Kolhapur:"+fare);
        startActivity(intent);

    }

}

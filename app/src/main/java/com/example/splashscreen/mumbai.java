package com.example.splashscreen;


import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Toast;


public class mumbai extends AppCompatActivity {
    Button btn;

    CheckBox chk1,chk2;
    public int cnt=0;
    public int fare=0;
    public CheckBox chk[];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mumbai);


        //code
        chk = new CheckBox[20];


        for(int i = 0;i<20;i++){

            chk[i] = new CheckBox(getApplicationContext());

        }

        chk[1]=(CheckBox) findViewById(R.id.chk21);
        chk[2]=(CheckBox) findViewById(R.id.chk22);
        chk[3]=(CheckBox) findViewById(R.id.chk23);
        chk[4]=(CheckBox) findViewById(R.id.chk24);
        chk[5]=(CheckBox) findViewById(R.id.chk25);
        chk[6]=(CheckBox) findViewById(R.id.chk26);
        chk[7]=(CheckBox) findViewById(R.id.chk27);
        chk[8]=(CheckBox) findViewById(R.id.chk28);
        chk[9]=(CheckBox) findViewById(R.id.chk29);
        chk[10]=(CheckBox) findViewById(R.id.chk30);
        chk[11]=(CheckBox) findViewById(R.id.chk31);
        chk[12]=(CheckBox) findViewById(R.id.chk32);
        chk[13]=(CheckBox) findViewById(R.id.chk33);
        chk[14]=(CheckBox) findViewById(R.id.chk34);
        chk[15]=(CheckBox) findViewById(R.id.chk35);
        chk[16]=(CheckBox) findViewById(R.id.chk36);
        chk[17]=(CheckBox) findViewById(R.id.chk37);
        chk[18]=(CheckBox) findViewById(R.id.chk38);


        for (int i=1;i<19;i++){

            System.out.println("Value = "+i);
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

        if(cnt == 1){

            fare =400;

        }else if(cnt < 5 && cnt > 1){

            fare = 850;
        }else if(cnt < 10 && cnt > 4){

            fare = 1950;

        }else if(cnt == 0){

            fare = 0;

        }else{

            fare = 2850;

        }


        System.out.println("Fare = "+fare);
        Toast.makeText(getApplicationContext(),"Fare = "+fare,Toast.LENGTH_LONG).show();

        Intent intent = new Intent(getApplicationContext(),tkykop.class);
        intent.putExtra("cityname","Mumbai:"+fare);
        startActivity(intent);



    }



}

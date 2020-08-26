package com.example.healthystep2;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;


public class WorkOut extends AppCompatActivity {

    ImageView homeButton;

    DataBaseHelper dataBaseHelper;

    int weight;
    double height;
    double BMI;

    double maxBMI = 24.9;
    double minBMI = 18.5;

    TextView workout_message;

    ImageView gymimage,trximage,homeimage;
    TextView gymtxt,trxtxt,hometxt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_work_out);

        //this button is to take us to profile page as a main page
        homeButton = (ImageView) findViewById(R.id.homebuttonforworkout);
        homeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(WorkOut.this,Profile.class);
                startActivity(intent);
            }
        });

        dataBaseHelper = new DataBaseHelper(this);

        Cursor cursor = dataBaseHelper.getData();

        weight = Integer.parseInt(cursor.getString(4));
        height = (Integer.parseInt(cursor.getString(5))) / 100.0;

        //calculating body mass index
        BMI = weight / height;
        BMI = BMI / height;
        //end of calculating body mass index

        workout_message = (TextView) findViewById(R.id.workout_txt);

        String normal = "Your Current Statue s 'Normal weight' Which Means You Have To Work On Saving That Body Weight, The Following Plans Will Help You To Save Your Current Statue.";
        String over = "Your Current Statue is 'Over Weight' Which Means You Have To Work On Losing Weight To Get To The 'Normal Weight', The Following Plans Will Help You To Lose Weight.";
        String under = "Your Current Statue is 'Under Weight' Which Means You Have To Work On Gaining Fat And Muscle To Get To A 'Normal Weight', The Following Plans Will Help You Gain Weight.";

        //the user will get a message depending on the BMI statue
        if (BMI > maxBMI)
            workout_message.setText(over);
        else if (BMI < minBMI)
            workout_message.setText(under);
        else if (BMI > minBMI && BMI < maxBMI)
            workout_message.setText(normal);

        gymimage = (ImageView) findViewById(R.id.imageforgym);
        trximage = (ImageView) findViewById(R.id.imagefortrx);
        homeimage = (ImageView) findViewById(R.id.imageforhome);
        gymtxt = (TextView) findViewById(R.id.txtforgym);
        trxtxt = (TextView) findViewById(R.id.txtfortrx);
        hometxt = (TextView) findViewById(R.id.txtforhome);

        gymimage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(WorkOut.this,Gym.class);
                startActivity(intent);
            }
        });

        gymtxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(WorkOut.this,Gym.class);
                startActivity(intent);
            }
        });

        trximage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(WorkOut.this,Trx.class);
                startActivity(intent);
            }
        });

        trxtxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(WorkOut.this,Trx.class);
                startActivity(intent);
            }
        });

        homeimage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(WorkOut.this,Home.class);
                startActivity(intent);
            }
        });

        hometxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(WorkOut.this,Home.class);
                startActivity(intent);
            }
        });





    }
}

package com.example.healthystep2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class OptionPage extends AppCompatActivity {

    Button randomcalc;
    Button workout;
    Button diet;

    ImageView homeButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_option_page);

        homeButton = (ImageView) findViewById(R.id.homebuttonforoptionpage);
        homeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(OptionPage.this,Profile.class);
                startActivity(intent);
            }
        });

        randomcalc = (Button) findViewById(R.id.randomcalc_button);
        workout = (Button) findViewById(R.id.workout_button);
        diet = (Button) findViewById(R.id.diet_button);

        randomcalc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(OptionPage.this,RandomCalculator.class);
                startActivity(intent);
            }
        });

        workout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(OptionPage.this,WorkOut.class);
                startActivity(intent);
            }
        });

        diet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(OptionPage.this,DietHome.class);
                startActivity(intent);
            }
        });
    }
}

package com.example.healthystep2;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class GymExercises extends AppCompatActivity {

    int[] images = {R.drawable.chestpress, R.drawable.flychest, R.drawable.squat, R.drawable.deadlift, R.drawable.back, R.drawable.shoulderpress, R.drawable.abs};

    ImageView gobackbutton;
    ImageView imageforexercise;

    TextView txt;

    DataBaseHelper dataBaseHelper;

    //======all these to calculate BMI=====
    int weight;
    double height;
    double BMI;
    double maxBMI = 24.9;
    double minBMI = 18.5;
    //=====================================

    //flag to save the BMI over, under or Normal
    String flag;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gym_exercises);

        //this button is to take us back to gym class
        gobackbutton = (ImageView) findViewById(R.id.backtogymlist);
        gobackbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(GymExercises.this, Gym.class);
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

        txt = (TextView) findViewById(R.id.gymextxt);
        //=======================
        Intent data = getIntent();
        final int index = data.getExtras().getInt("exerciseforgym");

        imageforexercise = (ImageView) findViewById(R.id.imgexercise_txt);
        imageforexercise.setImageResource(images[index]);
        //=======================


        if (BMI > maxBMI)
            flag = "over";
        else if (BMI < minBMI)
            flag = "under";
        else if (BMI > minBMI && BMI < maxBMI)
            flag = "normal";

        if (flag == "over") {
            switch (index) {
                case 0:
                    txt.setText("Chest Press\n\nsets: 3\nrepeat: 15\nlow weight\n4th set can be 4-6 repeats with high weight(unnecessary)");
                    break;

                case 1:
                    txt.setText("Fly Chest\n\nsets: 3-4\nrepeat: 10-12\nlow weight\n1 minute rest between each set");
                    break;

                case 2:
                    txt.setText("Squat\n\nsets: 3\nrepeat: 15-20\nlow weight");
                    break;

                case 3:
                    txt.setText("Dead Lift\n\nsets: 4\nrepeat: 10-12\nlow weight\n1 minute rest between each set");
                    break;

                case 4:
                    txt.setText("Back\n\nsets: 3\nrepeat: 15\nlow-medium weight\n4th set can be 4-6 repeats with high weight(unnecessary)");
                    break;

                case 5:
                    txt.setText("Shoulder Press\n\nsets: 3\nrepeat: 10-12\nmedium-high weight\n");
                    break;

                case 6:
                    txt.setText("ABS\n\nsets: 4\nrepeat: 20\nlow-medium-high weight");
                    break;

                default:
                    break;

            }
        }

        if (flag == "under") {
            switch (index) {
                case 0:
                    txt.setText("Chest Press\n\nsets: 3\nrepeat: 8-12\nmedium-high weight\n4th set can be 4-6 repeats with high weight(unnecessary)");
                    break;
                case 1:
                    txt.setText("Fly Chest\n\nsets: 3-4\nrepeat: 10-12\nmedium weight\n1 minute rest between each set");
                    break;
                case 2:
                    txt.setText("Squat\n\nsets: 3\nrepeat: 10-12\nmedium weight");
                    break;
                case 3:
                    txt.setText("Dead Lift\n\nsets: 3\nrepeat: 8-10\nmedium-high weight\n1 minute rest between each set");
                    break;
                case 4:
                    txt.setText("Back\n\nsets: 3\nrepeat: 12\nmedium-high weight\n4th set can be 4-6 repeats with high weight(unnecessary)");
                    break;
                case 5:
                    txt.setText("Shoulder Press\n\nsets: 3\nrepeat: 10-12\nmedium-high weight\n");
                    break;
                case 6:
                    txt.setText("ABS\n\nsets: 4\nrepeat: 20\nlow-medium-high weight");
                    break;

                default:
                    break;
            }
        }

        if (flag == "normal") {
            switch (index) {
                case 0:
                    txt.setText("Chest Press\n\nsets: 3\nrepeat: 10\nmedium-high weight\n4th set can be 4-6 repeats with high weight(unnecessary)");
                    break;
                case 1:
                    txt.setText("Fly Chest\n\nsets: 3\nrepeat: 10\nmedium weight\n1 minute rest between each set");
                    break;
                case 2:
                    txt.setText("Squat\n\nsets: 3\nrepeat: 15\nmedium weight");
                    break;
                case 3:
                    txt.setText("Dead Lift\n\nsets: 3\nrepeat: 8-12\nmedium weight\n1 minute rest between each set");
                    break;
                case 4:
                    txt.setText("Back\n\nsets: 3\nrepeat: 12\nmedium weight\n4th set can be 4-6 repeats with high weight(unnecessary)");
                    break;
                case 5:
                    txt.setText("Shoulder Press\n\nsets: 3\nrepeat: 10-12\nmedium-high weight\n");
                    break;
                case 6:
                    txt.setText("ABS\n\nsets: 4\nrepeat: 20\nmedium weight");
                    break;

                default:
                    break;
            }
        }

    }

}

package com.example.healthystep2;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class HomeExercises extends AppCompatActivity {

    int[] images = {R.drawable.homepushup, R.drawable.homedeclinepushup, R.drawable.homediamonpushup, R.drawable.homepullups, R.drawable.homechairdips, R.drawable.homesquat, R.drawable.homelunge, R.drawable.homesitups, R.drawable.hometoetoucher};

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
        setContentView(R.layout.activity_home_exercises);

        //this button is to take us back to gym class
        gobackbutton = (ImageView) findViewById(R.id.backtohomelist);
        gobackbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeExercises.this, Home.class);
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

        txt = (TextView) findViewById(R.id.homeextxt);
        //=======================
        Intent data = getIntent();
        int index = data.getExtras().getInt("exerciseforhome");

        imageforexercise = (ImageView) findViewById(R.id.imgexercise_txt_forhome);
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
                    txt.setText("HOME Push Up\n\nTargets: chest,shoulders,arms\nSets: 3\nRepeat: 15-20\nDifficulty: beginner");
                    break;
                case 1:
                    txt.setText("HOME Decline Push Up\n\nTargets: chest,arms,abs\nSets: 3\nRepeat: 12-15\nDifficulty: beginner");
                    break;
                case 2:
                    txt.setText("HOME Diamond Push Up\n\nTargets: arms,biceps,chest\nSets: 3\nRepeat: 15-20\nDifficulty: advanced");
                    break;
                case 3:
                    txt.setText("HOME Pull Up\n\nTargets: back,shoulders\nSets: 4\nRepeat: 15-20\nlDifficulty: beginner");
                    break;
                case 4:
                    txt.setText("HOME Chair Dip\n\nTargets: arms,shoulders\nSets: 3\nRepeat: 15\nDifficulty: beginner)");
                    break;
                case 5:
                    txt.setText("HOME Squat\n\nTargets: quads,hamstrings,legs\nSets: 3\nRepeat: 10-20\nDifficulty: beginner");
                    break;
                case 6:
                    txt.setText("HOME Lunge\n\nTargets: legs,abs\nSets: 4\nRepeat: 15\nDifficulty: Intermediate");
                    break;
                case 7:
                    txt.setText("HOME Sit Up\n\nTargets: abs\nSets: 3\nRepeat: 20\nDifficulty: beginner");
                    break;
                case 8:
                    txt.setText("HOME Toe Touches\n\nTargets: abs,Obliques\nSets: 3\nRepeat: 10-20\nDifficulty: Intermediate");
                    break;

                default:
                    break;
            }
        }

        if (flag == "under") {
            switch (index) {
                case 0:
                    txt.setText("HOME Push Up\n\nTargets: chest,shoulders,arms\nSets: 3\nRepeat: 8-12\nDifficulty: beginner");
                    break;
                case 1:
                    txt.setText("HOME Decline Push Up\n\nTargets: chest,arms,abs\nSets: 4\nRepeat: 10\nDifficulty: beginner");
                    break;
                case 2:
                    txt.setText("HOME Diamond Push Up\n\nTargets: arms,biceps,chest\nSets: 3\nRepeat: 10-12\nDifficulty: advanced");
                    break;
                case 3:
                    txt.setText("HOME Pull Up\n\nTargets: back,shoulders\nSets: 4\nRepeat: 12-15\nlDifficulty: beginner");
                    break;
                case 4:
                    txt.setText("HOME Chair Dip\n\nTargets: arms,shoulders\nSets: 3\nRepeat: 15\nDifficulty: beginner)");
                    break;
                case 5:
                    txt.setText("HOME Squat\n\nTargets: quads,hamstrings,legs\nSets: 3\nRepeat: 15-20\nDifficulty: beginner");
                    break;
                case 6:
                    txt.setText("HOME Lunge\n\nTargets: legs,abs\nSets: 3\nRepeat: 10-12\nDifficulty: Intermediate");
                    break;
                case 7:
                    txt.setText("HOME Sit Up\n\nTargets: abs\nSets: 3\nRepeat: 15\nDifficulty: beginner");
                    break;
                case 8:
                    txt.setText("HOME Toe Touches\n\nTargets: abs,Obliques\nSets: 3\nRepeat: 10-20\nDifficulty: Intermediate");
                    break;

                default:
                    break;
            }
        }

        if (flag == "normal") {
            switch (index) {
                case 0:
                    txt.setText("HOME Push Up\n\nTargets: chest,shoulders,arms\nSets: 3\nRepeat: 20\nDifficulty: beginner");
                    break;
                case 1:
                    txt.setText("HOME Decline Push Up\n\nTargets: chest,arms,abs\nSets: 4\nRepeat: 12\nDifficulty: beginner");
                    break;
                case 2:
                    txt.setText("HOME Diamond Push Up\n\nTargets: arms,biceps,chest\nSets: 3\nRepeat: 15-20\nDifficulty: advanced");
                    break;
                case 3:
                    txt.setText("HOME Pull Up\n\nTargets: back,shoulders\nSets: 3\nRepeat: 20\nlDifficulty: beginner");
                    break;
                case 4:
                    txt.setText("HOME Chair Dip\n\nTargets: arms,shoulders\nSets: 3\nRepeat: 15\nDifficulty: beginner)");
                    break;
                case 5:
                    txt.setText("HOME Squat\n\nTargets: quads,hamstrings,legs\nSets: 3\nRepeat: 20\nDifficulty: beginner");
                    break;
                case 6:
                    txt.setText("HOME Lunge\n\nTargets: legs,abs\nSets: 3\nRepeat: 20\nDifficulty: Intermediate");
                    break;
                case 7:
                    txt.setText("HOME Sit Up\n\nTargets: abs\nSets: 4\nRepeat: 20\nDifficulty: beginner");
                    break;
                case 8:
                    txt.setText("HOME Toe Touches\n\nTargets: abs,Obliques\nSets: 3\nRepeat: 15\nDifficulty: Intermediate");
                    break;

                default:
                    break;
            }
        }
    }
}

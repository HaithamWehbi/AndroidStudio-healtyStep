package com.example.healthystep2;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class TrxExercises extends AppCompatActivity {

    int[] images = {R.drawable.trxpushup, R.drawable.trxchestpress, R.drawable.trxlowrow, R.drawable.trxalligator, R.drawable.trxbicepcurl, R.drawable.trxsquat, R.drawable.trxlunge ,R.drawable.trxsideplank, R.drawable.trxatomicpike};

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
        setContentView(R.layout.activity_trx_exercises);

        //this button is to take us back to gym class
        gobackbutton = (ImageView) findViewById(R.id.backtotrxlist);
        gobackbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(TrxExercises.this, Trx.class);
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

        txt = (TextView) findViewById(R.id.trxextxt);
        //=======================
        Intent data = getIntent();
        int index = data.getExtras().getInt("exercisefortrx");

        imageforexercise = (ImageView) findViewById(R.id.imgexercise_txt_fortrx);
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
                    txt.setText("TRX Push Up\n\nTargets: chest,shoulders,arms\nSets: 3-4\nRepeat: 15-20\nDifficulty: beginner");
                    break;
                case 1:
                    txt.setText("TRX Chest Press\n\nTargets: chest,arms\nSets: 3\nRepeat: 10-15\nDifficulty: beginner");
                    break;
                case 2:
                    txt.setText("TRX Low Row\n\nTargets: back,biceps,shoulders,abs\nSets: 3\nRepeat: 15-20\nDifficulty: beginner");
                    break;
                case 3:
                    txt.setText("TRX Alligator\n\nTargets: shoulders,back,obliques\nSets: 4\nRepeat: 10-12\nlDifficulty: Intermediate");
                    break;
                case 4:
                    txt.setText("TRX Biceps Curl\n\nTargets: arms,abs\nSets: 3\nRepeat: 15\nDifficulty: Intermediate)");
                    break;
                case 5:
                    txt.setText("TRX Squat\n\nTargets: quads,glutes,hamstrings\nSets: 3\nRepeat: 10-12\nDifficulty: beginner");
                    break;
                case 6:
                    txt.setText("TRX Lunge\n\nTargets: legs,abs\nSets: 4\nRepeat: 20\nDifficulty: Intermediate");
                    break;
                case 7:
                    txt.setText("TRX Side Plank\n\nTargets: Obliques\nSets: 3\nRepeat: 15\nDifficulty: beginner");
                    break;
                case 8:
                    txt.setText("TRX Atomic Pike\n\nTargets: abs,shoulders\nSets: 3\nRepeat: 10-15\nDifficulty: Intermediate");
                    break;


                default:
                    break;
            }
        }

        if (flag == "under") {
            switch (index) {

                case 0:
                    txt.setText("TRX Push Up\n\nTargets: chest,shoulders,arms\nSets: 3\nRepeat: 15-20\nDifficulty: beginner");
                    break;
                case 1:
                    txt.setText("TRX Chest Press\n\nTargets: chest,arms\nSets: 3\nRepeat: 8-12\nDifficulty: beginner");
                    break;
                case 2:
                    txt.setText("TRX Low Row\n\nTargets: back,biceps,shoulders,abs\nSets: 4\nRepeat: 10-12\nDifficulty: beginner");
                    break;
                case 3:
                    txt.setText("TRX Alligator\n\nTargets: shoulders,back,obliques\nSets: 3\nRepeat: 10\nlDifficulty: Intermediate");
                    break;
                case 4:
                    txt.setText("TRX Biceps Curl\n\nTargets: arms,abs\nSets: 3\nRepeat: 12\nDifficulty: Intermediate)");
                    break;
                case 5:
                    txt.setText("TRX Squat\n\nTargets: quads,glutes,hamstrings\nSets: 3\nRepeat: 10-15\nDifficulty: beginner");
                    break;
                case 6:
                    txt.setText("TRX Lunge\n\nTargets: legs,abs\nSets: 4\nRepeat: 8-12\nDifficulty: Intermediate");
                    break;
                case 7:
                    txt.setText("TRX Side Plank\n\nTargets: Obliques\nSets: 3\nRepeat: 10\nDifficulty: beginner");
                    break;
                case 8:
                    txt.setText("TRX Atomic Pike\n\nTargets: abs,shoulders\nSets: 3\nRepeat: 10-12\nDifficulty: Intermediate");
                    break;

                default:
                    break;
            }
        }

        if (flag == "normal") {
            switch (index) {
                case 0:
                    txt.setText("TRX Push Up\n\nTargets: chest,shoulders,arms\nSets: 4\nRepeat: 15\nDifficulty: beginner");
                    break;
                case 1:
                    txt.setText("TRX Chest Press\n\nTargets: chest,arms\nSets: 3\nRepeat: 15\nDifficulty: beginner");
                    break;
                case 2:
                    txt.setText("TRX Low Row\n\nTargets: back,biceps,shoulders,abs\nSets: 3\nRepeat: 20\nDifficulty: beginner");
                    break;
                case 3:
                    txt.setText("TRX Alligator\n\nTargets: shoulders,back,obliques\nSets: 4\nRepeat: 15\nlDifficulty: Intermediate");
                    break;
                case 4:
                    txt.setText("TRX Biceps Curl\n\nTargets: arms,abs\nSets: 3\nRepeat: 20\nDifficulty: Intermediate");
                    break;
                case 5:
                    txt.setText("TRX Squat\n\nTargets: quads,glutes,hamstrings\nSets: 4\nRepeat: 15\nDifficulty: beginner");
                    break;
                case 6:
                    txt.setText("TRX Lunge\n\nTargets: legs,abs\nSets: 3\nRepeat: 20\nDifficulty: Intermediate");
                    break;
                case 7:
                    txt.setText("TRX Side Plank\n\nTargets: Obliques\nSets: 3\nRepeat: 15\nDifficulty: beginner");
                    break;
                case 8:
                    txt.setText("TRX Atomic Pike\n\nTargets: abs,shoulders\nSets: 3\nRepeat: 10-15\nDifficulty: Intermediate");
                    break;

                default:
                    break;

            }
        }
    }
}

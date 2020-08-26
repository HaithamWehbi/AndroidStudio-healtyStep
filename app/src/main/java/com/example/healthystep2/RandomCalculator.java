package com.example.healthystep2;

import android.content.Intent;
import android.graphics.Color;
import android.support.annotation.ColorRes;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DecimalFormat;

public class RandomCalculator extends AppCompatActivity {

    Button calculate;

    EditText weight;
    EditText height;

    TextView ShowBMI;

    double BMI;

    int weightTemp;
    double heightTemp;

    ImageView homeButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_random_calculator);

        homeButton = (ImageView) findViewById(R.id.homebuttonforrandcalc);
        homeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RandomCalculator.this, Profile.class);
                startActivity(intent);
            }
        });

        calculate = (Button) findViewById(R.id.calculate_button);
        weight = (EditText) findViewById(R.id.weight_txt);
        height = (EditText) findViewById(R.id.height_txt);
        ShowBMI = (TextView) findViewById(R.id.result);

        //starting the result without value so it only gets this string
        String str = "Your BMI:";
        ShowBMI.setText(str);

        //every time we click on calculate button we get the bmi as result
        calculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //if EditTexts are empty error
                //else if the info are valid to calculate we return the bmi
                //else error and toast message will appear

                if (!weight.getText().toString().isEmpty() && !height.getText().toString().isEmpty()) {
                    weightTemp = Integer.parseInt(weight.getText().toString());
                    heightTemp = (Integer.parseInt(height.getText().toString())) / 100.0;

                    //calculating body mass index
                    BMI = weightTemp / heightTemp;
                    BMI = BMI / heightTemp;
                    //end of calculating body mass index

                    //display BMI in the box with 2 digits after point
                    String BMITemp;
                    BMITemp = new DecimalFormat("##.##").format(BMI);
                    ShowBMI.setText("Your BMI: " + BMITemp);
                    if (BMI > 24.9)//if over weight
                        ShowBMI.setBackgroundColor(Color.RED);
                    if (BMI < 18.5)//if under weight
                        ShowBMI.setBackgroundColor(Color.YELLOW);
                    if (BMI > 18.5 && BMI < 24.9)//if normal weight
                        ShowBMI.setBackgroundColor(Color.GREEN);
                } else {
                    Toast.makeText(RandomCalculator.this, "Error! Enter A Valid Weight And Height In Metrics", Toast.LENGTH_SHORT).show();
                }


            }
        });

    }

}

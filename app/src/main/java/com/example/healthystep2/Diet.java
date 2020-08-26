package com.example.healthystep2;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;

public class Diet extends AppCompatActivity {

    DataBaseHelper dataBaseHelper;

    ImageView homebutton;

    double BMI;
    double maxBMI = 24.9;
    double minBMI = 18.5;

    int weight;
    double height;

    DietHome dietHome;

    TextView KG, CM, BT;
    TextView DietPlan;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_diet);

        dataBaseHelper = new DataBaseHelper(this);

        Cursor cursor = dataBaseHelper.getData();

        DietPlan = (TextView) findViewById(R.id.MyPlan);

        homebutton = (ImageView) findViewById(R.id.homebuttonfordiet);
        homebutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Diet.this, Profile.class);
                startActivity(intent);
            }
        });

        weight = Integer.parseInt(cursor.getString(4));
        height = (Integer.parseInt(cursor.getString(5))) / 100.0;

        //getting blood type from diet home, if given
        String mybt = dietHome.BloodTypes;

        //these 3 variables to display in this page as diet info
        String BloodTypeToDisplay = mybt;
        int KgToDisplay = weight;
        double CmToDisplay = height;

        //these 3 TextViews are the info
        KG = (TextView) findViewById(R.id.textViewKG);
        CM = (TextView) findViewById(R.id.textViewCM);
        BT = (TextView) findViewById(R.id.textViewBloodType);

        KG.setText("KG: " + weight);
        CM.setText("CM: " + height);
        BT.setText("Blood Type: " + mybt);


        //calculating body mass index
        BMI = weight / height;
        BMI = BMI / height;
        //end of calculating body mass index

        //these are the diet plans from strings xml file
        String strForOver = getResources().getString(R.string.stringForOver);
        String strForUnder = getResources().getString(R.string.stringForUnder);
        String strForNormal = getResources().getString(R.string.stringForNormal);

        //my blood type tip
        String FlagForBloodType;

        //mixing my diet plan with the blood type tip
        String NormalAfterBloodTypeTip, OverAfterBloodTypeTip, UnderAfterBloodTypeTip;


        if (mybt.equals("A")) {
            FlagForBloodType = "\nType A blood:\n A meat-free diet based on fruits and vegetables, beans and legumes, and whole grains -- ideally, organic and fresh, because D'Adamo says people with type A blood have a sensitive immune system.";
            NormalAfterBloodTypeTip = strForNormal + FlagForBloodType;
            OverAfterBloodTypeTip = strForOver + FlagForBloodType;
            UnderAfterBloodTypeTip = strForUnder + FlagForBloodType;

            if (BMI > maxBMI)//if over
            {
                DietPlan.setText(OverAfterBloodTypeTip);
            }
            if (BMI < minBMI)//if under
            {
                DietPlan.setText(UnderAfterBloodTypeTip);
            }
            if (BMI > minBMI && BMI < maxBMI)//if normal
            {
                DietPlan.setText(NormalAfterBloodTypeTip);
            }
        }
        if (mybt.equals("B")) {
            FlagForBloodType = "\nType B blood:\n Avoid corn, wheat, buckwheat, lentils, tomatoes, peanuts, and sesame seeds. Chicken is also problematic, D'Adamo says. He encourages eating green vegetables, eggs, certain meats, and low-fat dairy.";
            NormalAfterBloodTypeTip = strForNormal + FlagForBloodType;
            OverAfterBloodTypeTip = strForOver + FlagForBloodType;
            UnderAfterBloodTypeTip = strForUnder + FlagForBloodType;

            if (BMI > maxBMI)//if over
            {
                DietPlan.setText(OverAfterBloodTypeTip);
            }
            if (BMI < minBMI)//if under
            {
                DietPlan.setText(UnderAfterBloodTypeTip);
            }
            if (BMI > minBMI && BMI < maxBMI)//if normal
            {
                DietPlan.setText(NormalAfterBloodTypeTip);
            }
        }
        if (mybt.equals("AB")) {
            FlagForBloodType = "\nType AB blood:\n Foods to focus on include tofu, seafood, dairy, and green vegetables. He says people with type AB blood tend to have low stomach acid. Avoid caffeine, alcohol, and smoked or cured meats.";
            NormalAfterBloodTypeTip = strForNormal + FlagForBloodType;
            OverAfterBloodTypeTip = strForOver + FlagForBloodType;
            UnderAfterBloodTypeTip = strForUnder + FlagForBloodType;

            if (BMI > maxBMI)//if over
            {
                DietPlan.setText(OverAfterBloodTypeTip);
            }
            if (BMI < minBMI)//if under
            {
                DietPlan.setText(UnderAfterBloodTypeTip);
            }
            if (BMI > minBMI && BMI < maxBMI)//if normal
            {
                DietPlan.setText(NormalAfterBloodTypeTip);
            }
        }
        if (mybt.equals("O")) {
            FlagForBloodType = "\nType O blood:\n A high-protein diet heavy on lean meat, poultry, fish, and vegetables, and light on grains, beans, and dairy. D'Adamo also recommends various supplements to help with tummy troubles and other issues he says people with type O tend to have.";
            NormalAfterBloodTypeTip = strForNormal + FlagForBloodType;
            OverAfterBloodTypeTip = strForOver + FlagForBloodType;
            UnderAfterBloodTypeTip = strForUnder + FlagForBloodType;

            if (BMI > maxBMI)//if over
            {
                DietPlan.setText(OverAfterBloodTypeTip);
            }
            if (BMI < minBMI)//if under
            {
                DietPlan.setText(UnderAfterBloodTypeTip);
            }
            if (BMI > minBMI && BMI < maxBMI)//if normal
            {
                DietPlan.setText(NormalAfterBloodTypeTip);
            }
        }

        //if the user didn't choose blood type
        if (mybt.equals("None")) {
            if (BMI > maxBMI)//if over
            {
                DietPlan.setText(strForOver);
            }
            if (BMI < minBMI)//if under
            {
                DietPlan.setText(strForUnder);
            }
            if (BMI > minBMI && BMI < maxBMI)//if normal
            {
                DietPlan.setText(strForNormal);
            }
        }


    }
}

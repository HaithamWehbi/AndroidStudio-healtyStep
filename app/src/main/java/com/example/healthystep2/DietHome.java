package com.example.healthystep2;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

public class DietHome extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    DataBaseHelper dataBaseHelper;

    double BMI;
    double maxBMI=24.9;
    double minBMI=18.5;

    int weight;
    double height;

    String name;
    public static String BloodTypes = "none";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_diet_home);

        dataBaseHelper = new DataBaseHelper(this);

        Cursor cursor = dataBaseHelper.getData();

        TextView diethometxt = (TextView) findViewById(R.id.DietHomeTxtMsg);
        Button diethomebtn = (Button) findViewById(R.id.DietHomeButton);
        Spinner spinner = findViewById(R.id.DietHomeSpinner);

        //setting up adapter for my blood type spinner
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,R.array.Blood_Types, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);
        //blood type adapter ends


        weight = Integer.parseInt(cursor.getString(4));
        height = (Integer.parseInt(cursor.getString(5)))/100.0;

        //calculating body mass index
        BMI = weight/height;
        BMI = BMI/height;
        //end of calculating body mass index

        //getting user's name from database
        name = cursor.getString(2);

        //this string "not required" was made in "strings values" to make the work BOLD
        String rndstr = getString(R.string.not_req);

        String normal = "Hello "+name+"\nAccording To Our BMI Calculations Your Current Statue Is 'Normal' Which Means You Have Reached Your Healthy Goal, This Diet Plan Will Help You On Keeping Your BMI Statue As It Is Now, By Doing That We Need You To Choose Below Your Blood Type "+rndstr+" Which Will Help Us To Provide You The Most Efficient Plan.";
        String under = "Hello "+name+"\nAccording To Our BMI Calculations Your Current Statue Is 'Under' Which Means You Have To Gain Some Weight To Reach Your Healthy Goal, This Diet Plan Will Help You On Gaining Weight, By Doing That We Need You To Choose Below Your Blood Type "+rndstr+" Which Will Help Us To Provide You The Most Efficient Plan.";
        String over = "Hello "+name+"\nAccording To Our BMI Calculations Your Current Statue Is 'Over' Which Means You Have To Lose Some Weight To Reach Your Healthy Goal, This Diet Plan Will Help You On Losing Body Fat And Saving Muscle, By Doing That We Need You To Choose Below Your Blood Type "+rndstr+" Which Will Help Us To Provide You The Most Efficient Plan.";

        if(BMI>maxBMI)//if over
        {
            diethometxt.setText(over);
        }
        if(BMI<minBMI)//if under
        {
            diethometxt.setText(under);
        }
        if(BMI>minBMI && BMI<maxBMI)//if normal
        {
            diethometxt.setText(normal);
        }

        diethomebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DietHome.this,Diet.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String text = parent.getItemAtPosition(position).toString();
        BloodTypes = text;
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
        //nothing to do when nothing selected
    }
}

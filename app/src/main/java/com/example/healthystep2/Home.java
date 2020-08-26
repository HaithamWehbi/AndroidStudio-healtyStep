package com.example.healthystep2;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

public class Home extends AppCompatActivity {

    ImageView homeButton;

    DataBaseHelper dataBaseHelper;

    int weight;
    double height;
    double BMI;

    double maxBMI = 24.9;
    double minBMI = 18.5;

    TextView gymmsg;

    Button backtoworkoutplans;

    int[] images = {R.drawable.homepushup, R.drawable.homedeclinepushup, R.drawable.homediamonpushup, R.drawable.homepullups, R.drawable.homechairdips, R.drawable.homesquat, R.drawable.homelunge, R.drawable.homesitups, R.drawable.hometoetoucher};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        //this button is to take us to profile page as a main page
        homeButton = (ImageView) findViewById(R.id.homebuttonforhomeworkout);
        homeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Home.this, Profile.class);
                startActivity(intent);
            }
        });

        //this button is to take us back to workout plans page
        backtoworkoutplans = (Button) findViewById(R.id.backtoworkoutplanfromhome);
        backtoworkoutplans.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Home.this,WorkOut.class);
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

        gymmsg = (TextView) findViewById(R.id.homemsg_txt);

        String normal = "Your Current Statue is 'Normal Weight' The Plan is To WorkOut Healthy To Save The Current Statue";
        String over = "Your Current Statue is 'Over Weight' The Plan is Work Harder On Losing BodyFat Instead Of Gaining Muscles";
        String under = "Your Current Statue is 'Under Weight' The Plan is To Work Harder On Building Muscle And Gaining Weight";

        //the user will get a message depending on the BMI statue
        if (BMI > maxBMI)
            gymmsg.setText(over);
        else if (BMI < minBMI)
            gymmsg.setText(under);
        else if (BMI > minBMI && BMI < maxBMI)
            gymmsg.setText(normal);

        ListView listView = (ListView) findViewById(R.id.listforhome);

        ListAdapterForHome listAdapterForHome = new ListAdapterForHome(this, images);

        listView.setAdapter(listAdapterForHome);
    }
}

package com.example.healthystep2;

import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.text.DecimalFormat;
import java.util.Calendar;

public class Profile extends AppCompatActivity {

    DataBaseHelper dataBaseHelper;

    double BMI;
    double maxBMI=24.9;
    double minBMI=18.5;

    String name;
    TextView showusername;

    int weight;
    double height;

    ImageView image1;
    ImageView image2;
    ImageView image3;

    Button editProfile;
    Button optionList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        dataBaseHelper = new DataBaseHelper(this);

        Cursor cursor = dataBaseHelper.getData();

        image1 = (ImageView) findViewById(R.id.red);
        image2 = (ImageView) findViewById(R.id.green);
        image3 = (ImageView) findViewById(R.id.yellow);

        editProfile = (Button) findViewById(R.id.edit_profile);
        optionList = (Button) findViewById(R.id.OptionList_button);

        showusername = (TextView) findViewById(R.id.showingusername);

        weight = Integer.parseInt(cursor.getString(4));
        height = (Integer.parseInt(cursor.getString(5)))/100.0;

        //getting user's name from database
        name = cursor.getString(2);
        showusername.setText("Hello "+name);

        //calculating body mass index
        BMI = weight/height;
        BMI = BMI/height;
        //end of calculating body mass index

        //if the user's weight "over weight" the pointer "picture" will point to the red zone
        //else if the user's weight "under weight" the pointer "picture" will point to the yellow zone
        //else the user's weight is "normal weight" the pointer "picture" will point to the green zone
        if(BMI>maxBMI)
        {
            image1.setVisibility(View.INVISIBLE);
            image2.setVisibility(View.INVISIBLE);
            image3.setVisibility(View.VISIBLE);
        }
        if(BMI<minBMI)
        {
            image1.setVisibility(View.VISIBLE);
            image2.setVisibility(View.INVISIBLE);
            image3.setVisibility(View.INVISIBLE);
        }
        if(BMI>minBMI && BMI<maxBMI)
        {
            image1.setVisibility(View.INVISIBLE);
            image2.setVisibility(View.VISIBLE);
            image3.setVisibility(View.INVISIBLE);
        }

        //if we click on edit profile button we move to another activity to update data
        editProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Profile.this,EditProfile.class);
                startActivity(intent);
            }
        });

        optionList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Profile.this,OptionPage.class);
                startActivity(intent);
            }
        });


        //the application will send notification every sunday to update user's weight
        Calendar calendar = Calendar.getInstance();
        int day = calendar.get(Calendar.DAY_OF_WEEK);


        if(day == Calendar.SUNDAY)
        {
            NotificationCompat.Builder builder = new NotificationCompat.Builder(this, "update weight")
                    .setSmallIcon(R.drawable.ic_notifications_black_24dp)
                    .setContentTitle("HealthyStep")
                    .setContentText("Hello "+name+", Update your weight for accurate plans.")
                    .setPriority(NotificationCompat.PRIORITY_DEFAULT);
            NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
            notificationManager.notify(1,builder.build());

        }
        //app notification end


    }
}

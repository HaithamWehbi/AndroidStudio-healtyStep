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

public class Trx extends AppCompatActivity {

    ImageView homeButton;

    DataBaseHelper dataBaseHelper;

    int weight;
    double height;
    double BMI;

    double maxBMI = 24.9;
    double minBMI = 18.5;

    TextView trxmsg;

    Button backtoworkoutplans;

    int[] images = {R.drawable.trxpushup, R.drawable.trxchestpress, R.drawable.trxlowrow, R.drawable.trxalligator, R.drawable.trxbicepcurl, R.drawable.trxsquat, R.drawable.trxlunge ,R.drawable.trxsideplank, R.drawable.trxatomicpike};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trx);

        //this button is to take us to profile page as a main page
        homeButton = (ImageView) findViewById(R.id.homebuttonfortrx);
        homeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Trx.this, Profile.class);
                startActivity(intent);
            }
        });

        //this button is to take us back to workout plans page
        backtoworkoutplans = (Button) findViewById(R.id.backtoworkoutplanfromtrx);
        backtoworkoutplans.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Trx.this,WorkOut.class);
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

        trxmsg = (TextView) findViewById(R.id.trxmsg_txt);

        String normal = "Your Current Statue is 'Normal Weight' The Plan is To WorkOut Healthy To Save The Current Statue";
        String over = "Your Current Statue is 'Over Weight' The Plan is Work Harder On Losing BodyFat Instead Of Gaining Muscles";
        String under = "Your Current Statue is 'Under Weight' The Plan is To Work Harder On Building Muscle And Gaining Weight";

        //the user will get a message depending on the BMI statue
        if (BMI > maxBMI)
            trxmsg.setText(over);
        else if (BMI < minBMI)
            trxmsg.setText(under);
        else if (BMI > minBMI && BMI < maxBMI)
            trxmsg.setText(normal);

        ListView listView = (ListView) findViewById(R.id.listfortrx);

        ListAdapterForTrx listAdapterForTrx = new ListAdapterForTrx(this, images);

        listView.setAdapter(listAdapterForTrx);
    }
}

package com.example.healthystep2;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class EditProfile extends AppCompatActivity {

    DataBaseHelper dataBaseHelper;

    EditText email;
    EditText name;
    EditText age;
    EditText weight;
    EditText height;

    Button save;

    String emailToFill;
    String nameToFill;
    String ageToFill;
    String weightToFill;
    String heightToFill;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);

        dataBaseHelper = new DataBaseHelper(this);

        Cursor cursor = dataBaseHelper.getData();

        email = (EditText) findViewById(R.id.email2);
        name = (EditText) findViewById(R.id.name2);
        age = (EditText) findViewById(R.id.age2);
        weight = (EditText) findViewById(R.id.weight2);
        height = (EditText) findViewById(R.id.height2);
        save = (Button) findViewById(R.id.update_data1);

        //getting the user info from data base
        emailToFill = cursor.getString(1);
        nameToFill = cursor.getString(2);
        ageToFill = cursor.getString(3);
        weightToFill = cursor.getString(4);
        heightToFill = cursor.getString(5);

        //setting the user info into the EditTexts the user is about to change so he can see the old data
        email.setText(emailToFill);
        name.setText(nameToFill);
        age.setText(ageToFill);
        weight.setText(weightToFill);
        height.setText(heightToFill);

        //if we click on the save data button we update the new info into the database
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                int rndFlag = 0;

                if(email.getText().toString().equals("") || name.getText().toString().equals("") || age.getText().toString().equals("") || weight.getText().toString().equals("") || height.getText().toString().equals(""))
                {
                    Toast.makeText(EditProfile.this,"Fill In All The EditTexts",Toast.LENGTH_SHORT).show();
                    rndFlag = 1;
                }

                if(rndFlag == 0)
                {
                    update();
                }
            }
        });
    }

    public void update() {

        //if the check is false the data wont be saved.
        //  else it will be saved and move the user to Profile.
        if (check()) {

            String email1 = email.getText().toString().trim();
            String name1 = name.getText().toString().trim();
            String age1 = age.getText().toString().trim();
            String weight1 = weight.getText().toString().trim();
            String height1 = height.getText().toString().trim();


            boolean val = dataBaseHelper.updateData(email1, name1, age1, weight1, height1);

            if (val) {
                Toast.makeText(EditProfile.this, "Info Updated!", Toast.LENGTH_SHORT).show();

                Intent moveToProfile = new Intent(EditProfile.this, Profile.class);
                startActivity(moveToProfile);
            } else {
                Toast.makeText(EditProfile.this, "Error Updating Info!", Toast.LENGTH_SHORT).show();
            }
        }
    }

    public boolean check() {

        boolean checker = true;

        String value = age.getText().toString();//casting for age
        int age2 = Integer.parseInt(value);

        String value2 = weight.getText().toString();//casting for weight
        int weight2 = Integer.parseInt(value2);

        String value3 = height.getText().toString();//casting for height
        int height2 = Integer.parseInt(value3);


        if (name.getText().toString().equals("") || name.length() < 3 || name.length() > 12) {
            Toast.makeText(EditProfile.this, "Name Error!, Enter Between 3 to 12 Characters", Toast.LENGTH_LONG).show();
            checker = false;
        } else if (email.getText().toString().equals("") || !Patterns.EMAIL_ADDRESS.matcher(email.getText().toString()).matches()) {
            Toast.makeText(EditProfile.this, "Enter a Valid Email", Toast.LENGTH_LONG).show();
            checker = false;
        } else if (age.getText().toString().equals("") || age2 > 100 || age2 < 10) {
            Toast.makeText(EditProfile.this, "Enter a Valid Age", Toast.LENGTH_LONG).show();
            checker = false;
        } else if (weight.getText().toString().equals("") || weight2 > 250 || weight2 < 20) {
            Toast.makeText(EditProfile.this, "Enter a Valid Weight in KG", Toast.LENGTH_LONG).show();
            checker = false;
        } else if (height.getText().toString().equals("") || height2 > 250 || height2 < 100) {
            Toast.makeText(EditProfile.this, "Enter a Valid Height in CM,", Toast.LENGTH_LONG).show();
            checker = false;
        }

        return checker;
    }


}

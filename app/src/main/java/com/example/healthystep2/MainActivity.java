package com.example.healthystep2;



import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {


    EditText email;
    EditText name;
    EditText age;
    EditText weight;
    EditText height;
    Button getStarted;

    DataBaseHelper dataBaseHelper;

    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dataBaseHelper = new DataBaseHelper(this);

        email = (EditText) findViewById(R.id.email);
        name = (EditText) findViewById(R.id.name);
        age = (EditText) findViewById(R.id.age);
        weight = (EditText) findViewById(R.id.weight);
        height = (EditText) findViewById(R.id.height);
        getStarted = (Button) findViewById(R.id.login);


        sharedPreferences = getSharedPreferences("login", MODE_PRIVATE);

        if (sharedPreferences.getBoolean("logged", false)) {
            Intent intent = new Intent(MainActivity.this, Profile.class);
            startActivity(intent);
        }


        //if we click on getStarted button to register
        //first we go to function "createAcc"
        //then function "check" to check the info the user entered
        //if all the info are valid the function "check" will return "true" to function "createAcc"
        //then the function "createAcc" will go to function "addData" if all info was valid
        //else a toast message will show that the account was not made.
        getStarted.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int rndFlag = 0;

                if(email.getText().toString().equals("") || name.getText().toString().equals("") || age.getText().toString().equals("") || weight.getText().toString().equals("") || height.getText().toString().equals(""))
                {
                    Toast.makeText(MainActivity.this,"Fill In All The EditTexts",Toast.LENGTH_SHORT).show();
                    rndFlag = 1;
                }

                if(rndFlag == 0)
                {
                    createAcc();
                }

            }
        });


    }

    public void createAcc() {

        //if the check is false the data wont be saved.
        //  else it will be saved and move the user to Profile.
        if (check()) {

            String email1 = email.getText().toString().trim();
            String name1 = name.getText().toString().trim();
            String age1 = age.getText().toString().trim();
            String weight1 = weight.getText().toString().trim();
            String height1 = height.getText().toString().trim();

            //remove everything from database
            dataBaseHelper.clearDatabase();

            long val = dataBaseHelper.addData(email1, name1, age1, weight1, height1);

            if (val > 0) {
                Toast.makeText(MainActivity.this, "Welcome!", Toast.LENGTH_SHORT).show();

                //changing flag for sharedPreferences so the next time user open app it will go to profile activity
                sharedPreferences.edit().putBoolean("logged", true).apply();

                Intent moveToLogin = new Intent(MainActivity.this, Profile.class);
                startActivity(moveToLogin);
            } else {
                Toast.makeText(MainActivity.this, "registration Error", Toast.LENGTH_SHORT).show();
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
            Toast.makeText(MainActivity.this, "Name Error!, Enter Between 3 to 12 Characters", Toast.LENGTH_LONG).show();
            checker = false;
        } else if (email.getText().toString().equals("") || !Patterns.EMAIL_ADDRESS.matcher(email.getText().toString()).matches()) {
            Toast.makeText(MainActivity.this, "Enter a Valid Email", Toast.LENGTH_LONG).show();
            checker = false;
        } else if (age.getText().toString().equals("") || age2 > 100 || age2 < 10) {
            Toast.makeText(MainActivity.this, "Enter a Valid Age", Toast.LENGTH_LONG).show();
            checker = false;
        } else if (weight.getText().toString().equals("") || weight2 > 250 || weight2 < 20) {
            Toast.makeText(MainActivity.this, "Enter a Valid Weight in KG", Toast.LENGTH_LONG).show();
            checker = false;
        } else if (height.getText().toString().equals("") || height2 > 250 || height2 < 100) {
            Toast.makeText(MainActivity.this, "Enter a Valid Height in CM,", Toast.LENGTH_LONG).show();
            checker = false;
        }

        return checker;
    }


}


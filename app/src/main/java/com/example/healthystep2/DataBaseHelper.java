package com.example.healthystep2;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DataBaseHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "register.db";
    public static final String TABLE_NAME = "registeruser";
    public static final String COL_1 = "ID";
    public static final String COL_2 = "username";
    public static final String COL_3 = "name";
    public static final String COL_4 = "age";
    public static final String COL_5 = "weight";
    public static final String COL_6 = "height";



    public DataBaseHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL("CREATE TABLE registeruser (ID INTEGER PRIMARY  KEY AUTOINCREMENT, username TEXT, name TEXT, age TEXT, weight TEXT, height TEXT)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(" DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);

    }

    //function to save user in database.
    public long addData(String user, String name, String age, String weight, String height)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put("username", user);
        contentValues.put("name", name);
        contentValues.put("age", age);
        contentValues.put("weight", weight);
        contentValues.put("height", height);

        long res = db.insert("registeruser", null, contentValues);
        db.close();

        return res;
    }

    //function to update the COLS in table
    //basic use is to keep the user's weight updated to get the right workout and diet plan
    public boolean updateData(String user, String name, String age, String weight, String height)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put("username", user);
        contentValues.put("name", name);
        contentValues.put("age", age);
        contentValues.put("weight", weight);
        contentValues.put("height", height);

        int result = db.update(TABLE_NAME,contentValues,"ID = ?",new String[]{"1"});

        if(result == 0)
        {
            return false;
        }
        else
        {
            return true;
        }
    }

    //function that gets for me the data from database
    //all COLS
    public Cursor getData()
    {
        SQLiteDatabase database = this.getWritableDatabase();

        String query = "SELECT  * FROM " + TABLE_NAME;
        Cursor res = database.rawQuery(query, null);
        res.moveToFirst();

        return res;
    }

    //this function will clear my database from the last table
    //because my application works on one user with one profile
    //if the user make a new account, the old account will be removed permanently
    //because there is no point saving more than one account
    //since each account has nothing to save but age, weight and height
    public void clearDatabase()
    {
        SQLiteDatabase database = this.getWritableDatabase();

        database.delete(TABLE_NAME,null,null);
    }

}

package com.example.aleksandar.mysqldemo;

/**
 * Created by Vlatko Popovic on 27-Jan-17.
 */

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.TextView;


public class ContactDB extends SQLiteOpenHelper {

    public ContactDB(Context context,SQLiteDatabase.CursorFactory factory, int version) {
        super(context, "Base", factory, version);
    }



    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("CREATE TABLE JAJE( ID INTEGER PRIMARY KEY AUTOINCREMENT,PASSWORD TEXT, NAME TEXT,LASTNAME TEXT);");

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS JAJE;");
        onCreate(sqLiteDatabase);
    }
    public void addContact(String number,String name,String lastname){

        ContentValues contentValues = new ContentValues();
        contentValues.put("PASSWORD", number);
        contentValues.put("NAME", name);
        contentValues.put("LASTNAME", lastname);

        this.getWritableDatabase().insertOrThrow("JAJE", "", contentValues);

    }
    public Cursor list_all_contact(TextView textView){
        Cursor cursor = this.getReadableDatabase().rawQuery("SELECT * FROM JAJE",null);


        while (cursor.moveToNext()) {







                    textView.append("Bend: " + cursor.getString(1)+ " " + "Datum: " + cursor.getString(2)+" " + "Datum: " + cursor.getString(3)+"\n" );

            }


        return cursor;
    }


    public void delete(){
        SQLiteDatabase db = this.getReadableDatabase();
        db.execSQL("DELETE FROM JAJE");

    }



}

package com.example.aleksandar.mysqldemo;

/**
 * Created by Vlatko Popovic on 27-Jan-17.
 */

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;



public class ContactDB extends SQLiteOpenHelper {

    public ContactDB(Context context,SQLiteDatabase.CursorFactory factory, int version) {
        super(context, "Base", factory, version);
    }



    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("CREATE TABLE CONTACT( ID INTEGER PRIMARY KEY AUTOINCREMENT,PASSWORD TEXT, NAME TEXT);");

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS CONTACT;");
        onCreate(sqLiteDatabase);
    }
    public void addContact(String number,String name){

        ContentValues contentValues = new ContentValues();
        contentValues.put("PASSWORD", number);
        contentValues.put("NAME", name);

        this.getWritableDatabase().insertOrThrow("CONTACT", "", contentValues);

    }
    public Cursor list_all_contact(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM CONTACT",null);
        return cursor;
    }


    public void delete(){
        SQLiteDatabase db = this.getReadableDatabase();
        db.execSQL("DELETE FROM CONTACT");

    }



}

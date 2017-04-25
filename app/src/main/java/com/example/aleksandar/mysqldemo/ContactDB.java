package com.example.aleksandar.mysqldemo;

/**
 * Created by Vlatko Popovic on 27-Jan-17.
 */

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteQueryBuilder;
import android.widget.TextView;


public class ContactDB extends SQLiteOpenHelper {

    public ContactDB(Context context,SQLiteDatabase.CursorFactory factory, int version) {
        super(context, "Base", factory, version);
    }



    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("CREATE TABLE JAJE( ID INTEGER PRIMARY KEY AUTOINCREMENT, DATUM TEXT, BEND TEXT,IME TEXT,GRAD TEXT,LOKAL TEXT,EVENT TEXT);");

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS JAJE;");
        onCreate(sqLiteDatabase);
    }
    public void addContact(String datum,String bend,String ime,String grad,String lokal,String event){

        ContentValues contentValues = new ContentValues();
        contentValues.put("DATUM", datum);
        contentValues.put("BEND", bend);
        contentValues.put("IME", ime);
        contentValues.put("GRAD", grad);
        contentValues.put("LOKAL", lokal);
        contentValues.put("EVENT", event);

        this.getWritableDatabase().insertOrThrow("JAJE", "", contentValues);

    }
    public Cursor list_all_contact(TextView textView){
        Cursor cursor = this.getReadableDatabase().rawQuery("SELECT * FROM JAJE",null);

        while (cursor.moveToNext()) {
                    textView.append(cursor.getString(1)+ "  " + cursor.getString(2));
            }

        return cursor;
    }
    public Cursor list_all_list(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM JAJE ORDER BY DATUM asc",null);
        return cursor;
    }
    public Cursor list_all_list2(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM JAJE",null);
        return cursor;
    }



    public void delete(){
        SQLiteDatabase db = this.getReadableDatabase();
        db.execSQL("DELETE FROM JAJE");

    }






}

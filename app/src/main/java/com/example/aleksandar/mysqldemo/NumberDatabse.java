package com.example.aleksandar.mysqldemo;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.TextView;

/**
 * Created by Vlatko Popovic on 13-Mar-17.
 */

     public class NumberDatabse extends SQLiteOpenHelper {

    public NumberDatabse(Context context, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, "Baza", factory, version);
    }



    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("CREATE TABLE IMENIK( ID INTEGER PRIMARY KEY AUTOINCREMENT, IME TEXT, BROJ TEXT);");

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS IMENIK;");
        onCreate(sqLiteDatabase);
    }
    public void save_u_imenik(String ime,String broj){

        ContentValues contentValues = new ContentValues();
        contentValues.put("IME", ime);
        contentValues.put("BROJ", broj);


        this.getWritableDatabase().insertOrThrow("IMENIK", "", contentValues);

    }
    public Cursor listaj_imenik(TextView textView){
        Cursor cursor = this.getReadableDatabase().rawQuery("SELECT * FROM IMENIK",null);

        while (cursor.moveToNext()) {
            textView.append(cursor.getString(1)+ "  " + cursor.getString(2)+"  " + cursor.getString(3)+"  "+cursor.getString(4)+"  "+ cursor.getString(5)+"  "+ cursor.getString(6)+"\n" );
        }

        return cursor;
    }
    public Cursor prikazi_ceo_imenik(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM IMENIK",null);
        return cursor;
    }



    public void delete(){
        SQLiteDatabase db = this.getReadableDatabase();
        db.execSQL("DELETE FROM IMENIK");

    }


}

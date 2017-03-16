package com.example.aleksandar.mysqldemo.PROBA;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.text.TextUtils;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Vlatko Popovic on 13-Mar-17.
 */

     public class NumberDatabse extends SQLiteOpenHelper {




    public static final String DATABASE_NAME = "Baza.db";
    public static final String CONTACTS_TABLE_NAME = "contacts";
    public static final String CONTACTS_COLUMN_ID = "id";
    public static final String CONTACTS_COLUMN_NAME = "name";
    public static final String CONTACTS_COLUMN_PHONE = "phone";
    private HashMap hp;








    public NumberDatabse(Context context) {
        super(context,DATABASE_NAME, null,1);
    }



    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(
                "create table contacts " +
                        "(id integer primary key, name text,phone text)"
        );
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS contacts");
        onCreate(sqLiteDatabase);
    }
    public boolean  save_u_imenik(String ime,String broj){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("name", ime);
        contentValues.put("phone", broj);


        db.insert("contacts", null, contentValues);
        return true;

    }
    public Cursor getPodatke(int id) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery( "select * from contacts where id="+id+"", null );
        return res;
    }



    public int numberOfRows(){
        SQLiteDatabase db = this.getReadableDatabase();
        int numRows = (int) DatabaseUtils.queryNumEntries(db, CONTACTS_TABLE_NAME);
        return numRows;
    }



    public boolean updateContact (Integer id, String name, String phone) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("name", name);
        contentValues.put("phone", phone);
        db.update("contacts", contentValues, "id = ? ", new String[] { Integer.toString(id) } );
        return true;
    }
    public Integer deleteContact (int id) {

        SQLiteDatabase db = this.getWritableDatabase();

       return db.delete("contacts",
                "id = ? ",
                new String[] { Integer.toString(id) });



    }
    public ArrayList<String> getAllContacts() {
        ArrayList<String> array_list = new ArrayList<String>();

        //hp = new HashMap();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery( "select * from contacts", null );
        res.moveToFirst();

        while(res.isAfterLast() == false){
            array_list.add(res.getString(res.getColumnIndex(CONTACTS_COLUMN_NAME)));
            res.moveToNext();
        }
        return array_list;
    }

    public Cursor list_all_list(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM contacts ORDER BY phone asc",null);
        return cursor;
    }



    public void delete(){
        SQLiteDatabase db = this.getReadableDatabase();
        db.execSQL("DELETE FROM contacts");

    }







  /*  public void deleteRec(String[] ids) { //ids is an array
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete("contacts", "CAST("+CONTACTS_COLUMN_ID+" AS TEXT) IN (" + new String(new char[ids.length-1]).replace("\0", "?,") + "?)", ids);
        db.close();
    }*/


}

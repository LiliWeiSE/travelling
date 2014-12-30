package com.example.traveing.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class MyDataBase extends SQLiteOpenHelper {
	public static final String DB_NAME = "travel_db";
	
	//journey table
    public static final String JOURNEY_TABLE_NAME = "journey_table";
    public static final String JOURNEY_COLUMN1 = "place";
    public static final String JOURNEY_COLUMN2 = "date";
    
    //Record table
    public static final String RECORD_TABLE_NAME = "record_table";
    public static final String RECORD_COLUMN1 = "jid";
    public static final String RECORD_COLUMN2 = "description";
    public static final String RECORD_COLUMN3 = "pic_url";
	public MyDataBase(Context context){
        super(context, DB_NAME, null, 1);
    }
	
	public void onCreate(SQLiteDatabase db) {
		db.execSQL("create table " + JOURNEY_TABLE_NAME + " ( _id integer primary key autoincrement, "
                + JOURNEY_COLUMN1 + " text, "
                + JOURNEY_COLUMN2 + " text );");
		
        db.execSQL("create table " + RECORD_TABLE_NAME + " ( _id integer primary key autoincrement, "
                + RECORD_COLUMN1 + " int, "
                + RECORD_COLUMN2 + " text, "
                + RECORD_COLUMN3 + " text);");
	}
	public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i2) {
        //To change body of implemented methods use File | Settings | File Templates.
    }
}
package com.example.traveing.database;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class JourneyDAL {
	private SQLiteDatabase myDb;
    private Cursor journeyCursor;
    
    public JourneyDAL(Context context){
        MyDataBase dbHelper = new MyDataBase(context);
        myDb = dbHelper.getWritableDatabase();
        journeyCursor = myDb.query(MyDataBase.JOURNEY_TABLE_NAME, new String[]{ "_id", MyDataBase.JOURNEY_COLUMN1, MyDataBase.JOURNEY_COLUMN2},null,null,null,null,null);

        journeyCursor.moveToFirst();
    }
	public Cursor getJourneyCursor() {
		return journeyCursor;
	}
    public long insert(String place, String date) {
    	ContentValues journey = new ContentValues();
    	
    	journey.put(MyDataBase.JOURNEY_COLUMN1, place);
    	journey.put(MyDataBase.JOURNEY_COLUMN2, date);
    	long returnVal = myDb.insert(MyDataBase.JOURNEY_TABLE_NAME, null, journey);
    	
    	journeyCursor = myDb.query(MyDataBase.JOURNEY_TABLE_NAME, new String[]{ "_id", MyDataBase.JOURNEY_COLUMN1, MyDataBase.JOURNEY_COLUMN2},null,null,null,null,null);
    	
    	return returnVal;
    }
    public String getPlace(long jid) {
    	Cursor cursor= myDb.query(MyDataBase.JOURNEY_TABLE_NAME, new String[]{MyDataBase.JOURNEY_COLUMN1}, "_id = ?", new String[]{Long.toString(jid)}, null, null, null);
    	if(cursor.moveToFirst() == false)
    		return null;
    	return cursor.getString(cursor.getColumnIndex(MyDataBase.JOURNEY_COLUMN1));
    }
}
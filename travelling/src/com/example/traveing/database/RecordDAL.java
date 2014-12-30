package com.example.traveing.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class RecordDAL {
	private SQLiteDatabase myDb;
	private Cursor recordCursor;
	
	public RecordDAL(Context context, long jid) {
		MyDataBase dbHelper = new MyDataBase(context);
        myDb = dbHelper.getWritableDatabase();
        recordCursor = myDb.query(
        				MyDataBase.RECORD_TABLE_NAME, 
        				new String[]{ "_id", MyDataBase.RECORD_COLUMN1, MyDataBase.RECORD_COLUMN2, MyDataBase.RECORD_COLUMN3},
        				"jid = ?",
        				new String[]{Long.toString(jid)},
        				null,
        				null,
        				null);

        recordCursor.moveToFirst();
	}

	public Cursor getRecordCursor() {
		return recordCursor;
	}

	public long insert(long jid, String description, String url) {
		ContentValues record = new ContentValues();
		record.put(MyDataBase.RECORD_COLUMN1, jid);
		record.put(MyDataBase.RECORD_COLUMN2, description);
		record.put(MyDataBase.RECORD_COLUMN3, url);
		long returnVal = myDb.insert(MyDataBase.RECORD_TABLE_NAME, null, record);
		
		recordCursor = myDb.query(MyDataBase.RECORD_TABLE_NAME, new String[]{ "_id", MyDataBase.RECORD_COLUMN1, MyDataBase.RECORD_COLUMN2, MyDataBase.RECORD_COLUMN3},null,null,null,null,null);
		
    	return returnVal;
	}
}
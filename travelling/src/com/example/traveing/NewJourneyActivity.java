package com.example.traveing;

import com.example.traveing.database.JourneyDAL;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class NewJourneyActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_new_journey);
		//getActionBar().setDisplayHomeAsUpEnabled(true);
		ActionBar actionBar=getActionBar();
		actionBar.setDisplayHomeAsUpEnabled(true);
		actionBar.setTitle("新建旅程");
		actionBar.setDisplayShowHomeEnabled(false);
	}
	
	public void finish(View view) {
		EditText editTextPlace = (EditText) findViewById(R.id.editTextPlace);
		EditText editTextTime = (EditText) findViewById(R.id.editTextTime);
		String strPlace = editTextPlace.getText().toString();
		String strTime = editTextTime.getText().toString();
		
		if(strPlace.equals("") || strTime.equals(""))
			Toast.makeText(getApplicationContext(), "时间与地点必填", Toast.LENGTH_SHORT).show();
		else {
			JourneyDAL dal = new JourneyDAL(this);
			long jid = dal.insert(strPlace, strTime);
			if(jid == -1)
				Toast.makeText(getApplicationContext(), "数据库错误", Toast.LENGTH_SHORT).show();
			else {
				Intent intent = new Intent(this, JourneyListActivity.class);
				intent.putExtra("place", strPlace);
				intent.putExtra("time", strTime);
				intent.putExtra("jid", jid);
				startActivity(intent);
		        finish();
			}
			
		}
		
	} 
}

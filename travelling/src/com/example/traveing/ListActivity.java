package com.example.traveing;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

//import com.example.test.R;


import com.example.traveing.database.JourneyDAL;
import com.example.traveing.database.MyDataBase;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.AdapterView.OnItemClickListener;
import android.os.Build;

public class ListActivity extends Activity {

	private List<Map<String, Object>> mList;
	private ListView mListView;
	private SimpleAdapter mListAdapter;
	private JourneyDAL dal = null;
	private Cursor cursor = null;

	private class onListClickListener implements OnItemClickListener {

		@Override
		public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
				long arg3) {
			// TODO Auto-generated method stub

			Map<String, Object> map = mList.get(arg2);

			Intent intent = new Intent(ListActivity.this,
					JourneyListActivity.class);
			intent.putExtra("place", (String)map.get("place"));
			intent.putExtra("jid", (long)map.get("id"));
			startActivity(intent);
		}
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_list);
		ActionBar actionBar = getActionBar();
		actionBar.setDisplayHomeAsUpEnabled(true);
		actionBar.setTitle("我的旅程");
		actionBar.setDisplayShowHomeEnabled(false);
		dal = new JourneyDAL(this);
		cursor = dal.getJourneyCursor();
		if (cursor.moveToFirst() != false) {
			mList = new ArrayList<Map<String, Object>>();

			//Map<String, Object> map = new HashMap<String, Object>();
			// map.put("First", "2014-10");
			// map.put("Next", "北京");
			// map.put("State", "");
			// mList.add(map);
			// mList.add(map);
			// mList.add(map);
			do{
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("time", cursor.getString(cursor.getColumnIndex(MyDataBase.JOURNEY_COLUMN2)));
				map.put("place", cursor.getString(cursor.getColumnIndex(MyDataBase.JOURNEY_COLUMN1)));
				map.put("id", cursor.getLong(cursor.getColumnIndex("_id")));
				mList.add(map);
			}while(cursor.moveToNext());
			mListAdapter = new SimpleAdapter(this, mList, R.layout.list_row,
					new String[] { "time", "place" }, new int[] {
							R.id.textOwn, R.id.textTo});

			mListView = (ListView) findViewById(R.id.ListView01);
			mListView.setAdapter(mListAdapter);
			mListView.setOnItemClickListener(new onListClickListener());
		}
	}

}

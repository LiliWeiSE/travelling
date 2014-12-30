package com.example.traveing;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.example.traveing.database.MyDataBase;
import com.example.traveing.database.RecordDAL;

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

public class JourneyListActivity extends Activity {
	private List<Map<String, Object>> mList;
	private ListView mListView;
	private SimpleAdapter mListAdapter;
	private RecordDAL dal;
	private Cursor cursor;
	private Intent intent;
	private long jid;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_journey_list);
		ActionBar actionBar = getActionBar();
		actionBar.setDisplayHomeAsUpEnabled(true);
		intent = getIntent();
		jid = intent.getLongExtra("jid", -1);
		actionBar.setTitle(intent.getStringExtra("place"));
		actionBar.setDisplayShowHomeEnabled(false);
		
		dal = new RecordDAL(this, jid);

		cursor = dal.getRecordCursor();

		if (cursor.moveToFirst() != false) {
			mList = new ArrayList<Map<String, Object>>();
			//Map<String, Object> map = new HashMap<String, Object>();
			// map.put("item_image", R.drawable.bj);
			// map.put("item_description", "第一天出发");
			// mList.add(map);
			// mList.add(map);
			// mList.add(map);
			do{
				
				//buggy??????????????????????????????????????????????????????????????????????????
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("item_image", cursor.getString(cursor.getColumnIndex(MyDataBase.RECORD_COLUMN3)));
				map.put("item_description", cursor.getString(cursor.getColumnIndex(MyDataBase.RECORD_COLUMN2)));
				mList.add(map);
			}while(cursor.moveToNext());

			mListAdapter = new SimpleAdapter(this, mList,
					R.layout.journey_item, new String[] { "item_image",
							"item_description" }, new int[] { R.id.item_image,
							R.id.item_description });

			mListView = (ListView) findViewById(R.id.listView_journey);
			mListView.setAdapter(mListAdapter);
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.journey_list, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_add) {
			onAdd();
		}
		return super.onOptionsItemSelected(item);
	}

	public void onAdd() {
		Intent intent = new Intent(JourneyListActivity.this, AddActivity.class);
		intent.putExtra("jid", jid);
		startActivity(intent);
	}
}

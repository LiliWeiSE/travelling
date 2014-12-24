package com.example.traveing;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

//import com.example.test.R;


import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
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
	
	private class onListClickListener implements OnItemClickListener {
		
		 @Override
        public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
                long arg3) {
            // TODO Auto-generated method stub
            
        		Map<String, Object> map=mList.get(arg2);
        			
        		Intent intent = new Intent(ListActivity.this, JourneyListActivity.class);
                startActivity(intent);
        }
	}
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_list);
		ActionBar actionBar=getActionBar();
		actionBar.setDisplayHomeAsUpEnabled(true);
		actionBar.setTitle("我的旅程");
		actionBar.setDisplayShowHomeEnabled(false);

		/*if (savedInstanceState == null) {
			getSupportFragmentManager().beginTransaction()
					.add(R.id.container, new PlaceholderFragment()).commit();
		}*/
		mList  = new ArrayList<Map<String,Object>>();
		Map<String, Object> map = new HashMap<String, Object>(); 
		map.put("First", "2014-10"); 
		map.put("Next",  "北京"); 
		map.put("State", "");
		mList.add(map);
		mList.add(map);
		mList.add(map);
		mListAdapter = new SimpleAdapter(this, mList, R.layout.list_row, 
				new String[]{"First", "Next", "State"}, 
				new int[]{R.id.textOwn, R.id.textTo, R.id.textState}); 
				
				mListView = (ListView) findViewById(R.id.ListView01);
				mListView.setAdapter(mListAdapter);
				mListView.setOnItemClickListener(new onListClickListener());
	}


	/**
	 * A placeholder fragment containing a simple view.
	 */
	/*public static class PlaceholderFragment extends Fragment {

		public PlaceholderFragment() {
		}

		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {
			View rootView = inflater.inflate(R.layout.fragment_list, container,
					false);
			return rootView;
		}
	}*/

}

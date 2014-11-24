package com.example.traveing;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	}
	
	public void gotoList(View view) {
        Intent intent = new Intent(this, ListActivity.class);
        startActivity(intent);
    }
	public void gotoNew(View view) {
        Intent intent = new Intent(this, NewJourneyActivity.class);
        startActivity(intent);
    }
}

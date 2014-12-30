package com.example.traveing;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.example.traveing.database.JourneyDAL;
import com.example.traveing.database.RecordDAL;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

public class AddActivity extends Activity {
	private final int CAMERA = 1;
	private String imgPath = null;
	private String imgName = null;
	private ImageView addImage = null;
	private File mFile = null;
	private long jid;
	private EditText editText; 

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_add);
		ActionBar actionBar=getActionBar();
		actionBar.setDisplayHomeAsUpEnabled(true);
		actionBar.setTitle("我的旅程");
		actionBar.setDisplayShowHomeEnabled(false);
		addImage = (ImageView) findViewById(R.id.imageButtonAdd);
		editText = (EditText) findViewById(R.id.editDes);
		jid = getIntent().getLongExtra("jid", -1);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.add, menu);
		return true;
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_finish) {
			onFinish();
		}
		return super.onOptionsItemSelected(item);
	}
	
	public void addImage(View view) {
		DateFormat df = new SimpleDateFormat("yyMMdd_HHmmss");
		imgName = df.format(new Date());
		boolean sdCardExist = Environment.getExternalStorageState().equals(
				android.os.Environment.MEDIA_MOUNTED);
		if (sdCardExist) {
			imgPath = Environment.getExternalStoragePublicDirectory(
					Environment.DIRECTORY_DCIM).toString()
					+ "/AppTrave/";

		} else {
			imgPath = Environment.getDataDirectory().toString() + "/AppTrave/";
		}
		System.out.println(imgPath);

		File mDirPath = new File(imgPath);
		if (!mDirPath.exists())
			mDirPath.mkdirs();

		try {
			mFile = File.createTempFile(imgName, /* prefix */
					".jpg", /* suffix */
					mDirPath /* directory */
			);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		Uri uri = Uri.fromFile(mFile);

		Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
		intent.putExtra(MediaStore.EXTRA_OUTPUT, uri);
		startActivityForResult(intent, CAMERA);
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		if (resultCode == RESULT_OK) {
			addImage.setImageURI(Uri.fromFile(mFile));
			addImage.setClickable(false);
		}
	}
	public void onFinish() {
		RecordDAL dal = new RecordDAL(this, jid);
		if(mFile!=null)
			dal.insert(jid, editText.getText().toString(), mFile.getPath());
		else
			dal.insert(jid, editText.getText().toString(), "");
		
		JourneyDAL jDal = new JourneyDAL(this);
		String place = jDal.getPlace(jid);
		
		Intent intent = new Intent(this, JourneyListActivity.class);
		intent.putExtra("place", place);
		intent.putExtra("jid", jid);
		
		startActivity(intent);
		finish();
		//sdfghjkl;f not ready!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
	}
}

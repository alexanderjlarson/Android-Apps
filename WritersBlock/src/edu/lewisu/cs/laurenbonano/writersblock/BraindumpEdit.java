package edu.lewisu.cs.laurenbonano.writersblock;

import android.app.Activity;
import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class BraindumpEdit extends Activity {
	
	EditText bdumpTitle;
	EditText bdumpStory;
	Button bdumpSave;
	Uri bdumpUri;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.braindump2test);
		setTitle("Braindump Edit");
		bdumpTitle = (EditText)findViewById(R.id.bdumpTitle);
		bdumpStory = (EditText)findViewById(R.id.bdumpStory);
		bdumpSave = (Button)findViewById(R.id.bdumpSave);
		
		Bundle extras = getIntent().getExtras();
		if(savedInstanceState!=null){
			bdumpUri=(Uri) savedInstanceState.getParcelable("uri");
		}
		if(extras != null){
			bdumpUri = extras.getParcelable("uri");
		}
		
		String buttonText;
		if(bdumpUri != null)
			buttonText = getResources().getString(R.string.update);
		else
			buttonText = getResources().getString(R.string.save);
		bdumpSave.setText(buttonText);
		
		if (bdumpUri != null){
			populateFields(bdumpUri);
		}
	}
	
	public void buttonClick(View v){
		String title = bdumpTitle.getText().toString();
		String story = bdumpStory.getText().toString();
		ContentValues values = new ContentValues();
		values.put(BraindumpTable.COL_BDUMP, title);
		values.put(BraindumpTable.COL_STORY, story);
		if(bdumpUri == null && !title.equals("")){
			bdumpUri = getContentResolver().insert(CProvider.BDUMP_URI, values);
		}
		else if(bdumpUri!=null){
			getContentResolver().update(bdumpUri, values, null, null);
		}
		finish();
	}
	
	@Override
	protected void onSaveInstanceState(Bundle outState) {
		super.onSaveInstanceState(outState);
		outState.putParcelable("uri", bdumpUri);
	}

	private void populateFields(Uri uri){
		String[] projection = {BraindumpTable.COL_BDUMP, BraindumpTable.COL_STORY};
		Cursor cursor = getContentResolver().query(uri, projection, null, null, null);
		
		if(cursor!=null){
			cursor.moveToFirst();
			String title = cursor.getString(cursor.getColumnIndexOrThrow(BraindumpTable.COL_BDUMP));
			String story = cursor.getString(cursor.getColumnIndexOrThrow(BraindumpTable.COL_STORY));
			bdumpTitle.setText(title);
			bdumpStory.setText(story);
		}
	}

}

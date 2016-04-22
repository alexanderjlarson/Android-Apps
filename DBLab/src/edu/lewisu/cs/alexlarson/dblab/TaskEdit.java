package edu.lewisu.cs.alexlarson.dblab;

import android.app.Activity;
import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;

public class TaskEdit extends Activity{
	
	EditText text;
	Spinner spin;
	CheckBox box;
	Button button;
	Uri taskUri;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.edit);
		text = (EditText) findViewById(R.id.editText1);
		spin = (Spinner) findViewById(R.id.spinner1);
		box = (CheckBox) findViewById(R.id.checkBox1);
		button = (Button) findViewById(R.id.button1);
		
		Bundle extras = getIntent().getExtras();
		if(savedInstanceState!=null){
			taskUri=(Uri) savedInstanceState.getParcelable("uri");
		}
		if(extras != null){
			taskUri = extras.getParcelable("uri");
		}
		String buttonText;
		if(taskUri !=null)
			buttonText = getResources().getString(R.string.update);
		else
			buttonText = getResources().getString(R.string.add);
		button.setText(buttonText);
		
		if (taskUri != null){
			populateFields(taskUri);
		}
	}
	
	@Override
	protected void onSaveInstanceState(Bundle outState) {
		super.onSaveInstanceState(outState);
		outState.putParcelable("uri", taskUri);
	}

	public void buttonClick(View v){
		String descr = text.getText().toString();
		String priority = spin.getSelectedItem().toString();
		int done=0;
		if(box.isChecked())
			done=1;
		
		ContentValues values = new ContentValues();
		values.put(TaskTable.COL_DESCRIPTION, descr);
		values.put(TaskTable.COL_PRIORITY, priority);
		values.put(TaskTable.COL_DONE, done);
		
		if(taskUri == null && !descr.equals("")){
			taskUri = getContentResolver().insert(TaskProvider.CONTENT_URI, values);
		}else if(taskUri !=null){
			getContentResolver().update(taskUri, values, null, null);
		}
		
		finish();
	}
	
	private void populateFields(Uri uri){
		String[] projection = {TaskTable.COL_DESCRIPTION,
				TaskTable.COL_PRIORITY, TaskTable.COL_DONE};
		Cursor cursor = getContentResolver().query(uri, projection, null, null, null);
		
		if(cursor != null){
			cursor.moveToFirst();
			String desc = cursor.getString(cursor.getColumnIndexOrThrow(TaskTable.COL_DESCRIPTION));
			String priority = cursor.getString(cursor.getColumnIndexOrThrow(TaskTable.COL_PRIORITY));
			int check = cursor.getInt(cursor.getColumnIndexOrThrow(TaskTable.COL_DONE));
			text.setText(desc);
			
			String[] priorities = getResources().getStringArray(R.array.priorities);
			for(int i=0; i < priorities.length; i++){
				spin.setSelection(i);
			}
			
			if (check == 1)
				box.setChecked(true);
		}
	}
}

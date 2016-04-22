package edu.lewisu.cs.alexlarson.dblab;

import android.app.Activity;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteQueryBuilder;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.widget.TextView;

public class MainActivity extends Activity {
	
	TextView view;
	DBHelper dbHelper;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		view = (TextView) findViewById(R.id.textView1);
		//dbHelper = new DBHelper(getApplication());
		//dbHelper.getWritableDatabase();
		//view.setText("success");
		//dbHelper.close();
		/*ContentValues values = new ContentValues();
		values.put(TaskTable.COL_DESCRIPTION, "Something to do");
		values.put(TaskTable.COL_PRIORITY, "1");
		values.put(TaskTable.COL_DONE, 0);
		
		Uri result = getContentResolver().insert(TaskProvider.CONTENT_URI, values);
		view.setText(result.toString());*/
		
		/*Uri taskUri = Uri.parse(TaskProvider.CONTENT_URI + "/1");
		ContentValues newValues = new ContentValues();
		newValues.put(TaskTable.COL_DESCRIPTION, "New Task Name");
		newValues.put(TaskTable.COL_PRIORITY, "1");
		newValues.put(TaskTable.COL_DONE, 0);
		getContentResolver().update(taskUri, newValues, null, null);
		Uri uri = Uri.parse(TaskProvider.CONTENT_URI + "/1");
		String[] projection = {TaskTable.COL_ID, TaskTable.COL_PRIORITY, TaskTable.COL_DESCRIPTION };
		Cursor cursor = getContentResolver().query(uri, projection, null, null, null);
		String queryResult="";
		if(cursor == null){
			queryResult = "not found";
		}else if (cursor.getCount()==1){
			cursor.moveToNext();
			queryResult = cursor.getString(2);
		}else{
			queryResult = "error";
		}
		view.setText(queryResult);*/
		
		Uri uri = Uri.parse(TaskProvider.CONTENT_URI + "/1");
		int rowsDeleted = getContentResolver().delete(uri, null, null);
		view.setText(rowsDeleted + " row deleted");
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}

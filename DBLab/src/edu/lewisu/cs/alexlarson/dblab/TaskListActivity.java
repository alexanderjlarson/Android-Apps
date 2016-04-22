package edu.lewisu.cs.alexlarson.dblab;

import android.app.ListActivity;
import android.app.LoaderManager;
import android.content.CursorLoader;
import android.content.Intent;
import android.content.Loader;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView.AdapterContextMenuInfo;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

public class TaskListActivity extends ListActivity implements LoaderManager.LoaderCallbacks<Cursor>{

	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
		super.onListItemClick(l, v, position, id);
		Intent i = new Intent(this, TaskEdit.class);
		Uri taskUri = Uri.parse(TaskProvider.CONTENT_URI + "/" + id);
		i.putExtra("uri", taskUri);
		startActivity(i);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public void onCreateContextMenu(ContextMenu menu, View v,
			ContextMenuInfo menuInfo) {
		getMenuInflater().inflate(R.menu.context, menu);
	}

	@Override
	public boolean onContextItemSelected(MenuItem item) {
		switch(item.getItemId()){
		case R.id.menu_delete:
			AdapterContextMenuInfo info = (AdapterContextMenuInfo) item.getMenuInfo();
			Uri uri = Uri.parse(TaskProvider.CONTENT_URI + "/" + info.id);
			getContentResolver().delete(uri, null, null);
			return true;
		}
		return super.onContextItemSelected(item);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch(item.getItemId()){
		case R.id.menu_add:
			Intent i = new Intent(this, TaskEdit.class);
			startActivity(i);
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	private SimpleCursorAdapter adapter;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_list);
		
		String[] from = new String[]{ TaskTable.COL_PRIORITY,
				TaskTable.COL_DESCRIPTION, TaskTable.COL_ID};
		int[] to = new int[]{R.id.priorityText, R.id.taskText};
		
		adapter = new SimpleCursorAdapter(
				this, R.layout.row, null, from, to, 0);
		setListAdapter(adapter);
		
		getLoaderManager().initLoader(0, null, this);
		registerForContextMenu(getListView());
	}

	@Override
	public Loader<Cursor> onCreateLoader(int id, Bundle arg) {
		String[] projection = { TaskTable.COL_ID, TaskTable.COL_PRIORITY,
				TaskTable.COL_DESCRIPTION };
		CursorLoader cursorLoader = new CursorLoader(this, TaskProvider.CONTENT_URI,
				projection, TaskTable.COL_DONE + "= 0", null, null);
		return cursorLoader;
	}

	@Override
	public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
		adapter.swapCursor(data);
		
	}

	@Override
	public void onLoaderReset(Loader<Cursor> loader) {
		adapter.swapCursor(null);
		
	}

}

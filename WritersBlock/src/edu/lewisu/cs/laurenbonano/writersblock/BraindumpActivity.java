package edu.lewisu.cs.laurenbonano.writersblock;

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

public class BraindumpActivity extends ListActivity implements LoaderManager.LoaderCallbacks<Cursor> {
	
	private SimpleCursorAdapter adapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.brain_dump);
		setTitle("Braindump");
		getActionBar().setHomeButtonEnabled(true);
		getActionBar().setDisplayHomeAsUpEnabled(true);
		
		String[] from = new String[]{ BraindumpTable.COL_BDUMP, BraindumpTable.COL_ID};
		int[] to = new int[]{R.id.bdump};
		adapter = new SimpleCursorAdapter(this, R.layout.brain_dump_row, null, from, to, 0);
		setListAdapter(adapter);
		getLoaderManager().initLoader(0, null, this);
		registerForContextMenu(getListView());
	}

	@Override
	public Loader<Cursor> onCreateLoader(int id, Bundle args) {
		String[] projection = { BraindumpTable.COL_ID, BraindumpTable.COL_BDUMP};
		CursorLoader cursorLoader = new CursorLoader(this, CProvider.BDUMP_URI, projection, null, null, null);
		return cursorLoader;
	}

	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
		super.onListItemClick(l, v, position, id);
		Intent i = new Intent(this, BraindumpEdit.class);
		Uri bdumpUri = Uri.parse(CProvider.BDUMP_URI + "/" + id);
		i.putExtra("uri", bdumpUri);
		startActivity(i);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch(item.getItemId()){
		case R.id.menu_add:
			Intent i = new Intent(this, BraindumpEdit.class);
			startActivity(i);
			return true;
		}
		return super.onOptionsItemSelected(item);
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
			Uri uri = Uri.parse(CProvider.BDUMP_URI + "/" + info.id);
			getContentResolver().delete(uri,null,null);
			return true;
		}
		return super.onContextItemSelected(item);
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

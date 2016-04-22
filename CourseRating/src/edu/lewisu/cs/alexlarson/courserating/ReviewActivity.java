package edu.lewisu.cs.alexlarson.courserating;

import android.app.ListActivity;
import android.app.LoaderManager;
import android.content.CursorLoader;
import android.content.Loader;
import android.database.Cursor;
import android.os.Bundle;
import android.widget.SimpleCursorAdapter;

public class ReviewActivity extends ListActivity implements LoaderManager.LoaderCallbacks<Cursor> {
	
	private SimpleCursorAdapter adapter;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.reviews);
		String[] from = new String[]{ ReviewTable.COL_COURSE,
				ReviewTable.COL_RATING, ReviewTable.COL_ID};
		int[] to = new int[]{R.id.coursename, R.id.courserating};
		adapter = new SimpleCursorAdapter(this, R.layout.edit_review, null, from, to, 0);
		setListAdapter(adapter);
		getLoaderManager().initLoader(0, null, this);
	}

	@Override
	public Loader<Cursor> onCreateLoader(int id, Bundle args) {
		String[] projection = { ReviewTable.COL_COURSE, ReviewTable.COL_RATING, ReviewTable.COL_ID};
		CursorLoader cursorLoader = new CursorLoader(this, DBProvider.CONTENT_URI,
				projection, null, null, null);
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

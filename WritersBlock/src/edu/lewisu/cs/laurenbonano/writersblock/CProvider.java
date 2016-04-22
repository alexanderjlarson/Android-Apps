package edu.lewisu.cs.laurenbonano.writersblock;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQueryBuilder;
import android.net.Uri;
import android.text.TextUtils;

public class CProvider extends ContentProvider{
	private DbHelper dbHelper;
	private static final String AUTHORITY = "edu.lewisu.cs.laurenbonano.writersblock.CProvider";
	public static final Uri PICS_URI = Uri.parse("content://" + AUTHORITY + "/pics");
	private static final int PICS = 20;
	public static final Uri BDUMP_URI = Uri.parse("content://" + AUTHORITY +"/bdump");
	private static final int BDUMP = 30;
	private static final int SINGLE_BDUMP = 10;
	private static final UriMatcher uriMatcher;
	
	static{
		uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
		uriMatcher.addURI(AUTHORITY, "pics", PICS);
		uriMatcher.addURI(AUTHORITY, "bdump", BDUMP);
		uriMatcher.addURI(AUTHORITY, "bdump/#", SINGLE_BDUMP);
	}
	
	@Override
	public int delete(Uri uri, String selection, String[] selectionArgs) {
		SQLiteDatabase sqlDB = dbHelper.getWritableDatabase();
		int rowsDeleted = 0;
		int uriType = uriMatcher.match(uri);
		switch (uriType){
		case SINGLE_BDUMP:
			String id = uri.getLastPathSegment();
			if(TextUtils.isEmpty(selection)){
				rowsDeleted = sqlDB.delete(BraindumpTable.TABLE_BRAINDUMP, BraindumpTable.COL_ID + "=" + id, null);
			}
			else {
				rowsDeleted = sqlDB.delete(BraindumpTable.TABLE_BRAINDUMP, BraindumpTable.COL_ID + "=" + id + " and " + selection, selectionArgs);
			}
			break;
			default:
				throw new IllegalArgumentException("Unknown URI: " + uri);
		}
		getContext().getContentResolver().notifyChange(uri, null);
		return rowsDeleted;
	}

	@Override
	public String getType(Uri uri) {
		return null;
	}

	@Override
	public Uri insert(Uri uri, ContentValues values) {
		int uriType = uriMatcher.match(uri);
		SQLiteDatabase sqlDB = dbHelper.getWritableDatabase();
		long id = 0;
		switch (uriType){
		case PICS:
			id = sqlDB.insert(PictureTable.TABLE_PICTURE, null, values);
			break;
		case BDUMP:
			id = sqlDB.insert(BraindumpTable.TABLE_BRAINDUMP, null, values);
			break;
			
		default:
			throw new IllegalArgumentException("Unknown URI: " + uri);
			}
		getContext().getContentResolver().notifyChange(uri, null);
		return Uri.parse("tasks/" + id);
	}

	@Override
	public boolean onCreate() {
		dbHelper = new DbHelper(getContext());
		return false;
	}
	
	public Cursor storyQuery(){
		
		Cursor cursor = dbHelper.getReadableDatabase().rawQuery("select verb from words", null);
		
		return cursor;
	}

	@Override
	public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
		SQLiteQueryBuilder queryBuilder = new SQLiteQueryBuilder();
		
		int uriType = uriMatcher.match(uri);
		switch (uriType){
		case PICS:
			queryBuilder.setTables(PictureTable.TABLE_PICTURE);
			Cursor picCursor = dbHelper.getReadableDatabase().rawQuery("select pictures from picture", null);
			return picCursor;
		case BDUMP:
			queryBuilder.setTables(BraindumpTable.TABLE_BRAINDUMP);
			SQLiteDatabase db = dbHelper.getWritableDatabase();
			Cursor cursor = queryBuilder.query(db, projection, selection, selectionArgs, null, null, sortOrder);
			cursor.setNotificationUri(getContext().getContentResolver(), uri);
			return cursor;
		case SINGLE_BDUMP:
			queryBuilder.setTables(BraindumpTable.TABLE_BRAINDUMP);
			queryBuilder.appendWhere(BraindumpTable.COL_ID + "=" + uri.getLastPathSegment());
			SQLiteDatabase db1 = dbHelper.getWritableDatabase();
			Cursor bdump_cursor = queryBuilder.query(db1, projection, selection, selectionArgs, null, null, sortOrder);
			bdump_cursor.setNotificationUri(getContext().getContentResolver(), uri);
			return bdump_cursor;
		default:
			throw new IllegalArgumentException("Unknown URI: " + uri);
		}
		
	}

	@Override
	public int update(Uri uri, ContentValues values, String selection, String[] selectionArgs) {
		SQLiteDatabase sqlDB = dbHelper.getWritableDatabase();
		int rowsUpdated = 0;
		int uriType = uriMatcher.match(uri);
		switch (uriType){
		case BDUMP:
		String id = uri.getLastPathSegment();
		rowsUpdated = sqlDB.update(BraindumpTable.TABLE_BRAINDUMP, values, BraindumpTable.COL_ID + "=" + id, null);
		break;
		case SINGLE_BDUMP:
			String id1 = uri.getLastPathSegment();
			rowsUpdated = sqlDB.update(BraindumpTable.TABLE_BRAINDUMP, values, BraindumpTable.COL_ID + "=" + id1, null);
			break;
		default:
			throw new IllegalArgumentException("Unknown URI: " + uri);
		}
		return 0;
	}
}

package edu.lewisu.cs.alexlarson.courserating;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class DataHelper extends SQLiteOpenHelper {

	private static final String DATABASE_NAME = "data.db";
	private static final int DATABASE_VERSION = 1;
	
	public DataHelper(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}

	@Override
	public void onCreate(SQLiteDatabase database) {
		ReviewTable.onCreate(database);
	}

	@Override
	public void onUpgrade(SQLiteDatabase database, int oldVersion, int newVersion) {
		ReviewTable.onUpgrade(database, oldVersion, newVersion);
	}

}

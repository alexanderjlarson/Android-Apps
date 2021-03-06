package edu.lewisu.cs.laurenbonano.writersblock;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DbHelper extends SQLiteOpenHelper {
	private static final String DATABASE_NAME = "data.db";
	private static final int DATABASE_VERSION = 1;
	
	public DbHelper(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);		
	}

	@Override
	public void onCreate(SQLiteDatabase database) {
		PictureTable.onCreate(database);
		BraindumpTable.onCreate(database);
	}

	@Override
	public void onUpgrade(SQLiteDatabase database, int oldVersion, int newVersion) {
		PictureTable.onUpgrade(database, oldVersion, newVersion);
		BraindumpTable.onUpgrade(database, oldVersion, newVersion);
	}

}

package edu.lewisu.cs.laurenbonano.writersblock;

import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

public class BraindumpTable {

	public static final String TABLE_BRAINDUMP = "braindump";
	public static final String COL_ID = "_id";
	public static final String COL_BDUMP= "bdump";
	public static final String COL_STORY = "story";
	
	private static final String DATABASE_CREATE =
			"create table " + TABLE_BRAINDUMP + " (" +
			COL_ID + " integer primary key autoincrement, " +
			COL_BDUMP + " text not null, " +
			COL_STORY + " text not null);";
	
	public static void onCreate(SQLiteDatabase database){
		database.execSQL(DATABASE_CREATE);
	}
	
	public static void onUpgrade(SQLiteDatabase database, int oldVersion, int newVersion){
		database.execSQL("DROP TABLE IF EXISTS" + TABLE_BRAINDUMP);
		onCreate(database);
	}
	
}

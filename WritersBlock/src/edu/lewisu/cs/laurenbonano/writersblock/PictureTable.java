package edu.lewisu.cs.laurenbonano.writersblock;

import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

public class PictureTable {
	public static final String TABLE_PICTURE = "picture";
	public static final String COL_ID = "_id";
	public static final String COL_PIC = "pictures";
	public static final String COL_STORY = "storypic";
	
	//database creation statement
	private static final String DATABASE_CREATE =
		 "create table " + TABLE_PICTURE +" (" +
		  COL_ID +  " integer primary key autoincrement, " + 
		  COL_PIC + " text not null, " +
		  COL_STORY + " text);";

	//creates the table
	public static void onCreate(SQLiteDatabase database){
		Log.d(PictureTable.class.getName(), DATABASE_CREATE);
		database.execSQL(DATABASE_CREATE);
		database.execSQL("insert into picture(pictures) values('cat_dog')");
		database.execSQL("insert into picture(pictures) values('forest_ride')");
		database.execSQL("insert into picture(pictures) values('ghost')");
		database.execSQL("insert into picture(pictures) values('mystical_walk')");
		database.execSQL("insert into picture(pictures) values('train_ride')");		
	}

	//upgrades the table
	public static void onUpgrade(SQLiteDatabase database, int oldVersion, int newVersion) {
		Log.w(PictureTable.class.getName(), "Upgrading database from version " + oldVersion + " to " + newVersion
				+ ", which will destroy all old data");
		database.execSQL("DROP TABLE IF EXISTS " + TABLE_PICTURE);
		onCreate(database);
	}

}

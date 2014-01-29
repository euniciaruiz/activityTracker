package com.project.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {
	
	private static final String CREATE_TABLE_ACTIVTY_TRACKER =
			"CREATE TABLE " + DatabaseContract.ActivityTable.TABLE_NAME + " (" +
					DatabaseContract.ActivityTable.COLUMN_NAME_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
		    		DatabaseContract.ActivityTable.COLUMN_NAME_TITLE + " TEXT UNIQUE NOT NULL," +
		    		DatabaseContract.ActivityTable.COLUMN_NAME_DESCRIPTION + " TEXT," +
		    		DatabaseContract.ActivityTable.COLUMN_NAME_LOCATION + " TEXT," +
		    		DatabaseContract.ActivityTable.COLUMN_NAME_START_DATE + " DATE," +
		    		DatabaseContract.ActivityTable.COLUMN_NAME_END_DATE + " DATE," +
		    		DatabaseContract.ActivityTable.COLUMN_NAME_START_TIME + " DATETIME," +
		    		DatabaseContract.ActivityTable.COLUMN_NAME_END_TIME + " DATETIME," +
		    		DatabaseContract.ActivityTable.COLUMN_NAME_PRIORITY + " TEXT," +
		    		DatabaseContract.ActivityTable.COLUMN_NAME_ALERT + " TEXT NOT NULL," +
		    		DatabaseContract.ActivityTable.COLUMN_NAME_REPETITION + " TEXT," +
		    		DatabaseContract.ActivityTable.COLUMN_NAME_NOTIFICATION + " TEXT," +
		    " )";

	private static final String DELETE_TABLE_ACTIVTY_TRACKER =
			"DROP TABLE IF EXISTS " + CREATE_TABLE_ACTIVTY_TRACKER;
	
	public DatabaseHelper(Context context, String name, CursorFactory factory,
			int version) {
		super(context, name, factory, version);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub
		
	}
}

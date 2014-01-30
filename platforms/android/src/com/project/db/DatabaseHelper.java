package com.project.db;

import com.project.db.DatabaseContract.ActivityTable;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {
	
	private static final String CREATE_TABLE_ACTIVTY_TRACKER =
		"CREATE TABLE " + DatabaseContract.ActivityTable.TABLE_NAME + "(" +
			ActivityTable.COLUMN_NAME_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
	    		ActivityTable.COLUMN_NAME_TITLE + " TEXT UNIQUE NOT NULL," +
	    		ActivityTable.COLUMN_NAME_DESCRIPTION + " TEXT," +
	    		ActivityTable.COLUMN_NAME_LOCATION + " TEXT," +
	    		ActivityTable.COLUMN_NAME_START_DATE + " DATE," +
	    		ActivityTable.COLUMN_NAME_END_DATE + " DATE," +
	    		ActivityTable.COLUMN_NAME_START_TIME + " DATETIME," +
		    	ActivityTable.COLUMN_NAME_END_TIME + " DATETIME," +
		    	ActivityTable.COLUMN_NAME_PRIORITY + " TEXT," +
		    	ActivityTable.COLUMN_NAME_ALERT + " TEXT NOT NULL," +
		    	ActivityTable.COLUMN_NAME_REPETITION + " TEXT," +
		    	ActivityTable.COLUMN_NAME_NOTIFICATION + " TEXT);";

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
	
	/**
	 * All CRUD(Create, Read, Update, Delete) Operations
	 */

	// Adding new activity
	public void createActivity(ActivityModel activity) {
		SQLiteDatabase db = this.getWritableDatabase();

		ContentValues values = new ContentValues();
		values.put(ActivityTable.COLUMN_NAME_TITLE, activity.getTitle());
		values.put(ActivityTable.COLUMN_NAME_DESCRIPTION, activity.getDescription());
		values.put(ActivityTable.COLUMN_NAME_LOCATION, activity.getLocation());
		values.put(ActivityTable.COLUMN_NAME_START_DATE, activity.getStart_date());
		values.put(ActivityTable.COLUMN_NAME_END_DATE, activity.getEnd_date());
		values.put(ActivityTable.COLUMN_NAME_START_TIME, activity.getStart_time());
		values.put(ActivityTable.COLUMN_NAME_END_TIME, activity.getEnd_time());
		values.put(ActivityTable.COLUMN_NAME_PRIORITY, activity.getPriority());
		values.put(ActivityTable.COLUMN_NAME_ALERT, activity.getAlert());
		values.put(ActivityTable.COLUMN_NAME_REPETITION, activity.getRepetition());
		values.put(ActivityTable.COLUMN_NAME_NOTIFICATION, activity.getNotification());

		// insert row
		db.insert(CREATE_TABLE_ACTIVTY_TRACKER, null, values);
		db.close();
	}
}

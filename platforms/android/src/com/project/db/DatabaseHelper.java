package com.project.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {

	private static final String KEY_TITLE = title;
	private static final String KEY_DESCRIPTION = description;
	private static final String KEY_LOCATION = location;
	private static final String KEY_START_DATE = startDate;
	private static final String KEY_END_DATE = endDate;
	private static final String KEY_START_TIME = startTime;
	private static final String KEY_END_TIME = endTime;
	private static final String KEY_PRIORITY = priority;
	private static final String KEY_ALERT = alert;
	private static final String KEY_REPETITION = repetition;
	private static final String KEY_NOTIFICATION = notification;
	
	private static final String CREATE_TABLE_ACTIVTY_TRACKER;
	
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

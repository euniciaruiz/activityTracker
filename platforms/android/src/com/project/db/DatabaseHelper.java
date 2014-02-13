package com.project.db;

import java.util.ArrayList;
import java.util.List;

import com.project.db.DatabaseContract.ActivityTable;
import com.project.model.ActivityModel;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DatabaseHelper extends SQLiteOpenHelper {
	// If you change the database schema, you must increment the database version.
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "activityTracker.db";

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
	
	public DatabaseHelper(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		db.execSQL(CREATE_TABLE_ACTIVTY_TRACKER);		
	}
	

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// This database is only a cache for online data, so its upgrade policy is
        // to simply to discard the data and start over
        db.execSQL(DELETE_TABLE_ACTIVTY_TRACKER);
        onCreate(db);
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

		db.insert(ActivityTable.TABLE_NAME, null, values);
		db.close();
	}
	
	public List<String> readActivityTitle(){
		SQLiteDatabase db = this.getReadableDatabase();
		List<String> titles = new ArrayList<String>();
		String[] projection = {ActivityTable.COLUMN_NAME_TITLE};

		Cursor c = db.query(
				ActivityTable.TABLE_NAME,  			 // The table to query
				projection,     					 // The columns to return
				null,                                // The columns for the WHERE clause
				null,                           	 // The values for the WHERE clause
				null,                                // don't group the rows
				null,                                // don't filter by row groups
				null								 // The sort order
				);
		
		while(c.moveToNext()){
			String title = c.getString(0);
			int count = c.getCount();
			Log.i("count:", "count is: "+count);
			titles.add(title);
		}
		return titles;
	}
	
	public String getActivityTitle(long id){
		SQLiteDatabase db = this.getReadableDatabase();
		
		String title = null;
		String i = String.valueOf(id);
		Log.v("value of", "value"+i);
		String ids[] = {i};
		Log.v("ids", "ids"+ids);
		String[] projection = {ActivityTable.COLUMN_NAME_TITLE};
		Cursor c = db.query(ActivityTable.TABLE_NAME, projection, ActivityTable.COLUMN_NAME_ID + "=" + id, null, null, null, null);
		while(c.moveToNext()){
			title = c.getString(0);
		}
		return title;
	}
	
	public ActivityModel getActivityDetails(String activityTitle){
		SQLiteDatabase db = this.getReadableDatabase();
		ActivityModel activity = new ActivityModel();
		String id[] = {activityTitle};
		String[] projection = {ActivityTable.COLUMN_NAME_ID, ActivityTable.COLUMN_NAME_TITLE, 
								ActivityTable.COLUMN_NAME_DESCRIPTION, ActivityTable.COLUMN_NAME_LOCATION,
								ActivityTable.COLUMN_NAME_START_DATE, ActivityTable.COLUMN_NAME_END_DATE,
								ActivityTable.COLUMN_NAME_START_TIME, ActivityTable.COLUMN_NAME_END_TIME,
								ActivityTable.COLUMN_NAME_PRIORITY, ActivityTable.COLUMN_NAME_ALERT,
								ActivityTable.COLUMN_NAME_REPETITION, ActivityTable.COLUMN_NAME_NOTIFICATION};

		Cursor cursor = db.query("activity", projection, "title=?", id, null, null, null);
		
		cursor.moveToFirst();
		activity.setId(cursor.getInt(cursor.getColumnIndexOrThrow(ActivityTable.COLUMN_NAME_ID)));
		activity.setTitle(cursor.getString(cursor.getColumnIndexOrThrow(ActivityTable.COLUMN_NAME_TITLE)));
		activity.setDescription(cursor.getString(cursor.getColumnIndexOrThrow(ActivityTable.COLUMN_NAME_DESCRIPTION)));
		activity.setLocation(cursor.getString(cursor.getColumnIndexOrThrow(ActivityTable.COLUMN_NAME_LOCATION)));
		activity.setStart_date(cursor.getString(cursor.getColumnIndexOrThrow(ActivityTable.COLUMN_NAME_START_DATE)));
		activity.setEnd_date(cursor.getString(cursor.getColumnIndexOrThrow(ActivityTable.COLUMN_NAME_END_DATE)));
		activity.setStart_time(cursor.getString(cursor.getColumnIndexOrThrow(ActivityTable.COLUMN_NAME_START_TIME)));
		activity.setEnd_time(cursor.getString(cursor.getColumnIndexOrThrow(ActivityTable.COLUMN_NAME_END_TIME)));
		activity.setPriority(cursor.getString(cursor.getColumnIndexOrThrow(ActivityTable.COLUMN_NAME_PRIORITY)));
		activity.setAlert(cursor.getString(cursor.getColumnIndexOrThrow(ActivityTable.COLUMN_NAME_ALERT)));
		activity.setRepetition(cursor.getString(cursor.getColumnIndexOrThrow(ActivityTable.COLUMN_NAME_REPETITION)));
		activity.setNotification(cursor.getString(cursor.getColumnIndexOrThrow(ActivityTable.COLUMN_NAME_NOTIFICATION)));
		
		return activity;
	}
	
	public String getActivityLocation(String title){
		String loc = null;
		String titles[] = {title};
		SQLiteDatabase db = this.getReadableDatabase();
		String[] projection = { ActivityTable.COLUMN_NAME_LOCATION};
		Cursor c = db.query(ActivityTable.TABLE_NAME, projection, "title=?", titles, null, null, null);
		while(c.moveToNext()){
			
			loc = c.getString(0);
			
		}
		return loc;
	}
	
	public String getStartDate(String title){
		String startDate = null;
		String titles[] = {title};
		SQLiteDatabase db = this.getReadableDatabase();
		String[] projection = {ActivityTable.COLUMN_NAME_START_DATE};
		Cursor c = db.query(ActivityTable.TABLE_NAME, projection, "title=?", titles, null, null, null);
		while(c.moveToNext()){
			
			startDate = c.getString(0);
			
		}
		return startDate;
	}

	public String getEndDate(String title){

		String endDate = null;
		String titles[] = {title};
		SQLiteDatabase db = this.getReadableDatabase();
		String[] projection = {ActivityTable.COLUMN_NAME_END_DATE};
		Cursor c = db.query(ActivityTable.TABLE_NAME, projection, "title=?", titles, null, null, null);
		while(c.moveToNext()){
			
			endDate = c.getString(0);
			
		}
		return endDate;
	}
	
	public String getStartTime(String title){
		String startTime = null;
		String titles[] = {title};
		SQLiteDatabase db = this.getReadableDatabase();
		String[] projection = {ActivityTable.COLUMN_NAME_START_TIME};
		Cursor c = db.query(ActivityTable.TABLE_NAME, projection, "title=?", titles, null, null, null);
		while(c.moveToNext()){
			
			startTime = c.getString(0);
			
		}
		return startTime;		
	}
	
	public String endTime(String title){
		String endTime = null;
		String titles[] = {title};
		SQLiteDatabase db = this.getReadableDatabase();
		String[] projection = {ActivityTable.COLUMN_NAME_END_TIME};
		Cursor c = db.query(ActivityTable.TABLE_NAME, projection, "title=?", titles, null, null, null);
		while(c.moveToNext()){
			
			endTime = c.getString(0);
			
		}
		return endTime;
	
	
	
	public int updateActivity(ActivityModel activity){
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
		
		return db.update(ActivityTable.TABLE_NAME, values, ActivityTable.COLUMN_NAME_ID + " = ?", 
				new String[] { String.valueOf(activity.getId()) } );
	}
	
	public void deleteActivity(String activityTitle){
		SQLiteDatabase db = this.getWritableDatabase();
		
		db.delete(ActivityTable.TABLE_NAME, ActivityTable.COLUMN_NAME_TITLE + " = ?", 
				new String[] {activityTitle});
		db.close();
		
	}
}

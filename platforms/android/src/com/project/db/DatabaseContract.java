package com.project.db;

import android.provider.BaseColumns;

public final class DatabaseContract {
	// To prevent someone from accidentally instantiating the contract class, give it an empty constructor.
	public DatabaseContract() {}
	
	/* Inner class that defines the table contents */
	public static abstract class ActivityTable implements BaseColumns {
		public static final String TABLE_NAME = "activity";
		public static final String COLUMN_NAME_ID = "id";
		public static final String COLUMN_NAME_TITLE = "title";
		public static final String COLUMN_NAME_DESCRIPTION = "description";
		public static final String COLUMN_NAME_LOCATION = "location";
		public static final String COLUMN_NAME_START_DATE = "startDate";
		public static final String COLUMN_NAME_END_DATE = "endDate";
		public static final String COLUMN_NAME_START_TIME = "startTime";
		public static final String COLUMN_NAME_END_TIME = "endTime";
		public static final String COLUMN_NAME_PRIORITY = "priority";
		public static final String COLUMN_NAME_ALERT = "alert";
		public static final String COLUMN_NAME_REPETITION = "repetition";
		public static final String COLUMN_NAME_NOTIFICATION = "notification";
	}
}

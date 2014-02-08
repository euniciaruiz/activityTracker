package com.project.activityTracker;

import java.util.List;


import com.project.db.DatabaseHelper;
import com.project.model.ActivityModel;

import android.app.Activity;
import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class ShowActivity extends Activity{
	DatabaseHelper db;
	
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_show);
		db = new DatabaseHelper(this);
		
		Bundle extras = getIntent().getExtras();
		long id = extras.getLong("id");
		id++;
		Log.v("id", "id from intent: "+id);
		String t = db.getActivityTitle(id);
		Log.v("title", "activity title:"+t);
		String desc = db.getActivityDetails(t);
		Log.v("desc", "activity details: "+desc);
		String startD = db.getStartDate(t);
		TextView ti = (TextView)  findViewById(R.id.title);
		ti.setText(t);
		TextView des = (TextView)  findViewById(R.id.desc);
		des.setText(desc);
		TextView startDate = (TextView) findViewById(R.id.sdate);
		startDate.setText(startD);
	}
}

package com.project.activityTracker;

import java.util.List;


import com.project.db.DatabaseHelper;
import com.project.model.ActivityModel;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class ShowActivity extends Activity{
	DatabaseHelper db;
	String activityTitle;
	
	
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
		
		Button btnDelete=(Button) findViewById(R.id.btnDelete);	
		
		btnDelete.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				deleteDialog("Are you sure?");
			}
		});
	}
	
	public void deleteDialog(String msg){
		AlertDialog.Builder alertBuilder = new AlertDialog.Builder(this);
        alertBuilder.setTitle("Message Box");
        alertBuilder.setMessage(msg);      
        alertBuilder.setPositiveButton("Ok", new DialogInterface.OnClickListener(){
        	public void onClick(DialogInterface arg0, int arg1){
        		try{
        			db = new DatabaseHelper(ShowActivity.this);
        			db.deleteActivity(activityTitle);
        			alertMessage("Deleted Successfully");
        			
        			Intent intent = new Intent(getApplicationContext(), ActivityTracker.class);
        			startActivity(intent);
        		}catch(Exception e){
        			e.printStackTrace();
        			alertMessage("Problem in Deletion");
        		}
        	}
        });
        alertBuilder.setNegativeButton("Cancel", null);
        alertBuilder.show();
	}
			
	
	public void alertMessage(String msg){
	    AlertDialog.Builder alertBuilder = new AlertDialog.Builder(this);
	    alertBuilder.setTitle("Message Box");
	    alertBuilder.setMessage(msg);       
	    alertBuilder.setCancelable(false);       
	    alertBuilder.setPositiveButton("Ok",null);
	    alertBuilder.show();
	}
}

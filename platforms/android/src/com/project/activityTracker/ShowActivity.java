package com.project.activityTracker;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.project.db.DatabaseHelper;
import com.project.model.ActivityModel;

public class ShowActivity extends Activity {
	DatabaseHelper db;
	String activityTitle;
	
	@SuppressLint("NewApi")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_show);
		
		db = new DatabaseHelper(this);
		Intent getActivityTitle = getIntent();
		activityTitle = getActivityTitle.getStringExtra("activityTitle");
		
		ActivityModel activity = db.getActivityDetails(activityTitle);
		
		TextView title = (TextView) findViewById(R.id.title);
		TextView description = (TextView) findViewById(R.id.description);
		TextView location = (TextView) findViewById(R.id.location);
		TextView startDate = (TextView) findViewById(R.id.startDate);
		TextView endDate = (TextView) findViewById(R.id.endDate);
		TextView startTime = (TextView) findViewById(R.id.startTime);
		TextView endTime = (TextView) findViewById(R.id.endTime);
		TextView priority = (TextView) findViewById(R.id.priority);
		TextView alert = (TextView) findViewById(R.id.alert);
		TextView repetition = (TextView) findViewById(R.id.repetition);
		TextView notification = (TextView) findViewById(R.id.notification);
		Button btnEdit = (Button) findViewById(R.id.edit_button);
		Button btnDelete = (Button) findViewById(R.id.delete_button);

		title.setText("Title: " + activity.getTitle());
		description.setText("Description: " + activity.getDescription());
		location.setText(activity.getLocation());
		startDate.setText("Start Date: " + activity.getStart_date());
		endDate.setText("End Date: " + activity.getEnd_date());
		startTime.setText("Start Date: " + activity.getStart_time());
		endTime.setText("End Time: " + activity.getEnd_time());
		priority.setText("Priority: " + activity.getPriority());
		alert.setText("Alert: " + activity.getAlert());
		repetition.setText("Repetition: " + activity.getRepetition());
		notification.setText("Notification: " + activity.getNotification());
		

		btnEdit.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				editActivity();
			}
		});
	    btnDelete.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				deleteDialog("Are you sure?");
			}
		});
	}

	public void editActivity() {
		Intent intent = new Intent(getApplicationContext(), EditActivity.class);
		intent.putExtra("activityTitle", activityTitle);
		startActivity(intent);
	}
	
	public void deleteDialog(String msg){
        AlertDialog.Builder alertBuilder = new AlertDialog.Builder(this);
        alertBuilder.setTitle("Message Box");
        alertBuilder.setMessage(msg);      
        alertBuilder.setPositiveButton("Ok", new DialogInterface.OnClickListener(){
        	public void onClick(DialogInterface arg0, int arg1) {
        		try{
        			db = new DatabaseHelper(ShowActivity.this);
        			db.deleteActivity(activityTitle);   
        			
        			//alertMessage("Deleted Successfully");
        			
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
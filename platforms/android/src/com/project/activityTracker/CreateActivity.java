package com.project.activityTracker;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.project.db.DatabaseHelper;
import com.project.model.ActivityModel;

public class CreateActivity extends Activity {
	DatabaseHelper db;
	ActivityModel activity;
	EditText inputTitle;
	EditText inputDescription;
	EditText inputLocation;
	EditText inputStartDate;
	EditText inputEndDate;
	EditText inputStartTime;
	EditText inputEndTime;
	EditText inputPriority;
	EditText inputAlert;
	EditText inputRepetition;
	EditText inputNotification;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_create);
		
		//Show the Up button in the action bar.
		setupActionBar();
		
		inputTitle = (EditText) findViewById(R.id.inputTitle);
		Button btnCreateProduct = (Button) findViewById(R.id.btnCreateActivity);

		btnCreateProduct.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				// creating new product in background thread
				//new CreateNewActivity().execute();
							
				db = new DatabaseHelper(getApplicationContext());
				activity = new ActivityModel();
				Log.d("Insert: ", "Inserting ..");
				activity.setTitle(inputTitle.getText().toString());
		        activity.setDescription(inputTitle.getText().toString());
		        activity.setLocation(inputTitle.getText().toString());
		        activity.setStart_date(inputTitle.getText().toString());
		        activity.setEnd_date(inputTitle.getText().toString());
		        activity.setStart_time(inputTitle.getText().toString());
		        activity.setEnd_time(inputTitle.getText().toString());
		        activity.setPriority(inputTitle.getText().toString());
		        activity.setAlert(inputTitle.getText().toString());
		        activity.setRepetition(inputTitle.getText().toString());
		        activity.setNotification(inputTitle.getText().toString());
				
				db.createActivity(activity);
				
				Intent i = new Intent(getApplicationContext(), ActivityTracker.class);
				startActivity(i);
			
				finish();
			}
		});
	}

	/**
	 * Set up the {@link android.app.ActionBar}, if the API is available.
	 */
	@TargetApi(Build.VERSION_CODES.HONEYCOMB)
	private void setupActionBar() {
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
			getActionBar().setDisplayHomeAsUpEnabled(true);
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.create, menu);
		return true;
	}
}

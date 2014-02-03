package com.project.activityTracker;

import java.util.Calendar;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;

import com.project.db.DatabaseHelper;
import com.project.model.ActivityModel;

public class CreateActivity extends Activity implements OnClickListener {
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
	
	private ImageButton startDateCalendar;
	private ImageButton endDateCalendar;
	private Calendar cal;
	private int day;
	private int month;
	private int year;
	private EditText et;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_create);
		
		//Show the Up button in the action bar.
		setupActionBar();
		
		inputTitle = (EditText) findViewById(R.id.inputTitle);
		inputDescription = (EditText) findViewById(R.id.inputDescription);
		inputLocation = (EditText) findViewById(R.id.inputLocation);
		inputStartDate = (EditText) findViewById(R.id.inputStartDate);
		startDateCalendar = (ImageButton) findViewById(R.id.calendarImageButton1);
		inputEndDate = (EditText) findViewById(R.id.inputEndDate);
		endDateCalendar = (ImageButton) findViewById(R.id.calendarImageButton2);
		inputStartTime = (EditText) findViewById(R.id.inputStartTime);
		inputEndTime = (EditText) findViewById(R.id.inputEndTime);
		inputPriority = (EditText) findViewById(R.id.inputPriority);
		inputAlert = (EditText) findViewById(R.id.inputAlert);
		inputRepetition = (EditText) findViewById(R.id.inputRepetition);
		inputNotification = (EditText) findViewById(R.id.inputNotification);

		cal = Calendar.getInstance();
		day = cal.get(Calendar.DAY_OF_MONTH);
		month = cal.get(Calendar.MONTH);
		year = cal.get(Calendar.YEAR);
		startDateCalendar.setOnClickListener(this);
		endDateCalendar.setOnClickListener(this);
		
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
		        activity.setDescription(inputDescription.getText().toString());
		        activity.setLocation(inputLocation.getText().toString());
		        activity.setStart_date(inputStartDate.getText().toString());
		        activity.setEnd_date(inputEndDate.getText().toString());
		        activity.setStart_time(inputStartTime.getText().toString());
		        activity.setEnd_time(inputEndTime.getText().toString());
		        activity.setPriority(inputPriority.getText().toString());
		        activity.setAlert(inputAlert.getText().toString());
		        activity.setRepetition(inputRepetition.getText().toString());
		        activity.setNotification(inputNotification.getText().toString());
				
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
	
	@SuppressWarnings("deprecation")
	@Override
	public void onClick(View v) {
		showDialog(0);
	}

	@Override
	protected Dialog onCreateDialog(int id) {
		return new DatePickerDialog(this, datePickerListener, year, month, day);
	}

	private DatePickerDialog.OnDateSetListener datePickerListener = new DatePickerDialog.OnDateSetListener() {
		 public void onDateSet(DatePicker view, int selectedYear, int selectedMonth, int selectedDay) {
			 et.setText(selectedDay + " / " + (selectedMonth + 1) + " / " + selectedYear);
		 }
	 };
}

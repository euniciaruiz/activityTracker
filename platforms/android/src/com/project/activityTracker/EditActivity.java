package com.project.activityTracker;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TimePicker;

import com.project.db.DatabaseHelper;
import com.project.model.ActivityModel;

public class EditActivity extends Activity{
	private DatabaseHelper db;
	private ActivityModel activity;
	private EditText inputTitle;
	private EditText inputDescription;
	private EditText inputLocation;
	private EditText inputStartDate;
	private EditText inputEndDate;
	private EditText inputStartTime;
	private EditText inputEndTime;
	private Spinner inputPriority;
	private Spinner inputAlert;
	private Spinner inputRepetition;
	private Spinner inputNotification;
	private Button btnEditProduct;
	
	private ImageButton startDateCalendar;
	private ImageButton endDateCalendar;
	private ImageButton startTimePicker;
	private ImageButton endTimePicker;
	
	private final Calendar c = Calendar.getInstance();
	
	// Use the current date as the default date in the picker
	private int year = c.get(Calendar.YEAR);
	private int month = c.get(Calendar.MONTH);
	private int day = c.get(Calendar.DAY_OF_MONTH);
	
	// Use the current time as the default values for the picker
	private int hour = c.get(Calendar.HOUR_OF_DAY);
	private int minute = c.get(Calendar.MINUTE);
	
	private static final int START_DATE_DIALOG_ID = 0;
	private static final int END_DATE_DIALOG_ID = 1;
	private static final int START_TIME_DIALOG_ID = 2;
	private static final int END_TIME_DIALOG_ID = 3;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_edit);
		
		inputTitle = (EditText) findViewById(R.id.inputTitle);
		inputDescription = (EditText) findViewById(R.id.inputDescription);
		inputLocation = (EditText) findViewById(R.id.inputLocation);
		inputStartDate = (EditText) findViewById(R.id.inputStartDate);
		startDateCalendar = (ImageButton) findViewById(R.id.calendarImageButton1);
		inputEndDate = (EditText) findViewById(R.id.inputEndDate);
		endDateCalendar = (ImageButton) findViewById(R.id.calendarImageButton2);
		inputStartTime = (EditText) findViewById(R.id.inputStartTime);
		startTimePicker = (ImageButton) findViewById(R.id.startTimeImageButton);
		inputEndTime = (EditText) findViewById(R.id.inputEndTime);
		endTimePicker = (ImageButton) findViewById(R.id.endTimeImageButton);
		inputPriority = (Spinner) findViewById(R.id.priority_spinner);
		inputAlert = (Spinner) findViewById(R.id.alert_spinner);
		inputRepetition = (Spinner) findViewById(R.id.repetition_spinner);
		inputNotification = (Spinner) findViewById(R.id.notification_spinner);
		btnEditProduct = (Button) findViewById(R.id.btnEditActivity);
		
		Intent intent = getIntent();
		String activityTitle = intent.getStringExtra("activityTitle");
		
		startDateCalendar.setOnClickListener(new View.OnClickListener() {
			@SuppressWarnings("deprecation")
			@Override
			public void onClick(View v) {
				showDialog(START_DATE_DIALOG_ID);
			}
		});
		
		endDateCalendar.setOnClickListener(new View.OnClickListener() {
			@SuppressWarnings("deprecation")
			@Override
			public void onClick(View v) {
				showDialog(END_DATE_DIALOG_ID);
			}
		});
		
		startTimePicker.setOnClickListener(new View.OnClickListener() {
			@SuppressWarnings("deprecation")
			@Override
			public void onClick(View v) {
				showDialog(START_TIME_DIALOG_ID);
			}
		});
		
		endTimePicker.setOnClickListener(new View.OnClickListener() {
			@SuppressWarnings("deprecation")
			@Override
			public void onClick(View v) {
				showDialog(END_TIME_DIALOG_ID);
			}
		});
		
		addItemsOnPrioritySpinner();
		addItemsOnAlertSpinner();
		addItemsOnRepetitionSpinner();
		addItemsOnNotificationSpinner();
		
		btnEditProduct.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				
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
		        activity.setPriority(inputPriority.getSelectedItem().toString());
		        activity.setAlert(inputAlert.getSelectedItem().toString());
		        activity.setRepetition(inputRepetition.getSelectedItem().toString());
		        activity.setNotification(inputNotification.getSelectedItem().toString());
				
				db.updateActivity(activity);
				
				Intent i = new Intent(getApplicationContext(), ActivityTracker.class);
				startActivity(i);
			
				finish();
			}
		});
	}
	
	protected Dialog onCreateDialog(int id) {
		switch (id) {
	    case START_DATE_DIALOG_ID:
			return new DatePickerDialog(this, startDatePickerListener, year, month, day);
	    case END_DATE_DIALOG_ID:
			return new DatePickerDialog(this, endDatePickerListener, year, month, day);
	    case START_TIME_DIALOG_ID:
			return new TimePickerDialog(this, startTimePickerListener, hour, minute, false);
		case END_TIME_DIALOG_ID:
			return new TimePickerDialog(this, endTimePickerListener, hour, minute, false);
		}
		return null;
	}
	
	private DatePickerDialog.OnDateSetListener startDatePickerListener = new DatePickerDialog.OnDateSetListener() {
        public void onDateSet(DatePicker view, int year, int month, int day) {
        	inputStartDate.setText(day + " / " + (month + 1) + " / " + year);
        }
    };
    
    private DatePickerDialog.OnDateSetListener endDatePickerListener = new DatePickerDialog.OnDateSetListener() {
        public void onDateSet(DatePicker view, int year, int month, int day) {
        	inputEndDate.setText(day + " / " + (month + 1) + " / " + year);
        }
    };

    private TimePickerDialog.OnTimeSetListener startTimePickerListener = new TimePickerDialog.OnTimeSetListener() {
        public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
        	int hour;
        	String am_pm;
        	
        	if (hourOfDay > 12) {
        	    hour = hourOfDay - 12;
        	    am_pm = "PM";
        	} else {
        	    hour = hourOfDay;
        	    am_pm = "AM";
        	}
        	inputStartTime.setText(hour + " : " + minute + " " + am_pm);
        }
    };
    
    private TimePickerDialog.OnTimeSetListener endTimePickerListener = new TimePickerDialog.OnTimeSetListener() {
        public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
        	int hour;
        	String am_pm;
        	
        	if (hourOfDay > 12) {
        	    hour = hourOfDay - 12;
        	    am_pm = "PM";
        	} else {
        	    hour = hourOfDay;
        	    am_pm = "AM";
        	}
        	inputEndTime.setText(hour + " : " + minute + " " + am_pm);
        }
    };
    
    // add items into spinner dynamically
    public void addItemsOnPrioritySpinner() {
    	List<String> list = new ArrayList<String>();
    	ArrayAdapter<String> dataAdapter;
    	
    	list.add("High");
    	list.add("Medium");
    	list.add("Low");
    	
    	inputPriority = (Spinner) findViewById(R.id.priority_spinner);
    	dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, list);
    	dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
    	inputPriority.setAdapter(dataAdapter);
     }
    
    public void addItemsOnAlertSpinner() {
    	List<String> list = new ArrayList<String>();
    	ArrayAdapter<String> dataAdapter;
    	
    	list.add("No");
    	list.add("Yes");
    	    	
    	inputAlert = (Spinner) findViewById(R.id.alert_spinner);
    	dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, list);
    	dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
    	inputAlert.setAdapter(dataAdapter);
     }
    
    public void addItemsOnRepetitionSpinner() {
    	List<String> list = new ArrayList<String>();
    	ArrayAdapter<String> dataAdapter;
    	
    	list.add("never");
    	list.add("daily");
    	list.add("weekly");
    	list.add("monthly");
    	list.add("yearly");
    	
    	inputRepetition = (Spinner) findViewById(R.id.repetition_spinner);
    	dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, list);
    	dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
    	inputRepetition.setAdapter(dataAdapter);
     }
    
    public void addItemsOnNotificationSpinner() {
    	List<String> list = new ArrayList<String>();
    	ArrayAdapter<String> dataAdapter;
    	
    	list.add("High");
    	list.add("Medium");
    	list.add("Low");
    	
    	inputNotification = (Spinner) findViewById(R.id.notification_spinner);
    	dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, list);
    	dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
    	inputNotification.setAdapter(dataAdapter);
     }
}

package com.project.activityTracker;

import java.util.List;

import com.project.db.DatabaseHelper;
import com.project.model.ActivityModel;

import android.app.ListActivity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;

public class ShowActivity extends ListActivity implements OnClickListener{
	DatabaseHelper db;
	List<String> list;
    ArrayAdapter<String> ad;
	ActivityModel act;

	protected void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		db = new DatabaseHelper(this);
		db.getReadableDatabase();
		
		
		
	
		list = db.readActivityTitle();
		ad = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, list);
		setListAdapter(ad);
		db.close();
	
		
	}

	@SuppressWarnings("deprecation")
	@Override
	public void onClick(View arg0) {
		showDialog(0);
		
	}
}

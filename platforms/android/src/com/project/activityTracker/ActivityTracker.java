/*
       Licensed to the Apache Software Foundation (ASF) under one
       or more contributor license agreements.  See the NOTICE file
       distributed with this work for additional information
       regarding copyright ownership.  The ASF licenses this file
       to you under the Apache License, Version 2.0 (the
       "License"); you may not use this file except in compliance
       with the License.  You may obtain a copy of the License at

         http://www.apache.org/licenses/LICENSE-2.0

       Unless required by applicable law or agreed to in writing,
       software distributed under the License is distributed on an
       "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
       KIND, either express or implied.  See the License for the
       specific language governing permissions and limitations
       under the License.
 */

package com.project.activityTracker;

import java.util.List;

import org.apache.cordova.CordovaActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import com.project.db.DatabaseHelper;

public class ActivityTracker extends CordovaActivity 
{
	Button showBtn;
	Intent showActivity;
	private DatabaseHelper datasource;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_screen);
        
        datasource = new DatabaseHelper(this);
        
		List<String> value = datasource.readActivityTitle();

		ListView activityList = (ListView) findViewById(R.id.listView1);
		
		// Use the SimpleCursorAdapter to show the
		// elements in a ListView
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, value);
		activityList.setAdapter(adapter);
    }
   
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
    	// Inflate the menu items for use in the action bar
    	MenuInflater inflater = getMenuInflater();
    	inflater.inflate(R.menu.main_screen_actions, menu);
    	return super.onCreateOptionsMenu(menu);
    }

    public void addActivity(View view) {
    	Intent intent = new Intent(this, CreateActivity.class);
    	startActivity(intent);
    }
    
    public void showActivity(View v){
    	Intent intent = new Intent(this, ShowActivity.class);
    	startActivity(intent);
    }
}

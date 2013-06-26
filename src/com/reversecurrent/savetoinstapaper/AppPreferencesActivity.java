package com.reversecurrent.savetoinstapaper;

import android.os.Bundle;
import android.preference.PreferenceActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

public class AppPreferencesActivity extends PreferenceActivity
{
	@Override	
	public void onCreate(Bundle savedInstanceState) {    
	    super.onCreate(savedInstanceState);       
	    addPreferencesFromResource(R.xml.preferences);       
	}
	
	@Override
	public void onSaveInstanceState(Bundle outState)
	{
		//UserMessage.ShowMessage(getApplicationContext(), "");
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
	    MenuInflater inflater = getMenuInflater();
	    inflater.inflate(R.menu.appmenu, menu);
	    return true;
	}
	
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
	    // Handle item selection
	    switch (item.getItemId()) {
	        
	       
	        default:
	            return super.onOptionsItemSelected(item);
	    }
	}

	

}

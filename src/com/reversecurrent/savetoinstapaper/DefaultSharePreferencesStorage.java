package com.reversecurrent.savetoinstapaper;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.widget.Toast;

public class DefaultSharePreferencesStorage implements IPersistentStorage {

	private final String DEFAULTVALUE_STRING = "";
	
	

	
	@Override
	public Credentials GetModel(Context context)
	{
		Credentials model = new Credentials();
		try
		{

			// This automatically creates the xml file if it does not exist and then stores in the same. 
			SharedPreferences sharedPrefs =  PreferenceManager.getDefaultSharedPreferences(context);
		
			//if(sharedPrefs.contains(ACTIVITYNAME_KEY))
			//{
				
				model.UserName =  sharedPrefs.getString(context.getResources().getString(R.string.usernamekey),DEFAULTVALUE_STRING );
				model.PlainPassword = sharedPrefs.getString(context.getResources().getString(R.string.passwordkey), DEFAULTVALUE_STRING);
				
				return model;
			//}
			

			
		}
		catch(Exception exObj)
		{
			Toast.makeText((Context) context, exObj.getMessage(), Toast.LENGTH_LONG).show();
			return null;
		}
	}


	@Override
	public Credentials GetModelActivity(Activity activity) {
		// TODO Auto-generated method stub
		
		return null;
	}

	@Override
	public boolean Save(Context context, Credentials model) {
		// TODO Auto-generated method stub
		return true;
	}

}

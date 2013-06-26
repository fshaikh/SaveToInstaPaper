package com.reversecurrent.savetoinstapaper;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.widget.Toast;

public class SharedPreferencesStorage implements IPersistentStorage {

	private final String SHAREDPREFS_NAME = "SaveToInstaPaperPrefs";
	private final String DEFAULTVALUE_STRING = "";
	private final String ACTIVITYNAME_KEY = "ActivityNameKey";
	
	// define the keys
	private final String USERNAME_KEY = "username";
	private final String PLAINPASSWORD_KEY = "plainpassword";
	private final String ENCPASSWORD_KEY = "encpassword";
	private final String SHOWPASSWORD_KEY = "showpassword";
	private final String CHECKCREDSONSAVE_KEY = "checkcredsonsave";
	
	@Override
	public Credentials GetModel(Context context)
	{
		Credentials model = new Credentials();
		try
		{

			// This automatically creates the xml file if it does not exist and then stores in the same. 
			SharedPreferences sharedPrefs =  context.getSharedPreferences(SHAREDPREFS_NAME, 0);
		
			if(sharedPrefs.contains(ACTIVITYNAME_KEY))
			{
				model.UserName =  sharedPrefs.getString(USERNAME_KEY,DEFAULTVALUE_STRING );
				model.PlainPassword = sharedPrefs.getString(PLAINPASSWORD_KEY, DEFAULTVALUE_STRING);
				model.EncPassword = sharedPrefs.getString(ENCPASSWORD_KEY, DEFAULTVALUE_STRING);
				model.ShowPassword = sharedPrefs.getBoolean(SHOWPASSWORD_KEY, false);
				return model;
			}
			else
			{
				return null;
			}

			
		}
		catch(Exception exObj)
		{
			Toast.makeText((Context) context, exObj.getMessage(), Toast.LENGTH_LONG).show();
			return null;
		}
	}

	@Override
	public Credentials GetModelActivity(Activity activity)
	{
		Credentials model = new Credentials();
		try
		{

			// This automatically creates the xml file if it does not exist and then stores in the same. 
			SharedPreferences sharedPrefs =  activity.getSharedPreferences(SHAREDPREFS_NAME, 0);
		
			if(sharedPrefs.contains(ACTIVITYNAME_KEY))
			{
				model.UserName =  sharedPrefs.getString(USERNAME_KEY,DEFAULTVALUE_STRING );
				model.PlainPassword = sharedPrefs.getString(PLAINPASSWORD_KEY, DEFAULTVALUE_STRING);
				model.EncPassword = sharedPrefs.getString(ENCPASSWORD_KEY, DEFAULTVALUE_STRING);
				model.ShowPassword = sharedPrefs.getBoolean(SHOWPASSWORD_KEY, false);
				model.CheckCredsOnSave = sharedPrefs.getBoolean(CHECKCREDSONSAVE_KEY, false);
				return model;
			}
			else
			{
				return null;
			}	
		}
		catch(Exception exObj)
		{
			return null;
		}
	}

	@Override
	public boolean Save(Context context, Credentials model)
	{
		SharedPreferences prefs = context.getSharedPreferences(SHAREDPREFS_NAME, 0);
    	Editor editor = prefs.edit();
    	
    	editor.putString(ACTIVITYNAME_KEY, ACTIVITYNAME_KEY);
    	editor.putString(USERNAME_KEY, model.UserName);
    	editor.putString(PLAINPASSWORD_KEY, model.PlainPassword);
    	editor.putString(ENCPASSWORD_KEY, model.EncPassword);
    	editor.putBoolean(SHOWPASSWORD_KEY, model.ShowPassword);
    	editor.putBoolean(CHECKCREDSONSAVE_KEY, model.CheckCredsOnSave);
    	
    	editor.commit();
		return true;
	}

}

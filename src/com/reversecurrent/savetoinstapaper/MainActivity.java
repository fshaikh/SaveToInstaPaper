package com.reversecurrent.savetoinstapaper;

import com.reversecurrent.utility.network.NetworkUtility;


import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends Activity {

	
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        Initialize();
        
        // hook the save button
        Button btn = (Button)findViewById(R.id.saveBtn);
        btn.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				IPersistentStorage storage = new SharedPreferencesStorage();
				Credentials creds = new Credentials();
				
				EditText usernameCtrl = (EditText)findViewById(R.id.usernameEditTxt);
				creds.UserName = usernameCtrl.getText().toString();
				
				EditText passwordCtrl = (EditText)findViewById(R.id.pwdEditTxt);
				creds.PlainPassword = passwordCtrl.getText().toString();
				
				storage.Save(v.getContext(), creds);
				
				// check the credentials if user has asked to do so
				CheckBox chkBox = (CheckBox)findViewById(R.id.checkCredsOnSavecheckBox);
				if(chkBox.isChecked()){
					// check the crednetials
					if(NetworkUtility.IsNetworkAvailable(getApplicationContext()))
					{
						new AuthAsyncTask(getApplicationContext()).execute(creds);
					}
					else
					{
						Toast.makeText(getApplicationContext(),
								getApplicationContext().getResources().getString(R.string.NoNetworkMessage),
								Toast.LENGTH_LONG).show();
					}
				}
				
				//finish();
				
			}
		});
    }

    private void Initialize() {
    	// load the creds from storage
        IPersistentStorage storage = new SharedPreferencesStorage();
        Credentials creds = storage.GetModelActivity(this);
        if(creds != null)
        {
        	PopulateUI(creds);
        }
		
	}

	private void PopulateUI(Credentials creds) {
		// TODO Auto-generated method stub
		EditText usernameCtrl = (EditText)findViewById(R.id.usernameEditTxt);
		usernameCtrl.setText(creds.UserName);
		
		EditText passwordCtrl = (EditText)findViewById(R.id.pwdEditTxt);
		passwordCtrl.setText(creds.PlainPassword);
	}
	
	

	@Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }
    
}

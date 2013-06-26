package com.reversecurrent.savetoinstapaper;

import android.net.Uri;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;

//import com.reversecurrent.payload.RequestPayload;
import com.reversecurrent.utility.logging.UserMessage;
import com.reversecurrent.utility.network.*;

public class ShareReceiverActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		try
		{
			super.onCreate(savedInstanceState);
			
			// get the intent
			Intent intent = getIntent();
			String action = intent.getAction();
			String mimeType = intent.getType();
			
			if(Intent.ACTION_SEND.equals(action) && mimeType != null)
			{
				if(mimeType.equals(Constants.MIMETYPE_TEXT))
				{
					handleSendText(intent);
				}
				else if(mimeType.startsWith(Constants.MIMETYPE_SINGLEIMAGE))
				{
					handleSingleImage(intent);
				}
			}
			finish();
		}
		catch(Exception exObj)
		{
			StreamError(exObj);
		}
	}

	private void handleSendText(Intent intent) {
		try
		{
			// get the intent data
			String sharedData = intent.getStringExtra(Intent.EXTRA_TEXT);
			if(sharedData != null){
				
				// time to save to instapaper if network is available
				if(NetworkUtility.IsNetworkAvailable(this.getApplicationContext())){
					Intent shareIntent = new Intent();
				shareIntent.putExtra(Constants.SHAREDDATA_KEY,sharedData );
				shareIntent.setAction(getApplicationContext().getResources().getString(R.string.BroadcastIntentMessage));
				getApplicationContext().sendBroadcast(shareIntent);
				}
			}
		}
		catch(Exception exObj)
		{
			StreamError(exObj);
		}
	}
	
	private void handleSingleImage(Intent intent)
	{
		Uri imageUri = (Uri) intent.getParcelableExtra(Intent.EXTRA_STREAM);
	    if (imageUri != null)
	    {
	    	// time to save to skydrive if network is available
			if(NetworkUtility.IsNetworkAvailable(this.getApplicationContext())){
				Intent shareIntent = new Intent();
				shareIntent.putExtra(Constants.SHAREDDATA_KEY,imageUri );
				Constants.AppActivity = this;
				shareIntent.setAction(getApplicationContext().getResources().getString(R.string.SkyDriveBroadcastIntentMessage));
				getApplicationContext().sendBroadcast(shareIntent);
			}
	    }
	}

	private void StreamError(Exception exObj) {
		String message = exObj.getMessage();
		StringBuilder stackTraceBuilder = new StringBuilder();
		StackTraceElement[] traces = exObj.getStackTrace();
		for(int i=0;i<traces.length;i++)
		{
			stackTraceBuilder.append(traces[i]);
			stackTraceBuilder.append("\n");
		}
		stackTraceBuilder.append(message);
		
		UserMessage.ShowMessage(getApplicationContext(),stackTraceBuilder.toString());
		
	}

}

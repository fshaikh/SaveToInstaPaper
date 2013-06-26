package com.reversecurrent.savetoinstapaper;


import com.reversecurrent.utility.logging.UserMessage;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;


public class SharedIntentReceiver extends BroadcastReceiver {

	@Override
	public void onReceive(Context context, Intent intent) {
		
		Credentials creds = GetCredentials(context);
		if(creds != null)
		{
			RequestPayload payload = GetRequestPayload(intent.getStringExtra(Constants.SHAREDDATA_KEY));
			// time to send the payload to instapaper.
			new ShareAsyncTask(context,creds).execute(payload);
		}
		else
		{
			UserMessage.ShowMessage(context, context.getResources().getString(R.string.CredsNotSetMessage));
		}
	}
	
	private Credentials GetCredentials(Context context)
	{

		IPersistentStorage storage= new DefaultSharePreferencesStorage();
		return storage.GetModel(context);
	}

	private RequestPayload GetRequestPayload(String sharedData)
	{
		RequestPayload p = new RequestPayload();
		p.Url = sharedData;
		return p;
	}

}

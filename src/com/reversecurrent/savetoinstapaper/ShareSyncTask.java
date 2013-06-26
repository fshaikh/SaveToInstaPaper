package com.reversecurrent.savetoinstapaper;

import android.content.Context;


import com.reversecurrent.utility.logging.UserMessage;


public class ShareSyncTask {
	private Context _context = null;
	private Credentials _credentials = null;
	
	public ShareSyncTask(Context context,Credentials creds)
	{
		_context = context;
		_credentials = creds;
	}
	
	public ResponsePayload Do(com.reversecurrent.savetoinstapaper.RequestPayload params)
	{
		UserMessage.ShowMessage(_context,"done");
		InstaPaperHTTPClient client = new InstaPaperHTTPClient();
		com.reversecurrent.savetoinstapaper.ResponsePayload payload = client.AddShare(_credentials.UserName,
												   _credentials.PlainPassword,
												   params);
		UserMessage.ShowMessage(_context,"done");
		return payload;
	}
	
	/*@Override
	protected void onPostExecute(ResponsePayload payload)
	{
		if(payload == null)
		{
			UserMessage.ShowMessage(_context, _context.getResources().getString(R.string.NoResponseMessage));
			return;
		}
		switch(payload.ResponseStatus)
		{
		case SUCCESS:
			String message = ConstructSuccessMessage(payload.ContentLocation);
			UserMessage.ShowMessage(_context, message);
			break;
		case INVALIDCREDS:
			UserMessage.ShowMessage(_context,
					_context.getResources().getString(R.string.InValidCredsMessage));
			break;
		}
	}

	private String ConstructSuccessMessage(String contentLocation)
	{
		StringBuilder builder = new StringBuilder();
		builder.append(_context.getResources().getString(R.string.AddSuccessMessage));
		builder.append(" ");
		builder.append(contentLocation);
		
		return builder.toString();
	}*/
}

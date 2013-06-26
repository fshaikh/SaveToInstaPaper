package com.reversecurrent.savetoinstapaper;



import android.content.Context;
import android.os.AsyncTask;
import com.reversecurrent.utility.logging.*;

public class ShareAsyncTask extends AsyncTask<RequestPayload, Void, ResponsePayload>
{
	private Context _context = null;
	private Credentials _credentials = null;
	
	public ShareAsyncTask(Context context,Credentials creds)
	{
		_context = context;
		_credentials = creds;
	}
	
	@Override
	protected ResponsePayload doInBackground(RequestPayload... params)
	{
		
		InstaPaperHTTPClient client = new InstaPaperHTTPClient();
		ResponsePayload payload = client.AddShare(_credentials.UserName,
												   _credentials.PlainPassword,
												   params[0]);
		return payload;
	}
	
	@Override
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
		//builder.append(" ");
		//builder.append(contentLocation);
		
		return builder.toString();
	}

}

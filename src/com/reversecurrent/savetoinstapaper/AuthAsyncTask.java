package com.reversecurrent.savetoinstapaper;


import android.content.Context;
import android.os.AsyncTask;
import com.reversecurrent.utility.logging.*;

public class AuthAsyncTask extends AsyncTask<Credentials  , Void, Boolean>
{
	private Context _context = null;
	
	public AuthAsyncTask(Context context)
	{
		_context = context;
	}
	
	@Override 
	protected Boolean doInBackground(Credentials... params)
	{

		InstaPaperHTTPClient client = new InstaPaperHTTPClient();
		Boolean status = client.Authenticate(params[0].UserName, params[0].PlainPassword);
		return status;
	}
	
	@Override
	protected void onPostExecute(Boolean status)
	{
		if(status)
		{
			UserMessage.ShowMessage(_context,_context.getResources().getString(R.string.ValidCredsMessage));
		}
		else
		{
			UserMessage.ShowMessage(_context,_context.getResources().getString(R.string.InValidCredsMessage));
		}
	}


}

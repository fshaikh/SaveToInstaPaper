package com.reversecurrent.savetoinstapaper;

import java.io.IOException;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.methods.HttpGet;

//import com.reversecurrent.payload.*;

import android.net.http.AndroidHttpClient;

public class InstaPaperHTTPClient {
	private final String INSTAPAPERAUTHURL = "https://www.instapaper.com/api/authenticate?";
	private final String INSTAPAPERSIMPLE_ADDURL = "https://www.instapaper.com/api/add?";
	private final String USERNAMEKEY = "username=";
	private final String PASSWORDKEY = "password=";
	private final String URLKEY = "url=";
	//private final String TITLEKEY = "title=";
	//private final String SELECTIONKEY = "selection=";
	private final String QSDELIMITER = "&";
	
	public boolean Authenticate(String username,String password)
	{
		// TODO: encode the username and password
		String url = ConstructAuthURL(INSTAPAPERAUTHURL, username, password);
		HttpResponse httpResponse = null;
		try
		{
			httpResponse = GetResponse(url);
			if(httpResponse == null || httpResponse.getStatusLine().getStatusCode() != HttpStatus.SC_OK)
			{
				return false;
			}
			return true;
		}
		catch (Exception e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		
	}
	
	public ResponsePayload AddShare(String username,String password,RequestPayload requestPayload)
	{
		String url = ConstructAddUrl(username, password, requestPayload);
		HttpResponse httpResponse = null;
		ResponsePayload payload = new ResponsePayload();
		try
		{
			httpResponse = GetResponse(url);
			if(httpResponse == null)
			{
				payload.ResponseStatus = ResponseEnum.SERVICEERROR;
				return payload;
			}
			// read the status code, title and content location
			SetResponseStatus(payload,httpResponse.getStatusLine().getStatusCode());
			if(payload.ResponseStatus == ResponseEnum.SUCCESS)
			{
				SetResponseHeaders(payload,httpResponse);
			}
			
			return payload;
		}
		catch (Exception e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
		finally
		{
			
		}
	}
	
	

	private void SetResponseHeaders(ResponsePayload payload,HttpResponse httpResponse)
	{
		/*
		 When a 201 is returned from this call, two additional response headers are set:
			•Content-Location: The saved URL for the newly created item, after any normalization and redirect-following.
 			•X-Instapaper-Title: The saved title for the page, after any auto-detection.
	  
		 */
	}

	private void SetResponseStatus(ResponsePayload payload, int statusCode)
	{
		switch(statusCode)
		{
		case 201:
			payload.ResponseStatus = ResponseEnum.SUCCESS;
			break;
		case 400:
			payload.ResponseStatus = ResponseEnum.BADREQUEST;
			break;
		case 403:
			payload.ResponseStatus = ResponseEnum.INVALIDCREDS;
			break;
		case 500:
			payload.ResponseStatus = ResponseEnum.SERVICEERROR;
			break;
		default:
			payload.ResponseStatus = ResponseEnum.OTHERS;
			break;
		}
	}

	private HttpResponse GetResponse(String url) throws Exception
	{
		AndroidHttpClient httpClient = AndroidHttpClient.newInstance("reversecurrent");
		HttpGet authRequest = new HttpGet(url);
		HttpResponse response = null;
		try
		{

			response = httpClient.execute(authRequest);
			if(response == null)
			{
				throw new Exception();
			}
			return 	response;

		}
		catch (IOException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
		finally
		{
			if(httpClient != null)
				httpClient.close();
			
		}
	}

	private String ConstructAuthURL(String mainUrl, String username, String password) {
		//https://www.instapaper.com/api/authenticate?username=furqan_shaikh_1999@hotmail.com&password=fur12
		StringBuilder builder = new StringBuilder();
		builder.append(mainUrl);
		builder.append(USERNAMEKEY);
		builder.append(username);
		builder.append(QSDELIMITER);
		builder.append(PASSWORDKEY);
		builder.append(password);
		
		return builder.toString();
		
	}
	
	private String ConstructAddUrl(String username,String password, RequestPayload payload)
	{
		StringBuilder builder = new StringBuilder();
		builder.append(ConstructAuthURL(INSTAPAPERSIMPLE_ADDURL,username,password));
		builder.append(QSDELIMITER);
		builder.append(URLKEY);
		builder.append(payload.Url);
		
		return builder.toString();
	}
}


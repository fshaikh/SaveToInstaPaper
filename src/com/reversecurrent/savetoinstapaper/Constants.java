package com.reversecurrent.savetoinstapaper;

import android.app.Activity;

public class Constants {
	public static final String SHAREDDATA_KEY = "";
	public static final String MIMETYPE_TEXT = "text/plain";
	public static final String MIMETYPE_SINGLEIMAGE = "image/";
	public static final String CLIENT_ID = "00000000440E5A96";
	
	public static final String[] SCOPES = {
        "wl.signin",
        "wl.basic",
        "wl.offline_access",
        "wl.skydrive_update",
    };
	
	public static Activity AppActivity = null;
}

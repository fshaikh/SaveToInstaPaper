package com.reversecurrent.savetoinstapaper;

import android.app.Activity;
import android.content.Context;

public interface IPersistentStorage {
	Credentials GetModel(Context context);
	Credentials GetModelActivity(Activity activity);
	boolean Save(Context context, Credentials model);
}

package com.googlecode.android.widgets.DateSlider;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;

public class ServiceManager extends BroadcastReceiver {

	
	String date="rahul";
	String day="raja";
	Context mContext;
	private final String BOOT_ACTION = "android.intent.action.BOOT_COMPLETED";
	
	@Override
	public void onReceive(Context context, Intent intent) {
                // All registered broadcasts are received by this
		mContext = context;
		String action = intent.getAction();
		if (action.equalsIgnoreCase(BOOT_ACTION)) {
                        //check for boot complete event & start your service
			startService();
		} 
		
	}

	
	private void startService() {
		
		 SharedPreferences settings = PreferenceManager
			.getDefaultSharedPreferences(mContext);
		 SharedPreferences.Editor editor1 = settings.edit();
		 editor1.putString("prefUsername","yyyy");
		 editor1.commit();
		
                //here, you will start your service
		Intent mServiceIntent = new Intent(mContext,MyService2.class);
		//Intent intent=new Intent(MainActivity.this,MyService.class);         
		/*Bundle b=new Bundle();
		String a[]={date,day};
		b.putStringArray("Array",a);
		mServiceIntent.putExtras(b);*/
		//startService(mServiceIntent);
		//mServiceIntent.setAction("com.bootservice.test.DataService");
		mContext.startService(mServiceIntent);
	}
}

package com.googlecode.android.widgets.DateSlider;

import android.app.ActivityManager;

import android.app.Dialog;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.app.ActivityManager.RunningServiceInfo;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.preference.PreferenceManager;
import android.text.format.DateFormat;
import android.util.Log;
import android.util.SparseBooleanArray;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

import javax.security.auth.callback.Callback;



public class MyService4 extends Service
{
	int counter=0;
	int serv=0;
	int notificationID=1;
	static final int UPDATE_INTERVAL=1000;
	int serv_start;
	
	private Timer timer=new Timer();
	

	@Override
	public IBinder onBind(Intent arg0)
	{
		return null;
	}


	
	@Override
	public int onStartCommand(Intent intent,int flags,int startId)
	{
		Log.d("rahul","thisi has started");
		dosomething();
		return START_STICKY;
	}
	
	
	
	private void dosomething()
	{
		//System.out.println(minute1);
		
		
		timer.scheduleAtFixedRate(new TimerTask(){
			public void run()
			{
				Log.d("my service main",String.valueOf(++counter));
				
  Calendar no=Calendar.getInstance();
  if(no.getTime().getHours()==0 && no.getTime().getMinutes()==0 && no.getTime().getSeconds()==0)
	  serv=0;
									//break;
							start();
				//Toast.makeText(this, "service started", Toast.LENGTH_SHORT).show();
		
	    	    ActivityManager manager = (ActivityManager) getSystemService(Context.ACTIVITY_SERVICE);
	    	    for (RunningServiceInfo service : manager.getRunningServices(Integer.MAX_VALUE)) {
	    	        if (MyService.class.getName().equals(service.service.getClassName())) {
	    	            
	    	        	Log.d("rahul","service running");
	    	        }
	    	    }
	    	   
				
			}
		}, 0, UPDATE_INTERVAL);
	}
	
	
	void start()
	{
		
		Log.d("the value of serv is",String.valueOf(serv));
		Calendar now=Calendar.getInstance();
		
		if(now.getTime().getHours()==7 && now.getTime().getMinutes()==0 && now.getTime().getSeconds()==0)
		{
			startService(new Intent(getBaseContext(),MyService5.class)); 
			serv=1;
		}
		if(serv==0 && now.getTime().getHours()>=7)
		{
			Log.d("inside serv","yes i am");
		
			startService(new Intent(getBaseContext(),MyService5.class)); 
			serv=1;
		}
		
	}
	
	
	
			
	@Override
	public void onDestroy()
	{
		System.out.println("destroyed b oss");
		super.onDestroy();
		if(timer!=null)
		{
			timer.cancel();
		}
		Toast.makeText(this, "service destroyed", Toast.LENGTH_SHORT).show();
	}

}

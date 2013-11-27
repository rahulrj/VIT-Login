
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

public class MyService2 extends Service
{
	int counter=0;
	int notificationID=1;
	static final int UPDATE_INTERVAL=60000;
	
	private Timer timer=new Timer();
	//DBAdapter inf;
	Cursor x,jk;
	String dayOfTheWeek="";
	Calendar cal= Calendar.getInstance();
	int w;
	int len1;
	String[] array;
	int[] great;
	int greatest;
	String alarmtime;
	Date date3;
	Date date;
	long diffInMins;
	String sub,subcontext;
	String[] subs;
	Dialog dialo;
	int counte;
	ListView menulist1;
	String subnoti;
	Button b1;
	String not;
	String b[];
	String[] notes;
	String gg;
	int day,day1,month,month1,year,year1,hour,hour1,minute,minute1;
	int[] day2,month2,year2,hour2,minute2,notifid;
	String[] subj,cate;
	int sel_not;
	private ArrayAdapter<String> listAdapter ;
	String notification;
	Cursor c;
	Quiz q;

	@Override
	public IBinder onBind(Intent arg0)
	{
		return null;
	}


	
	@Override
	public int onStartCommand(Intent intent,int flags,int startId)
	{
		System.out.println("In service");
	 q=new Quiz(this);
		q.open();
	
	 c=q.getAllContacts2();
	 w=c.getCount();
	 System.out.println("the count is"+Integer.toString(w));
	day2=new int[w];
	hour2=new int[w];
	minute2=new int[w];
	year2=new int[w];
	month2=new int[w];
	subj=new String[w];
	cate=new String[w];
	notes=new String[w];
	notifid=new int[w];
	int k=0;
	if(c.moveToFirst())
	{
	do
	{
	year2[k]=c.getInt(4);
	month2[k]=c.getInt(5);
	day2[k]=c.getInt(6);
	hour2[k]=c.getInt(7);
	minute2[k]=c.getInt(8);
	subj[k]=c.getString(1);
	cate[k]=c.getString(2);
	notifid[k]=c.getInt(0);
	notes[k]=c.getString(9);
	k++;
	}while(c.moveToNext());
	}
		q.close();
		dosomething();
		return START_STICKY;
	}
	
	
	
	private void dosomething()
	{
		System.out.println(minute1);
		
		
		timer.scheduleAtFixedRate(new TimerTask(){
			public void run()
			{
				Log.d("my service",String.valueOf(++counter));
				System.out.println("hello");
			 Date date=new Date();
				  day1=date.getDate();
				 month1=date.getMonth()+1;
				 year1=date.getYear()+1900;
				  hour1=date.getHours();
				  minute1=date.getMinutes();
				 System.out.println("today date");
				 System.out.println(day1);
				 System.out.println(month1);
				 System.out.println(year1);
				 System.out.println(hour1);
				 System.out.println(minute1);

				
				
			
				for(int i=0;i<w;i++)
				{
					System.out.println(year2[i]);
					System.out.println(month2[i]);
					System.out.println(day2[i]);
					System.out.println(hour2[i]);
					System.out.println(minute2[i]);
					
					System.out.println("runnng yr");
				if(year2[i]==year1)
				{
					System.out.println("true yr");
					if(month2[i]==month1)
					{
						System.out.println("true yr");
						if(day2[i]==day1)
						{
							System.out.println("true yr");
							if(hour2[i]==hour1)
							{
								System.out.println(minute2[i]);
								System.out.println(minute1);

								System.out.println("true yr");
								if(minute2[i]==minute1)
								{
																	
									System.out.println("true yr");
									notification=subj[i];
									subnoti=cate[i];
									subcontext=notes[i];
									System.out.println("value matched exaclty");
									sel_not=notifid[i];
									q.open();
									q.delete(sel_not);
									q.close();

									shownotif(sel_not);
									//break;
									
								}
							}
						}
					}
						
				}}
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
	
	
	void shownotif(int selnot)
	{
		
		
		
		Intent i=new Intent(this,notif.class);
		i.putExtra("notificationID", selnot);
	////	System.out.println(subcontext);
		//i.putExtra("context", subcontext);
		

        

        // The PendingIntent will launch activity if the user selects this
        // notification
        PendingIntent pi = PendingIntent.getActivity(this,
                0, i, 0);
        NotificationManager nm = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        
        Notification notification2 = new Notification(R.drawable.ic_launcher,
                notification, System
                        .currentTimeMillis());
        notification2.setLatestEventInfo(this, notification, subnoti,
               pi);
        notification2.flags=Notification.FLAG_AUTO_CANCEL;
        notification2.vibrate=new long[]{100,250,100,500};
      nm.notify(selnot, notification2);
		
	}
	
	void ss(Intent in,int flgs,int start)
	{
		SharedPreferences sharedPrefs = PreferenceManager
		.getDefaultSharedPreferences(this);

//StringBuilder builder = new StringBuilder();

gg=sharedPrefs.getString("prefUsername", "NULL");
if (gg.equalsIgnoreCase("zzzz")||gg.equalsIgnoreCase("yyyy"))
{
	super.onStart(in, start);
    Bundle id=in.getExtras();
     b=id.getStringArray("Array");
	Toast.makeText(this, not, Toast.LENGTH_SHORT).show();
	Log.d("the value is",b[0]);
	Log.d("the value is",b[1]);
	
	dosomething();
}
else
{
	Toast.makeText(this, "running", Toast.LENGTH_SHORT).show();
	dosomething();
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

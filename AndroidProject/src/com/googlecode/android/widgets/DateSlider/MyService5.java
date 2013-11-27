
package com.googlecode.android.widgets.DateSlider;
import android.app.ActivityManager;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.Service;
import android.app.ActivityManager.RunningServiceInfo;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.database.Cursor;
import android.media.AudioManager;
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

public class MyService5 extends Service
{
	int counter=0;
	Cursor jk;
	DBAdapter in;
	int value;
	Dialog dialo;
	ListView menulist1;
	Button b1;
	String[] subs;
	Date[] holidates;
	int day_class;
	int mk2;
	SimpleDateFormat dateformat;
	Date fromdate,todate;
	long day,diff;
	int length=0;
	String is_holiday="false";
	int length_classes=0;
	String[] subjects;
	String timings[];
	String tim_slot[];
	String slots[];
	String time_for_silent[];
	private ArrayAdapter<String> listAdapter ;
	SimpleDateFormat formatter;
	int[] great;
	int show_dialog=0;
	 
	 Date date,date3;
	 long diffInMins;
	AudioManager am;
	static final int UPDATE_INTERVAL=2000;
	private Timer timer=new Timer();
	int silentmode=0;
	SharedPreferences pref;
	SharedPreferences pref1;
	int start=0;
	int x=1;
	
	
	

	@Override
	public IBinder onBind(Intent arg0)
	{
		return null;
	}


	
	@Override
	public int onStartCommand(Intent intent,int flags,int startId)
	{
		start=1;
		Toast.makeText(this, "service3 started", Toast.LENGTH_SHORT).show();
		Log.d("service3","has statrted");
		dosomething();
		return START_STICKY;
	}
	
	
	
	private void dosomething()
	{
		
		
		timer.scheduleAtFixedRate(new TimerTask(){
			public void run()
			{
				
				Calendar now=Calendar.getInstance();
				if(now.getTime().getHours()==0 && now.getTime().getMinutes()==0 && (now.getTime().getSeconds()==1 || now.getTime().getSeconds()==2 || now.getTime().getSeconds()==3))
				{
					 pref1 = PreferenceManager.getDefaultSharedPreferences(getBaseContext());
						Editor editor = pref1.edit();
						editor.putBoolean("save_show",false);
						editor.commit();
						x=0;
						
				}
				
				
				Log.d("the value of x is",String.valueOf(x));
				if(now.getTime().getHours()>=0 && x==1)
				{
					Log.d("the time is","yes it is");
					 pref1 = PreferenceManager.getDefaultSharedPreferences(getBaseContext());
						Editor editor = pref1.edit();
						editor.putBoolean("save_show",false);
						editor.commit();
						x=0;
						
				}
					
				
				
				Log.d("my service333333333",String.valueOf(++counter));
				//Toast.makeText(this, "service started", Toast.LENGTH_SHORT).show();
		
	    	    ActivityManager manager = (ActivityManager) getSystemService(Context.ACTIVITY_SERVICE);
	    	    for (RunningServiceInfo service : manager.getRunningServices(Integer.MAX_VALUE)) {
	    	        if (MyService.class.getName().equals(service.service.getClassName())) {
	    	            
	    	        	Log.d("rahul","service running");
	    	        }
	    	    }

			
	    	   some1();
	    	   // silent();
				
			}
		},0, UPDATE_INTERVAL);
	}
	
	void some1()
	{
		
		if(start==1)
			one_time_calc();
		

		SharedPreferences sharedPrefs = PreferenceManager
		.getDefaultSharedPreferences(this);
		Boolean attn=sharedPrefs.getBoolean("mark",false);
		if(attn==true)
		{
			
			Log.d("rahul","its true");
			some();
			
		}
		Boolean silen=sharedPrefs.getBoolean("silent",false);
		if(silen==true)
			some();
		
		
		
	}
		
		void some()
		{
			
			
			
	
			
		//	Log.d("rahul","its here in some");
		//	Log.d("the day is",String.valueOf(day_class));
			Log.d("the holiday is",is_holiday);
			
			Calendar now=Calendar.getInstance();
			if(day_class==0 || day_class==6 || (is_holiday.equalsIgnoreCase("true")))
			{
				
			}
			else
			{
				
				 pref1 = PreferenceManager.getDefaultSharedPreferences(getBaseContext());
				 
				Boolean show=pref1.getBoolean("save_show", false);
				Log.d("the value of pref is",String.valueOf(show));
			
					Log.d("the time is",String.valueOf(now.getTime().getHours()));
					if((now.getTime().getHours()>=20) && show==false)
					{
						Log.d("i am here","in show");
						show();
						
						 pref1 = PreferenceManager.getDefaultSharedPreferences(getBaseContext());
							Editor editor = pref1.edit();
							editor.putBoolean("save_show",true);
							editor.commit();
					}
				    
				
			
			if(now.getTime().getHours()==20 && now.getTime().getMinutes()==0 && show==false && (now.getTime().getSeconds()==0 || now.getTime().getSeconds()==1 || now.getTime().getSeconds()==2))
			{
				
				Log.d("rahul","time matched");
				
			show();
			 pref1 = PreferenceManager.getDefaultSharedPreferences(getBaseContext());
				Editor editor = pref1.edit();
				editor.putBoolean("save_show",true);
				editor.commit();
				
			
			
			}
			}

		
			
			for(int i=0;i<subs.length;i++)
			{
				tim_slot[i]=slotToTiming(Integer.parseInt(timings[i]),"slots[i]");
				Log.d("the timings arw",tim_slot[i]);
			}
			
			
			
			
			SharedPreferences sharedPrefs = PreferenceManager
			.getDefaultSharedPreferences(this);
			Boolean silent=sharedPrefs.getBoolean("silent",false);
			if(silent==true)
				silent();
			
				
				
			
			
			

				
				for(int i=0;i<subs.length;i++)
				{
					/*Log.d("subjects","the subject are");
					Log.d("subjects",subs[i]);*/
					//Log.d("subjects",tim_slot[i]);
					//Log.d("subjects",time_for_silent[i]);
					
				}
				
			
		}
	
	
	
	
		public void calculate_holidays()
		 {
			 System.out.println("in calculate holidays");
			 		String commence_sem="";
			 		DBAdapter5 dba=new DBAdapter5(getApplicationContext());
			 		dba.open();
			Cursor c=dba.getDates();
			if(c.moveToFirst())
			{
				System.out.println("true");
				do
				{
					String from_date= c.getString(0);
					String to_date=c.getString(1);
					String desc=c.getString(2);
					String is_holiday=c.getString(3);

				System.out.println(desc);

			
			if(is_holiday.equalsIgnoreCase("n"))
			{
				System.out.println("in holidays");
			
				from_date = c.getString(0);
				to_date=c.getString(1);
				System.out.println(from_date);
				System.out.println(to_date);
				 try {
					
					 fromdate = dateformat.parse(from_date);
					 todate=dateformat.parse(to_date);
				} catch (ParseException e) {
					e.printStackTrace();
			       }
				long t1 = fromdate.getTime();
		        long t2 = todate.getTime();
		       day = 1000 * 60 * 60 * 24; // milliseconds in a day
		       diff=(t2-t1)/day;
		       Calendar start = Calendar.getInstance();
		       start.setTime(fromdate);
		       Calendar end = Calendar.getInstance();
		       end.setTime(todate);
		       for (; !start.after(end); start.add(Calendar.DATE, 1)) {
		       	Date current = start.getTime();
		       	holidates[mk2]=current;
		       	System.out.println(holidates[mk2]);
		       	mk2++;
				}}
			
					}while(c.moveToNext() && (! (c.getString(2).equalsIgnoreCase("Last Instructional Day"))));
				length=mk2;
				   for(int i=0;i<mk2;i++)
			       {
			      	 System.out.println(holidates[i]);
			       }
				   
				
			 
		 }
			mk2=0;
		 }
		
		
		 public String slotToTiming(int slotNum, String slot) {
			 System.out.println("slotnum"+slotNum);
				if (slotNum <= 30){
					if (slotNum%6 == 1)
						return "08:00:00 am";
					else if (slotNum%6 == 2)
						return "09:00:00 am";
					else if (slotNum%6 == 3)
						return "10:00:00 am";
					else if (slotNum%6 == 4)
						return "11:00:00 am";
					else if ((slotNum%6 == 5)&&(slot.charAt(0)=='L'))
						return "11:50:00 am";
					else if (slotNum%6 == 5)
						return "12:00:00 am";
				}
				else {
					if (slotNum%6 == 1)
						return "02:00:00 pm";
					else if (slotNum%6 == 2)
						return "03:00:00 pm";
					else if (slotNum%6 == 3)
						return "04:00:00 pm";
					else if (slotNum%6 == 4)
						return "05:00:00 pm";
					else if ((slotNum%6 == 5)&&(slot.charAt(0)=='L'))
						return "05:50:00 pm";
					else if (slotNum%6 == 5)
						return "06:00:00 pm";
				}
			return null;
			}
	
	
		 void silent()
		 {
		 	
		 	
		 	
		 	
		 	
		 	
		 	for(int i=0;i<slots.length;i++)
		 	{
		 		if(slots[i].charAt(0)=='L')
		 		{
		 			
		 			int count=0;
		 			tim_slot[i]=slotToTiming(Integer.parseInt(timings[i]),slots[i]);
		 			//int count = slots[i].length() - slots[i].replaceAll("+", "").length();
		 			for(int k=0;k<slots[i].length();k++)
		 			{
		 				if(slots[i].charAt(k)=='+')
		 					count++;
		 					
		 					
		 			}
		 			if(count==2)
		 				time_for_silent[i]=String.valueOf(150);
		 			else if(count==1)
		 				time_for_silent[i]=String.valueOf(100);
		 			else if(count==0)
		 				time_for_silent[i]=String.valueOf(50);
		 		}
		 		else
		 		{
		 			tim_slot[i]=slotToTiming(Integer.parseInt(timings[i]),slots[i]);
		 			time_for_silent[i]=String.valueOf(50);
		 		}
		 	}
		 	
		 	 for(int i=0;i<tim_slot.length;i++)
		 	 {
		 		 
		 		 try {
		 			 date = (Date)formatter.parse(tim_slot[i]);
		 			 Log.d("dthe date value in  rahul","silent"+date);
		 			 Date date2=new Date();
		 				String date22=formatter.format(date2);
		 				date3 = (Date)formatter.parse(date22);
		 				Log.d("dthe date3 value in  rahul","silent"+date3);
		 				
		 				diffInMins = (date3.getTime() - date.getTime()) / 60000;
		 				
		 				if(diffInMins>=(long)0 && Math.abs(diffInMins)<=(long)Integer.parseInt(time_for_silent[i]))
		 				
		 					call_silent(date,time_for_silent[i]);
		 				
		 				if(Integer.parseInt(time_for_silent[i])==50)
		 				{
		 					if(diffInMins==(long)51)
		 						am.setRingerMode(AudioManager.RINGER_MODE_NORMAL);
		 						
		 				}
		 				if(Integer.parseInt(time_for_silent[i])==100)
		 				{
		 					if(diffInMins==(long)101)
		 						am.setRingerMode(AudioManager.RINGER_MODE_NORMAL);
		 						
		 				}
		 				
		 				
		 				if(Integer.parseInt(time_for_silent[i])==150)
		 				{
		 					if(diffInMins==(long)151)
		 						am.setRingerMode(AudioManager.RINGER_MODE_NORMAL);
		 						
		 				}
		 				
		 				
		 				
		 				/*if(diffInMins>=(long)0 && Math.abs(diffInMins)<=(long)Integer.parseInt(time_for_silent[i]))
		 				{
		 					Log.d("differenec",String.valueOf(diffInMins));
		 					pref = getSharedPreferences("silent", MODE_PRIVATE);
		 				 	 
		 				 	String u = pref.getString("silentmode", "");
		 				 	if(u.equalsIgnoreCase("1"))
		 						break;
		 					am.setRingerMode(AudioManager.RINGER_MODE_SILENT);
		 					Log.d("rahul time for",time_for_silent[i]);
		 					Log.d("silentmode",String.valueOf(silentmode));
		 					
		 					SharedPreferences.Editor edit = pref.edit();
					        
					        edit.putString("silentmode","1");
					       // edit.putString("password", password.getText().toString());
					        
					        // Commit the data
					        edit.commit();

		 				}
		 				else
		 				{
		 					Log.d("differenec2",String.valueOf(diffInMins));
		 					Log.d("no silent","not here");
		 					am.setRingerMode(AudioManager.RINGER_MODE_NORMAL);
		 					//silentmode=0;
		 				}*/
		 				
		 				
		 			} catch (ParseException e) {
		 				// TODO Auto-generated catch block
		 				e.printStackTrace();
		 			}
		 			
		 	 }
		 	 
		 }
		 
		 
		 
		 void call_silent(Date d,String duration)
		 {
			 Date date2=new Date();
			 Date date3 = null;
				String date22=formatter.format(date2);
				try {
					date3 = (Date)formatter.parse(date22);
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				Log.d("the obtained date is","rahul"+d.getTime());
				 Long diffInMins = (date3.getTime() - d.getTime()) / 60000;
				 
				 Log.d("in silent the diff is",String.valueOf(diffInMins));
				
				
				 if(diffInMins>=(long)0 && Math.abs(diffInMins)<=(long)Integer.parseInt(duration))
				 {
					 am.setRingerMode(AudioManager.RINGER_MODE_SILENT);
				 }
				
				 else
						am.setRingerMode(AudioManager.RINGER_MODE_NORMAL);
				
				
				
				
				
		 }
		 public void show()
			{
			 
			 pref1 = PreferenceManager.getDefaultSharedPreferences(getBaseContext());
				Editor editor = pref1.edit();
				editor.putBoolean("save_show",true);
				editor.commit();
				global.service=1;
			int counte=2;
				//Intent trIntent = new Intent("android.intent.action.MAIN");
				Intent trIntent=new Intent(MyService5.this,BackGroundDialogs.class);
				//trIntent.putExtra("key",counte);
				trIntent.putExtra("key", subs);
				
				//trIntent.setClass(this, BackGroundDialogs.class);
				trIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);        			
			        this.startActivity(trIntent);        	   
			}
			
	
	
		 
		 void one_time_calc()
		 {
			 am= (AudioManager) getBaseContext().getSystemService(Context.AUDIO_SERVICE);
			 formatter = new SimpleDateFormat("hh:mm:ss");

				Log.d("value of length",String.valueOf(value));
				// subs=new String[value];
				Context ctx = this;
				in=new DBAdapter(this);
				in.open();
				
				Log.d("rahul","this is executed");
				final AlertDialog.Builder alert = new AlertDialog.Builder(ctx);
			    alert.setTitle("Message");
			    alert.setMessage("checking");
			    jk=in.getAllContacts2();
				String val="";
				int kl=0;
				Date d=new Date();
				System.out.println(d);
				 day_class=d.getDay();
			System.out.println("In services "+day_class);
			holidates=new Date[100];
			   dateformat = new SimpleDateFormat("dd/MM/yyyy"); 
			   String dat=dateformat.format(d);
			   calculate_holidays();
			   for(int i=0;i<length;i++)
			   {
				   System.out.println("comparing");
				   System.out.println(d.toString()+holidates[i]);
				   if(dat.equalsIgnoreCase(dateformat.format(holidates[i])))
				   {
					   
					   is_holiday="true";
					   break;
				   }
				   else
				   {
					   System.out.println("false");
				   }
			   }
			if(!(day_class==0 || day_class==6) && is_holiday=="false" )
			{
				
				if(jk.moveToFirst() && jk.getCount() >= 1){
					  do{
						 
						 Log.d("inside my function3","yes");
						 if(day_class==1)
						 {
						 val=jk.getString(10);
						 }
						 if(day_class==2)
						 {
						 val=jk.getString(11);
						 }
						 if(day_class==3)
						 {
						 val=jk.getString(12);
						 }
						 if(day_class==4)
						 {
						 val=jk.getString(13);
						 }
						 if(day_class==5)
						 {
						 val=jk.getString(14);
						 }
						
						 int foo=Integer.parseInt(val);
						 if(foo!=0)
						 {
							// subs[kl]=jk.getString(1);
							 length_classes++;
						 }
						
						 
						// Log.d("hoi rahul",value);

					}while(jk.moveToNext());
					//  kl=0;
				}
				
				
				subs=new String[length_classes];
				timings=new String[length_classes];
				tim_slot=new String[length_classes];
				slots=new String[length_classes];
				time_for_silent=new String[length_classes];
				great=new int[length_classes];
				length_classes=0;
			}
			
			if(!(day_class==0 || day_class==6) && is_holiday=="false" )
			{
				
				if(jk.moveToFirst() && jk.getCount() >= 1){
					  do{
						 
						 Log.d("inside my function3","yes");
						 if(day_class==1)
						 {
						 val=jk.getString(10);
						 }
						 if(day_class==2)
						 {
						 val=jk.getString(11);
						 }
						 if(day_class==3)
						 {
						 val=jk.getString(12);
						 }
						 if(day_class==4)
						 {
						 val=jk.getString(13);
						 }
						 if(day_class==5)
						 {
						 val=jk.getString(14);
						 }
						
						 int foo=Integer.parseInt(val);
						 if(foo!=0)
						 {
							 subs[kl]=jk.getString(1);
							 timings[kl]=String.valueOf(foo);
							 great[kl]=foo;
							 slots[kl]=jk.getString(2);
							 //great[kl]=jk.getSt
							 kl++;
						 }
						
						 
						// Log.d("hoi rahul",value);

					}while(jk.moveToNext());
					  kl=0;
				}
			}
			
			
			start=0;
			
		 }
	@Override
	public void onDestroy()
	{
		
		super.onDestroy();
		if(timer!=null)
		{
			timer.cancel();
		}
		Toast.makeText(this, "service destroyed", Toast.LENGTH_SHORT).show();
	}

}

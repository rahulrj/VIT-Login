
package com.googlecode.android.widgets.DateSlider;
import android.app.ActivityManager;

import android.app.Dialog;
import android.app.Service;
import android.app.ActivityManager.RunningServiceInfo;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
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

public class MyService extends Service
{
	int counter=0;
	
	static final int UPDATE_INTERVAL=60000;
	private Timer timer=new Timer();
	DBAdapter inf;
	Cursor x,jk;
	String dayOfTheWeek="";
	Calendar cal= Calendar.getInstance();
	int day2;
	int len1;
	String[] array;
	int[] great;
	int greatest;
	String alarmtime;
	Date date3;
	Date date;
	long diffInMins;
	String sub;
	String[] subs;
	Dialog dialo;
	int counte;
	ListView menulist1;
	Button b1;
	
	private ArrayAdapter<String> listAdapter ;
	
	
	

	@Override
	public IBinder onBind(Intent arg0)
	{
		return null;
	}


	
	@Override
	public int onStartCommand(Intent intent,int flags,int startId)
	{
		Toast.makeText(this, "service started", Toast.LENGTH_SHORT).show();
		dosomething();
		return START_STICKY;
	}
	
	
	
	private void dosomething()
	{
		
		
		timer.scheduleAtFixedRate(new TimerTask(){
			public void run()
			{
				
				
				Log.d("my service",String.valueOf(++counter));
				//Toast.makeText(this, "service started", Toast.LENGTH_SHORT).show();
		
	    	    ActivityManager manager = (ActivityManager) getSystemService(Context.ACTIVITY_SERVICE);
	    	    for (RunningServiceInfo service : manager.getRunningServices(Integer.MAX_VALUE)) {
	    	        if (MyService.class.getName().equals(service.service.getClassName())) {
	    	            
	    	        	Log.d("rahul","service running");
	    	        }
	    	    }
	    	    attndnc();
				
			}
		}, 20000, UPDATE_INTERVAL);
	}
	
	void attndnc()
	{
		
		inf=new DBAdapter(this);
		inf.open();
	    x=inf.getAllContacts();
	    day2 = cal.get(Calendar.DAY_OF_WEEK);
	    displayday(day2);
	 
	    Log.d("the day is",dayOfTheWeek);
	    
	   // if(dayOfTheWeek.equalsIgnoreCase("Friday"))
	 	{
	 		Log.d("inside my function","yes");
	 		//m=info.getAllContacts();
	 		len1=x.getCount();
	 		Log.d("its length",String.valueOf(len1));
	 
	 		array=new String[len1];
	 		great=new int[len1];
	 		
	 		
	 		Log.d("inside my function2","yes");
	 		int rr=0;
 			String value;
	 		
 			
 			if(x.moveToFirst() && x.getCount() >= 1){
 				  do{
 					 
 					 Log.d("inside my function3","yes");
 					  
 					 value=x.getString(0);
					 int foo=Integer.parseInt(value);
					 if(foo!=0)
					 {
						 counte++;
					 }
					 great[rr]=foo;
					 rr++;
					 
					 Log.d("hoi rahul",value);

 				}while(x.moveToNext());
 			}
	 	}
	    
	    greatest=great[0];
	 	for(int kk=0;kk<len1;kk++)
	 	{
	 	
	 	if(great[kk]>greatest)
	 	{
	 		greatest=great[kk];
	 	}
	 		
	 	}
 			
	 	 //subs=new String[counte];
	 		
	 	alarmtime=slotToTiming(greatest,"s");
		 Log.d("the alarm time is",alarmtime);
		 //String str = "08:03:10 pm";
		 SimpleDateFormat formatter = new SimpleDateFormat("hh:mm:ss a");
		 
		 
		 
		 
		 
		 try {
		 date = (Date)formatter.parse(alarmtime);
		 Log.d("date1 is","rahul"+date);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss a");
		Date date2=new Date();
		String dat=dateFormat.format(date2);
		
		try {
			date3 = (Date)formatter.parse(dat);
			Log.d("date 2 is","rahul"+date3);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	diffInMins = (date3.getTime() - date.getTime()) / 60000;
	Log.d("the diffrnce is","rahul"+diffInMins);
	inf.close();
	 		
		//if(diffInMins==(long)75 && dayOfTheWeek.equalsIgnoreCase("Friday"))
		{
		
		
		show();
		
		/*dialo=new Dialog(this);
		dialo.setContentView(R.layout.post_att);
		 menulist1=(ListView) dialo.findViewById(R.id.post_att1);
		 listAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_multiple_choice, subs);
		 menulist1.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
		 menulist1.setAdapter(listAdapter);
		 dialo.show();
		 
		
		 b1=(Button)dialo.findViewById(R.id.buttonok);*/

		 	/*b1.setOnClickListener(new View.OnClickListener() {
				public void onClick(View v)
				{
					SparseBooleanArray checked = menulist1.getCheckedItemPositions();
					 ArrayList<String> selectedItems = new ArrayList<String>();
					 for (int i = 0; i < checked.size(); i++) {
				            // Item position in adapter
				            int position = checked.keyAt(i);
				            // Add sport if it is checked i.e.) == TRUE!
				            if (checked.valueAt(i))
				                selectedItems.add(listAdapter.getItem(position));
					 }
				            
				            
				            String[] outputStrArr = new String[selectedItems.size()];
				            
				            for (int i = 0; i < selectedItems.size(); i++) {
				                outputStrArr[i] = selectedItems.get(i);
				            }
				        
				 
					  
				}
			});*/
		 
		
		}
		
		
		
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
	
	public void displayday(int day)
	 {
			if(day2 == 2){
		        dayOfTheWeek = "Monday";           
		    } else if (day2 == 3){
		        dayOfTheWeek = "Tuesday";
		    } else if (day2 == 4){
		        dayOfTheWeek = "Wednesday";
		    } else if (day2 == 5){
		        dayOfTheWeek = "Thursday";
		    } else if (day2 == 6){
		        dayOfTheWeek = "Friday";
		    } else if (day2 == 7){
		        dayOfTheWeek = "Saturday";
		    } else if (day2 == 1){
		        dayOfTheWeek = "Sunday";
		    }
		 
		 	 
	 }
	
	public void show()
	{
		global.service=1;
	
		//Intent trIntent = new Intent("android.intent.action.MAIN");
		Intent trIntent=new Intent(MyService.this,BackGroundDialogs.class);
		trIntent.putExtra("key",counte);
		//trIntent.setClass(this, BackGroundDialogs.class);
		trIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);        			
	        this.startActivity(trIntent);        	   
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


package com.googlecode.android.widgets.DateSlider;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import com.googlecode.android.widgets.DateSlider.MarkAttendance.ViewHolder;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.graphics.drawable.ColorDrawable;
import android.media.AudioManager;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class BackGroundDialogs extends Activity {
	
	Cursor jk;
	DBAdapter in;
	int value;
	Dialog dialo;
	ListView menulist1;
	Button b1;
	String[] subs;
	Date[] holidates;
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
	boolean[] arr;
	 Date date,date3;
	 long diffInMins;
	ViewHolder holder;
	AudioManager am;
	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.back);
		
		Bundle extras = getIntent().getExtras();
		if (extras != null) {
		     subs = extras.getStringArray("key");
		}

		
		
		 
		
		
		arr=new boolean[subs.length];
		for(int i=0;i<subs.length;i++)
			arr[i]=false;
		dialo=new Dialog(this);
		dialo.setContentView(R.layout.background);
		dialo.getWindow().setLayout(300, 400);
	      dialo.getWindow().setBackgroundDrawable(new ColorDrawable(0));
	      Button post=(Button)findViewById(R.id.today_post);
	      Button cancel=(Button)findViewById(R.id.today_cancel);
	      dateformat = new SimpleDateFormat("dd-MMM-yyyy"); 
	 	 
		     
		 menulist1=(ListView) dialo.findViewById(R.id.back_subjects);
		// listAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_multiple_choice, subs);
		
		 // menulist1.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
		 SpecialAdapter a=new SpecialAdapter(this,subs);
		 
		 menulist1.setAdapter(a);
		 dialo.show();
		 
		
		 b1=(Button)dialo.findViewById(R.id.postattnd);
		 DBAdapter db=new DBAdapter(this);
		 db.open();
		 Cursor c=db.getAllContacts2();
		 global.serv_sub=new String[c.getCount()];
		 int i=0;
		 if(c.moveToFirst())
		 {
			 do
			 {
				 global.serv_sub[i]=c.getString(1).replaceAll("\\s","");
				 i++;
			 }while(c.moveToNext());
		 }
		 i=0;
		 db.close();
		 
	 final Student stud=new Student(getApplicationContext());
		stud.open();
		 
		 
		b1.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v)
			{

				Student stud=new Student(getApplicationContext());
				stud.open();
		
					for (int i = 0; i < arr.length; i++)
					{
						
					 if(arr[i]==true)
					 {
				int x=	stud.insert(dateformat.format(new Date()), subs[i].replaceAll("\\s",""), "Present");
				if(x==-1)
				{
					stud.update(dateformat.format(new Date()), subs[i].replaceAll("\\s",""), "Present");	
				}
					 }
					 else
					 {
							int x=	stud.insert(dateformat.format(new Date()), subs[i].replaceAll("\\s",""), "Absent");
							if(x==-1)
							{
								stud.update(dateformat.format(new Date()), subs[i].replaceAll("\\s",""), "Absent");	
							}
			 
					 }
					}
				stud.close();
			for(int i=0;i<arr.length;i++)
			{
				arr[i]=false;
			}
			for(int i=0;i<arr.length;i++)
			{
				System.out.println(subs[i]);
				System.out.println(arr[i]);
			}
	dialo.cancel();
			}
			});
	 	 	
				
	  
	}
	
	void show()
	{
		
		Intent intent = new Intent(this, sample.class);
		intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
		startActivity(intent);
		finish();
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
			 Log.d("date1 is","rahul"+date);
			 Date date2=new Date();
				String date22=formatter.format(date2);
				date3 = (Date)formatter.parse(date22);
				
				diffInMins = (date3.getTime() - date.getTime()) / 60000;
				if(diffInMins>=(long)0 && diffInMins<=(long)Integer.parseInt(time_for_silent[i]))
				{
					am.setRingerMode(AudioManager.RINGER_MODE_SILENT);

				}
				else
					am.setRingerMode(AudioManager.RINGER_MODE_NORMAL);
				
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
	 }
	 
}
public class ViewHolder
{
	TextView course_title;  
	CheckBox chk;
}
	 private class SpecialAdapter extends BaseAdapter {
			private LayoutInflater mInflater;

			//The variable that will hold our text data to be tied to list.
			private String[] data;
			private boolean[] chek;
			public SpecialAdapter(Context context, String[] items) {
			    mInflater = LayoutInflater.from(context);
			    this.data = items;
			 this.chek=chek;
			}

			public int getCount() {
			    return data.length;
			   
			}

			public Object getItem(int position) {
			    return position;
			}

			public long getItemId(int position) {
			    return position;
			}

			//A view to hold each row in the list
		
			public View getView(final int position, View convertView, ViewGroup parent) {
 
			// A ViewHolder keeps references to children views to avoid unnecessary calls
			// to findViewById() on each row.
		
			if (convertView == null) {
			    convertView = mInflater.inflate(R.layout.custom_line2, null);
              convertView.setMinimumHeight(70);
			    holder = new ViewHolder();
			    holder.course_title = (TextView) convertView.findViewById(R.id.customline_text2);
			    holder.course_title.setTextColor(getResources().getColor(android.R.color.black));
			    holder.chk=(CheckBox)convertView.findViewById(R.id.chkatt);
		    
			    
			    convertView.setTag(holder);
			} else {
			    holder = (ViewHolder) convertView.getTag();
			}
			
			    // Bind the data efficiently with the holder.
				holder.course_title.setText(data[position]);
			
				holder.chk.setOnClickListener(new OnClickListener() {
			           public void onClick(View arg0) {
			            if(arr[position]==false)
			            {
			            	arr[position]=true;
			            	
			            }
			            else if(arr[position]==true)
			            	arr[position]=false;
			            System.out.println(arr[position]);
			            System.out.println(subs[position]);
			            Log.d("value",String.valueOf(arr[position]));
			            Log.d("subject",subs[position]);
					      
			           }
			         });
				
		
			
		
			   return convertView;
			}

			}
	
}
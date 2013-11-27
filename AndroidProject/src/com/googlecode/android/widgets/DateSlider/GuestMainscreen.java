package com.googlecode.android.widgets.DateSlider;




import java.util.ArrayList;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.text.ParseException;
import com.commonsware.cwac.merge.MergeAdapter;
import com.googlecode.android.widgets.DateSlider.Mainscreen.ViewHolder1;

import com.googlecode.android.widgets.DateSlider.SimpleGestureFilter.SimpleGestureListener;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.gesture.GestureOverlayView;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.BaseAdapter;
import android.widget.AdapterView.OnItemClickListener;

public class GuestMainscreen extends Activity implements SimpleGestureListener
{
	TextView bunk_display,bunk_sub;
	Dialog dialog;
	SpecialAdapter adapter;
	SimpleGestureFilter detector;
	String[] items = { "Test text 1", "Test text 2", "Test text 3", "Test text 4", "Test text 5", "Test text 6" }; 
	String[] course_title;
	String[] title;
	int count_attend=0;
	int count_bunk=0;
	String[] course_type;
	String[] course_slot;
	String[] course_percent;
	String[] course_classes;
	String[] classes;
	String[] course_marks;
	String[] course_attended;
	String[] course_total;
	String[] course_title_today;
	String[] course_title_nottoday;
	String[] marks_today;
	String[] marks_nottoday;
	int[][] term_class;
	ViewHolder holder;
	String[] course_slot_today;
	String[] course_slot_nottoday;
	String[] course_percent_today;
	String[] course_percent_nottoday;
	String[] course_attended_today;
	String[] course_total_today;
	String[] course_attended_notoday;
	String[] course_total_notoday;
	String[] course_classes_today;
	String[] course_classes_nottoday;
	Dialog poss_att;
	String[] course_time;
	String[] quiz1,quiz2,quiz3,cat1,cat2,assn,obtained,total,attended,total_class;
	String b_subject,b_attended,b_total;
	int[] sub_dates;
	Date comm_cat1;
	Date comm_cat2;
	Date comm_termend;
	String[] slots;
	Date[]dates,holidates,classdates,classdates2,classdates3,cat1_dates,cat2_dates,term_dates;
	int[] course_num;
	ListView list,nolist,menulist;
	TextView l,s;
	GDBAdapter info;
	GDBAdapter5 dba;
	Calendar cal= Calendar.getInstance();
	private ArrayAdapter<String> listAdapter ;
	int day2;
	String dayOfTheWeek="";
	TextView day_of_the_week;
	Cursor c,m;
	int len,len1;
	String[] array;
	int[] great;
	int greatest;
	String alarmtime;
	DBAdapter3 info3;
	Cursor xy;
	int lenr;
	long day,diff;
	int mk2=0;
	int mk=0;
	int mk3=0;
	int mk4=0;
	int mk5=0;
	int mk6=0;
	int curr_attended=0;
 	int curr_total=0;
 	Typeface face1,face2,face3,face4,face5;
	int classtoday,classnottoday;
	String[] mon,tues,wed,thurs,fri,marks;
	SimpleDateFormat dateformat;
	Date fromdate,todate;
	int globalcount;
	int kkk=0;
	MergeAdapter merge;
	@Override
	 protected void onCreate(Bundle savedInstanceState) {
	    //TODO Auto-generated method stub
	 	super.onCreate(savedInstanceState);
	 	 requestWindowFeature(Window.FEATURE_CUSTOM_TITLE);
		 	
	 	setContentView(R.layout.custom_list);
	 	  getWindow().setFeatureInt(Window.FEATURE_CUSTOM_TITLE, R.layout.windowtitle);
	        Button options=(Button)findViewById(R.id.options);
	        Button refresh=(Button)findViewById(R.id.refresh);
	       options.setVisibility(View.INVISIBLE);
	       refresh.setVisibility(View.INVISIBLE);
	   
	       
	 	Bundle b=getIntent().getExtras();
	 	   dateformat = new SimpleDateFormat("dd/MM/yyyy"); 
			dates=new Date[200];
			holidates=new Date[200];
			classdates=new Date[200];
			classdates2=new Date[200];
			classdates3=new Date[200];
	 	dba=new GDBAdapter5(this);
	 	dba.open();
	    detector = new SimpleGestureFilter(this,this); 

		GDBAdapter db2=new GDBAdapter(this);
		db2.open();
		Cursor c2=db2.getData();
		globalcount=c2.getCount();
		term_class=new int[c2.getCount()][3];
		mon=new String[c2.getCount()];
		tues=new String[c2.getCount()];
		wed=new String[c2.getCount()];
		thurs=new String[c2.getCount()];
		fri=new String[c2.getCount()];
		sub_dates=new int[c2.getCount()];
		quiz1=new String[c2.getCount()];
		quiz2=new String[c2.getCount()];
		quiz3=new String[c2.getCount()];
		cat1=new String[c2.getCount()];
		cat2=new String[c2.getCount()];
		assn=new String[c2.getCount()];
		obtained=new String[c2.getCount()];
		total=new String[c2.getCount()];
		marks=new String[c2.getCount()];
		attended=new String[c2.getCount()];
		total_class=new String[c2.getCount()];
		title=new String[c2.getCount()];

		slots=new String[c2.getCount()];
		  face1=Typeface.createFromAsset(getAssets(),"fonts/KentuckyFriedChickenFont.ttf");
	      face2=Typeface.createFromAsset(getAssets(),"fonts/Titania.ttf");
	      face3=Typeface.createFromAsset(getAssets(),"fonts/VeryChristmess.ttf");
	      face4=Typeface.createFromAsset(getAssets(),"fonts/nevis.ttf");
	      face5=Typeface.createFromAsset(getAssets(),"fonts/KentuckyFriedChickenFont.ttf");
		
		/*attended=b.getStringArray("attended");
	 	total_class=b.getStringArray("total");*/
	 //	System.out.println("anshul jain"+attended[0]);
	 
	MergeAdapter merge=new MergeAdapter();
		
		int k=0;
		if(c2.moveToFirst())
		{
			do
			{
				slots[k]=c2.getString(2);
			mon[k]=c2.getString(10);	
			tues[k]=c2.getString(11);
			wed[k]=c2.getString(12);
			thurs[k]=c2.getString(13);
			fri[k]=c2.getString(14);
			quiz1[k]=c2.getString(4);
			quiz2[k]=c2.getString(5);
			quiz3[k]=c2.getString(6);
			cat1[k]=c2.getString(7);
			cat2[k]=c2.getString(8);
			assn[k]=c2.getString(9);
			attended[k]=c2.getString(15);
			total_class[k]=c2.getString(16);
			obtained[k]=Integer.toString(0);
			total[k]=Integer.toString(0);
			if(!(quiz1[k].equalsIgnoreCase("")))
			{
				double marks=Double.parseDouble(quiz1[k]);
			
				obtained[k]=Double.toString(marks+Double.parseDouble(obtained[k]));
				total[k]=Double.toString(5+Double.parseDouble(total[k]));
				
				
			}
			if(!(quiz2[k].equalsIgnoreCase("")))
			{
				double marks=Double.parseDouble(quiz2[k]);
				obtained[k]=Double.toString(marks+Double.parseDouble(obtained[k]));
				total[k]=Double.toString(5+Double.parseDouble(total[k]));
				
			}if(!(quiz3[k].equalsIgnoreCase("")))
			{
				double marks=Double.parseDouble(quiz3[k]);
				obtained[k]=Double.toString(marks+Double.parseDouble(obtained[k]));
				total[k]=Double.toString(5+Double.parseDouble(total[k]));
				
			}if(!(cat1[k].equalsIgnoreCase("")))
			{
				double marks=Double.parseDouble(cat1[k]);
				marks=(marks*15)/50;
				obtained[k]=Double.toString(marks+Double.parseDouble(obtained[k]));
				total[k]=Double.toString(15+Double.parseDouble(total[k]));
				
			}if(!(cat2[k].equalsIgnoreCase("")))
			{
				double marks=Double.parseDouble(cat2[k]);
				marks=(marks*15)/50;
				obtained[k]=Double.toString(marks+Double.parseDouble(obtained[k]));
				total[k]=Double.toString(15+Double.parseDouble(total[k]));
				
			}if(!(assn[k].equalsIgnoreCase("")))
			{
				double marks=Double.parseDouble(assn[k]);
				obtained[k]=Double.toString(marks+Double.parseDouble(obtained[k]));
				total[k]=Double.toString(5+Double.parseDouble(total[k]));
				
			}
		System.out.println(quiz1[k]);
		System.out.println(quiz2[k]);
		System.out.println(quiz3[k]);
		System.out.println(cat1[k]);
		System.out.println(cat2[k]);
		System.out.println(assn[k]);
		System.out.println(obtained[k]+"/"+total[k]);
	marks[k]=obtained[k]+"/"+total[k];
			k++;
			}while(c2.moveToNext());
		}
		db2.close();
	
//	calculate_days();
	try {
		calculate_hoidays();
	} catch (ParseException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	 	
		 info = new GDBAdapter(this);
		info.open();
		c=info.getData();
		 len=c.getCount();
		 System.out.println("place1");
		System.out.println(len);
		int i=0;
		System.out.println("place2");
		course_title=new String[len];
		 course_type=new String[len];
		 course_slot=new String[len];
		 course_percent=new String[len];
		 course_classes=new String[len];
		 classes=new String[len];
		 course_marks=new String[len];
		 course_attended=new String[len];
		 course_total=new String[len];
			
		 course_time=new String[len];
		 course_num=new int[len];
		 dialog=new Dialog(this);
		 dialog.setContentView(R.layout.dialog2);
		dialog.getWindow().setBackgroundDrawable(new ColorDrawable(0));
		      
		 menulist=(ListView) dialog.findViewById(R.id.dialog_menu2);
		 String menu[]={"Attendance","Marks","Possible Attendance"};
		// listAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, menu);
		 adapter x=new adapter(this,menu);
			
		 menulist.setAdapter(x);
			day_of_the_week = (TextView) findViewById(R.id.day_of_the_week);
		 	day2 = cal.get(Calendar.DAY_OF_WEEK);
		 	System.out.println("place3");
		 	
		 	
	 calculate_days("Term End Theory");
		 calculate_days("CAT1");
		 	calculate_days("CAT2");
		 bunk_classes();
		 displayday(day2);
			

		 	
		 	Button left=(Button)findViewById(R.id.arrow_left);
		 	Button right=(Button)findViewById(R.id.arrow_right);
		 	
		 	
		 	left.setOnClickListener(new View.OnClickListener() {
				public void onClick(View v)
				{
			day2=day2-1;
				if(day2==0)
					day2=7;
				displayday(day2);
				settimetable(day2);
		//	calculate_days("CAT1");
			//calculate_days("CAT2");		
			////System.out.println(dates[0]);	  
				}
			});
		 	
		 	right.setOnClickListener(new View.OnClickListener() {
				public void onClick(View v)
				{
					//System.out.println(holidates[0]);
					try {
						calculate_hoidays();
					} catch (ParseException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				//	calculate_classes();
				day2=day2+1;
				if(day2==8)
					day2=1;
				displayday(day2);
				settimetable(day2);
					  
					}
			});
		   menulist.setOnItemClickListener(new OnItemClickListener() {

	            public void onItemClick(AdapterView<?> arg0, View view, int position,
	                    long id) {
	            	
	            System.out.println("postion"+position);
	            
	            
	            if(position==0)
	            {
	            	Intent i= new Intent(getApplicationContext(), GAttendance.class);
	       		 
	       		startActivity(i);
  	
	            }
	            else if(position==1)
	            {
	            	Intent i= new Intent(getApplicationContext(), GMarks.class);
		       		 
		       		startActivity(i);
	  	   	
	            }
	            else if(position==2)
	            {
	            	dialog.cancel();
	            	curr_attended=0;
	            	curr_total=0;
	            	bunk_sub.setText(b_subject);
	            	curr_attended=Integer.parseInt(b_attended);
	    		 	curr_total=Integer.parseInt(b_total);
	    		 	if(!(curr_total==0))
	    			bunk_display.setText(b_attended+ "/"+b_total+" ="+Integer.toString(curr_attended*100/curr_total));
					
	            	poss_att.show();
	            }
	        
	            
	                         }
	        });

		 
			int day = cal.get(Calendar.DAY_OF_WEEK);
			 settimetable(day);
		
			
	 	
	    }
	 
	 static class ViewHolder {
	        TextView course_title;
	        TextView course_slot;
	        TextView course_percent;
	        TextView course_classes;
	        TextView course_marks;
	        
	    }
	 static class noViewHolder {
	        TextView course_notitle;
	        TextView course_noslot;
	        TextView course_nopercent;
	        TextView course_noclasses;
	        TextView course_nomarks;
	        
	    }

	
	 private class SpecialAdapter extends BaseAdapter {
			private LayoutInflater mInflater;

			//The variable that will hold our text data to be tied to list.
			private String[] data;
		    private String[] data2;
		    private String[] data3;
		    private String[] data4;
		    private String[] data5;
			public SpecialAdapter(Context context, String[] items,String[] items2,String[] items3,String[] items4) {
			    mInflater = LayoutInflater.from(context);
			    this.data = items;
			    this.data2 = items2;
			    this.data3 = items3;
			    this.data4=items4;
			 
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
			public View getView(int position, View convertView, ViewGroup parent) {

			// A ViewHolder keeps references to children views to avoid unnecessary calls
			// to findViewById() on each row.
		
			if (convertView == null) {
			    convertView = mInflater.inflate(R.layout.custom_row, null);
                convertView.setMinimumHeight(70);
			    holder = new ViewHolder();
			    holder.course_title = (TextView) convertView.findViewById(R.id.title);
			    holder.course_slot = (TextView) convertView.findViewById(R.id.slot);
			    holder.course_percent = (TextView) convertView.findViewById(R.id.percent);
			    holder.course_classes = (TextView) convertView.findViewById(R.id.classes);
			    holder.course_marks = (TextView) convertView.findViewById(R.id.marks);
			    
			    
			    convertView.setTag(holder);
			} else {
			    holder = (ViewHolder) convertView.getTag();
			}
			
			    // Bind the data efficiently with the holder.
				holder.course_title.setText(data[position]);
			    holder.course_slot.setText(data2[position]);
			    holder.course_percent.setText(data3[position]);
			    holder.course_marks.setText(data4[position]);
			    holder.course_classes.setText(course_classes_today[position]);

			 //   holder.course_classes.setText(data4[position]);
			  //  holder.course_marks.setText(data5[position]);

			   return convertView;
			}

			}
	 
	 private class SpecialAdapter2 extends BaseAdapter {
			private LayoutInflater mInflater;

			//The variable that will hold our text data to be tied to list.
			private String[] data;
		    private String[] data2;
		    private String[] data3;
		    private String[] data4;
		    private String[] data5;
			public SpecialAdapter2(Context context, String[] items,String[] items2,String[] items3,String[] items4) {
			    mInflater = LayoutInflater.from(context);
			    this.data = items;
			    this.data2 = items2;
			    
			    this.data3 = items3;
			    this.data4=items4;
			 
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
			public View getView(int position, View convertView, ViewGroup parent) {

			// A ViewHolder keeps references to children views to avoid unnecessary calls
			// to findViewById() on each row.
			noViewHolder noholder;

			if (convertView == null) {
			    convertView = mInflater.inflate(R.layout.custom_row2, null);
			    convertView.setMinimumHeight(70);
			    noholder = new noViewHolder();
			    noholder.course_notitle = (TextView) convertView.findViewById(R.id.notitle);
			    noholder.course_noslot = (TextView) convertView.findViewById(R.id.noslot);
			    noholder.course_nopercent = (TextView) convertView.findViewById(R.id.nopercent);
			    noholder.course_noclasses = (TextView) convertView.findViewById(R.id.noclasses);
			    noholder.course_nomarks = (TextView) convertView.findViewById(R.id.nomarks);
			    
			    
			    convertView.setTag(noholder);
			} else {
			    noholder = (noViewHolder) convertView.getTag();
			}
			
			    // Bind the data efficiently with the holder.
				noholder.course_notitle.setText(data[position]);
				System.out.println(data2[position]+"heheh");
			    noholder.course_noslot.setText(data2[position]);
			    noholder.course_nopercent.setText(data3[position]);
			 //   holder.course_classes.setText(data4[position]);
			    noholder.course_nomarks.setText(data4[position]);
			    noholder.course_noclasses.setText(course_classes_nottoday[position]);

			   return convertView;
			}

			}
 
	 public String slotToTiming(int slotNum, String slot) {
		 System.out.println("slotnum"+slotNum);
			if (slotNum <= 30){
				if (slotNum%6 == 1)
					return "08:00 am";
				else if (slotNum%6 == 2)
					return "09:00 am";
				else if (slotNum%6 == 3)
					return "10:00 am";
				else if (slotNum%6 == 4)
					return "11:00 am";
				else if ((slotNum%6 == 5)&&(slot.charAt(0)=='L'))
					return "11:50 am";
				else if (slotNum%6 == 5)
					return "12:00 am";
			}
			else {
				if (slotNum%6 == 1)
					return "02:00 pm";
				else if (slotNum%6 == 2)
					return "03:00 pm";
				else if (slotNum%6 == 3)
					return "04:00 pm";
				else if (slotNum%6 == 4)
					return "05:00 pm";
				else if ((slotNum%6 == 5)&&(slot.charAt(0)=='L'))
					return "05:50 pm";
				else if (slotNum%6 == 5)
					return "06:00 pm";
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
	 	day_of_the_week.setText(dayOfTheWeek);
	 	 
 }
 public void settimetable(int day)
 {
	
	 		System.out.println("check");
	 		int i=0;
	 		if(c.moveToFirst())
			{
				do
				{
					title[i]=c.getString(1);
				course_title[i]=c.getString(1);
				course_slot[i]=c.getString(2);
		
				course_percent[i]=c.getString(3);
				course_marks[i]=marks[i];
				course_attended[i]=attended[i];
				course_total[i]=total_class[i];
				
			
				
				if(day==2)
				course_num[i]=Integer.parseInt(c.getString(10));
				else if(day==3)
				course_num[i]=Integer.parseInt(c.getString(11));
				else if(day==4)
					course_num[i]=Integer.parseInt(c.getString(12));
				else if(day==5)
					course_num[i]=Integer.parseInt(c.getString(13));
				else if(day==6)
					course_num[i]=Integer.parseInt(c.getString(14));
				else if(day==7)
					course_num[i]=0;
				else if(day==1)
					course_num[i]=0;
					
				
			
					i++;
				}while(c.moveToNext());
			}
	 		
	 		int temp;
	 		String name,slot,percent,mark,attend,total,classe;
	 		for(int ii=0;ii<len;ii++)
	 		{
	 			course_classes[ii]=classes[ii];
	 		}
	 		for(int j=0;j<len;j++)
	 		{
	 			for(int k=0;k<len;k++)
	 			{
	 		if(course_num[j]<course_num[k])
	 		{
	 			temp=course_num[j];
	 			course_num[j]=course_num[k];
	 			course_num[k]=temp;
	 			
	 			name=course_title[j];
	 			course_title[j]=course_title[k];
	 			course_title[k]=name;
	 
	 			
	 			slot=course_slot[j];
	 			course_slot[j]=course_slot[k];
	 			course_slot[k]=slot;
	 
	 			
	 			percent=course_percent[j];
	 			course_percent[j]=course_percent[k];
	 			course_percent[k]=percent;
	 			
	 			mark=course_marks[j];
	 			course_marks[j]=course_marks[k];
	 			course_marks[k]=mark;
	 			
	 			attend=course_attended[j];
	 			course_attended[j]=course_attended[k];
	 			course_attended[k]=attend;
	 			
	 			total=course_total[j];
	 			course_total[j]=course_total[k];
	 			course_total[k]=total;
	 			
	 			classe=course_classes[j];
	 			course_classes[j]=course_classes[k];
	 			course_classes[k]=classe;
	 
	 
	 
	 
	 		}
	 			}
	 		}
	 		int today=0;
	 		int nottoday=0;
	 		System.out.println("course numbers after sorting");
	 		int count_classes,count_noclasses;
	 		count_classes=0;
	 		count_noclasses=0;
	 		for(int count=0;count<len;count++)
	 		{
	 			System.out.println(course_title[count]);
	 			System.out.println(course_num[count]);
	 			System.out.println(course_classes[count]);
	 			if(course_num[count]==0)
	 			{
	 			count_noclasses++;	
	 			}
	 			else
	 				count_classes++;
	 		}
	 		classtoday=count_classes;
	 		classnottoday=count_noclasses;
	 		 course_title_today=new String[count_classes];
			 course_slot_today=new String[count_classes];
			 course_percent_today=new String[count_classes];
			 marks_today=new String[count_classes];
			 course_attended_today=new String[count_classes];
			 course_total_today=new String[count_classes];
			 course_classes_today=new String[count_classes];
				
			 course_title_nottoday=new String[count_noclasses];
			 course_slot_nottoday=new String[count_noclasses];
			 course_percent_nottoday=new String[count_noclasses];
			 marks_nottoday=new String[count_noclasses];
			 course_attended_notoday=new String[count_noclasses];
			 course_total_notoday=new String[count_noclasses];
			 course_classes_nottoday=new String[count_noclasses];
				
	 		System.out.println("counting");
	 		System.out.println(count_classes);
	 		System.out.println(count_noclasses);
	 		for(int j=0;j<len;j++)
	 		{
	 		System.out.println(course_num[j]+course_slot[j]);
	 		if(course_num[j]==0)
	 		{
	 			
	 			course_title_nottoday[nottoday]=course_title[j];
	 			course_slot_nottoday[nottoday]=course_slot[j];
	 			course_percent_nottoday[nottoday]=course_percent[j];
	 			marks_nottoday[nottoday]=course_marks[j];
	 			course_attended_notoday[nottoday]=course_attended[j];
	 			course_total_notoday[nottoday]=course_total[j];
	 			course_classes_nottoday[nottoday]=course_classes[j];
	 			nottoday++;
	 			
	 		}
	 		else if(course_num[j]>0)
	 		{
	 			course_title_today[today]=course_title[j];
	 			course_slot_today[today]=slotToTiming(course_num[j],course_slot[j]);
	 			course_percent_today[today]=course_percent[j];
	 			marks_today[today]=course_marks[j];
	 			course_attended_today[today]=course_attended[j];
	 			course_total_today[today]=course_total[j];
	 			course_classes_today[today]=course_classes[j];
	 			today++;
		
	 		}
	 	
	 		}
	 		System.out.println("today classes");
	 		for(int l=0;l<today;l++)
	 		{
	 			System.out.println(course_title_today[l]);
	 			System.out.println(course_classes_today[l]);
	 		}
	 		
	 		System.out.println("not today classes");
	 		for(int l=0;l<nottoday;l++)
	 		{
	 			System.out.println(course_title_nottoday[l]);
	 			System.out.println(course_classes_nottoday[l]);
	 		}
	 	
	 		  list = (ListView) findViewById(R.id.custom_list);

		        adapter = new SpecialAdapter(this,course_title_today,course_slot_today,course_percent_today,marks_today);
		        merge=new MergeAdapter();
		        merge.addAdapter(adapter);
		       
		       // list.setAdapter(adapter);
		       // list.setItemsCanFocus(false);
		        TextView add=new TextView(this);
		        if(classtoday==0)
		        	add.setHeight(0);
		        else
		        add.setHeight(40);
		      
		        add.setWidth(100);
		        add.setBackgroundResource(android.R.color.white);
			      
		        merge.addView(add);
		    
		      	poss_att=new Dialog(this);
		       	poss_att.setContentView(R.layout.bunk);
		       	poss_att.getWindow().setBackgroundDrawable(new ColorDrawable(0));
		        bunk_display=(TextView)poss_att.findViewById(R.id.bunk_display);
		        bunk_sub=(TextView)poss_att.findViewById(R.id.bunk_sub);
				Button  add1=(Button)poss_att.findViewById(R.id.add1);
				Button  add2=(Button) poss_att.findViewById(R.id.add2);
				Button  subtract1=(Button)poss_att.findViewById(R.id.subtract1);
				Button  subtract2=(Button) poss_att.findViewById(R.id.subtract2);

				add1.setOnClickListener(new View.OnClickListener() {
					public void onClick(View v)
					{
						b_attended=Integer.toString(curr_attended+1);
						b_total=Integer.toString(curr_total+1);
			curr_attended=curr_attended+1;
			curr_total=curr_total+1;
			
			 count_attend++;
			 if(!(curr_total==0))
			bunk_display.setText(b_attended+ "/"+b_total+" ="+Integer.toString(curr_attended*100/curr_total));
					 	 
					}
					
					
				});

				add2.setOnClickListener(new View.OnClickListener() {
					public void onClick(View v)
					{
					
						b_total=Integer.toString(curr_total+1);
			curr_attended=curr_attended+0;
			curr_total=curr_total+1;
			
			if(!(curr_total==0))
			bunk_display.setText(b_attended+ "/"+b_total+" ="+Integer.toString(curr_attended*100/curr_total));
					 	count_bunk++; 
					}
					
					
				});
				subtract1.setOnClickListener(new View.OnClickListener() {
					public void onClick(View v)
					{
						if(!(count_attend==0))
						{
		

						b_total=Integer.toString(curr_total-1);
						b_attended=Integer.toString(curr_attended-1);
						
					
						curr_total=curr_total-1;
						curr_attended=curr_attended-1;
						if(!(curr_total==0))	
					bunk_display.setText(b_attended+ "/"+b_total+" ="+Integer.toString(curr_attended*100/curr_total));
						count_attend--;
						}
								  
					}
					
					
				});

				
				subtract2.setOnClickListener(new View.OnClickListener() {
					public void onClick(View v)
					{
						if(!(count_bunk==0))
						{
						b_total=Integer.toString(curr_total-1);
						
						curr_attended=curr_attended+0;
						curr_total=curr_total-1;
						if(!(curr_total==0))	
					bunk_display.setText(b_attended+ "/"+b_total+" ="+Integer.toString(curr_attended*100/curr_total));
						count_bunk--;
						}
								  
					}
					
					
				});
			
			
		        list.setOnItemClickListener(new OnItemClickListener() {

		            public void onItemClick(AdapterView<?> arg0, View view, int position,
		                    long id) {
		            	//adapter.notifyDataSetChanged();
		            System.out.println(classtoday);
		            System.out.println(classnottoday);
		            System.out.println(position);
		            if(!(classtoday==0))
		            {
		            	if(position<classtoday)
		            	{
		            		System.out.println(course_title_today[position]);
		            		b_subject=course_title_today[position];
		            		b_attended=course_attended_today[position];
		            		b_total=course_total_today[position];
		            	}
		            	else
		            	{
		            		b_subject=course_title_nottoday[position-classtoday-1];
		            		b_attended=course_attended_notoday[position-classtoday-1];
		            		b_total=course_total_notoday[position-classtoday-1];
		            	
		            		System.out.println(course_title_nottoday[position-classtoday-1]);
		            	}
		            }
		            else
		            {
		            	b_subject=course_title_nottoday[position-1];
		            	b_attended=course_attended_notoday[position-1];
	            		b_total=course_total_notoday[position-1];
	            	
		            System.out.println(course_title_nottoday[position-1]);	
		            }
		            /*	System.out.println(course_title[position]);
		            	System.out.println(course_attended[position]);
		            	System.out.println(course_total[position]);
				        
		            	b_subject=course_title[position];
		            	b_attended=course_attended[position];
		            	b_total=course_total[position];*/
		            System.out.println(b_subject);
		            System.out.println(b_attended);
		            System.out.println(b_total);
		            	dialog.show();
		                         }
		        });
		      
				
	     info.close();
		        
		        
		 nolist = (ListView) findViewById(R.id.custom_nolist);

		        SpecialAdapter2 noadapter = new SpecialAdapter2(this,course_title_nottoday,course_slot_nottoday,course_percent_nottoday,marks_nottoday);
		     merge.addAdapter(noadapter);
		     list.setAdapter(merge);
		        
		        //  nolist.setAdapter(noadapter);
		      //  nolist.setItemsCanFocus(false);
	

	 	
	 
 }
 
 public void calculate_days(String type)
 {
	 int count1=0;
     int count2=0;
     int count3=0;
    
	String commence_sem="";
    Cursor c=dba.getDates();
	
	if(c.moveToFirst())
	{
		do
		{
			String from_date= c.getString(0);
			String to_date=c.getString(1);
			String desc=c.getString(2);
			String is_holiday=c.getString(3);
			System.out.println(desc);

	if(desc.equalsIgnoreCase("Commencement of Semester"))
	{
		System.out.println("comnece");
		commence_sem=c.getString(0);
		
		
	}
	else if(desc.equalsIgnoreCase(type))
	{
		System.out.println("in theory term end");
		from_date = c.getString(0);
		to_date=c.getString(1);
		System.out.println(from_date);
		System.out.println(to_date);
		 try {
			 System.out.println("in try block");   
			 fromdate = dateformat.parse(commence_sem);
			 todate=dateformat.parse(from_date);
			 System.out.println(fromdate);
			 System.out.println(todate);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			 
		} 
		long t1 = fromdate.getTime();
        long t2 = todate.getTime();
        Log.d("rahul","dayte convetred");
       Log.d("the day is",String.valueOf(t2-t1));
       day = 1000 * 60 * 60 * 24; // milliseconds in a day
       diff=(t2-t1)/day;
      // dates=new Date[100];
       System.out.println("Difference in cat"+diff);
       Calendar start = Calendar.getInstance();
       start.setTime(fromdate);
       Calendar end = Calendar.getInstance();
       end.setTime(todate);
       Calendar start2 = Calendar.getInstance();
       start2.setTime(fromdate);
       Calendar end2 = Calendar.getInstance();
       end2.setTime(todate);
     
       Log.d("rahul","alldone");
       for (; !start.after(end); start.add(Calendar.DATE, 1)) {
          	//Date current = start.getTime();
    	   System.out.println("going in for");
          	if(type.equalsIgnoreCase("CAT1"))
          	{
          		count1++;
          //	cat1_dates[mk]=current;
          	}
          	else if(type.equalsIgnoreCase("CAT2"))
          	{
          		count2++;
          		//cat2_dates[mk]=current;
          	}
          	else if(type.equalsIgnoreCase("Term End Theory"))
          	{
          		count3++;
          	//	term_dates[mk]=current;
          	}
          	
          
              // Do your job here.
          }
        
        cat1_dates=new Date[count1];
        cat2_dates=new Date[count2];
        term_dates=new Date[count3];
       int mk=0; 
       for (; !start2.after(end2); start2.add(Calendar.DATE, 1)) {
    	   System.out.println("going in 2nd for");
       	Date current = start2.getTime();
       	if(type.equalsIgnoreCase("CAT1"))
       	{
       	cat1_dates[mk]=current;
       	}
       	else if(type.equalsIgnoreCase("CAT2"))
       	{
       		cat2_dates[mk]=current;
       	}
       	else if(type.equalsIgnoreCase("Term End Theory"))
       	{
       		term_dates[mk]=current;
       	}
       	mk++;
       
           // Do your job here.
       }
       System.out.println("value of mk is "+mk);
       System.out.println("Displaying all the dates");
       if(type.equalsIgnoreCase("CAT1"))
       for(int i=0;i<mk;i++)
       {
      	 System.out.println(cat1_dates[i]);
       }
       if(type.equalsIgnoreCase("CAT2"))
           for(int i=0;i<mk;i++)
           {
          	 System.out.println(cat2_dates[i]);
           }
       if(type.equalsIgnoreCase("Term End Theory"))
           for(int i=0;i<mk;i++)
           {
          	 System.out.println(term_dates[i]);
           }


		}
			}while(c.moveToNext() );
		//dba.close();
	}
	  if(type.equalsIgnoreCase("CAT1"))
		  calculate_classes(count1,"CAT1");
	       if(type.equalsIgnoreCase("CAT2"))
	    	   calculate_classes(count2,"CAT2");
	       if(type.equalsIgnoreCase("Term End Theory"))
	    	   calculate_classes(count3,"Term End Theory");
	         
	      
 }
 
 public void calculate_hoidays() throws ParseException
 {
	 System.out.println("in calculate holidays");
	 		String commence_sem="";
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
			fromdate = dateformat.parse(from_date);
			if(desc.equalsIgnoreCase("CAT1"))
			{
				comm_cat1=todate;
			}
			if(desc.equalsIgnoreCase("CAT2"))
			{
				comm_cat2=todate;
			}
			if(desc.equalsIgnoreCase("Term End Theory"))
			{
				comm_termend=todate;
			}


			
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
	
			}while(c.moveToNext() );
		   for(int i=0;i<mk2;i++)
	       {
	      	 System.out.println(holidates[i]);
	       }
		   
		   
		
	 
 }
	mk6=mk2;
	mk2=0;
 }
 public void calculate_classes(int count,String type)
 {
	 int cnt=0;
	 int day=0;
 	 for(int i=0;i<count;i++)
	 {
 		 if(type.equalsIgnoreCase("CAT1"))
		 day=cat1_dates[i].getDay();
 		 else if(type.equalsIgnoreCase("CAT2"))
 			 day=cat2_dates[i].getDay();
 		 else if(type.equalsIgnoreCase("Term End Theory"))
 			 day=term_dates[i].getDay();

		// System.out.println(dates[i]+Integer.toString(day));
		 if(!(day==0 || day==6))
		 {
			 cnt++;
			// classdates[mk3]=dates[i];
			 
		 }
	 }
 	 System.out.println("Value of count is"+count);
 	 System.out.println("Value of cnt is"+cnt);
 	 Date[] tempdate=new Date[cnt];
 	 int mk3=0;
 	 for(int i=0;i<count;i++)
	 {
 		 if(type.equalsIgnoreCase("CAT1"))
 			 day=cat1_dates[i].getDay();
 	 		 else if(type.equalsIgnoreCase("CAT2"))
 	 			 day=cat2_dates[i].getDay();
 	 		 else if(type.equalsIgnoreCase("Term End Theory"))
 	 			 day=term_dates[i].getDay();

		// System.out.println(dates[i]+Integer.toString(day));
		 if(!(day==0 || day==6))
		 {
			 if(type.equalsIgnoreCase("CAT1"))
					 {
			 tempdate[mk3]=cat1_dates[i];
					 }
			 if(type.equalsIgnoreCase("CAT2"))
					 {
			 tempdate[mk3]=cat2_dates[i];
					 }
			 if(type.equalsIgnoreCase("Term End Theory"))
					 {
			 tempdate[mk3]=term_dates[i];
					 }
			
			 mk3++;
		 }
	 }
	
	 
	 System.out.println("Printing the attended classes");
	 System.out.println("value of mk3"+mk3);
	 for(int i=0;i<mk3;i++)
	 {
		 System.out.println(tempdate[i]);
	 }
	 ArrayList list1=new ArrayList();
	 ArrayList list2=new ArrayList();
	 for(int i=0;i<mk6;i++)
		 list1.add(holidates[i]);
	 for(int i=0;i<mk3;i++)
	 {
		list2.add(tempdate[i]) ;
	 }
	 System.out.println(list1);
	 System.out.println(list2);
	 list2.removeAll(list1);
	 System.out.println(list2);
	 classdates2 = (Date[]) list2.toArray(new Date[list2.size()]);
	 System.out.println("size is"+list2.size());
	 mk4=list2.size();
	for(int i=0;i<mk4;i++)
	{
		System.out.println(classdates2[i]);
		System.out.println(classdates2[i].getDay());
		for(int w=0;w<globalcount;w++)
		{
		if(classdates2[i].getDay()==1)
		{
	if(!(Integer.parseInt(mon[w])==0))
	{
	sub_dates[w]++;
	if(type.equalsIgnoreCase("CAT1"))
		term_class[w][0]++;
		else if(type.equalsIgnoreCase("CAT2"))
		term_class[w][1]++;
		else if(type.equalsIgnoreCase("Term End Theory"))
		term_class[w][2]++;

	}
		}
		else if(classdates2[i].getDay()==2)
		{
			if(!(Integer.parseInt(tues[w])==0))
			{
			sub_dates[w]++;
			if(type.equalsIgnoreCase("CAT1"))
			term_class[w][0]++;
			else if(type.equalsIgnoreCase("CAT2"))
			term_class[w][1]++;
			else if(type.equalsIgnoreCase("Term End Theory"))
			term_class[w][2]++;
				
			
			}
		
		}
		else if(classdates2[i].getDay()==3)
		{
			if(!(Integer.parseInt(wed[w])==0))
			{
			sub_dates[w]++;
			if(type.equalsIgnoreCase("CAT1"))
			term_class[w][0]++;
			else if(type.equalsIgnoreCase("CAT2"))
			term_class[w][1]++;
			else if(type.equalsIgnoreCase("Term End Theory"))
			term_class[w][2]++;

				
			
			}

		}
		else if(classdates2[i].getDay()==4)
		{
			if(!(Integer.parseInt(thurs[w])==0))
			{
			sub_dates[w]++;
			if(type.equalsIgnoreCase("CAT1"))
			term_class[w][0]++;
			else if(type.equalsIgnoreCase("CAT2"))
			term_class[w][1]++;
			else if(type.equalsIgnoreCase("Term End Theory"))
			term_class[w][2]++;

			
			
			}

		}
		else if(classdates2[i].getDay()==5)
		{
			if(!(Integer.parseInt(fri[w])==0))
			{
		sub_dates[w]++;
		if(type.equalsIgnoreCase("CAT1"))
		term_class[w][0]++;
		else if(type.equalsIgnoreCase("CAT2"))
		term_class[w][1]++;
		else if(type.equalsIgnoreCase("Term End Theory"))
		term_class[w][2]++;
				
			}

		}
		}	

	}
	 System.out.println("Displaying final classes");
	for( int i=0;i<mk5;i++)
	{
		System.out.println(classdates3[i]);
	}
	for( int i=0;i<globalcount;i++)
	{
		System.out.println(sub_dates[i]);
	}
	
	 
	 
	 
	System.out.println("displaying total classes before term edn");
	for( int i=0;i<globalcount;i++)
	{
	//	System.out.println(sub_dates[i]);
		System.out.println(term_class[i][0]);
		System.out.println(term_class[i][1]);
		System.out.println(term_class[i][2]);
		
	} 

	 
	 mk3=0;
	 mk4=0;
 }
 void bunk_classes()
 {
	 
  DBAdapter5 dba=new DBAdapter5(this);
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
				try {
					todate = dateformat.parse(to_date);
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				if(desc.equalsIgnoreCase("CAT1"))
				{
					comm_cat1=todate;
				}
				if(desc.equalsIgnoreCase("CAT2"))
				{
					comm_cat2=todate;
				}
				if(desc.equalsIgnoreCase("Term End Theory"))
				{
					comm_termend=todate;
				}
			}while(c.moveToNext());
		}

				
	 System.out.println("calculate");
	 System.out.println("Schedule");
	 System.out.println(comm_cat1);
	 System.out.println(comm_cat2);
	 System.out.println(comm_termend);
	 Date d=new Date();
	 System.out.println(d);
	 int a,b;
	 if(d.compareTo(comm_cat1)<0)
	 {
		 
		 for(int i=0;i<globalcount;i++)
		 {
			 
			 b=Integer.parseInt(total_class[i])-Integer.parseInt(attended[i]);
		//	 (attended[i]+x)/term_class[i][2]> (75*term_class[i])/100
			 int x=0;
			 int k=0;
			 int y=0;
			 System.out.println("displaying the info");
			 System.out.println(attended[i]);
			 System.out.println(total_class[i]);
			 System.out.println(term_class[i][1]);
			while(x<=((75*term_class[i][0]/100) -Integer.parseInt(attended[i])))
					 {
				 x++;
					 }
			 System.out.println("the number of classes to do is"+x);
			 y=term_class[i][0]-Integer.parseInt(total_class[i]);
			 System.out.println("the classes you can bunk is "+Integer.toString(y-x)+"/"+Integer.toString(y));
				classes[i]=Integer.toString(y-x)+"/"+Integer.toString(y);
	 
		 }

		 System.out.println("cat1 going yr");
	 }
	 else  if(d.compareTo(comm_cat2)<0)
	 {
		 for(int i=0;i<globalcount;i++)
		 {
			 b=Integer.parseInt(total_class[i])-Integer.parseInt(attended[i]);
		//	 (attended[i]+x)/term_class[i][2]> (75*term_class[i])/100
			 int x=0;
			 int k=0;
			 int y=0;
			 System.out.println("displaying the info");
			 System.out.println(attended[i]);
			 System.out.println(total_class[i]);
			 System.out.println(term_class[i][1]);
			while(x<=((75*term_class[i][1]/100) -Integer.parseInt(attended[i])))
					 {
				 x++;
					 }
			 System.out.println("the number of classes to do is"+x);
			 y=term_class[i][1]-Integer.parseInt(total_class[i]);
			 System.out.println("the classes you can bunk is "+Integer.toString(y-x)+"/"+Integer.toString(y));
				classes[i]=Integer.toString(y-x)+"/"+Integer.toString(y);
	 
		 }

		 System.out.println("cat2 going yr");
	 }
	 else if(d.compareTo(comm_termend)<0)
	 {
		 for(int i=0;i<globalcount;i++)
		 {
			
			 int countdigit=0;
			 if(slots[i].startsWith("L"))
			 {
				 System.out.println("check");
				 System.out.println(total_class[i]);
				 System.out.println(attended[i]);
				 System.out.println("yes");
				for(int w=0;w<slots[i].length();w++)
				{
					if(slots[i].charAt(w)=='+')
					{
					countdigit++;
					}
				}
				
				 total_class[i]=Integer.toString(Integer.parseInt(total_class[i])/(countdigit+1));
					attended[i]=Integer.toString(Integer.parseInt(attended [i])/(countdigit+1));
				
				
			 }
				
			 b=Integer.parseInt(total_class[i])-Integer.parseInt(attended[i]);
		//	 (attended[i]+x)/term_class[i][2]> (75*term_class[i])/100
			 int x=0;
			 int k=0;
			 int y=0;
			 System.out.println("displaying the info");
			 System.out.println(attended[i]);
			 System.out.println(total_class[i]);
			 System.out.println(term_class[i][2]);
			while(x<=((75*term_class[i][2]/100) -Integer.parseInt(attended[i])))
					 {
				 x++;
					 }
			 System.out.println("the number of classes to do is"+x);
			 y=term_class[i][2]-Integer.parseInt(total_class[i]);
			 System.out.println("the classes you can bunk is "+Integer.toString(y-x)+"/"+Integer.toString(y));
	classes[i]=Integer.toString(y-x)+"/"+Integer.toString(y);
		 }

		 System.out.println("termend going yr");
	 }


			 
 }
 void sync()
 {
	 
 }
 public void onSwipe(int direction) {
     String str = "";
     
     switch (direction) {
     
     case SimpleGestureFilter.SWIPE_LEFT : str = "Swipe Right";
 	day2=day2+1;
	if(day2==8)
		day2=1;
	displayday(day2);
	settimetable(day2);
    
                                              break;
     case SimpleGestureFilter.SWIPE_RIGHT :  str = "Swipe Left";
     day2=day2-1;
		if(day2==0)
			day2=7;
		displayday(day2);
		settimetable(day2);
   /*  if(kkk<9)
     {
    	  
     }*/
                                                    break;
     case SimpleGestureFilter.SWIPE_DOWN :  str = "Swipe Down";
                                                    break;
     case SimpleGestureFilter.SWIPE_UP :    str = "Swipe Up";
                                                    break;
                                              
     } 
    
    }
    @Override 
    public boolean dispatchTouchEvent(MotionEvent me){ 
      this.detector.onTouchEvent(me);
      
     return super.dispatchTouchEvent(me); 
    }
	public boolean onTouch(View arg0, MotionEvent arg1) {
		// TODO Auto-generated method stub
		return false;
	}
	public void onDoubleTap() {
		// TODO Auto-generated method stub
		
	}
	public void onGesture(GestureOverlayView arg0, MotionEvent arg1) {
		// TODO Auto-generated method stub
		
	}
	public void onGestureCancelled(GestureOverlayView arg0, MotionEvent arg1) {
		// TODO Auto-generated method stub
		
	}
	public void onGestureEnded(GestureOverlayView arg0, MotionEvent arg1) {
		// TODO Auto-generated method stub
		
	}
	public void onGestureStarted(GestureOverlayView arg0, MotionEvent arg1) {
		// TODO Auto-generated method stub
		
	}
	 @Override
	 public void onBackPressed() {
		System.out.println("all your data is erased");
		GFaculty fac=new GFaculty(this);
		fac.open();
		fac.deleteDatabse();
		fac.close();
		GDBAdapter dba=new GDBAdapter(this);
		dba.open();
		dba.deleteDatabse();
		dba.close();
		GDBAdapter5 dba5=new GDBAdapter5(this);
		dba5.open();
		dba5.deleteDatabse();
		dba5.close();
		GDBAdapter6 dba6=new GDBAdapter6(this);
		dba6.open();
		dba6.deleteDatabse();
		dba6.close();
		
	     super.onBackPressed();   
	     //    finish();

	 }
	 public class ViewHolder1
		{
			TextView course_menu;
		}

	 private class adapter extends BaseAdapter {
			private LayoutInflater mInflater;

			//The variable that will hold our text data to be tied to list.
			private String[] data;
			public adapter(Context context, String[] items) {
			    mInflater = LayoutInflater.from(context);
			    this.data = items;
			 
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
			public View getView(int position, View convertView, ViewGroup parent) {
ViewHolder1 holder;
			// A ViewHolder keeps references to children views to avoid unnecessary calls
			// to findViewById() on each row.
		
			if (convertView == null) {
			    convertView = mInflater.inflate(R.layout.custom_line3, null);
          convertView.setMinimumHeight(70);
			    holder = new ViewHolder1();
			    holder.course_menu = (TextView) convertView.findViewById(R.id.customline_text3);
			    
			    
			    convertView.setTag(holder);
			} else {
			    holder = (ViewHolder1) convertView.getTag();
			}
			
			    // Bind the data efficiently with the holder.
				holder.course_menu.setText(data[position]);
				holder.course_menu.setTypeface(face2);
				holder.course_menu.setPadding(0, 10, 0, 0);
				holder.course_menu.setTextSize(20);
		
		
			   return convertView;
			}

			}



}

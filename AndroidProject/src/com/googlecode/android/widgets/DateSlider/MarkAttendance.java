package com.googlecode.android.widgets.DateSlider;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import com.googlecode.android.widgets.DateSlider.QuizRem3.ViewHolder;
import com.googlecode.android.widgets.DateSlider.labeler.TimeLabeler;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class MarkAttendance extends Activity
{
	static final int DEFAULTDATESELECTOR_ID = 0;
	static final int DEFAULTDATESELECTOR_WITHLIMIT_ID = 6;
	static final int ALTERNATIVEDATESELECTOR_ID = 1;
	static final int CUSTOMDATESELECTOR_ID = 2;
	static final int MONTHYEARDATESELECTOR_ID = 3;
	static final int TIMESELECTOR_ID = 4;
	static final int TIMESELECTOR_WITHLIMIT_ID = 7;
	static final int DATETIMESELECTOR_ID = 5;
	int is_modify=0;
	int is_post=0;
	int is_delete=0;
	int is_classtoday=0;
Button post,modify,delete;
	Date fromdate,todate,dat;
	TextView showstatus;
	int mk2=0;
	int mk6=0;
	int num[];
	boolean[] chek;
	int tempsub=0;
	Date[] holidates=new Date[200];
	SimpleDateFormat dateformat,sdf;
	DBAdapter5 dba;
	long day,diff;
	boolean is_holiday=false;
	boolean is_enter=false;
	int sub=0;
	String[] subs;
	ListView list;
	String[] marked_subs;
	String[] status,status_day;
	ImageButton dateText;
	TextView displaydate_mark;
	int q_year,q_month,q_day;
	private ArrayAdapter<String> listAdapter ;
	boolean[] arr;
	ViewHolder holder;

	 protected void onCreate(Bundle savedInstanceState) {
    //TODO Auto-generated method stub
 	super.onCreate(savedInstanceState);
 	// requestWindowFeature(Window.FEATURE_CUSTOM_TITLE);
 	setContentView(R.layout.markattendance);
 
 post=(Button)findViewById(R.id.post_attn);
modify=(Button)findViewById(R.id.modi_attn);
delete=(Button)findViewById(R.id.del_attn);
	dateText=(ImageButton)findViewById(R.id.seldate_mark);
	displaydate_mark=(TextView)findViewById(R.id.displaydate_mark);
	showstatus=(TextView)findViewById(R.id.showstatus);
	
	  dateText.setOnClickListener(new OnClickListener() {
           public void onClick(View arg0) {
        	    showDialog(DEFAULTDATESELECTOR_ID);
                
           }
       });


 
 	 sdf = new SimpleDateFormat("dd-MMM-yyyy");
 	dateformat = new SimpleDateFormat("dd/MM/yyyy"); 
 	initialise();
 	 
 	System.out.println("Hello");
	post.setOnClickListener(new View.OnClickListener() {
		public void onClick(View v)
		{
			if(is_classtoday==0)
			{
		Toast.makeText(getApplicationContext(), "no classes", Toast.LENGTH_SHORT).show();		
			}
			else
			{
			if(is_post==0)
			{
		Toast.makeText(getApplicationContext(), "Attendance is already posted for this date, Kindly modify it",Toast.LENGTH_SHORT).show();		
			}
			else
			{
			Student stud=new Student(getApplicationContext());
			stud.open();
	
				for (int i = 0; i < arr.length; i++)
				{
					
				 if(arr[i]==true)
				 {
			int x=	stud.insert(sdf.format(dat), subs[i].replaceAll("\\s",""), "Present");
			if(x==-1)
			{
				stud.update(sdf.format(dat), subs[i].replaceAll("\\s",""), "Present");	
			}
				 }
				 else
				 {
						int x=stud.insert(sdf.format(dat), subs[i].replaceAll("\\s",""), "Absent");
						if(x==-1)
						{
							stud.update(sdf.format(dat), subs[i].replaceAll("\\s",""), "Absent");	
						}
		 
				 }
				}
			stud.close();
		for(int i=0;i<arr.length;i++)
		{
			arr[i]=false;
			
		}
		Toast.makeText(getApplicationContext(), "Entered the Attendance", Toast.LENGTH_SHORT).show();
		fun();
			}}
		}
		});
 	
	modify.setOnClickListener(new View.OnClickListener() {
		public void onClick(View v)
		{
			System.out.println("value"+is_modify);
			if(is_classtoday==0)
			{
		Toast.makeText(getApplicationContext(), "no classes", Toast.LENGTH_SHORT).show();		
			}
			else
			{
		if(is_modify==0)
		{
		Toast.makeText(getApplicationContext(), "You are entering the attendance for this date first time, so cannot modify it", Toast.LENGTH_SHORT).show();
		}
		else
		{
			System.out.println("inside modify"+tempsub);
			for(int i=0;i<tempsub;i++)
				chek[i]=false;
			 SpecialAdapter a=new SpecialAdapter(getApplicationContext(),subs, chek);
				list.setAdapter(a);
				list.setClickable(false);
				is_post=1;

		}}}
		});
	
	delete.setOnClickListener(new View.OnClickListener() {
		public void onClick(View v)
		{
			System.out.println("value"+is_modify);
			if(is_classtoday==0)
			{
		Toast.makeText(getApplicationContext(), "no classes", Toast.LENGTH_SHORT).show();		
			}
			else
			{
		if(is_delete==0)
		{
		Toast.makeText(getApplicationContext(), "You didnot enter the attendance for this date, so cannot modify it", Toast.LENGTH_SHORT).show();
		}
		else
		{
			Student stu=new Student(getApplicationContext());
			stu.open();
			System.out.println(q_day);
			System.out.println(q_month);
			System.out.println(q_year);
			Date d=new Date(q_year-1900,q_month-1,q_day);
			System.out.println(d);
			String givendate=sdf.format(d);
			System.out.println(givendate);
			stu.deletedate(givendate);
			stu.close();
			Toast.makeText(getApplicationContext(), "Deleted Attendance", Toast.LENGTH_SHORT).show();
			fun();
	
		}}}
		});

 	
 	
 	
		calculate_holidays();
}
	 public boolean isEnabled(int position) {
		    if(is_enter==false){
		        return false;
		    }
		    return true;
		}
	 
	 public void calculate_holidays()
	 {
		 System.out.println("in calculate holidays");
		 		String commence_sem="";
		 		dba=new DBAdapter5(this);
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
			   for(int i=0;i<mk2;i++)
		       {
		      	 System.out.println(holidates[i]);
		       }
			   
			   
			
		 
	 }
		mk6=mk2;
		mk2=0;
		
		dba.close();
	 }
	 private DateSlider.OnDateSetListener mDateSetListener =
	        new DateSlider.OnDateSetListener() {
	            public void onDateSet(DateSlider view, Calendar selectedDate) {
	                // update the dateText view with the corresponding date
	            //    dateText.setText(String.format("The chosen date:%n%te. %tB %tY", selectedDate, selectedDate, selectedDate));
	          
	                q_year=Integer.parseInt((String.format("%tY",
	                        selectedDate, selectedDate, selectedDate, selectedDate)));
	              
	                q_day=Integer.parseInt(String.format("%te",
	                        selectedDate, selectedDate, selectedDate, selectedDate));
	                String w=String.format("%tB",
	                        selectedDate, selectedDate, selectedDate, selectedDate);
	                if(w.equalsIgnoreCase("January"))
	                	q_month=1;
	                if(w.equalsIgnoreCase("February"))
	                	q_month=2;
	                if(w.equalsIgnoreCase("March"))
	                	q_month=3;
	                if(w.equalsIgnoreCase("April"))
	                	q_month=4;
	                if(w.equalsIgnoreCase("May"))
	                	q_month=5;
	                if(w.equalsIgnoreCase("June"))
	                	q_month=6;
	                if(w.equalsIgnoreCase("July"))
	                	q_month=7;
	                if(w.equalsIgnoreCase("August"))
	                	q_month=8;
	                if(w.equalsIgnoreCase("September"))
	                	q_month=9;
	                if(w.equalsIgnoreCase("October"))
	                	q_month=10;
	                if(w.equalsIgnoreCase("November"))
	                	q_month=11;
	                if(w.equalsIgnoreCase("December"))
	                	q_month=12;
	                String w1=Integer.toString(q_day)+" "+w+" "+Integer.toString(q_year);
	     	       displaydate_mark.setText(w1);
	         fun();
	          
	            }
	    };

	    private DateSlider.OnDateSetListener mMonthYearSetListener =
	        new DateSlider.OnDateSetListener() {
	            public void onDateSet(DateSlider view, Calendar selectedDate) {
	            }
	    };

	    private DateSlider.OnDateSetListener mTimeSetListener =
	        new DateSlider.OnDateSetListener() {
	            public void onDateSet(DateSlider view, Calendar selectedDate) {
	            }
	    };

	    private DateSlider.OnDateSetListener mDateTimeSetListener =
	        new DateSlider.OnDateSetListener() {
	            public void onDateSet(DateSlider view, Calendar selectedDate) {
	                // update the dateText view with the corresponding date
	                int minute = selectedDate.get(Calendar.MINUTE) /
	                        TimeLabeler.MINUTEINTERVAL*TimeLabeler.MINUTEINTERVAL;
	               }
	    };

	    @Override
	    protected Dialog onCreateDialog(int id) {
	        // this method is called after invoking 'showDialog' for the first time
	        // here we initiate the corresponding DateSlideSelector and return the dialog to its caller
	    	
	        final Calendar c = Calendar.getInstance();
	        switch (id) {
	        case DEFAULTDATESELECTOR_ID:
	            return new DefaultDateSlider(this,mDateSetListener,c);
	        case DEFAULTDATESELECTOR_WITHLIMIT_ID:
	        	final Calendar maxTime = Calendar.getInstance();
	        	maxTime.add(Calendar.DAY_OF_MONTH, 14);
	            return new DefaultDateSlider(this,mDateSetListener,c,c,maxTime);
	        case ALTERNATIVEDATESELECTOR_ID:
	            return new AlternativeDateSlider(this,mDateSetListener,c,c,null);
	        case CUSTOMDATESELECTOR_ID:
	            return new CustomDateSlider(this,mDateSetListener,c);
	        case MONTHYEARDATESELECTOR_ID:
	            return new MonthYearDateSlider(this,mMonthYearSetListener,c);
	        case TIMESELECTOR_ID:
	            return new TimeSlider(this,mTimeSetListener,c,15);
	        case TIMESELECTOR_WITHLIMIT_ID:
	        	final Calendar minTime = Calendar.getInstance();
	        	minTime.add(Calendar.HOUR, -2);
	            return new TimeSlider(this,mTimeSetListener,c,minTime,c,5);
	        case DATETIMESELECTOR_ID:
	            return new DateTimeSlider(this,mDateTimeSetListener,c);
	        }
	        return null;
	    }

public void fun()
{
	System.out.println("Nnn");
	
 	 System.out.println(q_year);
 	System.out.println(q_month);
 	System.out.println(q_day);
	 
 	 
 	 dat=new Date( q_year-1900,q_month-1, q_day);
 	 System.out.println(dat);
 	String date=dateformat.format(dat);
 	System.out.println(date);
 	int day2=dat.getDay();
 	if(day2==0 || day2==6)
 		is_holiday=true;
 for(int i=0;i<mk6;i++)
 	{
 		String w=dateformat.format(holidates[i]);
 		System.out.println(w);
 		if(date.equalsIgnoreCase(w))
 		{
 			//Toast.makeText(getApplicationContext(), "This is a holiday", Toast.LENGTH_SHORT).show();
 			is_holiday=true;
 			break;
 		}
 	}
 if(is_holiday==true)
 {
	// Toast.makeText(getApplicationContext(), "This is a holiday", Toast.LENGTH_SHORT).show();	 
	 showstatus.setText("No classes today");
	 is_classtoday=0;
 }
 else
 {
	is_classtoday=1;
	int i=0;
	 DBAdapter db=new DBAdapter(getApplicationContext());
	 db.open();
	 Cursor c=db.getData();
	 global.subjects2=new String[c.getCount()];
	 global.subjects=new String[c.getCount()];
	 System.out.println(c.getCount());
	 System.out.println(day);
	 subs=new String[c.getCount()];
	 status=new String[c.getCount()];

	 Student stud=new Student(getApplicationContext());
		stud.open();
		Cursor c2=stud.getAllContacts();
		System.out.println("the count is"+c2.getCount());
		marked_subs=new String[c2.getColumnCount()-1];
		i=0;
		String tempdate;
		is_post=1;
		is_modify=0;
		is_delete=0;
		if(c2.moveToFirst())
		{
			do
			{
				tempdate=c2.getString(0);
				if(tempdate.equalsIgnoreCase(sdf.format(dat)))
				{
					Toast.makeText(getApplicationContext(), "already entered. Click modify to modify the attendance", Toast.LENGTH_SHORT).show();
					is_post=0;
					is_modify=1;
					is_delete=1;
					for(int j=0;j<c2.getColumnCount()-1;j++)
					{
						if(!(c2.getString(j+1)==null))
						{
						System.out.println(c2.getString(j+1));
						status[j]=c2.getString(j+1);
						}
						else
						status[j]="";
						System.out.println("status"+status[j]);
					}
				}
				i++;
			}while(c2.moveToNext());
		}
		System.out.println(c2.getCount());
		stud.close();
	
		 if(c.moveToFirst())
	 {
		 int x=0;
		 do
		 {
	if(day2==1)
	{
		x=c.getInt(10);
		if(x!=0)
		sub++;

	}
	if(day2==2)
	{
		x=c.getInt(11);
		if(x!=0)
		sub++;
	}if(day2==3)
	{
		x=c.getInt(12);	
		if(x!=0)
		sub++;
	}if(day2==4)
	{
		x=c.getInt(13);
		if(x!=0)
		sub++;
	}if(day2==5)
	{
		x=c.getInt(14);	
		if(x!=0)
		sub++;
	}
	
		 }while(c.moveToNext());
	 }
	 subs=new String[sub];
	 arr=new boolean[sub];
	 for( i=0;i<sub;i++)
		 arr[i]=false;
	 num=new int[sub];
	 status_day=new String[sub];
	  i=0; 
	  int count=0;
 if(c.moveToFirst())
	 {
	 global.subjects2[i]=c.getString(1);
	 global.subjects[i]=global.subjects2[i].replaceAll("\\s","");

		 int x=0;
		 do
		 {
	if(day2==1)
	{
		x=c.getInt(10);
		if(x!=0)
		{
		subs[i]=c.getString(1);
		status_day[i]=status[count];
		
		num[i]=c.getInt(10);
		i++;
		}

	}
	if(day2==2)
	{
		x=c.getInt(11);
		if(x!=0)
		{
			subs[i]=c.getString(1);
			num[i]=c.getInt(11);
			status_day[i]=status[count];

			System.out.println(subs[i]);
			System.out.println("value of i"+Integer.toString(i));

			i++;
			
		}
					}
	if(day2==3)
	{
		x=c.getInt(12);	
		if(x!=0)
		{
			subs[i]=c.getString(1);
			num[i]=c.getInt(12);
			status_day[i]=status[count];

			i++;
		}
	}if(day2==4)
	{
		x=c.getInt(13);
		if(x!=0)
		{
			subs[i]=c.getString(1);
			num[i]=c.getInt(13);
			status_day[i]=status[count];

			i++;
		}
	}if(day2==5)
	{
		x=c.getInt(14);	
		if(x!=0)
		{
			subs[i]=c.getString(1);
			num[i]=c.getInt(14);
			status_day[i]=status[count];

			i++;
		}
	}
count++;
		 }while(c.moveToNext());
	 }
	 
i=0;
int temp;
String temp2,temp3;
for(int j=0;j<sub;j++)
{
	for(int k=j+1;k<sub;k++)
	{
	if(num[j]>num[k])
	{
		temp=num[j];
		num[j]=num[k];
		num[k]=temp;
		
		temp2=subs[j];
		subs[j]=subs[k];
		subs[k]=temp2;
		
		temp3=status_day[j];
		status_day[j]=status_day[k];
		status_day[k]=temp3;

	}
	}
}
System.out.println("Printing the subjects with their status");
		

list=(ListView)findViewById(R.id.list_markatt);
//listAdapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_multiple_choice, subs);
//list.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
chek=new boolean[sub];
	 for(i=0;i<sub;i++)
		{
	//	 list.setClickable(false);
		 if(!(status_day[i]==null))
		 {
			if(status_day[i].equalsIgnoreCase("Present"))
			{
				System.out.println("tru yr");
		//list.setItemChecked(i, true);
				chek[i]=true;
				
			}
			else
				chek[i]=false;
				//list.setItemChecked(i,false);

		 }
		}
	 SpecialAdapter a=new SpecialAdapter(getApplicationContext(),subs, chek);
		list.setAdapter(a);
		list.setClickable(false);

	 list.setClickable(false);
	 
//list.setItemsCanFocus(false);
	 db.close();
	 
 }
 is_holiday=false;
 System.out.println("the number of classees are");
 System.out.println(sub);
 tempsub=sub;
 sub=0;
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
			public SpecialAdapter(Context context, String[] items,boolean[] chek) {
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
			    holder.chk=(CheckBox)convertView.findViewById(R.id.chkatt);
		    
			    
			    convertView.setTag(holder);
			} else {
			    holder = (ViewHolder) convertView.getTag();
			}
			
			    // Bind the data efficiently with the holder.
				holder.course_title.setText(data[position]);
				if(chek[position]==true)
					holder.chk.setChecked(true);
					else
					{
					holder.chk.setChecked(false);
					//holder.chk.setBackgroundColor(getResources().getColor(android.R.color.darker_gray));
					}
				if(!(is_post==0))
				{
				holder.chk.setOnClickListener(new OnClickListener() {
			           public void onClick(View arg0) {
			            if(arr[position]==false)
			            {
			            	arr[position]=true;
			            	
			            }
			            else if(arr[position]==true)
			            	arr[position]=false;
			            System.out.println(arr[position]);
					      
			           }
			         });
				}
				System.out.println("post value"+is_post);
				if(is_post==0)
				{
					System.out.println(is_post);
					holder.chk.setClickable(false);
				//	Toast.makeText(getApplicationContext(), "Attendance is already posted for this date, Kindly modify it",Toast.LENGTH_SHORT).show();		
					
				}
			
		
			   return convertView;
			}

			}
public void initialise()
{
	Date d=new Date();
	q_day = d.getDate();
	 q_month = d.getMonth()+1;
	 q_year = d.getYear()+1900;	
	 String w="";
	 if(q_month==1)
		 w="January";
	 if(q_month==2)
		 w="February";
	 if(q_month==3)
		 w="March";
	 if(q_month==4)
		 w="April";
	 if(q_month==5)
		 w="May";
	 if(q_month==6)
		 w="June";
	 if(q_month==7)
		 w="July";
	 if(q_month==8)
		 w="August";
	 if(q_month==9)
		 w="September";
	 if(q_month==10)
		 w="October";
	 if(q_month==11)
		 w="November";
	 if(q_month==12)
		 w="December";
	
	      String w1=Integer.toString(q_day)+" "+w+" "+Integer.toString(q_year);
      displaydate_mark.setText(w1);

	 fun();

}

}

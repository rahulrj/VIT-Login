package com.googlecode.android.widgets.DateSlider;

import java.util.Calendar;

import com.googlecode.android.widgets.DateSlider.QuizRem3.ViewHolder;
import com.googlecode.android.widgets.DateSlider.labeler.TimeLabeler;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.AdapterView.OnItemClickListener;

public class QuizRem4 extends Activity{
	DatePicker datePicker;
	TimePicker timePicker;
	int q_year,q_month,q_day,q_hours,q_minutes;
	TextView notes;
	String[] subjects;
	Quiz q;
	String sub,cat;
	int year,month,day,hours,minutes;
	int rowid;
	TextView dateText;
	private ArrayAdapter<String> listAdapter,listAdapter2 ;
	TextView displaydatetime;
	static final int DEFAULTDATESELECTOR_ID = 0;
	static final int DEFAULTDATESELECTOR_WITHLIMIT_ID = 6;
	static final int ALTERNATIVEDATESELECTOR_ID = 1;
	static final int CUSTOMDATESELECTOR_ID = 2;
	static final int MONTHYEARDATESELECTOR_ID = 3;
	static final int TIMESELECTOR_ID = 4;
	static final int TIMESELECTOR_WITHLIMIT_ID = 7;
	static final int DATETIMESELECTOR_ID = 5;
	String displayeddate;
	

	 protected void onCreate(Bundle savedInstanceState) {
		    //TODO Auto-generated method stub
		 	super.onCreate(savedInstanceState);
		  	setContentView(R.layout.quizrem4);
		  	Bundle b=getIntent().getExtras();
		 	String sub1=b.getString("sub");
		 	String cat1=b.getString("cat");
		 	sub=sub1;
		 	cat=cat1;
		 	year=b.getInt("year");
		 	month=b.getInt("month");
		 	day=b.getInt("day");
		 	hours=b.getInt("hours");
		 	minutes=b.getInt("minutes");
		 	rowid=b.getInt("rowid");
		 	displayeddate=b.getString("display_date");
		 	System.out.println(displayeddate);
		 	q=new Quiz(this);
		 	final TextView selsub2=(TextView)findViewById(R.id.selsubq2);
			final TextView selcat2=(TextView)findViewById(R.id.selcatq2);
			final TextView displaysub2=(TextView)findViewById(R.id.displaysubq2);
			final TextView displaycat2=(TextView)findViewById(R.id.displaycatq2);
			 TextView dateTimeButton = (TextView) this.findViewById(R.id.seldateandtimeq2);
			 	displaydatetime=(TextView)findViewById(R.id.displaydatetime2);
			    dateTimeButton.setOnClickListener(new OnClickListener() {
		            public void onClick(View arg0) {
		                // call the internal showDialog method using the predefined ID
		                showDialog(DATETIMESELECTOR_ID);
		            }
		        });
		    displaydatetime.setText(displayeddate);
				
		
			final Button save_quiz=(Button)findViewById(R.id.saverem);
			 save_quiz.setOnClickListener(new View.OnClickListener() {
					public void onClick(View v)
					{
								 q.open();	 
				q.updateEntry(sub, cat, "", q_year, q_month, q_day, q_hours, q_minutes,"",rowid);
					 q.close();
						stopService(new Intent(getBaseContext(),MyService2.class));
						startService(new Intent(getBaseContext(),MyService2.class));
					
							
					}
				});
		
		
			displaysub2.setText(sub1);
			displaycat2.setText(cat1);
			System.out.println("Following");
			System.out.println(year);
			System.out.println(month);
			System.out.println(day);
		 	DBAdapter db2=new DBAdapter(this);
			db2.open();
			Cursor c2=db2.getData();
			int globalcount=c2.getCount();
			subjects=new String[globalcount];
			int k=0;
			if(c2.moveToFirst())
			{
				do
				{
				subjects[k]=c2.getString(1);
				k++;
				} while(c2.moveToNext());
				}
			db2.close();
			  final Dialog options_dialog=new Dialog(this);
			  final Dialog options_dialog2=new Dialog(this);
		       options_dialog.setContentView(R.layout.options_dialog1);
		       options_dialog2.setContentView(R.layout.options_dialog2);
		       final ListView optionslist=(ListView)options_dialog.findViewById(R.id.optionslist1);
		       final ListView optionslist2=(ListView)options_dialog2.findViewById(R.id.optionslist2);
		       options_dialog.getWindow().setBackgroundDrawable(new ColorDrawable(0));  
			     options_dialog2.getWindow().setBackgroundDrawable(new ColorDrawable(0));  
				
		       final String[] category={"quiz","assignment","Others"};
		       q.open();
		   	
				 selsub2.setOnClickListener(new View.OnClickListener() {
						public void onClick(View v)
						{
							SpecialAdapter a=new SpecialAdapter(getApplicationContext(), subjects);	
							
							optionslist.setAdapter(a);
							
							options_dialog.show();
							
						}
					});
				 selcat2.setOnClickListener(new View.OnClickListener() {
						public void onClick(View v)
						{
						
							SpecialAdapter a=new SpecialAdapter(getApplicationContext(), category);	
						      
							
							optionslist2.setAdapter(a);
					
					options_dialog2.show();				
				}
					});
				  optionslist.setOnItemClickListener(new OnItemClickListener() {
					  
			            public void onItemClick(AdapterView<?> arg0, View view, int position,
			                    long id) {
			            	System.out.println("in options");
			            	options_dialog.dismiss();
			            System.out.println("postion"+position);
			            System.out.println(subjects[position]);
			            sub=subjects[position];
			            selsub2.setText(subjects[position]);
			            
			          		                         }
			        });
				  optionslist2.setOnItemClickListener(new OnItemClickListener() {

			            public void onItemClick(AdapterView<?> arg0, View view, int position,
			                    long id) {
			            	options_dialog2.dismiss();
			            System.out.println("postion"+position);
			            System.out.println(category[position]);
			            cat=category[position];
			            selcat2.setText(category[position]);
			          		                         }
			        });

		
	 }
	  public class ViewHolder
	  {
		TextView course_title;  
	  }
		 private class SpecialAdapter extends BaseAdapter {
				private LayoutInflater mInflater;

				//The variable that will hold our text data to be tied to list.
				private String[] data;
				public SpecialAdapter(Context context, String[] items) {
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

				// A ViewHolder keeps references to children views to avoid unnecessary calls
				// to findViewById() on each row.
			ViewHolder holder;
				if (convertView == null) {
				    convertView = mInflater.inflate(R.layout.custom_line, null);
	                convertView.setMinimumHeight(70);
				    holder = new ViewHolder();
				    holder.course_title = (TextView) convertView.findViewById(R.id.customline_text);
			    
				    
				    convertView.setTag(holder);
				} else {
				    holder = (ViewHolder) convertView.getTag();
				}
				
				    // Bind the data efficiently with the holder.
					holder.course_title.setText(data[position]);
			
				   return convertView;
				}

				}
		 private DateSlider.OnDateSetListener mDateSetListener =
		        new DateSlider.OnDateSetListener() {
		            public void onDateSet(DateSlider view, Calendar selectedDate) {
		                // update the dateText view with the corresponding date
		                dateText.setText(String.format("The chosen date:%n%te. %tB %tY", selectedDate, selectedDate, selectedDate));
		            }
		    };

		    private DateSlider.OnDateSetListener mMonthYearSetListener =
		        new DateSlider.OnDateSetListener() {
		            public void onDateSet(DateSlider view, Calendar selectedDate) {
		                // update the dateText view with the corresponding date
		                dateText.setText(String.format("The chosen date:%n%tB %tY", selectedDate, selectedDate));
		            }
		    };

		    private DateSlider.OnDateSetListener mTimeSetListener =
		        new DateSlider.OnDateSetListener() {
		            public void onDateSet(DateSlider view, Calendar selectedDate) {
		                // update the dateText view with the corresponding date
		                dateText.setText(String.format("The chosen time:%n%tR", selectedDate));
		            }
		    };

		    private DateSlider.OnDateSetListener mDateTimeSetListener =
		        new DateSlider.OnDateSetListener() {
		            public void onDateSet(DateSlider view, Calendar selectedDate) {
		                // update the dateText view with the corresponding date
		                int minute = selectedDate.get(Calendar.MINUTE) /
		                        TimeLabeler.MINUTEINTERVAL*TimeLabeler.MINUTEINTERVAL;
		                System.out.println(selectedDate);
		                System.out.println("ansh");
		                System.out.println(minute);
		                
		              
		               // dateText.setText(String.format("The chosen date and time:%n%te. %tB %tY%n%tH:%02d",
		                //        selectedDate, selectedDate, selectedDate, selectedDate, minute));
		                q_year=Integer.parseInt((String.format("%tY",
		                        selectedDate, selectedDate, selectedDate, selectedDate, minute)));
		                q_hours=Integer.parseInt(String.format("%tH",
		                		selectedDate, selectedDate, selectedDate, selectedDate, minute));
		                q_minutes=minute;
		                q_day=Integer.parseInt(String.format("%tH",
		                        selectedDate, selectedDate, selectedDate, selectedDate, minute));
		                String w=String.format("%tB",
		                        selectedDate, selectedDate, selectedDate, selectedDate, minute);
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
		         String x= Integer.toString(q_hours)+":"+Integer.toString(q_minutes);
		         
		                displaydatetime.setText(w1
		                		+"   "+x);
		                System.out.println("details are");
		                System.out.println(q_year);
		                System.out.println(w);
		                System.out.println(q_day);
		                System.out.println(q_hours);
		                System.out.println(q_minutes);
		                
		                
		              /*  System.out.println((String.format(" %tY",
		                        selectedDate, selectedDate, selectedDate, selectedDate, minute)));
		                System.out.println((String.format("%tH",
		                        selectedDate, selectedDate, selectedDate, selectedDate, minute)));
		                System.out.println((String.format("%te",
		                        selectedDate, selectedDate, selectedDate, selectedDate, minute)));
		                System.out.println((String.format("%tB",
		                        selectedDate, selectedDate, selectedDate, selectedDate, minute)));*/
		             
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

		


}

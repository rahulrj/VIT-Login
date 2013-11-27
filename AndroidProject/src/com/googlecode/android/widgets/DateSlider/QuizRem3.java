package com.googlecode.android.widgets.DateSlider;

import java.util.Calendar;

import com.googlecode.android.widgets.DateSlider.labeler.TimeLabeler;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
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
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

public class QuizRem3 extends Activity {
	static final int DEFAULTDATESELECTOR_ID = 0;
	static final int DEFAULTDATESELECTOR_WITHLIMIT_ID = 6;
	static final int ALTERNATIVEDATESELECTOR_ID = 1;
	static final int CUSTOMDATESELECTOR_ID = 2;
	static final int MONTHYEARDATESELECTOR_ID = 3;
	static final int TIMESELECTOR_ID = 4;
	static final int TIMESELECTOR_WITHLIMIT_ID = 7;
	static final int DATETIMESELECTOR_ID = 5;
	private TextView dateText;
	private ArrayAdapter<String> listAdapter,listAdapter2 ;
	DatePicker date;
	TimePicker time;
	String[] subjects;
	String sub,cat;
	String dat,tim;
	String displayeddate;
	TextView displaysub,displaycat,displaylabel;
	SharedPreferences[] prefs=new SharedPreferences[10];
//	String prefName="MyPref";
	SharedPreferences number;
	String prefnum="MyNumber";
	TextView notes;
	Quiz q;
	ListView edit;
	Dialog dialog;
	String[] heading,subheading;
	int[] year,month,day,hours,minutes;
	int[] notificationid;
	int q_year,q_month,q_day,q_hours,q_minutes;
 TextView  displaydatetime;
	 protected void onCreate(Bundle savedInstanceState) {
		    //TODO Auto-generated method stub
		 	super.onCreate(savedInstanceState);
		 	 
		 	setContentView(R.layout.quizrem3);
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
	
			final TextView selsub=(TextView)findViewById(R.id.selsubq);
		 	final TextView selcat=(TextView)findViewById(R.id.selcatq);
		 	 displaysub=(TextView)findViewById(R.id.displaysubq);
		 	 displaycat=(TextView)findViewById(R.id.displaycatq);
			final TextView selectlabel=(TextView)findViewById(R.id.selectlabel);
		 displaylabel=(TextView)findViewById(R.id.displaylabel);
			final TextView viewrem=(TextView)findViewById(R.id.viewrem);
			 
		 
		 	final Button addrem=(Button)findViewById(R.id.addrem);
		 	
		 	final Dialog dialog_label=new Dialog(this);
		 	dialog_label.setContentView(R.layout.label);
		 	  dialog_label.getWindow().setBackgroundDrawable(new ColorDrawable(0));  
		 	 final Button ok=(Button)dialog_label.findViewById(R.id.ok);
		 	final Button cancel=(Button)dialog_label.findViewById(R.id.cancel);
			final EditText text_label=(EditText)dialog_label.findViewById(R.id.edit_text);
			 
			 ok.setOnClickListener(new OnClickListener() {
		            public void onClick(View arg0) {
		                // call the internal showDialog method using the predefined ID
		                dialog_label.dismiss();
		                displaylabel.setText(text_label.getText().toString());
				           
		            }
		        });
			 cancel.setOnClickListener(new OnClickListener() {
		            public void onClick(View arg0) {
		                // call the internal showDialog method using the predefined ID
		                dialog_label.dismiss();
		            }
		        });
		      
		    selectlabel.setOnClickListener(new OnClickListener() {
	            public void onClick(View arg0) {
	                // call the internal showDialog method using the predefined ID
	                dialog_label.show();
	            }
	        });
	    
		 	
		 	
		 	 TextView dateTimeButton = (TextView) this.findViewById(R.id.seldateandtimeq);
		 	displaydatetime=(TextView)findViewById(R.id.displaydatetime);
			 q=new Quiz(this);
		     dialog=new Dialog(this);
		     viewrem.setOnClickListener(new View.OnClickListener() {
					public void onClick(View v)
					{
						q.open();
						Cursor c=q.getAllContacts2();
						int  len=c.getCount();
						System.out.println(len);
						heading=new String[len];
						subheading=new String[len];
						year=new int[len];
						month=new int[len];
						day=new int[len];
						hours=new int[len];
						minutes=new int[len];
						notificationid=new int[len];
						int i=0;
						if(c.moveToFirst())
						{
							System.out.println("coming under");
							do
							{
								notificationid[i]=c.getInt(0);
							heading[i]=c.getString(1);
							subheading[i]=c.getString(2);
							year[i]=c.getInt(4);
							month[i]=c.getInt(5);
							day[i]=c.getInt(6);
							hours[i]=c.getInt(7);
							minutes[i]=c.getInt(8);
							i++;
							}while(c.moveToNext());
					    
						dialog.setContentView(R.layout.dialog);
						dialog.getWindow().setBackgroundDrawable(new ColorDrawable(0)); 
							edit = (ListView) dialog.findViewById(R.id.dialog_menu);

						        SpecialAdapter2 adapter = new SpecialAdapter2(getApplicationContext(),heading,subheading);

						    edit.setAdapter(adapter);
						    dialog.show();

						}
						else
						{
							//dialog.dismiss();
							Toast.makeText(getApplicationContext(), "No reminders", Toast.LENGTH_SHORT).show();
						}
						
						q.close();
						
			
					}
					});
		 	
		        
		    
			 
			 
		        // set up a listener for when the button is pressed
		        dateTimeButton.setOnClickListener(new OnClickListener() {
		            public void onClick(View arg0) {
		                // call the internal showDialog method using the predefined ID
		                showDialog(DATETIMESELECTOR_ID);
		            }
		        });
		        
		        addrem.setOnClickListener(new View.OnClickListener() {
					public void onClick(View v)
					{
						   q.open();
						    
						if(displaysub.getText().toString().equalsIgnoreCase(""))
						{
				Toast.makeText(getApplicationContext(), "Select a Subject", Toast.LENGTH_SHORT).show();		
						}
						else
						{
							if(displaycat.getText().toString().equalsIgnoreCase(""))
							{
					Toast.makeText(getApplicationContext(), "Select a Category", Toast.LENGTH_SHORT).show();		
							}
							else
							{
								if(displaydatetime.getText().toString().equalsIgnoreCase(""))
								{
						Toast.makeText(getApplicationContext(), "Select a Date", Toast.LENGTH_SHORT).show();		
								}
								else
								{
									System.out.println(q_year);
									System.out.println(q_month);
									System.out.println(q_day);
									System.out.println(q_hours);
									System.out.println(q_minutes);
				q.createEntry(sub, cat, "", q_year, q_month, q_day, q_hours, q_minutes,displaylabel.getText().toString());
				stopService(new Intent(getBaseContext(),MyService2.class));
				startService(new Intent(getBaseContext(),MyService2.class));
				displaysub.setText("");
				displaycat.setText("");
				displaylabel.setText("");
				displaydatetime.setText("");
				
								}
							}
						}
						q.close();
						Toast.makeText(getApplicationContext(), "Reminder added", Toast.LENGTH_SHORT).show();
					}
				});
	
		    
			
		 	  final Dialog options_dialog=new Dialog(this);
			  final Dialog options_dialog2=new Dialog(this);
			  
		       options_dialog.setContentView(R.layout.options_dialog1);
		       options_dialog2.setContentView(R.layout.options_dialog2);
		     options_dialog.getWindow().setBackgroundDrawable(new ColorDrawable(0));  
		     options_dialog2.getWindow().setBackgroundDrawable(new ColorDrawable(0));  
			    
		       final ListView optionslist=(ListView)options_dialog.findViewById(R.id.optionslist1);
		       final ListView optionslist2=(ListView)options_dialog2.findViewById(R.id.optionslist2);
		       final String[] category={"quiz","assignment","Others"};
		    
		 
			 selsub.setOnClickListener(new View.OnClickListener() {
					public void onClick(View v)
					{
					//	 listAdapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1, subjects);
					      
					SpecialAdapter a=new SpecialAdapter(getApplicationContext(), subjects);	
						
						optionslist.setAdapter(a);
						
						options_dialog.show();
					
					}
				});
			 selcat.setOnClickListener(new View.OnClickListener() {
					public void onClick(View v)
					{
					
						//listAdapter2 = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1, category );
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
		            displaysub.setText(subjects[position]);
		            
		          		                         }
		        });
			  optionslist2.setOnItemClickListener(new OnItemClickListener() {

		            public void onItemClick(AdapterView<?> arg0, View view, int position,
		                    long id) {
		            	options_dialog2.dismiss();
		            System.out.println("postion"+position);
		            System.out.println(category[position]);
		            cat=category[position];
		            displaycat.setText(category[position]);
		          		                         }
		        });

		
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
	                
	              
	               System.out.println((String.format("The chosen date and time:%n%te. %tB %tY%n%tH:%02d",
	                        selectedDate, selectedDate, selectedDate, selectedDate, minute)));
	                q_year=Integer.parseInt((String.format("%tY",
	                        selectedDate, selectedDate, selectedDate, selectedDate, minute)));
	                q_hours=Integer.parseInt(String.format("%tH",
	                		selectedDate, selectedDate, selectedDate, selectedDate, minute));
	                q_minutes=minute;
	                q_day=Integer.parseInt(String.format("%te",
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
	                displayeddate=w1+"   "+x;
	                System.out.println(displayeddate);
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
		 static class ViewHolder2 {
		        TextView quiz_heading;
		        TextView quiz_subheading;  
		        Button quiz_edit;
		        Button quiz_delete;
		    }

		 private class SpecialAdapter2 extends BaseAdapter {
				private LayoutInflater mInflater;

				//The variable that will hold our text data to be tied to list.
				private String[] data1;
			    private String[] data2;
			    
				public SpecialAdapter2(Context context, String[] items1,String[] items2)
				{
				    mInflater = LayoutInflater.from(context);
				    this.data1 = items1;
				    this.data2 = items2;
				   
				}

				public int getCount() {
				    return data1.length;
				   
				}

				public Object getItem(int position) {
				    return position;
				}

				public long getItemId(int position) {
				    return position;
				}
					
				//A view to hold each row in the list
				public View getView(final int position, View convertView, ViewGroup parent) {
					 ViewHolder2   holder = new ViewHolder2();
						
				// A ViewHolder keeps references to children views to avoid unnecessary calls
				// to findViewById() on each row.
			
				if (convertView == null) {
				    convertView = mInflater.inflate(R.layout.editquiz, null);
	             convertView.setMinimumHeight(70);
				    holder.quiz_heading = (TextView) convertView.findViewById(R.id.quiz_sub);
				    holder.quiz_subheading = (TextView) convertView.findViewById(R.id.quiz_subheading);
				    holder.quiz_edit = (Button) convertView.findViewById(R.id.quiz_edit);
				    holder.quiz_delete = (Button) convertView.findViewById(R.id.quiz_del);
					
					  
				    
				    convertView.setTag(holder);
				} else {
				    holder = (ViewHolder2) convertView.getTag();
				}
				
				    // Bind the data efficiently with the holder.
					holder.quiz_heading.setText(data1[position]);
				    holder.quiz_subheading.setText(data2[position]);
				    holder.quiz_edit.setOnClickListener(new View.OnClickListener() {
							public void onClick(View v)
							{
							dialog.dismiss();
					Bundle b=new Bundle();
					b.putString("sub", data1[position]);
					b.putString("cat", data2[position]);
					b.putInt("year", year[position]);
					b.putInt("month", month[position]);
					b.putInt("day", day[position]);
					b.putInt("hours", hours[position]);
					b.putInt("minutes", minutes[position]);
					b.putInt("rowid", notificationid[position]);
					   String w1=Integer.toString(day[position])+" "+Integer.toString(month[position])+" "+Integer.toString(year[position]);
				         String x= Integer.toString(hours[position])+":"+Integer.toString(minutes[position]);
				         
				           /*     displaydatetime.setText(w1
				                		+"   "+x);*/
				                displayeddate=w1+"   "+x;
				      
					b.putString("display_date", displayeddate);
					System.out.println(displayeddate);
					Intent i=new Intent(QuizRem3.this,QuizRem4.class);
					i.putExtras(b);
					startActivity(i);
							}
							});
				
				   holder.quiz_delete.setOnClickListener(new View.OnClickListener() {
						public void onClick(View v)
						{
					q.open();
					q.delete(notificationid[position]);
					q.close();
					view();
					stopService(new Intent(getBaseContext(),MyService2.class));
					startService(new Intent(getBaseContext(),MyService2.class));
				
					
							}
					});
			
				   return convertView;
				}

				}
public void view()
{
	System.out.println("testing babes");
	q.open();
	Cursor c=q.getAllContacts2();
	int  len=c.getCount();
	System.out.println(len);
	heading=new String[len];
	subheading=new String[len];
	year=new int[len];
	month=new int[len];
	day=new int[len];
	hours=new int[len];
	minutes=new int[len];
	notificationid=new int[len];
	int i=0;
	if(c.moveToFirst())
	{
		System.out.println("coming under");
		do
		{
			notificationid[i]=c.getInt(0);
		heading[i]=c.getString(1);
		subheading[i]=c.getString(2);
		year[i]=c.getInt(4);
		month[i]=c.getInt(5);
		day[i]=c.getInt(6);
		hours[i]=c.getInt(7);
		minutes[i]=c.getInt(8);
		i++;
		}while(c.moveToNext());
    
	dialog.setContentView(R.layout.dialog);
	dialog.getWindow().setBackgroundDrawable(new ColorDrawable(0)); 
		edit = (ListView) dialog.findViewById(R.id.dialog_menu);

	        SpecialAdapter2 adapter = new SpecialAdapter2(getApplicationContext(),heading,subheading);

	    edit.setAdapter(adapter);
	    dialog.show();

	}
	else
	{
		dialog.dismiss();
		//Toast.makeText(getApplicationContext(), "No reminders", Toast.LENGTH_SHORT).show();
	}
	
	q.close();
	
}


}

package com.googlecode.android.widgets.DateSlider;



import com.commonsware.cwac.merge.MergeAdapter;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.location.GpsStatus.Listener;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.AdapterView.OnItemClickListener;

public class Marks extends Activity implements ScrollViewListener ,ScrollViewListener2
{
	String[] course_title, quiz1,quiz2,quiz3,cat1,cat2,assignment;
	ListView list,list_marks;
	private ArrayAdapter<String> listAdapter ;
	View mTouched=null;;
	boolean mMovingFlag=false;
	LinearLayout mlayout;
	View clickSource=null;
	View touchSource=null;
	int length_subjects=0;
	ObservableScrollView2 scroll_marks_subjects,scroll_display_subjecth;
	ObservableScrollView scroll_subjectname,scroll_heading;
	 protected void onCreate(Bundle savedInstanceState) {
		    //TODO Auto-generated method stub
		 	super.onCreate(savedInstanceState);
		 	setContentView(R.layout.marks);
		 	
		 	
		 	
		 	
		 	
		 	
			DBAdapter info = new DBAdapter(this);
			info.open();
			Cursor c=info.getData();
			int len=c.getCount();
			length_subjects=c.getCount();
			System.out.println(len);
			course_title=new String[len];
			quiz1=new String[len];
			quiz2=new String[len];
			quiz3=new String[len];
			cat1=new String[len];
			cat2=new String[len];
			assignment=new String[len];
			
			int i=0;
			if(c.moveToFirst())
			{
				do
				{
				course_title[i]=c.getString(1);
				quiz1[i]=c.getString(4);
				quiz2[i]=c.getString(5);
				quiz3[i]=c.getString(6);
				cat1[i]=c.getString(7);
				cat2[i]=c.getString(8);
				assignment[i]=c.getString(9);
			System.out.println(course_title[i]);
			System.out.println(quiz1[i]);
    		System.out.println(quiz2[i]);
    		System.out.println(quiz3[i]);
    		System.out.println(cat1[i]);
    		System.out.println(cat2[i]);
    		System.out.println(assignment[i]);
    	
					i++;
				}while(c.moveToNext());
			}
			info.close();
			
		    scroll_marks_subjects=(ObservableScrollView2)findViewById(R.id.scroll_marks_subjects);
		    scroll_display_subjecth=(ObservableScrollView2)findViewById(R.id.scroll_display_subjecth);
		    scroll_heading=(ObservableScrollView)findViewById(R.id.scroll_heading);
		    scroll_subjectname=(ObservableScrollView)findViewById(R.id.scroll_display_marksv);

		    LinearLayout scroll_linear2=(LinearLayout)findViewById(R.id.scroll_marks_subjectname);
	
	     
	       TextView[] dat22=new TextView[length_subjects];

		for(int  j=0;j<length_subjects;j++)
		{
				     
		   dat22[j]=new TextView(getApplicationContext());
		    dat22[j].setWidth(170);
		    dat22[j].setHeight(55);
		   dat22[j].setText(course_title[j]);
		   dat22[j].setPadding(2, 4, 0, 0);
		   dat22[j].setTextColor(getResources().getColor(android.R.color.black));
		   
		   TextView space=new TextView(this);
			  space.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT,2));
			//   space.setBackgroundResource(getResources().getColor(android.R.color.black));
			   space.setBackgroundColor(getResources().getColor(android.R.color.darker_gray));
				 
			
		  scroll_linear2.addView(dat22[j]);
		  scroll_linear2.addView(space);
		}
		

	    LinearLayout scroll_displaysubs=(LinearLayout)findViewById(R.id.scroll_displaysubs);
	    TextView[] marks_quiz1=new TextView[length_subjects];
	    TextView[] marks_quiz2=new TextView[length_subjects];
	    TextView[] marks_quiz3=new TextView[length_subjects];
	    TextView[] marks_cat1=new TextView[length_subjects];
	    TextView[] marks_cat2=new TextView[length_subjects];
	    TextView[] marks_assn=new TextView[length_subjects];
	    TextView[] marks_term=new TextView[length_subjects];
	    LinearLayout[] marks_linear=new LinearLayout[length_subjects];
	    
	    scroll_display_subjecth.setScrollViewListener(this);
	    scroll_marks_subjects.setScrollViewListener(this);
	    
	    scroll_subjectname.setScrollViewListener(this);
	    scroll_heading.setScrollViewListener(this);




	    for(int  j=0;j<length_subjects;j++)
		{
	    	 marks_linear[j]=new LinearLayout(getApplicationContext());
			    marks_linear[j].setOrientation(LinearLayout.HORIZONTAL);	     
		   marks_quiz1[j]=new TextView(getApplicationContext());
		    marks_quiz1[j].setWidth(70);
		    marks_quiz1[j].setHeight(55);
		    marks_quiz1[j].setText(quiz1[j]);
		    marks_quiz1[j].setTextColor(getResources().getColor(android.R.color.black));
		    
		    marks_quiz2[j]=new TextView(getApplicationContext());
		    marks_quiz2[j].setWidth(70);
		    marks_quiz2[j].setHeight(55);
		    marks_quiz2[j].setText(quiz2[j]);
		    marks_quiz2[j].setTextColor(getResources().getColor(android.R.color.black));
		    
		    marks_quiz3[j]=new TextView(getApplicationContext());
		    marks_quiz3[j].setWidth(70);
		    marks_quiz3[j].setHeight(55);
		    marks_quiz3[j].setText(quiz3[j]);
		    marks_quiz3[j].setTextColor(getResources().getColor(android.R.color.black));
		    
		    marks_cat1[j]=new TextView(getApplicationContext());
		    marks_cat1[j].setWidth(70);
		    marks_cat1[j].setHeight(55);
		    marks_cat1[j].setText(cat1[j]);
		    marks_cat1[j].setTextColor(getResources().getColor(android.R.color.black));
		    
		    marks_cat2[j]=new TextView(getApplicationContext());
		    marks_cat2[j].setWidth(70);
		    marks_cat2[j].setHeight(55);
		    marks_cat2[j].setText(cat2[j]);
		    marks_cat2[j].setTextColor(getResources().getColor(android.R.color.black));
		    
		    marks_assn[j]=new TextView(getApplicationContext());
		    marks_assn[j].setWidth(70);
		    marks_assn[j].setHeight(55);
		    marks_assn[j].setText(assignment[j]);
		    marks_assn[j].setTextColor(getResources().getColor(android.R.color.black));
		    
		 
		   
		   
		   
		  marks_linear[j].addView(marks_quiz1[j]);
		  marks_linear[j].addView(marks_quiz2[j]);
		  marks_linear[j].addView(marks_quiz3[j]);
		  marks_linear[j].addView(marks_cat1[j]);
		  marks_linear[j].addView(marks_cat2[j]);
		  marks_linear[j].addView(marks_assn[j]);
		  
		  
		  TextView space=new TextView(this);
		  space.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT,2));
		//   space.setBackgroundResource(getResources().getColor(android.R.color.black));
		   space.setBackgroundColor(getResources().getColor(android.R.color.darker_gray));
			 
		  scroll_displaysubs.addView(marks_linear[j]);
		  scroll_displaysubs.addView(space);
		}

	
				 	
	 }
	 
	 
	 
	 
	 
	 @Override
	    public boolean onCreateOptionsMenu(Menu menu)
	    {
	        MenuInflater menuInflater = getMenuInflater();
	        menuInflater.inflate(R.layout.menu_marks, menu);
	        return true;
	    }
	 
	 
	 
	 
	 @Override
	    public boolean onOptionsItemSelected(MenuItem item)
	    {
	 
	        switch (item.getItemId())
	        {
	        case R.id.menu_pbl:
	            // Single menu item is selected do something
	            // Ex: launching new activity/screen or show alert message
	           // Toast.makeText(AndroidMenusActivity.this, "Bookmark is Selected", Toast.LENGTH_SHORT).show();
	        	Intent i =new Intent(this,pbl.class);
	        	startActivity(i);
	        	
	            return true;
	 
	        case R.id.menu_grades:
	           // Toast.makeText(AndroidMenusActivity.this, "Save is Selected", Toast.LENGTH_SHORT).show();
	        	Intent i1 =new Intent(this,grades.class);
	        	startActivity(i1);
	            return true;
	 
	 
	        default:
	            return super.onOptionsItemSelected(item);
	        }
	    }    
	 
	 
	 
	 
	 
		public void onScrollChanged(ObservableScrollView2 scrollView, int x, int y,
				int oldx, int oldy) {
			 if(scrollView == scroll_marks_subjects) {
		        	System.out.println("1");
		            scroll_display_subjecth.scrollTo(x, y);
		        } else if(scrollView == scroll_display_subjecth) {
		        	System.out.println("2");
		          scroll_marks_subjects.scrollTo(x, y);
		         
		        }
		}

		public void onScrollChanged(ObservableScrollView scrollView, int x,
				int y, int oldx, int oldy) {
			// TODO Auto-generated method stub
			 if(scrollView == scroll_heading) {
		        	System.out.println("1");
		            scroll_subjectname.scrollTo(x, y);
		        } else if(scrollView == scroll_subjectname) {
		        	System.out.println("2");
		          scroll_heading.scrollTo(x, y);
		         
		        }

			
		}
	 }

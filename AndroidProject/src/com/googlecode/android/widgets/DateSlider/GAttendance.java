
package com.googlecode.android.widgets.DateSlider;



import java.util.Date;
import java.text.SimpleDateFormat;
import java.text.ParseException;

import com.commonsware.cwac.merge.MergeAdapter;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.database.Cursor;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.ViewGroup.LayoutParams;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.AdapterView.OnItemClickListener;

public class GAttendance extends Activity  implements ScrollViewListener ,ScrollViewListener2
{
	String[] course_title;
	ListView list,list_attn;
	String[][] attn;
	View clickSource=null;
	View touchSource=null;
	MergeAdapter merge;
	int length_dates,length_subjects;
	Date[] class_dates;
	ListView list_dates;
	String[] class_dates_string;
	private ArrayAdapter<String> listAdapter ;
    ObservableScrollView scroll_dates = null;
	    ObservableScrollView scroll_subjects = null;
	    ObservableScrollView subject_name=null;
	    ObservableScrollView2 check = null;
	    ObservableScrollView2 check2 = null;

	 protected void onCreate(Bundle savedInstanceState) {
		    //TODO Auto-generated method stub
		 	super.onCreate(savedInstanceState);
		 	setContentView(R.layout.attendance2);
		 	
		 	
			GDBAdapter info = new GDBAdapter(this);
			info.open();
			Cursor c=info.getData();
			length_subjects=c.getCount();
			System.out.println(length_subjects);
			course_title=new String[length_subjects];
			int i=0;
			if(c.moveToFirst())
			{
			do
				{
				course_title[i]=c.getString(1);
			//System.out.println(course_title[i]);
					i++;
				}while(c.moveToNext());
			}
			info.close();
			
			 SimpleDateFormat format = new SimpleDateFormat("dd-MMM-yyyy");
				
			GFaculty fac=new GFaculty(this);
			fac.open();
			Cursor c2=fac.getAllContacts();
			length_dates=c2.getCount();
			attn=new String[length_subjects][length_dates];
			System.out.println("lenghtoof"+length_dates);
			String[] dates=new String[length_dates];
			class_dates=new Date[length_dates];
			class_dates_string=new String[length_dates];
			 i=0;
			 int j=0;
				if(c2.moveToFirst())
			do
				{
			dates[j]=c2.getString(0);
		    try {
		      
		        class_dates[j]= format.parse(dates[j]);
		    } catch (Exception e) {
		        e.printStackTrace();
		    }
		    System.out.println(class_dates[j]);
		
			j++;
				}while(c2.moveToNext());
		
	 fac.close();
	 
	for( i=0;i<length_subjects;i++)
	 {
		 j=0;
		if(c2.moveToFirst())
		{
			do
			{
			attn[i][j]=c2.getString(i+1);
			j++;
		}while(c2.moveToNext());
		
	 }}
	 
	
	
	  Date temp;
	  String temp2;
	  for( i=0;i<length_dates;i++)
	{
	for(j=i+1;j<length_dates;j++)
	{
		if((class_dates[i].compareTo(class_dates[j])>0))
				{
			temp=class_dates[i];
			class_dates[i]=class_dates[j];
			class_dates[j]=temp;
			for(int k=0;k<length_subjects;k++)
			{
				temp2=attn[k][i];
				attn[k][i]=attn[k][j];
				attn[k][j]=temp2;
			}
				}
	}
	}

	  
    SimpleDateFormat sdf =  new SimpleDateFormat("MMM dd");
 for( i=0;i<length_dates;i++)
 {
	 System.out.println(class_dates[i]);
	class_dates_string[i]= sdf.format(class_dates[i]);
 }
		      		  scroll_dates=(ObservableScrollView)findViewById(R.id.scroll_dates);
		    LinearLayout scroll_linear=(LinearLayout)findViewById(R.id.scroll_linear);
		    LinearLayout[] l2= new LinearLayout[length_dates];
		     
		       TextView[] dat2=new TextView[length_dates];
				int x; 
		 for( i=0;i<length_dates;i++)   
		 {
		       l2[i]=new LinearLayout(getApplicationContext());
			    l2[i].setOrientation(LinearLayout.VERTICAL);
			   dat2[i]=new TextView(getApplicationContext());
			    dat2[i].setWidth(71);
			    dat2[i].setHeight(55);
			   dat2[i].setText(class_dates_string[length_dates-i-1]);
			   dat2[i].setPadding(0, 20, 0, 0);
			   dat2[i].setId(i);
			   dat2[i].setTextSize(15);
			   dat2[i].setTextColor(getResources().getColor(android.R.color.white));
			   x=dat2[i].getId();
			    l2[i].addView(dat2[i]);
		    scroll_linear.addView(l2[i]);
	      
		     
		 }
		
		    scroll_subjects=(ObservableScrollView)findViewById(R.id.scroll_subjects);
		    LinearLayout scroll_linear2=(LinearLayout)findViewById(R.id.scroll_linear2);
		  LinearLayout[] l22= new LinearLayout[length_subjects];
	     
	      ImageView[][] dat22=new ImageView[length_subjects][length_dates];
		x=0;
		for( j=0;j<length_subjects;j++)
		{
			  l22[j]=new LinearLayout(getApplicationContext());
			    l22[j].setOrientation(LinearLayout.HORIZONTAL);
	 for( i=0;i<length_dates;i++)   
	 {
	     
		   dat22[j][i]=new ImageView(getApplicationContext());
		    dat22[j][i].setMinimumWidth(70);
		    dat22[j][i].setMinimumHeight(55);
		    dat22[j][i].setPadding(20, 10, 20, 10);
			 
		    if(!(attn[j][length_dates-i-1]==null))
		    {
		    if(attn[j][length_dates-i-1].equalsIgnoreCase("Present") || attn[j][length_dates-i-1].equalsIgnoreCase("On Duty"))
		    dat22[j][i].setImageResource(R.drawable.right);
		    else
		    dat22[j][i].setImageResource(R.drawable.wrong);
		    }

		    l22[j].addView(dat22[j][i]);
		    TextView space=new TextView(this);
			  space.setLayoutParams(new LayoutParams(1,LayoutParams.MATCH_PARENT));
			//   space.setBackgroundResource(getResources().getColor(android.R.color.black));
			   space.setBackgroundColor(getResources().getColor(android.R.color.darker_gray));
			l22[j].addView(space);	
	
     
	     
	 } 
	  TextView space=new TextView(this);
	  space.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT,2));
	//   space.setBackgroundResource(getResources().getColor(android.R.color.black));
	   space.setBackgroundColor(getResources().getColor(android.R.color.darker_gray));
		 
	scroll_linear2.addView(space);
	  scroll_linear2.addView(l22[j]);
		}
	   scroll_dates.setScrollViewListener(this);
       scroll_subjects.setScrollViewListener(this);
       subject_name=(ObservableScrollView)findViewById(R.id.scroll_subjectname);
       check=(ObservableScrollView2)findViewById(R.id.check1);
       check2=(ObservableScrollView2)findViewById(R.id.check2);				 
       check.setScrollViewListener(this);
       check2.setScrollViewListener(this);
       LinearLayout scroll_linear3=(LinearLayout)findViewById(R.id.scroll_linear3);
	    LinearLayout[] l3= new LinearLayout[length_subjects];
	      
	       TextView[] dat3=new TextView[length_subjects];
	 for( i=0;i<length_subjects;i++)   
	 {
		   dat3[i]=new TextView(getApplicationContext());
		    dat3[i].setWidth(170);
		    dat3[i].setHeight(55);
		   dat3[i].setText(course_title[i]);
		  dat3[i].setTextColor(getResources().getColor(android.R.color.black));
		    
		  TextView space=new TextView(this);
		  space.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT,2));
		//   space.setBackgroundResource(getResources().getColor(android.R.color.black));
		   space.setBackgroundColor(getResources().getColor(android.R.color.darker_gray));
			 
		scroll_linear3.addView(space);
	    scroll_linear3.addView(dat3[i]);
     
	     
	 }
	 subject_name.setScrollViewListener(this);
	 }
	 


	 public void onScrollChanged(ObservableScrollView scrollView, int x, int y, int oldx, int oldy) {
		 System.out.println("x="+Integer.toString(x));
		 System.out.println("x="+Integer.toString(y));
	        if(scrollView == scroll_dates) {
	        	System.out.println("1");
	            scroll_subjects.scrollTo(x, y);
	        } else if(scrollView == scroll_subjects) {
	        	System.out.println("2");
	            scroll_dates.scrollTo(x, y);
	            subject_name.scrollTo(x,y);
	        }
	        else if(scrollView==subject_name)
	        {
	        	System.out.println("scoll");
	        	scroll_subjects.scrollTo(x, y);
	        }
	    }

	public void onScrollChanged(ObservableScrollView2 scrollView, int x, int y,
			int oldx, int oldy) {
		 if(scrollView == check) {
	        	System.out.println("1");
	            check2.scrollTo(x, y);
	        } else if(scrollView == check2) {
	        	System.out.println("2");
	          check.scrollTo(x, y);
	         
	        }

		// TODO Auto-generated method stub
		
	}


}

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

public class Attendance extends Activity implements ScrollViewListener ,ScrollViewListener2
{
	String[] course_title;
	ListView list,list_attn;
	String[][] attn, attn_stud;
	View clickSource=null;
	View touchSource=null;
	MergeAdapter merge;
	int length_dates,length_subjects,length_dates_stud,final_dates_length;
	Date[] class_dates,class_dates_stud;
	ListView list_dates;
	String[] class_dates_string,class_dates_string_stud,final_dates_string;
	private ArrayAdapter<String> listAdapter ;
	ObservableScrollView scroll_dates = null;
    ObservableScrollView scroll_subjects = null;
    ObservableScrollView subject_name=null;
    ObservableScrollView2 check = null;
    ObservableScrollView2 check2 = null;
    int count_fac=0;
    int count_stud=0;

	 protected void onCreate(Bundle savedInstanceState) {
		    //TODO Auto-generated method stub
		 	super.onCreate(savedInstanceState);
		 	setContentView(R.layout.attendance2);
		 	
		 	Student stud2=new Student(this);
		 	stud2.open();
		 	Cursor x=stud2.getAllContacts();
		 		 	System.out.println("fuuu"+x.getCount());
		 			stud2.close();

			DBAdapter info = new DBAdapter(this);
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
				
			Faculty fac=new Faculty(this);
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
 String[] final_dates=new String[200];
 
			//fac.close();
			
			
 Student stud=new Student(this);
	stud.open();
	Cursor c3=stud.getAllContacts();
	length_dates_stud=c3.getCount();
	attn_stud=new String[length_subjects][length_dates_stud];
	System.out.println("length of"+length_dates_stud);
	String[] dates_stud=new String[length_dates_stud];
	class_dates_stud=new Date[length_dates_stud];
	class_dates_string_stud=new String[length_dates_stud];
	 i=0;
  j=0;
		if(c3.moveToFirst())
	do
		{
	dates_stud[j]=c3.getString(0);
 try {
   
     class_dates_stud[j]= format.parse(dates_stud[j]);
 } catch (Exception e) {
     e.printStackTrace();
 }
 System.out.println(class_dates_stud[j]);

	j++;
		}while(c3.moveToNext());

fac.close();

for( i=0;i<length_subjects;i++)
{
j=0;
if(c3.moveToFirst())
{
	do
	{
	attn_stud[i][j]=c3.getString(i+1);
	j++;
}while(c3.moveToNext());

}}




for( i=0;i<length_dates_stud;i++)
{
for(j=i+1;j<length_dates_stud;j++)
{
if((class_dates_stud[i].compareTo(class_dates_stud[j])>0))
		{
	temp=class_dates_stud[i];
	class_dates_stud[i]=class_dates_stud[j];
	class_dates_stud[j]=temp;
	for(int k=0;k<length_subjects;k++)
	{
		temp2=attn_stud[k][i];
		attn_stud[k][i]=attn_stud[k][j];
		attn_stud[k][j]=temp2;
	}
		}
}
}


System.out.println("finally yr");
for( i=0;i<length_dates_stud;i++)
{
System.out.println(class_dates_stud[i]);
class_dates_string_stud[i]= sdf.format(class_dates_stud[i]);
}
	//fac.close();
	System.out.println("be ready");	
	System.out.println(length_dates);
	System.out.println(length_dates_stud);
			
		for(i=0;i<length_subjects;i++)
		{
			System.out.println(course_title[i]);
			for( j=0;j<length_dates_stud;j++)
			{
				System.out.println(dates_stud[j]+attn_stud[i][j]);
			}
		}
			
String[] finaldates=	new String[200];
for(i=0;i<length_dates;i++)
{
	finaldates[i]=class_dates_string[i];
	
}
int flag=0;
System.out.println("before the value of i is"+i);
System.out.println(length_dates);
System.out.println(length_dates_stud);
for(j=0;j<length_dates_stud;j++)
{
//	System.out.println(class_dates_string_stud[j]);
	for(int k=0;k<length_dates;k++)
	{
	
		if(class_dates_string_stud[j].equalsIgnoreCase(class_dates_string[k]))
		{
		
		flag=1;
		}
	}
	if(flag==0)
	{
		System.out.println("it entered yr");
		finaldates[i]=class_dates_string_stud[j];
		i++;
	}
	flag=0;
}
final_dates_string=new String[i];
final_dates_length=i;

	for( int w=0;w<i;w++)
	{
		final_dates_string[w]=finaldates[w];
		
		System.out.println(final_dates_string[w]);
	}
			System.out.println("the value of i is"+i);
			System.out.println(final_dates_length);
			
			
			
			
    		  scroll_dates=(ObservableScrollView)findViewById(R.id.scroll_dates);
  		    LinearLayout scroll_linear=(LinearLayout)findViewById(R.id.scroll_linear);
  		    LinearLayout[] l2= new LinearLayout[final_dates_length];
  		     
  		       TextView[] dat2=new TextView[final_dates_length];
  			
  		 for( i=0;i<final_dates_length;i++)   
  		 {
  			 System.out.println(final_dates_string[i]);
  		       l2[i]=new LinearLayout(getApplicationContext());
  			    l2[i].setOrientation(LinearLayout.VERTICAL);
  			   dat2[i]=new TextView(getApplicationContext());
  			    dat2[i].setWidth(71);
  			    dat2[i].setHeight(55);
  			   dat2[i].setText(final_dates_string[final_dates_length-i-1]);
  			   dat2[i].setTextColor(getResources().getColor(android.R.color.white));
  				dat2[i].setPadding(0, 20, 0, 0);
  			    l2[i].addView(dat2[i]);
  			
  		    scroll_linear.addView(l2[i]);
  	      
  		     
  		 }
  		
  		    scroll_subjects=(ObservableScrollView)findViewById(R.id.scroll_subjects);
  		    LinearLayout scroll_linear2=(LinearLayout)findViewById(R.id.scroll_linear2);
  		  LinearLayout[] l22= new LinearLayout[length_subjects];
  		 LinearLayout[] l= new LinearLayout[length_subjects];
  		 LinearLayout[] l1= new LinearLayout[length_subjects];
  	     
  	       ImageView[][] dat22=new ImageView[length_subjects][final_dates_length];
  	     ImageView[][] dat222=new ImageView[length_subjects][final_dates_length];
   		
  		for( j=0;j<length_subjects;j++)
  		{
  			  l22[j]=new LinearLayout(getApplicationContext());
  			 l1[j]=new LinearLayout(getApplicationContext());
  			 l[j]=new LinearLayout(getApplicationContext());
 			  
 			  
  			    l22[j].setOrientation(LinearLayout.VERTICAL);
  			  l[j].setOrientation(LinearLayout.HORIZONTAL);
  			 l1[j].setOrientation(LinearLayout.HORIZONTAL);
  			 int c_stud=0;
  	 for( i=0;i<final_dates_length;i++)   
  	 {
  	     
  		   dat22[j][i]=new ImageView(getApplicationContext());
  		    dat22[j][i].setMinimumWidth(70);
  		    dat22[j][i].setMinimumHeight(55);
  		   dat22[j][i].setPadding(20, 10, 20, 10);
  		 
  		    if(c_stud<length_dates_stud)
  		    {
  		    if((final_dates_string[final_dates_length-i-1].equalsIgnoreCase(class_dates_string_stud[length_dates_stud -c_stud-1])) && (c_stud<length_dates_stud))
  		    {
  		   
  		  if(!(attn_stud[j][final_dates_length-i-1]==null))
		    {
		    if(attn_stud[j][length_dates_stud-i-1].equalsIgnoreCase("Present") || attn_stud[j][length_dates_stud-i-1].equalsIgnoreCase("On Duty"))
		    dat22[j][i].setImageResource(R.drawable.right);
		    else
		    dat22[j][i].setImageResource(R.drawable.wrong);
		    }

  		   c_stud++;
  		    }
  		    }
  		  
  		  TextView space=new TextView(this);
  		  space.setLayoutParams(new LayoutParams(1,LayoutParams.MATCH_PARENT));
  		//   space.setBackgroundResource(getResources().getColor(android.R.color.black));
  		   space.setBackgroundColor(getResources().getColor(android.R.color.darker_gray));
  		
  	  	
  	  	    l[j].addView(dat22[j][i]);
  	  	l[j].addView(space);
  		
  	
       
  	     
  	 } 
  	 int c_fac=0;
  	 for( i=0;i<final_dates_length;i++)   
  	 {
  	     
  		   dat222[j][i]=new ImageView(getApplicationContext());
  		    dat222[j][i].setMinimumWidth(70);
  		    dat222[j][i].setMinimumHeight(55);
  		   dat222[j][i].setPadding(20, 10, 20, 10);
  		 
  		    if(c_fac<length_dates)
  		    {
  		  if((final_dates_string[final_dates_length-i-1].equalsIgnoreCase(class_dates_string[length_dates-c_fac-1])) )
		    {
		 //  dat222[j][i].setText(attn[j][c_fac]);
		   if(!(attn[j][length_dates-c_fac-1]==null))
		    {
		    if(attn[j][length_dates-c_fac-1].equalsIgnoreCase("Present") || attn[j][final_dates_length-i-1].equalsIgnoreCase("On Duty"))
		    dat222[j][i].setImageResource(R.drawable.right);
		    else
		    dat222[j][i].setImageResource(R.drawable.wrong);
		    }

		   c_fac++;
		    }}
		   
  		  
  		  TextView space=new TextView(this);
  		  space.setLayoutParams(new LayoutParams(1,LayoutParams.MATCH_PARENT));
  		//   space.setBackgroundResource(getResources().getColor(android.R.color.black));
  		   space.setBackgroundColor(getResources().getColor(android.R.color.darker_gray));
  		
  	  	
  	  	
  		    l1[j].addView(dat222[j][i]);
  			l1[j].addView(space);
  	  		
  		
  	
       
  	     
  	 } 
     TextView space=new TextView(this);
	  space.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT,1));
	//   space.setBackgroundResource(getResources().getColor(android.R.color.black));
	   space.setBackgroundColor(getResources().getColor(android.R.color.darker_gray));
	
  	 l22[j].addView(l[j]);
  	 l22[j].addView(l1[j]);
  	l22[j].addView(space);	

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
  		    dat3[i].setHeight(110);
  		   dat3[i].setText(course_title[i]);
  		  
  		   dat3[i].setTextColor(getResources().getColor(android.R.color.black));
  		 TextView space=new TextView(this);
  		  space.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT,1));
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

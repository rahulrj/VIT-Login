package com.googlecode.android.widgets.DateSlider;



import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;


import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.gesture.GestureOverlayView;
import android.gesture.GestureOverlayView.OnGestureListener;
import android.os.Build;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ViewFlipper;

public class ViewflipActivity extends Activity implements com.googlecode.android.widgets.DateSlider.SimpleGestureFilter.SimpleGestureListener{
	 ViewFlipper flip;
	 int i=0;
	 boolean mIsScrolling=false;
	 SimpleGestureFilter detector;
	 TextView middle1,right1,left2,middle2,right2,left3,middle3;
	 String[] fac_name,fac_cabin;
	 int textlength = 0;
	 String[] from,to,reason;
	 
	 ArrayList<String> text_sort = new ArrayList<String>();
	 ArrayList<String> image_sort = new ArrayList<String>();
	 EditText edittext;
String[] cat1_dates,cat1_time,cat1_venue,cat2_dates,cat2_venue,cat2_time,term_dates,term_time,term_venue,title;
    /** Called when the activity is first created. */
    @Override
   
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
      setContentView(R.layout.main2);
        DBAdapter6 db=new DBAdapter6(this);
        db.open();
        Cursor c=db.getData();
        title=new String[c.getCount()];
        cat1_dates=new String[c.getCount()];
        cat1_time=new String[c.getCount()];
        cat1_venue=new String[c.getCount()];
        cat2_dates=new String[c.getCount()];
        cat2_time=new String[c.getCount()];
        cat2_venue=new String[c.getCount()];
        term_dates=new String[c.getCount()];
        term_time=new String[c.getCount()];
        term_venue=new String[c.getCount()];
        int i=0;
        if(c.moveToFirst())
        {
        	do
        	{
        title[i]=c.getString(1);	
        cat1_dates[i]=c.getString(2);	
        cat1_time[i]=c.getString(3);	
        cat1_venue[i]=c.getString(4);	
        cat2_dates[i]=c.getString(5);	
        cat2_time[i]=c.getString(6);	
        cat2_venue[i]=c.getString(7);	
        term_dates[i]=c.getString(8);	
        term_time[i]=c.getString(9);
        term_venue[i]=c.getString(10);
               	 SimpleDateFormat formatter = new SimpleDateFormat("dd-MMM-yyyy");
    		 SimpleDateFormat formatter2 = new SimpleDateFormat("MMM dd");
    		
			 System.out.println(title[i]);
			 try {
 
			 Date temp = formatter.parse(cat1_dates[i]);

		   cat1_dates[i]=new SimpleDateFormat("MMM dd").format(temp);
			 } catch (Exception e) {
			     e.printStackTrace();
			     }
			 try
			 {
		  
		   Date temp2 = formatter.parse(cat2_dates[i]);
		   System.out.println(temp2);
		   cat2_dates[i]=new SimpleDateFormat("MMM dd").format(temp2);
			 } catch (Exception e) {
			     e.printStackTrace();
			     }
			 try
			 {
	
		   Date temp3 = formatter.parse(term_dates[i]);
		   System.out.println(temp3);
		   term_dates[i]=new SimpleDateFormat("MMM dd").format(temp3);
		   System.out.println(term_dates[i]);
		     } catch (Exception e) {
		     e.printStackTrace();
		     }

        
        i++;
        	}while(c.moveToNext());
        }
        db.close();
        
              
      ListView  exam_list = (ListView) findViewById(R.id.exam_list);
    //  list.setOnTouchListener(this);

        SpecialAdapter adapter = new SpecialAdapter(this,title,cat1_dates,cat1_time,cat1_venue,cat2_dates,cat2_time,cat2_venue,term_dates,term_time,term_venue);
        exam_list.setAdapter(adapter);
        
        exam_list.setItemsCanFocus(false);
        
       
        
        ListView  acad_list = (ListView) findViewById(R.id.acad_list);
        //  list.setOnTouchListener(this);
        DBAdapter5 ac=new DBAdapter5(this);
        ac.open();
        Cursor c3=ac.getDates();
        from=new String[c3.getCount()];
        to=new String[c3.getCount()];
        reason=new String[c3.getCount()];
         i=0;
        if(c3.moveToFirst())
        {
        	do
        	{
        from[i]=c3.getString(0);	
        to[i]=c3.getString(1);	
        reason[i]=c3.getString(2);	
        
        i++;
        	}while(c3.moveToNext());
        }
        db.close();

            SpecialAdapter3 adapter3 = new SpecialAdapter3(this,reason,from,to);
           acad_list.setAdapter(adapter3);
            
            acad_list.setItemsCanFocus(false);

  
        
 
       detector = new SimpleGestureFilter(this,this); 
       FacultyCabin fc=new FacultyCabin(this);
       fc.open();
       Cursor c2=fc.getFaculties();
       fac_name=new String[c2.getCount()];
       fac_cabin=new String[c2.getCount()];
            i=0;
       if(c2.moveToFirst())
       {
       	do
       	{
      fac_name[i]=c2.getString(1);	
      fac_cabin[i]=c2.getString(2);	
            
       i++;
       	}while(c2.moveToNext());
       }
       fc.close();
       
        
       final ListView  fac_list = (ListView) findViewById(R.id.fac_list);
       //  list.setOnTouchListener(this);

           SpecialAdapter2 fac_adapter = new SpecialAdapter2(this,fac_name,fac_cabin);
           fac_list.setAdapter(fac_adapter);
           
           fac_list.setItemsCanFocus(false);
        
           edittext = (EditText) findViewById(R.id.searchbox);
           
           edittext.addTextChangedListener(new TextWatcher()
           {

            public void afterTextChanged(Editable s)
            {

            }

            public void beforeTextChanged(CharSequence s, int start,
             int count, int after)
            {

            }

            public void onTextChanged(CharSequence s, int start,
             int before, int count)
            {

             textlength = edittext.getText().length();
             text_sort.clear();
             image_sort.clear();

             for (int i = 0; i < fac_name.length; i++)
             {
              if (textlength <= fac_name[i].length())
              {
               if (edittext.getText().toString().
            equalsIgnoreCase((String) fac_name[i].subSequence(0, textlength)))
               {
                text_sort.add(fac_name[i]);
                image_sort.add(fac_cabin[i]);
               }
              }
             }

          /*   SpecialAdapter2 fac_adapter = new SpecialAdapter2(getApplicationContext(),fac_name,fac_cabin);
             fac_list.setAdapter(fac_adapter);
             
             fac_list.setItemsCanFocus(false);*/
            fac_list.setAdapter(new MyCustomAdapter
            	     (text_sort, image_sort));
          /*   SpecialAdapter2 fac_adapter2 = new SpecialAdapter2(getApplicationContext(),text_sort.toArray(new String[text_sort.size()]),image_sort.toArray(new String[text_sort.size()]));
             fac_list.setAdapter(fac_adapter2);
             
             fac_list.setItemsCanFocus(false);*/
          
            }
           });

  
        
        
      
      
        
        flip=(ViewFlipper)findViewById(R.id.flip);
        flip.setInAnimation(this,android.R.anim.slide_in_left);
   
     flip.setFlipInterval(1000);
     middle1=(TextView)findViewById(R.id.middle1);
     right1=(TextView)findViewById(R.id.right1);
     middle2=(TextView)findViewById(R.id.middle2);
     right2=(TextView)findViewById(R.id.right2);
   
   left2=(TextView)findViewById(R.id.left2);
     middle3=(TextView)findViewById(R.id.middle3);
     left3=(TextView)findViewById(R.id.left3);
   //  display();
   

    
	
	
    }

    private class ViewHolder
    {
    	TextView cat1_dates,cat1_time,cat1_venue,cat2_dates,cat2_venue,cat2_time,term_dates,term_time,term_venue,title;
    }
    private class SpecialAdapter extends BaseAdapter {
		private LayoutInflater mInflater;

		//The variable that will hold our text data to be tied to list.
		private String[] title;
	    private String[] cat1_date;
	    private String[] cat1_time;
	    private String[] cat1_venue;
	    private String[] cat2_date;
	    private String[] cat2_time;
	    private String[] cat2_venue;
	    private String[] term_date;
	    private String[] term_time;
	    private String[] term_venue;
	  
	  
	    private String[] data5;
		public SpecialAdapter(Context context, String[] title,String[] cat1_date,String[] cat1_time,String[] cat1_venue,String[] cat2_date,String []cat2_time,String[] cat2_venue, String[] term_date,String[] term_time,String[] term_venue) {
		    mInflater = LayoutInflater.from(context);
		    this.title=title;
		    this.cat1_date=cat1_date;
		    this.cat1_time=cat1_time;
		    this.cat1_venue=cat1_venue;
		    this.cat2_date=cat2_date;
		    this.cat2_time=cat2_time;
		    this.cat2_venue=cat2_venue;
		    this.term_date=term_date;
		    this.term_time=term_time;
		    this.term_venue=term_venue;
		}

		public int getCount() {
		    return title.length;
		   
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
			 ViewHolder  holder = new ViewHolder();

		if (convertView == null) {
		    convertView = mInflater.inflate(R.layout.schedule_row, null);
		 		    holder.title = (TextView) convertView.findViewById(R.id.sch_title);
		    holder.cat1_dates = (TextView) convertView.findViewById(R.id.cat1_dates);
		    holder.cat1_time = (TextView) convertView.findViewById(R.id.cat1_times);
		    holder.cat1_venue= (TextView) convertView.findViewById(R.id.cat1_venue);
		    holder.cat2_dates = (TextView) convertView.findViewById(R.id.cat2_dates);
		    holder.cat2_time = (TextView) convertView.findViewById(R.id.cat2_times);
		    holder.cat2_venue = (TextView) convertView.findViewById(R.id.cat2_venue);
		    holder.term_dates = (TextView) convertView.findViewById(R.id.term_dates);
		    holder.term_time = (TextView) convertView.findViewById(R.id.term_times);
		    holder.term_venue = (TextView) convertView.findViewById(R.id.term_venue);
			  
		    
		    
		    convertView.setTag(holder);
		} else {
		  holder = (ViewHolder) convertView.getTag();
		}
		holder.title.setText(title[position]);
			
				holder.cat1_dates.setText(cat1_date[position]);
		holder.cat1_time.setText(cat1_time[position]);
		holder.cat1_venue.setText(cat1_venue[position]);
		holder.cat2_dates.setText(cat2_date[position]);
		holder.cat2_time.setText(cat2_time[position]);
		holder.cat2_venue.setText(cat2_venue[position]);
		holder.term_dates.setText(term_date[position]) ;
		holder.term_time.setText(term_time[position]);
		holder.term_venue.setText(term_venue[position]);
		holder.title.setTextColor(getResources().getColor(android.R.color.black));
		holder.cat1_dates.setTextColor(getResources().getColor(android.R.color.black));
		holder.cat1_venue.setTextColor(getResources().getColor(android.R.color.black));
		holder.cat1_time.setTextColor(getResources().getColor(android.R.color.black));
		holder.cat2_dates.setTextColor(getResources().getColor(android.R.color.black));
		holder.cat2_venue.setTextColor(getResources().getColor(android.R.color.black));
		holder.cat2_time.setTextColor(getResources().getColor(android.R.color.black));
		holder.term_dates.setTextColor(getResources().getColor(android.R.color.black));
		holder.term_venue.setTextColor(getResources().getColor(android.R.color.black));
		holder.term_time.setTextColor(getResources().getColor(android.R.color.black));
	
	

		 //   holder.course_classes.setText(data4[position]);
		  //  holder.course_marks.setText(data5[position]);

		   return convertView;
		}

		}
    
    
     String TAG="ANSHU;";
public void onSwipe(int direction) {
     String str = "";
     
     switch (direction) {
     
     case SimpleGestureFilter.SWIPE_RIGHT : str = "Swipe Right";
     if(i>0)
     {
    flip.setOutAnimation(this, android.R.anim.slide_out_right);
     flip.showPrevious();
     i--;
     }
                                              break;
     case SimpleGestureFilter.SWIPE_LEFT :  str = "Swipe Left";
     if(i<2)
     {
    	  flip.setInAnimation(this, android.R.anim.slide_in_left);
     flip.showNext();
     i++;
     }
                                                    break;
     case SimpleGestureFilter.SWIPE_DOWN :  str = "Swipe Down";
                                                    break;
     case SimpleGestureFilter.SWIPE_UP :    str = "Swipe Up";
                                                    break;
                                              
     } 
   //   Toast.makeText(this, str, Toast.LENGTH_SHORT).show();
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
	@SuppressLint("NewApi")
	public void onGesture(GestureOverlayView arg0, MotionEvent arg1) {
		// TODO Auto-generated method stub
		
	}
	@TargetApi(Build.VERSION_CODES.DONUT)
	@SuppressLint("NewApi")
	public void onGestureCancelled(GestureOverlayView arg0, MotionEvent arg1) {
		// TODO Auto-generated method stub
		
	}
	@TargetApi(Build.VERSION_CODES.DONUT)
	@SuppressLint("NewApi")
	public void onGestureEnded(GestureOverlayView arg0, MotionEvent arg1) {
		// TODO Auto-generated method stub
		
	}
	@SuppressLint("NewApi")
	public void onGestureStarted(GestureOverlayView arg0, MotionEvent arg1) {
		// TODO Auto-generated method stub
		
	}
	 private class ViewHolder2
	    {
	    	TextView name,cabin; 
	    	}
	    private class SpecialAdapter2 extends BaseAdapter {
			private LayoutInflater mInflater;

			//The variable that will hold our text data to be tied to list.
			private String[] name;
		    private String[] cabin;
		 
			public SpecialAdapter2(Context context, String[] name,String[] cabin) {
			    mInflater = LayoutInflater.from(context);
			    this.name=name;
			    this.cabin=cabin;
			}

			public int getCount() {
			    return name.length;
			   
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
				 ViewHolder2  holder = new ViewHolder2();

			if (convertView == null) {
			    convertView = mInflater.inflate(R.layout.custom_fac_cabin, null);
			    convertView.setMinimumHeight(60);
			 		    holder.name = (TextView) convertView.findViewById(R.id.fac_name);
			 		   holder.cabin = (TextView) convertView.findViewById(R.id.fac_cabin);
			  			
			    
			    
			    convertView.setTag(holder);
			} else {
			  holder = (ViewHolder2) convertView.getTag();
			}
			holder.name.setText(name[position]);
			holder.cabin.setText(cabin[position]);
		
			   return convertView;
			}

			}
	    
	    class MyCustomAdapter extends BaseAdapter
	    {

	     String[] data_text;
	     String[] data_image;

	     MyCustomAdapter()
	     {

	     }

	     MyCustomAdapter(String[] text, String[] image)
	     {
	      data_text = text;
	      data_image = image;
	     }
	     
	     MyCustomAdapter(ArrayList<String> text, ArrayList<String> image)
	     { 
	     
	      data_text = new String[text.size()];
	      data_image = new String[image.size()];

	      for(int i=0;i<text.size();i++)
	      {
	       data_text[i] = text.get(i);
	       data_image[i] = image.get(i);
	      }

	     }

	     public int getCount()
	     {
	      return data_text.length;
	     }

	     public String getItem(int position)
	     {
	      return null;
	     }

	     public long getItemId(int position)
	     {
	      return position;
	     }

	     public View getView(int position, View convertView, ViewGroup parent)
	     {

	      LayoutInflater inflater = getLayoutInflater();
	      View row;

	      row = inflater.inflate(R.layout.listview, parent, false);

	      TextView textview = (TextView) row.findViewById(R.id.TextView01);
	      TextView imageview = (TextView) row
	        .findViewById(R.id.ImageView01);

	      textview.setText(data_text[position]);
	     imageview.setText(data_image[position]);

	      return (row);

	     }
	    }


	    private class ViewHolder3
	    {
	    	TextView reason,dates; 
	    	}
	    private class SpecialAdapter3 extends BaseAdapter {
			private LayoutInflater mInflater;

			//The variable that will hold our text data to be tied to list.
			private String[] dates1;
			private String[] dates2;
		    private String[] reason;
		 
			public SpecialAdapter3(Context context, String[] reason,String[] date1,String[] date2) {
			    mInflater = LayoutInflater.from(context);
			    this.dates1=date1;
			    this.dates2=date2;
			    this.reason=reason;
			}

			public int getCount() {
			    return reason.length;
			   
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
				 ViewHolder3  holder = new ViewHolder3();

			if (convertView == null) {
			    convertView = mInflater.inflate(R.layout.custom_row_acad, null);
			    convertView.setMinimumHeight(60);
			 		    holder.reason = (TextView) convertView.findViewById(R.id.acad_reason);
			 		   holder.dates= (TextView) convertView.findViewById(R.id.acad_date);
			  			
			    
			    
			    convertView.setTag(holder);
			} else {
			  holder = (ViewHolder3) convertView.getTag();
			}
			holder.reason.setText(reason[position]);
			if(dates1[position].equalsIgnoreCase(dates2[position]))
			{
				holder.dates.setText(dates1[position]);
			}
			else
			{
				holder.dates.setText(dates1[position]+"-"+dates2[position]);
			}
		
			   return convertView;
			}

			}
	    
	 
  
}
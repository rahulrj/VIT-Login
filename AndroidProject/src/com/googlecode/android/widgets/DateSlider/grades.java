package com.googlecode.android.widgets.DateSlider;
 
import java.text.ParseException;

import java.util.ArrayList;



import java.util.ArrayList;





import android.app.Activity;
import android.gesture.Gesture;
import android.gesture.GestureLibraries;
import android.gesture.GestureLibrary;
import android.gesture.GestureOverlayView;
import android.gesture.GestureOverlayView.OnGesturePerformedListener;
import android.gesture.Prediction;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;
import android.webkit.WebView;
import android.widget.Toast;
import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.telephony.PhoneStateListener;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.BaseExpandableListAdapter;
import android.widget.EditText;
import android.widget.ExpandableListView;
import android.widget.ExpandableListView.OnChildClickListener;
import android.widget.TextView;
import android.widget.Toast;
 
public class grades extends Activity implements OnChildClickListener{
 
	private ArrayList<String> groups;
	GestureLibrary mLibrary;
	private ArrayList<ArrayList<ArrayList<String>>> childs;
	String tag="evevnts";
 String LOGTAG="EVENTS";
 Dialog dialog1,dialog2,dialog3;
 
 OnChildClickListener my;
 DBAdaptergrades dbg;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
 
        setContentView(R.layout.fmessagenew);
        dbg=new DBAdaptergrades(this);
        
        
 
        ExpandableListView l = (ExpandableListView) findViewById(R.id.ExpandableListView01);
 
        loadData();
 
        myExpandableAdapter adapter = new myExpandableAdapter(this, groups, childs);
		l.setAdapter(adapter);
		
		
		 
		 
		// mLibrary = GestureLibraries.fromRawResource(this, R.raw.gestures);
	        
        /* if (!mLibrary.load()) {
      
           finish();
      
         }*/
      
       
      
        

		l.setOnChildClickListener(this);
		//elv=getExpandableListView();
		
		
		/*l.setOnChildClickListener(new OnChildClickListener() {

	        public boolean onChildClick(ExpandableListView parent, View v,
	                int groupPosition, int childPosition, long id) {
	            Log.d(tag,Integer.toString(childPosition));
	           // v.setBackgroundColor(0x000000);
	            return false;
	        }
	    });*/
		
	
		
    }
    public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {
        Log.i("Test",Integer.toString(childPosition));
        Log.i("test",Integer.toString(groupPosition));
        
        if(childPosition==0 && groupPosition==0)
        {
        	//Log.i("yes","it works");
        	//Toast.makeText(getApplicationContext(), "hi rahul",Toast.LENGTH_SHORT);
        	//ture(Window.FEATURE_NO_TITLE);
             // final Dialog dialog = new Dialog(this, R.style.FullHeightDialog);
      		//dialog1.setContentView(R.layout.dialog);
      		//dialog1.show();
      		
        	
        }
        
        if(childPosition==0 && groupPosition==1)
        {
        	
             // dialog2.requestWindowFeature(Window.FEATURE_NO_TITLE);
             // final Dialog dialog = new Dialog(this, R.style.FullHeightDialog);
      		//dialog2.setContentView(R.layout.dialog);
      		//dialog2.show();
      		
        	
        }
        
        
        if(childPosition==0 && groupPosition==2)
        {
        	
             // dialog2.requestWindowFeature(Window.FEATURE_NO_TITLE);
             // final Dialog dialog = new Dialog(this, R.style.FullHeightDialog);
      		//dialog3.setContentView(R.layout.dialog);
      		//dialog3.show();
      		
        	
        }
        
        
        return false;
    }
    
    
            
        
 
    public class myExpandableAdapter extends BaseExpandableListAdapter {
 
    	private ArrayList<String> groups;
 
        private ArrayList<ArrayList<ArrayList<String>>> children;
 
    	private Context context;
 
    	public myExpandableAdapter(Context context, ArrayList<String> groups, ArrayList<ArrayList<ArrayList<String>>> children) {
            this.context = context;
            this.groups = groups;
            this.children = childs;
        }
 
 
    	@Override
        public boolean areAllItemsEnabled()
        {
            return true;
        }
 
 
        public ArrayList<String> getChild(int groupPosition, int childPosition) {
            return children.get(groupPosition).get(childPosition);
        }
 
        public long getChildId(int groupPosition, int childPosition) {
            return childPosition;
        }
 
 
    
        public View getChildView(int groupPosition, int childPosition, boolean isLastChild,View convertView, ViewGroup parent) {
 
        	String child = (String) ((ArrayList<String>)getChild(groupPosition, childPosition)).get(0);
 
            if (convertView == null) {
                LayoutInflater infalInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                convertView = infalInflater.inflate(R.layout.expandablelistview_child, null);
            }
 
           TextView childtxt = (TextView) convertView.findViewById(R.id.TextViewChild01);
           
         
            
           
 
            childtxt.setText(child);
            Typeface face1=Typeface.createFromAsset(getAssets(),"fonts/nevis.ttf");
            childtxt.setTypeface(face1);
           
           /* childtxt.setOnClickListener(new OnClickListener() {

                @Override
                public void onClick(View v) {
                   Log.d(tag,"button push");
                }
            });   */   
            
            
 
            return convertView;
           
    		
        }
        
       // setOnChildClickListener(myOnChildClick);
      /* OnChildClickListener my= new OnChildClickListener() {

	        @Override
	        public boolean onChildClick(ExpandableListView l, View convertView, int groupPosition, int childPosition,
	                long arg4) {
	        	
	        	Log.d(tag,Integer.toString(groupPosition));
	            // TODO Auto-generated method stub
	            return true;
	        }

	    };*/
 
        public int getChildrenCount(int groupPosition) {
            return children.get(groupPosition).size();
        }
 
        public String getGroup(int groupPosition) {
            return groups.get(groupPosition);
        }
 
        public int getGroupCount() {
            return groups.size();
        }
 
        public long getGroupId(int groupPosition) {
            return groupPosition;
        }
 
        public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
 
        	String group = (String) getGroup(groupPosition);
 
        	if (convertView == null) {
                LayoutInflater infalInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                convertView = infalInflater.inflate(R.layout.expandablelistview_group, null);
            }
 
            TextView grouptxt = (TextView) convertView.findViewById(R.id.TextViewGroup);
            
           
 
            grouptxt.setText(group);
            Typeface face=Typeface.createFromAsset(getAssets(),"fonts/nevis.ttf");
            grouptxt.setTypeface(face);
            grouptxt.setTextSize(25);
            grouptxt.setOnClickListener(new OnClickListener() {

            public void onClick(View v) {
               Log.d(tag,"button push");
            }
        });    
 
            return convertView;
        }
 
        public boolean hasStableIds() {
            return true;
        }
 
        public boolean isChildSelectable(int arg0, int arg1) {
            return true;
        }
 
    }
 
    private void loadData(){
    	groups= new ArrayList<String>();
    	childs= new ArrayList<ArrayList<ArrayList<String>>>();
    	dbg.open();
    	
    	 Cursor c=dbg.getAllContacts();
    	 String subjects_grades[][]=new String[c.getCount()][c.getColumnCount()];
    	 
  int i=0;
  
  if(c.getCount()==0)
  {
	  Toast.makeText(this, "Not Available", Toast.LENGTH_SHORT).show();
  }
  else
  {
 		if(c.moveToFirst())
 		{
 			System.out.println("true");
 			do
 			{
 			 subjects_grades[i][0]= c.getString(0);
 			 subjects_grades[i][1]=c.getString(1);
 			subjects_grades[i][2]=c.getString(2);
 			subjects_grades[i][3]=c.getString(3);
 			subjects_grades[i][4]=c.getString(4);
 			subjects_grades[i][5]=c.getString(5);
 			subjects_grades[i][6]=c.getString(6);
 			subjects_grades[i][7]=c.getString(7);
 			subjects_grades[i][8]=c.getString(8);
 			subjects_grades[i][9]=c.getString(9);
 			
 			i++;
 			}while(c.moveToNext());
 		}

    	
    	
    	
    	String rahul[]={"convener","proshows","dance"};
    	String rahul1[]={"hello","hi","good","job"};
    	for(int kk=0;kk<c.getCount();kk++)
    		groups.add(subjects_grades[kk][0]);
 
    	/*groups.add("Convener");
        groups.add("Proshows");
        groups.add("culturals n events");
        groups.add("Design n edit");
        groups.add("Gen.Enquiry");
        groups.add("Guestcare(studnts)");
        groups.add("Hall arrngmnts");
        groups.add("Printing");
        groups.add("Marketing");
        groups.add("Purchase");
        groups.add("Reception n rgstration");
        groups.add("Sales");
        groups.add("spcl guestcare");
        groups.add("sports");
        groups.add("sponsor n stall");*/
        
    	for(int kk=0;kk<c.getCount();kk++)
    	{
    		childs.add(new ArrayList<ArrayList<String>>());	
    		for(int j=1;j<10;j++)
    		{
    			childs.get(kk).add(new ArrayList<String>());
    			childs.get(kk).get(j-1).add(subjects_grades[kk][j]);
    		}
    	}
    
    	
    	
  }
 
       /* childs.add(new ArrayList<ArrayList<String>>());
        childs.get(0).add(new ArrayList<String>());
        childs.get(0).get(0).add("Prof. Amit Mahindrakar");
       // childs.get(0).add(new ArrayList<String>());
       /* childs.get(0).get(1).add("Held");
        childs.get(0).add(new ArrayList<String>());
        childs.get(0).get(2).add("Remaining");
        childs.get(0).add(new ArrayList<String>());
        childs.get(0).get(3).add("Attended");
        childs.get(0).add(new ArrayList<String>());
        childs.get(0).get(4).add("Missed");
        
        childs.add(new ArrayList<ArrayList<String>>());
        childs.get(1).add(new ArrayList<String>());
        childs.get(1).get(0).add("Krishna Gupta");
        childs.get(1).add(new ArrayList<String>());
        childs.get(1).get(1).add("Sadhna Sinha");
        childs.get(1).add(new ArrayList<String>());
        childs.get(1).get(2).add("Senthil Kumar");
        
        childs.add(new ArrayList<ArrayList<String>>());
        childs.get(2).add(new ArrayList<String>());
       
        childs.get(2).get(0).add("Sainand Angampally");
        childs.get(2).add(new ArrayList<String>());
        childs.get(2).get(1).add("Shobhika Mathur");
        
        childs.add(new ArrayList<ArrayList<String>>());
        childs.get(3).add(new ArrayList<String>());
        childs.get(3).get(0).add("Varun Ramani");
        
        childs.add(new ArrayList<ArrayList<String>>());//hall
        childs.get(4).add(new ArrayList<String>());
        childs.get(4).get(0).add("Annie Monica");
        childs.get(4).add(new ArrayList<String>());
        childs.get(4).get(1).add("Dharmika Chaudhary");
        
        
        childs.add(new ArrayList<ArrayList<String>>());//print
        childs.get(5).add(new ArrayList<String>());
        childs.get(5).get(0).add("Aditya Raj");
        
        
        childs.add(new ArrayList<ArrayList<String>>());
        childs.get(6).add(new ArrayList<String>());
        childs.get(6).get(0).add("Gembali Swaroop");
        
        
        childs.add(new ArrayList<ArrayList<String>>());
        childs.get(7).add(new ArrayList<String>());
        childs.get(7).get(0).add("Ashutosh Agarwal");
        
        childs.add(new ArrayList<ArrayList<String>>());
        childs.get(8).add(new ArrayList<String>());
        childs.get(8).get(0).add("Ayaz Mahmood");
        childs.get(8).add(new ArrayList<String>());
        childs.get(8).get(1).add("Smit Pandey ");
        
        childs.add(new ArrayList<ArrayList<String>>());
        childs.get(9).add(new ArrayList<String>());
        childs.get(9).get(0).add("Karthik. S");
        
        
        childs.add(new ArrayList<ArrayList<String>>());
        childs.get(10).add(new ArrayList<String>());
        childs.get(10).get(0).add("Alban Prashanth");
        childs.get(10).add(new ArrayList<String>());
        childs.get(10).get(1).add("Nithin Katragadda");
        
        childs.add(new ArrayList<ArrayList<String>>());
        childs.get(11).add(new ArrayList<String>());
        childs.get(11).get(0).add("Bonala Sai Sharan");
        
        childs.add(new ArrayList<ArrayList<String>>());
        childs.get(12).add(new ArrayList<String>());
        childs.get(12).get(0).add("Ashutosh Agarwal");
        childs.get(12).add(new ArrayList<String>());
        childs.get(12).get(1).add("Varun Ramani");
        
        childs.add(new ArrayList<ArrayList<String>>());
        childs.get(13).add(new ArrayList<String>());
        childs.get(13).get(0).add("Garishma Keyal");
        childs.get(13).add(new ArrayList<String>());
        childs.get(13).get(1).add("Neal Manjeshwar");
        
        childs.add(new ArrayList<ArrayList<String>>());
        childs.get(14).add(new ArrayList<String>());
        childs.get(14).get(0).add("Mohan Prashanth ");
        childs.get(14).add(new ArrayList<String>());
        childs.get(14).get(1).add("Sashi Kumar");*/
        
        
       
        
       
        
       /* myExpandableAdapter m=new myExpandableAdapter(this,groups,childs);
        ArrayList<String> s=m.getChild(0, 1);
        
        for(int i=0;i<s.size();i++)
        {
        	Toast.makeText(getApplicationContext(), s.get(i), Toast.LENGTH_SHORT).show();
        	Log.d(tag,s.get(i));
        }*/
       
 
       
    }
    
    
   

    
    
    
   
}
